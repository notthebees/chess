package chess;


public interface Game {

	Board currentBoard();

	void play(Move move);

}
