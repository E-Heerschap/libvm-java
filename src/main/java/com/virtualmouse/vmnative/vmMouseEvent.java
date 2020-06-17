package com.virtualmouse.vmnative;

import com.sun.jna.Structure;
import com.virtualmouse.impl.Button;

@Structure.FieldOrder({"dx", "dy", "buttons"})
public class vmMouseEvent extends Structure{

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
        return libvm.lib.buildMouseEvent(dx, dy, buttons.toVal()).toRef();
    }

    public static vmMouseEvent buildVmMouseEvent(final int dx, final int dy, final byte buttons) {
        return libvm.lib.buildMouseEvent(dx, dy, libvm.lib.buttonsFromByte(buttons)).toRef();
    }

    public static vmMouseEvent buildVmMouseEvent(final int dx, final int dy) {
        return libvm.lib.buildMouseEvent(dx, dy, Button.NONE_CLICK.button).toRef();
    }

    public int dx, dy;
    public Buttons.ByValue buttons;

}
