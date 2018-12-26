/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enetjava.objects;

import enetjava.ENetLib;
import java.nio.ByteBuffer;

/**
 *
 * @author anom
 */
public class ENetEvent {
    
    public static ByteBuffer nativeState;
    
    public static enum Type
    {
        None,
        Connect,
        Disconnect,
        Receive
    }
    
    public ENetEvent() {
        this(ByteBuffer.allocateDirect(ENetLib.sizeof()));
    }
    
    public ENetEvent(ByteBuffer nativeState)
    {
        this.nativeState = nativeState;
    }
    
    public ENetPeer peer()
    {
        ByteBuffer peer = ENetLib.peer(nativeState);
        if (peer == null)
            return null;
        return new ENetPeer(peer);
    }
    
    public Type type()
    {
        return ENetLib.type(nativeState);
    }
    
    public int channelID()
    {
        return ENetLib.channelID(nativeState) & 0xFF;
    }
    
    public int data()
    {
        return ENetLib.data(nativeState);
    }
    
    public ENetPacket packet()
    {
        ByteBuffer packet = ENetLib.packet(nativeState);
        if (packet == null)
            return null;
        return new ENetPacket(packet);
    }
}
