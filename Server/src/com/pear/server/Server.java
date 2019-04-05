package com.pear.server;

import com.pear.common.Article;
import com.pear.common.Cart;
import com.pear.common.CartDispenser;
import com.pear.common.Catalog;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.UUID;

public class Server {
	private static HashMap<String, Cart> carts;

	static {
		carts = new HashMap<>();
	}

	public static Cart createCart() throws Exception {
		String uuid = UUID.randomUUID().toString();
		carts.put(uuid, new CartImpl(uuid));
		return (Cart) UnicastRemoteObject.exportObject(carts.get(uuid), 0);
	}

	public static void removeCart(String uuid){
		carts.remove(uuid);
	}

	public static void main(String[] args) throws Exception{
		CatalogImpl catalog = new CatalogImpl();
		catalog.addArticle("T-shirt", new Article("T-shirt", "Un t-shirt coton plutôt doux", 15.0f));
		catalog.addArticle("Banane", new Article("Banane", "Un fruit plutôt dégeulasse", 0.99f));
		Catalog catalogStub = (Catalog) UnicastRemoteObject.exportObject(((Catalog) catalog), 0);
		Registry reg = LocateRegistry.createRegistry(1099);
		reg.rebind("catalog", catalogStub);
		CartDispenser dispenser = new CartDispenserImpl();
		CartDispenser dispenserStub = (CartDispenser) UnicastRemoteObject.exportObject(dispenser, 0);
		reg.rebind("dispenser", dispenserStub);
	}
}
