package Testing;

import Domain.Factories.GameObjectFactory;
import Domain.Interfaces.IMovable;
import Domain.Primitives.Movables.Molecule;
import Domain.Primitives.Movables.PowerUp;
import Domain.Primitives.Movables.ReactionBlocker;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CreateMovableObjectTest {
    // REQUIRES: type 0 <= type <= 3, 2 <= atomCount <= 6
    // EFFECTS: Returns IMovable depending on objectName variable.

    GameObjectFactory gameObjectFactory = GameObjectFactory.getInstance();

    // GlassBox
    @Test
    public void createMovableObjectTest1() {
        String objectName = "Molecule";
        IMovable result = gameObjectFactory.createMovableObject(objectName, 0, 2, 10, 10, "ZigZag");
        assertEquals(result instanceof Molecule, true);
    }

    // GlassBox
    @Test
    public void createMovableObjectTest2() {
        String objectName = "PowerUp";
        IMovable result = gameObjectFactory.createMovableObject(objectName, 0, 2, 10, 10, "ZigZag");
        assertEquals(result instanceof PowerUp, true);
    }

    // GlassBox
    @Test
    public void createMovableObjectTest3() {
        String objectName = "ReactionBlocker";
        IMovable result = gameObjectFactory.createMovableObject(objectName, 0, 2, 10, 10, "ZigZag");
        assertEquals(result instanceof ReactionBlocker, true);
    }

    // BlackBox
    @Test
    public void createMovableObjectTest4() {
        String objectName = "Molecule";
        Molecule result = (Molecule) gameObjectFactory.createMovableObject(objectName, 0, 2, 10, 10, "ZigZag");
        assertEquals(result.getAtomCount(), 2);
    }

    // BlackBox
    @Test
    public void createMovableObjectTest5() {
        String objectName = "Molecule";
        Molecule result = (Molecule) gameObjectFactory.createMovableObject(objectName, 0, 6, 10, 10, "ZigZag");
        assertEquals(result.getAtomCount(), 6);
    }

}
