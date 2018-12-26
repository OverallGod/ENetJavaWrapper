#include "ENetJavaLib.h"
#include <stdio.h>
#include "enet/enet.h"

JNIEXPORT void JNICALL Java_enetjava_ENetLib_enet_1initialize 
(JNIEnv * env, jclass cls) {
    enet_initialize();
}

JNIEXPORT void JNICALL Java_enetjava_ENetLib_enet_1deinitialize 
(JNIEnv * env, jclass cls) {
    enet_deinitialize();
}

JNIEXPORT jobject JNICALL Java_enetjava_ENetLib_create_1host
  (JNIEnv * env, jclass cls, jstring add, jint port, jint peerCount, jint channelLimit, jint incoming, jint outgoing) {
    
    ENetAddress addr;
    const char * nativeString = (*env)->GetStringUTFChars(env, add, 0);
    enet_address_set_host(&addr, nativeString);
    addr.port = (enet_uint32) port;
    ENetHost * host = enet_host_create(&addr, peerCount, channelLimit, (enet_uint32) incoming, (enet_uint32) outgoing);
    if (host == NULL) {
        (*env)->ThrowNew(env, (*env)->FindClass(env, "enetjava/objects/EnetException"), "Failed to create ENetHost!");
        return NULL;
    }
    return (*env)->NewDirectByteBuffer(env, host, sizeof(ENetHost));
}

JNIEXPORT jobject JNICALL Java_enetjava_ENetLib_connect
  (JNIEnv * env, jclass cls, jobject obj, jstring add, jint port, jint channelCount, jint data) {
    ENetHost * host = (ENetHost *) (*env)->GetDirectBufferAddress(env, obj);
    
    if (host != NULL) {
        ENetAddress addr;
        const char * nativeString = (*env)->GetStringUTFChars(env, add, 0);
        enet_address_set_host(&addr, add);
        addr.port = (enet_uint32) port;
        
        ENetPeer * peer = enet_host_connect(host, &addr, channelCount, data);
        
        if (peer == NULL) {
            (*env)->ThrowNew(env, (*env)->FindClass(env, "enetjava/objects/EnetException"), "Failed to connect ENetPeer!");
            return NULL;
        }
        
        return (*env)->NewDirectByteBuffer(env, peer, sizeof(ENetPeer));
    }
    
    return NULL;
}

JNIEXPORT void JNICALL Java_enetjava_ENetLib_broadcast
  (JNIEnv * env, jclass cls, jobject obj, jint channel, jobject packet) {
    ENetHost * host = (ENetHost *) (*env)->GetDirectBufferAddress(env, obj);
    ENetPacket * p = (ENetPacket *) (*env)->GetDirectBufferAddress(env, packet);
    
    enet_host_broadcast(host, channel, p);
}

JNIEXPORT void JNICALL Java_enetjava_ENetLib_channel_1limit
  (JNIEnv * env, jclass cls, jobject obj, jint channelLimit) {
    ENetHost *host = (ENetHost *) (*env)->GetDirectBufferAddress(env, obj);
    
    if (host != NULL) {
        enet_host_channel_limit(host, channelLimit);
    }
}

JNIEXPORT void JNICALL Java_enetjava_ENetLib_bandwidth_1limit
  (JNIEnv * env, jclass cls, jobject obj, jint inbw, jint outbw) {
    ENetHost *host = (ENetHost *) (*env)->GetDirectBufferAddress(env, cls);
    
    if (host != NULL) {
        enet_host_bandwidth_limit(host, inbw, outbw);
    }
}

JNIEXPORT void JNICALL Java_enetjava_ENetLib_flush
  (JNIEnv * env, jclass cls, jobject obj) {
    ENetHost *host = (ENetHost *) (*env)->GetDirectBufferAddress(env, obj);
    
    if (host != NULL) {
        enet_host_flush(host);
    }
}

JNIEXPORT jint JNICALL Java_enetjava_ENetLib_checkEvents
  (JNIEnv * env, jclass cls, jobject obj, jobject evnt) {
   
    ENetHost *host = (ENetHost *) (*env)->GetDirectBufferAddress(env, obj);
    if (host != NULL)
	{
            ENetEvent *event = NULL;
            if (evnt != NULL)
                    event = (ENetEvent *) (*env)->GetDirectBufferAddress(env, evnt);
            int ret = enet_host_check_events(host, event);
            if (ret < 0)
            {
                    (*env)->ThrowNew(env, (*env)->FindClass(env, "enetjava/objects/EnetException"), "Failed to check ENetEvent!");
                    return -1;
            }
            return ret;
	}
    return 0;
}

JNIEXPORT jint JNICALL Java_enetjava_ENetLib_service
  (JNIEnv * env, jclass cls, jobject obj, jint timeout, jobject ev) {
    
    ENetHost *host = (ENetHost *) (*env)->GetDirectBufferAddress(env, obj);
    if (host != NULL) {
        ENetEvent *event = NULL;
        if (ev != NULL)
                event = (ENetEvent *) (*env)->GetDirectBufferAddress(env, ev);
        int ret = enet_host_service(host, event, timeout);
        if (ret < 0)
        {
            (*env)->ThrowNew(env, (*env)->FindClass(env, "enetjava/objects/EnetException"), "Failed to start service host!");
            return -1;
        }
        return ret;
    }
    return 0;
}

JNIEXPORT void JNICALL Java_enetjava_ENetLib_destroy_1host
  (JNIEnv * env, jclass cls, jobject obj) {
    ENetHost * host = (ENetHost *) (*env)->GetDirectBufferAddress(env, obj);
    
    if (host != NULL) {
        enet_host_destroy(host);
    }
}

JNIEXPORT void JNICALL Java_enetjava_ENetLib_set_1crc32
  (JNIEnv * env, jclass cls, jobject obj) {
    ENetHost * host = (ENetHost *) (*env)->GetDirectBufferAddress(env, obj);
    if (host != NULL) host->checksum = enet_crc32;
}

JNIEXPORT void JNICALL Java_enetjava_ENetLib_compress_1with_1range_1coder
  (JNIEnv * env, jclass cls, jobject obj) {
    ENetHost * host = (ENetHost *) (*env)->GetDirectBufferAddress(env, obj);
    if (host != NULL) enet_host_compress_with_range_coder(host);
}

JNIEXPORT jint JNICALL Java_enetjava_ENetLib_sizeof
  (JNIEnv * env, jclass cls) {
    return sizeof(ENetEvent);
}

JNIEXPORT jobject JNICALL Java_enetjava_ENetLib_peer
  (JNIEnv * env, jclass cls, jobject obj) {
    ENetEvent * event = (*env)->GetDirectBufferAddress(env, obj);
    
    if (event != NULL) {
        if (event->peer != NULL) {
            return (*env)->NewDirectByteBuffer(env, event->peer, sizeof(ENetPeer));
        }
    }
    return NULL;
}

JNIEXPORT jobject JNICALL Java_enetjava_ENetLib_type
  (JNIEnv * env, jclass cls, jobject obj) {
    ENetEvent * event = (*env)->GetDirectBufferAddress(env, obj);
    
    if (event != NULL) {
        jclass enumClass = (*env)->FindClass(env, "enetjava/objects/ENetEvent$Type");
        if (enumClass == NULL) return NULL;
        jfieldID field = NULL;
        switch (event->type) {
            case ENET_EVENT_TYPE_CONNECT:
                field = (*env)->GetStaticFieldID(env, enumClass, "Connect", "Lenetjava/objects/ENetEvent$Type;");
                break;
            case ENET_EVENT_TYPE_DISCONNECT:
                field = (*env)->GetStaticFieldID(env, enumClass, "Disconnect", "Lenetjava/objects/ENetEvent$Type;");
                break;
            case ENET_EVENT_TYPE_NONE:
                field = (*env)->GetStaticFieldID(env, enumClass, "None", "Lenetjava/objects/ENetEvent$Type;");
                break;
            case ENET_EVENT_TYPE_RECEIVE:
                field = (*env)->GetStaticFieldID(env, enumClass, "Receive", "Lenetjava/objects/ENetEvent$Type;");
                break;
        }
        
        if (field != NULL) {
            return (*env)->GetStaticObjectField(env, enumClass, field);
        }
    }
    return NULL;
}

JNIEXPORT jbyte JNICALL Java_enetjava_ENetLib_channelID
  (JNIEnv * env, jclass cls, jobject obj) {
    ENetEvent * event = (*env)->GetDirectBufferAddress(env, obj);
    if (event == NULL) return 0;
    return (jbyte) event->channelID;
}

JNIEXPORT jint JNICALL Java_enetjava_ENetLib_data
  (JNIEnv * env, jclass cls, jobject obj) {
    ENetEvent *event = (*env)->GetDirectBufferAddress(env, obj);
    if (event == NULL) return 0;
    return event->data;
}

JNIEXPORT jobject JNICALL Java_enetjava_ENetLib_packet
  (JNIEnv * env, jclass cls, jobject obj) {
    ENetEvent *event = (*env)->GetDirectBufferAddress(env, obj);
    if (event != NULL)
    {
        if (event->packet != NULL) return (*env)->NewDirectByteBuffer(env, event->packet, sizeof(ENetPacket));
    }
    return NULL;
}

JNIEXPORT void JNICALL Java_enetjava_ENetLib_throttleConfigure
  (JNIEnv * env, jclass cls, jobject obj, jint interval, jint acc, jint decc) {
    ENetPeer *peer = (ENetPeer *) (*env)->GetDirectBufferAddress(env, obj);
    enet_peer_throttle_configure(peer, interval, acc, decc);
}

JNIEXPORT void JNICALL Java_enetjava_ENetLib_send
  (JNIEnv * env, jclass cls, jobject obj, jint channel, jobject packet) {
    
    ENetPeer *peer = (ENetPeer *) (*env)->GetDirectBufferAddress(env, obj);
    ENetPacket *_packet = (ENetPacket *) (*env)->GetDirectBufferAddress(env, packet);
    int ret = enet_peer_send(peer, (enet_uint8) channel, _packet);
    if (ret != 0)
    {
        (*env)->ThrowNew(env, (*env)->FindClass(env, "enetjava/objects/EnetException"), "Failed to send an ENetPacket!");
    }
}

JNIEXPORT jobject JNICALL Java_enetjava_ENetLib_receive
  (JNIEnv * env, jclass cls, jobject obj, jobject channel) {
    
    ENetPeer *peer = (ENetPeer *) (*env)->GetDirectBufferAddress(env, obj);
    enet_uint8 channel_storage;
    ENetPacket *packet = enet_peer_receive(peer, &channel_storage);
    
    if (packet == NULL) return NULL;
    if (channel != NULL)
    {
        jmethodID setter = (*env)->GetMethodID(env, channel, "setValue", "(V)I");
        (*env)->CallVoidMethod(env, channel, setter, (jint) channel_storage);
    }
    
    return (*env)->NewDirectByteBuffer(env, packet, sizeof(ENetPacket));
}

JNIEXPORT void JNICALL Java_enetjava_ENetLib_ping
  (JNIEnv * env, jclass cls, jobject obj) {
    ENetPeer * peer = (ENetPeer *) (*env)->GetDirectBufferAddress(env, obj);
    enet_peer_ping(peer);
}

JNIEXPORT void JNICALL Java_enetjava_ENetLib_disconnect_1now
  (JNIEnv * env, jclass class, jobject obj, jint data) {
    ENetPeer * peer = (ENetPeer *) (*env)->GetDirectBufferAddress(env, obj);
    enet_peer_disconnect_now(peer, data);
}

JNIEXPORT void JNICALL Java_enetjava_ENetLib_disconnect
  (JNIEnv * env, jclass class, jobject obj, jint data) {
    ENetPeer * peer = (ENetPeer *) (*env)->GetDirectBufferAddress(env, obj);
    enet_peer_disconnect(peer, data);
}

JNIEXPORT void JNICALL Java_enetjava_ENetLib_disconnect_1later
  (JNIEnv * env, jclass class, jobject obj, jint data) {
    ENetPeer * peer = (ENetPeer *) (*env)->GetDirectBufferAddress(env, obj);
    enet_peer_disconnect_later(peer, data);
}

JNIEXPORT jbyteArray JNICALL Java_enetjava_ENetLib_get_1address 
  (JNIEnv * env, jclass cls, jobject obj) {
    
    ENetPeer *peer = (ENetPeer *) (*env)->GetDirectBufferAddress(env, obj);
    jbyteArray array = (*env)->NewByteArray(env, 4);
    jbyte *buf = (*env)->GetByteArrayElements(env, array, NULL);
    buf[0] = (jbyte) (peer->address.host >> 24);
    buf[1] = (jbyte) (peer->address.host >> 16);
    buf[2] = (jbyte) (peer->address.host >>  8);
    buf[3] = (jbyte) peer->address.host;
    (*env)->ReleaseByteArrayElements(env, array, buf, JNI_COMMIT);
    return array;
}

JNIEXPORT jint JNICALL Java_enetjava_ENetLib_get_1port
  (JNIEnv * env, jclass cls, jobject obj) {
    
    ENetPeer *peer = (ENetPeer *) (*env)->GetDirectBufferAddress(env, obj);
    return peer->address.port & 0xFFFF;
}

JNIEXPORT void JNICALL Java_enetjava_ENetLib_reset
  (JNIEnv * env, jclass cls, jobject obj) {
    ENetPeer *peer = (ENetPeer *) (*env)->GetDirectBufferAddress(env, obj);
    enet_peer_reset(peer);
}

JNIEXPORT jobject JNICALL Java_enetjava_ENetLib_create_1packet
  (JNIEnv * env, jclass cls, jobject obj, jint flag) {
    
    void *ptr;
    
    int length;
    ENetPacket *packet;

    ptr = (*env)->GetDirectBufferAddress(env, obj);
    length = (*env)->GetDirectBufferCapacity(env, obj);

    packet = enet_packet_create(ptr, length, flag | ENET_PACKET_FLAG_NO_ALLOCATE);
    if (packet == NULL)
    {
        (*env)->ThrowNew(env, (*env)->FindClass(env, "enetjava/objects/EnetException"), "Failed to create ENetPacket!");
        return NULL;
    }

    return (*env)->NewDirectByteBuffer(env, packet, sizeof(ENetPacket));
}

JNIEXPORT jobject JNICALL Java_enetjava_ENetLib_get_1bytes
  (JNIEnv * env, jclass cls, jobject obj) {
    ENetPacket *packet = (ENetPacket *) (*env)->GetDirectBufferAddress(env, obj);
    
    return (*env)->NewDirectByteBuffer(env, packet->data, packet->dataLength);
}

JNIEXPORT jint JNICALL Java_enetjava_ENetLib_get_1flags
  (JNIEnv * env, jclass cls, jobject obj) {
    ENetPacket *packet = (ENetPacket *) (*env)->GetDirectBufferAddress(env, obj);
    return packet->flags;
}

JNIEXPORT void JNICALL Java_enetjava_ENetLib_destroy_1packet
  (JNIEnv * env, jclass cls, jobject obj) {
    ENetPacket *packet = (ENetPacket *) (*env)->GetDirectBufferAddress(env, obj);
    enet_packet_destroy(packet);
}