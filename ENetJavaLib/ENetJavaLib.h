/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class enetjava_ENetLib */

#ifndef _Included_enetjava_ENetLib
#define _Included_enetjava_ENetLib
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     enetjava_ENetLib
 * Method:    enet_initialize
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_enetjava_ENetLib_enet_1initialize
  (JNIEnv *, jclass);

/*
 * Class:     enetjava_ENetLib
 * Method:    enet_deinitialize
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_enetjava_ENetLib_enet_1deinitialize
  (JNIEnv *, jclass);

/*
 * Class:     enetjava_ENetLib
 * Method:    create_host
 * Signature: (IIIIII)Ljava/nio/ByteBuffer;
 */
JNIEXPORT jobject JNICALL Java_enetjava_ENetLib_create_1host
  (JNIEnv *, jclass, jstring, jint, jint, jint, jint, jint);

/*
 * Class:     enetjava_ENetLib
 * Method:    connect
 * Signature: (Ljava/nio/ByteBuffer;IIII)Ljava/nio/ByteBuffer;
 */
JNIEXPORT jobject JNICALL Java_enetjava_ENetLib_connect
  (JNIEnv *, jclass, jobject, jstring, jint, jint, jint);

/*
 * Class:     enetjava_ENetLib
 * Method:    broadcast
 * Signature: (Ljava/nio/ByteBuffer;ILjava/nio/ByteBuffer;)V
 */
JNIEXPORT void JNICALL Java_enetjava_ENetLib_broadcast
  (JNIEnv *, jclass, jobject, jint, jobject);

/*
 * Class:     enetjava_ENetLib
 * Method:    channel_limit
 * Signature: (Ljava/nio/ByteBuffer;I)V
 */
JNIEXPORT void JNICALL Java_enetjava_ENetLib_channel_1limit
  (JNIEnv *, jclass, jobject, jint);

/*
 * Class:     enetjava_ENetLib
 * Method:    bandwidth_limit
 * Signature: (Ljava/nio/ByteBuffer;II)V
 */
JNIEXPORT void JNICALL Java_enetjava_ENetLib_bandwidth_1limit
  (JNIEnv *, jclass, jobject, jint, jint);

/*
 * Class:     enetjava_ENetLib
 * Method:    flush
 * Signature: (Ljava/nio/ByteBuffer;)V
 */
JNIEXPORT void JNICALL Java_enetjava_ENetLib_flush
  (JNIEnv *, jclass, jobject);

/*
 * Class:     enetjava_ENetLib
 * Method:    checkEvents
 * Signature: (Ljava/nio/ByteBuffer;Ljava/nio/ByteBuffer;)I
 */
JNIEXPORT jint JNICALL Java_enetjava_ENetLib_checkEvents
  (JNIEnv *, jclass, jobject, jobject);

/*
 * Class:     enetjava_ENetLib
 * Method:    service
 * Signature: (Ljava/nio/ByteBuffer;ILjava/nio/ByteBuffer;)I
 */
JNIEXPORT jint JNICALL Java_enetjava_ENetLib_service
  (JNIEnv *, jclass, jobject, jint, jobject);

/*
 * Class:     enetjava_ENetLib
 * Method:    destroy_host
 * Signature: (Ljava/nio/ByteBuffer;)V
 */
JNIEXPORT void JNICALL Java_enetjava_ENetLib_destroy_1host
  (JNIEnv *, jclass, jobject);

/*
 * Class:     enetjava_ENetLib
 * Method:    set_crc32
 * Signature: (Ljava/nio/ByteBuffer;)V
 */
JNIEXPORT void JNICALL Java_enetjava_ENetLib_set_1crc32
  (JNIEnv *, jclass, jobject);

/*
 * Class:     enetjava_ENetLib
 * Method:    compress_with_range_coder
 * Signature: (Ljava/nio/ByteBuffer;)V
 */
JNIEXPORT void JNICALL Java_enetjava_ENetLib_compress_1with_1range_1coder
  (JNIEnv *, jclass, jobject);

/*
 * Class:     enetjava_ENetLib
 * Method:    sizeof
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_enetjava_ENetLib_sizeof
  (JNIEnv *, jclass);

/*
 * Class:     enetjava_ENetLib
 * Method:    peer
 * Signature: (Ljava/nio/ByteBuffer;)Ljava/nio/ByteBuffer;
 */
JNIEXPORT jobject JNICALL Java_enetjava_ENetLib_peer
  (JNIEnv *, jclass, jobject);

/*
 * Class:     enetjava_ENetLib
 * Method:    type
 * Signature: (Ljava/nio/ByteBuffer;)Lenetjava/objects/ENetEvent/Type;
 */
JNIEXPORT jobject JNICALL Java_enetjava_ENetLib_type
  (JNIEnv *, jclass, jobject);

/*
 * Class:     enetjava_ENetLib
 * Method:    channelID
 * Signature: (Ljava/nio/ByteBuffer;)B
 */
JNIEXPORT jbyte JNICALL Java_enetjava_ENetLib_channelID
  (JNIEnv *, jclass, jobject);

/*
 * Class:     enetjava_ENetLib
 * Method:    data
 * Signature: (Ljava/nio/ByteBuffer;)I
 */
JNIEXPORT jint JNICALL Java_enetjava_ENetLib_data
  (JNIEnv *, jclass, jobject);

/*
 * Class:     enetjava_ENetLib
 * Method:    packet
 * Signature: (Ljava/nio/ByteBuffer;)Ljava/nio/ByteBuffer;
 */
JNIEXPORT jobject JNICALL Java_enetjava_ENetLib_packet
  (JNIEnv *, jclass, jobject);

/*
 * Class:     enetjava_ENetLib
 * Method:    throttleConfigure
 * Signature: (Ljava/nio/ByteBuffer;III)V
 */
JNIEXPORT void JNICALL Java_enetjava_ENetLib_throttleConfigure
  (JNIEnv *, jclass, jobject, jint, jint, jint);

/*
 * Class:     enetjava_ENetLib
 * Method:    send
 * Signature: (Ljava/nio/ByteBuffer;ILjava/nio/ByteBuffer;)V
 */
JNIEXPORT void JNICALL Java_enetjava_ENetLib_send
  (JNIEnv *, jclass, jobject, jint, jobject);

/*
 * Class:     enetjava_ENetLib
 * Method:    receive
 * Signature: (Ljava/nio/ByteBuffer;Lenetjava/objects/MutableInteger;)Ljava/nio/ByteBuffer;
 */
JNIEXPORT jobject JNICALL Java_enetjava_ENetLib_receive
  (JNIEnv *, jclass, jobject, jobject);

/*
 * Class:     enetjava_ENetLib
 * Method:    ping
 * Signature: (Ljava/nio/ByteBuffer;)V
 */
JNIEXPORT void JNICALL Java_enetjava_ENetLib_ping
  (JNIEnv *, jclass, jobject);

/*
 * Class:     enetjava_ENetLib
 * Method:    disconnect_now
 * Signature: (Ljava/nio/ByteBuffer;I)V
 */
JNIEXPORT void JNICALL Java_enetjava_ENetLib_disconnect_1now
  (JNIEnv *, jclass, jobject, jint);

/*
 * Class:     enetjava_ENetLib
 * Method:    disconnect
 * Signature: (Ljava/nio/ByteBuffer;I)V
 */
JNIEXPORT void JNICALL Java_enetjava_ENetLib_disconnect
  (JNIEnv *, jclass, jobject, jint);

/*
 * Class:     enetjava_ENetLib
 * Method:    disconnect_later
 * Signature: (Ljava/nio/ByteBuffer;I)V
 */
JNIEXPORT void JNICALL Java_enetjava_ENetLib_disconnect_1later
  (JNIEnv *, jclass, jobject, jint);

/*
 * Class:     enetjava_ENetLib
 * Method:    get_address
 * Signature: (Ljava/nio/ByteBuffer;)[B
 */
JNIEXPORT jbyteArray JNICALL Java_enetjava_ENetLib_get_1address
  (JNIEnv *, jclass, jobject);

/*
 * Class:     enetjava_ENetLib
 * Method:    get_port
 * Signature: (Ljava/nio/ByteBuffer;)I
 */
JNIEXPORT jint JNICALL Java_enetjava_ENetLib_get_1port
  (JNIEnv *, jclass, jobject);

/*
 * Class:     enetjava_ENetLib
 * Method:    reset
 * Signature: (Ljava/nio/ByteBuffer;)V
 */
JNIEXPORT void JNICALL Java_enetjava_ENetLib_reset
  (JNIEnv *, jclass, jobject);

/*
 * Class:     enetjava_ENetLib
 * Method:    create_packet
 * Signature: (Ljava/nio/ByteBuffer;I)Ljava/nio/ByteBuffer;
 */
JNIEXPORT jobject JNICALL Java_enetjava_ENetLib_create_1packet
  (JNIEnv *, jclass, jobject, jint);

/*
 * Class:     enetjava_ENetLib
 * Method:    get_bytes
 * Signature: (Ljava/nio/ByteBuffer;)Ljava/nio/ByteBuffer;
 */
JNIEXPORT jobject JNICALL Java_enetjava_ENetLib_get_1bytes
  (JNIEnv *, jclass, jobject);

/*
 * Class:     enetjava_ENetLib
 * Method:    get_flags
 * Signature: (Ljava/nio/ByteBuffer;)I
 */
JNIEXPORT jint JNICALL Java_enetjava_ENetLib_get_1flags
  (JNIEnv *, jclass, jobject);

/*
 * Class:     enetjava_ENetLib
 * Method:    destroy_packet
 * Signature: (Ljava/nio/ByteBuffer;)V
 */
JNIEXPORT void JNICALL Java_enetjava_ENetLib_destroy_1packet
  (JNIEnv *, jclass, jobject);

#ifdef __cplusplus
}
#endif
#endif
