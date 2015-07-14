package chess.pieces.move;

import java.util.Set;

import chess.Board;
import chess.pieces.Colour;
import chess.pieces.Piece;
import chess.pieces.Position;

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
	public boolean isIllegal(final Board board) {
		final Piece king = board.pieceAt(colour.kingPosition());
		final Piece rook = board.pieceAt(colour.queenSideRookPosition());
		if (king.hasMoved() | rook.hasMoved()) {
			return true;
		}
		return rook.moveIsIllegal(rookDestination(), board);
	}

	private Position rookDestination() {
		return new Position(colour.backRow()+3*colour.forwardStep(), colour.backRow());
	}

}
