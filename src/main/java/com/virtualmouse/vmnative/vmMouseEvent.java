package com.virtualmouse.vmnative;

import com.sun.jna.Structure;
import com.virtualmouse.impl.Button;

public class vmMouseEvent extends Structure {

    private static final libvm clibvm = libvm.libvm;

    public static vmMouseEvent buildVmMouseEvent(final int dx, final int dy, final Buttons buttons) {
        return clibvm.buildMouseEvent(dx, dy, buttons);
    }

    public static vmMouseEvent buildVmMouseEvent(final int dx, final int dy, final byte buttons) {
        return clibvm.buildMouseEvent(dx, dy, clibvm.buttonsFromByte(buttons));
    }

    public static vmMouseEvent buildVmMouseEvent(final int dx, final int dy) {
        return clibvm.buildMouseEvent(dx, dy, Button.NONE_CLICK.button);
    }

    public int dx, dy;
    public Buttons buttons;

}
