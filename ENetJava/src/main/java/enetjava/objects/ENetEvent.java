/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enetjava.objects;

import enetjava.ENetLib;
import lombok.Getter;

import java.nio.ByteBuffer;

public class ENetEvent {

    @Getter
    private ByteBuffer nativeState;

    public ENetEvent() {
        this(ByteBuffer.allocateDirect(ENetLib.sizeof()));
    }
    
    public ENetEvent(ByteBuffer nativeState)
    {
        this.nativeState = nativeState;
    }

    /**
     * Get the ENetPeer
     *
     * @return the ENetPeer if available, null otherwise
     */
    public ENetPeer getPeer() {
        ByteBuffer peer = ENetLib.peer(nativeState);
        if (peer == null)
            return null;
        return new ENetPeer(peer);
    }

    /**
     * Get the event type
     *
     * @return event type {@link ENetEvent.Type}
     */
    public Type getType() {
        return ENetLib.type(nativeState);
    }

    /**
     * Get the channel ID
     *
     * @return The channel ID
     */
    public int getChannelID() {
        return ENetLib.channelID(nativeState) & 0xFF;
    }

    /**
     * Get the packet data
     *
     * @return the packet data
     */
    public int getData() {
        return ENetLib.data(nativeState);
    }

    /**
     * Get the ENetPacket via native state
     *
     * @return ENetPacket if available, null otherwise
     */
    public ENetPacket getPacket() {
        ByteBuffer packet = ENetLib.packet(nativeState);
        if (packet == null)
            return null;
        return new ENetPacket(packet);
    }

    public enum Type {
        NONE,
        CONNECT,
        DISCONNECT,
        RECEIVE
    }
}
