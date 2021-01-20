package Domain;

public class Blender {
    StatisticsWindow statisticsWindow;

    public Blender(StatisticsWindow statisticsWindow) {
        this.statisticsWindow = statisticsWindow;
    }

    public void blendOperation(int blended, int source) {
        if (blended > source) {
            int requiredAmountToBeBroken = blended - source + 1;
            if (statisticsWindow.getAtomCount(source) >= requiredAmountToBeBroken) {
                statisticsWindow.decreaseAtomCount(source, requiredAmountToBeBroken);
                statisticsWindow.incrementAtomCount(blended);
            }
        }
    }

    public void breakOperation(int created, int destroyed) {
        if (destroyed > created) {
            if (statisticsWindow.getAtomCount(destroyed) >= 1) {
                int createdAtomCount = destroyed - created + 1;
                statisticsWindow.decrementAtomCount(destroyed);
                statisticsWindow.increaseAtomCount(created, createdAtomCount);
            }
        }
    }
}
