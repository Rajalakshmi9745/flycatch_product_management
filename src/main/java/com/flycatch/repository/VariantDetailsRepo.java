package com.flycatch.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.flycatch.model.ProductDetails;

import com.flycatch.model.*;

public interface VariantDetailsRepo extends JpaRepository<VariantDetails, Integer>, JpaSpecificationExecutor<VariantDetails> {
	@Query(value = "SELECT * FROM tbl_variant ", 
			  nativeQuery = true)
	List<VariantDetails> findAllvaraint();
	
	public static final String UPD_QUERY = "UPDATE tbl_variant set variant_name =?1 WHERE variant_id = ?2";
	@Modifying
	@Query(UPD_QUERY)
	@Transactional
	public int edit_variant_action(String variant_name,int variant_id);
	
	@Modifying
	@Query("delete from tbl_variant where variant_id =:variant_id")
	@Transactional
	void deleteVariant(@Param("variant_id") int variant_id);
}
