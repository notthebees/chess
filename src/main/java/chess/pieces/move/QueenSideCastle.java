package chess.pieces.move;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import chess.Board;
import chess.pieces.Colour;
import chess.pieces.King;
import chess.pieces.Piece;
import chess.pieces.Position;
import chess.pieces.Rook;

public class QueenSideCastle implements Move {

	private final Colour colour;

	public QueenSideCastle(final Colour colour) {
		this.colour = colour;
	}

	@Override
	public Set<Piece> updatePieces(final Board board) {
		final Set<Piece> pieces = board.pieces();
		final Piece king = board.pieceAt(colour.kingPosition());
		final Piece rook = board.pieceAt(colour.queenSideRookPosition());
		removeOldPieces(pieces, king, rook);
		addMovedPieces(pieces, king, rook);
		return pieces;
	}

	private void addMovedPieces(final Set<Piece> pieces, final Piece king, final Piece rook) {
		pieces.add(king.moveTo(kingDestination()));
		pieces.add(rook.moveTo(rookDestination()));
	}

	private void removeOldPieces(final Set<Piece> pieces, final Piece king, final Piece rook) {
		pieces.remove(king);
		pieces.remove(rook);
	}

	private Position kingDestination() {
		return new Position(colour.backRow()+2*colour.forwardStep(), colour.backRow());
	}

	@Override
	public boolean isIllegal(final Colour colour, final Board board) {
		if (piecesNotInPosition(colour, board)) {
			return true;
		}
		final Piece king = board.pieceAt(colour.kingPosition());
		final Piece rook = board.pieceAt(colour.queenSideRookPosition());
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

	private boolean piecesNotInPosition(final Colour colour2, final Board board) {
		if (! (relevantPositionsOccupied(colour, board))) {
			return true;
		}
		if (! correctPiecesInPosition(colour, board)) {
			return true;
		}
		return false;
	}

	private boolean correctPiecesInPosition(final Colour colour, final Board board) {
		final Piece kingPositionPiece = board.pieceAt(colour.kingPosition());
		final Piece rookPositionPiece = board.pieceAt(colour.queenSideRookPosition());
		return kingPositionPiece.getClass().equals(King.class) & rookPositionPiece.getClass().equals(Rook.class);
	}

	private boolean relevantPositionsOccupied(final Colour colour, final Board board) {
		return board.isOccupiedAt(colour.kingPosition()) & board.isOccupiedAt(colour.queenSideRookPosition());
	}

	private boolean interveningPositionsAttacked(final Board board) {
		for (final Position position : interveningPositions()) {
			if (board.isAttackedBy(colour.opposite(), position)) {
				return true;
			}
		}
		return false;
	}

	private boolean interveningPositionsOccupied(final Board board) {
		for (final Position position : interveningPositions()) {
			if (board.isOccupiedAt(position)) {
				return true;
			}
		}
		return false;
	}

	private Set<Position> interveningPositions() {
		final Set<Position> interveningPositions = new HashSet<>();
		interveningPositions.add(kingDestination());
		interveningPositions.add(rookDestination());
		interveningPositions.add(new Position(colour.backRow()+colour.forwardStep(), colour.backRow()));
		return interveningPositions;
	}

	private Position rookDestination() {
		return new Position(colour.backRow()+3*colour.forwardStep(), colour.backRow());
	}

	@Override
	public String toString() {
		return colour + " queenside castle";
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
