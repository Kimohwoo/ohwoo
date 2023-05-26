package com.ohwoo.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ohwoo.DTO.BoardDTO;
import com.ohwoo.Service.BoardService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@RestController
@RequestMapping("/board/*")
@AllArgsConstructor
@Log4j
public class BoardController {

	private BoardService boardService;

	@GetMapping()
	public List<BoardDTO> list() {
		log.info("list");
		return boardService.getList();
	}

//	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@GetMapping("{no}")
	public BoardDTO get(@PathVariable int no) {
		log.info("get");
		return boardService.read(no);
	}

	@PostMapping("{board}")
//	@PreAuthorize("isAuthenticated()")
	public BoardDTO register(@PathVariable BoardDTO board) {
		boardService.regist(board);
		return board;
	}

	@PutMapping("{board}")
	public BoardDTO modify(@PathVariable BoardDTO board) {
		boardService.update(board);
		return board;
	}

	@DeleteMapping("{board}")
	public void delete(@PathVariable BoardDTO board) {
		boardService.delete(board);
	}

}
