package Domain;

public class StatisticsWindow {
    private int timeLeft;
    private int health;
    private float score;
    private float windowHeight;
    private int[] atomCounts;
    private int[] powerUpCounts;
    private int[] shieldCounts;

    public StatisticsWindow() {
        windowHeight = 0;
        score = 0;
        timeLeft = 60 * 10;
        health = 100;
        atomCounts = new int[]{0, 0, 0, 0};
        powerUpCounts = new int[]{0, 0, 0, 0};
        shieldCounts = new int[]{0, 0, 0, 0};
    }

    public void increaseAtomCount(int type, int atomCount) {
        atomCounts[type] += atomCount;
    }

    public int getTimeLeft() {
        return timeLeft;
    }

    public int getHealth() {
        return health;
    }

    public float getScore() {
        return score;
    }

    public int[] getAtomCounts() {
        return new int[]{atomCounts[0], atomCounts[1], atomCounts[2], atomCounts[3]};
    }

    public int[] getPowerUpCounts() {
        return new int[]{powerUpCounts[0], powerUpCounts[1], powerUpCounts[2], powerUpCounts[3]};
    }

    public int getAtomCount(int index) {
        return atomCounts[index];
    }

    public int getPowerUpCount(int index) {
        return powerUpCounts[index];
    }

    public void incrementAtomCount(int index) {
        // REQUIRES: 0<=index<=3 index:int
        // EFFECTS atomCounts[]
        //
        atomCounts[index]++;
    }

    public void decrementAtomCount(int index) {
        atomCounts[index]--;
    }

    public void decreaseAtomCount(int index, int amount) {
        atomCounts[index] -= amount;
    }

    public void incrementPowerUpCount(int type) {
        powerUpCounts[type] += 1;
    }

    public void decrementPowerUpCount(int type) {
        powerUpCounts[type] -= 1;
    }

    public String getAtomCountsString() {
        return atomCounts[0] + " " + atomCounts[1] + " " + atomCounts[2] + " " + atomCounts[3];
    }

    public String getPowerUpCountsString() {
        return powerUpCounts[0] + " " + powerUpCounts[1] + " " + powerUpCounts[2] + " " + powerUpCounts[3];
    }

    public String getShieldCountsString() {
        return shieldCounts[0] + " " + shieldCounts[1] + " " + shieldCounts[2] + " " + shieldCounts[3];
    }


    public void setTimeLeft(int timeLeft) {
        this.timeLeft = timeLeft;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public void setAtomCounts(int[] atomCounts) {
        this.atomCounts = atomCounts;
    }

    public void setPowerUpCounts(int[] powerUpCounts) {
        this.powerUpCounts = powerUpCounts;
    }

    public float getWindowHeight() {
        return windowHeight;
    }

    public void setWindowHeight(float windowHeight) {
        this.windowHeight = windowHeight;
    }

    public void decreaseHealth(int healthReduction) {
        health -= healthReduction;
        if(health < 0) {
            health = 0;
        }
    }

    public void increaseScore(float efficiency) {
        score += efficiency;
    }

    public int[] getShieldCounts() {
        return new int []{shieldCounts[0], shieldCounts[1], shieldCounts[2], shieldCounts[3]};
    }

    public void setShieldCounts(int[] shieldCounts) {
        this.shieldCounts = shieldCounts;
    }

    public void decrementShieldCount(int type) {
        shieldCounts[type]--;
    }

    public void decrementTime() {
        timeLeft--;
    }

    public int getShieldCount(int type) {
        return shieldCounts[type];
    }
}
