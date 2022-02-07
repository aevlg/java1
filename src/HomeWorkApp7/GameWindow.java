package HomeWorkApp7;

import javax.swing.*;
import java.awt.*;

public class GameWindow  extends JFrame {
    //размеры окна
    private int winWidht;
    private int winHeight;

    // где оно появляется при запуске
    private int winPosX;
    private int winPosY;


    //кнопочки
    private JButton buttonStartGame;
    private JButton buttonEndGame;

    private GameField gameField; // игровое поле

    private JPanel panelSettings; // панель настроек общий

    private JPanel panelControls; // панелька для настроек

    GameWindow(){
        // настройки окна
        prepareWindow();
        // панельки
        prepareGameSettingsPanel();
        // кнопки
        prepareGameButtons();

        // область для кнопок
        prepareGameControlsPanel();

        setVisible(true); // видимость окна




        gameField = new GameField();
        panelSettings.add(panelControls, BorderLayout.NORTH);
        add(panelSettings, BorderLayout.EAST);
        add(gameField);



//        add(buttonEnd, BorderLayout.NORTH);
//        add(buttonStart, BorderLayout.SOUTH); //по сторнам света
//        setLayout(new GridLayout(15,15));
//        for (int i = 0; i < 30; i++){
//            new Button("Texet " + i);



        }


    // созадание кнопок
    private void prepareGameButtons() {
        buttonStartGame = new JButton("Start");
        buttonEndGame = new JButton("Exit");
    }

    // все настройки
    private void prepareGameControlsPanel() {
        panelControls = new JPanel();
        panelControls.setLayout(new GridLayout(6, 1));

        // добавляем кнопки
        panelControls.add(buttonStartGame);
        panelControls.add(buttonEndGame);
    }


    private  void prepareWindow(){

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //закрыть окно на крестик

        this.winWidht = 1024;
        this.winHeight = 780;

        this.winPosX= 300;
        this.winPosY = 150;

        setSize(winWidht, winHeight); //изменить размер
        setLocation(winPosX, winPosY);
        setTitle("tic-tac-toe"); // название окна
    }

    private void prepareGameSettingsPanel() {
        panelSettings = new JPanel();
        panelSettings.setLayout(new GridLayout(2,1));
    }

}

