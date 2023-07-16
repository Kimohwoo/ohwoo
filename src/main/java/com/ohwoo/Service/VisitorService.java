package com.ohwoo.Service;

import com.ohwoo.DTO.VisitorDTO;

public interface VisitorService {

	public void insertVisitor(VisitorDTO visitor);
	
	public boolean updateVisitor(VisitorDTO visitor);
	
	public VisitorDTO selectDayVisitor(VisitorDTO visitor);
	
	public VisitorDTO selectAllVisitor();
	
	
}
