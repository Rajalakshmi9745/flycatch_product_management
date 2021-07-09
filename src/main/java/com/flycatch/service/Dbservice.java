package com.flycatch.service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.json.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.flycatch.repository.*;
import com.flycatch.model.*;

@Service
public class Dbservice {
	
	@Autowired
	ProductDetailsRepo productrepo;
	
	@Autowired
	OptionDetailsRepo optionrepo;
	
	@Autowired
	VariantDetailsRepo variantrepo;
	
	private static final Logger logger = LoggerFactory.getLogger(Dbservice.class);
	
	public List<ProductDetails> findAllProducts() {
		logger.info("Fetching all product details------");
		return productrepo.findAllProducts();
	}
	
	public List<OptionDetails> findAlloptions() {
		logger.info("Fetching all option details------");
		return optionrepo.findAlloptions();
	}
	public List<VariantDetails> findAllvaraint() {
		logger.info("Fetching all variant details------");
		return variantrepo.findAllvaraint();
	}
	
	public VariantDetails saveVariant(String variantName){
		logger.info("Saving variant details------");
		VariantDetails bean = new VariantDetails();
		bean.setVariant_name(variantName);
		logger.debug(bean.toString());
		return variantrepo.save(bean);
	}
	
	public OptionDetails saveOption(int variantId,String optionName){
		logger.info("Saving option details------");
		OptionDetails bean = new OptionDetails();
		bean.setVariant_id(variantId);
		bean.setOption_value(optionName);
		logger.debug(bean.toString());
		return optionrepo.save(bean);
	}
	public ProductDetails saveProduct(int productsizeId,int productcolorId,String productName){
		logger.info("Saving product details------");
		ProductDetails bean = new ProductDetails();
		bean.setColor(productcolorId);
		bean.setSize(productsizeId);
		bean.setName(productName);
		logger.debug(bean.toString());
		return productrepo.save(bean);
	}
	
	public int edit_variant_action(String variant_name,int variant_id) {
		return variantrepo.edit_variant_action(variant_name, variant_id);
	}
	
	public int edit_option_action(int variant_id,String option_value,int option_id) {
		return optionrepo.edit_option_action(variant_id, option_value, option_id);
	}
	
	public int edit_product_action(String product_name,int product_size,int product_color,int product_id) {
		return productrepo.edit_product_action(product_name, product_size, product_color, product_id);
	}
	
	public void delete_product_action(int product_id) {
		productrepo.deleteProduct(product_id);
	}
	
	public void deleteVariant(int variant_id) {
		variantrepo.deleteVariant(variant_id);
	}
	
	public void deleteOption(int option_id) {
		optionrepo.deleteOption(option_id);
	}
	
	public List<ProductDetails> searchByFilter(String product_name, int productsizeId, int productcolorId) {

		int ch=0;
		if (!product_name.isEmpty() && productsizeId==0&& productcolorId==0) {
			ch = 1;
		} else if(product_name.isEmpty() && productsizeId!=0&& productcolorId!=0){
			ch = 2;
		} 
		switch (ch) {
		case 1:		
			return productrepo.findAllProductsbyName(product_name);
		case 2:
			return productrepo.findAllProductsbySizeandColor(productsizeId, productcolorId);

		default:
			return productrepo.findAllProductsbyColor(productcolorId);

		}
	}

}
