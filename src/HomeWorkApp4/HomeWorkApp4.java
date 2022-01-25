package HomeWorkApp4;

import java.util.Random;
import java.util.Scanner;

public class HomeWorkApp4 {


    public static int fieldSizeH;
    public static int fieldSizeW;
    public static char[][] field;
    public static int seriesWin;// кол-во ячеек для победы


    // игроки
    public static char playerChar = 'X';
    public static char pcChar = 'O';
    public static char emptyChar = '_';

    // ручка
    public static Scanner scanner = new Scanner(System.in);
    public static Random random = new Random();

    public static void main(String[] args) {
        setupFieldSize();
        setSeriesWin(fieldSizeH);
        createField(fieldSizeH, fieldSizeW);
        currentStatusField();
//ходы игроков
        while (true) {

            turnX();
            currentStatusField();

            if (checkWin(playerChar)) {
                System.out.println("ИГРОК " + playerChar + " ПОБЕДИЛ!");
                break;
            }

            if (checkFullField()) {
                System.out.println("ничья");
                break;
            }

            turnO();
            currentStatusField();
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

    //запрос размера поля
    public static void setupFieldSize() {
        fieldSizeH = getValueFromUser("ВВЕДИТЕ РАЗМЕР ПОЛЯ") + 1;
        fieldSizeW = fieldSizeH;

    }

    //ол-во ячеек для выигрыша
    public static void setSeriesWin(int seriesWin) {
        if (fieldSizeH == 3) {
            seriesWin = 3;
        } else {
            seriesWin = fieldSizeH - 1;
        }
        System.out.println("ЗАПОЛЕНЫХ ЯЧЕЕК ДЛЯ ПОБЕДЫ НУЖНО: " + seriesWin);
    }

    public static void turnX() {

        int x;
        int y;

        do {
            x = getValueFromUser("КООРДИНАТА X");
            y = getValueFromUser("КООРДИНАТА Y");
        } while (!checkTurnPlayer(y, x) || !checkEmptyFieldCell(y, x));

        field[y][x] = playerChar;
    }

    // ХОД ПК
    public static void turnO() {
        int x;
        int y;
        System.out.println("ХОД ИГРОКА > " + pcChar);

        do {
            x = random.nextInt(fieldSizeW);
            y = random.nextInt(fieldSizeH);
        } while (!checkEmptyFieldCell(y, x));

        field[y][x] = pcChar;

    }

    public static int getValueFromUser(String msg) {
        System.out.print(msg + " > ");
        return scanner.nextInt() - 1;
    }

    public static void createField(int height, int wight) {
        field = new char[height][wight];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < wight; x++) {
                field[y][x] = emptyChar;
            }
        }
    }

    public static void currentStatusField() {
        System.out.println("------------------");
        for (int y = 0; y < fieldSizeH; y++) {
            for (int x = 0; x < fieldSizeW; x++) {
                System.out.print(field[y][x] + "|");
            }
            System.out.println();
        }
        System.out.println("-----------------");
    }

    public static boolean checkEmptyFieldCell(int y, int x) {
        return field[y][x] == emptyChar;
    }

    public static boolean checkTurnPlayer(int y, int x) {
        return x >= 0 && x < fieldSizeW && y >= 0 && y < fieldSizeH;
    }

    public static boolean checkWin(char checkChar) {

//        if (field[0][0] == checkChar && field[0][1] == checkChar && field[0][2] == checkChar) return true;
//        if (field[1][0] == checkChar && field[1][1] == checkChar && field[1][2] == checkChar) return true;
//        if (field[2][0] == checkChar && field[2][1] == checkChar && field[2][2] == checkChar) return true;
//
//        if (field[0][0] == checkChar && field[1][0] == checkChar && field[2][0] == checkChar) return true;
//        if (field[0][1] == checkChar && field[1][1] == checkChar && field[2][1] == checkChar) return true;
//        if (field[0][2] == checkChar && field[1][2] == checkChar && field[2][2] == checkChar) return true;
//
//        if (field[0][0] == checkChar && field[1][1] == checkChar && field[2][2] == checkChar) return true;
//        if (field[0][2] == checkChar && field[1][1] == checkChar && field[2][0] == checkChar) return true;
//
//        return false;

        // горизонталь-вертикаль

        boolean ver = true; // логическа операция между i j (&=)
        boolean hor = true;
        boolean right = true;
        boolean left = true;

        for (int i = 0; i < seriesWin; i++) {
            for (int j = 0; j < seriesWin; j++) {
                ver &= (field[i][j] == checkChar);
                hor &= (field[j][i] == checkChar);
            }
            if (ver || hor) return true;  // проверка всех остальных столбцов и строк

            return false;
        }

        //диагональ
        for (int i = 0; i < seriesWin; i++){
                right &= (field[i][i] == checkChar);
                left &= (field[seriesWin - i - 1][i] == checkChar);
            }
            if (right || left) return true;
            return false;
        }


        public static boolean checkFullField () {
            for (int y = 0; y < fieldSizeH; y++) {
                for (int x = 0; x < fieldSizeW; x++) {
                    if (field[y][x] == emptyChar) {
                        return false;
                    }
                }
            }
            return true;
        }


    }


