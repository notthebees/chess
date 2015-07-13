package chess.pieces;

import static chess.pieces.Colour.WHITE;
import static java.lang.Math.abs;

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
		if (board.isOccupiedBy(colour, destination)) {
			return true;
		}
		if (board.isOccupiedAt(destination)) {
			return ! legitimateCapturingMove(destination);
		}

		return ! legitimateAdvancingMove(destination, board);
	}

	private boolean legitimateCapturingMove(final Position destination) {
		return (oneStepSideways(destination)
				& forwardOneStep(destination));
	}

	private boolean oneStepSideways(final Position destination) {
		return abs(destination.column - position.column) == 1;
	}

	private boolean legitimateAdvancingMove(final Position destination, final Board board) {
		if (! columnStaysTheSame(destination)) {
			return false;
		}
		if (forwardOneStep(destination)) {
			return true;
		}
		if (forwardTwoSteps(destination)) {
			return onStartingSquare() & ! board.isOccupiedAt(spaceInFront());
		}
		return false;
	}

	private boolean onStartingSquare() {
		if (colour.equals(WHITE)) {
			return position.row == 2;
		} else {
			return position.row == 7;
		}
	}

	private Position spaceInFront() {
		if (colour.equals(WHITE)) {
			return new Position(position.column, position.row + 1);
		} else {
			return new Position(position.column, position.row - 1);
		}
	}

	private boolean forwardTwoSteps(final Position destination) {
		if (colour.equals(WHITE)) {
			return destination.row == position.row + 2;
		} else {
			return destination.row == position.row - 2;
		}
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
