package ns.lolgg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ns.lolgg.domain.Board;
import ns.lolgg.dto.BoardDTO.BoardRegi;
import ns.lolgg.service.BoardService;
import ns.lolgg.service.UserService;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	@Autowired
	private UserService userService;
	
	@GetMapping("/list")
	public String mainBoard(@RequestParam(value="page", defaultValue = "0") int page, Model model) {
		
		Page<Board> boardList = boardService.getBoardList(page);
		model.addAttribute("pages", boardList);
		model.addAttribute("num", page);
		return "board";
	}
	
	@GetMapping("/write")
	public String writeBoardPage() {
		return "write";
	}
	
	@PostMapping("/write")
	public String writeBoard(BoardRegi board) {
		boardService.insertBoard(board, userService.findUserById(SecurityContextHolder.getContext().getAuthentication().getName()));
		return "redirect:/board/list";
	}
}
