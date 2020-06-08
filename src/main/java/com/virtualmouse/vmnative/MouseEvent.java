package com.virtualmouse.vmnative;

import com.sun.jna.Structure;
import com.virtualmouse.vmnative.Buttons;

public class MouseEvent extends Structure {

    public MouseEvent(int dx, int dy, Buttons buttons) {
        this.dx = dx;
        this.dy = dy;
        this.buttons = buttons;
    }

    public MouseEvent(int dx, int dy, byte buttons) {
        this.dx = dx;
        this.dy = dy;

        this.buttons = libvm.libvm.buttonsFromByte(buttons);
    }

    public int dx, dy;
    public Buttons buttons;

}
