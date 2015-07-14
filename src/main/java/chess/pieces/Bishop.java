package chess.pieces;

import static java.lang.Math.abs;
import static java.lang.Math.signum;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import chess.Board;

public class Bishop implements Piece {

	private final Colour colour;
	private final Position position;
	private final boolean hasMoved;

	private Bishop(final Colour colour, final Position position, final boolean hasMoved) {
		this.colour = colour;
		this.position = position;
		this.hasMoved = hasMoved;
	}

	public Bishop(final Colour colour, final Position position) {
		this(colour, position, false);
	}

	@Override
	public Bishop moveTo(final Position position) {
		return new Bishop(colour, position, true);
	}

	@Override
	public boolean moveIsIllegal(final Position position, final Board board) {
		if (rowDistance(position) != columnDistance(position)) {
			return true;
		}
		if (board.isOccupiedBy(colour, position)) {
			return true;
		}
		if (routeIsNotClear(position, board)) {
			return true;
		}
		return false;
	}

	private boolean routeIsNotClear(final Position destination, final Board board) {
		final int columnSign = (int) signum(destination.column - position.column);
		final int rowSign = (int) signum(destination.row - position.row);

		for (int i = 1; i < columnDistance(destination); i++) {
			final Position positionOnRoute = new Position(position.column + i*columnSign, position.row + i*rowSign);
			if (board.isOccupiedAt(positionOnRoute)) {
				return true;
			}
		}
		return false;
	}

	private int columnDistance(final Position position) {
		return abs(position.column - this.position.column);
	}

	private int rowDistance(final Position position) {
		return abs(position.row - this.position.row);
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
		return colour.toString() + " Bishop @ " + position;
	}

	@Override
	public boolean equals(final Object obj) {
		if (obj instanceof Bishop) {
			final Bishop other = (Bishop) obj;
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
