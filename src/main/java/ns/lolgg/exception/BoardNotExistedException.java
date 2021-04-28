package ns.lolgg.exception;

public class BoardNotExistedException extends RuntimeException{
	
	public BoardNotExistedException() {
		super("게시물이 존재하지 않습니다.");
	}
}
