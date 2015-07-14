package chess.pieces;

import static chess.pieces.Colour.BLACK;
import static chess.pieces.Colour.WHITE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;

import chess.Board;
import chess.StandardBoardBuilder;

public class TestKing {

	private final Board emptyBoard = board().build();

	@Test
	public void recordsIfItHasMoved() {
		King king = new King(WHITE, WHITE.kingPosition());
		assertThat(king.hasMoved(), equalTo(false));
		king = king.moveTo(WHITE.queenPosition());
		king = king.moveTo(WHITE.kingPosition());
		assertThat(king.hasMoved(), equalTo(true));
	}

	@Test
	public void moveIllegalIfDestinationOccupiedByFriendlyPiece() {
		final King king = new King(BLACK, new Position(1, 1));
		final Board board = board().withPieces(king, new Pawn(BLACK, position(1,  2))).build();
		assertThat(king.moveIsIllegal(position(1, 2), board), equalTo(true));
	}

	@Test
	public void testIllegalMoves() {
		final King king = new King(BLACK, new Position(1, 1));

		assertThat(king.moveIsIllegal(position(1, 3), emptyBoard), equalTo(true));
		assertThat(king.moveIsIllegal(position(-1, 2), emptyBoard), equalTo(true));
		assertThat(king.moveIsIllegal(position(5, 0), emptyBoard), equalTo(true));
		assertThat(king.moveIsIllegal(position(1, 3), emptyBoard), equalTo(true));
	}

	@Test
	public void canMoveOneSpaceInAnyDirection() {
		final King king = new King(BLACK, new Position(1, 1));

		assertThat(king.moveIsIllegal(position(1, 2), emptyBoard), equalTo(false));
		assertThat(king.moveIsIllegal(position(2, 2), emptyBoard), equalTo(false));
		assertThat(king.moveIsIllegal(position(1, 0), emptyBoard), equalTo(false));
		assertThat(king.moveIsIllegal(position(2, 1), emptyBoard), equalTo(false));
	}

	private Position position(final int column, final int row) {
		return new Position(column, row);
	}

	private StandardBoardBuilder board() {
		return new StandardBoardBuilder();
	}

}
