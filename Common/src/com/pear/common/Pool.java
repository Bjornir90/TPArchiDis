package com.pear.common;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.function.Supplier;

public class Pool<T extends Poolable> implements Remote {
	private Supplier<T> supplier;
	private ArrayList<T> pool, availableObjects;

	public Pool(Supplier<T> supplier) {
		this.supplier = supplier;
		pool = new ArrayList<>();
		availableObjects = new ArrayList<>();
	}

	public T getInstance(){
		if(availableObjects.size() > 0){
			T object = availableObjects.get(0);
			availableObjects.remove(object);
			return object;
		} else {
			T object = supplier.get();
			pool.add(object);
			return object;
		}
	}

	public void release(T toRelease) throws RuntimeException{
		if(pool.contains(toRelease)){
			try {
				toRelease.reset();
			} catch (RemoteException e){
				e.printStackTrace();
			}
			availableObjects.add(toRelease);
		} else {
			throw new RuntimeException("Released object is not in pool");
		}
	}

	public void init(int capacity){
		clearLists();
		for(int i = 0; i < capacity; i++){
			addToLists(supplier.get());
		}
	}

	private void clearLists(){
		pool.clear();
		availableObjects.clear();
	}

	private void addToLists(T toAdd){
		pool.add(toAdd);
		availableObjects.add(toAdd);
	}
}
