package chess;

import static chess.pieces.Colour.BLACK;
import static chess.pieces.Colour.WHITE;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import chess.pieces.Bishop;
import chess.pieces.Colour;
import chess.pieces.King;
import chess.pieces.Knight;
import chess.pieces.Pawn;
import chess.pieces.Piece;
import chess.pieces.Position;
import chess.pieces.Queen;
import chess.pieces.Rook;

public class StandardBoardBuilder {

	private final Set<Piece> pieces = new HashSet<>();

	public StandardBoardBuilder withPiece(final Piece piece) {
		pieces.add(piece);
		return this;
	}

	public StandardBoardBuilder withPieces(final Collection<Piece> pieces) {
		this.pieces.addAll(pieces);
		return this;
	}

	public StandardBoardBuilder withPieces(final Piece...pieces) {
		for (final Piece piece : pieces) {
			this.pieces.add(piece);
		}
		return this;
	}

	public StandardBoard build() {
		return new StandardBoard(pieces);
	}

	public StandardBoard initialSetup() {
		final Set<Piece> pieces = new HashSet<>();
		add(pieces, WHITE);
		add(pieces, BLACK);
		return new StandardBoard(pieces);
	}

	private void add(final Set<Piece> pieces, final Colour colour) {
		addPawns(pieces, colour);
		addRooks(pieces, colour);
		addKnights(pieces, colour);
		addBishops(pieces, colour);
		addQueen(pieces, colour);
		addKing(pieces, colour);
	}

	private void addKing(final Set<Piece> pieces, final Colour colour) {
		pieces.add(new King(colour, colour.kingPosition()));
	}

	private void addQueen(final Set<Piece> pieces, final Colour colour) {
		pieces.add(new Queen(colour, colour.queenPosition()));
	}

	private void addBishops(final Set<Piece> pieces, final Colour colour) {
		final int backRow = colour.backRow();
		pieces.add(new Bishop(colour, new Position(backRow+2*colour.forwardStep(), backRow)));
		pieces.add(new Bishop(colour, new Position(backRow+5*colour.forwardStep(), backRow)));
	}

	private void addKnights(final Set<Piece> pieces, final Colour colour) {
		final int backRow = colour.backRow();
		pieces.add(new Knight(colour, new Position(backRow+1*colour.forwardStep(), backRow)));
		pieces.add(new Knight(colour, new Position(backRow+6*colour.forwardStep(), backRow)));
	}

	private void addRooks(final Set<Piece> pieces, final Colour colour) {
		pieces.add(new Rook(colour, colour.queenSideRookPosition()));
		pieces.add(new Rook(colour, colour.kingSideRookPosition()));
	}

	private void addPawns(final Set<Piece> pieces, final Colour colour) {
		for (int column=1; column<=8; column++) {
			pieces.add(new Pawn(colour, new Position(column, colour.frontRow())));
		}
	}

}
