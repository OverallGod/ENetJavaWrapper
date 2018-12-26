/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enetjava.objects;

import enetjava.ENetLib;
import java.nio.ByteBuffer;
import java.util.EnumSet;

public class ENetPacket {
    
    public static ByteBuffer nativeState;
    private ByteBuffer buffer;
    
    public static enum Flag
    {
        RELIABLE (1<<0),
        UNSEQUENCED (1<<1),
        UNRELIABLE_FRAGMENT (1<<3);
        
        public final int bitValue;
        
        private Flag(int bitValue)
        {
            this.bitValue = bitValue;
        }
        
        static int toBits(EnumSet<Flag> set)
        {
            int ret = 0;
            for (Flag f : set)
                ret |= f.bitValue;
            return ret;
        }
        
        static EnumSet<Flag> fromBits(int bits)
        {
            EnumSet<Flag> ret = EnumSet.noneOf(Flag.class);
            for (Flag f : EnumSet.allOf(Flag.class))
            {
                if ((bits & f.bitValue) != 0)
                    ret.add(f);
            }
            return ret;
        }
    }
    
    boolean owned;
    
    public ENetPacket(ByteBuffer nativeState)
    {
        this.nativeState = nativeState;
        owned = false;
    }
    
    public ENetPacket(ByteBuffer buffer, EnumSet<Flag> flags)
        throws EnetException
    {
        if (!buffer.isDirect())
        {
            this.buffer = ByteBuffer.allocateDirect(buffer.remaining());
            this.buffer.put(buffer.duplicate());
        }
        else
            this.buffer = buffer;
        this.nativeState = ENetLib.create_packet(this.buffer, Flag.toBits(flags));
        owned = true;
    }
    
    public ENetPacket(byte[] bytes, int offset, int length, EnumSet<Flag> flags)
        throws EnetException
    {
        this(ByteBuffer.wrap(bytes, offset, length), flags);
    }
    
    public ENetPacket(byte[] bytes, EnumSet<Flag> flags) throws EnetException
    {
        this(bytes, 0, bytes.length, flags);
    }
    
    public ByteBuffer getBytes() throws EnetException
    {
        if (buffer == null)
            buffer = ENetLib.get_bytes(nativeState);
        return buffer;
    }
    
    public EnumSet<Flag> getFlags() throws EnetException
    {
        return Flag.fromBits(ENetLib.get_flags(nativeState));
    }
    
    protected void finalize() throws Throwable
    {
        if (owned)
            ENetLib.destroy_packet(nativeState);
        super.finalize();
    }
}