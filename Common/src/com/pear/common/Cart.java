package com.pear.common;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.UUID;

public interface Cart extends Remote, Poolable {

    /**
     * Add quantity instances of the Article toAdd to the cart
     * @param toAdd
     * @param quantity
     * @throws RemoteException
     */
	void addArticle(Article toAdd, int quantity) throws RemoteException;

    /**
     * Change the quantity of the article whose key is key to quantity
     * @param key
     * @param quantity
     * @throws RemoteException
     */
	void changeQuantity(String key, int quantity) throws RemoteException;

    /**
     * Remove all instances of the aeticle whose key is key from the cart
     * @param key
     * @throws RemoteException
     */
	void removeArticle(String key) throws RemoteException;

    /**
     * @return the total price of the cart
     * @throws RemoteException
     */
	float getTotalPrice() throws RemoteException;
}
