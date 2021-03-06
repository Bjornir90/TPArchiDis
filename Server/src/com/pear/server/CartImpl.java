package com.pear.server;

import com.pear.common.Article;
import com.pear.common.Cart;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class CartImpl implements Cart, Remote {

	ArrayList<Article> articles;
	HashMap<String, Integer> quantities;
	UUID uuid;

	public CartImpl(){
		this.uuid = UUID.randomUUID();
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
	public UUID getUuid() {
		return uuid;
	}

	@Override
	public void reset() {
		articles.clear();
		quantities.clear();
	}
}
