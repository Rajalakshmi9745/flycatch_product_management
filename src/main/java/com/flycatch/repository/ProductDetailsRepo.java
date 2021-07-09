package com.flycatch.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.flycatch.model.*;

public interface ProductDetailsRepo extends JpaRepository<ProductDetails, Integer>,JpaSpecificationExecutor<ProductDetails> {
	@Query(value = "SELECT * FROM tbl_product ", 
			  nativeQuery = true)
	List<ProductDetails> findAllProducts();
	
	@Query(value = "SELECT * FROM tbl_product WHERE product_name LIKE %:product_name%", 
			  nativeQuery = true)
	List<ProductDetails> findAllProductsbyName(@Param("product_name") String product_name);
	
	@Query(value = "SELECT * FROM tbl_product WHERE product_size=:product_size AND product_color=:product_color;", 
			  nativeQuery = true)
	List<ProductDetails> findAllProductsbySizeandColor(@Param("product_size") int product_size,@Param("product_color") int product_color);
	
	@Query(value = "SELECT * FROM tbl_product WHERE product_color=:product_color;", 
			  nativeQuery = true)
	List<ProductDetails> findAllProductsbyColor(@Param("product_color") int product_color);

	
	public static final String UPD_QUERY = "UPDATE tbl_product set product_name =?1 , product_size=?2 , product_size=?3  WHERE product_id = ?4";
	@Modifying
	@Query(UPD_QUERY)
	@Transactional
	public int edit_product_action(String product_name,int product_size,int product_color,int product_id);
	
	@Modifying
	@Query("delete from tbl_product where product_id =:product_id")
	@Transactional
	void deleteProduct(@Param("product_id") int product_id);
}
