#include "ENetJavaLib.h"
#include <stdio.h>
#include "enet/enet.h"

using namespace std;

JNIEXPORT void JNICALL Java_enetjava_ENetLib_enet_1initialize (JNIEnv * env, jclass cls) {
    enet_initialize();
}

JNIEXPORT void JNICALL Java_enetjava_ENetLib_enet_1deinitialize (JNIEnv * env, jclass cls) {
    enet_deinitialize();
}

JNIEXPORT jobject JNICALL Java_enetjava_ENetLib_create__IIIIII
  (JNIEnv * env, jclass cls, jint add, jint port, jint peerCount, jint channelLimit, jint incoming, jint outgoing) {
    
    const ENetAddress * addr = { (enet_uint32) add, (enet_uint16) port };
    //printf("[ENetHost] ENetAddress has been created!. (Port " + addr->port + ", addrs " + addr->host + ")");
    cout << "ENetAddress has been created!" << endl;
    
    ENetHost * host = enet_host_create(addr, peerCount, channelLimit, (enet_uint32) incoming, (enet_uint32) outgoing);
    // For Growtopia
    enet_host_compress_with_range_coder(host);
    host->checksum = enet_crc32;
    // Growtopia end
    if (host == NULL) {
        env->ThrowNew(env, env->FindClass(env, "enetjava/objects/EnetException"), "Failed to create ENetHost!");
        //(*env)->ThrowNew(env, (*env)->FindClass(env, "enetjava/objects/EnetException"), "Failed to create ENetHost!");
        return NULL;
    }
    return env->NewDirectByteBuffer(env, host, sizeof(ENetHost));
}

