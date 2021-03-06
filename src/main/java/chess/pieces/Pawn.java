package chess.pieces;

import static java.lang.Math.abs;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import chess.Board;

public class Pawn implements Piece{

	private final Colour colour;
	private final Position position;
	private final boolean hasMoved;

	private Pawn(final Colour colour, final Position position, final boolean hasMoved) {
		this.colour = colour;
		this.position = position;
		this.hasMoved = hasMoved;
	}

	public Pawn(final Colour colour, final Position position) {
		this(colour, position, false);
	}

	@Override
	public boolean requiresReplacement() {
		if (position.row == colour.endRow()) {
			return true;
		}
		return false;
	}

	@Override
	public boolean hasMoved() {
		return hasMoved;
	}

	@Override
	public Pawn moveTo(final Position position) {
		return new Pawn(colour, position, true);
	}

	@Override
	public boolean moveIsIllegal(final Position destination, final Board board) {
		if (destination.equals(position)) {
			return true;
		}
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
		return position.row == colour.frontRow();
	}

	private Position spaceInFront() {
		return new Position(position.column, position.row + colour.forwardStep());
	}

	private boolean forwardTwoSteps(final Position destination) {
		return destination.row == position.row + 2*colour.forwardStep();
	}

	private boolean forwardOneStep(final Position destination) {
		return destination.row == position.row + colour.forwardStep();
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
	public String print() {
		return "p" + colour.playerNumber();
	}

	@Override
	public String toString() {
		return colour.toString() + " Pawn @ " + position;
	}

	@Override
	public boolean equals(final Object obj) {
		if (obj instanceof Pawn) {
			final Pawn other = (Pawn) obj;
			return new EqualsBuilder()
			.append(colour, other.colour)
			.append(position, other.position)
			.isEquals();
		} else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder()
		.append(colour)
		.append(position)
		.toHashCode();
	}

}
