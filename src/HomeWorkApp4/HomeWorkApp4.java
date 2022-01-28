package HomeWorkApp4;

import java.util.Random;
import java.util.Scanner;

public class HomeWorkApp4 {

    //поле
    public static int fieldSizeX;
    public static int fieldSizeY;
    public static char[][] field;
    public static int seriesWin; // кол-во ячеек для победы

    // игроки
    public static char playerChar = 'X';
    public static char pcChar = 'O';
    public static char emptyChar = '_';

    // ручка
    public static Scanner scanner = new Scanner(System.in);
    public static Random random = new Random();



    public static void main(String[] args) {
        createField();
       StatusField();


        // ходы
        while (true) {

                turnX();
            StatusField();

                if (checkWin(playerChar)) {
                    System.out.println("ИГРОК " + playerChar + " ПОБЕДИЛ!");
                    break;
                }

                if (checkFullField()) {
                    System.out.println("ничья");
                    break;
                }

                turnO();
            StatusField();
                if (checkWin(pcChar)) {
                    System.out.println("ИГРОК " + pcChar + " ПОБЕДИЛ!");
                    break;
                }

                if (checkFullField()) {
                    System.out.println("ничья");
                    break;
                }
            }


            System.out.println(" ");
            System.out.println("ИГРА ОКОНЧЕНА!");
        }

    // поле
    public static void createField() {
        fieldSizeY = 5;
        fieldSizeX = 5;
        seriesWin = 4;
        field = new char[fieldSizeY][fieldSizeX];

        for (int i = 0; i < fieldSizeY; i++) {
            for (int j = 0; j < fieldSizeX; j++) {
                field[i][j] = emptyChar;
            }
        }
    }




    //игрок
    public static void turnX() {
        int x;
        int y;
        do {
            x = getValueFromUser("КООРДИНАТА X") ;
            y = getValueFromUser("КООРДИНАТА Y");
        } while (!checkTurnPlayer(x, y) || !checkEmptyFieldCell(x, y));
        field[y][x] = playerChar;
    }
    public static int getValueFromUser(String msg) {
        System.out.print(msg + " > ");
        return scanner.nextInt() - 1;
    }

    // ход пк
    public static void  turnO() {
        if (turnOWinCell()) {
            return;
        }
        if (turnXWinCell()) {
            return;
        }
        int x;
        int y;
        System.out.println("ХОД ИГРОКА > " + pcChar);
        do {
            x = random.nextInt(fieldSizeX);
            y = random.nextInt(fieldSizeY);
        } while (!checkEmptyFieldCell(x, y));
        field[y][x] = pcChar;
    }

    //ии
    public static boolean turnOWinCell() {
        for (int i = 0; i < fieldSizeY; i++) {
            for (int j = 0; j < fieldSizeX; j++) {
                if (checkEmptyFieldCell(j, i)) {
                    field[i][j] = pcChar;
                    if (checkWin(pcChar)) {
                        return true;
                    }
                    field[i][j] = emptyChar;
                }
            }
        }
        return false;
    }
    public static boolean turnXWinCell() {
        for (int i = 0; i < fieldSizeY; i++) {
            for (int j = 0; j < fieldSizeX; j++) {
                if (checkEmptyFieldCell(j, i)) {
                    field[i][j] = playerChar;
                    if (checkWin(playerChar)) {
                        field[i][j] = pcChar;
                        return true;
                    }
                    field[i][j] = emptyChar;
                }
            }
        }
        return false;
    }


    // проверка на победу
    public static boolean checkWin(char checkChar) {
        for (int i = 0; i < fieldSizeX; i++) {
            for (int j = 0; j < fieldSizeY; j++) {
                // x
                if (checkVector(i, j, 1, 0, seriesWin, checkChar)) {
                    return true;
                }
                //x y
                if (checkVector(i, j, 1, 1, seriesWin, checkChar)) {
                    return true;
                }
                // y
                if (checkVector(i, j, 0, 1, seriesWin, checkChar)) {
                    return true;
                }
                //x -y
                if (checkVector(i, j, 1, -1, seriesWin, checkChar)) {
                    return true;
                }
            }
        }
        return false;
    }

    //  линии
    public static boolean checkVector(int x, int y, int vx, int vy, int len, char checkChar) {
        // конец линии
        int farX = x + (len - 1) * vx;
        int farY = y + (len - 1) * vy;

        if (! checkTurnPlayer(farX, farY)) {
            return false;
        }
        // проверка одинаковых символов в ячейках
        for (int i = 0; i < len; i++) {
            if (field[y + i * vy][x + i * vx] != checkChar) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkFullField() {
        for (int i = 0; i < fieldSizeY; i++) {
            for (int j = 0; j < fieldSizeX; j++) {
                if (field[i][j] == emptyChar) {
                    return false;
                }
            }
        }
        return true;
    }
    //за пределы поля
    public static boolean  checkTurnPlayer(int x, int y) {
        return x >= 0 && x <fieldSizeX && y >= 0 && y <fieldSizeY;
    }
    // пустая ячейка
    public static boolean checkEmptyFieldCell(int x, int y) {
        return field[y][x] == emptyChar;
    }

    public static void  StatusField() {
        System.out.println("---------------------");
        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x < fieldSizeX; x++) {
                System.out.print(field[y][x] + "|");
            }
            System.out.println();
        }
        System.out.println("---------------------");
    }

}

