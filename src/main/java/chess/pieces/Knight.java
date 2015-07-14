package chess.pieces;

import static java.lang.Math.abs;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import chess.Board;

public class Knight implements Piece {

	private final Colour colour;
	private final Position position;
	private final boolean hasMoved;

	private Knight(final Colour colour, final Position position, final boolean hasMoved) {
		this.colour = colour;
		this.position = position;
		this.hasMoved = hasMoved;
	}

	public Knight(final Colour colour, final Position position) {
		this(colour, position, false);
	}

	@Override
	public Knight moveTo(final Position position) {
		return new Knight(colour, position, true);
	}

	@Override
	public boolean moveIsIllegal(final Position position, final Board board) {
		if (board.isOccupiedBy(colour, position)) {
			return true;
		}
		return ! ((columnDifference(position) == 2) & (rowDifference(position) == 1)
				| (columnDifference(position) == 1) & (rowDifference(position) == 2));
	}

	private int rowDifference(final Position position) {
		return abs(position.row - this.position.row);
	}

	private int columnDifference(final Position position) {
		return abs(position.column - this.position.column);
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
	public boolean hasMoved() {
		return hasMoved;
	}

	@Override
	public String toString() {
		return colour.toString() + " Knight @ " + position;
	}

	@Override
	public boolean equals(final Object obj) {
		if (obj instanceof Knight) {
			final Knight other = (Knight) obj;
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
