package Domain.MVC;

import Domain.Interfaces.IShootable;
import Domain.KuVidGame;
import Domain.Primitives.Element;
import UI.KuVidGameUI;
import UI.GameUI.StatisticsWindowUI;

public class Observer {
    KuVidGame kuVidGame;
    KuVidGameUI kuVidGameUI;
    StatisticsWindowUI statisticsWindowUI;

    public Observer(KuVidGame kuVidGame, KuVidGameUI kuVidGameUI, StatisticsWindowUI statisticsWindowUI) {
        this.kuVidGame = kuVidGame;
        this.kuVidGameUI = kuVidGameUI;
        this.statisticsWindowUI = statisticsWindowUI;
    }

    public void observeMessageList() {
        kuVidGameUI.updateMessageListFromObserver(kuVidGame.getMessageList());
    }

    public void observeShooter() {
        float[] shooterInfo = kuVidGame.getShooterInfo();
        int[] shooterInfoCasted = new int[]{(int) shooterInfo[0], (int) shooterInfo[1]};
        IShootable bullet = kuVidGame.getShooter().getBullet();
        kuVidGameUI.updateShooterInfoFromObserver(shooterInfoCasted, bullet.isAtom(), bullet.getColorType());
    }

    public void observeStatisticsWindow() {
        float score = this.kuVidGame.getStats().getScore();
        int health = this.kuVidGame.getStats().getHealth();
        int timeLeft = this.kuVidGame.getStats().getTimeLeft();
        int[] atomCounts = this.kuVidGame.getStats().getAtomCounts();
        int[] powerUpCounts = this.kuVidGame.getStats().getPowerUpCounts();
        int[] shieldCounts = this.kuVidGame.getStats().getShieldCounts();
        statisticsWindowUI.updateStatisticsWindow(score, timeLeft, health, atomCounts, powerUpCounts, shieldCounts);
    }

    public void observeShieldStatus() {
        kuVidGameUI.setShieldStatus(kuVidGame.getShooter().getBullet().getShieldStatus());
    }
}
