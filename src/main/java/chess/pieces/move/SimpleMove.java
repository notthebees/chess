package chess.pieces.move;

import java.util.Set;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import chess.Board;
import chess.StandardBoard;
import chess.pieces.Colour;
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
	public Set<Piece> updatePieces(final Board board) {
		final Set<Piece> newPieces = board.pieces();
		removeCapturedPieces(board, newPieces);
		movePiece(board.pieceAt(from), newPieces);
		return newPieces;
	}

	private void movePiece(final Piece movingPiece, final Set<Piece> newPieces) {
		newPieces.remove(movingPiece);
		newPieces.add(movingPiece.moveTo(to));
	}

	private void removeCapturedPieces(final Board board, final Set<Piece> newPieces) {
		if (board.isOccupiedAt(to)) {
			newPieces.remove(board.pieceAt(to));
		}
	}

	@Override
	public boolean isIllegal(final Colour colour, final Board board) {
		if (! board.isOccupiedAt(from)) {
			return true;
		}
		if (moveIsOffBoard()) {
			return true;
		}
		if (! board.pieceAt(from).colour().equals(colour)) {
			return true;
		}
		if (board.pieceAt(from).moveIsIllegal(to, board)) {
			return true;
		}
		if (leavesKingInCheck(board)) {
			return true;
		}
		if (board.pawnToReplace()) {
			return true;
		}
		return false;
	}

	private boolean leavesKingInCheck(final Board board) {
		final Board resultingBoard = new StandardBoard(updatePieces(board));
		return resultingBoard.isInCheck(board.pieceAt(from).colour());
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

	@Override
	public String toString() {
		return "Simple move from " + from + " to " + to;
	}

	@Override
	public boolean equals(final Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

}
