import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    String[] gameBoard = new String[9];
    Arrays.fill(gameBoard, "square");
    String Winner;

    Winner = startGame(gameBoard);

    System.out.println();
    if (Objects.equals(Winner, "X")) {
      System.out.println("Player 1 won!!");
    }
    if (Objects.equals(Winner, "O")) {
      System.out.println("Player 2 won!!");
    }
    if (Objects.equals(Winner, "tie")) {
      System.out.println("It's a tie!!");
    }

    printBoard(gameBoard);
  }

  public static String startGame(String[] gameBoard) {
    String gameWinner = "";


    while (gameWinner.isEmpty()) {
      boolean played = false;
      Scanner sc = new Scanner(System.in);

      while (!played) {
        printBoard(gameBoard);
        System.out.print("Enter player 1 move (1-9):");
        int playerOneMove = sc.nextInt() - 1;
        if (!Objects.equals(gameBoard[playerOneMove], "square")) {
          System.out.println("Invalid move, please try again!");
          continue;
        }

        gameBoard[playerOneMove] = "X";
        played = true;
      }

      gameWinner = checkWinner(gameBoard);
      played = false;

      while (!played && gameWinner.isEmpty()) {
        printBoard(gameBoard);
        System.out.print("Enter player 2 move (1-9):");
        int playerTwoMove = sc.nextInt() - 1;
        if (!Objects.equals(gameBoard[playerTwoMove], "square")) {
          System.out.println("Invalid move, please try again!");
          continue;
        }

        gameBoard[playerTwoMove] = "O";
        played = true;
      }

      gameWinner = checkWinner(gameBoard);
    }
    return gameWinner;
  }

  public static String checkWinner(String[] gameBoard) {

    String[][] lines = {
            Arrays.copyOfRange(gameBoard, 0, 3),
            Arrays.copyOfRange(gameBoard, 3, 6),
            Arrays.copyOfRange(gameBoard, 6, 9),
            new String[]{gameBoard[0], gameBoard[3], gameBoard[6]},
            new String[]{gameBoard[1], gameBoard[4], gameBoard[7]},
            new String[]{gameBoard[2], gameBoard[5], gameBoard[8]},
            new String[]{gameBoard[0], gameBoard[4], gameBoard[8]},
            new String[]{gameBoard[2], gameBoard[4], gameBoard[6]},
    };

    for (String[] line : lines) {
      boolean samePlayerInAllPositions = Arrays.stream(line).distinct().count() == 1;
      if (samePlayerInAllPositions && !Objects.equals(line[0], "square")) {
        return line[0];
      }
      if (!Arrays.asList(gameBoard).contains("square")) {
        return "tie";
      }
    }

    return "";
  }

  public static void printBoard(String[] gameBoard) {
    System.out.println();
    for (int i = 0; i < gameBoard.length; i += 3) {
      for (int j = 0; j < 3; j++) {
        if (Objects.equals(gameBoard[j + i], "square")) System.out.print("⬜");
        if (Objects.equals(gameBoard[j + i], "X")) System.out.print("✖️");
        if (Objects.equals(gameBoard[j + i], "O")) System.out.print("⭕");
      }
      System.out.println();
    }
    System.out.println();
  }

}