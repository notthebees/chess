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
import chess.pieces.Colour;
import chess.pieces.King;
import chess.pieces.Piece;
import chess.pieces.Position;

public class TestIntegration {

	@Test
	public void twoPlayersTakeTurnsMovingKings() {
		final Piece whiteKing = new King(WHITE, new Position(5, 1));
		final Piece blackKing = new King(BLACK, new Position(4, 8));

		final Board board = new StandardBoard(whiteKing, blackKing);

		final Game game = new ChessGame(board);

		game.play(new Position(5,1), new Position(6,2));
		game.play(new Position(4,8), new Position(5,8));

		final Board finalBoard = new StandardBoard(
				new King(Colour.WHITE, new Position(6, 2)),
				new King(Colour.BLACK, new Position(5, 8)));

		assertThat(game.currentBoard(), equalTo(finalBoard));
	}

}
