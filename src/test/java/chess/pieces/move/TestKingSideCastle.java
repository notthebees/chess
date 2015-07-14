package chess.pieces.move;

import static chess.pieces.Colour.WHITE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;

import chess.Board;
import chess.StandardBoard;
import chess.pieces.King;
import chess.pieces.Piece;
import chess.pieces.Position;
import chess.pieces.Rook;

public class TestKingSideCastle {

	private final Piece whiteKing = new King(WHITE, WHITE.kingPosition());
	private final Piece whiteRook = new Rook(WHITE, WHITE.kingSideRookPosition());

	@Test
	public void checksIfRookHasMoved() {
		final KingSideCastle castle = new KingSideCastle(WHITE);
		Board board = new StandardBoard(whiteKing, whiteRook);
		board = board.play(new SimpleMove(WHITE.kingSideRookPosition(), new Position(7, 1)));
		board = board.play(new SimpleMove(new Position(7, 1), WHITE.kingSideRookPosition()));
		assertThat(castle.isIllegal(board), equalTo(true));
	}

	@Test
	public void cannotCastleIfKingHasMoved() {
		final KingSideCastle castle = new KingSideCastle(WHITE);
		Board board = new StandardBoard(whiteKing, whiteRook);
		board = board.play(new SimpleMove(WHITE.kingPosition(), new Position(5, 2)));
		board = board.play(new SimpleMove(new Position(5, 2), WHITE.kingPosition()));
		assertThat(castle.isIllegal(board), equalTo(true));
	}

	@Test
	public void canCastleIfThereIsSpaceBetweenKingAndRook() {
		final KingSideCastle castle = new KingSideCastle(WHITE);
		final Board board = new StandardBoard(whiteKing, whiteRook);
		assertThat(castle.isIllegal(board), equalTo(false));
	}

}
