package chess.pieces;

import static chess.pieces.Colour.BLACK;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;

public class TestKing {

	@Test
	public void doesntMoveIfIllegal() {
		final King king = new King(BLACK, new Position(1, 1));

		assertThat(king.moveTo(position(1, 3)), equalTo(king));
		assertThat(king.moveTo(position(-1, 2)), equalTo(king));
		assertThat(king.moveTo(position(5, 0)), equalTo(king));
		assertThat(king.moveTo(position(1, 1)), equalTo(king));
	}

	@Test
	public void movesIfLegal() {
		final King king = new King(BLACK, new Position(1, 1));

		assertThat(king.moveTo(position(1, 2)), equalTo(new King(BLACK, position(1, 2))));
		assertThat(king.moveTo(position(2, 2)), equalTo(new King(BLACK, position(2, 2))));
		assertThat(king.moveTo(position(1, 0)), equalTo(new King(BLACK, position(1, 0))));
		assertThat(king.moveTo(position(2, 1)), equalTo(new King(BLACK, position(2, 1))));
	}

	private Position position(final int column, final int row) {
		return new Position(column, row);
	}

}
