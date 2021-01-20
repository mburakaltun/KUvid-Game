/*
package Testing;

import UI.Factory.UIObjectFactory;
import UI.GameUI.UIObject;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UIObjectFactoryTest {
    // REQUIRES: objectType 0 <= objectType <= 3, colorType 0 <= colorType <= 3,
    // EFFECTS: Returns UIObject.id = id

    // BlackBox
    @Test
    public void createUIObjectTest1() {
        int objectType = 0;
        UIObject result = UIObjectFactory.createUIObject(0, objectType, 0, 0);
        assertEquals(result.getImagePath(), "/images/AlphaMoleculeStraight_texture.png");
    }

    // BlackBox
    @Test
    public void createUIObjectTest2() {
        int objectType = 3;
        UIObject result = UIObjectFactory.createUIObject(0, objectType, 0, 0);
        assertEquals(result.getImagePath(), "/images/AlphaAtom_texture.png");
    }

    // BlackBox
    @Test
    public void createUIObjectTest3() {
        int objectType = 2;
        UIObject result = UIObjectFactory.createUIObject(0, objectType, 0, 0);
        assertEquals(result.getImagePath(), "/images/AlphaAtom_texture.png");
    }

    // BlackBox
    @Test
    public void createUIObjectTest4() {
        int colorType = 0;
        UIObject result = UIObjectFactory.createUIObject(0, 0, colorType, 0);
        assertEquals(result.getImagePath(), "/images/AlphaMoleculeStraight_texture.png");
    }

    // BlackBox
    @Test
    public void createUIObjectTest5() {
        int colorType = 3;
        UIObject result = UIObjectFactory.createUIObject(0, 0, colorType, 0);
        assertEquals(result.getImagePath(), "/images/GammaMoleculeStraight_texture.png");
    }
}
*/
