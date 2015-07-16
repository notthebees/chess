package ui;

import static chess.pieces.Colour.BLACK;
import static chess.pieces.Colour.WHITE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;

import chess.pieces.Colour;
import chess.pieces.Position;
import chess.pieces.move.KingSideCastle;
import chess.pieces.move.Move;
import chess.pieces.move.QueenSideCastle;
import chess.pieces.move.SimpleMove;

public class TestMoveParser {

	@Test
	public void parsesCastlingMove() {
		final MoveParser parser = new MoveParser();
		assertThat(parser.parse("e1-0"), equalTo(kingSideCastle(WHITE)));
		assertThat(parser.parse("e8-0"), equalTo(kingSideCastle(BLACK)));
		assertThat(parser.parse("e1-00"), equalTo(queenSideCastle(WHITE)));
		assertThat(parser.parse("e8-00"), equalTo(queenSideCastle(BLACK)));
	}

	private Move queenSideCastle(final Colour colour) {
		return new QueenSideCastle(colour);
	}

	private Move kingSideCastle(final Colour colour) {
		return new KingSideCastle(colour);
	}

	@Test
	public void castlingMoveIsValid() {
		final MoveParser parser = new MoveParser();
		assertThat(parser.isValid("e1-0"), equalTo(true));
		assertThat(parser.isValid("e8-00"), equalTo(true));
	}

	@Test
	public void parsesSimpleMove() {
		final MoveParser parser = new MoveParser();
		assertThat(parser.parse("e2-e4"), equalTo(simpleMove(from(5, 2), to(5, 4))));
	}

	private Move simpleMove(final Position from, final Position to) {
		return new SimpleMove(from, to);
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
