package com.pear.server;

import com.pear.common.Article;
import com.pear.common.Catalog;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server {
	public static void main(String[] args) throws Exception{
		CatalogImpl catalog = new CatalogImpl();
		catalog.addArticle("T-shirt", new Article("T-shirt", "Un t-shirt coton plutôt doux", 15.0f));
		catalog.addArticle("Banane", new Article("Banane", "Un fruit plutôt dégeulasse", 0.99f));
		Catalog catalogStub = (Catalog) UnicastRemoteObject.exportObject(((Catalog) catalog), 0);
		Registry reg = LocateRegistry.createRegistry(1099);
		reg.rebind("catalog", catalogStub);
	}
}
