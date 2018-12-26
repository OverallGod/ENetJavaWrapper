#!/bin/bash

RED='\033[0;31m'
NC='\033[0m'

#We build it first
gcc -fPIC -c ENetJavaLib.c -I /usr/lib/jvm/java-8-openjdk-amd64/include -I /usr/lib/jvm/java-8-openjdk-amd64/include/linux

echo -e "${RED}[DEBUG] ${NC}.o File has been builded. Now generating .so file!"

gcc -shared callbacks.o compress.o host.o list.o packet.o peer.o protocol.o unix.o ENetJavaLib.o -o ENetJavaLib.so

echo -e "${RED}[DEBUG] ${NC}.so file has been builded successfully!. You can now use it :D"
