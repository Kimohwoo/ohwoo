package com.ohwoo.Controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ohwoo.DTO.BoardDTO;
import com.ohwoo.DTO.Criteria;
import com.ohwoo.DTO.UserDTO;
import com.ohwoo.Service.BoardService;
import com.ohwoo.Service.UserService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@RestController
@RequestMapping("/board/*")
@AllArgsConstructor
@Log4j
public class BoardController {

	private final BoardService boardService;
	private final UserService userService;

	@GetMapping(value = "list", produces = "application/json")
	public ModelAndView getList(@RequestParam("pageNum") int pageNum, @RequestParam("amount") int amount) {
		log.info("board/list 오는지 확인");
		ModelAndView mv = new ModelAndView("/board/list");
		Criteria cri;
		if(pageNum != 0 && amount != 0) {
			cri = new Criteria(pageNum, amount);
		} else {
			cri = new Criteria();
		}
		
		List<BoardDTO> list = boardService.getListPaging(cri);
		
		mv.addObject("list", list);
		log.info(list);
		mv.addObject("pages", cri);
		return mv;
	}

	@GetMapping("new")
	public ModelAndView newboard() {
		ModelAndView mv = new ModelAndView("/board/new");
		return mv;
	}
	
	@GetMapping("user")
	public UserDTO user(Principal principal) {
		log.info("게시글 작성유저 닉네임 :" + principal);
		UserDTO user = userService.login(principal.getName());
		return user;
	}

	@GetMapping("article")
	public ModelAndView get(@RequestParam("no") long no) {
		log.info("디테일: get");
		BoardDTO board = boardService.read(no);
		ModelAndView mv = new ModelAndView("/board/article");
		mv.addObject("article", board);
		return mv;
	}

	@PostMapping("article")
	public BoardDTO register(@RequestBody BoardDTO board) {
		log.info("게시글 작성 : " + board);
		boardService.regist(board);
		return board;
	}

	@PutMapping("article")
	public BoardDTO modify(@RequestBody BoardDTO board) {
		boardService.update(board);
		return board;
	}

	@DeleteMapping("article")
	public void delete(@RequestBody BoardDTO board) {
		boardService.delete(board);
	}

}
