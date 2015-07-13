package chess.pieces;

import static java.lang.Math.abs;
import static java.lang.Math.signum;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import chess.Board;

public class Rook implements Piece {

	private final Colour colour;
	private final Position position;

	public Rook(final Colour colour, final Position position) {
		this.colour = colour;
		this.position = position;
	}

	@Override
	public Rook moveTo(final Position position) {
		return new Rook(colour, position);
	}

	@Override
	public boolean moveIsIllegal(final Position position, final Board board) {
		if (! (verticalMove(position) | horizontalMove(position))) {
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

	private boolean horizontalMove(final Position position) {
		return position.row == this.position.row;
	}

	private boolean verticalMove(final Position position) {
		return position.column == this.position.column;
	}

	private boolean routeIsNotClear(final Position destination, final Board board) {
		if (verticalMove(destination)) {
			return verticalRouteIsNotClear(destination, board);
		} else {
			return horizontalRouteIsNotClear(destination, board);
		}
	}

	private boolean horizontalRouteIsNotClear(final Position destination, final Board board) {
		final int columnSign = (int) signum(destination.column - position.column);
		for (int i = 1; i < abs(destination.column - position.column); i++) {
			final Position positionOnRoute = new Position(position.column + i*columnSign, position.row);
			if (board.isOccupiedAt(positionOnRoute)) {
				return true;
			}
		}
		return false;
	}

	private boolean verticalRouteIsNotClear(final Position destination, final Board board) {
		final int rowSign = (int) signum(destination.row - position.row);
		for (int i = 1; i < abs(destination.row - position.row); i++) {
			final Position positionOnRoute = new Position(position.column, position.row + i*rowSign);
			if (board.isOccupiedAt(positionOnRoute)) {
				return true;
			}
		}
		return false;
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
		return colour.toString() + " Rook @ " + position;
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
