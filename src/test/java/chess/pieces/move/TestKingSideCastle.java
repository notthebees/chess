package chess.pieces.move;

import static chess.pieces.Colour.BLACK;
import static chess.pieces.Colour.WHITE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import chess.Board;
import chess.StandardBoardBuilder;
import chess.pieces.Bishop;
import chess.pieces.King;
import chess.pieces.Pawn;
import chess.pieces.Piece;
import chess.pieces.Position;
import chess.pieces.Queen;
import chess.pieces.Rook;

public class TestKingSideCastle {

	private final Piece whiteKing = new King(WHITE, WHITE.kingPosition());
	private final Piece whiteRook = new Rook(WHITE, WHITE.kingSideRookPosition());

	@Test
	public void blackPiecesPlacedCorrectly() {
		Board board = board()
				.withPieces(new King(BLACK, at(5, 8)), new Rook(BLACK, at(8, 8)), new Pawn(WHITE, at(1, 2)))
				.build();
		board = board.play(new SimpleMove(from(1, 2), to(1, 3)));

		final Board finalBoard = board()
				.withPieces(new King(BLACK, at(7, 8)), new Rook(BLACK, at(6, 8)), new Pawn(WHITE, at(1, 3)))
				.build();

		assertThat(board.play(new KingSideCastle(BLACK)),
				equalTo(finalBoard));
	}

	@Test
	public void cannotMoveIfWrongColour() {
		final KingSideCastle move = new KingSideCastle(WHITE);
		final Board board = board()
				.withPieces(whiteKing, whiteRook)
				.build();

		assertThat(move.isIllegal(BLACK, board), equalTo(true));
	}

	@Test
	public void illegalIfWrongPieceInRookPosition() {
		final KingSideCastle castle = new KingSideCastle(WHITE);
		final Board board = board()
				.withPieces(whiteKing, new Rook(WHITE, at(8, 2)), new Queen(WHITE, at(5, 1)))
				.build();
		assertThat(castle.isIllegal(WHITE, board), equalTo(true));
	}

	@Test
	public void illegalIfWrongPieceInKingPosition() {
		final KingSideCastle castle = new KingSideCastle(WHITE);
		final Board board = board()
				.withPieces(new King(WHITE, at(5, 2)), new Queen(WHITE, at(5, 1)), whiteRook)
				.build();
		assertThat(castle.isIllegal(WHITE, board), equalTo(true));
	}

	@Test
	public void illegalIfNoPieceInRookPosition() {
		final KingSideCastle castle = new KingSideCastle(WHITE);
		final Board board = board()
				.withPieces(whiteRook, new Rook(WHITE, at(8, 2)))
				.build();
		assertThat(castle.isIllegal(WHITE, board), equalTo(true));
	}

	@Test
	public void illegalIfNoPieceInKingPosition() {
		final KingSideCastle castle = new KingSideCastle(WHITE);
		final Board board = board()
				.withPieces(new King(WHITE, at(5, 2)), whiteRook)
				.build();
		assertThat(castle.isIllegal(WHITE, board), equalTo(true));
	}

	@Test
	public void cannotMoveIfPawnNeedsReplacing() {
		final KingSideCastle castle = new KingSideCastle(WHITE);
		Board board = board()
				.withPieces(whiteKing, whiteRook, new Pawn(WHITE, at(1, 7)))
				.build();
		board = board.play(new SimpleMove(from(1, 7), to(1, 8)));
		assertThat(castle.isIllegal(WHITE, board), equalTo(true));
	}

	@Test
	public void cannotCastleIfInterveningSpacesAreOccupied() {
		final KingSideCastle castle = new KingSideCastle(WHITE);
		final Board board = board()
				.withPieces(whiteKing, whiteRook, new Bishop(BLACK, at(6, 1)))
				.build();
		assertThat(castle.isIllegal(WHITE, board), equalTo(true));
	}

	@Test
	public void cannotCastleIfInterveningSpacesAreAttacked() {
		final KingSideCastle castle = new KingSideCastle(WHITE);
		final Board board = board()
				.withPieces(whiteKing, whiteRook, new Rook(BLACK, at(6, 5)))
				.build();
		assertThat(castle.isIllegal(WHITE, board), equalTo(true));
	}

	@Test
	public void cannotCastleIfKingIsInCheck() {
		final KingSideCastle castle = new KingSideCastle(WHITE);
		final Board board = board()
				.withPieces(whiteKing, whiteRook, new Rook(BLACK, at(5, 5)))
				.build();
		assertThat(castle.isIllegal(WHITE, board), equalTo(true));
	}

	@Test
	public void movesPiecesToCorrectPositions() {
		final KingSideCastle castle = new KingSideCastle(WHITE);
		final Board board = board().withPieces(whiteKing, whiteRook).build();
		final Set<Piece> updatedPieces = new HashSet<>();
		updatedPieces.add(new King(WHITE, at(7, 1)));
		updatedPieces.add(new Rook(WHITE, at(6, 1)));
		assertThat(castle.updatePieces(board), equalTo(updatedPieces));
	}

	@Test
	public void cannotCastleIfRookHasMoved() {
		final KingSideCastle castle = new KingSideCastle(WHITE);
		Board board = board().withPieces(whiteKing, whiteRook, new Pawn(BLACK, at(1, 7))).build();
		board = board.play(new SimpleMove(from(8, 1), to(7, 1)));
		board = board.play(new SimpleMove(from(1, 7), to(1, 6)));
		board = board.play(new SimpleMove(from(7, 1), to(8, 1)));
		board = board.play(new SimpleMove(from(1, 6), to(1, 5)));
		assertThat(castle.isIllegal(WHITE, board), equalTo(true));
	}

	@Test
	public void cannotCastleIfKingHasMoved() {
		final KingSideCastle castle = new KingSideCastle(WHITE);
		Board board = board().withPieces(whiteKing, whiteRook, new Pawn(BLACK, at(1, 7))).build();
		board = board.play(new SimpleMove(from(5, 1), to(5, 2)));
		board = board.play(new SimpleMove(from(1, 7), to(1, 6)));
		board = board.play(new SimpleMove(from(5, 2), to(5, 1)));
		board = board.play(new SimpleMove(from(1, 6), to(1, 5)));
		assertThat(castle.isIllegal(WHITE, board), equalTo(true));
	}

	@Test
	public void canCastleIfThereIsSpaceBetweenKingAndRook() {
		final KingSideCastle castle = new KingSideCastle(WHITE);
		final Board board = board().withPieces(whiteKing, whiteRook).build();
		assertThat(castle.isIllegal(WHITE, board), equalTo(false));
	}

	private Position from(final int column, final int row) {
		return new Position(column, row);
	}

	private Position to(final int column, final int row) {
		return new Position(column, row);
	}

	private Position at(final int column, final int row) {
		return new Position(column, row);
	}

	private StandardBoardBuilder board() {
		return new StandardBoardBuilder();
	}

}
