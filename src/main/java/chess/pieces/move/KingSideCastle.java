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
	public boolean isIllegal(final Board board) {
		final Piece king = board.pieceAt(colour.kingPosition());
		final Piece rook = board.pieceAt(colour.kingSideRookPosition());
		if (king.hasMoved() | rook.hasMoved()) {
			return true;
		}
		return rook.moveIsIllegal(rookDestination(), board);
	}

	private Position rookDestination() {
		return new Position(colour.backRow()+5*colour.forwardStep(), colour.backRow());
	}

	@Override
	public Set<Piece> updatePieces(final Board board) {
		// TODO Auto-generated method stub
		return null;
	}

}
