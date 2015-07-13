package chess.pieces;

import static chess.pieces.Colour.BLACK;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;

import chess.Board;
import chess.StandardBoard;

public class TestPawn {

	private final Board emptyBoard = new StandardBoard();

	@Test
	public void basicMoveLegality() {
		final Pawn pawn = new Pawn(BLACK, new Position(2, 1));

		assertThat(pawn.moveIsIllegal(position(2, 2), emptyBoard), equalTo(false));
	}

	private Position position(final int column, final int row) {
		return new Position(column, row);
	}

}
