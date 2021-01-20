package UI.GameUI;

import Domain.MVC.GameController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class StatisticsWindowUI extends JPanel {
    private float score;
    private int timeLeft;
    private int health;
    private int[] atomCounts;
    private int[] powerUpCounts;
    private int[] shieldCounts;

    private JPanel topPanel = new JPanel();
    private JPanel powerUpCountsPanel = new JPanel();
    private JPanel atomCountsPanel = new JPanel();
    private JPanel shieldCountsPanel = new JPanel();

    private JLabel scoreLabel;
    private JLabel timeLabel;
    private JLabel healthLabel;
    private JLabel alphaPowerUpCountLabel;
    private JLabel betaPowerUpCountLabel;
    private JLabel gammaPowerUpCountLabel;
    private JLabel sigmaPowerUpCountLabel;
    private JLabel alphaAtomCountLabel;
    private JLabel betaAtomCountLabel;
    private JLabel gammaAtomCountLabel;
    private JLabel sigmaAtomCountLabel;
    private JLabel etaCountLabel = new JLabel();
    private JLabel thetaCountLabel = new JLabel();
    private JLabel lotaCountLabel = new JLabel();
    private JLabel zetaCountLabel = new JLabel();

    private JLabel alphaAtomImageLabel;
    private JLabel betaAtomImageLabel;
    private JLabel gammaAtomImageLabel;
    private JLabel sigmaAtomImageLabel;
    private JLabel alphaPowerUpImageLabel;
    private JLabel betaPowerUpImageLabel;
    private JLabel gammaPowerUpImageLabel;
    private JLabel sigmaPowerUpImageLabel;

    private JButton alphaPowerUpButton;
    private JButton betaPowerUpButton;
    private JButton gammaPowerUpButton;
    private JButton sigmaPowerUpButton;

    private JButton etaButton;
    private JButton thetaButton;
    private JButton lotaButton;
    private JButton zetaButton;

    private JButton saveButton;
    private JButton loadButton;

    GameController gameController;

    public class statsWindowUpdateTask extends TimerTask {
        @Override
        public void run() {
            scoreLabel.setText(String.format("Score: %.2f", score));
            int seconds = timeLeft % 60;
            int minutes = timeLeft / 60;
            if (seconds < 10) {
                timeLabel.setText("Time: 0" + minutes + ":0" + seconds);
            } else {
                timeLabel.setText("Time: 0" + minutes + ":" + seconds);
            }
            healthLabel.setText("Health: " + health);
            alphaPowerUpCountLabel.setText(String.valueOf(powerUpCounts[0]));
            betaPowerUpCountLabel.setText(String.valueOf(powerUpCounts[1]));
            gammaPowerUpCountLabel.setText(String.valueOf(powerUpCounts[2]));
            sigmaPowerUpCountLabel.setText(String.valueOf(powerUpCounts[3]));
            alphaAtomCountLabel.setText(String.valueOf(atomCounts[0]));
            betaAtomCountLabel.setText(String.valueOf(atomCounts[1]));
            gammaAtomCountLabel.setText(String.valueOf(atomCounts[2]));
            sigmaAtomCountLabel.setText(String.valueOf(atomCounts[3]));
            etaCountLabel.setText(String.valueOf(shieldCounts[0]));
            thetaCountLabel.setText(String.valueOf(shieldCounts[1]));
            lotaCountLabel.setText(String.valueOf(shieldCounts[2]));
            zetaCountLabel.setText(String.valueOf(shieldCounts[3]));
        }
    }

    public StatisticsWindowUI(GameController gameController) {
        this.gameController = gameController; // will be removed
        timeLeft = 600;
        health = 100;
        atomCounts = new int[]{0, 0, 0, 0};
        powerUpCounts = new int[]{0, 0, 0, 0};
        shieldCounts = new int[]{0, 0, 0, 0};

        Timer updateTimer = new Timer();
        TimerTask updateTask = new statsWindowUpdateTask();
        updateTimer.schedule(updateTask, 10, 200);

        this.setLayout(new GridLayout(6, 1));

        scoreLabel = new JLabel("Score: 0", SwingConstants.CENTER);
        timeLabel = new JLabel("Time: 10:00", SwingConstants.CENTER);
        healthLabel = new JLabel("Health: 100", SwingConstants.CENTER);
        alphaPowerUpCountLabel = new JLabel("0", SwingConstants.CENTER);
        betaPowerUpCountLabel = new JLabel("0", SwingConstants.CENTER);
        gammaPowerUpCountLabel = new JLabel("0", SwingConstants.CENTER);
        sigmaPowerUpCountLabel = new JLabel("0", SwingConstants.CENTER);
        alphaAtomCountLabel = new JLabel("0");
        betaAtomCountLabel = new JLabel("0");
        gammaAtomCountLabel = new JLabel("0");
        sigmaAtomCountLabel = new JLabel("0");

        alphaAtomImageLabel = new JLabel();
        betaAtomImageLabel = new JLabel();
        gammaAtomImageLabel = new JLabel();
        sigmaAtomImageLabel = new JLabel();

        alphaAtomImageLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/AlphaAtom_texture.png")));
        betaAtomImageLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/BetaAtom_texture.png")));
        gammaAtomImageLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/GammaAtom_texture.png")));
        sigmaAtomImageLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/SigmaAtom_texture.png")));

        alphaPowerUpImageLabel = new JLabel();
        betaPowerUpImageLabel = new JLabel();
        gammaPowerUpImageLabel = new JLabel();
        sigmaPowerUpImageLabel = new JLabel();
        alphaPowerUpImageLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/AlphaPowerUp_texture.png")));
        betaPowerUpImageLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/BetaPowerUp_texture.png")));
        gammaPowerUpImageLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/GammaPowerUp_texture.png")));
        sigmaPowerUpImageLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/SigmaPowerUp_texture.png")));

        alphaPowerUpButton = new JButton("Power!");
        betaPowerUpButton = new JButton("Power!");
        gammaPowerUpButton = new JButton("Power!");
        sigmaPowerUpButton = new JButton("Power!");

        etaButton = new JButton("Eta!");
        thetaButton = new JButton("Theta!");
        lotaButton = new JButton("Lota!");
        zetaButton = new JButton("Zeta!");

        saveButton = new JButton();
        loadButton = new JButton();
        saveButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/save_texture.png")));
        loadButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/load_texture.png")));
        saveButton.setBorder(BorderFactory.createEmptyBorder());
        saveButton.setContentAreaFilled(false);
        loadButton.setBorder(BorderFactory.createEmptyBorder());
        loadButton.setContentAreaFilled(false);

        topPanel.setLayout(new GridLayout(3, 1));
        powerUpCountsPanel.setLayout(new GridLayout(4, 3));
        atomCountsPanel.setLayout(new GridLayout(4, 2));
        shieldCountsPanel.setLayout(new GridLayout(4, 2));

        topPanel.add(scoreLabel);
        topPanel.add(timeLabel);
        topPanel.add(healthLabel);

        powerUpCountsPanel.add(alphaPowerUpImageLabel);
        powerUpCountsPanel.add(alphaPowerUpCountLabel);
        powerUpCountsPanel.add(alphaPowerUpButton);
        powerUpCountsPanel.add(betaPowerUpImageLabel);
        powerUpCountsPanel.add(betaPowerUpCountLabel);
        powerUpCountsPanel.add(betaPowerUpButton);
        powerUpCountsPanel.add(gammaPowerUpImageLabel);
        powerUpCountsPanel.add(gammaPowerUpCountLabel);
        powerUpCountsPanel.add(gammaPowerUpButton);
        powerUpCountsPanel.add(sigmaPowerUpImageLabel);
        powerUpCountsPanel.add(sigmaPowerUpCountLabel);
        powerUpCountsPanel.add(sigmaPowerUpButton);

        atomCountsPanel.add(alphaAtomImageLabel);
        atomCountsPanel.add(alphaAtomCountLabel);
        atomCountsPanel.add(betaAtomImageLabel);
        atomCountsPanel.add(betaAtomCountLabel);
        atomCountsPanel.add(gammaAtomImageLabel);
        atomCountsPanel.add(gammaAtomCountLabel);
        atomCountsPanel.add(sigmaAtomImageLabel);
        atomCountsPanel.add(sigmaAtomCountLabel);

        shieldCountsPanel.add(etaCountLabel);
        shieldCountsPanel.add(etaButton);
        shieldCountsPanel.add(thetaCountLabel);
        shieldCountsPanel.add(thetaButton);
        shieldCountsPanel.add(lotaCountLabel);
        shieldCountsPanel.add(lotaButton);
        shieldCountsPanel.add(zetaCountLabel);
        shieldCountsPanel.add(zetaButton);

        etaButton.setFocusable(false);
        thetaButton.setFocusable(false);

        alphaPowerUpButton.setFocusable(false);
        betaPowerUpButton.setFocusable(false);
        sigmaPowerUpButton.setFocusable(false);
        gammaPowerUpButton.setFocusable(false);

        lotaButton.setFocusable(false);
        zetaButton.setFocusable(false);
        saveButton.setFocusable(false);
        loadButton.setFocusable(false);

        this.add(topPanel);
        this.add(powerUpCountsPanel);
        this.add(atomCountsPanel);
        this.add(shieldCountsPanel);
        this.add(saveButton);
        this.add(loadButton);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameController.saveGame();
            }
        });

        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    gameController.loadGame();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        etaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameController.addShieldToShootersAtom(0);
            }
        });

        thetaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameController.addShieldToShootersAtom(1);
            }
        });

        lotaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameController.addShieldToShootersAtom(2);
            }
        });

        zetaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameController.addShieldToShootersAtom(3);
            }
        });

        alphaPowerUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameController.assignPowerUp(0);
            }
        });

        betaPowerUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameController.assignPowerUp(1);
            }
        });

        gammaPowerUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameController.assignPowerUp(2);
            }
        });

        sigmaPowerUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameController.assignPowerUp(3);
            }
        });

        topPanel.setVisible(true);
        powerUpCountsPanel.setVisible(true);
        atomCountsPanel.setVisible(true);
        shieldCountsPanel.setVisible(true);
        this.setVisible(true);
    }

    public void updateStatisticsWindow(float score, int timeLeft, int health, int[] atomCounts, int[] powerUpCounts, int[] shieldCounts) {
        this.score = score;
        this.timeLeft = timeLeft;
        this.health = health;
        this.atomCounts = atomCounts;
        this.powerUpCounts = powerUpCounts;
        this.shieldCounts = shieldCounts;
    }

}
