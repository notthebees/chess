package chess;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import chess.pieces.Colour;
import chess.pieces.Piece;
import chess.pieces.Position;
import chess.pieces.move.Move;

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
		if (move.isIllegal(this)) {
			return this;
		}
		return new StandardBoard(newPieces(move));
	}

	private List<Piece> newPieces(final Move move) {
		final Piece toMove = move.movingPiece(this);
		final List<Piece> newPieces = new ArrayList<>(pieces);
		newPieces.remove(toMove);
		newPieces.add(toMove.moveTo(move.destination()));
		return newPieces;
	}

	@Override
	public boolean isOccupiedBy(final Colour colour, final Position position) {
		if (! isOccupiedAt(position)) {
			return false;
		}
		return pieceAt(position).colour().equals(colour);
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
