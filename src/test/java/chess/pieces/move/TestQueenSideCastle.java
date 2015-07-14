package chess.pieces.move;

import static chess.pieces.Colour.WHITE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import chess.Board;
import chess.StandardBoard;
import chess.pieces.Bishop;
import chess.pieces.King;
import chess.pieces.Piece;
import chess.pieces.Position;
import chess.pieces.Rook;

public class TestQueenSideCastle {

	private final Piece whiteKing = new King(WHITE, WHITE.kingPosition());
	private final Piece whiteRook = new Rook(WHITE, WHITE.queenSideRookPosition());

	@Test
	public void movesPiecesToCorrectPositions() {
		final QueenSideCastle castle = new QueenSideCastle(WHITE);
		final Board board = new StandardBoard(whiteKing, whiteRook);
		final Set<Piece> updatedPieces = new HashSet<>();
		updatedPieces.add(new King(WHITE, new Position(3, 1)));
		updatedPieces.add(new Rook(WHITE, new Position(4, 1)));
		assertThat(castle.updatePieces(board), equalTo(updatedPieces));
	}

	@Test
	public void cannotCastleIfInterveningSpacesAreOccupied() {
		final QueenSideCastle castle = new QueenSideCastle(WHITE);
		final Board board = new StandardBoard(
				whiteKing,
				whiteRook,
				new Bishop(WHITE, new Position(3, 1)));
		assertThat(castle.isIllegal(board), equalTo(true));
	}

	@Test
	public void cannotCastleIfRookHasMoved() {
		final QueenSideCastle castle = new QueenSideCastle(WHITE);
		Board board = new StandardBoard(whiteKing, whiteRook);
		board = board.play(new SimpleMove(WHITE.queenSideRookPosition(), new Position(2, 1)));
		board = board.play(new SimpleMove(new Position(2, 1), WHITE.queenSideRookPosition()));
		assertThat(castle.isIllegal(board), equalTo(true));
	}

	@Test
	public void cannotCastleIfQueenHasMoved() {
		final QueenSideCastle castle = new QueenSideCastle(WHITE);
		Board board = new StandardBoard(whiteKing, whiteRook);
		board = board.play(new SimpleMove(WHITE.kingPosition(), new Position(5, 2)));
		board = board.play(new SimpleMove(new Position(5, 2), WHITE.kingPosition()));
		assertThat(castle.isIllegal(board), equalTo(true));
	}

	@Test
	public void canCastleIfThereIsSpaceBetweenQueenAndRook() {
		final QueenSideCastle castle = new QueenSideCastle(WHITE);
		final Board board = new StandardBoard(whiteKing, whiteRook);
		assertThat(castle.isIllegal(board), equalTo(false));
	}

}
