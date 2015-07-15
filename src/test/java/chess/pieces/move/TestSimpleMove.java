package chess.pieces.move;

import static chess.pieces.Colour.BLACK;
import static chess.pieces.Colour.WHITE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;

import chess.Board;
import chess.StandardBoard;
import chess.StandardBoardBuilder;
import chess.pieces.King;
import chess.pieces.Pawn;
import chess.pieces.Position;
import chess.pieces.Queen;

public class TestSimpleMove {

	@Test
	public void removesCapturedPieceFromBoard() {
		final SimpleMove move = new SimpleMove(from(5, 1), to(8, 1));

		final Queen attackingPiece = new Queen(WHITE, at(5, 1));
		final Queen capturedPiece = new Queen(BLACK, at(8, 1));
		final Pawn someOtherPiece = new Pawn(BLACK, at(4, 2));
		final Queen movedPiece = new Queen(WHITE, at(8, 1));

		final StandardBoard board = board()
				.withPieces(attackingPiece, capturedPiece, someOtherPiece)
				.build();

		assertThat(move.updatePieces(board), containsInAnyOrder(movedPiece, someOtherPiece));
	}

	@Test
	public void cannotMoveIfOwnKingWouldBeInCheck() {
		final SimpleMove move = new SimpleMove(from(6, 1), to(6, 2));

		final Board board = board().withPieces(
				new King(BLACK, at(5, 1)),
				new Queen(BLACK, at(6, 1)),
				new Queen(WHITE, at(8, 1)))
				.build();

		assertThat(move.isIllegal(board), equalTo(true));
	}

	@Test
	public void cannotMoveIfPositionOccupiedByPieceOfSameColour() {
		final SimpleMove move = new SimpleMove(from(1, 1), to(4, 4));

		final StandardBoard board = board()
				.withPieces(new Queen(BLACK, at(1, 1)), new King(BLACK, at(4, 4)))
				.build();

		assertThat(move.isIllegal(board), equalTo(true));
	}

	@Test
	public void cannotMovePieceOffBoard() {
		final StandardBoard board = board().withPieces(new Queen(BLACK, at(1, 1))).build();

		final SimpleMove aMove = new SimpleMove(from(1, 1), to(1, 0));
		final SimpleMove anotherMove = new SimpleMove(from(1, 1), to(9, 9));

		assertThat(aMove.isIllegal(board), equalTo(true));
		assertThat(anotherMove.isIllegal(board), equalTo(true));
	}

	private Position at(final int column, final int row) {
		return new Position(column, row);
	}

	private Position from(final int column, final int row) {
		return new Position(column, row);
	}

	private Position to(final int column, final int row) {
		return new Position(column, row);
	}

	private StandardBoardBuilder board() {
		return new StandardBoardBuilder();
	}

}
