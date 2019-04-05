package com.pear.server;

import com.pear.common.Article;
import com.pear.common.Cart;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;

public class CartImpl implements Cart {

	ArrayList<Article> articles;
	HashMap<String, Integer> quantities;
	String uuid;

	public CartImpl(String uuid){
		this.uuid = uuid;
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
		quantities.replace(key, quantity);
	}

	@Override
	public void removeArticle(String key) throws RemoteException {
		articles.remove(key);
		quantities.remove(key);
	}

	@Override
	public float getTotalPrice() throws RemoteException {
		float sum = 0.0f;
		for(Article a : articles){
			sum += quantities.get(a.getKey()) * a.getPrice();
		}
		return sum;
	}

	@Override
	public void destroy() throws RemoteException {
		Server.removeCart(this.uuid);
	}

	public String getUuid() {
		return uuid;
	}
}
