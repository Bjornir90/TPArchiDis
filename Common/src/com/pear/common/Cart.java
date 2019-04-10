package com.pear.common;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.UUID;

public interface Cart extends Remote, Poolable {

	void addArticle(Article toAdd, int quantity) throws RemoteException;
	void changeQuantity(String key, int quantity) throws RemoteException;
	void removeArticle(String key) throws RemoteException;
	float getTotalPrice() throws RemoteException;
}
