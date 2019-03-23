#!/bin/bash

RED='\033[0;31m'
NC='\033[0m'

# We build it first
gcc -fPIC -c ENetJavaLib.c -I "C:\Program Files\Java\jdk1.8.0_201\include" -I "C:\Program Files\Java\jdk1.8.0_201\include\win32"

echo -e "${RED}[DEBUG] ${NC}.o File has been builded. Now generating .so file!"

gcc -shared callbacks.o compress.o host.o list.o packet.o peer.o protocol.o win32.o ENetJavaLib.o -o ENetJavaLib.dll -lws2_32 -lwinmm

echo -e "${RED}[DEBUG] ${NC}.so file has been builded successfully!. You can now use it :D"
