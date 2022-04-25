package controller.factorias;

import controller.ADMenu;
import controller.interfaces.Menuable;

public abstract class MenuADFactory {

    private static Menuable m = new ADMenu();

    public static Menuable getAccessMenus() {
        return m;
    }
}
