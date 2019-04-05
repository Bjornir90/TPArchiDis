package com.pear.server;

import com.pear.common.Article;
import com.pear.common.Catalog;

public class Server {
	public static void main(String[] args) {
		CatalogImpl catalog = new CatalogImpl();
		catalog.addArticle("T-shirt", new Article("T-shirt", "Un t-shirt coton plutôt doux", 15.0f));
	}
}
