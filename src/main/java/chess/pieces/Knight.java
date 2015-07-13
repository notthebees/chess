package chess.pieces;

import static java.lang.Math.abs;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Knight implements Piece {

	private final Colour colour;
	private final Position position;

	public Knight(final Colour colour, final Position position) {
		this.colour = colour;
		this.position = position;
	}

	@Override
	public Knight moveTo(final Position position) {
		if (canMoveTo(position)) {
			return new Knight(colour, position);
		}
		return this;
	}

	private boolean canMoveTo(final Position position) {
		if (noMove(position)) {
			return false;
		}
		return (columnDifference(position) == 2) & (rowDifference(position) == 1)
				| (columnDifference(position) == 1) & (rowDifference(position) == 2);
	}

	private int rowDifference(final Position position) {
		return abs(position.row - this.position.row);
	}

	private int columnDifference(final Position position) {
		return abs(position.column - this.position.column);
	}

	private boolean noMove(final Position position) {
		return position.equals(this.position);
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
		return colour.toString() + " Knight @ " + position;
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
