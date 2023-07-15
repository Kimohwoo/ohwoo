package com.ohwoo.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class BoardDTO {

	private long no;
	private String title;
	private String author;
	private String content;
	private String regdate;
	private String updateDay;

}
