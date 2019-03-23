#!/bin/bash

RED='\033[0;31m'
NC='\033[0m'

#Execute the command
echo -e "${RED}[Generator] ${NC}Generating the header files"
javah -o ENetJavaLib/ENetJavaLib.h -classpath ENetJava/build/ enetjava.ENetLib
echo -e "${RED}[Generator] ${NC}Header files has been created!"
