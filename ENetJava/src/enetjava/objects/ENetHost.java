/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enetjava.objects;

import enetjava.ENetLib;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.concurrent.TimeUnit;

public class ENetHost {
    
    private ByteBuffer nativeState;
    private InetSocketAddress address;
        
    public ENetHost(InetSocketAddress address, int peerCount, int channelLimit, int incomingBandwidth, int outgoingBandwidth)
        throws EnetException
    {
        this.address = address;
        nativeState = ENetLib.create_host(address.getHostName(), address.getPort(), peerCount, channelLimit, incomingBandwidth, outgoingBandwidth);
    }
    
    public ENetPeer connect(InetSocketAddress address, int channelCount, int data)
        throws EnetException
    {
        return new ENetPeer(ENetLib.connect(nativeState, address.getHostName(), address.getPort(), channelCount, data));
    }
    
    public void broadcast(int channelID, ENetPacket packet)
    {
        ENetLib.broadcast(nativeState, channelID, packet.nativeState);
    }
    
    public void channelLimit(int channelLimit)
    {
        ENetLib.channel_limit(nativeState, channelLimit);
    }
    
    public void bandwidthLimit(int incomingBandwidth, int outgoingBandwidth)
    {
        ENetLib.bandwidth_limit(nativeState, incomingBandwidth, outgoingBandwidth);
    }
    
    public void flush()
    {
        ENetLib.flush(nativeState);
    }
    
    public ENetEvent checkEvents() throws EnetException
    {
        ENetEvent event = new ENetEvent();
        int ret = ENetLib.checkEvents(nativeState, event.nativeState);
        if (ret <= 0)
            return null;
        return event;
    }
    
    public ENetEvent service(int timeout) throws EnetException
    {
        ENetEvent event = new ENetEvent();
        int ret = ENetLib.service(nativeState, timeout, event.nativeState);
        if (ret <= 0)
            return null;
        return event;
    }
    
    public ENetEvent service(long timeout, TimeUnit unit) throws EnetException
    {
        return service((int) TimeUnit.MILLISECONDS.convert(timeout, unit));
    }

    public InetSocketAddress getAddress() {
        return address;
    }
    
    public void useCrc32() {
        ENetLib.set_crc32(nativeState);
    }
    
    public void compressWithRangeCoder() {
        ENetLib.compress_with_range_coder(nativeState);
    }
    
    @Override
    protected void finalize() throws Throwable
    {
        ENetLib.destroy_host(nativeState);
        super.finalize();
    }
}
