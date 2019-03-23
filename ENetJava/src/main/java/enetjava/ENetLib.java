package enetjava;

import enetjava.objects.ENetEvent.Type;
import enetjava.objects.ENetException;
import enetjava.objects.MutableInteger;
import java.nio.ByteBuffer;

public class ENetLib {

    /**
     * Initialize the library
     *
     * @param libPath : The lib path
     * @param debug : Should we enable debug message?
     */
    public static void initialize(String libPath, boolean debug) {
        try {
            System.load(libPath);
        } catch (UnsatisfiedLinkError e) {
            if (debug) System.err.println("Failed to load the library on " + libPath);
            e.printStackTrace();
            return;
        }
        if (debug) System.out.println("Lib found on " + libPath + ". ENetLib has been loaded!");
    }
     
    public static native void enet_initialize();
    public static native void enet_deinitialize();
    
    // For ENetHost (All method here are related to ENetHost)
    
    /**
     * ENet method : enet_host_create
     * Available in : ENetHost's Constructor
     * 
     * @return a new ByteBuffer
     * @throws ENetException : If failed to create!
     */
    public static native ByteBuffer create_host(String address, int port, int peerCount, int channelLimit, int incomingBandwidth, int outgoingBandwidth) throws ENetException;

    /**
     * ENet method : enet_host_create
     * Available in : ENetHost's Constructor
     *
     * This will create a "null" host (Basically, no address specified)
     * @return a new ByteBuffer
     * @throws ENetException : If failed to create!
     */
    public static native ByteBuffer create_null_host(int peerCount, int channelLimit, int incomingBandwidth, int outgoingBandwidth) throws ENetException;
    
    /**
     * ENet method : enet_host_connect
     * Available in : ENetHost's Method
     * 
     * @return a new ByteBuffer
     * @throws ENetException : If failed to connect
     */
    public static native ByteBuffer connect(ByteBuffer ctx, String address, int port, int channelCount, int data) throws ENetException;
    
    /**
     * ENet method : enet_host_broadcast
     * Available in : ENetHost's Method
     * 
     * @param channelID : The channel ID 
     * @param packet : The packet object
     */
    public static native void broadcast(ByteBuffer ctx, int channelID, ByteBuffer packet);
    
    /**
     * ENet method : enet_host_channel_limit
     * Available in : ENetHost's Method
     * 
     * @param channelLimit : The new channel limit
     */
    public static native void channel_limit(ByteBuffer ctx, int channelLimit);
    
    /**
     * ENet method : enet_host_bandwidth_limit
     * Available in : ENetHost's Method
     * 
     * @param in : The new incoming bandwidth value
     * @param out : The new outgoing bandwidth value
     */
    public static native void bandwidth_limit(ByteBuffer ctx, int in, int out);
    
    /**
     * ENet method : enet_host_flush
     * Available in : ENetHost's Method
     * 
     * @param ctx : The EnetHost object's ByteBuffer
     */
    public static native void flush(ByteBuffer ctx);
    
    /**
     * ENet method : enet_host_check_events
     * Available in : ENetHost's Method
     * 
     * @param ctx : The ENetHost's byte buffer
     * @param event : The ENetEvent's byte buffer
     * @return
     * @throws ENetException : If failed to check the event
     */
    public static native int checkEvents(ByteBuffer ctx, ByteBuffer event) throws ENetException;
    
    /**
     * ENet method : enet_host_service
     * Available in : ENetHost's Method
     * 
     * @param timeout : The timeout value
     * @param event : The ENetEvent's byte buffer value
     * @return
     * @throws ENetException : If failed to start the service
     */
    public static native int service(ByteBuffer ctx, int timeout, ByteBuffer event) throws ENetException;
    
    /**
     * ENet method : enet_host_destroy
     * Available in : ENetHost's Method (finalize)
     * 
     * @param ctx : The EnetHost's byte buffer value
     * @throws ENetException : If failed to destroy
     */
    public static native void destroy_host(ByteBuffer ctx) throws ENetException;
    
    /**
     * ENet method : enet_crc32
     * Available in : ENetHost's Method
     * 
     * This actually kinda different from the ENet one
     * this will automatically set the host to use crc32 as the checksum method
     * 
     * @param ctx : The ENetHost's byte buffer value
     */
    public static native void set_crc32(ByteBuffer ctx);
    
    /**
     * ENet method : enet_host_compress_with_range_coder
     * Available in : ENetHost's Method
     * 
     * @param ctx : The ENetHost's byte buffer value
     */
    public static native void compress_with_range_coder(ByteBuffer ctx);

    // TODO : Create documentation for this methods below

    // For ENetEvent (All method here are related to ENetEvent)
    public static native int sizeof();
    public static native ByteBuffer peer(ByteBuffer ctx);
    public static native Type type(ByteBuffer ctx);
    public static native byte channelID(ByteBuffer ctx);
    public static native int data(ByteBuffer ctx);
    public static native ByteBuffer packet(ByteBuffer ctx);
    
    // For ENetPeer (All method here are related to ENetPeer)
    public static native void throttleConfigure(ByteBuffer ctx, int interval, int acceleration, int deceleration);
    public static native void send(ByteBuffer ctx, int channelID, ByteBuffer packet);
    public static native ByteBuffer receive(ByteBuffer ctx, MutableInteger channelID);
    public static native void ping(ByteBuffer ctx);
    public static native void disconnect_now(ByteBuffer ctx, int data);
    public static native void disconnect(ByteBuffer ctx, int data);
    public static native void disconnect_later(ByteBuffer ctx, int data);
    public static native byte[] get_address(ByteBuffer ctx);
    public static native int get_port(ByteBuffer ctx);
    public static native void reset(ByteBuffer ctx);
    
    // For EnetPacket (All method here area related to ENetPacket)
    public static native ByteBuffer create_packet(ByteBuffer data, int flags) throws ENetException;
    public static native ByteBuffer get_bytes(ByteBuffer ctx) throws ENetException;
    public static native int get_flags(ByteBuffer ctx) throws ENetException;
    public static native void destroy_packet(ByteBuffer ctx) throws ENetException;
}
