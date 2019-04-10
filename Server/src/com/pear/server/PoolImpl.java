package com.pear.server;

import com.pear.common.Pool;
import com.pear.common.Poolable;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.function.Supplier;

public class PoolImpl<T extends Poolable> implements Pool<T> {
	private Supplier<T> supplier;
	private ArrayList<T> pool, availableObjects;

	public PoolImpl(Supplier<T> supplier) {
		this.supplier = supplier;
		pool = new ArrayList<>();
		availableObjects = new ArrayList<>();
	}

	@Override
	public T getInstance(){
		if(availableObjects.size() > 0){
			T object = availableObjects.get(0);
			T stub = null;
			try {
				stub = (T) UnicastRemoteObject.exportObject(object, 0);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			availableObjects.remove(object);
			return stub;
		} else {
			T object = supplier.get();
			T stub = null;
			try {
				stub = (T) UnicastRemoteObject.exportObject(object, 0);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			pool.add(object);
			return stub;
		}
	}

	@Override
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

	@Override
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
