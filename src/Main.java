import Domain.Builder;
import Domain.KuVidGame;
import Domain.MVC.BuilderController;
import Domain.MVC.GameController;
import Domain.MVC.Observer;
import UI.GameUI.BuilderUI;
import UI.GameUI.BlenderUI;
import UI.KuVidGameUI;
import UI.GameUI.StatisticsWindowUI;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        int width = 800;
        int height = 640;
        int gameWidth = 640;
        int statsWidth = 160;
        int builderWidth = 740;
        int builderHeight = 800;

        Builder builder = new Builder();
        BuilderController builderController = new BuilderController(builder);
        KuVidGame kuVidGame = builder.createGame(height, gameWidth);
        GameController gameController = new GameController(kuVidGame);

        JFrame builderFrame = new JFrame();
        builderFrame.pack();
        builderFrame.setSize(builderWidth, builderHeight);
        JPanel builderPanel = new JPanel();
        builderPanel.add(new BuilderUI(builderController, gameController));
        builderFrame.add(builderPanel);
        builderFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        builderFrame.setLocationRelativeTo(null);

        JFrame frame = new JFrame();
        frame.pack();
        frame.setSize(width, height);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        Color bgcolor = Color.decode("#272727");
        KuVidGameUI kuVidGameUI = new KuVidGameUI(gameWidth, height, gameController);
        kuVidGameUI.setBorder(BorderFactory.createEmptyBorder());
        kuVidGameUI.setBackground(bgcolor);
        kuVidGameUI.setVisible(true);
        StatisticsWindowUI statisticsWindowUI = new StatisticsWindowUI(gameController);
        statisticsWindowUI.setBackground(bgcolor);
        BlenderUI blenderUI = new BlenderUI(gameController);
        blenderUI.setRequestFocusEnabled(false);
        blenderUI.setBackground(bgcolor);
        Observer observer = new Observer(kuVidGame, kuVidGameUI, statisticsWindowUI);

        JPanel gamePanel = new JPanel();
        gamePanel.setLayout(new GridLayout(1, 1));
        gamePanel.setPreferredSize(new Dimension(gameWidth, height));
        gamePanel.add(kuVidGameUI);

        JPanel statsPanel = new JPanel();
        statsPanel.setLayout(new GridLayout(2, 1));
        statsPanel.setPreferredSize(new Dimension(statsWidth, height));
        statsPanel.add(statisticsWindowUI);
        statsPanel.add(blenderUI);

        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));
        frame.add(gamePanel);
        frame.add(statsPanel);
        frame.setVisible(true);

        builderFrame.setVisible(true);

        while (true) {
            observer.observeShooter();
            observer.observeMessageList();
            observer.observeStatisticsWindow();
            observer.observeShieldStatus();
        }
    }
}