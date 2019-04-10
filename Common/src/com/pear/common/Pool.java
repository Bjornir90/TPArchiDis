package com.pear.common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Pool<T extends Poolable> extends Remote {

	public T getInstance() throws RemoteException;
	public void release(T toRelease) throws RuntimeException, RemoteException;
	public void init(int capacity) throws RemoteException;

}

