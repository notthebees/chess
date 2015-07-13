package chess.pieces;

import static java.lang.Math.abs;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import chess.Board;

public class Knight implements Piece {

	private final Colour colour;
	private final Position position;

	public Knight(final Colour colour, final Position position) {
		this.colour = colour;
		this.position = position;
	}

	@Override
	public Knight moveTo(final Position position) {
		return new Knight(colour, position);
	}

	@Override
	public boolean moveIsIllegal(final Position position, final Board board) {
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
