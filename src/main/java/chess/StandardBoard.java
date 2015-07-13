package chess;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import chess.pieces.Piece;

public class StandardBoard implements Board {

	private final List<Piece> pieces = new ArrayList<>();

	public StandardBoard(final Piece...pieces) {
		for (final Piece piece : pieces) {
			this.pieces.add(piece);
		}
	}

	@Override
	public Board play(final Move move) {
		return null;
	}

	@Override
	public String toString() {
		return "Standard Board with " + pieces.toString();
	}

	@Override
	public boolean equals(final Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

}
