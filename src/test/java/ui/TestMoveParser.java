package ui;

import static chess.pieces.Colour.BLACK;
import static chess.pieces.Colour.WHITE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;

import chess.pieces.Colour;
import chess.pieces.Knight;
import chess.pieces.Piece;
import chess.pieces.Position;
import chess.pieces.Queen;
import chess.pieces.move.KingSideCastle;
import chess.pieces.move.Move;
import chess.pieces.move.QueenSideCastle;
import chess.pieces.move.ReplacePawn;
import chess.pieces.move.SimpleMove;

public class TestMoveParser {

	@Test
	public void parsesPawnReplacementMove() {
		final MoveParser parser = new MoveParser();
		assertThat(parser.parse("a8-Q"), equalTo(replacePawn(at(1, 8), new Queen(WHITE, at(1, 8)))));
		assertThat(parser.parse("d1-k"), equalTo(replacePawn(at(4, 1), new Knight(BLACK, at(4, 1)))));
	}

	private Move replacePawn(final Position position, final Piece piece) {
		return new ReplacePawn(position, piece);
	}

	@Test
	public void pawnReplacementMoveIsValid() {
		final MoveParser parser = new MoveParser();
		assertThat(parser.isValid("a8-Q"), equalTo(true));
		assertThat(parser.isValid("d1-k"), equalTo(true));
	}

	@Test
	public void someInvalidMoves() {
		final MoveParser parser = new MoveParser();
		assertThat(parser.isValid("e2e4"), equalTo(false));
		assertThat(parser.isValid("0-00"), equalTo(false));
		assertThat(parser.isValid("11-a2"), equalTo(false));
		assertThat(parser.isValid("a0-a2"), equalTo(false));
		assertThat(parser.isValid("a1-a"), equalTo(false));
		assertThat(parser.isValid("a1-a2-a3"), equalTo(false));
		assertThat(parser.isValid("a6-Q"), equalTo(false));
		assertThat(parser.isValid("a8-K"), equalTo(false));
	}

	@Test
	public void parsesCastlingMove() {
		final MoveParser parser = new MoveParser();
		assertThat(parser.parse("1-0"), equalTo(kingSideCastle(WHITE)));
		assertThat(parser.parse("8-0"), equalTo(kingSideCastle(BLACK)));
		assertThat(parser.parse("1-00"), equalTo(queenSideCastle(WHITE)));
		assertThat(parser.parse("8-00"), equalTo(queenSideCastle(BLACK)));
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
		assertThat(parser.isValid("1-0"), equalTo(true));
		assertThat(parser.isValid("8-00"), equalTo(true));
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
	public void simpleMoveIsValid() {
		final MoveParser parser = new MoveParser();
		assertThat(parser.isValid("e2-e4"), equalTo(true));
	}

	private Position at(final int column, final int row) {
		return new Position(column, row);
	}

	private Position to(final int column, final int row) {
		return new Position(column, row);
	}

	private Position from(final int column, final int row) {
		return new Position(column, row);
	}

}
