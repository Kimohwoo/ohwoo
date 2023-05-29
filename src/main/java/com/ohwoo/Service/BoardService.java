package com.ohwoo.Service;

import java.util.List;

import com.ohwoo.DTO.BoardDTO;
import com.ohwoo.DTO.Criteria;

public interface BoardService {

	public void regist(BoardDTO board);

	public BoardDTO read(long no);

	public boolean update(BoardDTO board);

	public boolean delete(BoardDTO board);

	public int hit(BoardDTO board);

	public List<BoardDTO> getListPaging(Criteria cri);

}
