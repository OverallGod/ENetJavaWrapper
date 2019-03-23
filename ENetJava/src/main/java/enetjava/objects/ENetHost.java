/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enetjava.objects;

import enetjava.ENetLib;
import lombok.Getter;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.util.concurrent.TimeUnit;

public class ENetHost {

    @Getter
    private ByteBuffer nativeState;
    @Getter
    private InetSocketAddress address;
    @Getter
    private boolean useCrc32;
    @Getter
    private boolean compressWithRangeCoder;
    @Getter
    private int peerCount, channelLimit, incomingBandwidth, outgoingBandwidth;

    public ENetHost(InetSocketAddress address, int peerCount, int channelLimit, int incomingBandwidth, int outgoingBandwidth) throws ENetException {
        if (address != null) {
            this.address = address;
            this.peerCount = peerCount;
            this.channelLimit = channelLimit;
            this.incomingBandwidth = incomingBandwidth;
            this.outgoingBandwidth = outgoingBandwidth;
            this.nativeState = ENetLib.create_host(address.getHostName(), address.getPort(), peerCount, channelLimit, incomingBandwidth, outgoingBandwidth);
        } else {
            this.peerCount = peerCount;
            this.channelLimit = channelLimit;
            this.incomingBandwidth = incomingBandwidth;
            this.outgoingBandwidth = outgoingBandwidth;
            this.nativeState = ENetLib.create_null_host(peerCount, channelLimit, incomingBandwidth, outgoingBandwidth);
        }
    }

    /**
     * Connect a new client
     *
     * @param address : The address to connect to
     * @param channelCount : Channel count
     * @param data : datas
     * @return ENetPeer if succeeded, null otherwise
     * @throws ENetException if something bad happens
     */
    public ENetPeer connectPeer(InetSocketAddress address, int channelCount, int data) throws ENetException {
        return new ENetPeer(ENetLib.connect(nativeState, address.getHostName(), address.getPort(), channelCount, data));
    }

    /**
     * Broadcast a packet
     *
     * @param channelID : The channel ID
     * @param packet : The packet to broadcast
     */
    public void broadcastPacket(int channelID, ENetPacket packet) {
        ENetLib.broadcast(nativeState, channelID, packet.getNativeState());
    }

    /**
     * Set the chanel limit
     *
     * @param channelLimit : New channel limit
     */
    public void setChannelLimit(int channelLimit) {
        this.channelLimit = channelLimit;
        ENetLib.channel_limit(nativeState, channelLimit);
    }

    /**
     * Set the bandWidthLimit for this host
     *
     * @param incomingBandwidth : New value
     * @param outgoingBandwidth : New value
     */
    public void setBandwidthLimit(int incomingBandwidth, int outgoingBandwidth) {
        this.incomingBandwidth = incomingBandwidth;
        this.outgoingBandwidth = outgoingBandwidth;
        ENetLib.bandwidth_limit(nativeState, incomingBandwidth, outgoingBandwidth);
    }

    /**
     * Flush the host
     */
    public void flush() {
        ENetLib.flush(nativeState);
    }

    /**
     * Check for events
     *
     * @return a ENetEvent if succeeded null otherwise
     * @throws ENetException if something goes wrong
     */
    public ENetEvent checkEvents() throws ENetException {
        ENetEvent event = new ENetEvent();
        int ret = ENetLib.checkEvents(nativeState, event.getNativeState());
        if (ret <= 0)
            return null;
        return event;
    }

    /**
     * Start the ENetService, will run on main thread
     *
     * @param timeout : The timeout
     * @return a ENetEvent if succeeded, null otherwise
     * @throws ENetException if something goes wrong
     */
    public ENetEvent startService(int timeout) throws ENetException {
        ENetEvent event = new ENetEvent();
        int ret = ENetLib.service(nativeState, timeout, event.getNativeState());
        if (ret <= 0)
            return null;
        return event;
    }

    /**
     * Start the ENetService, will run on main thread
     *
     * @param timeout : The timeout
     * @param unit : The TimeUnit, useful for 1 min time or more
     * @return a ENetEvent if succeeded, null otherwise
     * @throws ENetException if something goes wrong
     */
    public ENetEvent startService(long timeout, TimeUnit unit) throws ENetException {
        return startService((int) TimeUnit.MILLISECONDS.convert(timeout, unit));
    }

    /**
     * Should we use crc32? (Checksum)
     *
     * @param useCrc32 : Should we?
     */
    public void setUseCrc32(boolean useCrc32) {
        if (this.useCrc32 && !useCrc32) throw new IllegalStateException("Cannot set using crc32 to false if its already true!");
        this.useCrc32 = useCrc32;
        ENetLib.set_crc32(nativeState);
    }

    /**
     * Should we compress host with range coder?
     *
     * @param compressWithRangeCoder : Should we?
     */
    public void setCompressWithRangeCoder(boolean compressWithRangeCoder) {
        if (this.compressWithRangeCoder && !compressWithRangeCoder) throw new IllegalStateException("Cannot set using compressWithRangeCoder to false if its already true!");
        this.compressWithRangeCoder = compressWithRangeCoder;
        ENetLib.compress_with_range_coder(nativeState);
    }

    @Override
    protected void finalize() throws Throwable {
        ENetLib.destroy_host(nativeState);
        super.finalize();
    }
}
