package chess.pieces;

import static chess.pieces.Colour.BLACK;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;

public class TestKnight {

	@Test
	public void doesntMoveIfIllegal() {
		final Knight knight = new Knight(BLACK, new Position(1, 1));

		assertThat(knight.moveTo(position(2, 2)), equalTo(knight));
		assertThat(knight.moveTo(position(1, 2)), equalTo(knight));
		assertThat(knight.moveTo(position(4, 3)), equalTo(knight));
		assertThat(knight.moveTo(position(5, 3)), equalTo(knight));
	}

	@Test
	public void movesIfLegal() {
		final Knight knight = new Knight(BLACK, new Position(1, 1));

		assertThat(knight.moveTo(position(2, 3)), equalTo(new Knight(BLACK, position(2, 3))));
		assertThat(knight.moveTo(position(0, 3)), equalTo(new Knight(BLACK, position(0, 3))));
		assertThat(knight.moveTo(position(-1, 0)), equalTo(new Knight(BLACK, position(-1, 0))));
		assertThat(knight.moveTo(position(2, -1)), equalTo(new Knight(BLACK, position(2, -1))));
	}

	private Position position(final int column, final int row) {
		return new Position(column, row);
	}

}
