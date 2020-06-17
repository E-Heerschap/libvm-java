package com.virtualmouse.vmnative;

import com.sun.jna.Structure;

@Structure.FieldOrder({"dx", "dy", "buttons"})
public class vmMouseEvent extends Structure{

    public int dx, dy;
    public Buttons.ByValue buttons;


    public static class ByValue extends vmMouseEvent implements Structure.ByValue {

        public vmMouseEvent toRef() {
            return new vmMouseEvent(this);
        }

        public ByValue(vmMouseEvent ref) {
            this.buttons = ref.buttons;
            this.dx = ref.dx;
            this.dy = ref.dy;
        }

    }

    public vmMouseEvent() { }

    public vmMouseEvent(vmMouseEvent.ByValue value) {
        this.dx = value.dx;
        this.dy = value.dy;
        this.buttons = value.buttons;
    }

    public vmMouseEvent.ByValue toVal() {
        return new vmMouseEvent.ByValue(this);
    }

    /*
        STATIC FUNCS BELOW
     */

    public static vmMouseEvent buildVmMouseEvent(final int dx, final int dy, final Buttons buttons) {
        return libvm.lib.buildMouseEvent(dx, dy, buttons.toVal()).toRef();
    }

    public static vmMouseEvent buildVmMouseEvent(final int dx, final int dy, final byte buttons) {
        return libvm.lib.buildMouseEvent(dx, dy, libvm.lib.buttonsFromByte(buttons)).toRef();
    }

    public static vmMouseEvent buildVmMouseEvent(final int dx, final int dy) {
        return libvm.lib.buildMouseEvent(dx, dy, Buttons.getNoneClick().toVal()).toRef();
    }

    public static vmMouseEvent getNoneEvent() {
        return libvm.lib.noneEvent().toRef();
    }

    public static vmMouseEvent getLeftDownEvent() {
        return libvm.lib.leftDownEvent().toRef();
    }

    public static vmMouseEvent getRightDownEvent() {
        return libvm.lib.rightDownEvent().toRef();
    }

    public static vmMouseEvent moveEvent(int x, int y) {
        return libvm.lib.moveEvent(x, y).toRef();
    }


}
