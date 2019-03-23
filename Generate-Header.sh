#!/bin/bash

#Execute the command
echo "Generating the header files"
javah -o ENetJavaLib/ENetJavaLib.h -classpath ENetJava/build/ enetjava.ENetLib
echo "Header files has been created!"
