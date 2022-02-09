package HomeWorkApp7;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;


public class GameField extends JPanel {

    private Random random = new Random();

    private GameWindow gameWindow;

    //поле
    private int fieldSizeX;
    private int fieldSizeY;
    private int[][] field;
    private int seriesWin; // кол-во ячеек для победы


    // игроки

    private final int player = 1;
    private final int pc = 2;
    private final int  empty = 0;



    private final int STATE_HUMAN_WIN = 1;
    private final int STATE_AI_WIN = 2;
    private final int STATE_DRAW = 3;



    //сообщения и состояния
    private final String MSG_HUMAN_WIN = "ТЫ ВЫИГРАЛ!";
    private final String MSG_AI_WIN = "ПОРАЖЕНИЕ!";
    private final String MSG_DRAW = "НИЧЬЯ!";

    private int cellWidth;
    private int cellHeight;

    private boolean isExistGameField;
    private boolean isGameOver;


    GameField(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
        setBackground(new Color(183, 239, 207));


        //клик мышки
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent mouse) {
                super.mouseReleased(mouse);
                updater(mouse);
            }
        });

        isExistGameField = false; //начало игры
        isGameOver = true;
    }



    //поле
    void startGame(int fieldSizeX, int fieldSizeY, int seriesWin) {
        this.fieldSizeX = fieldSizeX;
        this.fieldSizeY = fieldSizeY;
        this.seriesWin = seriesWin;

        isExistGameField = true;
        isGameOver = false;

        field = new int[fieldSizeY][fieldSizeX];
        repaint(); //обновить состояние

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        render(g);
    }
    // отрисовка
    private void render(Graphics g) {
        if (!isExistGameField) {
            return;
        }

        paintGameMap(g);

        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x < fieldSizeX; x++) {

                if (checkEmptyFieldCell(x, y)) {
                    continue;
                }

                if (field[y][x] == player) {
                    g.setColor(Color.BLUE);
                    g.fillRect(x * cellWidth + 10, y * cellHeight + 10,
                            cellWidth - 20, cellHeight - 20);
                }

                if (field[y][x] == pc) {
                    g.setColor(Color.PINK);
                    g.fillRect(x * cellWidth + 10, y * cellHeight + 10,
                            cellWidth - 20, cellHeight - 20);
                }
            }
        }

        if (isGameOver) {
            return;
        }
    }
    //отрисовка кликов
    private void updater(MouseEvent mouse) {

        if (isGameOver) {
            return;
        }

        if (!isExistGameField) {
            return;
        }

        int clickX = mouse.getX() / cellWidth;
        int clickY = mouse.getY() / cellHeight;

        if (!checkTurnPlayer(clickX, clickY) || !checkEmptyFieldCell(clickX, clickY)) {
            return;
        }

        field[clickY][clickX] =player;
        gameWindow.saveLog(" ТВОЙ ХОД  В ЯЧЕЙКУ > [" + (clickX + 1) + ":" + (clickY + 1) + "]");

        if (checkWin(player)) {
            setGameOver(STATE_HUMAN_WIN);
            return;
        }

        if (checkFullField()) {
            setGameOver(STATE_DRAW);
            return;
        }

        pcTurn();
        repaint();

        if (checkWin(pc)) {
            setGameOver(STATE_AI_WIN);
            return;
        }

        if (checkFullField()) {
            setGameOver(STATE_DRAW);
            return;
        }
    }

    private void setGameOver(int state) {
        repaint();
        isGameOver = true;

        switch (state) {
            case STATE_HUMAN_WIN:
                gameWindow.saveLog(MSG_HUMAN_WIN);
                JOptionPane.showMessageDialog(this, MSG_HUMAN_WIN); //всплывающее окно
                break;
            case STATE_AI_WIN:
                gameWindow.saveLog(MSG_AI_WIN);
                JOptionPane.showMessageDialog(this, MSG_AI_WIN);
                break;
            case STATE_DRAW:
                gameWindow.saveLog(MSG_DRAW);
                JOptionPane.showMessageDialog(this, MSG_DRAW);
                break;
        }
    }


     //отрисовка поля
    private void paintGameMap(Graphics g) {
        int width = getWidth();
        int height = getHeight();

        cellWidth = width / fieldSizeX;
        cellHeight = height /fieldSizeY;

        g.setColor(new Color(0, 0, 0));

        for (int i = 0; i <= fieldSizeY; i++) {
            int y = i * cellHeight;
            g.drawLine(0, y, width, y); //нарисовать линию
        }
//горизонт
        for (int i = 0; i <= fieldSizeX; i++) {
            int x = i * cellWidth;
            g.drawLine(x, 0, x, height); //нарисовать линию
        }
    }


    // ход пк
    private void  pcTurn() {
        if (turnOWinCell()) {
            return;
        }
        if (turnXWinCell()) {
            return;
        }
        int x;
        int y;
        do {
            x = random.nextInt(fieldSizeX);
            y = random.nextInt(fieldSizeY);
        } while (!checkEmptyFieldCell(x, y));
        field[y][x] = pc;
        gameWindow.saveLog("ХОД ИГРОКА В ЯЧЕЙКУ > [" + (x + 1) + ":" + (y + 1) + "]");
    }

    //ии
    private boolean turnOWinCell() {
        for (int i = 0; i < fieldSizeY; i++) {
            for (int j = 0; j < fieldSizeX; j++) {
                if (checkEmptyFieldCell(j, i)) {
                    field[i][j] = pc;
                    if (checkWin(pc)) {
                        gameWindow.saveLog("AI turn in [" + (j + 1) + ":" + (i + 1) + "]");
                        return true;
                    }
                    field[i][j] = empty;
                }
            }
        }
        return false;
    }
    private  boolean turnXWinCell() {
        for (int i = 0; i < fieldSizeY; i++) {
            for (int j = 0; j < fieldSizeX; j++) {
                if (checkEmptyFieldCell(j, i)) {
                    field[i][j] = player;
                    if (checkWin(player)) {
                        field[i][j] = pc;
                        gameWindow.saveLog("AI turn in [" + (j + 1) + ":" + (i + 1) + "]");
                        return true;
                    }
                    field[i][j] = empty;
                }
            }
        }
        return false;
    }


    // проверка на победу
    private  boolean checkWin(int checkChar) {
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
    private  boolean checkVector(int x, int y, int vx, int vy, int len, int checkChar) {
        // конец линии
        int farX = x + (len - 1) * vx;
        int farY = y + (len - 1) * vy;

        if (!checkTurnPlayer(farX, farY)) {
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

    private  boolean checkFullField() {
        for (int i = 0; i < fieldSizeY; i++) {
            for (int j = 0; j < fieldSizeX; j++) {
                if (field[i][j] == empty) {
                    return false;
                }
            }
        }
        return true;
    }

    //за пределы поля
    private  boolean checkTurnPlayer(int x, int y) {
        return x >= 0 && x < fieldSizeX && y >= 0 && y < fieldSizeY;
    }

    // пустая ячейка
    private  boolean checkEmptyFieldCell(int x, int y) {
        return field[y][x] == empty;
    }


}


