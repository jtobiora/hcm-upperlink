package com.upl.nibss.hcmlib.repo.jdbcTemplatePagination;

import lombok.NoArgsConstructor;

/**
 * Base Domain model class
 * 
 * @author sheenobu
 *
 */
@NoArgsConstructor
public class BaseModel {

	private Long id;
	
	public BaseModel(Long id) {
		this.id = id;
	}
	
	
	public Long getId() {
		return id;
	}
}
