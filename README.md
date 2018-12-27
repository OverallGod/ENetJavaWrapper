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

Just run the build.sh inside the ENetJavaLib and you're good to go!.
