/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enetjava.objects;

import enetjava.ENetLib;
import lombok.Getter;

import java.nio.ByteBuffer;
import java.util.EnumSet;

public class ENetPacket {

    @Getter
    private ByteBuffer nativeState;
    @Getter
    private ByteBuffer buffer;
    @Getter
    boolean owned;
    
    public ENetPacket(ByteBuffer nativeState) {
        this.nativeState = nativeState;
        this.owned = false;
    }
    
    public ENetPacket(ByteBuffer buffer, EnumSet<Flag> flags) throws ENetException {
        if (!buffer.isDirect()) {
            this.buffer = ByteBuffer.allocateDirect(buffer.remaining());
            this.buffer.put(buffer.duplicate());
        } else {
            this.buffer = buffer;
        }
        this.nativeState = ENetLib.create_packet(this.buffer, Flag.toBits(flags));
        this.owned = true;
    }

    public ENetPacket(byte[] bytes, int offset, int length, EnumSet<Flag> flags) throws ENetException {
        this(ByteBuffer.wrap(bytes, offset, length), flags);
    }

    public ENetPacket(byte[] bytes, EnumSet<Flag> flags) throws ENetException {
        this(bytes, 0, bytes.length, flags);
    }

    /**
     * Get the packet bytes
     *
     * @return The bytes of the packet
     * @throws ENetException if something goes wrong
     */
    public ByteBuffer getBytes() throws ENetException {
        if (buffer == null) buffer = ENetLib.get_bytes(nativeState);
        return buffer;
    }

    /**
     * Get the flags for this packet
     *
     * @return the flags as {@link ENetPacket.Flag}
     * @throws ENetException if something goes wrong
     */
    public EnumSet<Flag> getFlags() throws ENetException {
        return Flag.fromBits(ENetLib.get_flags(nativeState));
    }

    /**
     * Finalize or destroy the packet
     *
     * @throws Throwable if something goes wrong
     */
    public void destroy() throws Throwable {
        if (owned) ENetLib.destroy_packet(nativeState);
        super.finalize();
    }

    public enum Flag {
        RELIABLE (1<<0),
        UNSEQUENCED (1<<1),
        UNRELIABLE_FRAGMENT (1<<3);

        @Getter
        public final int bitValue;

        Flag(int bitValue)
        {
            this.bitValue = bitValue;
        }

        public static int toBits(EnumSet<Flag> set) {
            int ret = 0;
            for (Flag f : set)
                ret |= f.bitValue;
            return ret;
        }

        public static EnumSet<Flag> fromBits(int bits) {
            EnumSet<Flag> ret = EnumSet.noneOf(Flag.class);
            for (Flag f : EnumSet.allOf(Flag.class)) {
                if ((bits & f.bitValue) != 0)
                    ret.add(f);
            }
            return ret;
        }
    }
}