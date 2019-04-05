package com.pear.common;

import com.pear.common.Article;

import java.io.Serializable;
import java.rmi.RemoteException;

public interface Catalog extends Serializable {

	int getNumberArticles() throws RemoteException;
	Article getArticle(String key) throws RemoteException, NonExistentArticleException;

}
