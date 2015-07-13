package chess;


public class ChessGame implements Game {

	private Board currentBoard;

	public ChessGame(final Board board) {
		currentBoard = board;
	}

	public void play(final Move move) {
		currentBoard = currentBoard.play(move);
	}

	public Board currentBoard() {
		return currentBoard;
	}

}
