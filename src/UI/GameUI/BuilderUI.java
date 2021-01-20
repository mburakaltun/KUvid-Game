package UI.GameUI;

import Domain.MVC.BuilderController;
import Domain.MVC.GameController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BuilderUI extends JPanel {
    BuilderController builderController;
    GameController gameController;
    JPanel atomBulletCountsPanel = new JPanel();
    JLabel alphaAtomCountLabel = new JLabel("Alpha Atom Count: ");
    JLabel betaAtomCountLabel = new JLabel("Beta Atom Count: ");
    JLabel gammaAtomCountLabel = new JLabel("Gamma Atom Count: ");
    JLabel sigmaAtomCountLabel = new JLabel("Sigma Atom Count: ");
    JTextField alphaAtomCountTextField = new JTextField("100");
    JTextField betaAtomCountTextField = new JTextField("100");
    JTextField gammaAtomCountTextField = new JTextField("100");
    JTextField sigmaAtomCountTextField = new JTextField("100");

    JPanel moleculeCountsPanel = new JPanel();
    JLabel alphaMoleculeCountLabel = new JLabel("Alpha Molecule Count: ");
    JLabel betaMoleculeCountLabel = new JLabel("Beta Molecule Count: ");
    JLabel gammaMoleculeCountLabel = new JLabel("Gamma Molecule Count: ");
    JLabel sigmaMoleculeCountLabel = new JLabel("Sigma Molecule Count: ");
    JTextField alphaMoleculeCountTextField = new JTextField("20");
    JTextField betaMoleculeCountTextField = new JTextField("20");
    JTextField gammaMoleculeCountTextField = new JTextField("20");
    JTextField sigmaMoleculeCountTextField = new JTextField("20");

    JPanel reactionBlockerCountsPanel = new JPanel();
    JLabel alphaReactionBlockerCountLabel = new JLabel("Alpha Reaction Blocker Count: ");
    JLabel betaReactionBlockerCountLabel = new JLabel("Beta Reaction Blocker Count: ");
    JLabel gammaReactionBlockerCountLabel = new JLabel("Gamma Reaction Blocker Count: ");
    JLabel sigmaReactionBlockerCountLabel = new JLabel("Sigma Reaction Blocker Count: ");
    JTextField alphaReactionBlockerCountTextField = new JTextField("10");
    JTextField betaReactionBlockerCountTextField = new JTextField("10");
    JTextField gammaReactionBlockerCountTextField = new JTextField("10");
    JTextField sigmaReactionBlockerCountTextField = new JTextField("10");

    JPanel shieldCountsPanel = new JPanel();
    JLabel etaCountLabel = new JLabel("Eta Shield Count: ");
    JLabel thetaCountLabel = new JLabel("Theta Shield Count: ");
    JLabel lotaCountLabel = new JLabel("Lota Shield Count: ");
    JLabel zetaCountLabel = new JLabel("Zeta Shield Count: ");
    JTextField etaCountTextField = new JTextField("20");
    JTextField thetaCountTextField = new JTextField("20");
    JTextField lotaCountTextField = new JTextField("20");
    JTextField zetaCountTextField = new JTextField("20");

    JPanel ratioPanel = new JPanel();
    JLabel ratioLabel = new JLabel("Ratio: ");
    JTextField ratioTextField = new JTextField("10");

    JPanel difficultyPanel = new JPanel();
    JLabel difficultyLabel = new JLabel("Difficulty");
    JRadioButton easyButton = new JRadioButton("Easy");
    JRadioButton mediumButton = new JRadioButton("Medium");
    JRadioButton hardButton = new JRadioButton("Hard");
    ButtonGroup difficultyButtonGroup = new ButtonGroup();

    JButton OKButton = new JButton("OK");


    public BuilderUI(BuilderController builderController, GameController gameController) {
        this.builderController = builderController;
        this.gameController = gameController;
        this.setLayout(new GridLayout(7, 1));

        atomBulletCountsPanel.setLayout(new GridLayout(4, 2));
        atomBulletCountsPanel.add(alphaAtomCountLabel);
        atomBulletCountsPanel.add(alphaAtomCountTextField);
        atomBulletCountsPanel.add(betaAtomCountLabel);
        atomBulletCountsPanel.add(betaAtomCountTextField);
        atomBulletCountsPanel.add(gammaAtomCountLabel);
        atomBulletCountsPanel.add(gammaAtomCountTextField);
        atomBulletCountsPanel.add(sigmaAtomCountLabel);
        atomBulletCountsPanel.add(sigmaAtomCountTextField);


        moleculeCountsPanel.setLayout(new GridLayout(4, 2));
        moleculeCountsPanel.add(alphaMoleculeCountLabel);
        moleculeCountsPanel.add(alphaMoleculeCountTextField);
        moleculeCountsPanel.add(betaMoleculeCountLabel);
        moleculeCountsPanel.add(betaMoleculeCountTextField);
        moleculeCountsPanel.add(gammaMoleculeCountLabel);
        moleculeCountsPanel.add(gammaMoleculeCountTextField);
        moleculeCountsPanel.add(sigmaMoleculeCountLabel);
        moleculeCountsPanel.add(sigmaMoleculeCountTextField);

        reactionBlockerCountsPanel.setLayout(new GridLayout(4, 2));
        reactionBlockerCountsPanel.add(alphaReactionBlockerCountLabel);
        reactionBlockerCountsPanel.add(alphaReactionBlockerCountTextField);
        reactionBlockerCountsPanel.add(betaReactionBlockerCountLabel);
        reactionBlockerCountsPanel.add(betaReactionBlockerCountTextField);
        reactionBlockerCountsPanel.add(gammaReactionBlockerCountLabel);
        reactionBlockerCountsPanel.add(gammaReactionBlockerCountTextField);
        reactionBlockerCountsPanel.add(sigmaReactionBlockerCountLabel);
        reactionBlockerCountsPanel.add(sigmaReactionBlockerCountTextField);

        shieldCountsPanel.setLayout(new GridLayout(4,2));
        shieldCountsPanel.add(etaCountLabel);
        shieldCountsPanel.add(etaCountTextField);
        shieldCountsPanel.add(thetaCountLabel);
        shieldCountsPanel.add(thetaCountTextField);
        shieldCountsPanel.add(lotaCountLabel);
        shieldCountsPanel.add(lotaCountTextField);
        shieldCountsPanel.add(zetaCountLabel);
        shieldCountsPanel.add(zetaCountTextField);

        ratioPanel.setLayout(new GridLayout(1, 2));
        ratioPanel.add(ratioLabel);
        ratioPanel.add(ratioTextField);

        difficultyPanel.setLayout(new GridLayout(1, 4));
        difficultyButtonGroup.add(easyButton);
        difficultyButtonGroup.add(mediumButton);
        difficultyButtonGroup.add(hardButton);
        difficultyButtonGroup.setSelected(easyButton.getModel(), true);
        difficultyPanel.add(difficultyLabel);
        difficultyPanel.add(easyButton);
        difficultyPanel.add(mediumButton);
        difficultyPanel.add(hardButton);

        this.add(atomBulletCountsPanel);
        this.add(moleculeCountsPanel);
        this.add(reactionBlockerCountsPanel);
        this.add(shieldCountsPanel);
        this.add(ratioPanel);
        this.add(difficultyPanel);
        this.add(OKButton);

        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] atomBulletCounts = new int[4];
                atomBulletCounts[0] = Integer.parseInt(alphaAtomCountTextField.getText());
                atomBulletCounts[1] = Integer.parseInt(betaAtomCountTextField.getText());
                atomBulletCounts[2] = Integer.parseInt(gammaAtomCountTextField.getText());
                atomBulletCounts[3] = Integer.parseInt(sigmaAtomCountTextField.getText());

                int[] moleculeCounts = new int[4];
                moleculeCounts[0] = Integer.parseInt(alphaMoleculeCountTextField.getText());
                moleculeCounts[1] = Integer.parseInt(betaMoleculeCountTextField.getText());
                moleculeCounts[2] = Integer.parseInt(gammaMoleculeCountTextField.getText());
                moleculeCounts[3] = Integer.parseInt(sigmaMoleculeCountTextField.getText());


                int[] reactionBlockerCounts = new int[4];
                reactionBlockerCounts[0] = Integer.parseInt(alphaReactionBlockerCountTextField.getText());
                reactionBlockerCounts[1] = Integer.parseInt(betaReactionBlockerCountTextField.getText());
                reactionBlockerCounts[2] = Integer.parseInt(gammaReactionBlockerCountTextField.getText());
                reactionBlockerCounts[3] = Integer.parseInt(sigmaReactionBlockerCountTextField.getText());

                int[] shieldCounts = new int[4];
                shieldCounts[0] = Integer.parseInt(etaCountTextField.getText());
                shieldCounts[1] = Integer.parseInt(thetaCountTextField.getText());
                shieldCounts[2] = Integer.parseInt(lotaCountTextField.getText());
                shieldCounts[3] = Integer.parseInt(zetaCountTextField.getText());

                float l = Float.parseFloat(ratioTextField.getText());

                int difficulty;
                if(easyButton.isSelected()) {
                    difficulty = 0;
                } else if(mediumButton.isSelected()) {
                    difficulty = 1;
                } else {
                    difficulty = 2;
                }

                builderController.initializeBuilder(l,
                        0,
                        0,
                        0,
                        0,
                        atomBulletCounts,
                        moleculeCounts,
                        reactionBlockerCounts,
                        new int[]{0, 0, 0, 0},
                        shieldCounts,
                        difficulty);
                closeFrame();
                gameController.resumeDomainGame();
            }
        });

    }

    public void closeFrame() {
        JFrame builderFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        builderFrame.dispose();
    }
}
