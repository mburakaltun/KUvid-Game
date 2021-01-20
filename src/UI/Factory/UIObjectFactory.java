package UI.Factory;

import UI.GameUI.UIObject;
import UI.KuVidGameUI;

public class UIObjectFactory {
    private static UIObjectFactory uiObjectFactory;

    private UIObjectFactory() {

    }

    public static UIObjectFactory getInstance() {
        if (uiObjectFactory == null) {
            uiObjectFactory = new UIObjectFactory();
        }
        return uiObjectFactory;
    }

    public static UIObject createUIObject(long id, boolean isShootersAtom, int objectType, int colorType, int patternType, KuVidGameUI kuVidGameUI) {
        // REQUIRES: objectType 0 <= objectType <= 3, colorType 0 <= colorType <= 3,
        // EFFECTS: Returns UIObject.id = id
        UIObject uiObject;

        String path = "/images/AlphaAtom_texture.png";

        switch (objectType) {
            case 0:
                switch (colorType) {
                    case 0:
                        if (patternType == 0) {
                            path = "/images/AlphaMoleculeStraight_texture.png";
                        } else {
                            path = "/images/AlphaMoleculeZigZag_texture.png";
                        }
                        break;
                    case 1:
                        if (patternType == 0) {
                            path = "/images/BetaMoleculeStraight_texture.png";
                        } else {
                            path = "/images/BetaMoleculeZigZag_texture.png";
                        }
                        break;
                    case 2:
                        path = "/images/GammaMoleculeStraight_texture.png";
                        break;
                    case 3:
                        path = "/images/SigmaMoleculeStraight_texture.png";
                        break;
                    default:
                        path = "/images/AlphaMoleculeStraight_texture.png";
                        break;
                }
                break;
            case 1:
                switch (colorType) {
                    case 0:
                        path = "/images/AlphaReactionBlocker_texture.png";
                        break;
                    case 1:
                        path = "/images/BetaReactionBlocker_texture.png";
                        break;
                    case 2:
                        path = "/images/GammaReactionBlocker_texture.png";
                        break;
                    case 3:
                        path = "/images/SigmaReactionBlocker_texture.png";
                        break;
                    default:
                        path = "/images/AlphaReactionBlocker_texture.png";
                        break;
                }
                break;
            case 2:
            case 4:
                switch (colorType) {
                    case 0:
                        path = "/images/AlphaPowerUp_texture.png";
                        break;
                    case 1:
                        path = "/images/BetaPowerUp_texture.png";
                        break;
                    case 2:
                        path = "/images/GammaPowerUp_texture.png";
                        break;
                    case 3:
                        path = "/images/SigmaPowerUp_texture.png";
                        break;
                    default:
                        path = "/images/AlphaPowerUp_texture.png";
                        break;
                }
                break;
            case 3:
                switch (colorType) {
                    case 0:
                        path = "/images/AlphaAtom_texture.png";
                        break;
                    case 1:
                        path = "/images/BetaAtom_texture.png";
                        break;
                    case 2:
                        path = "/images/GammaAtom_texture.png";
                        break;
                    case 3:
                        path = "/images/SigmaAtom_texture.png";
                        break;
                    default:
                        path = "/images/AlphaAtom_texture.png";
                        break;
                }
                break;
            default:
                path = "/images/AlphaMoleculeZigZag_texture.png";
                break;
        }
        uiObject = new UIObject(id, 0, 0, path, isShootersAtom, kuVidGameUI);
        return uiObject;
    }
}
