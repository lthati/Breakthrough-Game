/**
 * @author leela
 * Breakthrough Implementation
 */
public class BreakthroughImpl implements Breakthrough {

	private int size = 8;
	private PieceType[][] board = new PieceType[size][size];
	private PlayerType playerInTurn;

	// initializing the board with pieces
	public BreakthroughImpl() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (i == 0 || i == 1) {
					// Placing black pieces on 0 and 1st rows
					board[i][j] = PieceType.BLACK;
				} else if (i == size - 2 || i == size - 1) {
					// Placing white pieces on 6th and 7th rows
					board[i][j] = PieceType.WHITE;
				} else {
					// All the remaining are empty
					board[i][j] = PieceType.NONE;
				}
			}
		}

		// And the first turn is white's
		playerInTurn = PlayerType.WHITE;
	}

	public PieceType getPieceAt(int row, int column) {
		return board[row][column];
	}

	public PlayerType getPlayerInTurn() {
		return playerInTurn;
	}

	public PlayerType getWinner() {

		PlayerType winner = null;
		for (int j = 0; j < size; j++) {

			// Checking if white reached it's home row i.e (0th row)
			if (getPieceAt(0, j) == PieceType.WHITE) {
				winner = PlayerType.WHITE;
			}

			// Checking if black reached it's home row i.e (7th row)
			if (getPieceAt(size - 1, j) == PieceType.BLACK) {
				winner = PlayerType.BLACK;
			}
		}

		return winner;
	}

	public boolean isMoveValid(int fromRow, int fromColumn, int toRow, int toColumn) {
		if(!isValidLocation(fromRow, fromColumn) || !isValidLocation(toRow, toColumn))
		{
			return false;
		}
		
		//only straight and diagonal position moves are allowed
		if (toColumn > fromColumn + 1 || toColumn < fromColumn - 1) {
			return false;
		}
		PieceType pawn = getPieceAt(fromRow, fromColumn);
		if (pawn == PieceType.BLACK && getPlayerInTurn() == PlayerType.BLACK) {
			if (toRow != fromRow + 1) {
				return false;
			} else if (getPieceAt(toRow, toColumn) == PieceType.NONE) {
				return true;
			} else if ((getPieceAt(toRow, toColumn) == PieceType.WHITE) && (toColumn != fromColumn)) {
				return true;
			}
		} else if (pawn == PieceType.WHITE && getPlayerInTurn()== PlayerType.WHITE) {
			if (toRow != fromRow - 1) {
				return false;
			} else if (getPieceAt(toRow, toColumn) == PieceType.NONE) {
				return true;
			} else if ((getPieceAt(toRow, toColumn) == PieceType.BLACK) && (toColumn != fromColumn)) {
				return true;
			}
		}
		return false;
	}

	public void move(int fromRow, int fromColumn, int toRow, int toColumn) {
		if (isMoveValid(fromRow, fromColumn, toRow, toColumn)) {
			board[toRow][toColumn] = getPieceAt(fromRow, fromColumn);
			board[fromRow][fromColumn] = PieceType.NONE;
			playerInTurn = playerInTurn == PlayerType.WHITE ? PlayerType.BLACK : PlayerType.WHITE;
			getWinner();
		}
	}

	private boolean isValidLocation(int row, int column) {

		if(row < 0 || row >= size || column < 0 || column >= size)
		{
			return false;
		}
		return true;
	}
}
