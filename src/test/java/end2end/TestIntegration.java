package end2end;

import static chess.pieces.Colour.BLACK;
import static chess.pieces.Colour.WHITE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;

import chess.Board;
import chess.ChessGame;
import chess.Game;
import chess.StandardBoard;
import chess.pieces.King;
import chess.pieces.Piece;
import chess.pieces.Position;
import chess.pieces.move.SimpleMove;

public class TestIntegration {

	@Test
	public void twoPlayersTakeTurnsMovingKings() {
		final Piece whiteKing = new King(WHITE, position(5, 1));
		final Piece blackKing = new King(BLACK, position(4, 8));

		final Board board = new StandardBoard(whiteKing, blackKing);

		final Game game = new ChessGame(board);

		game.play(new SimpleMove(position(5, 1), position(6, 2)));
		game.play(new SimpleMove(position(4, 8), position(5, 8)));

		final Board finalBoard = new StandardBoard(
				new King(WHITE, position(6, 2)),
				new King(BLACK, position(5, 8)));

		assertThat(game.currentBoard(), equalTo(finalBoard));
	}

	private Position position(final int column, final int row) {
		return new Position(column, row);
	}

}
