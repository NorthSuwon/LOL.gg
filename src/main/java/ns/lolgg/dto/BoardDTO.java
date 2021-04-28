package ns.lolgg.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ns.lolgg.domain.Board;
import ns.lolgg.domain.User;

public class BoardDTO {

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class BoardRegi {

		private String position;
		private String content;

		public static Board toEntity(BoardRegi board, User user) {
			return Board.builder().position(board.getPosition()).content(board.getContent()).createDate(LocalDate.now())
					.user(user).build();
		}
	}
}
