package com.pear.common;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.UUID;

public interface Poolable extends Remote {
	void reset() throws RemoteException;
	UUID getUuid() throws RemoteException;
}
