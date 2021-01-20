package Domain;

import Domain.Interfaces.IMovable;
import Domain.Interfaces.IShootable;
import Domain.MVC.Message;
import Domain.Primitives.Element;
import Domain.Primitives.Shooter;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class GameObjects {

    private List<IMovable> movableList;
    private List<IShootable> bulletList;
    private List<Message> messageList;
    private IShootable shootersBullet;
    private StatisticsWindow statsWindow;
    private Shooter shooter;
    private float l = 0;

    private final float windowHeight;
    private final float windowWidth;

    public GameObjects(StatisticsWindow statsWindow, float windowHeight, float windowWidth, IShootable initialAtom, Shooter shooter) {
        movableList = new LinkedList<>();
        bulletList = new LinkedList<>();
        messageList = new LinkedList<>();
        this.statsWindow = statsWindow;
        this.shootersBullet = initialAtom;
        this.shooter = shooter;
        this.windowHeight = windowHeight;
        this.windowWidth = windowWidth;
    }

    public synchronized void addMovable(IMovable m) {
        movableList.add(m);
    }

    public void update(float stepSize, float effectiveRadius) {
        stepAll(stepSize);
        collideAll(effectiveRadius);
        compileIntoMessage();
    }

    private void stepAll(float stepSize) { //moves every single object in the game except shooter's bullet
        Iterator<IShootable> bulletIterator = bulletList.listIterator();
        Iterator<IMovable> elemIterator = movableList.listIterator();
        IShootable currentBullet;
        IMovable currentMovable;

        while (bulletIterator.hasNext()) {
            currentBullet = bulletIterator.next();
            currentBullet.step(stepSize);
            if (currentBullet.getPosition()[1] > this.windowHeight) {
                bulletIterator.remove();
            }
        }

        while (elemIterator.hasNext()) {
            currentMovable = elemIterator.next();
            currentMovable.step(stepSize);
            if (currentMovable.isMolecule() || currentMovable.isReactionBlocker()) {
                if (currentMovable.getPosition()[1] < 0) {
                    currentMovable.removeOperation(statsWindow, shooter, l);
                    elemIterator.remove();
                }
            } else {
                if (currentMovable.getPosition()[1] < 110) {
                    int powerUpStatus = currentMovable.removeOperation(statsWindow, shooter, l);
                    if (powerUpStatus == 1) {
                        elemIterator.remove();
                    }
                } else if(currentMovable.getPosition()[1] < 0) {
                    elemIterator.remove();
                }
            }

        }
    }

    private void collideAll(float effectiveRadius) {
        LinkedList<IShootable> tempShootableList;
        tempShootableList = (LinkedList<IShootable>) ((LinkedList<IShootable>) bulletList).clone();
        Iterator<IShootable> bulletIterator = tempShootableList.listIterator();
        Iterator<IMovable> elemIterator;
        IShootable currentBullet;

        while (bulletIterator.hasNext()) {
            currentBullet = bulletIterator.next();
            elemIterator = movableList.listIterator();

            while (elemIterator.hasNext()) {
                IMovable currentMovable = elemIterator.next();

                if (currentBullet.hitCheck(currentMovable, effectiveRadius)) {
                    currentMovable.modifyStatisticsWindow(statsWindow, currentBullet.getEfficiency());
                    elemIterator.remove();
                    bulletIterator.remove();
                    break;
                }
            }
        }
        setBulletList(tempShootableList);
    }

    private void compileIntoMessage() {
        LinkedList<IMovable> tempMovableList;
        LinkedList<IShootable> tempShootableList;
        tempMovableList = (LinkedList<IMovable>) ((LinkedList) movableList).clone();
        tempShootableList = (LinkedList<IShootable>) ((LinkedList<IShootable>) bulletList).clone();

        for (IMovable e : tempMovableList) {
            updateMessageForElement((Element) e);
        }

        for (IShootable e : tempShootableList) {
            updateMessageForElement(e);
        }
        updateMessageForElement(this.shootersBullet);
        deleteUnchangedMessages();
    }

    private void updateMessageForElement(Element e) {
        Iterator<Message> messageIterator = messageList.listIterator();
        Message currentMessage;

        while (messageIterator.hasNext()) {
            currentMessage = messageIterator.next();
            if (currentMessage.id == e.getId()) {
                currentMessage.updateMessage(e.getPosition());
                return;
            }
        }
        messageList.add(generateMessage(e));
    }

    private void updateMessageForElement(IShootable e) {
        Iterator<Message> messageIterator = messageList.listIterator();
        Message currentMessage;

        while (messageIterator.hasNext()) {
            currentMessage = messageIterator.next();
            if (currentMessage.id == e.getId()) {
                currentMessage.updateMessage(e.getPosition());
                return;
            }
        }
        messageList.add(generateMessage(e));
    }

    private Message generateMessage(Element e) {
        return e.formatIntoMessage();
    }

    private Message generateMessage(IShootable e) {
        return e.formatIntoMessage();
    }

    private void deleteUnchangedMessages() {
        Iterator<Message> messageIterator = this.messageList.listIterator();
        Message currentMessage;

        while (messageIterator.hasNext()) {
            currentMessage = messageIterator.next();
            if (currentMessage.isUpdated()) {
                currentMessage.resetFlag();
            } else {
                messageIterator.remove();
            }
        }
    }

    public void shootShootersBullet() {
        if(shooter.isBulletAvailable()) {
            this.bulletList.add(this.shootersBullet);
            this.shootersBullet.allowMovement();
            shooter.setBulletAvailable(false);
        }
    }

    public void assignShootersBullet(IShootable bullet) {
        this.shootersBullet = bullet;
    }

    public List<Message> getMessageList() {
        return this.messageList;
    }

    public List<IMovable> getMovableList() {
        return movableList;
    }

    public List<IShootable> getShootableList() {
        return bulletList;
    }

    public void setMovableList(List<IMovable> movableList) {
        this.movableList = movableList;
    }

    public synchronized void setBulletList(List<IShootable> bulletList) {
        this.bulletList = bulletList;
    }

    public void setL(float l) {
        this.l = l;
    }

}
