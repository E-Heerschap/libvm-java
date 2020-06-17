package com.virtualmouse.vmnative;

import com.sun.jna.Structure;

@Structure.FieldOrder({"_buttons"})
public class Buttons extends Structure {

    public byte _buttons;

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
     *
     * @return byte representation of buttons.
     * As representation may change in the future this
     * provides a constant assuming under 7 buttons are used.
     */
    byte toByte() {

        //Instead of returning _buttons, the lib is
        //used for future proofing :)
        return libvm.lib.buttonsToByte(this);

    }

    //TODO test this. If fail, maybe requires read() ?
    void setFromByte(byte buttons) {
        libvm.lib.setButtonsFromByte(this, buttons);
    }




    /*
        STATIC FUNCTIONS
     */

    public static Buttons addButtons(Buttons b1, Buttons b2) {
        return libvm.lib.addButtons(b1, b2).toRef();
    }

    public static Buttons getRightClick() {
        return libvm.lib.getRightClick().toRef();
    }

    public static Buttons getLeftClick() {
        return libvm.lib.getLeftClick().toRef();
    }

    public static Buttons getLeftAndRightClick() {
        return addButtons(getRightClick(), getLeftClick());
    }

    public static Buttons getNoneClick() {
        return libvm.lib.buttonsFromByte((byte) 0).toRef();
    }

}
