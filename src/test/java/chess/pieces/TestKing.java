package chess.pieces;

import static chess.pieces.Colour.BLACK;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;

public class TestKing {

	@Test
	public void testIllegalMoves() {
		final King king = new King(BLACK, new Position(1, 1));

		assertThat(king.canMoveTo(position(1, 3)), equalTo(false));
		assertThat(king.canMoveTo(position(-1, 2)), equalTo(false));
		assertThat(king.canMoveTo(position(5, 0)), equalTo(false));
		assertThat(king.canMoveTo(position(1, 1)), equalTo(false));
	}

	@Test
	public void testLegalMoves() {
		final King king = new King(BLACK, new Position(1, 1));

		assertThat(king.canMoveTo(position(1, 2)), equalTo(true));
		assertThat(king.canMoveTo(position(2, 2)), equalTo(true));
		assertThat(king.canMoveTo(position(1, 0)), equalTo(true));
		assertThat(king.canMoveTo(position(2, 1)), equalTo(true));
	}

	private Position position(final int column, final int row) {
		return new Position(column, row);
	}

}
