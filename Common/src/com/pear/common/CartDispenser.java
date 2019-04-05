package com.pear.common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CartDispenser extends Remote {
	Cart dispenseCart() throws Exception;
}
