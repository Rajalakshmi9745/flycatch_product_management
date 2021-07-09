package com.flycatch.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="tbl_variant")
@Table(name="tbl_variant")
public class VariantDetails implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	@Column(name = "variant_id", updatable = false)
	int variant_id;
		
	@Column(name = "variant_name", updatable = false)
	String variant_name;

	public int getVariant_id() {
		return variant_id;
	}

	public void setVariant_id(int variant_id) {
		this.variant_id = variant_id;
	}

	public String getVariant_name() {
		return variant_name;
	}

	public void setVariant_name(String variant_name) {
		this.variant_name = variant_name;
	}

	@Override
	public String toString() {
		return "VariantDetails [variant_id=" + variant_id + ", variant_name=" + variant_name + "]";
	}
}
