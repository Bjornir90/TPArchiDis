package com.pear.client;

import com.pear.common.Catalog;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {

    public static void main(String[] args) throws Exception {
        /* AH OUI OUI AH OUI OUI */

        Registry registry = LocateRegistry.getRegistry("localhost", 1099);
        Catalog catalog = (Catalog) registry.lookup("catalog");

        

    }

}
