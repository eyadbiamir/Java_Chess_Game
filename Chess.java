/*
====================================================================
||                                                                ||
|| COPYRIGHT © 2022 Eyad Binamir.                                 ||
||  Description:                                                  ||
||    This code represents an integrated Chess game based on CLI. ||
||                                                                ||
====================================================================
*/

import java.util.Scanner;

public class Chess {
  static String deadPiece;
  static boolean[][] whiteCheckMateIndexes = new boolean[8][8];
  static boolean blackCheckMate;

  static String board[][] = new String[8][8];
  static String[][] defaultBoard = new String[8][8];

  static boolean[][] blackCheckMateIndexes = new boolean[8][8];

  static boolean whiteCheckMate;

  static boolean whiteDone;
  static boolean blackDone;

  static boolean isDraw;
  static Scanner input = new Scanner(System.in);

  public static void main(String[] args) {
    Black.piece[0] = Black.solider1;
    Black.piece[1] = Black.solider2;
    Black.piece[2] = Black.solider3;
    Black.piece[3] = Black.solider4;
    Black.piece[4] = Black.solider5;
    Black.piece[5] = Black.solider6;
    Black.piece[6] = Black.solider7;
    Black.piece[7] = Black.solider8;

    Black.piece[8] = Black.castle1;
    Black.piece[9] = Black.castle2;
    Black.piece[10] = Black.horse1;
    Black.piece[11] = Black.horse2;
    Black.piece[12] = Black.elph1;
    Black.piece[13] = Black.elph2;
    Black.piece[14] = Black.queen;
    Black.piece[15] = Black.king;

    White.piece[0] = White.solider1;
    White.piece[1] = White.solider2;
    White.piece[2] = White.solider3;
    White.piece[3] = White.solider4;
    White.piece[4] = White.solider5;
    White.piece[5] = White.solider6;
    White.piece[6] = White.solider7;
    White.piece[7] = White.solider8;

    White.piece[8] = White.castle1;
    White.piece[9] = White.castle2;
    White.piece[10] = White.horse1;
    White.piece[11] = White.horse2;
    White.piece[12] = White.elph1;
    White.piece[13] = White.elph2;
    White.piece[14] = White.queen;
    White.piece[15] = White.king;

    for (int i = 0; i <= 7; i++) {
      White.piece[i].menuLabel =
        (i + 1) + "-" + White.piece[i].pieceType + "-" + (i + 1) + "  ";
      Black.piece[i].menuLabel =
        (i + 1) + "-" + Black.piece[i].pieceType + "-" + (i + 1) + "  ";
      Black.piece[i].soliderOrder = i + 1;
      White.piece[i].soliderOrder = i + 1;
    }
    White.castle1.menuLabel = " 9-castle-1  ";
    White.castle2.menuLabel = " 10-castle-2  ";
    White.horse1.menuLabel = " 11-horse-1  ";
    White.horse2.menuLabel = " 12-horse-2  ";
    White.elph1.menuLabel = " 13-Elephant-1  ";
    White.elph2.menuLabel = " 14-Elephant-2  ";
    White.queen.menuLabel = " 15-Queen  ";
    White.king.menuLabel = " 16-King  ";

    Black.castle1.menuLabel = " 9-castle-1  ";
    Black.castle2.menuLabel = " 10-castle-2  ";
    Black.horse1.menuLabel = " 11-horse-1  ";
    Black.horse2.menuLabel = " 12-horse-2  ";
    Black.elph1.menuLabel = " 13-Elephant-1  ";
    Black.elph2.menuLabel = " 14-Elephant-2  ";
    Black.queen.menuLabel = " 15-Queen  ";
    Black.king.menuLabel = " 16-King  ";

    for (int x = 0; x <= 6; x = x + 2) {
      for (int y = 0; y <= 6; y = y + 2) {
        defaultBoard[x][y] = "  WHITE  ";
      }
      for (int y = 1; y <= 7; y = y + 2) {
        defaultBoard[x][y] = "  BLACK  ";
      }
    }

    for (int x = 1; x <= 7; x = x + 2) {
      for (int y = 0; y <= 6; y = y + 2) {
        defaultBoard[x][y] = "  BLACK  ";
      }
      for (int y = 1; y <= 7; y = y + 2) {
        defaultBoard[x][y] = "  WHITE  ";
      }
    }

    for (int i = 0; i <= 7; i++) {
      for (int j = 0; j <= 7; j++) {
        board[i][j] = defaultBoard[i][j];
      }
    }
    // inital indexes of labels

    board[7][1] = White.horse1.label;
    board[7][6] = White.horse2.label;
    board[7][2] = White.elph1.label;
    board[7][5] = White.elph2.label;
    board[7][0] = White.castle1.label;
    board[7][7] = White.castle2.label;
    board[7][3] = White.queen.label;
    board[7][4] = White.king.label;
    board[6][0] = White.solider1.label;
    board[6][1] = White.solider2.label;
    board[6][2] = White.solider3.label;
    board[6][3] = White.solider4.label;
    board[6][4] = White.solider5.label;
    board[6][5] = White.solider6.label;
    board[6][6] = White.solider7.label;
    board[6][7] = White.solider8.label;
    // black
    board[0][1] = Black.horse1.label;
    board[0][6] = Black.horse2.label;
    board[0][2] = Black.elph1.label;
    board[0][5] = Black.elph2.label;
    board[0][0] = Black.castle1.label;
    board[0][7] = Black.castle2.label;
    board[0][3] = Black.queen.label;
    board[0][4] = Black.king.label;
    board[1][0] = Black.solider1.label;
    board[1][1] = Black.solider2.label;
    board[1][2] = Black.solider3.label;
    board[1][3] = Black.solider4.label;
    board[1][4] = Black.solider5.label;
    board[1][5] = Black.solider6.label;
    board[1][6] = Black.solider7.label;
    board[1][7] = Black.solider8.label;

    while (!White.king.isDead && !Black.king.isDead && !isDraw) {
      blackRefresh();
      whiteRefresh();
      setWhiteCheckMate();

      for (int i = 0; i <= 15; i++) {
        White.piece[i].setPosMovWithCheckMate();
      }

      if (getWhiteCheckMate()) {
        whiteCheckMate = true;
      }

      if (isWhitePlayable() == false && whiteCheckMate == false) {
        isDraw = true;
        System.out.println();
        System.out.println("Draw!");
        break;
      }

      getIsDraw();
      if (isDraw) {
        break;
      }
      // white player turn

      printWhiteBoard();

      if (!isWhitePlayable() && whiteCheckMate) {
        System.out.println();
        System.out.println(
          "CHECKMATE!! White king is DEAD!! | Black Player is the WINNER!!"
        );
        White.king.isDead = true;
        break;
      } else if (whiteCheckMate) {
        System.out.println();
        System.out.println("CHECKMATE!!");
        System.out.println();
      }
      whiteDone = false;
      while (!whiteDone) {
        System.out.println("White Player | choose a Piece to move:");
        System.out.println();
        for (int i = 0; i <= 15; i++) {
          if (!White.piece[i].isDead && White.piece[i].isPlayable()) {
            System.out.print(White.piece[i].menuLabel);
          }
        }

        System.out.println();

        if (
          White.king.isPlayed == false &&
          White.castle2.isPlayed == false &&
          White.castle2.isDead == false &&
          whiteCheckMate == false &&
          Piece.isEmpty(7, 6) &&
          whiteCheckMateIndexes[7][6] != true &&
          Piece.isEmpty(7, 5)
        ) {
          System.out.print(" 17-Protect the King by Castle2");
        }
        if (
          White.king.isPlayed == false &&
          White.castle1.isPlayed == false &&
          White.castle1.isDead == false &&
          whiteCheckMate == false &&
          Piece.isEmpty(7, 1) &&
          whiteCheckMateIndexes[7][1] != true &&
          Piece.isEmpty(7, 2) &&
          Piece.isEmpty(7, 3)
        ) {
          System.out.print(" 18-Protect the King by Castle1");
        }
        System.out.println();

        int selection = input.nextInt();
        if (selection >= 1 && selection <= 16) {
          White.piece[selection - 1].play();
        } else if (selection == 17) {
          if (
            White.king.isPlayed == false &&
            White.castle2.isPlayed == false &&
            White.castle2.isDead == false &&
            whiteCheckMate == false &&
            Piece.isEmpty(7, 6) &&
            Piece.isEmpty(7, 5)
          ) {
            White.kingChangePosition(1);
            whiteDone = true;
          } else {
            System.out.println();
            System.out.println("Unavailable");
            System.out.println();
          }
        } else if (selection == 18) {
          if (
            White.king.isPlayed == false &&
            White.castle1.isPlayed == false &&
            White.castle1.isDead == false &&
            whiteCheckMate == false &&
            Piece.isEmpty(7, 1) &&
            Piece.isEmpty(7, 2) &&
            Piece.isEmpty(7, 3)
          ) {
            White.kingChangePosition(2);
            whiteDone = true;
          } else {
            System.out.println();
            System.out.println("Unavailable");
            System.out.println();
          }
        } else {
          System.out.println();
          System.out.println("Invalid entry");
          System.out.println();
        }
      }
      for (int i = 0; i <= 7; i++) {
        White.piece[i].soliderTransform();
      }

      // the final result
      printWhiteBoard();
      printBlackBoard();

      whiteRefresh();
      blackRefresh();
      setBlackCheckMate();

      for (int i = 0; i <= 15; i++) {
        Black.piece[i].setPosMovWithCheckMate();
      }

      if (getBlackCheckMate()) {
        blackCheckMate = true;
      }
      getIsDraw();

      if (isDraw) {
        break;
      }
      if (isBlackPlayable() == false && blackCheckMate == false) {
        isDraw = true;
        System.out.println();
        System.out.println("Draw!");
        break;
      }

      if (isBlackPlayable() == false && blackCheckMate) {
        System.out.println();
        System.out.println(
          "CHECKMATE!! Black king is DEAD!! | White Player is the WINNER!!"
        );
        System.out.println();
        Black.king.isDead = true;
        break;
      } else if (blackCheckMate) {
        System.out.println();
        System.out.println("CHECKMATE!!");
        System.out.println();
      }

      blackDone = false;
      while (blackDone == false) {
        System.out.println("Black Player | Choose a Piece to move:");
        System.out.println();
        for (int i = 0; i <= 15; i++) {
          if (Black.piece[i].isDead == false && Black.piece[i].isPlayable()) {
            System.out.print(Black.piece[i].menuLabel);
          }
        }

        System.out.println();

        if (
          Black.king.isPlayed == false &&
          Black.castle2.isPlayed == false &&
          Black.castle2.isDead == false &&
          blackCheckMate == false &&
          Piece.isEmpty(0, 6) &&
          blackCheckMateIndexes[0][6] != true &&
          Piece.isEmpty(0, 5)
        ) {
          System.out.print(" 17-Protect the King by Castle2");
        }
        if (
          Black.king.isPlayed == false &&
          Black.castle1.isPlayed == false &&
          Black.castle1.isDead == false &&
          blackCheckMate == false &&
          Piece.isEmpty(0, 1) &&
          blackCheckMateIndexes[0][1] != true &&
          Piece.isEmpty(0, 2) &&
          Piece.isEmpty(0, 3)
        ) {
          System.out.print(" 18-Protect the King by Castle1");
        }
        System.out.println();

        int selection = input.nextInt();

        if (selection >= 1 && selection <= 16) {
          Black.piece[selection - 1].play();
        } else if (selection == 17) {
          if (
            Black.king.isPlayed == false &&
            Black.castle2.isPlayed == false &&
            Black.castle2.isDead == false &&
            blackCheckMate == false &&
            Piece.isEmpty(0, 6) &&
            Piece.isEmpty(0, 5)
          ) {
            Black.kingChangePosition(1);
            blackDone = true;
          } else {
            System.out.println();
            System.out.println("Unavailable");
            System.out.println();
          }
        } else if (selection == 18) {
          if (
            Black.king.isPlayed == false &&
            Black.castle1.isPlayed == false &&
            Black.castle1.isDead == false &&
            blackCheckMate == false &&
            Piece.isEmpty(0, 1) &&
            Piece.isEmpty(0, 2) &&
            Piece.isEmpty(0, 3)
          ) {
            Black.kingChangePosition(2);
            blackDone = true;
          } else {
            System.out.println();
            System.out.println("Unavailable");
            System.out.println();
          }
        } else {
          System.out.println("Invalid entry");
          System.out.println();
        }
      }
      for (int i = 0; i <= 7; i++) {
        Black.piece[i].soliderTransform();
      }
      // the final result
      printBlackBoard();
      getIsDraw();
    }
  }

  static void getIsDraw() {
    for (int i = 0; i <= 14; i++) {
      if (White.piece[i].isDead && Black.piece[i].isDead) {
        Chess.isDraw = true;
      } else {
        Chess.isDraw = false;
        break;
      }
    }
    if (Chess.isDraw) {
      System.out.println();
      System.out.println("Draw!");
    }
  }

  static void blackRefresh() {
    for (int i = 0; i <= 15; i++) {
      Black.piece[i].refresh();
    }
  }

  static void whiteRefresh() {
    for (int i = 0; i <= 15; i++) {
      White.piece[i].refresh();
    }
  }

  static void setBlackCheckMate() {
    for (int i = 0; i <= 7; i++) { // set black checkmate indexes
      for (int j = 0; j <= 7; j++) {
        blackCheckMateIndexes[i][j] = false;
      }
    }
    for (int i = 0; i <= 7; i++) { // set black checkmate indexes
      for (int j = 0; j <= 7; j++) {
        for (int k = 8; k <= 15; k++) {
          if (!White.piece[k].isDead && White.piece[k].posibleMoves[i][j]) {
            blackCheckMateIndexes[i][j] = true;
          }
        }
      }
    }
    for (int i = 0; i <= 7; i++) {
      White.piece[i].setSoliderCheckMate();
    }
  }

  static void setWhiteCheckMate() {
    for (int i = 0; i <= 7; i++) { // clear white checkmate indexes
      for (int j = 0; j <= 7; j++) {
        whiteCheckMateIndexes[i][j] = false;
      }
    }
    for (int i = 0; i <= 7; i++) { // set white checkmate indexes
      for (int j = 0; j <= 7; j++) {
        for (int k = 8; k <= 15; k++) {
          if (!Black.piece[k].isDead && Black.piece[k].posibleMoves[i][j]) {
            whiteCheckMateIndexes[i][j] = true;
          }
        }
      }
    }
    for (int i = 0; i <= 7; i++) {
      Black.piece[i].setSoliderCheckMate();
    }
  }

  static boolean isWhitePlayable() {
    boolean result = false;
    for (int i = 0; i <= 15; i++) {
      if (White.piece[i].isPlayable()) {
        result = true;
        break;
      }
    }
    return result;
  }

  static boolean isBlackPlayable() {
    boolean result = false;
    for (int i = 0; i <= 15; i++) {
      if (Black.piece[i].isPlayable()) {
        result = true;
        break;
      }
    }
    return result;
  }

  static final void printWhiteBoard() {
    System.out.println(
      "         1         2         3         4         5         6         7        8      "
    );
    System.out.println(
      "     ------------------------------------------------------------------------------"
    );
    for (int i = 0; i <= 7; i++) {
      System.out.println(
        "    |         |         |         |         |         |         |         |         |"
      );
      System.out.print((i * -1 + 8) + "   |");
      for (int j = 0; j <= 7; j++) {
        System.out.print(board[i][j] + "|");
      }

      System.out.println("   " + ((i * -1) + 8));
      System.out.println(
        "    |         |         |         |         |         |         |         |         |"
      );
      System.out.println(
        "     ------------------------------------------------------------------------------"
      );
    }
    System.out.println(
      "         1         2         3         4         5         6         7        8      "
    );
    System.out.println();
    System.out.println();

    System.out.println(
      "====================================================================================================="
    );
    System.out.println();
  }

  static final void printBlackBoard() {
    System.out.println(
      "         1         2         3         4         5         6         7        8      "
    );
    System.out.println(
      "     ------------------------------------------------------------------------------"
    );
    for (int i = 7; i >= 0; i--) {
      System.out.println(
        "    |         |         |         |         |         |         |         |         |"
      );
      System.out.print((i + 1) + "   |");
      for (int j = 0; j <= 7; j++) {
        System.out.print(board[i][j] + "|");
      }

      System.out.println("   " + (i + 1));
      System.out.println(
        "    |         |         |         |         |         |         |         |         |"
      );
      System.out.println(
        "     ------------------------------------------------------------------------------"
      );
    }
    System.out.println(
      "         1         2         3         4         5         6         7        8      "
    );
    System.out.println();
    System.out.println();

    System.out.println(
      "====================================================================================================="
    );
    System.out.println();
  }

  static boolean getWhiteCheckMate() {
    boolean result = false;
    if (
      whiteCheckMateIndexes[White.king.getPositionX()][White.king.getPositionY()]
    ) {
      whiteCheckMate = true;
      result = true;
    } else {
      whiteCheckMate = false;
      result = false;
    }
    return result;
  }

  static boolean getBlackCheckMate() {
    boolean result = false;
    if (
      blackCheckMateIndexes[Black.king.getPositionX()][Black.king.getPositionY()]
    ) {
      blackCheckMate = true;
      result = true;
    } else {
      blackCheckMate = false;
      result = false;
    }
    return result;
  }
}

class Piece {
  Scanner input = new Scanner(System.in);
  String label;
  String pieceType;
  int positionY;
  int positionX;
  boolean isDead;
  boolean[][] posibleMoves = new boolean[8][8];
  boolean isTransformed;
  String menuLabel;
  int soliderOrder;
  static String deadPiece;
  boolean isPlayed;
  String color;
  static int horseCount = 2;
  static int castleCount = 2;
  static int elephantCount = 2;
  static int queenCount = 1;

  int getPositionY() {
    return positionY;
  }

  int getPositionX() {
    return positionX;
  }

  void getIsDead() {
    if (Piece.deadPiece == label) {
      isDead = true;
    }
  }

  void move(int xSelection, int ySelection) {
    Chess.board[positionX][positionY] =
      Chess.defaultBoard[positionX][positionY];
    setPositionX(xSelection);
    setPositionY(ySelection);
    Chess.board[positionX][positionY] = label;
  }

  void setPositionX(int positionX) {
    this.positionX = positionX;
  }

  void setPositionY(int positionY) {
    this.positionY = positionY;
  }

  static boolean isEmpty(int x, int y) {
    boolean result = false;
    if (Chess.board[x][y] == "  WHITE  " || Chess.board[x][y] == "  BLACK  ") {
      result = true;
    }
    return result;
  }

  void refresh() {
    clearPosibMov();
    getIsDead();
    if (isDead == false) {
      setPosibleMoves();
    }
  }

  void setPosibleMoves() {
    if (pieceType == "king") {
      if (
        (
          (
            (positionX + 1) <= 7 &&
            (positionY + 1) <= 7 &&
            isEmpty((positionX + 1), (positionY + 1))
          )
        ) ||
        (
          (
            (positionX + 1) <= 7 &&
            (positionY + 1) <= 7 &&
            isKillable((positionX + 1), (positionY + 1))
          )
        )
      ) {
        posibleMoves[positionX + 1][positionY + 1] = true;
      }
      if (
        (
          (
            (positionX + 1) <= 7 &&
            (positionY - 1) >= 0 &&
            isEmpty((positionX + 1), (positionY - 1))
          )
        ) ||
        (
          (
            (positionX + 1) <= 7 &&
            (positionY - 1) >= 0 &&
            isKillable((positionX + 1), (positionY - 1))
          )
        )
      ) {
        posibleMoves[positionX + 1][positionY - 1] = true;
      }
      if (
        (
          (
            (positionX - 1) >= 0 &&
            (positionY + 1) <= 7 &&
            isEmpty((positionX - 1), (positionY + 1))
          )
        ) ||
        (
          (
            (positionX - 1) >= 0 &&
            (positionY + 1) <= 7 &&
            isKillable((positionX - 1), (positionY + 1))
          )
        )
      ) {
        posibleMoves[positionX - 1][positionY + 1] = true;
      }
      if (
        (
          (
            (positionX - 1) >= 0 &&
            (positionY - 1) >= 0 &&
            isEmpty((positionX - 1), (positionY - 1))
          )
        ) ||
        (
          (
            (positionX - 1) >= 0 &&
            (positionY - 1) >= 0 &&
            isKillable((positionX - 1), (positionY - 1))
          )
        )
      ) {
        posibleMoves[positionX - 1][positionY - 1] = true;
      }
      if (
        (((positionX + 1) <= 7 && isEmpty((positionX + 1), (positionY)))) ||
        (((positionX + 1) <= 7 && isKillable((positionX + 1), (positionY))))
      ) {
        posibleMoves[positionX + 1][positionY] = true;
      }
      if (
        (((positionY + 1) <= 7 && isEmpty((positionX), (positionY + 1)))) ||
        (((positionY + 1) <= 7 && isKillable((positionX), (positionY + 1))))
      ) {
        posibleMoves[positionX][positionY + 1] = true;
      }
      if (
        (((positionX - 1) >= 0 && isEmpty((positionX - 1), (positionY)))) ||
        (((positionX - 1) >= 0 && isKillable((positionX - 1), (positionY))))
      ) {
        posibleMoves[positionX - 1][positionY] = true;
      }
      if (
        (((positionY - 1) >= 0 && isEmpty((positionX), (positionY - 1)))) ||
        (((positionX) >= 0 && isKillable((positionX), (positionY - 1))))
      ) {
        posibleMoves[positionX][positionY - 1] = true;
      }
    }

    if (pieceType == "horse") {
      if (
        (
          (positionX + 2) <= 7 &&
          (positionY + 1) <= 7 &&
          isEmpty((positionX + 2), (positionY + 1))
        ) ||
        (
          (positionX + 2) <= 7 &&
          (positionY + 1) <= 7 &&
          isKillable((positionX + 2), (positionY + 1))
        )
      ) {
        posibleMoves[positionX + 2][positionY + 1] = true;
      }
      if (
        (
          (positionX + 2) <= 7 &&
          (positionY - 1) >= 0 &&
          isEmpty((positionX + 2), (positionY - 1))
        ) ||
        (
          (positionX + 2) <= 7 &&
          (positionY - 1) >= 0 &&
          isKillable((positionX + 2), (positionY - 1))
        )
      ) {
        posibleMoves[positionX + 2][positionY - 1] = true;
      }
      if (
        (
          (positionX - 2) >= 0 &&
          (positionY + 1) <= 7 &&
          isEmpty((positionX - 2), (positionY + 1))
        ) ||
        (
          (positionX - 2) >= 0 &&
          (positionY + 1) <= 7 &&
          isKillable((positionX - 2), (positionY + 1))
        )
      ) {
        posibleMoves[positionX - 2][positionY + 1] = true;
      }
      if (
        (
          (positionX - 2) >= 0 &&
          (positionY - 1) >= 0 &&
          isEmpty((positionX - 2), (positionY - 1))
        ) ||
        (
          (positionX - 2) >= 0 &&
          (positionY - 1) >= 0 &&
          isKillable((positionX - 2), (positionY - 1))
        )
      ) {
        posibleMoves[positionX - 2][positionY - 1] = true;
      }
      if (
        (
          (positionX + 1) <= 7 &&
          (positionY + 2) <= 7 &&
          isEmpty((positionX + 1), (positionY + 2))
        ) ||
        (
          (positionX + 1) <= 7 &&
          (positionY + 2) <= 7 &&
          isKillable((positionX + 1), (positionY + 2))
        )
      ) {
        posibleMoves[positionX + 1][positionY + 2] = true;
      }
      if (
        (
          (positionX - 1) >= 0 &&
          (positionY + 2) <= 7 &&
          isEmpty((positionX - 1), (positionY + 2))
        ) ||
        (
          (positionX - 1) >= 0 &&
          (positionY + 2) <= 7 &&
          isKillable((positionX - 1), (positionY + 2))
        )
      ) {
        posibleMoves[positionX - 1][positionY + 2] = true;
      }
      if (
        (
          (positionX + 1) <= 7 &&
          (positionY - 2) >= 0 &&
          isEmpty((positionX + 1), (positionY - 2))
        ) ||
        (
          (positionX + 1) <= 7 &&
          (positionY - 2) >= 0 &&
          isKillable((positionX + 1), (positionY - 2))
        )
      ) {
        posibleMoves[positionX + 1][positionY - 2] = true;
      }
      if (
        (
          (positionX - 1) >= 0 &&
          (positionY - 2) >= 0 &&
          isEmpty((positionX - 1), (positionY - 2))
        ) ||
        (
          (positionX - 1) >= 0 &&
          (positionY - 2) >= 0 &&
          isKillable((positionX - 1), (positionY - 2))
        )
      ) {
        posibleMoves[positionX - 1][positionY - 2] = true;
      }
    }

    if (pieceType == "castle") {
      int j = 1;
      for (int i = positionX; i < 7; i++) {
        if (isEmpty(positionX + j, positionY)) {
          posibleMoves[positionX + j][positionY] = true;
        }
        if (isKillable(positionX + j, positionY)) {
          posibleMoves[positionX + j][positionY] = true;
          break;
        }
        if (
          isKillable(positionX + j, positionY) == false &&
          isEmpty(positionX + j, positionY) == false
        ) {
          break;
        }
        j++;
      }
      j = 1;
      for (int i = positionX; i > 0; i--) {
        if (isEmpty(positionX - j, positionY)) {
          posibleMoves[positionX - j][positionY] = true;
        }
        if (isKillable(positionX - j, positionY)) {
          posibleMoves[positionX - j][positionY] = true;
          break;
        }
        if (
          isKillable(positionX - j, positionY) == false &&
          isEmpty(positionX - j, positionY) == false
        ) {
          break;
        }
        j++;
      }
      j = 1;

      for (int i = positionY; i < 7; i++) {
        if (isEmpty(positionX, positionY + j)) {
          posibleMoves[positionX][positionY + j] = true;
        }
        if (isKillable(positionX, positionY + j)) {
          posibleMoves[positionX][positionY + j] = true;
          break;
        }
        if (
          isKillable(positionX, positionY + j) == false &&
          isEmpty(positionX, positionY + j) == false
        ) {
          break;
        }
        j++;
      }
      j = 1;
      for (int i = positionY; i > 0; i--) {
        if (isEmpty(positionX, positionY - j)) {
          posibleMoves[positionX][positionY - j] = true;
        }
        if (isKillable(positionX, positionY - j)) {
          posibleMoves[positionX][positionY - j] = true;
          break;
        }
        if (
          isKillable(positionX, positionY - j) == false &&
          isEmpty(positionX, positionY - j) == false
        ) {
          break;
        }
        j++;
      }
    }
    if (pieceType == "queen") {
      int xStart = positionX;
      int yStart = positionY;
      int j = 1;

      while (xStart < 7 && yStart < 7) {
        if (isEmpty(positionX + j, positionY + j)) {
          posibleMoves[positionX + j][positionY + j] = true;
        }
        if (isKillable(positionX + j, positionY + j)) {
          posibleMoves[positionX + j][positionY + j] = true;
          break;
        }
        if (
          isKillable(positionX + j, positionY + j) == false &&
          isEmpty(positionX + j, positionY + j) == false
        ) {
          break;
        }
        j++;
        xStart++;
        yStart++;
      }
      j = 1;
      xStart = positionX;
      yStart = positionY;
      while (xStart > 0 && yStart > 0) {
        if (isEmpty(positionX - j, positionY - j)) {
          posibleMoves[positionX - j][positionY - j] = true;
        }
        if (isKillable(positionX - j, positionY - j)) {
          posibleMoves[positionX - j][positionY - j] = true;
          break;
        }
        if (
          isKillable(positionX - j, positionY - j) == false &&
          isEmpty(positionX - j, positionY - j) == false
        ) {
          break;
        }
        j++;
        xStart--;
        yStart--;
      }
      j = 1;
      xStart = positionX;
      yStart = positionY;
      while (xStart > 0 && yStart < 7) {
        if (isEmpty(positionX - j, positionY + j)) {
          posibleMoves[positionX - j][positionY + j] = true;
        }
        if (isKillable(positionX - j, positionY + j)) {
          posibleMoves[positionX - j][positionY + j] = true;
          break;
        }
        if (
          isKillable(positionX - j, positionY + j) == false &&
          isEmpty(positionX - j, positionY + j) == false
        ) {
          break;
        }
        j++;
        xStart--;
        yStart++;
      }
      j = 1;
      xStart = positionX;
      yStart = positionY;
      while (xStart < 7 && yStart > 0) {
        if (isEmpty(positionX + j, positionY - j)) {
          posibleMoves[positionX + j][positionY - j] = true;
        }
        if (isKillable(positionX + j, positionY - j)) {
          posibleMoves[positionX + j][positionY - j] = true;
          break;
        }
        if (
          isKillable(positionX + j, positionY - j) == false &&
          isEmpty(positionX + j, positionY - j) == false
        ) {
          break;
        }
        j++;
        xStart++;
        yStart--;
      }
      int k = 1;
      for (int i = positionX; i < 7; i++) {
        if (isEmpty(positionX + k, positionY)) {
          posibleMoves[positionX + k][positionY] = true;
        }
        if (isKillable(positionX + k, positionY)) {
          posibleMoves[positionX + k][positionY] = true;
          break;
        }
        if (
          isKillable(positionX + k, positionY) == false &&
          isEmpty(positionX + k, positionY) == false
        ) {
          break;
        }
        k++;
      }
      k = 1;
      for (int i = positionX; i > 0; i--) {
        if (isEmpty(positionX - k, positionY)) {
          posibleMoves[positionX - k][positionY] = true;
        }
        if (isKillable(positionX - k, positionY)) {
          posibleMoves[positionX - k][positionY] = true;
          break;
        }
        if (
          isKillable(positionX - k, positionY) == false &&
          isEmpty(positionX - k, positionY) == false
        ) {
          break;
        }
        k++;
      }
      k = 1;

      for (int i = positionY; i < 7; i++) {
        if (isEmpty(positionX, positionY + k)) {
          posibleMoves[positionX][positionY + k] = true;
        }
        if (isKillable(positionX, positionY + k)) {
          posibleMoves[positionX][positionY + k] = true;
          break;
        }
        if (
          isKillable(positionX, positionY + k) == false &&
          isEmpty(positionX, positionY + k) == false
        ) {
          break;
        }
        k++;
      }
      k = 1;

      for (int i = positionY; i > 0; i--) {
        if (isEmpty(positionX, positionY - k)) {
          posibleMoves[positionX][positionY - k] = true;
        }
        if (isKillable(positionX, positionY - k)) {
          posibleMoves[positionX][positionY - k] = true;
          break;
        }
        if (
          isKillable(positionX, positionY - k) == false &&
          isEmpty(positionX, positionY - k) == false
        ) {
          break;
        }
        k++;
      }
    }
    if (pieceType == "Elephant") {
      int xStart = positionX;
      int yStart = positionY;
      int j = 1;

      while (xStart < 7 && yStart < 7) {
        if (isEmpty(positionX + j, positionY + j)) {
          posibleMoves[positionX + j][positionY + j] = true;
        }
        if (isKillable(positionX + j, positionY + j)) {
          posibleMoves[positionX + j][positionY + j] = true;
          break;
        }
        if (
          isKillable(positionX + j, positionY + j) == false &&
          isEmpty(positionX + j, positionY + j) == false
        ) {
          break;
        }
        j++;
        xStart++;
        yStart++;
      }
      j = 1;
      xStart = positionX;
      yStart = positionY;
      while (xStart > 0 && yStart > 0) {
        if (isEmpty(positionX - j, positionY - j)) {
          posibleMoves[positionX - j][positionY - j] = true;
        }
        if (isKillable(positionX - j, positionY - j)) {
          posibleMoves[positionX - j][positionY - j] = true;
          break;
        }
        if (
          isKillable(positionX - j, positionY - j) == false &&
          isEmpty(positionX - j, positionY - j) == false
        ) {
          break;
        }
        j++;
        xStart--;
        yStart--;
      }
      j = 1;
      xStart = positionX;
      yStart = positionY;
      while (xStart > 0 && yStart < 7) {
        if (isEmpty(positionX - j, positionY + j)) {
          posibleMoves[positionX - j][positionY + j] = true;
        }
        if (isKillable(positionX - j, positionY + j)) {
          posibleMoves[positionX - j][positionY + j] = true;
          break;
        }
        if (
          isKillable(positionX - j, positionY + j) == false &&
          isEmpty(positionX - j, positionY + j) == false
        ) {
          break;
        }
        j++;
        xStart--;
        yStart++;
      }
      j = 1;
      xStart = positionX;
      yStart = positionY;
      while (xStart < 7 && yStart > 0) {
        if (isEmpty(positionX + j, positionY - j)) {
          posibleMoves[positionX + j][positionY - j] = true;
        }
        if (isKillable(positionX + j, positionY - j)) {
          posibleMoves[positionX + j][positionY - j] = true;
          break;
        }
        if (
          isKillable(positionX + j, positionY - j) == false &&
          isEmpty(positionX + j, positionY - j) == false
        ) {
          break;
        }
        j++;
        xStart++;
        yStart--;
      }
    }
    if (color == "black") {
      if (pieceType == "solider") {
        if (positionX == 1) {
          if (isEmpty(positionX + 1, positionY)) {
            posibleMoves[(positionX + 1)][positionY] = true;
            if (isEmpty(positionX + 2, positionY)) {
              posibleMoves[(positionX + 2)][positionY] = true;
            }
          }
          if (positionY != 0) {
            if (isKillable((positionX + 1), (positionY - 1))) {
              posibleMoves[(positionX + 1)][(positionY - 1)] = true;
            }
          }
          if (positionY != 7) {
            if (isKillable((positionX + 1), (positionY + 1))) {
              posibleMoves[(positionX + 1)][(positionY + 1)] = true;
            }
          }
        }
        if (positionX != 1 && positionX != 7) {
          if (isEmpty((positionX + 1), positionY)) {
            posibleMoves[(positionX + 1)][positionY] = true;
          }
          if (positionY != 0) {
            if (isKillable((positionX + 1), (positionY - 1))) {
              posibleMoves[(positionX + 1)][(positionY - 1)] = true;
            }
          }
          if (positionY != 7) {
            if (isKillable((positionX + 1), (positionY + 1))) {
              posibleMoves[(positionX + 1)][(positionY + 1)] = true;
            }
          }
        }
      }
    }
    if (color == "white") {
      if (pieceType == "solider") {
        if (positionX == 6) {
          if (isEmpty(positionX - 1, positionY)) {
            posibleMoves[(positionX - 1)][positionY] = true;
            if (isEmpty(positionX - 2, positionY)) {
              posibleMoves[(positionX - 2)][positionY] = true;
            }
          }
          if (positionY != 0) {
            if (isKillable((positionX - 1), (positionY - 1))) {
              posibleMoves[(positionX - 1)][(positionY - 1)] = true;
            }
          }
          if (positionY != 7) {
            if (isKillable((positionX - 1), (positionY + 1))) {
              posibleMoves[(positionX - 1)][(positionY + 1)] = true;
            }
          }
        }
        if (positionX != 6 && positionX != 0) {
          if (isEmpty(positionX - 1, positionY)) {
            posibleMoves[(positionX - 1)][positionY] = true;
          }
          if (positionY != 0) {
            if (isKillable((positionX - 1), (positionY - 1))) {
              posibleMoves[(positionX - 1)][(positionY - 1)] = true;
            }
          }
          if (positionY != 7) {
            if (isKillable((positionX - 1), (positionY + 1))) {
              posibleMoves[(positionX - 1)][(positionY + 1)] = true;
            }
          }
        }
      }
    }
  }

  boolean isKillable(int x, int y) {
    boolean result = false;
    if ((x >= 0 && x <= 7) && (y >= 0 && y <= 7)) {
      for (int i = 0; i <= 15; i++) {
        if (color == "white") {
          if (Chess.board[x][y] == Black.piece[i].label) {
            result = true;
          }
        } else {
          if (Chess.board[x][y] == White.piece[i].label) {
            result = true;
          }
        }
      }
    }

    return result;
  }

  boolean isPlayable() {
    boolean result = false;
    for (int i = 0; i <= 7; i++) {
      for (int j = 0; j <= 7; j++) {
        if (posibleMoves[i][j]) {
          result = true;
        }
      }
    }
    return result;
  }

  void clearPosibMov() {
    for (int i = 0; i <= 7; i++) {
      for (int j = 0; j <= 7; j++) {
        posibleMoves[i][j] = false;
      }
    }
  }

  Piece(
    int positionX,
    int positionY,
    boolean isDead,
    String label,
    String partType,
    String color
  ) {
    this.positionX = positionX;
    this.label = label;
    this.positionY = positionY;
    this.isDead = isDead;
    this.pieceType = partType;
    this.color = color;
  }
}

class White extends Piece {
  public static White[] piece = new White[16];

  static White solider1 = new White(
    6,
    0,
    false,
    " w-sol-1 ",
    "solider",
    "white"
  );
  static White solider2 = new White(
    6,
    1,
    false,
    " w-sol-2 ",
    "solider",
    "white"
  );
  static White solider3 = new White(
    6,
    2,
    false,
    " w-sol-3 ",
    "solider",
    "white"
  );
  static White solider4 = new White(
    6,
    3,
    false,
    " w-sol-4 ",
    "solider",
    "white"
  );
  static White solider5 = new White(
    6,
    4,
    false,
    " w-sol-5 ",
    "solider",
    "white"
  );
  static White solider6 = new White(
    6,
    5,
    false,
    " w-sol-6 ",
    "solider",
    "white"
  );
  static White solider7 = new White(
    6,
    6,
    false,
    " w-sol-7 ",
    "solider",
    "white"
  );
  static White solider8 = new White(
    6,
    7,
    false,
    " w-sol-8 ",
    "solider",
    "white"
  );
  static White castle1 = new White(7, 0, false, " w-cas-1 ", "castle", "white");
  static White castle2 = new White(7, 7, false, " w-cas-2 ", "castle", "white");
  static White horse1 = new White(7, 1, false, " w-hrs-1 ", "horse", "white");
  static White horse2 = new White(7, 6, false, " w-hrs-2 ", "horse", "white");
  static White elph1 = new White(7, 2, false, " w-elf-1 ", "Elephant", "white");
  static White elph2 = new White(7, 5, false, " w-elf-2 ", "Elephant", "white");
  static White queen = new White(7, 3, false, " w-queen ", "queen", "white");
  static White king = new White(7, 4, false, " w-king  ", "king", "white");

  static int whitePosition(int entry) {
    int result = 0;
    switch (entry) {
      case 1:
        result = 8;
        break;
      case 2:
        result = 7;
        break;
      case 3:
        result = 6;
        break;
      case 4:
        result = 5;
        break;
      case 5:
        result = 4;
        break;
      case 6:
        result = 3;
        break;
      case 7:
        result = 2;
        break;
      case 8:
        result = 1;
        break;
    }
    return result;
  }

  void play() {
    boolean done = false;
    if (isDead) {
      System.out.println("This Piece is dead!");
      System.out.println();
      done = true;
    }
    if (isPlayable() == false) {
      System.out.println("You can't play this piece");
      System.out.println();
      done = true;
    }
    while (done == false) {
      System.out.println();
      System.out.println("Specify the the position horizontally:");
      int xSelection = input.nextInt();
      System.out.println();
      System.out.println("Specify the position vertically:");
      int ySelection = input.nextInt();

      xSelection = whitePosition(xSelection);

      if (
        xSelection <= 8 && xSelection >= 1 && ySelection <= 8 && ySelection >= 1
      ) {
        if (posibleMoves[xSelection - 1][ySelection - 1]) {
          deadPiece = setIsDead(xSelection - 1, ySelection - 1);
          move(xSelection - 1, ySelection - 1);
          if (pieceType == "king" || pieceType == "castle") {
            isPlayed = true;
          }
          done = true;
          Chess.whiteDone = true;
        } else {
          System.out.println();
          System.out.println("Invalid move.");
        }
      } else {
        System.out.println();
        System.out.println("Out of the range.");
      }
    }
  }

  String setIsDead(int xSelection, int ySelection) {
    String result = "";
    for (int i = 0; i <= 15; i++) {
      if (Chess.board[xSelection][ySelection] == Black.piece[i].label) {
        result = Black.piece[i].label;
      }
    }

    return result;
  }

  void setSoliderCheckMate() {
    if (pieceType != "solider" && isDead == false) { // in transforming case
      for (int i = 0; i <= 7; i++) {
        for (int j = 0; j <= 7; j++) {
          if (posibleMoves[i][j]) {
            Chess.blackCheckMateIndexes[i][j] = true;
          }
        }
      }
    }
    if (pieceType == "solider" && isDead == false) {
      if (positionY != 0) {
        if ((positionX - 1) >= 0) {
          Chess.blackCheckMateIndexes[positionX - 1][positionY - 1] = true;
        }
      }
      if (positionY != 7) {
        if ((positionX - 1) >= 0) {
          Chess.blackCheckMateIndexes[positionX - 1][positionY + 1] = true;
        }
      }
    }
  }

  void soliderTransform() {
    if (isTransformed == false && positionX == 0) {
      if (positionX == 0) {
        boolean done = false;
        while (done == false) {
          System.out.println();

          System.out.println("  Which Piece you do want to transform to ? ");
          System.out.println("  1-Queen  2-Castle  3-Elephant   4-Horse  ");
          System.out.println();

          int selection = input.nextInt();
          if (selection == 1) {
            queenCount++;
            label = " w-queen" + queenCount;
            pieceType = "queen";
            Chess.board[positionX][positionY] = label;
            done = true;
            isTransformed = true;
            menuLabel = soliderOrder + "-Queen-" + castleCount + "  ";
          } else if (selection == 2) {
            castleCount++;
            label = " w-cas-" + castleCount + " ";
            Chess.board[positionX][positionY] = label;
            pieceType = "castle";
            done = true;
            isTransformed = true;
            menuLabel = soliderOrder + "-Castle-" + castleCount + "  ";
          } else if (selection == 3) {
            elephantCount++;
            label = " w-elf-" + elephantCount + " ";
            Chess.board[positionX][positionY] = label;
            pieceType = "Elephant";
            done = true;
            isTransformed = true;
            menuLabel = soliderOrder + "-Elephant-" + elephantCount + "  ";
          } else if (selection == 4) {
            horseCount++;
            label = " w-hrs-" + horseCount + " ";
            Chess.board[positionX][positionY] = label;
            pieceType = "horse";
            done = true;
            isTransformed = true;
            menuLabel = soliderOrder + "-Horse-" + horseCount + "  ";
          } else {
            System.out.println();
            System.out.println("Invalid entry.");
            System.out.println();
          }
        }
      }
    }
  }

  void setPosMovWithCheckMate() { // this method prevents the moves that leads to checkmate
    boolean tmpDead[] = new boolean[15];
    for (int piece = 0; piece <= 14; piece++) {
      tmpDead[piece] = Black.piece[piece].isDead;
    }

    int tmpPositionX = positionX;
    int tmpPositionY = positionY;

    boolean tmpWhiteCheck = Chess.whiteCheckMate;

    String[][] tmpBoard = new String[8][8];

    for (int i = 0; i <= 7; i++) {
      for (int j = 0; j <= 7; j++) {
        tmpBoard[i][j] = Chess.board[i][j];
      }
    }

    boolean[][] tmpPosibMov = new boolean[8][8];

    for (int i = 0; i <= 7; i++) {
      for (int j = 0; j <= 7; j++) {
        if (posibleMoves[i][j]) {
          for (int k = 0; k <= 15; k++) {
            if (Chess.board[i][j] == Black.piece[k].label) {
              Black.piece[k].isDead = true;
              Black.piece[k].clearPosibMov();
            }
          }
          move(i, j);

          Chess.blackRefresh();
          Chess.setWhiteCheckMate();

          if (
            !Chess.whiteCheckMateIndexes[king.getPositionX()][king.getPositionY()]
          ) {
            tmpPosibMov[i][j] = true;
          } else {
            tmpPosibMov[i][j] = false;
          }

          positionX = tmpPositionX;
          positionY = tmpPositionY;

          for (int ik = 0; ik <= 7; ik++) {
            for (int jk = 0; jk <= 7; jk++) {
              Chess.board[ik][jk] = tmpBoard[ik][jk];
            }
          }

          for (int piece = 0; piece <= 14; piece++) {
            Black.piece[piece].isDead = tmpDead[piece];
          }

          Chess.blackRefresh();
          Chess.setWhiteCheckMate();
        }
      }
    }
    for (int i = 0; i <= 7; i++) {
      for (int j = 0; j <= 7; j++) {
        posibleMoves[i][j] = tmpPosibMov[i][j];
      }
    }
    Chess.whiteCheckMate = tmpWhiteCheck;
  }

  static void kingChangePosition(int selection) {
    if (selection == 1) {
      king.move(7, 6);
      castle2.move(7, 5);
    }
    if (selection == 2) {
      king.move(7, 1);
      castle1.move(7, 2);
    }
  }

  White(
    int positionX,
    int positionY,
    boolean isDead,
    String label,
    String pieceType,
    String color
  ) {
    super(positionX, positionY, isDead, label, pieceType, color);
  }
}

class Black extends Piece {
  static int elephantCount = 2;
  static int castleCount = 2;
  static int horseCount = 2;
  static int queenCount = 1;
  static Black king = new Black(0, 4, false, " b-king  ", "king", "black");
  static Black queen = new Black(0, 3, false, " b-queen ", "queen", "black");
  static Black solider1 = new Black(
    1,
    0,
    false,
    " b-sol-1 ",
    "solider",
    "black"
  );
  static Black solider2 = new Black(
    1,
    1,
    false,
    " b-sol-2 ",
    "solider",
    "black"
  );
  static Black solider3 = new Black(
    1,
    2,
    false,
    " b-sol-3 ",
    "solider",
    "black"
  );
  static Black solider4 = new Black(
    1,
    3,
    false,
    " b-sol-4 ",
    "solider",
    "black"
  );
  static Black solider5 = new Black(
    1,
    4,
    false,
    " b-sol-5 ",
    "solider",
    "black"
  );
  static Black solider6 = new Black(
    1,
    5,
    false,
    " b-sol-6 ",
    "solider",
    "black"
  );
  static Black solider7 = new Black(
    1,
    6,
    false,
    " b-sol-7 ",
    "solider",
    "black"
  );
  static Black solider8 = new Black(
    1,
    7,
    false,
    " b-sol-8 ",
    "solider",
    "black"
  );
  static Black horse1 = new Black(0, 1, false, " b-hrs-1 ", "horse", "black");
  static Black horse2 = new Black(0, 6, false, " b-hrs-2 ", "horse", "black");
  static Black elph1 = new Black(0, 2, false, " b-elf-1 ", "Elephant", "black");
  static Black elph2 = new Black(0, 5, false, " b-elf-2 ", "Elephant", "black");
  static Black castle1 = new Black(0, 0, false, " b-cas-1 ", "castle", "black");
  static Black castle2 = new Black(0, 7, false, " b-cas-2 ", "castle", "black");
  static Black piece[] = new Black[16];

  void play() {
    boolean done = false;
    if (isDead) {
      System.out.println("This Piece is dead!");
      System.out.println();
      done = true;
    }
    if (isPlayable() == false) {
      System.out.println("You can't play this piece");
      System.out.println();
      done = true;
    }
    while (done == false) {
      System.out.println();

      System.out.println("Specify the the position horizontally:");
      int xSelection = input.nextInt();
      System.out.println();

      System.out.println("Specify the position vertically:");
      int ySelection = input.nextInt();

      if (
        xSelection <= 8 && xSelection >= 1 && ySelection <= 8 && ySelection >= 1
      ) {
        if (posibleMoves[xSelection - 1][ySelection - 1]) {
          deadPiece = setIsDead(xSelection - 1, ySelection - 1);
          move(xSelection - 1, ySelection - 1);
          if (pieceType == "king" || pieceType == "castle") {
            isPlayed = true;
          }
          done = true;
          Chess.blackDone = true;
        } else {
          System.out.println();
          System.out.println("Invalid move.");
        }
      } else {
        System.out.println();
        System.out.println("Out of the range.");
      }
    }
  }

  String setIsDead(int xSelection, int ySelection) {
    String result = "";
    for (int i = 0; i <= 15; i++) {
      if (Chess.board[xSelection][ySelection] == White.piece[i].label) {
        result = White.piece[i].label;
      }
    }

    return result;
  }

  void setSoliderCheckMate() {
    if (pieceType != "solider") {
      for (int i = 0; i <= 7; i++) {
        for (int j = 0; j <= 7; j++) {
          if (posibleMoves[i][j]) {
            Chess.whiteCheckMateIndexes[i][j] = true;
          }
        }
      }
    }
    if (pieceType == "solider" && isDead == false) {
      if (positionY != 0) {
        if ((positionX + 1) <= 7) {
          Chess.whiteCheckMateIndexes[positionX + 1][positionY - 1] = true;
        }
      }
      if (positionY != 7) {
        if ((positionX + 1) <= 7) {
          Chess.whiteCheckMateIndexes[positionX + 1][positionY + 1] = true;
        }
      }
    }
  }

  void soliderTransform() {
    if (isTransformed == false && positionX == 7) {
      boolean done = false;

      while (done == false) {
        System.out.println();
        System.out.println("Which Piece do you want to transform to ?");
        System.out.println("1-Queen  2-Castle  3-Elephant   4-Horse  ");
        System.out.println();
        int selection = input.nextInt();

        if (selection == 1) {
          queenCount++;
          label = " b-queen" + queenCount;
          Chess.board[positionX][positionY] = label;
          pieceType = "queen";
          done = true;
          isTransformed = true;
          menuLabel = soliderOrder + "-Queen-" + queenCount + "  ";
        } else if (selection == 2) {
          castleCount++;
          label = " b-cas-" + castleCount + " ";
          Chess.board[positionX][positionY] = label;
          pieceType = "castle";
          done = true;
          isTransformed = true;
          menuLabel = soliderOrder + "-Castle-" + castleCount + "  ";
        } else if (selection == 3) {
          elephantCount++;
          label = " b-elf-" + elephantCount + " ";
          Chess.board[positionX][positionY] = label;
          pieceType = "Elephant";
          isTransformed = true;
          done = true;
          menuLabel = soliderOrder + "-Elephant-" + elephantCount + "  ";
        } else if (selection == 4) {
          horseCount++;
          label = " b-hrs-" + horseCount + " ";
          Chess.board[positionX][positionY] = label;
          pieceType = "horse";
          isTransformed = true;
          done = true;
          menuLabel = soliderOrder + "-Horse-" + horseCount + "  ";
        } else {
          System.out.println();
          System.out.println("Invalid entry.");
          System.out.println();
        }
      }
    }
  }

  void setPosMovWithCheckMate() { // this method prevents the moves that leads to checkmate
    boolean tmpIsDead[] = new boolean[15];
    for (int piece = 0; piece <= 14; piece++) {
      tmpIsDead[piece] = White.piece[piece].isDead;
    }

    int tmpPositionX = positionX;
    int tmpPositionY = positionY;

    boolean tmpBlackCheck = Chess.blackCheckMate;
    String[][] tmpBoard = new String[8][8];

    for (int i = 0; i <= 7; i++) {
      for (int j = 0; j <= 7; j++) {
        tmpBoard[i][j] = Chess.board[i][j];
      }
    }

    boolean[][] tmpPosibMov = new boolean[8][8];

    for (int i = 0; i <= 7; i++) {
      for (int j = 0; j <= 7; j++) {
        if (posibleMoves[i][j]) {
          for (int k = 0; k <= 15; k++) {
            if (Chess.board[i][j] == White.piece[k].label) {
              White.piece[k].isDead = true;
              White.piece[k].clearPosibMov();
            }
          }
          move(i, j);

          Chess.whiteRefresh();

          Chess.setBlackCheckMate();

          if (
            Chess.blackCheckMateIndexes[king.getPositionX()][king.getPositionY()] ==
            false
          ) {
            tmpPosibMov[i][j] = true;
          } else {
            tmpPosibMov[i][j] = false;
          }
          positionX = tmpPositionX;
          positionY = tmpPositionY;

          for (int ik = 0; ik <= 7; ik++) {
            for (int jk = 0; jk <= 7; jk++) {
              Chess.board[ik][jk] = tmpBoard[ik][jk];
            }
          }
          for (int piece = 0; piece <= 14; piece++) {
            White.piece[piece].isDead = tmpIsDead[piece];
          }
          Chess.whiteRefresh();
          Chess.setBlackCheckMate();
        }
      }
    }
    for (int i = 0; i <= 7; i++) {
      for (int j = 0; j <= 7; j++) {
        posibleMoves[i][j] = tmpPosibMov[i][j];
      }
    }
    Chess.blackCheckMate = tmpBlackCheck;
  }

  static void kingChangePosition(int selection) {
    if (selection == 1) {
      king.move(0, 6);
      castle2.move(0, 5);
    }

    if (selection == 2) {
      king.move(0, 1);
      castle1.move(0, 2);
    }
  }

  Black(
    int positionX,
    int positionY,
    boolean isDead,
    String label,
    String pieceType,
    String color
  ) {
    super(positionX, positionY, isDead, label, pieceType, color);
  }
}
