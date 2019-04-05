package com.pear.server;

import com.pear.common.Article;
import com.pear.common.Catalog;
import com.pear.common.NonExistentArticleException;

import java.rmi.RemoteException;
import java.util.HashMap;

public class CatalogImpl implements Catalog {

	private HashMap<String, Article> articles;

	public CatalogImpl() {
		articles = new HashMap<>();
	}

	public void addArticle(String key, Article toAdd){
		articles.put(key, toAdd);
	}

	@Override
	public int getNumberArticles() throws RemoteException {
		return 10;
	}

	@Override
	public Article getArticle(String key) throws RemoteException, NonExistentArticleException {
		if(articles.containsKey(key)){
			return articles.get(key);
		} else {
			throw new NonExistentArticleException("Wallah il existe pas ton article khey");
		}
	}
}
