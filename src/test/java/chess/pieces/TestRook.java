package chess.pieces;

import static chess.pieces.Colour.BLACK;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;

public class TestRook {

	@Test
	public void doesntMoveIfIllegal() {
		final Rook rook = new Rook(BLACK, new Position(1, 1));

		assertThat(rook.moveIsIllegal(position(2, 3)), equalTo(true));
		assertThat(rook.moveIsIllegal(position(-1, 2)), equalTo(true));
		assertThat(rook.moveIsIllegal(position(5, 0)), equalTo(true));
		assertThat(rook.moveIsIllegal(position(2, 2)), equalTo(true));
	}

	@Test
	public void movesIfLegal() {
		final Rook rook = new Rook(BLACK, new Position(1, 1));

		assertThat(rook.moveIsIllegal(position(1, 2)), equalTo(false));
		assertThat(rook.moveIsIllegal(position(6, 1)), equalTo(false));
		assertThat(rook.moveIsIllegal(position(1, 0)), equalTo(false));
		assertThat(rook.moveIsIllegal(position(1, -2)), equalTo(false));
	}

	private Position position(final int column, final int row) {
		return new Position(column, row);
	}

}
