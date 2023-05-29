package com.ohwoo.mapper;

import java.util.List;

import com.ohwoo.DTO.BoardDTO;
import com.ohwoo.DTO.Criteria;

public interface BoardMapper {

	public void regist(BoardDTO board);

	public BoardDTO read(long no);

	public int update(BoardDTO board);

	public int delete(BoardDTO board);

	public int hit(BoardDTO board);

	public List<BoardDTO> getListPaging(Criteria cri);

}
