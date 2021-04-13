package com.progra3.java2048.backend.services;

import com.progra3.java2048.backend.models.GameState;
import com.progra3.java2048.backend.repositories.GameStateRepository;
import com.progra3.java2048.backend.models.Cell;
import com.progra3.java2048.backend.utils.Movement;

import java.util.*;

import static com.progra3.java2048.backend.utils.Movement.*;

public class GameStateService {
  private final GameStateRepository repository;
  private final Integer BOARD_SIZE = 4;
  private int partialScore = 0;

  public GameStateService() {
    this.repository = new GameStateRepository();
  }

  public GameState createGameState() {
    var state = repository.create();
    partialScore = 0;
    var gameBoard = arrayMatrixToListMatrix(state.getBoard());

    insertRandomValueInFreePosition(gameBoard);
    insertRandomValueInFreePosition(gameBoard);

    state.setBoard(listMatrixToArrayMatrix(gameBoard));
    state.setScore(partialScore);

    repository.update(state);

    return state;
  }

  public GameState updateGameState(Movement movement) {
    var state = repository.get();

    if (state.isGameOver()) return state;

    var gameBoard = arrayMatrixToListMatrix(state.getBoard());
    applyMovement(gameBoard, movement,false);


    if(gameBoard.equals(
      arrayMatrixToListMatrix(state.getBoard())
    )) return state;


    if(!getEmptyIndexes(gameBoard).isEmpty()) {
      insertRandomValueInFreePosition(gameBoard);
    }
    state.setWinner(isWinner(gameBoard));

    state.setGameOver( state.isWinner() || isGameOver(listMatrixToArrayMatrix(gameBoard)));


    state.setBoard(listMatrixToArrayMatrix(gameBoard));
    state.incrementMoves();
    state.setScore(partialScore);

    repository.update(state);
    return state;
  }

  private List<ArrayList<Integer>> arrayMatrixToListMatrix(Integer[][] board) {
    return Arrays.stream(board)
      .map(array -> new ArrayList<>(Arrays.asList(array)))
      .toList();
  }

  private Integer[][] listMatrixToArrayMatrix(List<ArrayList<Integer>> gameBoard) {
    return gameBoard.stream()
      .map(a -> a.toArray(new Integer[]{}))
      .toList()
      .toArray(new Integer[][]{});
  } 
  private void applyMovement(List<ArrayList<Integer>> board, Movement move, boolean validateGameOver) {

    if (move.equals(RIGHT) || move.equals(LEFT)) {

      for (int x = 0; x < BOARD_SIZE; x++) {
        removeAllZeroes(board.get(x));
        sum(board.get(x),validateGameOver);
        completeRow(board.get(x), move);
      }
    } else {
      var newColumn = new ArrayList<Integer>();
      for (int y = 0; y < BOARD_SIZE; y++) {
        newColumn = upOrDown(board, move, y,validateGameOver);

        for (int x = 0; x < board.get(y).size(); x++) {
          board.get(x).set(y, newColumn.get(x));
        }
      }
    }
  }

  private ArrayList<Integer> upOrDown(
    List<ArrayList<Integer>> board,
    Movement dir,
    int column,
    boolean validateGameOver
  ) {

    var newColumn = new ArrayList<Integer>();

    for (int row = 0; row < BOARD_SIZE; row++) {
      newColumn.add(board.get(row).get(column));
    }

    removeAllZeroes(newColumn);
    sum(newColumn,validateGameOver);

    if (dir.equals(UP)) {
      completeRow(newColumn, LEFT);
    } else {
      completeRow(newColumn, RIGHT);
    }

    return newColumn;
  }

  private void removeAllZeroes(ArrayList<Integer> list) {
    while (list.contains(0)) {
      list.remove((Integer) 0);
    }
  }

  private void sum(ArrayList<Integer> row,boolean ValidateGameOver) {

    for (int index = 0; index < row.size() - 1; index++) {
      var value = row.get(index);
      var consequentValue = row.get(index + 1);

      if (value.equals(consequentValue)) {
        if(ValidateGameOver == false) partialScore += (value * 2);
        row.remove(index + 1);
        row.set(index, value * 2);
      }
    }
  }

  private void completeRow(ArrayList<Integer> row, Movement move) {
    while (row.size() < BOARD_SIZE) {
      if (move.equals(RIGHT)) row.add(0, 0);
      else row.add(0);
    }
  }

  private boolean isGameOver(Integer[][] board) {
    var movementUp = arrayMatrixToListMatrix(board);
    var movementLeft = arrayMatrixToListMatrix(board);
    var movementRight = arrayMatrixToListMatrix(board);
    var movementDown = arrayMatrixToListMatrix(board);

    applyMovement(movementLeft, LEFT, true);
    applyMovement(movementRight, RIGHT,true);
    applyMovement(movementUp, UP,true);
    applyMovement(movementDown, DOWN,true);

    return movementDown.equals(movementUp)
      && movementUp.equals(movementLeft)
      && movementLeft.equals(movementRight);

  }


  private boolean isWinner(List<ArrayList<Integer>> board) {
    var winner = false;

    for(int x = 0; x < BOARD_SIZE; x++) {
      for(int y = 0; y < BOARD_SIZE; y++) {
        winner = winner || board.get(x).get(y) == 2048;
      }
    }
    return winner;
  }


  private LinkedList<Cell> getEmptyIndexes(List<ArrayList<Integer>> board) {
    var emptyIndexes = new LinkedList<Cell>();

    for (int x = 0; x < BOARD_SIZE; x++) {
      for (int y = 0; y < BOARD_SIZE; y++) {
        if (board.get(x).get(y) == 0) {
          Cell index = new Cell(x, y);
          emptyIndexes.add(index);
        }
      }
    }

    return emptyIndexes;
  }

  private void insertRandomValueInFreePosition(List<ArrayList<Integer>> board) {
    var emptyCells = getEmptyIndexes(board);

    if(emptyCells.isEmpty()) return;

    var newValue = generateNumber();

    var coordinates =  (int) Math.floor(Math.random() * emptyCells.size());
    var emptyCell = emptyCells.get(coordinates);
    var x = emptyCell.x();
    var y = emptyCell.y();

    if (board.get(x).get(y) == 0) {
      board.get(x).set(y, newValue);
    }

  }

  private Integer generateNumber() {
    return Math.random() * 10.00 < 9 ? 2 : 4;
  }

}
