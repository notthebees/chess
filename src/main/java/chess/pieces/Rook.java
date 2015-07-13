package chess.pieces;

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
		return ! ((position.column == this.position.column) | (position.row == this.position.row));
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
