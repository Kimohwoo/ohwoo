package com.ohwoo.Service;

import org.springframework.stereotype.Service;

import com.ohwoo.DTO.VisitorDTO;
import com.ohwoo.mapper.VisitorMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@AllArgsConstructor
@Log4j
public class VisitorServiceImpl implements VisitorService {
	
	private VisitorMapper visitorMapper;

	@Override
	public void insertVisitor(VisitorDTO visitor) {
		// TODO Auto-generated method stub
		log.info("Insert : " + visitor);
		visitorMapper.insertVisitor(visitor);
	}

	@Override
	public boolean updateVisitor(VisitorDTO visitor) {
		// TODO Auto-generated method stub
		return visitorMapper.updateVisitor(visitor) == 1;
	}

	@Override
	public VisitorDTO selectDayVisitor(VisitorDTO visitor) {
		// TODO Auto-generated method stub
		try {
			log.info("SELECT : "+visitor + ", " + visitorMapper.selectDayVisitor(visitor));
			return visitorMapper.selectDayVisitor(visitor);
		} catch(Exception e) {
			log.info("SELECT : 에러");
			return null;
		}
	}

	@Override
	public VisitorDTO selectAllVisitor() {
		// TODO Auto-generated method stub
		VisitorDTO visitor = new VisitorDTO();
		try {
			return visitorMapper.selectAllVisitor();
		} catch(Exception e) {
			return null;
		}
	}

}
