package gomoku.model;
import gomoku.history.*;

import java.io.Serializable;
import java.util.LinkedList;
import java.io.IOException;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Iterator;

public class Gomoku implements Serializable{
	
	private static final int size = 10;
	private static final int countForWin = 5;
	private boolean drawFlag;
	private char[][] board;
	private boolean player;
	private History history;

	public Gomoku(){
		history = new History();

		this.board = new char[size][size];
		for(int i = 0; i < size; i++){
			for(int j = 0; j < size; j++){
				this.board[i][j] = ' ';
			}
		}
		this.player = true;
		this.drawFlag = false;
	}

	public int move(int x, int y){
		if(!this.checkMove(x, y)){
			return -1;
		}
		history.addMove(x, y, this.changeBoard(x, y, player));
		
		if(this.checkWin())
			return 1;
		if(history.getSize() == size*size){
			this.itIsDraw();
			return 1;
		}
		this.changePlayer();
		return 0;
	}
	private char changeBoard(int x, int y, boolean player){
		if(player == true){
			this.board[x][y] = 'X';
			return 'X';
		}
		else{
			this.board[x][y] = 'O';
			return 'O';
		}
	}
	private boolean checkMove(int x, int y){
		if(x < 0 || x >= size && y < 0 || y >= size)
			return false;
		if(this.board[x][y] != ' ')
			return false;
		return true;
	}

	private boolean checkWin(){
		char c = getCurPlayerChar();

		int counter = 0;

		//проверка комбинаций по горизонтали
		for(int i = 0; i < size; i++){
			for(int j = 0; j < size; j++){
				if(c == this.board[i][j])
					counter++;
				else
					counter = 0;
				if(counter == this.countForWin)
					return true;
			}
			counter = 0;
		}

		//проверка комбинаций по вертикали
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				if(c == this.board[j][i])
					counter++;
				else 
					counter = 0;
				if(counter == this.countForWin)
					return true;
			}
			counter = 0;
		}

		//проверка правого нижнего угла
		for(int length = this.countForWin-1; length < size; length++){
			for(int j = 0, i = length; j <= length; j++, i--){
				if(c == this.board[i][j])
					counter++;
				else 
					counter = 0;
				if(counter == this.countForWin)
					return true;
			}
			counter = 0;
		}
		for (int length = size, start = 0; length > this.countForWin-1; length--, start++){
			for(int j = 0, i = start; j < length; j++, i++){
				if(c == this.board[i][j])
					counter++;
				else
					counter = 0;
				if(counter == this.countForWin)
					return true;
			}
			counter = 0;
		}

		//
		for (int length = size - 1, start = 1; length > this.countForWin-1; length--, start++) {
			for(int j = start, i = size - 1, k = 0; k < length; j++, k++){
				if(c == this.board[i-k][j])
					counter++;
				else 
					counter = 0;
				if(counter == this.countForWin)
					return true;
			}
			counter = 0;
		}
		for (int length = size - 1, start = 1; length > this.countForWin-1; length--, start++) {
			for(int j = start, i = 0, k = 0; k < length; j++, k++){
				if(c == this.board[i+k][j])
					counter++;
				else 
					counter = 0;
				if(counter == this.countForWin)
					return true;
			}
			counter = 0;
		}

		return false;
	}
	public History getHistory(){
		return history;
	}
	public boolean checkDraw(){
		return this.drawFlag;
	}
	public void itIsDraw(){
		this.drawFlag = true;
	}
	public String getWinner(){
		if(this.checkDraw()) return "         Draw";
		else if(this.player == true) return "Player X is winner";
		else return "PLayer O is winner";
	}
	public int getSize(){ return this.size; }
	public char getElement(int x, int y){
		return this.board[x][y];
	}
	public boolean getPlayer(){
		return this.player;
	}
	public char getCurPlayerChar(){
		if(this.player == true)
			return 'X';
		else 
			return 'O';
	}
	public char getEmenyPlayerChar(){
		if(this.player == true)
			return 'O';
		else 
			return 'X';
	}
	private void changePlayer(){
		this.player = !this.player;
	}
}