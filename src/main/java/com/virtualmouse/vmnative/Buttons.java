package com.virtualmouse.vmnative;

import com.sun.jna.Structure;

@Structure.FieldOrder({"_buttons"})
public class Buttons extends Structure {

    public static class ByValue extends Buttons implements Structure.ByValue {

        public ByValue() { }

        public ByValue(byte buttons) {
            this._buttons = buttons;
        }

        /**
         * This is used when a Buttons * struct is required but only the
         * Buttons.ByValue is present.
         * @return Returns a new Buttons class created from the Buttons.ByValue class.
         */
        public Buttons toRef() {
            return new Buttons(this);
        }

    }

    public Buttons() {}

    public Buttons(Buttons.ByValue value) {
        this._buttons = value._buttons;
    }

    public Buttons(byte buttons) {
        this._buttons = buttons;
    }

    Buttons.ByValue toVal() {
        return new Buttons.ByValue(this._buttons);
    }

    /**
     * Do not access directly. Only public for use with JNA.
     * Does private work?
     */
    public byte _buttons;

}
