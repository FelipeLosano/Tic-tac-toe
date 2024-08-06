import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    String[] gameBoard = boardInitializer();
    String winner = startGame(gameBoard);
    printWinner(winner);
    printBoard(gameBoard);
  }

  public static String[] boardInitializer() {
    String[] gameBoard = new String[9];
    Arrays.fill(gameBoard, "square");
    return gameBoard;
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

  public static String startGame(String[] gameBoard) {
    String winner = "";


    while (winner.isEmpty()) {

      gameMove(gameBoard, 1, winner);
      winner = checkWinner(gameBoard);
      gameMove(gameBoard, 2, winner);
      winner = checkWinner(gameBoard);
    }

    return winner;
  }

  public static void gameMove(String[] gameBoard, int player, String gameWinner) {
    boolean played = false;
    Scanner sc = new Scanner(System.in);

    while (!played && gameWinner.isEmpty()) {
      printBoard(gameBoard);
      System.out.print("Enter player " + player + " move (1-9):");
      int playerMove = sc.nextInt() - 1;
      if (!Objects.equals(gameBoard[playerMove], "square")) {
        System.out.println("Invalid move, please try again!");
        continue;
      }
      if (player == 1) {
        gameBoard[playerMove] = "X";
      } else if (player == 2) {
        gameBoard[playerMove] = "O";
      }

      played = true;
    }
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

  public static void printWinner(String winner) {
    System.out.println();
    if (Objects.equals(winner, "X")) {
      System.out.println("Player 1 won!!");
    }
    if (Objects.equals(winner, "O")) {
      System.out.println("Player 2 won!!");
    }
    if (Objects.equals(winner, "tie")) {
      System.out.println("It's a tie!!");
    }
  }


}