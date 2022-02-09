package HomeWorkApp7;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameWindow extends JFrame {

    //размеры окна
    private int winWidht;
    private int winHeight;

    // где оно появляется при запуске
    private int winPosX;
    private int winPosY;


    //кнопочки
    private JButton buttonStartGame;
    private JButton buttonEndGame;

    private JButton buttonClearLog; //очистка


    private GameField gameField;// игровое поле

    private JPanel panelSettings; // панель настроек общий
    private JPanel panelControls; // панелька для настроек
    // кол-во ячеек для победы
    private JSlider sliderMapSize;



    private JLabel labelMapSize;
    private final String MAP_SIZE_PREFIX = "РАЗМЕР ИГВОГО ПОЛЯ: ";

    // бегунки
    private JSlider sliderWinLength;
    private JLabel labelWinLength;
    private final String WIN_LENGTH_PREFIX = "КОЛЛИЧЕСТВО ЯЧЕЕК ДЛЯ ПОБЕДЫ:  ";


    //минимальный и максимальный размер карты
    private int minMapSize = 3;
    private int minWinLength = 3; // ячеек для победы
    private int maxMapSize = 10;

    private JTextArea gameLog;
    private JScrollPane scrollPanel; // скрол


    GameWindow() {
        prepareWindow(); // настройки окна
        prepareGameSettingsPanel(); //панель
        // кнопки
        prepareGameButtons();
        // область для кнопок
        prepareGameControlsPanel();


        prepareGameLog(); //панель с текстом

        gameField = new GameField(this);
        panelSettings.add(panelControls, BorderLayout.NORTH);
        panelSettings.add(scrollPanel, BorderLayout.SOUTH);

        add(panelSettings, BorderLayout.EAST);
        add(gameField);

        setVisible(true);
    }

    private void prepareWindow() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.winWidht = 1024;
        this.winHeight = 768;
        this.winPosX = 300;
        this.winPosY = 150;
        setSize(winWidht, winHeight);
        setLocation(winPosX, winPosY);
        setTitle("Application");
        setResizable(false);
    }

    private void prepareGameSettingsPanel() {
        panelSettings = new JPanel();
        panelSettings.setLayout(new GridLayout(2, 1));
    }

    private void prepareGameButtons() {
        buttonStartGame = new JButton("ЗАПУСТИТЬ ИГРУ");
        buttonStartGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameLog.setText("");
                collectUserSettingsAndLaunch();

            }
        });

        buttonEndGame = new JButton("ВЫЙТИ ИЗ ИГРЫ ");
        buttonEndGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }

        });

        buttonClearLog = new JButton("ОЧИСТИТЬ ПОЛЕ");
        buttonClearLog.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameLog.setText("");
            }
        });
    }

    private void prepareGameControlsPanel() {
        panelControls = new JPanel();
        panelControls.setLayout(new GridLayout(7, 1));

        labelMapSize = new JLabel(MAP_SIZE_PREFIX + minMapSize);
        sliderMapSize = new JSlider(minMapSize, maxMapSize, minMapSize);

        //изменение бегунка
        sliderMapSize.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int currentPos = sliderMapSize.getValue();
                labelMapSize.setText(MAP_SIZE_PREFIX + currentPos);
                sliderWinLength.setMaximum(currentPos);
            }
        });

        labelWinLength = new JLabel(WIN_LENGTH_PREFIX + minWinLength);
        sliderWinLength = new JSlider(minWinLength, minMapSize, minWinLength);
        sliderWinLength.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                labelWinLength.setText(WIN_LENGTH_PREFIX + sliderWinLength.getValue());
            }
        });

        panelControls.add(buttonStartGame);
        panelControls.add(buttonEndGame);
        panelControls.add(buttonClearLog);
        panelControls.add(labelMapSize);
        panelControls.add(sliderMapSize);
        panelControls.add(labelWinLength);
        panelControls.add(sliderWinLength);
    }
    //скрол ипанель с тестом
    private void prepareGameLog() {
        gameLog = new JTextArea();
        scrollPanel = new JScrollPane(gameLog);
        gameLog.setEditable(false);
        gameLog.setLineWrap(true);
    }

    //сообщения
    void saveLog(String str) {
        gameLog.append(str + "\n");
    }


    //СБОР ДАННЫХ
    private void collectUserSettingsAndLaunch() {
        int mapSize = sliderMapSize.getValue();
        int winLen = sliderWinLength.getValue();

        saveLog("ЗАПУСТИТЬ ИГРУ");
        saveLog("ВЫБЕРИТИ РАЗМЕРЫ ПОЛЯ " + mapSize + "x" + mapSize);
        saveLog("КОЛЛИЧЕСТВО ЯЧЕЕК ДЛЯ ПОБЕДЫ " + winLen);


        gameField.startGame(mapSize, mapSize, winLen);
    }
}
