package com.ohwoo.Controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
		
		Criteria cri = null;
		if((pageNum == 0) || (amount ==0)) {
			cri = new Criteria();
		} else {
			cri = new Criteria(pageNum, amount);
		}
		
		List<BoardDTO> list = boardService.getListPaging(cri);
		ModelAndView mv = new ModelAndView("/board/list");
		mv.addObject("pages", cri);
		mv.addObject("list", list);
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
	
	@GetMapping("myarticle")
	public ModelAndView getMine(@RequestParam("no") long no) {
		log.info("myArticle: " + no);
		BoardDTO board = boardService.read(no);
		ModelAndView mv = new ModelAndView("/board/myarticle");
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
	public ResponseEntity<BoardDTO> modify(@RequestBody BoardDTO board) {
		if (boardService.update(board)) {
	        return ResponseEntity.status(HttpStatus.OK).body(board);
	    } else {
	        return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(null);
	    }
	}

	@DeleteMapping("article")
	public ResponseEntity<String> delete(@RequestBody BoardDTO board) {
		log.info("delete 확인용");
		if(boardService.delete(board)) {
			return ResponseEntity.status(HttpStatus.OK).body("삭제 되었습니다.");
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("삭제 실패");
		}
	}

}
