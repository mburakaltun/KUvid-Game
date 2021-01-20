package UI;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
    KuVidGameUI kuVidGameUI;

    public KeyInput(KuVidGameUI kuVidGameUI) {
        this.kuVidGameUI = kuVidGameUI;
    }

    public void keyPressed(KeyEvent e) {
        kuVidGameUI.keyPressed(e);
    }

    public void keyReleased(KeyEvent e) {
        kuVidGameUI.keyReleased(e);
    }
}