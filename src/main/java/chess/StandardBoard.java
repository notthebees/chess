package chess;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import chess.pieces.Piece;
import chess.pieces.Position;

public class StandardBoard implements Board {

	private final Set<Piece> pieces = new HashSet<>();

	public StandardBoard(final List<Piece> pieces) {
		this.pieces.addAll(pieces);
	}

	public StandardBoard(final Piece...pieces) {
		for (final Piece piece : pieces) {
			this.pieces.add(piece);
		}
	}

	@Override
	public StandardBoard play(final Move move) {
		if (moveIsOffBoard(move.to)) {
			return this;
		}
		if (pieceAt(move.from).moveIsIllegal(move.to, this)) {
			return this;
		}
		if (isOccupiedAt(move.to)) {
			if (piecesAreSameColour(move)) {
				return this;
			}
		}
		return new StandardBoard(newPieces(move));
	}

	private boolean piecesAreSameColour(final Move move) {
		final Piece movingPiece = pieceAt(move.from);
		final Piece destinationPiece = pieceAt(move.to);
		return movingPiece.colour().equals(destinationPiece.colour());
	}

	private List<Piece> newPieces(final Move move) {
		final Piece toMove = pieceAt(move.from);
		final List<Piece> newPieces = new ArrayList<>(pieces);
		newPieces.remove(toMove);
		newPieces.add(toMove.moveTo(move.to));
		return newPieces;
	}

	private boolean moveIsOffBoard(final Position to) {
		return to.column < 1 | to.column > 8 | to.row < 1 | to.row > 8;
	}

	@Override
	public boolean isOccupiedAt(final Position position) {
		for (final Piece piece : pieces) {
			if (piece.position().equals(position)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Piece pieceAt(final Position position) {
		for (final Piece piece : pieces) {
			if (piece.position().equals(position)) {
				return piece;
			}
		}
		return null;
	}

	@Override
	public String toString() {
		return "Standard Board with " + pieces.toString();
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
