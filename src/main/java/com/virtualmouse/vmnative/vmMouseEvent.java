package com.virtualmouse.vmnative;

import com.sun.jna.Structure;
import com.virtualmouse.impl.Button;

@Structure.FieldOrder({"dx", "dy", "buttons"})
public class vmMouseEvent extends Structure{

    private static libvm clibvm = LibLoader.getLibVm();

    public static class ByValue extends vmMouseEvent implements Structure.ByValue {

        public vmMouseEvent toRef() {
            return new vmMouseEvent(this);
        }

    }

    public vmMouseEvent() { }

    public vmMouseEvent(vmMouseEvent.ByValue value) {
        this.dx = value.dx;
        this.dy = value.dy;
        this.buttons = value.buttons;
    }

    public static vmMouseEvent buildVmMouseEvent(final int dx, final int dy, final Buttons buttons) {
        return clibvm.buildMouseEvent(dx, dy, buttons).toRef();
    }

    public static vmMouseEvent buildVmMouseEvent(final int dx, final int dy, final byte buttons) {
        return clibvm.buildMouseEvent(dx, dy, clibvm.buttonsFromByte(buttons).toRef()).toRef();
    }

    public static vmMouseEvent buildVmMouseEvent(final int dx, final int dy) {
        return clibvm.buildMouseEvent(dx, dy, Button.NONE_CLICK.button.toRef()).toRef();
    }

    public int dx, dy;
    public Buttons buttons;

}
