package chess.ui;

import java.io.ByteArrayInputStream;

import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import ui.Output;
import ui.ScannerInterface;
import chess.Board;
import chess.Game;
import chess.pieces.Position;
import chess.pieces.move.SimpleMove;

public class TestScannerInterface {
	@Rule public final JUnitRuleMockery context = new JUnitRuleMockery();

	@Mock private Game game;
	@Mock private Output out;
	@Mock private Board board;

	@Test
	public void playsSpecifiedMove() {
		final String inputString = "move\ne2-e4";
		System.setIn(new ByteArrayInputStream(inputString.getBytes()));

		final ScannerInterface ui = new ScannerInterface(game, out);

		context.checking(new Expectations() {{
			oneOf(out).println("Enter command:");
			oneOf(out).println("Enter move:");
			oneOf(game).play(new SimpleMove(new Position(5, 2), new Position(5, 4)));
		}});

		ui.getCommand();
	}

	@Test
	public void showsBoardOnRequest() {
		final String inputString = "show";
		System.setIn(new ByteArrayInputStream(inputString.getBytes()));

		final ScannerInterface ui = new ScannerInterface(game, out);

		context.checking(new Expectations() {{
			oneOf(out).println("Enter command:");
			oneOf(game).currentBoard(); will(returnValue(board));
			oneOf(board).print(); will(returnValue("something"));
			oneOf(out).println("something");
		}});

		ui.getCommand();
	}

}
