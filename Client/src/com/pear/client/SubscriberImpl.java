package com.pear.client;

import com.pear.common.*;

import java.io.Serializable;
import java.rmi.NoSuchObjectException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class SubscriberImpl implements Subscriber, Serializable {

	@Override
	public void notify(Notification notification) {
		System.out.println(notification.getMessage());
	}
}
