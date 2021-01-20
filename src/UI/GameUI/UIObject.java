package UI.GameUI;

import Domain.KuVidGame;
import UI.KuVidGameUI;

import javax.swing.*;
import java.awt.*;

public class UIObject {
    private final long id;
    protected final String imagePath;
    public int x;
    public int y;
    private boolean isUpdated;

    private int height;
    private int width;
    private boolean isShootersAtom;
    private KuVidGameUI kuVidGameUI;
    String etaShieldImagePath = "/images/EtaShield_texture.png";
    String thetaShieldImagePath = "/images/ThetaShield_texture.png";
    String lotaShieldImagePath = "/images/LotaShield_texture.png";
    String zetaShieldImagePath = "/images/ZetaShield_texture.png";

    public UIObject(long id, int x, int y, String imagePath, boolean isShootersAtom, KuVidGameUI kuVidGameUI) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.imagePath = imagePath;
        isUpdated = true;
        this.isShootersAtom = isShootersAtom;
        this.kuVidGameUI = kuVidGameUI;

        width = (int) (getImage(imagePath).getWidth(null) * 0.4);
        height = (int) (getImage(imagePath).getHeight(null) * 0.4);
    }

    public void update(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics2D graphics2D) {
        graphics2D.drawImage(getImage(imagePath), x - width / 2, y - height / 2, width, height, null);
        if(isShootersAtom) {
            boolean[] shieldStatus = kuVidGameUI.getShieldStatus();
            if(shieldStatus[0]) {
                graphics2D.drawImage(getImage(etaShieldImagePath), x - width / 2, y - height / 2, width, height, null);
            }
            if(shieldStatus[1]) {
                graphics2D.drawImage(getImage(thetaShieldImagePath), x - width / 2, y - height / 2, width, height, null);
            }
            if(shieldStatus[2]) {
                graphics2D.drawImage(getImage(lotaShieldImagePath), x - width / 2, y - height / 2, width, height, null);
            }
            if(shieldStatus[3]) {
                graphics2D.drawImage(getImage(zetaShieldImagePath), x - width / 2, y - height / 2, width, height, null);
            }
        }
    }

    public Image getImage(String imagePath) {
        ImageIcon i = new ImageIcon(getClass().getResource(imagePath));
        return i.getImage();
    }

    public long getId() {
        return id;
    }

    public boolean isUpdated() {
        return isUpdated;
    }

    public void setUpdateStatus(boolean flag) {
        isUpdated = flag;
    }

    public String getImagePath() {
        return imagePath;
    }
}
