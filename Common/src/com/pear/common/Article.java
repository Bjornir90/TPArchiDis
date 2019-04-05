package com.pear.common;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Article implements Serializable {
	private String key, desc;
	private float price;
	private LocalDateTime availabilityDate;

	public Article(String key, String desc, float price) {
		this(key, desc, price, LocalDateTime.now());
	}

	public Article(String key, String desc, float price, LocalDateTime date){
		this.key = key;
		this.desc = desc;
		this.price = price;
		availabilityDate = date;
	}

	public String getKey() {
		return key;
	}

	public String getDesc() {
		return desc;
	}

	public float getPrice() {
		return price;
	}

	public LocalDateTime getAvailabilityDate() {
		return availabilityDate;
	}
}
