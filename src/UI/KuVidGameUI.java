package UI;

import Domain.MVC.GameController;
import Domain.MVC.Message;
import UI.Factory.UIObjectFactory;
import UI.GameUI.ShooterUI;
import UI.GameUI.StatisticsWindowUI;
import UI.GameUI.UIObject;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.TimerTask;
import java.util.Timer;


public class KuVidGameUI extends JPanel implements ActionListener {

    GameController gameController;
    ShooterUI shooterUI;
    UIObjectFactory uiObjectFactory;
    javax.swing.Timer timer;
    StatisticsWindowUI statisticsWindowUI;
    List<Message> domainMessageList;
    List<UIObject> gameObjects;
    int[] shooterInfo;
    int width;
    int height;
    private boolean[] shieldStatus;

    private class ShooterAnimationUpdateTask extends TimerTask {
        @Override
        public void run() {
            shooterUI.switchShooterType();
        }
    }

    public KuVidGameUI(int width, int height, GameController gameController) {
        this.gameController = gameController;
        uiObjectFactory = UIObjectFactory.getInstance();
        timer = new javax.swing.Timer(10, this);
        timer.start();
        setFocusable(true);
        shooterUI = new ShooterUI(width / 2, height-110);
        addKeyListener(new KeyInput(this));
        gameObjects = new LinkedList<>();
        this.width = width;
        this.height = height;
        domainMessageList = new LinkedList<Message>();
        Timer shooterAnimationTimer = new Timer();
        TimerTask shooterAnimationTimerTask = new ShooterAnimationUpdateTask();
        shooterAnimationTimer.schedule(shooterAnimationTimerTask, 30, 60);
        shieldStatus = new boolean[]{false, false, false, false};
    }

    public void paint(Graphics graphics) {
        super.paint(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.drawImage(getImage("/images/background.png"), 0, 0, width, height,null);
        shooterUI.draw(graphics2D);
        for (UIObject uiObject : gameObjects) {
            uiObject.draw(graphics2D);
        }
    }

    public Image getImage(String imagePath) {
        ImageIcon i = new ImageIcon(getClass().getResource(imagePath));
        return i.getImage();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        updateUIObjectFromMessageList();
        repaint();
    }

    public void updateMessageListFromObserver(List<Message> domainMessageList) {
        this.domainMessageList = domainMessageList;
    }

    public void updateShooterInfoFromObserver(int[] shooterInfo, boolean isAtom, int colorType) {
        this.shooterInfo = shooterInfo;
        shooterUI.updateParameters(shooterInfo[0], shooterInfo[1]);
        shooterUI.setBulletColorType(colorType);
        shooterUI.setBulletType(isAtom);
        //repaint();
    }

    private void updateUIObjectFromMessageList() {
        for (int i = 0; i < domainMessageList.size(); i++) {
        	Message message = domainMessageList.get(i);
            boolean updated = false;
            for (UIObject uiObject : gameObjects) {
                if (message.id == uiObject.getId()) {
                    uiObject.update((int) message.x, height - (int) message.y);
                    updated = true;
                    uiObject.setUpdateStatus(true);
                    break;
                }
            }
            if (!updated) {
                int objectType = message.objectType;
                int colorType = message.colorType;
                int patternType = message.patternType;
                UIObject newObject = uiObjectFactory.createUIObject(message.id, message.isShootersAtom,objectType, colorType, patternType, this);
                newObject.update((int) message.x, height - (int) message.y);
                gameObjects.add(newObject);
            }
        }

        Iterator<UIObject> uiObjectIterator = gameObjects.listIterator();
        UIObject currentUIObject;
        while (uiObjectIterator.hasNext()) {
            currentUIObject = uiObjectIterator.next();
            if (currentUIObject.isUpdated()) {
                currentUIObject.setUpdateStatus(false);
            } else {
                uiObjectIterator.remove();
            }
        }
    }

    public void setShieldStatus(boolean[] shieldStatus) {
        this.shieldStatus = shieldStatus;
    }

    public boolean[] getShieldStatus() {
        return shieldStatus;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_RIGHT) {
            gameController.slideDomainShooter(5);
        } else if (key == KeyEvent.VK_LEFT) {
            gameController.slideDomainShooter(-5);
        } else if (key == KeyEvent.VK_A) {
            gameController.rotateDomainShooter(5);
        } else if (key == KeyEvent.VK_D) {
            gameController.rotateDomainShooter(-5);
        } else if (key == KeyEvent.VK_SPACE) {
            gameController.shootDomainShooter();
        } else if (key == KeyEvent.VK_P) {
            gameController.pauseDomainGame();
        } else if (key == KeyEvent.VK_R) {
            gameController.resumeDomainGame();
        } else if (key == KeyEvent.VK_C) {
            gameController.switchShooterAtom();
        }
    }

    public void keyReleased(KeyEvent e) {

    }
}
