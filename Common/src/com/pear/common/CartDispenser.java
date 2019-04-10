package com.pear.common;

import java.rmi.Remote;

public interface CartDispenser extends Remote {
	Cart dispenseCart() throws Exception;
}
