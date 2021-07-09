package com.flycatch.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="tbl_options")
@Table(name="tbl_options")
public class OptionDetails implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	@Column(name = "option_id", updatable = false)
	int option_id;
	
	@Column(name = "variant_id", updatable = false)
	int variant_id;
	
	@Column(name = "option_value", updatable = false)
	String option_value;

	public int getOption_id() {
		return option_id;
	}

	public void setOption_id(int option_id) {
		this.option_id = option_id;
	}

	public int getVariant_id() {
		return variant_id;
	}

	public void setVariant_id(int variant_id) {
		this.variant_id = variant_id;
	}

	public String getOption_value() {
		return option_value;
	}

	public void setOption_value(String option_value) {
		this.option_value = option_value;
	}

	@Override
	public String toString() {
		return "OptionDetails [option_id=" + option_id + ", variant_id=" + variant_id + ", option_value=" + option_value
				+ "]";
	}	
}
