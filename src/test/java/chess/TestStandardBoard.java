package chess;

import static chess.pieces.Colour.BLACK;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;

import chess.pieces.King;
import chess.pieces.Position;

public class TestStandardBoard {

	@Test
	public void returnsNewBoardWithMovedPiece() {
		final StandardBoard board = new StandardBoard(new King(BLACK, new Position(1, 1)));

		final Move move = new Move(new Position(1, 1), new Position(1, 2));

		final Board finalBoard = new StandardBoard(new King(BLACK, new Position(1, 2)));
		assertThat(board.play(move), equalTo(finalBoard));
	}

}
