# ENetJavaWrapper

ENet is an UDP networking library that is written in C++. But with this program + api, you can run it on Java!.
the code here are copied from an old repository (7 years ago, but ofc with a lot of changes), and it was never get updated. Its also have several issue
like the host will not run, no crc32 method, no compress method and so on.

This is just a simple wrapper it doesn't contains all enet features, if you're willing to get the full one but in other languange you can try ENet.Managed (Written in C#). Its still good tho, I've tested it multiple times and its still working fine :D

## Information

This should work on Windows and Linux as of 23/03/2019 update. Open a new issue if its not!

## Credit

ENet `http://enet.bespin.org`

(Old) JavaENetWrapper `https://github.com/csm/java-enet-wrapper`

## How to use

```java
package yourpackage

import enetjava.ENetLib

public static void main(String[] args) {
    ENetLib.initialize(libPath: "your_so_file_path", debug: true|false); // Must be the absolute path
    ENetLib.enet_initialize();
}
```

## Building

**No Changes**

ENetJavaLib : Just run the `build.sh` inside the **ENetJavaLib** and you're good to go!

ENetJava : Open in any IDE that you prefer, and build it normally

**With Changes**

ENetJavaLib : If you're willing to make some changes you'll have to know the basic knowledge of JNI, Java, and C. If you're willing to modify the lib then open the **ENetJavaLib** on NetBeans and make your changes, the project should be working out of the box, you can also use **Visual Studio Code** for windows. The code editor is only used as the editing tools not for compilling, if you want to compile it. Run the `build.sh` file instead.

ENetJava : For the java version, you can open it on NetBeans or IntelliJ and you can build it normally its just a normal java project, nothing else. The project also support **Maven** so you can use `mvn clean install` to build the project

## Update log

> [20/03/2019] Added maven support on **ENetJavaLib** 
> [23/03/2019] Added Windows support and massive code cleanup!.
