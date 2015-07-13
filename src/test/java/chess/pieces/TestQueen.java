package chess.pieces;

import static chess.pieces.Colour.BLACK;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;

import chess.Board;
import chess.StandardBoard;

public class TestQueen {

	private final Board emptyBoard = new StandardBoard();

	@Test
	public void doesntMoveIfIllegal() {
		final Queen queen = new Queen(BLACK, new Position(1, 1));

		assertThat(queen.moveIsIllegal(position(2, 3), emptyBoard), equalTo(true));
		assertThat(queen.moveIsIllegal(position(-1, 2), emptyBoard), equalTo(true));
		assertThat(queen.moveIsIllegal(position(5, 0), emptyBoard), equalTo(true));
		assertThat(queen.moveIsIllegal(position(3, 2), emptyBoard), equalTo(true));
	}

	@Test
	public void movesIfLegal() {
		final Queen queen = new Queen(BLACK, new Position(1, 1));

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

}
