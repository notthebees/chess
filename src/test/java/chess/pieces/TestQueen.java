package chess.pieces;

import static chess.pieces.Colour.BLACK;
import static chess.pieces.Colour.WHITE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;

import chess.Board;
import chess.StandardBoardBuilder;

public class TestQueen {

	private final Board emptyBoard = board().build();

	@Test
	public void moveIllegalIfPathObstructedByAnyPiece() {
		final Queen queen = new Queen(BLACK, new Position(4, 4));
		final Board board = board().withPieces(
				queen,
				new Pawn(BLACK, position(5,  4)),
				new Pawn(BLACK, position(5,  5)),
				new Pawn(WHITE, position(4,  2)))
				.build();

		assertThat(queen.moveIsIllegal(position(7, 4), board), equalTo(true));
		assertThat(queen.moveIsIllegal(position(7, 7), board), equalTo(true));
		assertThat(queen.moveIsIllegal(position(4, 1), board), equalTo(true));
	}

	@Test
	public void moveIllegalIfDestinationOccupiedByFriendlyPiece() {
		final Queen queen = new Queen(BLACK, new Position(1, 1));
		final Board board = board().withPieces(queen, new Pawn(BLACK, position(3,  1))).build();
		assertThat(queen.moveIsIllegal(position(3, 1), board), equalTo(true));
	}

	@Test
	public void doesntMoveIfIllegal() {
		final Queen queen = new Queen(BLACK, position(1, 1));

		assertThat(queen.moveIsIllegal(position(2, 3), emptyBoard), equalTo(true));
		assertThat(queen.moveIsIllegal(position(-1, 2), emptyBoard), equalTo(true));
		assertThat(queen.moveIsIllegal(position(5, 0), emptyBoard), equalTo(true));
		assertThat(queen.moveIsIllegal(position(3, 2), emptyBoard), equalTo(true));
	}

	@Test
	public void movesIfLegal() {
		final Queen queen = new Queen(BLACK, position(1, 1));

		assertThat(queen.moveIsIllegal(position(1, 2), emptyBoard), equalTo(false));
		assertThat(queen.moveIsIllegal(position(6, 1), emptyBoard), equalTo(false));
		assertThat(queen.moveIsIllegal(position(1, 0), emptyBoard), equalTo(false));
		assertThat(queen.moveIsIllegal(position(1, -2), emptyBoard), equalTo(false));
		assertThat(queen.moveIsIllegal(position(2, 2), emptyBoard), equalTo(false));
		assertThat(queen.moveIsIllegal(position(0, 2), emptyBoard), equalTo(false));
		assertThat(queen.moveIsIllegal(position(4, -2), emptyBoard), equalTo(false));
		assertThat(queen.moveIsIllegal(position(-1, -1), emptyBoard), equalTo(false));
	}

	private Position position(final int column, final int row) {
		return new Position(column, row);
	}

	private StandardBoardBuilder board() {
		return new StandardBoardBuilder();
	}

}
