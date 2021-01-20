package Domain;

import java.util.Arrays;

public class Builder {
    private StatisticsWindow statisticsWindow;
    private ElementGenerator elementGenerator;
    private KuVidGame kuVidGame;

    public Builder() {
    }

    public void initialize(float l, int alphaMoleculeMode, int betaMoleculeMode, int alphaMoleculeLinearSpinMode,
                           int betaMoleculeLinearSpinMode, int[] atomBulletCounts, int[] moleculeCounts,
                           int[] reactionBlockerCounts, int[] powerUpCounts, int[] shieldCounts, int difficulty) {
        statisticsWindow.setAtomCounts(atomBulletCounts);
        statisticsWindow.setShieldCounts(shieldCounts);
        elementGenerator.setAlphaMoleculeLinearSpinMode(alphaMoleculeLinearSpinMode);
        elementGenerator.setAlphaMoleculeMode(alphaMoleculeMode);
        elementGenerator.setBetaMoleculeLinearSpinMode(betaMoleculeLinearSpinMode);
        elementGenerator.setBetaMoleculeMode(betaMoleculeMode);
        elementGenerator.setMoleculeCount(moleculeCounts);
        elementGenerator.setPowerUpCount(powerUpCounts);
        elementGenerator.setReactionBlockerCount(reactionBlockerCounts);
        int totalNumberOfElements = Arrays.stream(moleculeCounts).sum()
                + Arrays.stream(reactionBlockerCounts).sum()
                + Arrays.stream(powerUpCounts).sum();
        elementGenerator.setTotalNumberOfElements(totalNumberOfElements);
        kuVidGame.setLRatio(l);
        kuVidGame.setDifficulty(difficulty);
    }

    public KuVidGame createGame(float width, float height) {
        statisticsWindow = new StatisticsWindow();
        elementGenerator = new ElementGenerator();
        return this.kuVidGame = new KuVidGame(elementGenerator, statisticsWindow, width, height);
    }
}
