import org.junit.*;

import static org.junit.Assert.*;

/**
 * @author leela
 * Initial test case class for Breakthrough
 */
public class TestBreakthrough {
	Breakthrough game;

	/** Fixture */
	@Before
	public void setUp() {
		game = new BreakthroughImpl();
	}

	// Initially Should have black pawns on 0 and 1st rows
	@Test
	public void shouldHaveBlackPawnOn00() {
		assertEquals("Black has pawn on (0,0)", BreakthroughImpl.PieceType.BLACK, game.getPieceAt(0, 0));
	}

	@Test
	public void shouldHaveBlackPawnOn07() {
		assertEquals("Black has pawn on (0,7)", BreakthroughImpl.PieceType.BLACK, game.getPieceAt(0, 7));
	}

	@Test
	public void shouldHaveBlackPawnOn10() {
		assertEquals("Black has pawn on (1,0)", BreakthroughImpl.PieceType.BLACK, game.getPieceAt(1, 0));
	}

	@Test
	public void shouldHaveBlackPawnOn17() {
		assertEquals("Black has pawn on (1,7)", BreakthroughImpl.PieceType.BLACK, game.getPieceAt(1, 7));
	}

	// Initially Should have white pawns on 6th and 7th rows
	@Test
	public void shouldHaveWhitePawnOn60() {
		assertEquals("White has pawn on (6,0)", BreakthroughImpl.PieceType.WHITE, game.getPieceAt(6, 0));
	}

	@Test
	public void shouldHaveWhitePawnOn67() {
		assertEquals("White has pawn on (6,7)", BreakthroughImpl.PieceType.WHITE, game.getPieceAt(6, 7));
	}

	@Test
	public void shouldHaveWhitePawnOn70() {
		assertEquals("White has pawn on (7,0)", BreakthroughImpl.PieceType.WHITE, game.getPieceAt(7, 0));
	}

	@Test
	public void shouldHaveWhitePawnOn77() {
		assertEquals("White has pawn on (7,7)", BreakthroughImpl.PieceType.WHITE, game.getPieceAt(7, 7));
	}

	// Initially 2,3,4,5 rows should be empty
	@Test
	public void shouldBeEmptyOn20() {
		assertEquals("(2,0) should be empty initially", BreakthroughImpl.PieceType.NONE, game.getPieceAt(2, 0));
	}

	@Test
	public void shouldBeEmptyOn27() {
		assertEquals("(2,7) should be empty initially", BreakthroughImpl.PieceType.NONE, game.getPieceAt(2, 7));
	}

	@Test
	public void shouldBeEmptyOn50() {
		assertEquals("(5,0) should be empty initially", BreakthroughImpl.PieceType.NONE, game.getPieceAt(5, 0));
	}

	@Test
	public void shouldBeEmptyOn57() {
		assertEquals("(5,7) should be empty initially", BreakthroughImpl.PieceType.NONE, game.getPieceAt(5, 7));
	}

	// Initial turn should be white's
	@Test
	public void shouldBeWhiteTurnInitially() {
		assertEquals("turn should be white's initially", BreakthroughImpl.PlayerType.WHITE, game.getPlayerInTurn());
	}

	// Immediately after white makes a move the turn should be black's
	@Test
	public void shouldBeBlacksTurnAfterWhites() {
		assertEquals("turn should be white's initially", BreakthroughImpl.PlayerType.WHITE, game.getPlayerInTurn());
		game.move(6, 3, 5, 3);
		assertEquals("turn should be black's after white makes a move", BreakthroughImpl.PlayerType.BLACK,
				game.getPlayerInTurn());
	}

	// Immediately after black makes a move the turn should be white's
	@Test
	public void shouldBeWhiteTurnAfterBlack() {
		assertEquals("turn should be white's initially", BreakthroughImpl.PlayerType.WHITE, game.getPlayerInTurn());
		game.move(6, 3, 5, 3);
		assertEquals("turn should be black's after white makes a move", BreakthroughImpl.PlayerType.BLACK,
				game.getPlayerInTurn());
		game.move(1, 0, 2, 1);
		assertEquals("turn should be white's after black makes a move", BreakthroughImpl.PlayerType.WHITE,
				game.getPlayerInTurn());
	}

	// Initially no one should be a winner
	@Test
	public void winnerShouldBeNullInitially() {
		assertEquals("Winner should be null at the start of the game", null, game.getWinner());
	}

	// After few moves also winner should be null
	@Test
	public void winnerShouldBeNullAfterFewMoves() {
		assertEquals("Winner should be null at the start of the game", null, game.getWinner());
		game.move(6, 5, 5, 5);
		game.move(1, 2, 2, 3);
		game.move(5, 5, 4, 5);
		game.move(2, 3, 3, 2);
		game.move(4, 5, 3, 5);
		game.move(3, 2, 4, 1);
		game.move(3, 5, 2, 5);
		game.move(1, 6, 2, 5);
		assertEquals("Winner should be null if it didn't reach the home row", null, game.getWinner());
		game.move(6, 1, 5, 1);
		game.move(4, 1, 5, 0);
		game.move(7, 0, 6, 1);
		game.move(5, 0, 6, 1);
		assertEquals("Winner should be null if it didn't reach the home row", null, game.getWinner());
	}

	// if black reaches 7th row i.e it's home row then winner should be black
	@Test
	public void winnerShouldBeBlackIfItHasPawnOn7thRow() {
		assertEquals("Winner should be null at the start of the game", null, game.getWinner());
		game.move(6, 5, 5, 5);
		game.move(1, 2, 2, 3);
		game.move(5, 5, 4, 5);
		game.move(2, 3, 3, 2);
		game.move(4, 5, 3, 5);
		game.move(3, 2, 4, 1);
		game.move(3, 5, 2, 5);
		game.move(1, 6, 2, 5);
		assertEquals("Winner should be null if it didn't reach the home row", null, game.getWinner());
		game.move(6, 1, 5, 1);
		game.move(4, 1, 5, 0);
		game.move(7, 0, 6, 1);
		game.move(5, 0, 6, 1);
		assertEquals("Winner should be null if it didn't reach the home row", null, game.getWinner());
		game.move(5, 1, 4, 1);
		assertEquals("Final turn should be blacks before winning", BreakthroughImpl.PlayerType.BLACK,
				game.getPlayerInTurn());
		game.move(6, 1, 7, 2);
		assertEquals("Winner should be black if it reaches the 7th row", BreakthroughImpl.PlayerType.BLACK,
				game.getWinner());
	}
	
	// if white reaches 0th row i.e it's home row then winner should be white
	@Test
	public void winnerShouldBeWhiteIfItHasPawnOn0thRow() {
		assertEquals("Winner should be null at the start of the game", null, game.getWinner());
		game.move(6, 1, 5, 0);
		game.move(1, 6, 2, 6);
		game.move(5, 0, 4, 0);
		game.move(1, 0, 2, 1);
		game.move(4, 0, 3, 1);
		game.move(2, 1, 3, 2);
		assertEquals("Winner should be null if it didn't reach the home row", null, game.getWinner());
		game.move(3, 1, 2, 0);
		game.move(3, 2, 4, 3);
		game.move(2, 0, 1, 1);
		game.move(4, 3, 5, 3);
		assertEquals("Final turn should be whites before winning", BreakthroughImpl.PlayerType.WHITE,
				game.getPlayerInTurn());
		game.move(1, 1, 0, 2);
		assertEquals("Winner should be white if it reaches the 0th row", BreakthroughImpl.PlayerType.WHITE,
				game.getWinner());
	}

	
	// Initially black move 12 to 23 should be valid
	@Test
	public void testingBlackValidMove12to23() {
		game.move(6, 3, 5, 3);
		assertEquals("Initially black move 12 to 23 should be valid", true, game.isMoveValid(1, 2, 2, 3));
		BreakthroughImpl.PieceType pawn = game.getPieceAt(1, 2);
		assertEquals("initially (2,3) should be none", BreakthroughImpl.PieceType.NONE, game.getPieceAt(2, 3));
		game.move(1, 2, 2, 3);
		assertEquals("after move (1,2) should be none", BreakthroughImpl.PieceType.NONE, game.getPieceAt(1, 2));
		assertEquals("after move (2,3) should be of pawn", pawn, game.getPieceAt(2, 3));
	}

	// Initially black move 02 to 13 should be invalid because it is occupied by
	// black
	@Test
	public void testingBlackInValidMove02to13() {
		game.move(6, 3, 5, 3);
		assertEquals("Initially black move 02 to 13 should be invalid", false, game.isMoveValid(0, 2, 1, 3));
		assertEquals("after move (0,2) should be black as unchanged", BreakthroughImpl.PieceType.BLACK,
				game.getPieceAt(0, 2));
		assertEquals("after move (1,3) should be of black as unchanged", BreakthroughImpl.PieceType.BLACK,
				game.getPieceAt(1, 3));
	}

	// White move 62 to 50 should be invalid because it is not neighbor
	@Test
	public void testingWhiteInValidMove62to50() {
		assertEquals("Initially white move 62 to 50 should be invalid", false,

				game.isMoveValid(6, 2, 5, 0));
		assertEquals("initially (5,0) should be none", BreakthroughImpl.PieceType.NONE, game.getPieceAt(5, 0));
		game.move(6, 2, 5, 0);
		assertEquals("after move (6,2) should be white as unchanged", BreakthroughImpl.PieceType.WHITE,
				game.getPieceAt(6, 2));
		assertEquals("after move (5,0) should be of none as unchanged", BreakthroughImpl.PieceType.NONE,
				game.getPieceAt(5, 0));
	}

	// White move 67 to 68 should be invalid because it is out of board
	@Test
	public void testingWhiteInValidMove67to68() {
		assertEquals("Initially white move 67 to 68 should be invalid", false,

				game.isMoveValid(6, 7, 6, 8));
	}

	// Initially white move 63 to 53 should be valid
	@Test
	public void testingWhiteValidMove53to43() {
		assertEquals("Initially white move 63 to 53 should be valid", true, game.isMoveValid(6, 3, 5, 3));
		BreakthroughImpl.PieceType pawn = game.getPieceAt(6, 3);
		assertEquals("initially (5,3) should be none", BreakthroughImpl.PieceType.NONE, game.getPieceAt(5, 3));
		game.move(6, 3, 5, 3);
		assertEquals("after move (6,3) should be none", BreakthroughImpl.PieceType.NONE, game.getPieceAt(6, 3));
		assertEquals("after move (5,3) should be white", BreakthroughImpl.PieceType.WHITE, game.getPieceAt(5, 3));
	}
	
	@Test // Test isMoveValid() method to check if the piece moves backward if the player wants his previous position.
	  public void testIsMoveValidBackwards() {
		  
		  assertTrue(game.isMoveValid(6, 1, 5, 1));
		  game.move(6, 1, 5, 1);
		  assertTrue(game.isMoveValid(1, 2, 2, 2));
		  game.move(1, 2, 2, 2);
		  // White player cannot take his move back from (5,1) to (6,1). It's an invalid move
		  assertFalse(game.isMoveValid(5, 1, 6, 1));
	  }
	
	@Test // Initially black should not be able to move
	  public void blackShouldNotBeAbleToTakeTurn() {
		  
		  assertFalse(game.isMoveValid(1, 2, 2, 2));
		  
		  assertEquals(BreakthroughImpl.PieceType.BLACK, game.getPieceAt(1, 2));
		  assertEquals(BreakthroughImpl.PieceType.NONE, game.getPieceAt(2, 2));
		  
	  }
	
}