package chess;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import chess.pieces.Piece;
import chess.pieces.Position;

public class StandardBoard implements Board {

	private final List<Piece> pieces = new ArrayList<>();

	public StandardBoard(final Piece...pieces) {
		for (final Piece piece : pieces) {
			this.pieces.add(piece);
		}
	}

	@Override
	public Board move(final Position from, final Position to) {
		// TODO Auto-generated method stub
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
