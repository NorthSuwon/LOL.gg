package ns.lolgg.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ns.lolgg.dao.BoardRepository;
import ns.lolgg.domain.Board;
import ns.lolgg.domain.User;
import ns.lolgg.dto.BoardDTO.BoardRegi;
import ns.lolgg.exception.BoardNotExistedException;

@Service
public class BoardService {
	
	@Autowired
	BoardRepository boardRepo;
	
	public Page<Board> getBoardList(int page){
		Pageable pageable = PageRequest.of(page, 5);
        return boardRepo.findAll(pageable);
	}
	
	public Board getBoardById(Long id) {
		return boardRepo.findById(id).orElseThrow(()-> new BoardNotExistedException());
	}
	
	public Board insertBoard(BoardRegi board, User user) {
		return boardRepo.save(BoardRegi.toEntity(board, user));
	}
	
	public Board updateBoard(BoardRegi newBoard, Long id) {
		Board board = boardRepo.findById(id).orElseThrow(()-> new BoardNotExistedException());
		board.setPosition(newBoard.getPosition());
		board.setContent(newBoard.getContent());
		return board;
	}
	
	public void deleteBoard(Long id) {
		boardRepo.delete(boardRepo.findById(id).orElseThrow(()-> new BoardNotExistedException()));
	}
}
