package chess.pieces;

import static chess.pieces.Colour.BLACK;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;

import chess.Board;
import chess.StandardBoard;

public class TestKing {

	private final Board emptyBoard = new StandardBoard();

	@Test
	public void moveIllegalIfDestinationOccupiedByFriendlyPiece() {
		final King king = new King(BLACK, new Position(1, 1));
		final Board board = new StandardBoard(king, new Pawn(BLACK, position(1,  2)));
		assertThat(king.moveIsIllegal(position(1, 2), board), equalTo(true));
	}

	@Test
	public void doesntMoveIfIllegal() {
		final King king = new King(BLACK, new Position(1, 1));

		assertThat(king.moveIsIllegal(position(1, 3), emptyBoard), equalTo(true));
		assertThat(king.moveIsIllegal(position(-1, 2), emptyBoard), equalTo(true));
		assertThat(king.moveIsIllegal(position(5, 0), emptyBoard), equalTo(true));
		assertThat(king.moveIsIllegal(position(1, 3), emptyBoard), equalTo(true));
	}

	@Test
	public void movesIfLegal() {
		final King king = new King(BLACK, new Position(1, 1));

		assertThat(king.moveIsIllegal(position(1, 2), emptyBoard), equalTo(false));
		assertThat(king.moveIsIllegal(position(2, 2), emptyBoard), equalTo(false));
		assertThat(king.moveIsIllegal(position(1, 0), emptyBoard), equalTo(false));
		assertThat(king.moveIsIllegal(position(2, 1), emptyBoard), equalTo(false));
	}

	private Position position(final int column, final int row) {
		return new Position(column, row);
	}

}
