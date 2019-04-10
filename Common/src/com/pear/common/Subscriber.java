package com.pear.common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Subscriber extends Remote {

	/**
	 * Notify the subscriber with the notification
	 * @param notification
	 * @throws RemoteException
	 */
	void notify(Notification notification) throws RemoteException;
}
