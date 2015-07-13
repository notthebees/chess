package chess.pieces;

import static java.lang.Math.abs;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Bishop implements Piece {

	private final Colour colour;
	private final Position position;

	public Bishop(final Colour colour, final Position position) {
		this.colour = colour;
		this.position = position;
	}

	@Override
	public Bishop moveTo(final Position position) {
		if (canMoveTo(position)) {
			return new Bishop(colour, position);
		}
		return this;
	}

	private boolean canMoveTo(final Position position) {
		if (noMove(position)) {
			return false;
		}
		return (abs(position.row - this.position.row) == abs(position.column - this.position.column));
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
		return colour.toString() + " Bishop @ " + position;
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
