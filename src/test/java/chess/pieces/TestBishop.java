package chess.pieces;

import static chess.pieces.Colour.BLACK;
import static chess.pieces.Colour.WHITE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;

import chess.Board;
import chess.StandardBoard;

public class TestBishop {

	private final Board emptyBoard = new StandardBoard();

	@Test
	public void moveIllegalIfPathObstructedByAnyPiece() {
		final Bishop bishop = new Bishop(BLACK, new Position(4, 4));
		final Board board = new StandardBoard(
				bishop,
				new Pawn(BLACK, position(5,  5)),
				new Pawn(WHITE, position(2,  6)));

		assertThat(bishop.moveIsIllegal(position(7, 7), board), equalTo(true));
		assertThat(bishop.moveIsIllegal(position(1, 7), board), equalTo(true));
	}

	@Test
	public void moveIllegalIfDestinationOccupiedByFriendlyPiece() {
		final Bishop bishop = new Bishop(BLACK, new Position(1, 1));
		final Board board = new StandardBoard(bishop, new Pawn(BLACK, position(4,  4)));
		assertThat(bishop.moveIsIllegal(position(4, 4), board), equalTo(true));
	}

	@Test
	public void moveIllegalDueToMovementRules() {
		final Bishop bishop = new Bishop(BLACK, position(1, 1));

		assertThat(bishop.moveIsIllegal(position(2, 3), emptyBoard), equalTo(true));
		assertThat(bishop.moveIsIllegal(position(1, 2), emptyBoard), equalTo(true));
		assertThat(bishop.moveIsIllegal(position(5, 1), emptyBoard), equalTo(true));
		assertThat(bishop.moveIsIllegal(position(2, 4), emptyBoard), equalTo(true));
	}

	@Test
	public void legalMoves() {
		final Bishop bishop = new Bishop(BLACK, new Position(1, 1));

		assertThat(bishop.moveIsIllegal(position(2, 2), emptyBoard), equalTo(false));
		assertThat(bishop.moveIsIllegal(position(0, 2), emptyBoard), equalTo(false));
		assertThat(bishop.moveIsIllegal(position(4, -2), emptyBoard), equalTo(false));
		assertThat(bishop.moveIsIllegal(position(-1, -1), emptyBoard), equalTo(false));
	}

	private Position position(final int column, final int row) {
		return new Position(column, row);
	}

}
