package com.flycatch.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="tbl_product")
@Table(name="tbl_product")
public class ProductDetails implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	@Column(name = "product_Id", updatable = false)
	int id;
	
	@Column(name = "product_name", updatable = false)
	String name;
	
	@Column(name = "product_size", updatable = false)
	int size;
	
	@Column(name = "product_color", updatable = false)
	int color;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return "ProductDetails [id=" + id + ", name=" + name + ", size=" + size + ", color=" + color + "]";
	}
}
