package com.pear.client;

import com.pear.common.*;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Client {

    public static void main(String[] args) throws Exception{
        /* AH OUI OUI AH OUI OUI */

        Registry registry = LocateRegistry.getRegistry("localhost", 1099);
        Catalog catalog = (Catalog) registry.lookup("catalog");

        System.out.println("Le nombre d'articles dans le catalogue est : " + catalog.getNumberArticles());

        ArrayList<String> keys = catalog.getKeys();
        int article_pos = catalog.getNumberArticles()/2;
        String article_key = keys.get(article_pos);

        System.out.println("La clé de l'article n° " + article_pos + " est "+ article_key);

        Article article = catalog.getArticle(article_key);

        SubscriberImpl sub = new SubscriberImpl();
        Subscriber subStub = (Subscriber) UnicastRemoteObject.exportObject(sub, 0);

        System.out.println("Description de l'article : " + article.getDesc());
        Pool<Cart> pool = (Pool<Cart>) registry.lookup("pool");
        pool.subscribe(subStub);

        Cart cart = pool.getInstance();
        System.out.println("Cart UUID : " + cart.getUuid());
        cart.addArticle(article, 2);
        System.out.println("TotalPrice : "+ cart.getTotalPrice());
        cart.changeQuantity(article_key, 1);
        System.out.println("TotalPrice : " + cart.getTotalPrice());
        pool.unsubscribe(subStub);
        UnicastRemoteObject.unexportObject(subStub, false);
        pool.release(cart);
    }

}
