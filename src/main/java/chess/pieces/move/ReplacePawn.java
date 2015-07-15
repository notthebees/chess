package chess.pieces.move;

import java.util.Set;

import chess.Board;
import chess.pieces.Bishop;
import chess.pieces.Colour;
import chess.pieces.Knight;
import chess.pieces.Pawn;
import chess.pieces.Piece;
import chess.pieces.Position;
import chess.pieces.Queen;
import chess.pieces.Rook;

public class ReplacePawn implements Move {

	private final Position position;
	private final Piece replacementPiece;

	public ReplacePawn(final Position position, final Piece replacementPiece) {
		this.position = position;
		this.replacementPiece = replacementPiece;
	}

	@Override
	public Set<Piece> updatePieces(final Board board) {
		final Set<Piece> pieces = board.pieces();
		pieces.remove(board.pieceAt(position));
		pieces.add(replacementPiece);
		return pieces;
	}

	@Override
	public boolean isIllegal(final Board board) {
		if (! board.isOccupiedAt(position)) {
			return true;
		}
		if (pieceIsNotAPawn(board)) {
			return true;
		}
		if (replacementPieceInvalid(board)) {
			return true;
		}
		if (pawnNotAtEnd(board)) {
			return true;
		}
		return false;
	}

	private boolean pawnNotAtEnd(final Board board) {
		final Colour pawnColour = board.pieceAt(position).colour();
		if (position.row != pawnColour.endRow()) {
			return true;
		}
		return false;
	}

	private boolean replacementPieceInvalid(final Board board) {
		if (replacementPieceIsWrongColour(board)) {
			return true;
		}
		if (replacementPieceIsWrongType()) {
			return true;
		}
		return false;
	}

	private boolean replacementPieceIsWrongType() {
		if (replacementPiece.getClass().equals(Queen.class)) {
			return false;
		}
		if (replacementPiece.getClass().equals(Rook.class)) {
			return false;
		}
		if (replacementPiece.getClass().equals(Bishop.class)) {
			return false;
		}
		if (replacementPiece.getClass().equals(Knight.class)) {
			return false;
		}
		return true;
	}

	private boolean replacementPieceIsWrongColour(final Board board) {
		return ! replacementPiece.colour().equals(board.pieceAt(position).colour());
	}

	private boolean pieceIsNotAPawn(final Board board) {
		return ! board.pieceAt(position).getClass().equals(Pawn.class);
	}

}