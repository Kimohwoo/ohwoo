package com.ohwoo.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ohwoo.DTO.BoardDTO;
import com.ohwoo.DTO.Criteria;
import com.ohwoo.Service.BoardService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@RestController
@RequestMapping("/board/*")
@AllArgsConstructor
@Log4j
public class BoardController {

	private final BoardService boardService;

	@GetMapping()
	public ModelAndView list(@RequestParam int pageNum, @RequestParam int amount) {
		log.info("list");
		ModelAndView mv = new ModelAndView("/board/list");
		Criteria cri;
		if((pageNum != 0) && amount != 0) {
			cri = new Criteria(pageNum, amount);
		} else {
			cri = new Criteria();
		}
		
		List<BoardDTO> list = boardService.getListPaging(cri);
		mv.addObject("list", list);
		
		return mv;
	}

//	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
//	@GetMapping("{no}")
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
