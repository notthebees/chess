package chess.pieces;

import static chess.pieces.Colour.BLACK;
import static chess.pieces.Colour.WHITE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;

import chess.Board;
import chess.StandardBoardBuilder;

public class TestRook {

	private final Board emptyBoard = board().build();

	@Test
	public void recordsIfItHasMoved() {
		Rook rook = new Rook(WHITE, position(8, 1));
		assertThat(rook.hasMoved(), equalTo(false));
		rook = rook.moveTo(position(7, 1));
		rook = rook.moveTo(position(8, 1));
		assertThat(rook.hasMoved(), equalTo(true));
	}

	@Test
	public void moveIllegalIfPathObstructedByAnyPiece() {
		final Rook rook = new Rook(BLACK, position(4, 4));
		final Board board = board().withPieces(
				rook,
				new Pawn(BLACK, position(5,  4)),
				new Pawn(WHITE, position(4,  2)))
				.build();

		assertThat(rook.moveIsIllegal(position(7, 4), board), equalTo(true));
		assertThat(rook.moveIsIllegal(position(4, 1), board), equalTo(true));
	}

	@Test
	public void moveIllegalIfDestinationOccupiedByFriendlyPiece() {
		final Rook rook = new Rook(BLACK, position(1, 1));
		final Board board = board().withPieces(rook, new Pawn(BLACK, position(3,  1))).build();
		assertThat(rook.moveIsIllegal(position(3, 1), board), equalTo(true));
	}

	@Test
	public void doesntMoveIfIllegal() {
		final Rook rook = new Rook(BLACK, position(1, 1));

		assertThat(rook.moveIsIllegal(position(2, 3), emptyBoard), equalTo(true));
		assertThat(rook.moveIsIllegal(position(-1, 2), emptyBoard), equalTo(true));
		assertThat(rook.moveIsIllegal(position(5, 0), emptyBoard), equalTo(true));
		assertThat(rook.moveIsIllegal(position(2, 2), emptyBoard), equalTo(true));
	}

	@Test
	public void movesIfLegal() {
		final Rook rook = new Rook(BLACK, position(1, 1));

		assertThat(rook.moveIsIllegal(position(1, 2), emptyBoard), equalTo(false));
		assertThat(rook.moveIsIllegal(position(6, 1), emptyBoard), equalTo(false));
		assertThat(rook.moveIsIllegal(position(1, 0), emptyBoard), equalTo(false));
		assertThat(rook.moveIsIllegal(position(1, -2), emptyBoard), equalTo(false));
	}

	private Position position(final int column, final int row) {
		return new Position(column, row);
	}

	private StandardBoardBuilder board() {
		return new StandardBoardBuilder();
	}

}
