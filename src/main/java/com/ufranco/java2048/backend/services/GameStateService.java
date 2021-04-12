package com.ufranco.java2048.backend.services;

import com.ufranco.java2048.backend.models.Cell;
import com.ufranco.java2048.backend.models.GameState;
import com.ufranco.java2048.backend.repositories.GameStateRepository;
import com.ufranco.java2048.backend.utils.Movement;

import java.util.*;

import static com.ufranco.java2048.backend.utils.Movement.*;

public class GameStateService {

  public final Integer BOARD_SIZE = 4;
  GameStateRepository repository;

  public GameStateService() {
    this.repository = new GameStateRepository();
  }

  public GameStateService(GameStateRepository repository) {
    this.repository = repository;
  }

  public GameState createGameState() {
    var state = repository.create();
    var gameBoard = arrayMatrixToListMatrix(state.getBoard());
    insertValueRandomInFreePosition(gameBoard);
    insertValueRandomInFreePosition(gameBoard);

    state.setBoard(listMatrixToArrayMatrix(gameBoard));

    return state;
  }

  public GameState updateGameState(Movement movement) {
    var state = repository.get();

    var gameBoard = arrayMatrixToListMatrix(state.getBoard());

    System.out.println(gameBoard.get(0)+"\n"+gameBoard.get(1)+"\n"+gameBoard.get(2)+"\n"+gameBoard.get(3)+"\n");

    state.setScore(state.getScore() + applyMovement(gameBoard, movement));

    System.out.println(gameBoard.get(0)+"\n"+gameBoard.get(1)+"\n"+gameBoard.get(2)+"\n"+gameBoard.get(3)+"\n");

    state.setGameOver(
      isGameOver(listMatrixToArrayMatrix(gameBoard))
    );

    if(state.isGameOver()){
      isWinner(gameBoard);
    } else {
      insertValueRandomInFreePosition(gameBoard);
    }


    state.setBoard(listMatrixToArrayMatrix(gameBoard));
    state.incrementMoves();
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

  private Integer applyMovement(List<ArrayList<Integer>> board, Movement move) {
    Integer partialScore = 0;

    if (move.equals(RIGHT) || move.equals(LEFT)) {

      for (int x = 0; x < BOARD_SIZE; x++) {
        removeAllZeroes(board.get(x));
        partialScore = sum(board.get(x));
        completeRow(board.get(x), move);
      }
    }

    if (move.equals(UP) || move.equals(DOWN)) {
      var newColumn = new ArrayList<Integer>();
      for (int y = 0; y < BOARD_SIZE; y++) {
        newColumn = upOrDown(board, move, y);

        for (int row = 0; row < board.get(y).size(); row++) {
          board.get(row).set(y, newColumn.get(row));
        }
      }
    }

    return partialScore;
  }

  private ArrayList<Integer> upOrDown(
    List<ArrayList<Integer>> board,
    Movement dir,
    int column
  ) {

    Integer partialScore = 0;

    var newColumn = new ArrayList<Integer>();

    for (int row = 0; row < BOARD_SIZE; row++) {
      newColumn.add(board.get(row).get(column));
    }

    removeAllZeroes(newColumn);
    partialScore += sum(newColumn);

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

  private Integer sum(ArrayList<Integer> row) {
    int newValue = 0;
    int partialScore = 0;

    for (int index = 0; index < row.size() - 1; index++) {
      var value = row.get(index);
      var consequentValue = row.get(index + 1);

      if (value.equals(consequentValue)) {
        newValue = value + consequentValue;
        partialScore += newValue;
        row.remove(index + 1);
        row.set(index, newValue);
      }
    }

    return partialScore;
  }

  private void completeRow(ArrayList<Integer> fila, Movement dir) {
    while (fila.size() < BOARD_SIZE) {
      if (dir.equals(RIGHT)) {
        fila.add(0, 0);
      } else {
        fila.add(0);
      }
    }
  }

  private boolean isGameOver(Integer[][] board) {
    var movementLeft = arrayMatrixToListMatrix(board);
    var movementRight = arrayMatrixToListMatrix(board);
    var movementUp = arrayMatrixToListMatrix(board);
    var movementDown = arrayMatrixToListMatrix(board);

    applyMovement(movementLeft, LEFT);
    applyMovement(movementRight, RIGHT);
    applyMovement(movementUp, UP);
    applyMovement(movementDown, DOWN);





    return getEmptyIndexes(movementUp).isEmpty()
      && movementDown.equals(movementUp)
      && movementUp.equals(movementLeft)
      && movementLeft.equals(movementRight);


  }


  public boolean isWinner(List<ArrayList<Integer>> board) {

    boolean winner = false;

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

  private Integer generateNumber() {
    return (int) Math.floor(Math.random() + 1.5) * 2;
  }

  private void insertValueRandomInFreePosition(List<ArrayList<Integer>> board) {
    var emptyIndexes = getEmptyIndexes(board);
    var newValue = generateNumber();

    int coordinates = new Random().nextInt(emptyIndexes.size());
    int x = emptyIndexes.get(coordinates).getX();
    int y = emptyIndexes.get(coordinates).getY();

    if (board.get(x).get(y) == 0) {
      board.get(x).set(y, newValue);
    }

  }
}
