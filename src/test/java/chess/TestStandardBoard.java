package chess;

import static chess.pieces.Colour.BLACK;
import static chess.pieces.Colour.WHITE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;

import chess.pieces.Bishop;
import chess.pieces.King;
import chess.pieces.Pawn;
import chess.pieces.Position;
import chess.pieces.move.Move;
import chess.pieces.move.SimpleMove;

public class TestStandardBoard {

	@Test
	public void listsWhichPositionsAPieceIsAttacking() {
		final Bishop bishop = new Bishop(WHITE, at(3, 4));
		final Board board = new StandardBoard(
				bishop,
				new Pawn(WHITE, at(5, 2)),
				new Pawn(BLACK, at(6, 7)));

		assertThat(board.spacesAttackedBy(bishop),
				containsInAnyOrder(position(1, 2), position(1, 6), position(2, 3), position(2, 5),
						position(4, 3), position(4, 5), position(5, 6), position(6, 7)));
	}

	@Test
	public void checksIfPositionIsOccupied() {
		final StandardBoard board = new StandardBoard(
				new Pawn(BLACK, at(1, 2)),
				new Pawn(WHITE, at(2, 3)));

		assertThat(board.isOccupiedBy(BLACK, at(1, 2)), equalTo(true));
		assertThat(board.isOccupiedAt(at(2, 3)), equalTo(true));
	}

	@Test
	public void returnsNewBoardWithMovedPiece() {
		final StandardBoard board = new StandardBoard(new King(BLACK, at(1, 1)));

		final Move move = new SimpleMove(from(1, 1), to(1, 2));

		final Board finalBoard = new StandardBoard(new King(BLACK, at(1, 2)));
		assertThat(board.play(move), equalTo(finalBoard));
	}

	private Position position(final int column, final int row) {
		return new Position(column, row);
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
