package chess;

import ui.ScannerInterface;
import ui.SystemOutput;

public class Main {

	public static void main(final String[] args) {
		final ChessGame game = new ChessGame(new StandardBoardBuilder().initialSetup());
		final ScannerInterface ui = new ScannerInterface(
				game, new SystemOutput());

		while (! ui.gameOver()) {
			ui.getCommand();
		}
	}

}
