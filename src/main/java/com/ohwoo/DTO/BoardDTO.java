package com.ohwoo.DTO;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BoardDTO {

	private long no;
	private String title;
	private String author;
	private String content;
	private Date regdate;
	private Date updateDay;

}
