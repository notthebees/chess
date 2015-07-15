package chess.pieces.move;

import static chess.pieces.Colour.WHITE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;

import chess.Board;
import chess.StandardBoardBuilder;
import chess.pieces.Bishop;
import chess.pieces.King;
import chess.pieces.Pawn;
import chess.pieces.Position;
import chess.pieces.Queen;

public class TestReplacePawn {

	@Test
	public void moveIllegalIfNoPieceAtPosition() {
		final ReplacePawn replacePawn = new ReplacePawn(at(1, 8), new Queen(WHITE, at(1, 8)));
		final Board board = board().withPiece(new Pawn(WHITE, at(1, 7))).build();
		assertThat(replacePawn.isIllegal(board), equalTo(true));
	}

	@Test
	public void cannotReplaceIfPawnNotAtEndRow() {
		final ReplacePawn replacePawn = new ReplacePawn(at(1, 7), new Queen(WHITE, at(1, 7)));
		final Board board = board().withPiece(new Pawn(WHITE, at(1, 7))).build();
		assertThat(replacePawn.isIllegal(board), equalTo(true));
	}

	@Test
	public void cannotReplaceWithInvalidReplacementPiece() {
		final Board board = board().withPiece(new Pawn(WHITE, at(1, 8))).build();
		final ReplacePawn replaceWithKing = new ReplacePawn(at(1, 8), new King(WHITE, at(1, 8)));
		final ReplacePawn replaceWithPawn = new ReplacePawn(at(1, 8), new King(WHITE, at(1, 8)));
		assertThat(replaceWithKing.isIllegal(board), equalTo(true));
		assertThat(replaceWithPawn.isIllegal(board), equalTo(true));
	}

	@Test
	public void cannotReplaceIfPieceIsNotAPawn() {
		final ReplacePawn replacePawn = new ReplacePawn(at(1, 8), new Queen(WHITE, at(1, 8)));
		final Board board = board().withPiece(new Bishop(WHITE, at(1, 8))).build();
		assertThat(replacePawn.isIllegal(board), equalTo(true));
	}

	@Test
	public void legalIfPawnIsAtEndRowAndReplacementPieceIsValid() {
		final ReplacePawn replacePawn = new ReplacePawn(at(1, 8), new Queen(WHITE, at(1, 8)));
		final Board board = board().withPiece(new Pawn(WHITE, at(1, 8))).build();
		assertThat(replacePawn.isIllegal(board), equalTo(false));
	}

	private Position at(final int column, final int row) {
		return new Position(column, row);
	}

	private StandardBoardBuilder board() {
		return new StandardBoardBuilder();
	}

}
