package chess.pieces.move;

import java.util.Set;

import chess.Board;
import chess.pieces.Colour;
import chess.pieces.Piece;
import chess.pieces.Position;

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
	public boolean isIllegal(final Board board) {
		final Piece king = board.pieceAt(colour.kingPosition());
		final Piece rook = board.pieceAt(colour.kingSideRookPosition());
		if (king.hasMoved() | rook.hasMoved()) {
			return true;
		}
		if (board.isInCheck(colour)) {
			return true;
		}
		if (interveningSpacesAttacked(board)) {
			return true;
		}
		return rook.moveIsIllegal(rookDestination(), board);
	}

	private boolean interveningSpacesAttacked(final Board board) {
		return board.isAttackedBy(colour.opposite(), kingDestination())
				| board.isAttackedBy(colour.opposite(), rookDestination());
	}

	private Position rookDestination() {
		return new Position(colour.backRow()+5*colour.forwardStep(), colour.backRow());
	}

}
