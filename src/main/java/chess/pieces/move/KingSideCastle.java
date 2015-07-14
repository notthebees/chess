package chess.pieces.move;

import java.util.Set;

import chess.Board;
import chess.pieces.Colour;
import chess.pieces.Piece;

public class KingSideCastle implements Move {

	private final Colour colour;

	public KingSideCastle(final Colour colour) {
		this.colour = colour;
	}

	@Override
	public Set<Piece> updatePieces(final Set<Piece> pieces, final Board board) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isIllegal(final Board board) {
		final Piece king = board.pieceAt(colour.kingPosition());
		final Piece rook = board.pieceAt(colour.kingSideRookPosition());
		if (king.hasMoved() | rook.hasMoved()) {
			return true;
		}
		return false;
	}

}
