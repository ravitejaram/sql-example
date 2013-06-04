package com.soebes.casestudy.bo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Basis für alle BO Klassen.
 * 
 * @author Karl Heinz Marbaise
 * 
 */
@MappedSuperclass
public class BaseBO extends AbstractBaseBO {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long Id;

	@Override
	public Long getId() {
		return Id;
	}

	@Override
	public void setId(Long id) {
		Id = id;
	}

}
