package com.pear.common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Poolable extends Remote {
	void reset() throws RemoteException;
}
