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

	@GetMapping("")
	public ModelAndView boardList() {
		log.info("확인");
		ModelAndView mv = new ModelAndView("/board/list");
		Criteria cri = new Criteria();
		List<BoardDTO> list = boardService.getListPaging(cri);
		mv.addObject("list", list);

		return mv;
	}

	@GetMapping(produces = "application/json;charset=UTF-8")
	public List<BoardDTO> RequestList(@RequestParam int pageNum, @RequestParam int amount) {
		log.info("list");
		Criteria cri;
		if ((pageNum != 0) && amount != 0) {
			cri = new Criteria(pageNum, amount);
		} else {
			cri = new Criteria();
		}

		List<BoardDTO> list = boardService.getListPaging(cri);

		return list;
	}

	// ObjectMapper를 사용함
//	@ResponseBody
//	public String list(@RequestParam int pageNum, @RequestParam int amount) {
//		log.info("list");
//		ObjectMapper mapper = new ObjectMapper();
//		String jsonList = "";
//
//		try {
//			return jsonList = mapper.writeValueAsString(list);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
////		Map result = new HashMap();
////		result.put("list", jsonList);
////		result.put("code", "200");
////		result.put("boardList", "list");
//
////		mv.addObject("list", list);
//
//		return "";
//	}

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
