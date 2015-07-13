package chess.pieces;

import static chess.pieces.Colour.WHITE;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import chess.Board;

public class Pawn implements Piece{

	private final Colour colour;
	private final Position position;

	public Pawn(final Colour colour, final Position position) {
		this.colour = colour;
		this.position = position;
	}

	@Override
	public Pawn moveTo(final Position position) {
		return new Pawn(colour, position);
	}

	@Override
	public boolean moveIsIllegal(final Position destination, final Board board) {
		if (board.isOccupiedBy(colour, position)) {
			return true;
		}

		return ! oneStepForward(destination);
	}

	private boolean oneStepForward(final Position destination) {
		return (columnStaysTheSame(destination)
				& forwardOneStep(destination));
	}

	private boolean forwardOneStep(final Position destination) {
		if (colour.equals(WHITE)) {
			return destination.row == position.row + 1;
		} else {
			return destination.row == position.row - 1;
		}
	}

	private boolean columnStaysTheSame(final Position destination) {
		return destination.column == position.column;
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
		return colour.toString() + " Pawn @ " + position;
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
