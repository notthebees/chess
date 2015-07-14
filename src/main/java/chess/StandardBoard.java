package chess;

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

	public StandardBoard(final Set<Piece> pieces) {
		this.pieces.addAll(pieces);
	}

	public StandardBoard(final List<Piece> pieces) {
		this.pieces.addAll(pieces);
	}

	public StandardBoard(final Piece...pieces) {
		for (final Piece piece : pieces) {
			this.pieces.add(piece);
		}
	}

	@Override
	public Set<Position> spacesAttackedBy(final Piece piece) {
		final Set<Position> allPositions = allPositions();
		final Set<Position> attackedPositions = new HashSet<>();
		for (final Position position : allPositions) {
			if (! piece.moveIsIllegal(position, this)) {
				attackedPositions.add(position);
			}
		}
		return attackedPositions;
	}

	private Set<Position> allPositions() {
		final Set<Position> positions = new HashSet<>();
		for (int column=1; column<=8; column++) {
			for (int row=1; row<=8; row++) {
				positions.add(new Position(column, row));
			}
		}
		return positions;
	}

	@Override
	public Set<Piece> pieces() {
		return new HashSet<>(pieces);
	}

	@Override
	public StandardBoard play(final Move move) {
		if (move.isIllegal(this)) {
			return this;
		}
		return new StandardBoard(move.updatePieces(this));
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
