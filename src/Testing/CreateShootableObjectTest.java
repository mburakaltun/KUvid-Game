package Testing;

import Domain.Factories.GameObjectFactory;
import Domain.Interfaces.IShootable;
import Domain.Primitives.Shootables.AtomBullet;
import Domain.Primitives.Shootables.PowerUpBullet;
import org.junit.Test;
import static org.junit.Assert.*;

public class CreateShootableObjectTest {
    // REQUIRES: type 0 <= type <= 3
    // EFFECTS: Returns IShootable depending on objectName variable.

    GameObjectFactory gameObjectFactory = GameObjectFactory.getInstance();

    // GlassBox
    @Test
    public void createShootableObjectTest1() {
        String objectName = "AtomBullet";
        IShootable result = gameObjectFactory.createShootableObject(objectName, 0, 10, 10, 45, 100);
        assertEquals(result instanceof AtomBullet, true);
    }

    // GlassBox
    @Test
    public void createShootableObjectTest2() {
        String objectName = "PowerUpBullet";
        IShootable result = gameObjectFactory.createShootableObject(objectName, 0, 10, 10, 45, 100);
        assertEquals(result instanceof PowerUpBullet, true);
    }

    // GlassBox
    @Test
    public void createShootableObjectTest3() {
        String objectName = "AtomBullet";
        IShootable result = gameObjectFactory.createShootableObject(objectName, 0, 10, 10, 45, 100);
        assertTrue(result.getType().equals("Alpha"));
    }

    // BlackBox
    @Test
    public void createShootableObjectTest4() {
        String objectName = "AtomBullet";
        IShootable result = gameObjectFactory.createShootableObject(objectName, 3, 10, 10, 45, 100);
        assertTrue(result.getType().equals("Sigma"));
    }

    // BlackBox
    @Test
    public void createShootableObjectTest5() {
        String objectName = "AtomBullet";
        IShootable result = gameObjectFactory.createShootableObject(objectName, 2, 10, 10, 45, 100);
        assertTrue(result.getType().equals("Gamma"));
    }

}
