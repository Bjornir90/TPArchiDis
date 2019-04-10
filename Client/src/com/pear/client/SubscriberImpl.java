package com.pear.client;

import com.pear.common.*;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class SubscriberImpl implements Subscriber, Serializable {

	private Remote subStub;

	public SubscriberImpl(Pool<?> pool){
		try {
			subStub = (Subscriber) UnicastRemoteObject.exportObject(subStub, 0);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		try {
			pool.subscribe((Subscriber) subStub);
		} catch (RemoteException e) {
			e.printStackTrace();
		}

	}

	public void unsubscribe(Pool<?> pool){
		pool
	}

	@Override
	public void notify(Notification notification) {
		System.out.println(notification.getMessage());
	}
}
