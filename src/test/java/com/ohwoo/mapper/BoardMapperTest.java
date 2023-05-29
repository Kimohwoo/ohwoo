package com.ohwoo.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ohwoo.DTO.BoardDTO;
import com.ohwoo.DTO.Criteria;
import com.ohwoo.DTO.UserDTO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { com.ohwoo.Config.RootConfig.class, com.ohwoo.Config.SecurityConfig.class})
@Log4j
public class BoardMapperTest {

	@Setter(onMethod_=@Autowired)
	private BoardMapper boardMapper;
	@Setter(onMethod_=@Autowired)
	private UserMapper userMapper;
	
	@Test
	public void pagingList() {
		Criteria cri = new Criteria(3, 10);
		log.info(cri);
		List<BoardDTO> list = boardMapper.getListPaging(cri);
		log.info( boardMapper.getListPaging(cri));

		list.forEach(board -> log.info("확인용 : " + board));
		
	}
	
//	@Test
	public void regist() {
		
		for(int i = 5; i< 100; i++) {
		BoardDTO board = new BoardDTO();
		board.setTitle(i + "" + i + "" + i);
		board.setAuthor("user1");
		board.setContent("제목");
		boardMapper.regist(board);
		log.info(board);
		}
		
	}
	
}
