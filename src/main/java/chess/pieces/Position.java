package chess.pieces;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Position {

	public final int column;
	public final int row;

	public Position(final int column, final int row) {
		this.column = column;
		this.row = row;
	}

	@Override
	public String toString() {
		final String columnLetter = columnLetter();
		return columnLetter + row;
	}

	private String columnLetter() {
		final char columnLetter = (char) (column - 1 + 'a');
		return "" + columnLetter;
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
