package com.pear.common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Pool<T extends Poolable> extends Remote {

	T getInstance() throws RemoteException;
	void release(T toRelease) throws RuntimeException, RemoteException;
	void init(int capacity) throws RemoteException;

}

