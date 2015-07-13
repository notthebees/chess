package chess.pieces;

import static chess.pieces.Colour.BLACK;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;

public class TestRook {

	@Test
	public void doesntMoveIfIllegal() {
		final Rook rook = new Rook(BLACK, new Position(1, 1));

		assertThat(rook.moveTo(position(2, 3)), equalTo(rook));
		assertThat(rook.moveTo(position(-1, 2)), equalTo(rook));
		assertThat(rook.moveTo(position(5, 0)), equalTo(rook));
		assertThat(rook.moveTo(position(2, 2)), equalTo(rook));
	}

	@Test
	public void movesIfLegal() {
		final Rook rook = new Rook(BLACK, new Position(1, 1));

		assertThat(rook.moveTo(position(1, 2)), equalTo(new Rook(BLACK, position(1, 2))));
		assertThat(rook.moveTo(position(6, 1)), equalTo(new Rook(BLACK, position(6, 1))));
		assertThat(rook.moveTo(position(1, 0)), equalTo(new Rook(BLACK, position(1, 0))));
		assertThat(rook.moveTo(position(1, -2)), equalTo(new Rook(BLACK, position(1, -2))));
	}

	private Position position(final int column, final int row) {
		return new Position(column, row);
	}

}
