/*
package Testing;

import Domain.Primitives.Shootables.AtomBullet;
import Domain.Primitives.Movables.Molecule;
import Domain.Primitives.Movables.PowerUp;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class HitCheckTest {
    // REQUIRES: effective radius > 0
    // EFFECTS: return 0 if euclidean distance is between first argument's position and object's position smaller than 0
    // and types of both of these objects are equal

    AtomBullet atomBullet = new AtomBullet(0, 0, 0, 0, 0, 100);

    // BlackBox
    @Test
    public void hitCheckTest1() {
        int effectiveRadius = 5;
        Molecule molecule = new Molecule(0, 0, 5, 5, 5, "ZigZag");
        boolean result = atomBullet.hitCheck(molecule, effectiveRadius);
        assertFalse(result);
    }

    // BlackBox
    @Test
    public void hitCheckTest2() {
        int effectiveRadius = 5;
        Molecule molecule = new Molecule(0, 0, 1, 1, 5, "ZigZag");
        boolean result = atomBullet.hitCheck(molecule, effectiveRadius);
        assertTrue(result);
    }

    // BlackBox
    @Test
    public void hitCheckTest3() {
        int effectiveRadius = 5;
        Molecule molecule = new Molecule(0, 1, 1, 1, 5, "ZigZag");
        boolean result = atomBullet.hitCheck(molecule, effectiveRadius);
        assertFalse(result);
    }

    // BlackBox
    @Test
    public void hitCheckTest4() {
        int effectiveRadius = 5;
        Molecule molecule = new Molecule(0, 0, 5, 5, 5, "ZigZag");
        boolean result = atomBullet.hitCheck(molecule, effectiveRadius);
        assertFalse(result);
    }

    // BlackBox
    @Test
    public void hitCheckTest5() {
        int effectiveRadius = 5;
        PowerUp powerUp = new PowerUp(0, 0, 1, 1);
        boolean result = atomBullet.hitCheck(powerUp, effectiveRadius);
        assertTrue(result);
    }
}
*/
