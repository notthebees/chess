package chess.pieces.move;

import static chess.pieces.Colour.BLACK;
import static chess.pieces.Colour.WHITE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;

import chess.StandardBoard;
import chess.pieces.King;
import chess.pieces.Position;
import chess.pieces.Queen;

public class TestSimpleMove {

	@Test
	public void cannotMoveIfOwnKingWouldBeInCheck() {
		final SimpleMove move = new SimpleMove(from(6, 1), to(6, 2));

		final StandardBoard board = new StandardBoard(
				new King(BLACK, at(5, 1)),
				new Queen(BLACK, at(6, 1)),
				new Queen(WHITE, at(8, 1)));

		assertThat(move.isIllegal(board), equalTo(true));
	}

	@Test
	public void cannotMoveIfPositionOccupiedByPieceOfSameColour() {
		final SimpleMove move = new SimpleMove(from(1, 1), to(4, 4));

		final StandardBoard board = new StandardBoard(
				new Queen(BLACK, at(1, 1)),
				new King(BLACK, at(4, 4)));

		assertThat(move.isIllegal(board), equalTo(true));
	}

	@Test
	public void cannotMovePieceOffBoard() {
		final StandardBoard board = new StandardBoard(new Queen(BLACK, new Position(1, 1)));

		final SimpleMove aMove = new SimpleMove(from(1, 1), to(1, 0));
		final SimpleMove anotherMove = new SimpleMove(from(1, 1), to(9, 9));

		assertThat(aMove.isIllegal(board), equalTo(true));
		assertThat(anotherMove.isIllegal(board), equalTo(true));
	}

	private Position at(final int column, final int row) {
		return new Position(column, row);
	}

	private Position from(final int column, final int row) {
		return new Position(column, row);
	}

	private Position to(final int column, final int row) {
		return new Position(column, row);
	}

}
