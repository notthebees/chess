package chess.pieces;

import static chess.pieces.Colour.BLACK;
import static chess.pieces.Colour.WHITE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;

import chess.Board;
import chess.StandardBoard;

public class TestRook {

	private final Board emptyBoard = new StandardBoard();

	@Test
	public void moveIllegalIfPathObstructedByAnyPiece() {
		final Rook rook = new Rook(BLACK, new Position(4, 4));
		final Board board = new StandardBoard(
				rook,
				new Pawn(BLACK, position(5,  4)),
				new Pawn(WHITE, position(4,  2)));

		assertThat(rook.moveIsIllegal(position(7, 4), board), equalTo(true));
		assertThat(rook.moveIsIllegal(position(4, 1), board), equalTo(true));
	}

	@Test
	public void moveIllegalIfDestinationOccupiedByFriendlyPiece() {
		final Rook rook = new Rook(BLACK, new Position(1, 1));
		final Board board = new StandardBoard(rook, new Pawn(BLACK, position(3,  1)));
		assertThat(rook.moveIsIllegal(position(3, 1), board), equalTo(true));
	}

	@Test
	public void doesntMoveIfIllegal() {
		final Rook rook = new Rook(BLACK, new Position(1, 1));

		assertThat(rook.moveIsIllegal(position(2, 3), emptyBoard), equalTo(true));
		assertThat(rook.moveIsIllegal(position(-1, 2), emptyBoard), equalTo(true));
		assertThat(rook.moveIsIllegal(position(5, 0), emptyBoard), equalTo(true));
		assertThat(rook.moveIsIllegal(position(2, 2), emptyBoard), equalTo(true));
	}

	@Test
	public void movesIfLegal() {
		final Rook rook = new Rook(BLACK, new Position(1, 1));

		assertThat(rook.moveIsIllegal(position(1, 2), emptyBoard), equalTo(false));
		assertThat(rook.moveIsIllegal(position(6, 1), emptyBoard), equalTo(false));
		assertThat(rook.moveIsIllegal(position(1, 0), emptyBoard), equalTo(false));
		assertThat(rook.moveIsIllegal(position(1, -2), emptyBoard), equalTo(false));
	}

	private Position position(final int column, final int row) {
		return new Position(column, row);
	}

}
