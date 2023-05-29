package com.ohwoo.DTO;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class Criteria {

	//현재 페이지
	private int pageNum;
	//한 페이지당 게시물 갯수
	private int amount;
	//스킵할 게시물 수( pagenum-1 * amount )
	private int skip;
	
	private String type;
	private String keyword;

	public Criteria() {
		this(1, 10);
	}

	public Criteria(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
		this.skip = (pageNum-1) * amount;
	}

	public String[] getTypeArr() {
		return type == null ? new String[] {} : type.split("");
	}

}
