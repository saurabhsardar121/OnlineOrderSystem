package com.ofos.registerationandlogging.service;

import java.util.List;

import com.ofos.customer.dto.CoustmerDto;
import com.ofos.customer.entity.Coustmer;

public interface LoggingAndRgisteraionService  {
	
public abstract void Registercoustomer(CoustmerDto coustmerDto);
	
	public  boolean LoggingCoustomer(CoustmerDto coustmerDto);
	
	public Long getRollIdByUserType(String userType);
	
	public abstract List<Coustmer> listCoustomer();

	public abstract void Deletecoustomer(CoustmerDto coustmerDto);



}