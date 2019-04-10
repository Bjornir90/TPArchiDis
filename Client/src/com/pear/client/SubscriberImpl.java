package com.pear.client;

import com.pear.common.Notification;
import com.pear.common.Subscriber;

import java.io.Serializable;
import java.rmi.Remote;

public class SubscriberImpl implements Subscriber, Serializable {
	@Override
	public void notify(Notification notification) {
		System.out.println(notification.getMessage());
	}
}
