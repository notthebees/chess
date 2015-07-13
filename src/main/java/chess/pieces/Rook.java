package chess.pieces;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Rook implements Piece {

	private final Colour colour;
	private final Position position;

	public Rook(final Colour colour, final Position position) {
		this.colour = colour;
		this.position = position;
	}

	@Override
	public Rook moveTo(final Position position) {
		if (canMoveTo(position)) {
			return new Rook(colour, position);
		}
		return this;
	}

	@Override
	public boolean moveIsIllegal(final Position position) {
		return ! canMoveTo(position);
	}

	private boolean canMoveTo(final Position position) {
		if (noMove(position)) {
			return false;
		}
		return (position.column == this.position.column) | (position.row == this.position.row);
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
