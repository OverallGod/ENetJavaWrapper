/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enetjava.objects;

import enetjava.ENetLib;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;

public class ENetPeer {
    
    private ByteBuffer nativeState;
    
    public ENetPeer(ByteBuffer nativeState)
    {
        this.nativeState = nativeState;
    }
    
    public void throttleConfigure(int interval, int acceleration, int deceleration)
    {
        ENetLib.throttleConfigure(nativeState, interval, acceleration, deceleration);
    }
    
    public void send(int channelID, ENetPacket packet)
        throws EnetException
    {
        ENetLib.send(nativeState, channelID, packet.nativeState);
        packet.owned = false;
    }
    
    public ENetPacket receive(MutableInteger channelID) throws EnetException
    {
        ByteBuffer b = ENetLib.receive(nativeState, channelID);
        if (b == null)
            return null;
        return new ENetPacket(b);
    }

    public void ping()
    {
        ENetLib.ping(nativeState);
    }
    
    public void disconnectNow(int data)
    {
        ENetLib.disconnect_now(nativeState, data);
    }
    
    public void disconnect(int data)
    {
        ENetLib.disconnect(nativeState, data);
    }
    
    public void disconnectLater(int data)
    {
        ENetLib.disconnect_later(nativeState, data);
    }
    
    public void reset()
    {
        ENetLib.reset(nativeState);
    }
    
    public InetSocketAddress address() throws UnknownHostException
    {
        return new InetSocketAddress(InetAddress.getByAddress(ENetLib.get_address(nativeState)), ENetLib.get_port(nativeState));
    }
}
