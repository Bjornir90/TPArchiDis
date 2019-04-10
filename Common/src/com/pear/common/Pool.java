package com.pear.common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Pool<T extends Poolable> extends Remote {


	/**
	 * @return the instance attributed to the caller
	 * @throws RemoteException
	 * @throws ArrayIndexOutOfBoundsException
	 */
	T getInstance() throws RemoteException, ArrayIndexOutOfBoundsException;

	/**
	 * Release the object, making it available for other clients
	 * @param toRelease the object that must be released
	 * @throws RuntimeException
	 * @throws RemoteException
	 */
	void release(T toRelease) throws RuntimeException, RemoteException;

	/**
	 * @param capacity the number of instances to keep alive at all times
	 * @throws RemoteException
	 */
	void init(int capacity) throws RemoteException;


	/**
	 * Add the Subscriber subscriber to the list of subscribers to notify
	 * @param subscriber
	 * @throws RemoteException
	 */
	void subscribe(Subscriber subscriber) throws RemoteException;

}

