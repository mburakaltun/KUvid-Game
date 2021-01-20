package UI.GameUI;

import Domain.MVC.GameController;
import Domain.Primitives.Shooter;

import javax.swing.*;
import java.awt.*;

public class ShooterUI {

    private String shooterImagePath1 = "/images/AlphaShooter1_texture.png";
    private String shooterImagePath2 = "/images/AlphaShooter2_texture.png";
    private String shooterImagePath3 = "/images/BetaShooter1_texture.png";
    private String shooterImagePath4 = "/images/BetaShooter2_texture.png";
    private String shooterImagePath5 = "/images/GammaShooter1_texture.png";
    private String shooterImagePath6 = "/images/GammaShooter2_texture.png";
    private String shooterImagePath7 = "/images/SigmaShooter1_texture.png";
    private String shooterImagePath8 = "/images/SigmaShooter2_texture.png";
    private String shooterImagePath9 = "/images/PowerUpShooter1_texture.png";
    private String shooterImagePath10 = "/images/PowerUpShooter2_texture.png";

    private int x;
    private final int y;
    private int angle;
    private int shooterType;
    private boolean isAtom;
    private int bulletColorType;

    public ShooterUI(int x, int y) {
        this.x = x;
        this.y = y;
        angle = 0;
        shooterType = 0;
    }

    public void updateParameters(int x, int angle) {
        this.x = x;
        this.angle = angle;
    }

    public void draw(Graphics2D graphics2D) {
        String imagePath;
        if (shooterType == 0) {
            if (isAtom) {
                switch (bulletColorType) {
                    case 0:
                        imagePath = shooterImagePath1;
                        break;
                    case 1:
                        imagePath = shooterImagePath3;
                        break;
                    case 2:
                        imagePath = shooterImagePath5;
                        break;
                    default:
                        imagePath = shooterImagePath7;
                        break;
                }
            } else {
                imagePath = shooterImagePath9;
            }
        } else {
            if (isAtom) {
                switch (bulletColorType) {
                    case 0:
                        imagePath = shooterImagePath2;
                        break;
                    case 1:
                        imagePath = shooterImagePath4;
                        break;
                    case 2:
                        imagePath = shooterImagePath6;
                        break;
                    default:
                        imagePath = shooterImagePath8;
                        break;
                }
            } else {
                imagePath = shooterImagePath10;
            }
        }
        graphics2D.drawImage(getImage(imagePath), x - 88, y, 176, 88, null);
    }

    public Image getImage(String imagePath) {
        ImageIcon i = new ImageIcon(getClass().getResource(imagePath));
        return i.getImage();
    }

    public void switchShooterType() {
        if (shooterType == 0) {
            shooterType = 1;
        } else {
            shooterType = 0;
        }
    }

    public boolean isAtom() {
        return isAtom;
    }

    public void setBulletType(boolean isAtom) {
        this.isAtom = isAtom;
    }

    public int isBulletColorType() {
        return bulletColorType;
    }

    public void setBulletColorType(int bulletColorType) {
        this.bulletColorType = bulletColorType;
    }
}
