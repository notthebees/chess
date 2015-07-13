package chess.pieces;

import static chess.pieces.Colour.BLACK;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;

public class TestKnight {

	@Test
	public void doesntMoveIfIllegal() {
		final Knight knight = new Knight(BLACK, new Position(1, 1));

		assertThat(knight.moveIsIllegal(position(2, 2)), equalTo(true));
		assertThat(knight.moveIsIllegal(position(1, 2)), equalTo(true));
		assertThat(knight.moveIsIllegal(position(4, 3)), equalTo(true));
		assertThat(knight.moveIsIllegal(position(5, 3)), equalTo(true));
	}

	@Test
	public void movesIfLegal() {
		final Knight knight = new Knight(BLACK, new Position(1, 1));

		assertThat(knight.moveIsIllegal(position(2, 3)), equalTo(false));
		assertThat(knight.moveIsIllegal(position(0, 3)), equalTo(false));
		assertThat(knight.moveIsIllegal(position(-1, 0)), equalTo(false));
		assertThat(knight.moveIsIllegal(position(2, -1)), equalTo(false));
	}

	private Position position(final int column, final int row) {
		return new Position(column, row);
	}

}
