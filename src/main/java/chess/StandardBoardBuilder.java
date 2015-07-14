package chess;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import chess.pieces.Piece;

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

}
