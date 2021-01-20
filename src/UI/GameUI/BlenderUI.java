package UI.GameUI;

import Domain.MVC.GameController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BlenderUI extends JPanel {
    private GameController gameController;
    JPanel mainPanel = new JPanel();
    JButton blendButton;
    JButton breakButton;
    JLabel leftLabel;
    JLabel rightLabel;
    JRadioButton leftAlphaRadioButton;
    JRadioButton leftBetaRadioButton;
    JRadioButton leftGammaRadioButton;
    JRadioButton rightBetaRadioButton;
    JRadioButton rightGammaRadioButton;
    JRadioButton rightSigmaRadioButton;
    ButtonGroup leftButtonGroup;
    ButtonGroup rightButtonGroup;
    JButton OKButton;
    int operationMode;
    int leftState;
    int rightState;

    public BlenderUI(GameController gameController) {
        this.gameController = gameController;
        this.setLayout(new GridLayout(2, 1));
        mainPanel.setLayout(new GridLayout(5, 1));

        blendButton = new JButton("Blend");
        breakButton = new JButton("Break");
        leftLabel = new JLabel("Source", SwingConstants.CENTER);
        rightLabel = new JLabel("Target", SwingConstants.CENTER);
        leftAlphaRadioButton = new JRadioButton("Alpha");
        leftBetaRadioButton = new JRadioButton("Beta");
        leftGammaRadioButton = new JRadioButton("Gamma");
        rightBetaRadioButton = new JRadioButton("Beta");
        rightGammaRadioButton = new JRadioButton("Gamma");
        rightSigmaRadioButton = new JRadioButton("Sigma");
        leftButtonGroup = new ButtonGroup();
        rightButtonGroup = new ButtonGroup();
        OKButton = new JButton();
        OKButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Blender_texture.png")));

        blendButton.setFocusable(false);
        breakButton.setFocusable(false);
        leftAlphaRadioButton.setFocusable(false);
        leftBetaRadioButton.setFocusable(false);
        leftGammaRadioButton.setFocusable(false);
        rightBetaRadioButton.setFocusable(false);
        rightGammaRadioButton.setFocusable(false);
        rightSigmaRadioButton.setFocusable(false);
        OKButton.setFocusable(false);
        OKButton.setBorder(BorderFactory.createEmptyBorder());
        OKButton.setContentAreaFilled(false);

        leftButtonGroup.add(leftAlphaRadioButton);
        leftButtonGroup.add(leftBetaRadioButton);
        leftButtonGroup.add(leftGammaRadioButton);
        leftButtonGroup.setSelected(leftAlphaRadioButton.getModel(), true);
        rightButtonGroup.add(rightBetaRadioButton);
        rightButtonGroup.add(rightGammaRadioButton);
        rightButtonGroup.add(rightSigmaRadioButton);
        rightButtonGroup.setSelected(rightBetaRadioButton.getModel(), true);

        operationMode = 0; // blend = 0, break = 1
        leftState = 0;
        rightState = 1;

        mainPanel.add(blendButton);
        mainPanel.add(breakButton);
        mainPanel.add(leftLabel);
        mainPanel.add(rightLabel);
        mainPanel.add(leftAlphaRadioButton);
        mainPanel.add(rightBetaRadioButton);
        mainPanel.add(leftBetaRadioButton);
        mainPanel.add(rightGammaRadioButton);
        mainPanel.add(leftGammaRadioButton);
        mainPanel.add(rightSigmaRadioButton);

        this.add(mainPanel);
        this.add(OKButton);

        blendButton.setForeground(Color.BLACK);
        breakButton.setForeground(Color.GRAY);


        leftAlphaRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                leftState = 0;
                if(operationMode == 0) {
                    rightBetaRadioButton.setEnabled(true);
                    rightGammaRadioButton.setEnabled(true);
                }
            }
        });

        leftBetaRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                leftState = 1;
                if(operationMode == 0) {
                    if (rightButtonGroup.isSelected(rightBetaRadioButton.getModel())) {
                        rightButtonGroup.setSelected(rightGammaRadioButton.getModel(), true);
                        rightState = 2;
                    }
                    rightBetaRadioButton.setEnabled(false);
                    rightGammaRadioButton.setEnabled(true);
                }
            }
        });

        leftGammaRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                leftState = 2;
                if(operationMode == 0) {
                    if (rightButtonGroup.isSelected(rightBetaRadioButton.getModel()) || rightButtonGroup.isSelected(rightGammaRadioButton.getModel())) {
                        rightButtonGroup.setSelected(rightSigmaRadioButton.getModel(), true);
                        rightState = 3;
                    }
                    rightBetaRadioButton.setEnabled(false);
                    rightGammaRadioButton.setEnabled(false);
                }
            }
        });

        rightBetaRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rightState = 1;
                if(operationMode == 1) {
                    if(leftButtonGroup.isSelected(leftBetaRadioButton.getModel()) || leftButtonGroup.isSelected(leftGammaRadioButton.getModel())) {
                        leftButtonGroup.setSelected(leftAlphaRadioButton.getModel(), true);
                        leftState = 0;
                    }
                    leftBetaRadioButton.setEnabled(false);
                    leftGammaRadioButton.setEnabled(false);
                }
            }
        });

        rightGammaRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rightState = 2;
                if(operationMode == 1) {
                    if(leftButtonGroup.isSelected(leftGammaRadioButton.getModel())) {
                        leftButtonGroup.setSelected(leftAlphaRadioButton.getModel(), true);
                        leftState = 0;
                    }
                    leftGammaRadioButton.setEnabled(false);
                    leftBetaRadioButton.setEnabled(true);
                }
            }
        });

        rightSigmaRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rightState = 3;
                if(operationMode == 1) {
                    leftGammaRadioButton.setEnabled(true);
                    leftBetaRadioButton.setEnabled(true);
                }
            }
        });

        blendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                operationMode = 0;
                leftLabel.setText("Source");
                rightLabel.setText("Target");
                blendButton.setForeground(Color.BLACK);
                breakButton.setForeground(Color.GRAY);
                leftButtonGroup.setSelected(leftAlphaRadioButton.getModel(), true);
                rightButtonGroup.setSelected(rightBetaRadioButton.getModel(), true);
                leftState = 0;
                rightState = 1;
                leftBetaRadioButton.setEnabled(true);
                leftGammaRadioButton.setEnabled(true);
            }
        });

        breakButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                operationMode = 1;
                leftLabel.setText("Target");
                rightLabel.setText("Source");
                blendButton.setForeground(Color.GRAY);
                breakButton.setForeground(Color.BLACK);
                leftButtonGroup.setSelected(leftAlphaRadioButton.getModel(), true);
                rightButtonGroup.setSelected(rightBetaRadioButton.getModel(), true);
                leftState = 0;
                rightState = 1;
                rightBetaRadioButton.setEnabled(true);
                rightGammaRadioButton.setEnabled(true);
            }
        });

        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (operationMode) {
                    case 0:
                        gameController.blendOperation(rightState, leftState);
                        break;
                    case 1:
                        gameController.breakOperation(leftState, rightState);
                        break;
                    default:
                        break;
                }
            }
        });
    }

}
