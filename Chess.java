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
    Black.piece[0] = Black.pawn1;
    Black.piece[1] = Black.pawn2;
    Black.piece[2] = Black.pawn3;
    Black.piece[3] = Black.pawn4;
    Black.piece[4] = Black.pawn5;
    Black.piece[5] = Black.pawn6;
    Black.piece[6] = Black.pawn7;
    Black.piece[7] = Black.pawn8;

    Black.piece[8] = Black.rook1;
    Black.piece[9] = Black.rook2;
    Black.piece[10] = Black.knight1;
    Black.piece[11] = Black.knight2;
    Black.piece[12] = Black.bishop1;
    Black.piece[13] = Black.bishop2;
    Black.piece[14] = Black.queen;
    Black.piece[15] = Black.king;

    White.piece[0] = White.pawn1;
    White.piece[1] = White.pawn2;
    White.piece[2] = White.pawn3;
    White.piece[3] = White.pawn4;
    White.piece[4] = White.pawn5;
    White.piece[5] = White.pawn6;
    White.piece[6] = White.pawn7;
    White.piece[7] = White.pawn8;

    White.piece[8] = White.rook1;
    White.piece[9] = White.rook2;
    White.piece[10] = White.knight1;
    White.piece[11] = White.knight2;
    White.piece[12] = White.bishop1;
    White.piece[13] = White.bishop2;
    White.piece[14] = White.queen;
    White.piece[15] = White.king;

    for (int i = 0; i <= 7; i++) {
      White.piece[i].menuLabel = (i + 1) + "-" + White.piece[i].pieceType + "-" + (i + 1) + "  ";
      Black.piece[i].menuLabel = (i + 1) + "-" + Black.piece[i].pieceType + "-" + (i + 1) + "  ";
      Black.piece[i].pawnOrder = i + 1;
      White.piece[i].pawnOrder = i + 1;
    }
    White.rook1.menuLabel = " 9-rook-1  ";
    White.rook2.menuLabel = " 10-rook-2  ";
    White.knight1.menuLabel = " 11-knight-1  ";
    White.knight2.menuLabel = " 12-knight-2  ";
    White.bishop1.menuLabel = " 13-Bishop-1  ";
    White.bishop2.menuLabel = " 14-Bishop-2  ";
    White.queen.menuLabel = " 15-Queen  ";
    White.king.menuLabel = " 16-King  ";

    Black.rook1.menuLabel = " 9-rook-1  ";
    Black.rook2.menuLabel = " 10-rook-2  ";
    Black.knight1.menuLabel = " 11-knight-1  ";
    Black.knight2.menuLabel = " 12-knight-2  ";
    Black.bishop1.menuLabel = " 13-Bishop-1  ";
    Black.bishop2.menuLabel = " 14-Bishop-2  ";
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

    board[7][1] = White.knight1.label;
    board[7][6] = White.knight2.label;
    board[7][2] = White.bishop1.label;
    board[7][5] = White.bishop2.label;
    board[7][0] = White.rook1.label;
    board[7][7] = White.rook2.label;
    board[7][3] = White.queen.label;
    board[7][4] = White.king.label;
    board[6][0] = White.pawn1.label;
    board[6][1] = White.pawn2.label;
    board[6][2] = White.pawn3.label;
    board[6][3] = White.pawn4.label;
    board[6][4] = White.pawn5.label;
    board[6][5] = White.pawn6.label;
    board[6][6] = White.pawn7.label;
    board[6][7] = White.pawn8.label;
    // black
    board[0][1] = Black.knight1.label;
    board[0][6] = Black.knight2.label;
    board[0][2] = Black.bishop1.label;
    board[0][5] = Black.bishop2.label;
    board[0][0] = Black.rook1.label;
    board[0][7] = Black.rook2.label;
    board[0][3] = Black.queen.label;
    board[0][4] = Black.king.label;
    board[1][0] = Black.pawn1.label;
    board[1][1] = Black.pawn2.label;
    board[1][2] = Black.pawn3.label;
    board[1][3] = Black.pawn4.label;
    board[1][4] = Black.pawn5.label;
    board[1][5] = Black.pawn6.label;
    board[1][6] = Black.pawn7.label;
    board[1][7] = Black.pawn8.label;

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
            "CHECKMATE!! White king is DEAD!! | Black Player is the WINNER!!");
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

        if (White.king.isPlayed == false &&
            White.rook2.isPlayed == false &&
            White.rook2.isDead == false &&
            whiteCheckMate == false &&
            Piece.isEmpty(7, 6) &&
            whiteCheckMateIndexes[7][6] != true &&
            Piece.isEmpty(7, 5)) {
          System.out.print(" 17-Protect the King by rook2");
        }
        if (White.king.isPlayed == false &&
            White.rook1.isPlayed == false &&
            White.rook1.isDead == false &&
            whiteCheckMate == false &&
            Piece.isEmpty(7, 1) &&
            whiteCheckMateIndexes[7][1] != true &&
            Piece.isEmpty(7, 2) &&
            Piece.isEmpty(7, 3)) {
          System.out.print(" 18-Protect the King by rook1");
        }
        System.out.println();

        int selection = input.nextInt();
        if (selection >= 1 && selection <= 16) {
          White.piece[selection - 1].play();
        } else if (selection == 17) {
          if (White.king.isPlayed == false &&
              White.rook2.isPlayed == false &&
              White.rook2.isDead == false &&
              whiteCheckMate == false &&
              Piece.isEmpty(7, 6) &&
              Piece.isEmpty(7, 5)) {
            White.protectKing(1);
            whiteDone = true;
          } else {
            System.out.println();
            System.out.println("Unavailable");
            System.out.println();
          }
        } else if (selection == 18) {
          if (White.king.isPlayed == false &&
              White.rook1.isPlayed == false &&
              White.rook1.isDead == false &&
              whiteCheckMate == false &&
              Piece.isEmpty(7, 1) &&
              Piece.isEmpty(7, 2) &&
              Piece.isEmpty(7, 3)) {
            White.protectKing(2);
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
        White.piece[i].pawnTransform();
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
            "CHECKMATE!! Black king is DEAD!! | White Player is the WINNER!!");
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

        if (Black.king.isPlayed == false &&
            Black.rook2.isPlayed == false &&
            Black.rook2.isDead == false &&
            blackCheckMate == false &&
            Piece.isEmpty(0, 6) &&
            blackCheckMateIndexes[0][6] != true &&
            Piece.isEmpty(0, 5)) {
          System.out.print(" 17-Protect the King by rook2");
        }
        if (Black.king.isPlayed == false &&
            Black.rook1.isPlayed == false &&
            Black.rook1.isDead == false &&
            blackCheckMate == false &&
            Piece.isEmpty(0, 1) &&
            blackCheckMateIndexes[0][1] != true &&
            Piece.isEmpty(0, 2) &&
            Piece.isEmpty(0, 3)) {
          System.out.print(" 18-Protect the King by rook1");
        }
        System.out.println();

        int selection = input.nextInt();

        if (selection >= 1 && selection <= 16) {
          Black.piece[selection - 1].play();
        } else if (selection == 17) {
          if (Black.king.isPlayed == false &&
              Black.rook2.isPlayed == false &&
              Black.rook2.isDead == false &&
              blackCheckMate == false &&
              Piece.isEmpty(0, 6) &&
              Piece.isEmpty(0, 5)) {
            Black.protectKing(1);
            blackDone = true;
          } else {
            System.out.println();
            System.out.println("Unavailable");
            System.out.println();
          }
        } else if (selection == 18) {
          if (Black.king.isPlayed == false &&
              Black.rook1.isPlayed == false &&
              Black.rook1.isDead == false &&
              blackCheckMate == false &&
              Piece.isEmpty(0, 1) &&
              Piece.isEmpty(0, 2) &&
              Piece.isEmpty(0, 3)) {
            Black.protectKing(2);
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
        Black.piece[i].pawnTransform();
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
      White.piece[i].setCheckMateByPawn();
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
      Black.piece[i].setCheckMateByPawn();
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
        "         1         2         3         4         5         6         7        8      ");
    System.out.println(
        "     ------------------------------------------------------------------------------");
    for (int i = 0; i <= 7; i++) {
      System.out.println(
          "    |         |         |         |         |         |         |         |         |");
      System.out.print((i * -1 + 8) + "   |");
      for (int j = 0; j <= 7; j++) {
        System.out.print(board[i][j] + "|");
      }

      System.out.println("   " + ((i * -1) + 8));
      System.out.println(
          "    |         |         |         |         |         |         |         |         |");
      System.out.println(
          "     ------------------------------------------------------------------------------");
    }
    System.out.println(
        "         1         2         3         4         5         6         7        8      ");
    System.out.println();
    System.out.println();

    System.out.println(
        "=====================================================================================================");
    System.out.println();
  }

  static final void printBlackBoard() {
    System.out.println(
        "         1         2         3         4         5         6         7        8      ");
    System.out.println(
        "     ------------------------------------------------------------------------------");
    for (int i = 7; i >= 0; i--) {
      System.out.println(
          "    |         |         |         |         |         |         |         |         |");
      System.out.print((i + 1) + "   |");
      for (int j = 0; j <= 7; j++) {
        System.out.print(board[i][j] + "|");
      }

      System.out.println("   " + (i + 1));
      System.out.println(
          "    |         |         |         |         |         |         |         |         |");
      System.out.println(
          "     ------------------------------------------------------------------------------");
    }
    System.out.println(
        "         1         2         3         4         5         6         7        8      ");
    System.out.println();
    System.out.println();

    System.out.println(
        "=====================================================================================================");
    System.out.println();
  }

  static boolean getWhiteCheckMate() {
    boolean result = false;
    if (whiteCheckMateIndexes[White.king.getPositionX()][White.king.getPositionY()]) {
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
    if (blackCheckMateIndexes[Black.king.getPositionX()][Black.king.getPositionY()]) {
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
  int pawnOrder;
  static String deadPiece;
  boolean isPlayed;
  String color;

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
    Chess.board[positionX][positionY] = Chess.defaultBoard[positionX][positionY];
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
      if ((((positionX + 1) <= 7 &&
          (positionY + 1) <= 7 &&
          isEmpty((positionX + 1), (positionY + 1)))) ||
          (((positionX + 1) <= 7 &&
              (positionY + 1) <= 7 &&
              isKillable((positionX + 1), (positionY + 1))))) {
        posibleMoves[positionX + 1][positionY + 1] = true;
      }
      if ((((positionX + 1) <= 7 &&
          (positionY - 1) >= 0 &&
          isEmpty((positionX + 1), (positionY - 1)))) ||
          (((positionX + 1) <= 7 &&
              (positionY - 1) >= 0 &&
              isKillable((positionX + 1), (positionY - 1))))) {
        posibleMoves[positionX + 1][positionY - 1] = true;
      }
      if ((((positionX - 1) >= 0 &&
          (positionY + 1) <= 7 &&
          isEmpty((positionX - 1), (positionY + 1)))) ||
          (((positionX - 1) >= 0 &&
              (positionY + 1) <= 7 &&
              isKillable((positionX - 1), (positionY + 1))))) {
        posibleMoves[positionX - 1][positionY + 1] = true;
      }
      if ((((positionX - 1) >= 0 &&
          (positionY - 1) >= 0 &&
          isEmpty((positionX - 1), (positionY - 1)))) ||
          (((positionX - 1) >= 0 &&
              (positionY - 1) >= 0 &&
              isKillable((positionX - 1), (positionY - 1))))) {
        posibleMoves[positionX - 1][positionY - 1] = true;
      }
      if ((((positionX + 1) <= 7 && isEmpty((positionX + 1), (positionY)))) ||
          (((positionX + 1) <= 7 && isKillable((positionX + 1), (positionY))))) {
        posibleMoves[positionX + 1][positionY] = true;
      }
      if ((((positionY + 1) <= 7 && isEmpty((positionX), (positionY + 1)))) ||
          (((positionY + 1) <= 7 && isKillable((positionX), (positionY + 1))))) {
        posibleMoves[positionX][positionY + 1] = true;
      }
      if ((((positionX - 1) >= 0 && isEmpty((positionX - 1), (positionY)))) ||
          (((positionX - 1) >= 0 && isKillable((positionX - 1), (positionY))))) {
        posibleMoves[positionX - 1][positionY] = true;
      }
      if ((((positionY - 1) >= 0 && isEmpty((positionX), (positionY - 1)))) ||
          (((positionX) >= 0 && isKillable((positionX), (positionY - 1))))) {
        posibleMoves[positionX][positionY - 1] = true;
      }
    }

    if (pieceType == "knight") {
      if (((positionX + 2) <= 7 &&
          (positionY + 1) <= 7 &&
          isEmpty((positionX + 2), (positionY + 1))) ||
          ((positionX + 2) <= 7 &&
              (positionY + 1) <= 7 &&
              isKillable((positionX + 2), (positionY + 1)))) {
        posibleMoves[positionX + 2][positionY + 1] = true;
      }
      if (((positionX + 2) <= 7 &&
          (positionY - 1) >= 0 &&
          isEmpty((positionX + 2), (positionY - 1))) ||
          ((positionX + 2) <= 7 &&
              (positionY - 1) >= 0 &&
              isKillable((positionX + 2), (positionY - 1)))) {
        posibleMoves[positionX + 2][positionY - 1] = true;
      }
      if (((positionX - 2) >= 0 &&
          (positionY + 1) <= 7 &&
          isEmpty((positionX - 2), (positionY + 1))) ||
          ((positionX - 2) >= 0 &&
              (positionY + 1) <= 7 &&
              isKillable((positionX - 2), (positionY + 1)))) {
        posibleMoves[positionX - 2][positionY + 1] = true;
      }
      if (((positionX - 2) >= 0 &&
          (positionY - 1) >= 0 &&
          isEmpty((positionX - 2), (positionY - 1))) ||
          ((positionX - 2) >= 0 &&
              (positionY - 1) >= 0 &&
              isKillable((positionX - 2), (positionY - 1)))) {
        posibleMoves[positionX - 2][positionY - 1] = true;
      }
      if (((positionX + 1) <= 7 &&
          (positionY + 2) <= 7 &&
          isEmpty((positionX + 1), (positionY + 2))) ||
          ((positionX + 1) <= 7 &&
              (positionY + 2) <= 7 &&
              isKillable((positionX + 1), (positionY + 2)))) {
        posibleMoves[positionX + 1][positionY + 2] = true;
      }
      if (((positionX - 1) >= 0 &&
          (positionY + 2) <= 7 &&
          isEmpty((positionX - 1), (positionY + 2))) ||
          ((positionX - 1) >= 0 &&
              (positionY + 2) <= 7 &&
              isKillable((positionX - 1), (positionY + 2)))) {
        posibleMoves[positionX - 1][positionY + 2] = true;
      }
      if (((positionX + 1) <= 7 &&
          (positionY - 2) >= 0 &&
          isEmpty((positionX + 1), (positionY - 2))) ||
          ((positionX + 1) <= 7 &&
              (positionY - 2) >= 0 &&
              isKillable((positionX + 1), (positionY - 2)))) {
        posibleMoves[positionX + 1][positionY - 2] = true;
      }
      if (((positionX - 1) >= 0 &&
          (positionY - 2) >= 0 &&
          isEmpty((positionX - 1), (positionY - 2))) ||
          ((positionX - 1) >= 0 &&
              (positionY - 2) >= 0 &&
              isKillable((positionX - 1), (positionY - 2)))) {
        posibleMoves[positionX - 1][positionY - 2] = true;
      }
    }

    if (pieceType == "rook") {
      int j = 1;
      for (int i = positionX; i < 7; i++) {
        if (isEmpty(positionX + j, positionY)) {
          posibleMoves[positionX + j][positionY] = true;
        }
        if (isKillable(positionX + j, positionY)) {
          posibleMoves[positionX + j][positionY] = true;
          break;
        }
        if (isKillable(positionX + j, positionY) == false &&
            isEmpty(positionX + j, positionY) == false) {
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
        if (isKillable(positionX - j, positionY) == false &&
            isEmpty(positionX - j, positionY) == false) {
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
        if (isKillable(positionX, positionY + j) == false &&
            isEmpty(positionX, positionY + j) == false) {
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
        if (isKillable(positionX, positionY - j) == false &&
            isEmpty(positionX, positionY - j) == false) {
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
        if (isKillable(positionX + j, positionY + j) == false &&
            isEmpty(positionX + j, positionY + j) == false) {
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
        if (isKillable(positionX - j, positionY - j) == false &&
            isEmpty(positionX - j, positionY - j) == false) {
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
        if (isKillable(positionX - j, positionY + j) == false &&
            isEmpty(positionX - j, positionY + j) == false) {
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
        if (isKillable(positionX + j, positionY - j) == false &&
            isEmpty(positionX + j, positionY - j) == false) {
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
        if (isKillable(positionX + k, positionY) == false &&
            isEmpty(positionX + k, positionY) == false) {
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
        if (isKillable(positionX - k, positionY) == false &&
            isEmpty(positionX - k, positionY) == false) {
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
        if (isKillable(positionX, positionY + k) == false &&
            isEmpty(positionX, positionY + k) == false) {
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
        if (isKillable(positionX, positionY - k) == false &&
            isEmpty(positionX, positionY - k) == false) {
          break;
        }
        k++;
      }
    }
    if (pieceType == "bishop") {
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
        if (isKillable(positionX + j, positionY + j) == false &&
            isEmpty(positionX + j, positionY + j) == false) {
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
        if (isKillable(positionX - j, positionY - j) == false &&
            isEmpty(positionX - j, positionY - j) == false) {
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
        if (isKillable(positionX - j, positionY + j) == false &&
            isEmpty(positionX - j, positionY + j) == false) {
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
        if (isKillable(positionX + j, positionY - j) == false &&
            isEmpty(positionX + j, positionY - j) == false) {
          break;
        }
        j++;
        xStart++;
        yStart--;
      }
    }
    if (color == "black" && pieceType == "pawn") {
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
    if (color == "white" && pieceType == "pawn") {
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
      String color) {
    this.positionX = positionX;
    this.label = label;
    this.positionY = positionY;
    this.isDead = isDead;
    this.pieceType = partType;
    this.color = color;
  }
}

class White extends Piece {
  static int knightsCount = 2;
  static int rooksCount = 2;
  static int bishopsCount = 2;
  static int queensCount = 1;
  public static White[] piece = new White[16];

  static White pawn1 = new White(
      6,
      0,
      false,
      " w-pwn-1 ",
      "pawn",
      "white");
  static White pawn2 = new White(
      6,
      1,
      false,
      " w-pwn-2 ",
      "pawn",
      "white");
  static White pawn3 = new White(
      6,
      2,
      false,
      " w-pwn-3 ",
      "pawn",
      "white");
  static White pawn4 = new White(
      6,
      3,
      false,
      " w-pwn-4 ",
      "pawn",
      "white");
  static White pawn5 = new White(
      6,
      4,
      false,
      " w-pwn-5 ",
      "pawn",
      "white");
  static White pawn6 = new White(
      6,
      5,
      false,
      " w-pwn-6 ",
      "pawn",
      "white");
  static White pawn7 = new White(
      6,
      6,
      false,
      " w-pwn-7 ",
      "pawn",
      "white");
  static White pawn8 = new White(
      6,
      7,
      false,
      " w-pwn-8 ",
      "pawn",
      "white");
  static White rook1 = new White(7, 0, false, " w-rok-1 ", "rook", "white");
  static White rook2 = new White(7, 7, false, " w-rok-2 ", "rook", "white");
  static White knight1 = new White(7, 1, false, " w-knt-1 ", "knight", "white");
  static White knight2 = new White(7, 6, false, " w-knt-2 ", "knight", "white");
  static White bishop1 = new White(7, 2, false, " w-bsh-1 ", "bishop", "white");
  static White bishop2 = new White(7, 5, false, " w-bsh-2 ", "bishop", "white");
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
      System.out.println("Specify the the position vertically:");
      int xSelection = input.nextInt();
      System.out.println();
      System.out.println("Specify the position horizontally:");
      int ySelection = input.nextInt();

      xSelection = whitePosition(xSelection);

      if (xSelection <= 8 && xSelection >= 1 && ySelection <= 8 && ySelection >= 1) {
        if (posibleMoves[xSelection - 1][ySelection - 1]) {
          deadPiece = setIsDead(xSelection - 1, ySelection - 1);
          move(xSelection - 1, ySelection - 1);
          if (pieceType == "king" || pieceType == "rook") {
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

  void setCheckMateByPawn() {
    if (pieceType != "pawn" && isDead == false) { // in transforming case
      for (int i = 0; i <= 7; i++) {
        for (int j = 0; j <= 7; j++) {
          if (posibleMoves[i][j]) {
            Chess.blackCheckMateIndexes[i][j] = true;
          }
        }
      }
    }
    if (pieceType == "pawn" && isDead == false) {
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

  void pawnTransform() {
    if (isTransformed == false && positionX == 0) {
      if (positionX == 0) {
        boolean done = false;
        while (done == false) {
          System.out.println();

          System.out.println("  Which Piece you do want to transform to ? ");
          System.out.println("  1-Queen  2-rook  3-Bishop 4-Knight  ");
          System.out.println();

          int selection = input.nextInt();
          if (selection == 1) {
            queensCount++;
            label = " w-queen" + queensCount;
            pieceType = "queen";
            Chess.board[positionX][positionY] = label;
            done = true;
            isTransformed = true;
            menuLabel = pawnOrder + "-Queen-" + rooksCount + "  ";
          } else if (selection == 2) {
            rooksCount++;
            label = " w-rok-" + rooksCount + " ";
            Chess.board[positionX][positionY] = label;
            pieceType = "rook";
            done = true;
            isTransformed = true;
            menuLabel = pawnOrder + "-rook-" + rooksCount + "  ";
          } else if (selection == 3) {
            bishopsCount++;
            label = " w-bsh-" + bishopsCount + " ";
            Chess.board[positionX][positionY] = label;
            pieceType = "bishop";
            done = true;
            isTransformed = true;
            menuLabel = pawnOrder + "-bishop-" + bishopsCount + "  ";
          } else if (selection == 4) {
            knightsCount++;
            label = " w-knt-" + knightsCount + " ";
            Chess.board[positionX][positionY] = label;
            pieceType = "knight";
            done = true;
            isTransformed = true;
            menuLabel = pawnOrder + "-Knight-" + knightsCount + "  ";
          } else {
            System.out.println();
            System.out.println("Invalid entry.");
            System.out.println();
          }
        }
      }
    }
  }

  void setPosMovWithCheckMate() { // this method prevents moves that leads to checkmate
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

          if (!Chess.whiteCheckMateIndexes[king.getPositionX()][king.getPositionY()]) {
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

  static void protectKing(int selection) {
    if (selection == 1) {
      king.move(7, 6);
      rook2.move(7, 5);
    }
    if (selection == 2) {
      king.move(7, 1);
      rook1.move(7, 2);
    }
  }

  White(
      int positionX,
      int positionY,
      boolean isDead,
      String label,
      String pieceType,
      String color) {
    super(positionX, positionY, isDead, label, pieceType, color);
  }
}

class Black extends Piece {
  static int bishopsCount = 2;
  static int rooksCount = 2;
  static int knightsCount = 2;
  static int queensCount = 1;
  static Black king = new Black(0, 4, false, " b-king  ", "king", "black");
  static Black queen = new Black(0, 3, false, " b-queen ", "queen", "black");
  static Black pawn1 = new Black(
      1,
      0,
      false,
      " b-pwn-1 ",
      "pawn",
      "black");
  static Black pawn2 = new Black(
      1,
      1,
      false,
      " b-pwn-2 ",
      "pawn",
      "black");
  static Black pawn3 = new Black(
      1,
      2,
      false,
      " b-pwn-3 ",
      "pawn",
      "black");
  static Black pawn4 = new Black(
      1,
      3,
      false,
      " b-pwn-4 ",
      "pawn",
      "black");
  static Black pawn5 = new Black(
      1,
      4,
      false,
      " b-pwn-5 ",
      "pawn",
      "black");
  static Black pawn6 = new Black(
      1,
      5,
      false,
      " b-pwn-6 ",
      "pawn",
      "black");
  static Black pawn7 = new Black(
      1,
      6,
      false,
      " b-pwn-7 ",
      "pawn",
      "black");
  static Black pawn8 = new Black(
      1,
      7,
      false,
      " b-pwn-8 ",
      "pawn",
      "black");
  static Black knight1 = new Black(0, 1, false, " b-knt-1 ", "knight", "black");
  static Black knight2 = new Black(0, 6, false, " b-knt-2 ", "knight", "black");
  static Black bishop1 = new Black(0, 2, false, " b-bsh-1 ", "bishop", "black");
  static Black bishop2 = new Black(0, 5, false, " b-bsh-2 ", "bishop", "black");
  static Black rook1 = new Black(0, 0, false, " b-rok-1 ", "rook", "black");
  static Black rook2 = new Black(0, 7, false, " b-rok-2 ", "rook", "black");
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

      System.out.println("Specify the the position vertically:");
      int xSelection = input.nextInt();
      System.out.println();

      System.out.println("Specify the position horizontally:");
      int ySelection = input.nextInt();

      if (xSelection <= 8 && xSelection >= 1 && ySelection <= 8 && ySelection >= 1) {
        if (posibleMoves[xSelection - 1][ySelection - 1]) {
          deadPiece = setIsDead(xSelection - 1, ySelection - 1);
          move(xSelection - 1, ySelection - 1);
          if (pieceType == "king" || pieceType == "rook") {
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

  void setCheckMateByPawn() {
    if (pieceType != "pawn") {
      for (int i = 0; i <= 7; i++) {
        for (int j = 0; j <= 7; j++) {
          if (posibleMoves[i][j]) {
            Chess.whiteCheckMateIndexes[i][j] = true;
          }
        }
      }
    }
    if (pieceType == "pawn" && isDead == false) {
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

  void pawnTransform() {
    if (isTransformed == false && positionX == 7) {
      boolean done = false;

      while (done == false) {
        System.out.println();
        System.out.println("Which Piece do you want to transform to ?");
        System.out.println("1-Queen  2-rook  3-Bishop 4-Knight  ");
        System.out.println();
        int selection = input.nextInt();

        if (selection == 1) {
          queensCount++;
          label = " b-queen" + queensCount;
          Chess.board[positionX][positionY] = label;
          pieceType = "queen";
          done = true;
          isTransformed = true;
          menuLabel = pawnOrder + "-Queen-" + queensCount + "  ";
        } else if (selection == 2) {
          rooksCount++;
          label = " b-rok-" + rooksCount + " ";
          Chess.board[positionX][positionY] = label;
          pieceType = "rook";
          done = true;
          isTransformed = true;
          menuLabel = pawnOrder + "-rook-" + rooksCount + "  ";
        } else if (selection == 3) {
          bishopsCount++;
          label = " b-bsh-" + bishopsCount + " ";
          Chess.board[positionX][positionY] = label;
          pieceType = "bishop";
          isTransformed = true;
          done = true;
          menuLabel = pawnOrder + "-bishop-" + bishopsCount + "  ";
        } else if (selection == 4) {
          knightsCount++;
          label = " b-knt-" + knightsCount + " ";
          Chess.board[positionX][positionY] = label;
          pieceType = "knight";
          isTransformed = true;
          done = true;
          menuLabel = pawnOrder + "-Knight-" + knightsCount + "  ";
        } else {
          System.out.println();
          System.out.println("Invalid entry.");
          System.out.println();
        }
      }
    }
  }

  void setPosMovWithCheckMate() { // this method prevents moves that leads to checkmate
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

          if (Chess.blackCheckMateIndexes[king.getPositionX()][king.getPositionY()] == false) {
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

  static void protectKing(int selection) {
    if (selection == 1) {
      king.move(0, 6);
      rook2.move(0, 5);
    }

    if (selection == 2) {
      king.move(0, 1);
      rook1.move(0, 2);
    }
  }

  Black(
      int positionX,
      int positionY,
      boolean isDead,
      String label,
      String pieceType,
      String color) {
    super(positionX, positionY, isDead, label, pieceType, color);
  }
}
