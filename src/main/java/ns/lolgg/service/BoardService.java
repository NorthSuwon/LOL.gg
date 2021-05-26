package ns.lolgg.service;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ns.lolgg.dao.BoardRepository;
import ns.lolgg.domain.Board;
import ns.lolgg.domain.MatchUser;
import ns.lolgg.domain.User;
import ns.lolgg.dto.BoardDTO.BoardRegi;
import ns.lolgg.exception.BoardNotExistedException;

@Service
public class BoardService {
	
	@Autowired
	private BoardRepository boardRepo;
	
	public Page<Board> getBoardList(int page){
		Pageable pageable = PageRequest.of(page, 5);
        return boardRepo.findAll(pageable);
	}
	
	public Board getBoardById(Long id) {
		return boardRepo.findById(id).orElseThrow(()-> new BoardNotExistedException());
	}
	
	public Board insertBoard(BoardRegi board, User user) {
		Board newBoard = BoardRegi.toEntity(board, user);
		
		Comparator<String> comparator = (s1, s2)->s2.compareTo(s1);
		Map<String, Integer> map = new TreeMap<>(comparator);
		
		Long killAssist = 0L;
		Long death= 0L;
		for (MatchUser match : user.getMatches()) {
			killAssist += match.getKills() + match.getAssists();
			death += match.getDeaths();
			
			if (!map.containsKey(match.getChampionName())) {
				map.put(match.getChampionName(), 0);
			}
			map.replace(match.getChampionName(), map.get(match.getChampionName()+1));
		}
		
		newBoard.setKda("perfect");
		if (death>0) {
			newBoard.setKda(Double.toString(killAssist/death)); 
		}
		
		int i = 0;
		for (Map.Entry<String, Integer> entry : map.entrySet()) {
		    if (i==0) {
		    	newBoard.setMost1(entry.getKey());
		    } else if(i==1) {
		    	newBoard.setMost2(entry.getKey());
		    } else if(i==2) {
		    	newBoard.setMost3(entry.getKey());
		    } else {
		    	break;
		    }
		    i++;
		}
		
		return boardRepo.save(newBoard);
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
