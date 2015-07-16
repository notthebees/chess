package chess;

import static chess.pieces.Colour.BLACK;
import static chess.pieces.Colour.WHITE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;

import chess.pieces.Bishop;
import chess.pieces.King;
import chess.pieces.Pawn;
import chess.pieces.Position;
import chess.pieces.Rook;
import chess.pieces.move.Move;
import chess.pieces.move.SimpleMove;

public class TestStandardBoard {

	@Test
	public void ifPawnRequiresReplacingReturnsBoardInAppropriateState() {
		final StandardBoard board = board().withPiece(new Pawn(WHITE, at(1, 7))).build();
		final StandardBoard updatedBoard = board.play(new SimpleMove(from(1, 7), to(1, 8)));
		assertThat(updatedBoard.pawnToReplace(), equalTo(true));
	}

	@Test
	public void saysIfSpecificColourIsInCheck() {
		final StandardBoard board = board().withPieces(
				new King(WHITE, at(3, 4)),
				new Rook(WHITE, at(4, 4)),
				new King(BLACK, at(6, 4)))
				.build();

		assertThat(board.isInCheck(BLACK), equalTo(true));
		assertThat(board.isInCheck(WHITE), equalTo(false));
	}

	@Test
	public void saysIfPositionIsAttackedBySpecificColour() {
		final StandardBoard board = board().withPieces(
				new Bishop(WHITE, at(3, 4)),
				new Rook(WHITE, at(4, 4)),
				new Bishop(BLACK, at(6, 4)))
				.build();

		assertThat(board.isAttackedBy(WHITE, at(2, 5)), equalTo(true));
		assertThat(board.isAttackedBy(WHITE, at(7, 5)), equalTo(false));
		assertThat(board.isAttackedBy(WHITE, at(3, 4)), equalTo(true));
		assertThat(board.isAttackedBy(WHITE, at(6, 4)), equalTo(true));
		assertThat(board.isAttackedBy(WHITE, at(4, 6)), equalTo(true));
		assertThat(board.isAttackedBy(BLACK, at(4, 6)), equalTo(true));
	}

	@Test
	public void listsWhichPositionsAPieceIsAttacking() {
		final Bishop bishop = new Bishop(WHITE, at(3, 4));
		final StandardBoard board = board()
				.withPieces(bishop, new Pawn(WHITE, at(5, 2)), new Pawn(BLACK, at(6, 7)))
				.build();

		assertThat(board.positionsAttackedBy(bishop),
				containsInAnyOrder(position(1, 2), position(1, 6), position(2, 3), position(2, 5),
						position(4, 3), position(4, 5), position(5, 2), position(5, 6), position(6, 7)));
	}

	@Test
	public void checksIfPositionIsOccupied() {
		final StandardBoard board = board()
				.withPieces(new Pawn(BLACK, at(1, 2)), new Pawn(WHITE, at(2, 3)))
				.build();

		assertThat(board.isOccupiedBy(BLACK, at(1, 2)), equalTo(true));
		assertThat(board.isOccupiedAt(at(2, 3)), equalTo(true));
	}

	@Test
	public void returnsNewBoardWithMovedPiece() {
		final StandardBoard board = board().withPiece(new King(WHITE, at(1, 1))).build();

		final Move move = new SimpleMove(from(1, 1), to(1, 2));

		final Board finalBoard = board().withPiece(new King(WHITE, at(1, 2))).build();
		assertThat(board.play(move), equalTo(finalBoard));
	}

	private Position position(final int column, final int row) {
		return new Position(column, row);
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
