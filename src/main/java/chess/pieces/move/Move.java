package chess.pieces.move;

import chess.Board;
import chess.pieces.Piece;
import chess.pieces.Position;

public class Move {

	public final Position from;
	public final Position to;

	public Move(final Position from, final Position to) {
		this.from = from;
		this.to = to;
	}

	public Position destination() {
		return to;
	}

	public Piece movingPiece(final Board board) {
		return board.pieceAt(from);
	}

	public boolean isIllegal(final Board board) {
		if (moveIsOffBoard()) {
			return true;
		}
		if (board.pieceAt(from).moveIsIllegal(to, board)) {
			return true;
		}
		return false;
	}

	private boolean moveIsOffBoard() {
		if (to.column < 1 | to.row < 1) {
			return true;
		}
		if (to.column > 8 | to.row > 8) {
			return true;
		}
		return false;
	}

}
