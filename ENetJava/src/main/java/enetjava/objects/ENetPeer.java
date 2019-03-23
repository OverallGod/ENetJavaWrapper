/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enetjava.objects;

import enetjava.ENetLib;
import lombok.Getter;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;

public class ENetPeer {

    @Getter
    private ByteBuffer nativeState;
    
    public ENetPeer(ByteBuffer nativeState)
    {
        this.nativeState = nativeState;
    }

    /**
     * Unknown
     *
     * @param interval : integer
     * @param acceleration : integer
     * @param deceleration : integer
     */
    public void throttleConfigure(int interval, int acceleration, int deceleration) {
        ENetLib.throttleConfigure(nativeState, interval, acceleration, deceleration);
    }

    /**
     * Send a packet to that channel ID
     *
     * @param channelID : The channel ID
     * @param packet : The packet
     */
    public void sendPacket(int channelID, ENetPacket packet) {
        ENetLib.send(nativeState, channelID, packet.getNativeState());
        packet.owned = false;
    }

    /**
     * Receive a packet from that channel ID
     *
     * @param channelID : The channel ID as MutableInteger
     * @return the packet if succeeded, null otherwise
     */
    public ENetPacket receivePacket(MutableInteger channelID) {
        ByteBuffer b = ENetLib.receive(nativeState, channelID);
        if (b == null) return null;
        return new ENetPacket(b);
    }

    /**
     * Ping the peer
     */
    public void pingPeer() {
        ENetLib.ping(nativeState);
    }

    /**
     * Disconnect peer (immediately)
     *
     * @param data : Data to send
     */
    public void disconnectPeerNow(int data) {
        ENetLib.disconnect_now(nativeState, data);
    }

    /**
     * Disconnect peer
     *
     * @param data : Data to send
     */
    public void disconnectPeer(int data) {
        ENetLib.disconnect(nativeState, data);
    }

    /**
     * Disconnect peer (later)
     *
     * @param data : Data to send
     */
    public void disconnectPeerLater(int data) {
        ENetLib.disconnect_later(nativeState, data);
    }

    /**
     * Reset the peer
     */
    public void reset() {
        ENetLib.reset(nativeState);
    }

    /**
     * Get the peer address
     *
     * @return an InetSocketAddress if succeeded, null otherwise
     */
    public InetSocketAddress getPeerAddress() {
        try {
            return new InetSocketAddress(InetAddress.getByAddress(ENetLib.get_address(nativeState)), ENetLib.get_port(nativeState));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return null;
    }
}
