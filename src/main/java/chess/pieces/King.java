package chess.pieces;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class King implements Piece {

	private final Colour colour;
	private final Position position;

	public King(final Colour colour, final Position position) {
		this.colour = colour;
		this.position = position;
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
