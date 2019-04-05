package com.pear.server;

import com.pear.common.Article;
import com.pear.common.Cart;

import java.lang.reflect.Array;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;

public class CartImpl implements Cart {

	ArrayList<Article> articles;
	HashMap<String, Integer> quantities;

	public CartImpl(){
		articles = new ArrayList<>();
		quantities = new HashMap<>();
	}

	@Override
	public void addArticle(Article toAdd, int quantity) throws RemoteException {
		articles.add(toAdd);
		quantities.put(toAdd.getKey(), quantity);
	}

	@Override
	public void changeQuantity(String key, int quantity) throws RemoteException {

	}

	@Override
	public void removeArticle(String key) throws RemoteException {

	}

	@Override
	public float getTotalPrice() throws RemoteException {
		return 0;
	}

	@Override
	public void destroy() throws RemoteException {

	}
}
