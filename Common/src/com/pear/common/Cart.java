package com.pear.common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Cart extends Remote {

	void addArticle(Article toAdd, int quantity) throws RemoteException;
	void changeQuantity(String key, int quantity) throws RemoteException;
	void removeArticle(String key) throws RemoteException;
	float getTotalPrice() throws RemoteException;
	void destroy() throws RemoteException;
	String getUuid() throws RemoteException;
}
