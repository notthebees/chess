package chess.pieces;

import static java.lang.Math.abs;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import chess.Board;

public class Bishop implements Piece {

	private final Colour colour;
	private final Position position;

	public Bishop(final Colour colour, final Position position) {
		this.colour = colour;
		this.position = position;
	}

	@Override
	public Bishop moveTo(final Position position) {
		return new Bishop(colour, position);
	}

	@Override
	public boolean moveIsIllegal(final Position position, final Board board) {
		return (rowDistance(position) != columnDistance(position));
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
