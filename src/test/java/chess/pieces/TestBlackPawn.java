package chess.pieces;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;

import chess.Board;
import chess.StandardBoard;

public class TestBlackPawn {

	private final Board emptyBoard = new StandardBoard();

	@Test
	public void canMoveTwoSpacesOffStartingSquare() {
		final BlackPawn pawn = new BlackPawn(position(1, 7));
		final BlackPawn movedPawn = new BlackPawn(position(2, 6));

		assertThat(pawn.moveIsIllegal(position(1, 5), emptyBoard), equalTo(false));
		assertThat(movedPawn.moveIsIllegal(position(2, 4), emptyBoard), equalTo(true));

		final Board board = new StandardBoard(pawn, new BlackPawn(position(1, 6)));
		assertThat(pawn.moveIsIllegal(position(1, 5), board), equalTo(true));
	}

	@Test
	public void moveDiagonallyToCapture() {
		final BlackPawn pawn = new BlackPawn(new Position(1, 7));
		final Board board = new StandardBoard(
				pawn,
				new BlackPawn(position(1, 6)),
				new WhitePawn(position(2, 6)));

		assertThat(pawn.moveIsIllegal(position(1, 6), board), equalTo(true));
		assertThat(pawn.moveIsIllegal(position(2, 6), board), equalTo(false));
	}

	@Test
	public void moveIllegalIfDestinationOccupiedByFriendlyPiece() {
		final BlackPawn pawn = new BlackPawn(new Position(1, 7));
		final Board board = new StandardBoard(pawn, new BlackPawn(position(1, 6)));
		assertThat(pawn.moveIsIllegal(position(1, 6), board), equalTo(true));
	}

	@Test
	public void someIllegalMoves() {
		final BlackPawn pawn = new BlackPawn(new Position(1, 7));

		assertThat(pawn.moveIsIllegal(position(2, 7), emptyBoard), equalTo(true));
		assertThat(pawn.moveIsIllegal(position(2, 6), emptyBoard), equalTo(true));
		assertThat(pawn.moveIsIllegal(position(1, 4), emptyBoard), equalTo(true));
		assertThat(pawn.moveIsIllegal(position(1, 8), emptyBoard), equalTo(true));
	}

	@Test
	public void basicMovement() {
		final BlackPawn pawn = new BlackPawn(new Position(1, 7));

		assertThat(pawn.moveIsIllegal(position(1, 6), emptyBoard), equalTo(false));
	}

	private Position position(final int column, final int row) {
		return new Position(column, row);
	}

}
