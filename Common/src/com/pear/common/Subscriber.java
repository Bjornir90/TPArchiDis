package com.pear.common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Subscriber extends Remote {

	void notify(Notification notification) throws RemoteException;
}
