package com.pear.server;

import com.pear.common.Notification;
import com.pear.common.Pool;
import com.pear.common.Poolable;
import com.pear.common.Subscriber;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.UUID;
import java.util.function.Supplier;

public class PoolImpl<T extends Poolable> implements Pool<T> {
	private Supplier<T> supplier;
	//pool contains all instances, availableObjects only contains instances not yet sent to a client
	private ArrayList<T> pool, availableObjects;
	private ArrayList<Subscriber> subscribers;
	private int initialCapacity;

	private static int MAX_SIZE = 2048;

	public PoolImpl(Supplier<T> supplier) {
		this.supplier = supplier;
		subscribers = new ArrayList<>();
		initialCapacity = 0;
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
			if(availableObjects.size() < 20){
				notifyAllSubscribers(new Notification("Attention : moins de 20 paniers disponibles"));
			}
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
				notifyAllSubscribers(new Notification("Un panier est disponible !"));
			} else {
				throw new RuntimeException("Released object is not in pool");
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		if (availableObjects.size() > initialCapacity){
			for(int i=0; i<availableObjects.size()-initialCapacity; i++){
				pool.remove(availableObjects.get(i));
				availableObjects.remove(i);
				pool.trimToSize();
				availableObjects.trimToSize();
			}
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
		initialCapacity = capacity;
	}

	@Override
	public void subscribe(Subscriber subscriber) throws RemoteException {
		subscribers.add(subscriber);
	}

	private void notifyAllSubscribers(Notification notification){
		for(Subscriber sub : subscribers){
			try {
				sub.notify(notification);
			} catch (RemoteException e) {
				e.printStackTrace();
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
