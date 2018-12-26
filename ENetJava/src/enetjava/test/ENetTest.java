/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enetjava.test;

import enetjava.ENetLib;
import enetjava.objects.ENetEvent;
import enetjava.objects.ENetHost;
import enetjava.objects.EnetException;
import java.net.InetSocketAddress;

public class ENetTest {
    
    private static boolean starting;
    
    public static void main(String[] args) throws EnetException {
        ENetLib.initialize("/home/anom/NetBeansProjects/ENetJavaLib/ENetJavaLib.so");
        ENetLib.enet_initialize();
        starting = true;
        ENetHost host = new ENetHost(new InetSocketAddress("127.0.0.1", 27015), 32, 10, 0, 0);
        host.compressWithRangeCoder();
        host.useCrc32();
        
        while (starting) {
            ENetEvent event;
            try {
                event = host.service(1000);
            } catch (EnetException e) {
                e.printStackTrace();
                System.exit(0);
                return;
            }
            if (event == null) continue;
            switch (event.type()) {
                case Connect:
                    System.out.println("There's a connection!");
                    break;
                case Disconnect:
                    break;
            }
        }
    }
}
