package chess.pieces;

import static chess.pieces.Colour.BLACK;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;

public class TestBishop {

	@Test
	public void doesntMoveIfIllegal() {
		final Bishop bishop = new Bishop(BLACK, new Position(1, 1));

		assertThat(bishop.moveIsIllegal(position(2, 3)), equalTo(true));
		assertThat(bishop.moveIsIllegal(position(1, 2)), equalTo(true));
		assertThat(bishop.moveIsIllegal(position(5, 1)), equalTo(true));
		assertThat(bishop.moveIsIllegal(position(2, 4)), equalTo(true));
	}

	@Test
	public void movesIfLegal() {
		final Bishop bishop = new Bishop(BLACK, new Position(1, 1));

		assertThat(bishop.moveIsIllegal(position(2, 2)), equalTo(false));
		assertThat(bishop.moveIsIllegal(position(0, 2)), equalTo(false));
		assertThat(bishop.moveIsIllegal(position(4, -2)), equalTo(false));
		assertThat(bishop.moveIsIllegal(position(-1, -1)), equalTo(false));
	}

	private Position position(final int column, final int row) {
		return new Position(column, row);
	}

}
