package chess.pieces.move;

import java.util.HashSet;
import java.util.Set;

import chess.Board;
import chess.pieces.Piece;
import chess.pieces.Position;

public class SimpleMove implements Move {

	private final Position from;
	private final Position to;

	public SimpleMove(final Position from, final Position to) {
		this.from = from;
		this.to = to;
	}

	@Override
	public Set<Piece> updatePieces(final Set<Piece> pieces, final Board board) {
		final Piece movingPiece = board.pieceAt(from);
		final Set<Piece> newPieces = new HashSet<>(pieces);
		newPieces.remove(movingPiece);
		newPieces.add(movingPiece.moveTo(to));
		return newPieces;
	}

	@Override
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