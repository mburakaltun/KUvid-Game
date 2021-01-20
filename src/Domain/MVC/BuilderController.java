package Domain.MVC;

import Domain.Builder;

public class BuilderController {
    Builder builder;

    public BuilderController(Builder builder) {
        this.builder = builder;
    }

    public void initializeBuilder(float l, int alphaMoleculeMode, int betaMoleculeMode, int alphaMoleculeLinearSpinMode,
                                  int betaMoleculeLinearSpinMode, int[] atomBulletCounts, int[] moleculeCounts,
                                  int[] reactionBlockerCounts, int[] powerUpCounts, int[] shieldCounts, int difficulty) {
        builder.initialize(l,
                alphaMoleculeMode,
                betaMoleculeMode,
                alphaMoleculeLinearSpinMode,
                betaMoleculeLinearSpinMode,
                atomBulletCounts,
                moleculeCounts,
                reactionBlockerCounts,
                powerUpCounts,
                shieldCounts,
                difficulty);
    }

}
