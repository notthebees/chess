package chess.pieces;

import static chess.pieces.Colour.BLACK;
import static chess.pieces.Colour.WHITE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;

import chess.Board;
import chess.StandardBoard;

public class TestPawn {

	private final Board emptyBoard = new StandardBoard();

	@Test
	public void moveIllegalIfDestinationOccupiedByFriendlyPiece() {
		final Pawn pawn = new Pawn(WHITE, new Position(1, 2));
		final Board board = new StandardBoard(pawn, new Pawn(WHITE, position(1, 3)));
		assertThat(pawn.moveIsIllegal(position(1, 3), board), equalTo(true));
	}

	@Test
	public void someIllegalMoves() {
		final Pawn pawn = new Pawn(WHITE, new Position(1, 2));

		assertThat(pawn.moveIsIllegal(position(2, 2), emptyBoard), equalTo(true));
		assertThat(pawn.moveIsIllegal(position(2, 3), emptyBoard), equalTo(true));
		assertThat(pawn.moveIsIllegal(position(1, 5), emptyBoard), equalTo(true));
		assertThat(pawn.moveIsIllegal(position(1, 1), emptyBoard), equalTo(true));
	}

	@Test
	public void moveUpIfWhiteAndDownIfBlack() {
		final Pawn whitePawn = new Pawn(WHITE, new Position(1, 2));
		final Pawn blackPawn = new Pawn(BLACK, new Position(1, 7));

		assertThat(whitePawn.moveIsIllegal(position(1, 3), emptyBoard), equalTo(false));
		assertThat(blackPawn.moveIsIllegal(position(1, 6), emptyBoard), equalTo(false));
	}

	private Position position(final int column, final int row) {
		return new Position(column, row);
	}

}
