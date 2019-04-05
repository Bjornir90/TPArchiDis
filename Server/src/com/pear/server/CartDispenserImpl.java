package com.pear.server;

import com.pear.common.Cart;
import com.pear.common.CartDispenser;

import java.rmi.RemoteException;

public class CartDispenserImpl implements CartDispenser {
	@Override
	public Cart dispenseCart() throws Exception {
		return Server.createCart();
	}
}
