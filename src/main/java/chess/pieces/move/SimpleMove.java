package chess.pieces.move;

import java.util.Set;

import chess.Board;
import chess.StandardBoard;
import chess.pieces.Colour;
import chess.pieces.Pawn;
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
		movePiece(board.pieceAt(from), newPieces, board);
		return newPieces;
	}

	private void movePiece(final Piece movingPiece, final Set<Piece> newPieces, final Board board) {
		newPieces.remove(movingPiece);
		if (pawnReachingEnd(movingPiece)) {
			newPieces.add(board.pawnReplacementAt(to));
		} else {
			newPieces.add(movingPiece.moveTo(to));
		}
	}

	private boolean pawnReachingEnd(final Piece movingPiece) {
		final Colour colour = movingPiece.colour();
		return movingPiece.getClass().equals(Pawn.class) & to.row == colour.endRow();
	}

	private void removeCapturedPieces(final Board board, final Set<Piece> newPieces) {
		if (board.isOccupiedAt(to)) {
			newPieces.remove(board.pieceAt(to));
		}
	}

	@Override
	public boolean isIllegal(final Board board) {
		if (to.equals(from)) {
			return true;
		}
		if (moveIsOffBoard()) {
			return true;
		}
		if (board.pieceAt(from).moveIsIllegal(to, board)) {
			return true;
		}
		if (leavesKingInCheck(board)) {
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

}
