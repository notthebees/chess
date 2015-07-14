package chess.pieces.move;

import static chess.pieces.Colour.BLACK;
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

public class TestKingSideCastle {

	private final Piece whiteKing = new King(WHITE, WHITE.kingPosition());
	private final Piece whiteRook = new Rook(WHITE, WHITE.kingSideRookPosition());

	@Test
	public void cannotCastleIfInterveningSpacesAreOccupied() {
		final KingSideCastle castle = new KingSideCastle(WHITE);
		final Board board = new StandardBoard(
				whiteKing,
				whiteRook,
				new Bishop(BLACK, new Position(6, 1)));
		assertThat(castle.isIllegal(board), equalTo(true));
	}

	@Test
	public void cannotCastleIfInterveningSpacesAreAttacked() {
		final KingSideCastle castle = new KingSideCastle(WHITE);
		final Board board = new StandardBoard(whiteKing, whiteRook, new Rook(BLACK, new Position(6, 5)));
		assertThat(castle.isIllegal(board), equalTo(true));
	}

	@Test
	public void cannotCastleIfKingIsInCheck() {
		final KingSideCastle castle = new KingSideCastle(WHITE);
		final Board board = new StandardBoard(whiteKing, whiteRook, new Rook(BLACK, new Position(5, 5)));
		assertThat(castle.isIllegal(board), equalTo(true));
	}

	@Test
	public void movesPiecesToCorrectPositions() {
		final KingSideCastle castle = new KingSideCastle(WHITE);
		final Board board = new StandardBoard(whiteKing, whiteRook);
		final Set<Piece> updatedPieces = new HashSet<>();
		updatedPieces.add(new King(WHITE, new Position(7, 1)));
		updatedPieces.add(new Rook(WHITE, new Position(6, 1)));
		assertThat(castle.updatePieces(board), equalTo(updatedPieces));
	}

	@Test
	public void cannotCastleIfRookHasMoved() {
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
