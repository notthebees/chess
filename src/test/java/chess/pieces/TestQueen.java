package chess.pieces;

import static chess.pieces.Colour.BLACK;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;

public class TestQueen {

	@Test
	public void doesntMoveIfIllegal() {
		final Queen queen = new Queen(BLACK, new Position(1, 1));

		assertThat(queen.moveTo(position(2, 3)), equalTo(queen));
		assertThat(queen.moveTo(position(-1, 2)), equalTo(queen));
		assertThat(queen.moveTo(position(5, 0)), equalTo(queen));
		assertThat(queen.moveTo(position(3, 2)), equalTo(queen));
	}

	@Test
	public void movesIfLegal() {
		final Queen queen = new Queen(BLACK, new Position(1, 1));

		assertThat(queen.moveTo(position(1, 2)), equalTo(new Queen(BLACK, position(1, 2))));
		assertThat(queen.moveTo(position(6, 1)), equalTo(new Queen(BLACK, position(6, 1))));
		assertThat(queen.moveTo(position(1, 0)), equalTo(new Queen(BLACK, position(1, 0))));
		assertThat(queen.moveTo(position(1, -2)), equalTo(new Queen(BLACK, position(1, -2))));
		assertThat(queen.moveTo(position(2, 2)), equalTo(new Queen(BLACK, position(2, 2))));
		assertThat(queen.moveTo(position(0, 2)), equalTo(new Queen(BLACK, position(0, 2))));
		assertThat(queen.moveTo(position(4, -2)), equalTo(new Queen(BLACK, position(4, -2))));
		assertThat(queen.moveTo(position(-1, -1)), equalTo(new Queen(BLACK, position(-1, -1))));
	}

	private Position position(final int column, final int row) {
		return new Position(column, row);
	}

}
