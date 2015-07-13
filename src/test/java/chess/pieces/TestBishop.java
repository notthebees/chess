package chess.pieces;

import static chess.pieces.Colour.BLACK;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;

public class TestBishop {

	@Test
	public void doesntMoveIfIllegal() {
		final Bishop bishop = new Bishop(BLACK, new Position(1, 1));

		assertThat(bishop.moveTo(position(2, 3)), equalTo(bishop));
		assertThat(bishop.moveTo(position(1, 2)), equalTo(bishop));
		assertThat(bishop.moveTo(position(5, 1)), equalTo(bishop));
		assertThat(bishop.moveTo(position(2, 4)), equalTo(bishop));
	}

	@Test
	public void movesIfLegal() {
		final Bishop bishop = new Bishop(BLACK, new Position(1, 1));

		assertThat(bishop.moveTo(position(2, 2)), equalTo(new Bishop(BLACK, position(2, 2))));
		assertThat(bishop.moveTo(position(0, 2)), equalTo(new Bishop(BLACK, position(0, 2))));
		assertThat(bishop.moveTo(position(4, -2)), equalTo(new Bishop(BLACK, position(4, -2))));
		assertThat(bishop.moveTo(position(-1, -1)), equalTo(new Bishop(BLACK, position(-1, -1))));
	}

	private Position position(final int column, final int row) {
		return new Position(column, row);
	}

}
