package com.ofos.registerationandlogging.dao;

import java.util.List;

import com.ofos.customer.dto.CoustmerDto;
import com.ofos.customer.entity.Coustmer;

public interface LoggingAndRgisteraionDao {
    
	
	public abstract void Registercoustomer(Coustmer coustmer);
	
	public  boolean LoggingCoustomer(Coustmer coustmer);
	
	public Long getRollIdByUserType(String userType);
	
	public abstract List<Coustmer> listCoustomer();

	public abstract void Deletecoustomer(Coustmer coustmer);


}
