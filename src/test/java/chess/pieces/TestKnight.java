package chess.pieces;

import static chess.pieces.Colour.BLACK;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;

import chess.Board;
import chess.StandardBoardBuilder;

public class TestKnight {

	private final Board emptyBoard = board().build();

	@Test
	public void moveIllegalIfDestinationOccupiedByFriendlyPiece() {
		final Knight knight = new Knight(BLACK, position(1, 1));
		final Board board = board().withPieces(knight, new Pawn(BLACK, position(2,  3))).build();
		assertThat(knight.moveIsIllegal(position(2, 3), board), equalTo(true));
	}

	@Test
	public void doesntMoveIfIllegal() {
		final Knight knight = new Knight(BLACK, position(1, 1));

		assertThat(knight.moveIsIllegal(position(2, 2), emptyBoard), equalTo(true));
		assertThat(knight.moveIsIllegal(position(1, 2), emptyBoard), equalTo(true));
		assertThat(knight.moveIsIllegal(position(4, 3), emptyBoard), equalTo(true));
		assertThat(knight.moveIsIllegal(position(5, 3), emptyBoard), equalTo(true));
	}

	@Test
	public void movesIfLegal() {
		final Knight knight = new Knight(BLACK, position(1, 1));

		assertThat(knight.moveIsIllegal(position(2, 3), emptyBoard), equalTo(false));
		assertThat(knight.moveIsIllegal(position(0, 3), emptyBoard), equalTo(false));
		assertThat(knight.moveIsIllegal(position(-1, 0), emptyBoard), equalTo(false));
		assertThat(knight.moveIsIllegal(position(2, -1), emptyBoard), equalTo(false));
	}

	private Position position(final int column, final int row) {
		return new Position(column, row);
	}

	private StandardBoardBuilder board() {
		return new StandardBoardBuilder();
	}

}
