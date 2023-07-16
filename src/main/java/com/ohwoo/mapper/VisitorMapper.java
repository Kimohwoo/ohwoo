package com.ohwoo.mapper;

import com.ohwoo.DTO.VisitorDTO;

public interface VisitorMapper {

	public void insertVisitor(VisitorDTO visitor);
	
	public int updateVisitor(VisitorDTO visitor);
	
	public VisitorDTO selectDayVisitor(VisitorDTO visitor);
	
	public VisitorDTO selectAllVisitor();
	
}
