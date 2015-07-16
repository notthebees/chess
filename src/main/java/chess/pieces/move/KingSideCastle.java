package chess.pieces.move;

import java.util.Set;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import chess.Board;
import chess.pieces.Colour;
import chess.pieces.King;
import chess.pieces.Piece;
import chess.pieces.Position;
import chess.pieces.Rook;

public class KingSideCastle implements Move {

	private final Colour colour;

	public KingSideCastle(final Colour colour) {
		this.colour = colour;
	}

	@Override
	public Set<Piece> updatePieces(final Board board) {
		final Set<Piece> pieces = board.pieces();
		final Piece king = board.pieceAt(colour.kingPosition());
		final Piece rook = board.pieceAt(colour.kingSideRookPosition());
		removeOldPieces(pieces, king, rook);
		addMovedPieces(pieces, king, rook);
		return pieces;
	}

	private void addMovedPieces(final Set<Piece> pieces, final Piece king,
			final Piece rook) {
		pieces.add(king.moveTo(kingDestination()));
		pieces.add(rook.moveTo(rookDestination()));
	}

	private void removeOldPieces(final Set<Piece> pieces, final Piece king,
			final Piece rook) {
		pieces.remove(king);
		pieces.remove(rook);
	}

	private Position kingDestination() {
		return new Position(colour.backRow()+6*colour.forwardStep(), colour.backRow());
	}

	@Override
	public boolean isIllegal(final Colour toMove, final Board board) {
		if (! toMove.equals(colour)) {
			return true;
		}
		if (piecesNotInPosition(board)) {
			return true;
		}
		final Piece king = board.pieceAt(toMove.kingPosition());
		final Piece rook = board.pieceAt(toMove.kingSideRookPosition());
		if (king.hasMoved() | rook.hasMoved()) {
			return true;
		}
		if (interveningPositionsOccupied(board)) {
			return true;
		}
		if (board.isInCheck(colour)) {
			return true;
		}
		if (interveningPositionsAttacked(board)) {
			return true;
		}
		if (board.pawnToReplace()) {
			return true;
		}
		return false;
	}

	private boolean piecesNotInPosition(final Board board) {
		if (! (relevantPositionsOccupied(board))) {
			return true;
		}
		if (! correctPiecesInPosition(board)) {
			return true;
		}
		return false;
	}

	private boolean correctPiecesInPosition(final Board board) {
		final Piece kingPositionPiece = board.pieceAt(colour.kingPosition());
		final Piece rookPositionPiece = board.pieceAt(colour.kingSideRookPosition());
		return kingPositionPiece.getClass().equals(King.class) & rookPositionPiece.getClass().equals(Rook.class);
	}

	private boolean relevantPositionsOccupied(final Board board) {
		return board.isOccupiedAt(colour.kingPosition()) & board.isOccupiedAt(colour.kingSideRookPosition());
	}

	private boolean interveningPositionsOccupied(final Board board) {
		return board.isOccupiedAt(kingDestination()) | board.isOccupiedAt(rookDestination());
	}

	private boolean interveningPositionsAttacked(final Board board) {
		return board.isAttackedBy(colour.opposite(), kingDestination())
				| board.isAttackedBy(colour.opposite(), rookDestination());
	}

	private Position rookDestination() {
		return new Position(colour.backRow()+5*colour.forwardStep(), colour.backRow());
	}

	@Override
	public String toString() {
		return colour + " kingside castle";
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
