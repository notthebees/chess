package chess.pieces;

import static chess.pieces.Colour.BLACK;
import static chess.pieces.Colour.WHITE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;

public class TestColour {

	@Test
	public void testOpposites() {
		assertThat(WHITE.opposite(), equalTo(BLACK));
		assertThat(BLACK.opposite(), equalTo(WHITE));
	}

}
