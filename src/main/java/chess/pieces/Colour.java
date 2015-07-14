package chess.pieces;

public enum Colour {
	WHITE (1, 2, 1),
	BLACK (8, 7, -1);

	private final int backRow;
	private final int frontRow;
	private final int forwardStep;

	Colour(final int backRow, final int frontRow, final int forwardStep) {
		this.backRow = backRow;
		this.frontRow = frontRow;
		this.forwardStep = forwardStep;
	}

	public int backRow() {
		return backRow;
	}

	public int frontRow() {
		return frontRow;
	}

	public int forwardStep() {
		return forwardStep;
	}

	public Position kingPosition() {
		final int kingColumn = backRow + 4*forwardStep;
		return new Position(kingColumn, backRow);
	}

	public Position queenPosition() {
		final int queenColumn = backRow + 3*forwardStep;
		return new Position(queenColumn, backRow);
	}

	public Position kingSideRookPosition() {
		final int rookColumn = backRow + 7*forwardStep;
		return new Position(rookColumn, backRow);
	}

	public Position queenSideRookPosition() {
		final int rookColumn = backRow;
		return new Position(rookColumn, backRow);
	}

}
