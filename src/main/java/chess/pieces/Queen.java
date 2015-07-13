package chess.pieces;

import static java.lang.Math.abs;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import chess.Board;

public class Queen implements Piece {

	private final Colour colour;
	private final Position position;

	public Queen(final Colour colour, final Position position) {
		this.colour = colour;
		this.position = position;
	}

	@Override
	public Queen moveTo(final Position position) {
		return new Queen(colour, position);
	}

	@Override
	public boolean moveIsIllegal(final Position position, final Board board) {
		return ! (diagonalMove(position) | verticalMove(position) | horizontalMove(position));
	}

	private boolean horizontalMove(final Position position) {
		return position.row == this.position.row;
	}

	private boolean verticalMove(final Position position) {
		return position.column == this.position.column;
	}

	private boolean diagonalMove(final Position position) {
		return abs(position.row - this.position.row) == abs(position.column - this.position.column);
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
		return colour.toString() + " Queen @ " + position;
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
