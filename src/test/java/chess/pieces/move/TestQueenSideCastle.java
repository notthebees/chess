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

public class TestQueenSideCastle {

	private final Piece whiteKing = new King(WHITE, WHITE.kingPosition());
	private final Piece whiteRook = new Rook(WHITE, WHITE.queenSideRookPosition());

	@Test
	public void illegalIfWrongPieceInRookPosition() {
		final QueenSideCastle castle = new QueenSideCastle(WHITE);
		final Board board = board()
				.withPieces(whiteRook, new Rook(WHITE, at(8, 2)), new Queen(WHITE, at(5, 1)))
				.build();
		assertThat(castle.isIllegal(WHITE, board), equalTo(true));
	}

	@Test
	public void illegalIfWrongPieceInKingPosition() {
		final QueenSideCastle castle = new QueenSideCastle(WHITE);
		final Board board = board()
				.withPieces(new King(WHITE, at(5, 2)), new Queen(WHITE, at(5, 1)), whiteRook)
				.build();
		assertThat(castle.isIllegal(WHITE, board), equalTo(true));
	}

	@Test
	public void illegalIfNoPieceInRookPosition() {
		final QueenSideCastle castle = new QueenSideCastle(WHITE);
		final Board board = board()
				.withPieces(whiteRook, new Rook(WHITE, at(8, 2)))
				.build();
		assertThat(castle.isIllegal(WHITE, board), equalTo(true));
	}

	@Test
	public void illegalIfNoPieceInKingPosition() {
		final QueenSideCastle castle = new QueenSideCastle(WHITE);
		final Board board = board()
				.withPieces(new King(WHITE, at(5, 2)), whiteRook)
				.build();
		assertThat(castle.isIllegal(WHITE, board), equalTo(true));
	}

	@Test
	public void cannotMoveIfPawnNeedsReplacing() {
		final QueenSideCastle castle = new QueenSideCastle(WHITE);
		Board board = board()
				.withPieces(whiteKing, whiteRook, new Pawn(WHITE, at(1, 7)))
				.build();
		board = board.play(new SimpleMove(from(1, 7), to(1, 8)));
		assertThat(castle.isIllegal(WHITE, board), equalTo(true));
	}

	@Test
	public void cannotCastleIfInterveningSpacesAreAttacked() {
		final QueenSideCastle castle = new QueenSideCastle(WHITE);
		final Board board = board()
				.withPieces(whiteKing, whiteRook, new Rook(BLACK, new Position(4, 5)))
				.build();
		assertThat(castle.isIllegal(WHITE, board), equalTo(true));
	}

	@Test
	public void cannotCastleIfKingIsInCheck() {
		final QueenSideCastle castle = new QueenSideCastle(WHITE);
		final Board board = board()
				.withPieces(whiteKing, whiteRook, new Rook(BLACK, at(5, 5)))
				.build();
		assertThat(castle.isIllegal(WHITE, board), equalTo(true));
	}

	@Test
	public void movesPiecesToCorrectPositions() {
		final QueenSideCastle castle = new QueenSideCastle(WHITE);
		final Board board = board().withPieces(whiteKing, whiteRook).build();
		final Set<Piece> updatedPieces = new HashSet<>();
		updatedPieces.add(new King(WHITE, at(3, 1)));
		updatedPieces.add(new Rook(WHITE, at(4, 1)));
		assertThat(castle.updatePieces(board), equalTo(updatedPieces));
	}

	@Test
	public void cannotCastleIfInterveningSpacesAreOccupied() {
		final QueenSideCastle castle = new QueenSideCastle(WHITE);
		final Board board = board()
				.withPieces(whiteKing, whiteRook, new Bishop(BLACK, at(4, 1)))
				.build();
		assertThat(castle.isIllegal(WHITE, board), equalTo(true));
	}

	@Test
	public void cannotCastleIfRookHasMoved() {
		final QueenSideCastle castle = new QueenSideCastle(WHITE);
		Board board = board().withPieces(whiteKing, whiteRook, new Pawn(BLACK, at(1, 7))).build();
		board = board.play(new SimpleMove(WHITE.queenSideRookPosition(), to(2, 1)));
		board = board.play(new SimpleMove(from(1, 7), to(1, 6)));
		board = board.play(new SimpleMove(from(2, 1), WHITE.queenSideRookPosition()));
		board = board.play(new SimpleMove(from(1, 6), to(1, 5)));
		assertThat(castle.isIllegal(WHITE, board), equalTo(true));
	}

	@Test
	public void cannotCastleIfQueenHasMoved() {
		final QueenSideCastle castle = new QueenSideCastle(WHITE);
		Board board = board().withPieces(whiteKing, whiteRook, new Pawn(BLACK, at(1, 7))).build();
		board = board.play(new SimpleMove(WHITE.kingPosition(), to(5, 2)));
		board = board.play(new SimpleMove(from(1, 7), to(1, 6)));
		board = board.play(new SimpleMove(from(5, 2), WHITE.kingPosition()));
		board = board.play(new SimpleMove(from(1, 6), to(1, 5)));
		assertThat(castle.isIllegal(WHITE, board), equalTo(true));
	}

	@Test
	public void canCastleIfThereIsSpaceBetweenQueenAndRook() {
		final QueenSideCastle castle = new QueenSideCastle(WHITE);
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
