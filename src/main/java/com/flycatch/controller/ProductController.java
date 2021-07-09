package com.flycatch.controller;

import java.util.List;


import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.json.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.flycatch.service.*;
import com.flycatch.model.*;

@RestController
@RequestMapping("/flycatch")
public class ProductController {
	@Autowired
	Dbservice service;
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

	//api for fetching all products
	@SuppressWarnings({ "unchecked" })
	@GetMapping("/allproducts")
	public ResponseEntity<JSONObject> findAllProducts() {
		logger.info("\n----Started API for fetching all products-----\n");
		JSONObject response = new JSONObject(); //output object

		try {
			List<ProductDetails> products= service.findAllProducts();  //fetching products from db
			response.put("products", products);
			logger.info("\n----Finished executing API for fetching all products----\n");
			return new ResponseEntity<JSONObject>(response, HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
			response.put("message", "failed to fetch product details due to internal error");
			return new ResponseEntity<JSONObject>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//api for fetching all variants
	@SuppressWarnings({ "unchecked" })
	@GetMapping("/allvariants")
	public ResponseEntity<JSONObject> findAllvaraint() {
		logger.info("\n----Started API for fetching all variants-----\n");
		JSONObject response = new JSONObject(); //output object
		try {
			List<VariantDetails> variants = service.findAllvaraint(); 	 //fetching variants from db	
			response.put("variants", variants);
			logger.info("\n----Finished executing API for fetching all variants----\n");
			return new ResponseEntity<JSONObject>(response, HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
			response.put("message", "failed to fetch variants details due to internal error");
			return new ResponseEntity<JSONObject>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//api for fetching all options
	@SuppressWarnings({ "unchecked" })
	@GetMapping("/alloptions")
	public ResponseEntity<JSONObject> findAlloptions() {
		logger.info("\n----Started API for fetching all options-----\n");
		JSONObject response = new JSONObject();  //output object

		try {
			List<OptionDetails> options = service.findAlloptions();   //fetching all options from db
			response.put("options", options);
			logger.info("\n----Finished executing API for fetching all options----\n");
			return new ResponseEntity<JSONObject>(response, HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
			response.put("message", "failed to fetch option details due to internal error");
			return new ResponseEntity<JSONObject>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	//saving new variant in db
	@SuppressWarnings({ "unchecked" })
	@PostMapping(value="/newVariant",produces = "application/json")
	public ResponseEntity<JSONObject> insertVariant(@RequestParam("variantName") String variantName) {
		logger.info("\n----Started API for saving new variant in db-----\n");
		JSONObject response = new JSONObject();  //output object
		
		try {
			VariantDetails list = service.saveVariant(variantName);   //saving data in db
			response.put("response", list);
			if(list.toString().isEmpty()) {
				response.put("message", "Failed to insert variant data in db. Please check the data.");
				return new ResponseEntity<JSONObject>(response, HttpStatus.OK);
			}
			response.put("message", "Successfully inserted variant data in db");
			return new ResponseEntity<JSONObject>(response, HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
			response.put("message", "failed to insert data due to internal error");
			return new ResponseEntity<JSONObject>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//saving new option in db
	@SuppressWarnings({ "unchecked" })
	@PostMapping(value="/newOption",produces = "application/json")
	public ResponseEntity<JSONObject> insertOption(@RequestParam("variantId") int variantId, @RequestParam("optionName") String optionName) {
		logger.info("\n----Started API for saving new option in db-----\n");
		JSONObject response = new JSONObject();  //output object
			
		try {
			OptionDetails list = service.saveOption(variantId, optionName);   //saving data in db
			response.put("response", list);
			if(list.toString().isEmpty()) {
				response.put("message", "Failed to insert option data in db. Please check the data.");
				return new ResponseEntity<JSONObject>(response, HttpStatus.OK);
			}
			response.put("message", "Successfully inserted option data in db");
			return new ResponseEntity<JSONObject>(response, HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
			response.put("message", "failed to insert data due to internal error");
			return new ResponseEntity<JSONObject>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//saving new product in db
	@SuppressWarnings({ "unchecked" })
	@PostMapping(value="/newProduct",produces = "application/json")
	public ResponseEntity<JSONObject> insertProduct(@RequestParam("productsizeId") int productsizeId, @RequestParam("productcolorId") int productcolorId, @RequestParam("productName") String productName) {
		logger.info("\n----Started API for saving new product in db-----\n");
		JSONObject response = new JSONObject();  //output object
				
		try {
			ProductDetails list = service.saveProduct(productsizeId,productcolorId, productName);   //saving data in db
			response.put("response", list);
			if(list.toString().isEmpty()) {
				response.put("message", "Failed to insert product data in db. Please check the data.");
				return new ResponseEntity<JSONObject>(response, HttpStatus.OK);
			}
			response.put("message", "Successfully inserted product data in db");
			return new ResponseEntity<JSONObject>(response, HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
			response.put("message", "failed to insert data due to internal error");
			return new ResponseEntity<JSONObject>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//updating product in db
	@SuppressWarnings({ "unchecked" })
	@PutMapping(value="/editProduct/productId/{productId}",produces = "application/json")
	public ResponseEntity<JSONObject> editProduct(@PathVariable("productId") int productId,@RequestParam("productsizeId") int productsizeId, @RequestParam("productcolorId") int productcolorId, @RequestParam("productName") String productName) {
		logger.info("\n----Started API for updating product in db-----\n");
		JSONObject response = new JSONObject();  //output object
					
		try {
			service.edit_product_action(productName,productsizeId,productcolorId, productId);   //saving data in db
			response.put("message", "Successfully updated product data in db");
			return new ResponseEntity<JSONObject>(response, HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
			response.put("message", "failed to update data due to internal error");
			return new ResponseEntity<JSONObject>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//updating variant in db
	@SuppressWarnings({ "unchecked" })
	@PutMapping(value="/editVariant/variantId/{variantId}",produces = "application/json")
	public ResponseEntity<JSONObject> editVariant(@PathVariable("variantId") int variant_id,@RequestParam("variantName") String variant_name) {
		logger.info("\n----Started API for updating variant in db-----\n");
		JSONObject response = new JSONObject();  //output object
						
		try {
			service.edit_variant_action(variant_name, variant_id);   //saving data in db
			response.put("message", "Successfully updated variant data in db");
			return new ResponseEntity<JSONObject>(response, HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
			response.put("message", "failed to updata data due to internal error");
			return new ResponseEntity<JSONObject>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//updating option in db
	@SuppressWarnings({ "unchecked" })
	@PutMapping(value="/editOption/optionId/{optionId}",produces = "application/json")
	public ResponseEntity<JSONObject> editOption(@PathVariable("optionId") int option_id,@RequestParam("optionValue") String option_value,@RequestParam("variantId") int variant_id) {
		logger.info("\n----Started API for updating option in db-----\n");
		JSONObject response = new JSONObject();  //output object
						
		try {
			service.edit_option_action(variant_id, option_value, option_id);   //saving data in db
			response.put("message", "Successfully updated option data in db");
			return new ResponseEntity<JSONObject>(response, HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
			response.put("message", "failed to update data due to internal error");
			return new ResponseEntity<JSONObject>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//deleting variant in db
	@SuppressWarnings({ "unchecked" })
	@DeleteMapping(value="/deleteVariant/variantId/{variantId}",produces = "application/json")
	public ResponseEntity<JSONObject> deleteVariant(@PathVariable("variantId") int variant_id) {
		logger.info("\n----Started API for deleting variant in db-----\n");
		JSONObject response = new JSONObject();  //output object
						
		try {
			service.deleteVariant(variant_id);   //deleting data in db
			response.put("message", "Successfully deleted variant data in db");
			return new ResponseEntity<JSONObject>(response, HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
			response.put("message", "failed to delete data due to internal error");
			return new ResponseEntity<JSONObject>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//deleting option in db
	@SuppressWarnings({ "unchecked" })
	@DeleteMapping(value="/deleteOption/optionId/{optionId}",produces = "application/json")
	public ResponseEntity<JSONObject> deleteOption(@PathVariable("optionId") int option_id) {
		logger.info("\n----Started API for deleting option in db-----\n");
		JSONObject response = new JSONObject();  //output object
						
		try {
			service.deleteOption(option_id);   //deleting data in db
			response.put("message", "Successfully deleted option data in db");
			return new ResponseEntity<JSONObject>(response, HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
			response.put("message", "failed to delete data due to internal error");
			return new ResponseEntity<JSONObject>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//deleting product in db
	@SuppressWarnings({ "unchecked" })
	@DeleteMapping(value="/deleteProduct/productId/{productId}",produces = "application/json")
	public ResponseEntity<JSONObject> deleteProduct(@PathVariable("productId") int productId) {
		logger.info("\n----Started API for deleting product in db-----\n");
		JSONObject response = new JSONObject();  //output object
						
		try {
			service.delete_product_action(productId);   //deleting data in db
			response.put("message", "Successfully deleted product data in db");
			return new ResponseEntity<JSONObject>(response, HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
			response.put("message", "failed to delete data due to internal error");
			return new ResponseEntity<JSONObject>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	//filtering product in db
	@SuppressWarnings({ "unchecked" })
	@GetMapping(value="/getProductByfilter",produces = "application/json")
	public ResponseEntity<JSONObject> getProductByfilter(@RequestParam(required = false, defaultValue="0") int productsizeId,
			@RequestParam(required = false, defaultValue="0") int productcolorId, @RequestParam("productName") String productName) {
		logger.info("\n----Started API for filtering product in db-----\n");
		JSONObject response = new JSONObject();  //output object
					
		try {
			List<ProductDetails> list=service.searchByFilter(productName,productsizeId,productcolorId);   //filtering data in db
			response.put("response",list);
			response.put("message", "Successfully filter product data");
			return new ResponseEntity<JSONObject>(response, HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
			response.put("message", "failed to filter data due to internal error");
			return new ResponseEntity<JSONObject>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
