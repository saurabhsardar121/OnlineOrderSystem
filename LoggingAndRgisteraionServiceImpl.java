package com.ofos.registerationandlogging.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ofos.customer.dto.CoustmerDto;
import com.ofos.customer.entity.Coustmer;
import com.ofos.registerationandlogging.dao.LoggingAndRgisteraionDao;

@Service
public class LoggingAndRgisteraionServiceImpl implements LoggingAndRgisteraionService {
	
	@Autowired
	LoggingAndRgisteraionDao loggingAndRgisteraionDao;
	
	@Override
	public void Registercoustomer(CoustmerDto coustmerDto) {
		Coustmer coustmer = dtoToEntity(coustmerDto);
		loggingAndRgisteraionDao.Registercoustomer(coustmer);
	}

	private Coustmer dtoToEntity(CoustmerDto coustmerDto) {
		Coustmer coustmer = new Coustmer();
		coustmer.setId(coustmerDto.getId());
		coustmer.setFirstname(coustmerDto.getFirstname());
		coustmer.setLastname(coustmerDto.getLastname());
		coustmer.setMobno(coustmerDto.getMobno());
		coustmer.setAddress(coustmerDto.getAddress());
		coustmer.setCity(coustmerDto.getCity());
		coustmer.setEmail(coustmerDto.getEmail());
		coustmer.setPassw(coustmerDto.getPassw());
		coustmer.setRoleId(coustmerDto.getRoleId());
		return coustmer;
	}

	@Override
	public boolean LoggingCoustomer(CoustmerDto coustmerDto) {
		Coustmer coustmer = dtoToEntity(coustmerDto);
		return loggingAndRgisteraionDao.LoggingCoustomer(coustmer);

	}
	
	@Override
	public Long getRollIdByUserType(String userType) {

		return loggingAndRgisteraionDao.getRollIdByUserType(userType);
	}

	@Override
	public List<Coustmer> listCoustomer() {
		// TODO Auto-generated method stub
		return loggingAndRgisteraionDao.listCoustomer();
	}

	@Override
	public void Deletecoustomer(CoustmerDto coustmerDto) {
		Coustmer coustmer = dtoToEntity(coustmerDto);
		loggingAndRgisteraionDao.Deletecoustomer(coustmer);
		
	}



}
