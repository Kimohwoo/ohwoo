package com.ohwoo.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.ohwoo.Service.BoardService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@RestController
@RequestMapping("/board/*")
@AllArgsConstructor
@Log4j
public class BoardController {

	private final BoardService boardService;

	@GetMapping("list")
	public ModelAndView getList() {
		log.info("board/list 오는지 확인");
		ModelAndView mv = new ModelAndView("/board/list");
		Criteria cri = new Criteria();

		List<BoardDTO> list = boardService.getListPaging(cri);
		mv.addObject("list", list);
		return mv;
	}

	@GetMapping("new")
	public ModelAndView newboard() {
		return new ModelAndView("/board/new");
	}

	@GetMapping("list-page")
	public Map<String, Object> getCriteria(@RequestParam("pageNum") int pageNum, @RequestParam("amount") int amount) {
		log.info("list");
		Criteria cri = new Criteria(pageNum, amount);

		List<BoardDTO> list = boardService.getListPaging(cri);
		Map<String, Object> boardList = new HashMap<String, Object>();
		boardList.put("item", list);

		return boardList;
	}

	@GetMapping("article")
	public ModelAndView get(@RequestParam("no") long no) {
		log.info("디테일: get");
		ModelAndView mv = new ModelAndView("/board/article");
		mv.addObject("article", boardService.read(no));
		return mv;
	}

	@PostMapping("article")
	public BoardDTO register(@RequestBody BoardDTO board) {
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
