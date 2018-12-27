# ENetJavaWrapper

ENet is an UDP networking library that is written in C++. But with this program + api, you can run it on Java!.
the code here are copied from an old repository (7 years ago), and it was never get updated. Its also have several issue
like the host will not run, no crc32 method, no compress method and so on.

This is just a simple wrapper it doesn't contains all enet features, if you're willing to get the full one but in other languange you can try ENet.Managed (Written in C#). Its still good tho, I've tested it multiple times and its still working fine :D

## Information

Currently there's no **Windows** support yet. There's several bugs that I need to fix in order to make this works on Windows. I don't know when will I publish it but I'm sure it will take a really long time.

## Credit

ENet `http://enet.bespin.org`
(Old) JavaENetWrapper `https://github.com/csm/java-enet-wrapper`

## How to use

```java
package yourpackage

import enetjava.ENetLib

public static void main(String[] args) {
    ENetLib.initialize("your_so_file_path"); // Must be the absolute path
    ENetLib.enet_initialize();
}
```

## Building

**No Changes**

ENetJavaLib : Just run the build.sh inside the ENetJavaLib and you're good to go!.
ENetJava : Open in any IDE that you prefer, and build it normally

**With Changes**

ENetJavaLib : If you're willing to make some changes you'll have to know the basic knowledge of JNI, Java, and C. If you're willing to modify the lib then open the **ENetJavaLib** on NetBeans linux (**DO NOT OPEN ON WINDOWS NETBEANS**) and make your changes, the project should be working out of the box. The netbeans is only used as the IDE not for compilling, if you want to build it. Run the `build.sh` file instead.

ENetJava : For the java version, you can open it on NetBeans or IntelliJ and you can build it normally its just a normal java project, nothing else.
