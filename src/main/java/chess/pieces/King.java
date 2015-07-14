package chess.pieces;

import static java.lang.Math.abs;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import chess.Board;

public class King implements Piece {

	private final Colour colour;
	private final Position position;
	private final boolean hasMoved;

	private King(final Colour colour, final Position position, final boolean hasMoved) {
		this.colour = colour;
		this.position = position;
		this.hasMoved = hasMoved;

	}

	public King(final Colour colour, final Position position) {
		this(colour, position, false);
	}

	@Override
	public boolean hasMoved() {
		return hasMoved;
	}

	@Override
	public King moveTo(final Position position) {
		return new King(colour, position, true);
	}

	@Override
	public boolean moveIsIllegal(final Position destination, final Board board) {
		if (destination.equals(position)) {
			return true;
		}
		if (board.isOccupiedBy(colour, destination)) {
			return true;
		}
		return columnTooFar(destination) | rowTooFar(destination);
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
		if (obj instanceof King) {
			final King other = (King) obj;
			return new EqualsBuilder()
			.append(colour, other.colour)
			.append(position, other.position)
			.isEquals();
		} else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder()
		.append(colour)
		.append(position)
		.toHashCode();
	}

}
