package com.ohwoo.DTO;

import java.sql.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

	private String id;
	private String password;
	private String name;
	private String nickName;
	private String phone;
	private String address;
	private String level;
	private Date regdate;
	private List<AuthDTO> authList;

}
