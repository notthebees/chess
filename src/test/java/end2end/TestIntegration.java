package end2end;

import static chess.pieces.Colour.BLACK;
import static chess.pieces.Colour.WHITE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;

import chess.Board;
import chess.ChessGame;
import chess.Game;
import chess.StandardBoardBuilder;
import chess.pieces.King;
import chess.pieces.Position;
import chess.pieces.move.Move;
import chess.pieces.move.SimpleMove;

public class TestIntegration {

	@Test
	public void gameWithSomeMovesPlayedOutOfTurn() {
		final Game game = new ChessGame(
				board()
				.withPieces(new King(WHITE, at(5, 1)), new King(BLACK, at(4, 8)))
				.build());

		game.play(move(from(4, 8), to(5, 8)));
		game.play(move(from(5, 1), to(6, 2)));
		game.play(move(from(6, 2), to(6, 1)));
		game.play(move(from(4, 8), to(4, 7)));

		final Board finalBoard = board()
				.withPieces(new King(WHITE, at(6, 2)), new King(BLACK, at(4, 7)))
				.build();

		assertThat(game.currentBoard(), equalTo(finalBoard));
	}

	@Test
	public void twoPlayersTakeTurnsMovingKings() {
		final Game game = new ChessGame(
				board()
				.withPieces(new King(WHITE, at(5, 1)), new King(BLACK, at(4, 8)))
				.build());

		game.play(move(from(5, 1), to(6, 2)));
		game.play(move(from(4, 8), to(5, 8)));

		final Board finalBoard = board()
				.withPieces(new King(WHITE, at(6, 2)), new King(BLACK, at(5, 8)))
				.build();

		assertThat(game.currentBoard(), equalTo(finalBoard));
	}

	private Move move(final Position from, final Position to) {
		return new SimpleMove(from, to);
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

	private StandardBoardBuilder board() {
		return new StandardBoardBuilder();
	}

}
