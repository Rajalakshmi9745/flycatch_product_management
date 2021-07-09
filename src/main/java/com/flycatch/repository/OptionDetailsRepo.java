package com.flycatch.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.flycatch.model.*;

public interface OptionDetailsRepo extends JpaRepository<OptionDetails, Integer>, JpaSpecificationExecutor<OptionDetails> {
	@Query(value = "SELECT * FROM tbl_options ", 
			  nativeQuery = true)
	List<OptionDetails> findAlloptions();
	
	public static final String UPD_QUERY = "UPDATE tbl_options set variant_id =?1 , option_value=?2 WHERE option_id = ?3";
	@Modifying
	@Query(UPD_QUERY)
	@Transactional
	public int edit_option_action(int variant_id,String option_value,int option_id);
	
	@Modifying
	@Query("delete from tbl_options where option_id =:option_id")
	@Transactional
	void deleteOption(@Param("option_id") int option_id);
}
