package com.pear.common;

import com.pear.common.Article;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Set;

public interface Catalog extends Remote {

	/**
	 * @return the number of articles in the catalog
	 * @throws RemoteException
	 */
	int getNumberArticles() throws RemoteException;

	/**
	 * @param key
	 * @return the article whose key is key
	 * @throws RemoteException
	 * @throws NonExistentArticleException
	 */
	Article getArticle(String key) throws RemoteException, NonExistentArticleException;

	/**
	 * @return the list of all the keys
	 * @throws RemoteException
	 */
	ArrayList<String> getKeys() throws RemoteException;

}
