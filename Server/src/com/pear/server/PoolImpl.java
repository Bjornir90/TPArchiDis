package com.pear.server;

import com.pear.common.Pool;
import com.pear.common.Poolable;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.UUID;
import java.util.function.Supplier;

public class PoolImpl<T extends Poolable> implements Pool<T> {
	private Supplier<T> supplier;
	//pool contains all instances, availableObjects only contains instances not yet sent to a client
	private ArrayList<T> pool, availableObjects;

	private static int MAX_SIZE = 2048;

	public PoolImpl(Supplier<T> supplier) {
		this.supplier = supplier;
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
			availableObjects.remove(availableObjects.indexOf(object));
			return stub;
		} else if(pool.size() < MAX_SIZE){
			T object = supplier.get();
			T stub = null;
			try {
				stub = (T) UnicastRemoteObject.exportObject(object, 0);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			pool.add(object);
			return stub;
		} else throw new ArrayIndexOutOfBoundsException();
	}

	@Override
	public void release(T toRelease) throws RuntimeException{
		try {
			if(contains(toRelease.getUuid())){
				try {
					toRelease.reset();
				} catch (RemoteException e){
					e.printStackTrace();
				}
				availableObjects.add(toRelease);
				System.out.println("Removed object : "+toRelease.getUuid());
			} else {
				throw new RuntimeException("Released object is not in pool");
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void init(int capacity){
		if (capacity > MAX_SIZE) throw new ArrayIndexOutOfBoundsException();
		else {
			pool = new ArrayList<>(capacity);
			availableObjects = new ArrayList<>(capacity);
			clearLists();
			for (int i = 0; i < capacity; i++) {
				addToLists(supplier.get());
			}
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

	private boolean contains(UUID id) throws RemoteException {
		for(T object : pool){
			if(object.getUuid().equals(id)){
				return true;
			}
		}
		return false;
	}
}
