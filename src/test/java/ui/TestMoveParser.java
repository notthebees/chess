package ui;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;

import chess.pieces.Position;
import chess.pieces.move.Move;
import chess.pieces.move.SimpleMove;

public class TestMoveParser {

	@Test
	public void parsesSimpleMove() {
		final MoveParser parser = new MoveParser();
		final Move simpleMove = new SimpleMove(from(5, 2), to(5, 4));
		assertThat(parser.parse("e2-e4"), equalTo(simpleMove));
	}

	@Test
	public void someInvalidMoves() {
		final MoveParser parser = new MoveParser();
		assertThat(parser.isValid("e2e4"), equalTo(false));
	}

	@Test
	public void simpleMoveIsValid() {
		final MoveParser parser = new MoveParser();
		assertThat(parser.isValid("e2-e4"), equalTo(true));
	}

	private Position to(final int column, final int row) {
		return new Position(column, row);
	}

	private Position from(final int column, final int row) {
		return new Position(column, row);
	}

}
