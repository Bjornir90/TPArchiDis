package com.pear.client;

import com.pear.common.Article;
import com.pear.common.Catalog;

import java.lang.reflect.Array;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Set;

public class Client {

    public static void main(String[] args) throws Exception{
        /* AH OUI OUI AH OUI OUI */

        Registry registry = LocateRegistry.getRegistry("localhost", 1099);
        Catalog catalog = (Catalog) registry.lookup("catalog");

        System.out.println("Le nombre d'articles dans le catalogue est : " + catalog.getNumberArticles());

        ArrayList<String> keys = new ArrayList<>(catalog.getKeys());
        int article_pos = (int) catalog.getNumberArticles()/2;
        String article_key = keys.get(article_pos);

        System.out.println("La clé de l'article n° " + article_pos + " est "+ article_key);

        Article article = catalog.getArticle(article_key);

        System.out.println("Description de l'article" + article.getDesc());
    }

}
