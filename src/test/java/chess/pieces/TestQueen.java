package chess.pieces;

import static chess.pieces.Colour.BLACK;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;

public class TestQueen {

	@Test
	public void doesntMoveIfIllegal() {
		final Queen queen = new Queen(BLACK, new Position(1, 1));

		assertThat(queen.moveIsIllegal(position(2, 3)), equalTo(true));
		assertThat(queen.moveIsIllegal(position(-1, 2)), equalTo(true));
		assertThat(queen.moveIsIllegal(position(5, 0)), equalTo(true));
		assertThat(queen.moveIsIllegal(position(3, 2)), equalTo(true));
	}

	@Test
	public void movesIfLegal() {
		final Queen queen = new Queen(BLACK, new Position(1, 1));

		assertThat(queen.moveIsIllegal(position(1, 2)), equalTo(false));
		assertThat(queen.moveIsIllegal(position(6, 1)), equalTo(false));
		assertThat(queen.moveIsIllegal(position(1, 0)), equalTo(false));
		assertThat(queen.moveIsIllegal(position(1, -2)), equalTo(false));
		assertThat(queen.moveIsIllegal(position(2, 2)), equalTo(false));
		assertThat(queen.moveIsIllegal(position(0, 2)), equalTo(false));
		assertThat(queen.moveIsIllegal(position(4, -2)), equalTo(false));
		assertThat(queen.moveIsIllegal(position(-1, -1)), equalTo(false));
	}

	private Position position(final int column, final int row) {
		return new Position(column, row);
	}

}
