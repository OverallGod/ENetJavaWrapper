#!/bin/bash

#Command
g++ -shared callbacks.o compress.o host.o list.o packet.o peer.o protocol.o unix.o ENetJavaLib.o -o ENetJavaLib.so

echo "Successfully builded!. Now test it :D"

