package chess.pieces;

import static java.lang.Math.abs;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import chess.Board;

public class King implements Piece {

	private final Colour colour;
	private final Position position;

	public King(final Colour colour, final Position position) {
		this.colour = colour;
		this.position = position;
	}

	@Override
	public King moveTo(final Position position) {
		return new King(colour, position);
	}

	@Override
	public boolean moveIsIllegal(final Position position, final Board board) {
		if (board.isOccupiedBy(colour, position)) {
			return true;
		}
		return columnTooFar(position) | rowTooFar(position);
	}

	private boolean rowTooFar(final Position position) {
		return abs(position.row - this.position.row) > 1;
	}

	private boolean columnTooFar(final Position position) {
		return abs(position.column - this.position.column) > 1;
	}

	@Override
	public Colour colour() {
		return colour;
	}

	@Override
	public Position position() {
		return position;
	}

	@Override
	public String toString() {
		return colour.toString() + " King @ " + position;
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
