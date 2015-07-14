package chess.pieces;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;

import chess.Board;
import chess.StandardBoard;

public class TestWhitePawn {

	private final Board emptyBoard = new StandardBoard();

	@Test
	public void canMoveTwoSpacesOffStartingSquare() {
		final WhitePawn pawn = new WhitePawn(position(1, 2));
		final WhitePawn movedPawn = new WhitePawn(position(2, 3));

		assertThat(pawn.moveIsIllegal(position(1, 4), emptyBoard), equalTo(false));
		assertThat(movedPawn.moveIsIllegal(position(2, 5), emptyBoard), equalTo(true));

		final Board board = new StandardBoard(pawn, new WhitePawn(position(1, 3)));
		assertThat(pawn.moveIsIllegal(position(1, 4), board), equalTo(true));
	}

	@Test
	public void moveDiagonallyToCapture() {
		final WhitePawn pawn = new WhitePawn(new Position(1, 2));
		final Board board = new StandardBoard(
				pawn,
				new WhitePawn(position(1, 3)),
				new BlackPawn(position(2, 3)));

		assertThat(pawn.moveIsIllegal(position(1, 3), board), equalTo(true));
		assertThat(pawn.moveIsIllegal(position(2, 3), board), equalTo(false));
	}

	@Test
	public void moveIllegalIfDestinationOccupiedByFriendlyPiece() {
		final WhitePawn pawn = new WhitePawn(new Position(1, 2));
		final Board board = new StandardBoard(pawn, new WhitePawn(position(1, 3)));
		assertThat(pawn.moveIsIllegal(position(1, 3), board), equalTo(true));
	}

	@Test
	public void someIllegalMoves() {
		final WhitePawn pawn = new WhitePawn(new Position(1, 2));

		assertThat(pawn.moveIsIllegal(position(2, 2), emptyBoard), equalTo(true));
		assertThat(pawn.moveIsIllegal(position(2, 3), emptyBoard), equalTo(true));
		assertThat(pawn.moveIsIllegal(position(1, 5), emptyBoard), equalTo(true));
		assertThat(pawn.moveIsIllegal(position(1, 1), emptyBoard), equalTo(true));
	}

	@Test
	public void basicMovement() {
		final WhitePawn pawn = new WhitePawn(new Position(1, 2));

		assertThat(pawn.moveIsIllegal(position(1, 3), emptyBoard), equalTo(false));
	}

	private Position position(final int column, final int row) {
		return new Position(column, row);
	}

}
