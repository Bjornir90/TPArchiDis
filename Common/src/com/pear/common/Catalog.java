package com.pear.common;

import com.pear.common.Article;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Set;

public interface Catalog extends Remote {

	int getNumberArticles() throws RemoteException;
	Article getArticle(String key) throws RemoteException, NonExistentArticleException;
	ArrayList<String> getKeys() throws RemoteException;

}
