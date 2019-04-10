package com.pear.common;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.UUID;

public interface Poolable extends Remote {

	/**
	 * Reset the object, setting its state back to its instantiation state
	 * The object must be available for another client after this method has successfully completed
	 * @throws RemoteException
	 */
	void reset() throws RemoteException;

	/**
	 * The UUID returned by this method must be unique across the system
	 * @return the UUID identifying this object
	 * @throws RemoteException
	 */
	UUID getUuid() throws RemoteException;
}
