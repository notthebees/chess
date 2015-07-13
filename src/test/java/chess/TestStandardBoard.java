package chess;

import static chess.pieces.Colour.BLACK;
import static chess.pieces.Colour.WHITE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;

import chess.pieces.King;
import chess.pieces.Pawn;
import chess.pieces.Position;
import chess.pieces.Queen;

public class TestStandardBoard {

	@Test
	public void checksIfPositionIsOccupied() {
		final Board board = new StandardBoard(
				new Pawn(BLACK, at(1, 2)),
				new Pawn(WHITE, at(2, 3)));

		assertThat(board.isOccupiedBy(BLACK, at(1, 2)), equalTo(true));
		assertThat(board.isOccupiedAt(at(2, 3)), equalTo(true));
	}

	@Test
	public void cannotMoveIfPositionOccupiedByPieceOfSameColour() {
		final StandardBoard board = new StandardBoard(
				new Queen(BLACK, at(1, 1)),
				new King(BLACK, at(4, 4)));

		assertThat(board.play(new Move(from(1, 1), to(4, 4))), equalTo(board));
	}

	@Test
	public void cannotMovePieceOffBoard() {
		final StandardBoard board = new StandardBoard(new Queen(BLACK, new Position(1, 1)));

		assertThat(board.play(new Move(from(1, 1), to(1, 0))), equalTo(board));
		assertThat(board.play(new Move(from(1, 1), to(9, 9))), equalTo(board));
	}

	@Test
	public void returnsNewBoardWithMovedPiece() {
		final StandardBoard board = new StandardBoard(new King(BLACK, at(1, 1)));

		final Move move = new Move(from(1, 1), to(1, 2));

		final Board finalBoard = new StandardBoard(new King(BLACK, at(1, 2)));
		assertThat(board.play(move), equalTo(finalBoard));
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
