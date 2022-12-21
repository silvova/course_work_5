package gomoku.tests;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import gomoku.model.*;

public class GomokuTests {

	@Test
	public void moveTest1(){//проверка хода 1 игрока
		Gomoku game = new Gomoku();
		game.move(0, 0);
		assertEquals(game.getElement(0, 0),'X');
	}

	@Test
	public void moveTest2(){//проверка хода 2 игрока
		Gomoku game = new Gomoku();
		game.move(0, 0);
		game.move(5, 5);
		assertEquals(game.getElement(5, 5), 'O');
	}


	@Test
	public void moveOutOfBoard1(){//выход за пределы поля
		Gomoku game = new Gomoku();
		assertEquals(game.move(-1, -1), -1);
	}

	@Test
	public void moveOutOfBoard2(){//выход за пределы поля
		Gomoku game = new Gomoku();
		assertEquals(game.move(13, 13), -1);
	}
	
	@Test
	public void checkWinTest1(){
		Gomoku game = new Gomoku();
		game.move(0, 0);
		game.move(0, 1);
		game.move(1, 0);
		game.move(1, 2);
		game.move(2, 0);
		game.move(2, 3);
		game.move(3, 0);
		game.move(3, 4);
		assertEquals(game.move(4, 0), 1);
	}
	
	@Test
	public void checkWinTest2(){
		Gomoku game = new Gomoku();
		game.move(5, 9);
		game.move(1, 2);
		game.move(6, 8);
		game.move(2, 3);
		game.move(7, 7);
		game.move(3, 4);
		game.move(8, 6);
		game.move(9, 9);
		assertEquals(game.move(9, 5), 1);
	}

}