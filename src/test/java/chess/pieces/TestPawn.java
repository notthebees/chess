package chess.pieces;

import static chess.pieces.Colour.BLACK;
import static chess.pieces.Colour.WHITE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;

import chess.Board;
import chess.StandardBoardBuilder;

public class TestPawn {

	private final Board emptyBoard = board().build();

	@Test
	public void recordsIfItHasMoved() {
		Pawn pawn = new Pawn(WHITE, at(1, 2));
		assertThat(pawn.hasMoved(), equalTo(false));
		pawn = pawn.moveTo(position(1, 3));
		assertThat(pawn.hasMoved(), equalTo(true));
	}

	@Test
	public void canMoveTwoSpacesOffStartingSquare() {
		final Pawn whitePawn = new Pawn(WHITE, at(1, 2));
		final Pawn blackPawn = new Pawn(BLACK, at(1, 7));
		final Pawn movedWhitePawn = new Pawn(WHITE, at(2, 3));

		assertThat(whitePawn.moveIsIllegal(position(1, 4), emptyBoard), equalTo(false));
		assertThat(blackPawn.moveIsIllegal(position(1, 5), emptyBoard), equalTo(false));
		assertThat(movedWhitePawn.moveIsIllegal(position(2, 5), emptyBoard), equalTo(true));

		final Board board = board().withPieces(whitePawn, new Pawn(BLACK, at(1, 3))).build();
		assertThat(whitePawn.moveIsIllegal(position(1, 4), board), equalTo(true));
	}

	@Test
	public void moveDiagonallyToCapture() {
		final Pawn pawn = new Pawn(WHITE, at(1, 2));
		final Board board = board()
				.withPieces(pawn, new Pawn(BLACK, position(1, 3)), new Pawn(BLACK, at(2, 3))).build();

		assertThat(pawn.moveIsIllegal(position(1, 3), board), equalTo(true));
		assertThat(pawn.moveIsIllegal(position(2, 3), board), equalTo(false));
	}

	@Test
	public void moveIllegalIfDestinationOccupiedByFriendlyPiece() {
		final Pawn pawn = new Pawn(WHITE, at(1, 2));
		final Board board = board().withPieces(pawn, new Pawn(WHITE, at(1, 3))).build();
		assertThat(pawn.moveIsIllegal(position(1, 3), board), equalTo(true));
	}

	@Test
	public void someIllegalMoves() {
		final Pawn pawn = new Pawn(WHITE, at(1, 2));

		assertThat(pawn.moveIsIllegal(position(2, 2), emptyBoard), equalTo(true));
		assertThat(pawn.moveIsIllegal(position(2, 3), emptyBoard), equalTo(true));
		assertThat(pawn.moveIsIllegal(position(1, 5), emptyBoard), equalTo(true));
		assertThat(pawn.moveIsIllegal(position(1, 1), emptyBoard), equalTo(true));
	}

	@Test
	public void moveUpIfWhiteAndDownIfBlack() {
		final Pawn whitePawn = new Pawn(WHITE, at(1, 2));
		final Pawn blackPawn = new Pawn(BLACK, at(1, 7));

		assertThat(whitePawn.moveIsIllegal(position(1, 3), emptyBoard), equalTo(false));
		assertThat(blackPawn.moveIsIllegal(position(1, 6), emptyBoard), equalTo(false));
	}

	private Position position(final int column, final int row) {
		return new Position(column, row);
	}

	private Position at(final int column, final int row) {
		return new Position(column, row);
	}

	private StandardBoardBuilder board() {
		return new StandardBoardBuilder();
	}

}
