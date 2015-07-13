package chess.pieces;

import static chess.pieces.Colour.BLACK;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;

public class TestKing {

	@Test
	public void doesntMoveIfIllegal() {
		final King king = new King(BLACK, new Position(1, 1));

		assertThat(king.moveIsIllegal(position(1, 3)), equalTo(true));
		assertThat(king.moveIsIllegal(position(-1, 2)), equalTo(true));
		assertThat(king.moveIsIllegal(position(5, 0)), equalTo(true));
		assertThat(king.moveIsIllegal(position(1, 3)), equalTo(true));
	}

	@Test
	public void movesIfLegal() {
		final King king = new King(BLACK, new Position(1, 1));

		assertThat(king.moveIsIllegal(position(1, 2)), equalTo(false));
		assertThat(king.moveIsIllegal(position(2, 2)), equalTo(false));
		assertThat(king.moveIsIllegal(position(1, 0)), equalTo(false));
		assertThat(king.moveIsIllegal(position(2, 1)), equalTo(false));
	}

	private Position position(final int column, final int row) {
		return new Position(column, row);
	}

}
