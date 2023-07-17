package com.ohwoo.Service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ohwoo.DTO.BoardDTO;
import com.ohwoo.DTO.Criteria;
import com.ohwoo.domain.JwtTokenProvider;
import com.ohwoo.mapper.BoardMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
@AllArgsConstructor
public class BoardServiceImpl implements BoardService {

	private BoardMapper mapper;
	private final JwtTokenProvider jwtTokenProvider;
	
	@Override
	public void regist(BoardDTO board) {
		// TODO Auto-generated method stub
		log.info(board + "등록");
		mapper.regist(board);
	}

	@Override
	public BoardDTO read(long no) {
		// TODO Auto-generated method stub
		log.info("board 1 읽기" + no);
		return mapper.read(no);
	}

	@Override
	public boolean update(BoardDTO board) {
		// TODO Auto-generated method stub
		log.info("update : " + board);
		return mapper.update(board) == 1;
	}

	@Override
	public boolean delete(BoardDTO board) {
		// TODO Auto-generated method stub
		log.info("delete : " + board);
		return mapper.delete(board) == 1;
	}

	@Override
	public boolean hit(BoardDTO board) {
		// TODO Auto-generated method stub
		log.info("hit 확인" + board);
		return mapper.hit(board) == 1;
	}


	@Override
	public List<BoardDTO> getListPaging(Criteria cri) {
		// TODO Auto-generated method stub
		return mapper.getListPaging(cri);
	}

}
