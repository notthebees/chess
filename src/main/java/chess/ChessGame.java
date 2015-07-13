package chess;

import chess.pieces.Position;

public class ChessGame implements Game {

	private Board currentBoard;

	public ChessGame(final Board board) {
		currentBoard = board;
	}

	public void play(final Position from, final Position to) {
		currentBoard = currentBoard.move(from, to);
	}

	public Board currentBoard() {
		return currentBoard;
	}

}
