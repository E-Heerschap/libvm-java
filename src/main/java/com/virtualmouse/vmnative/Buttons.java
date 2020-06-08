package com.virtualmouse.vmnative;

import com.sun.jna.Structure;

public class Buttons extends Structure {

    public Buttons(byte buttons) {
        this._buttons = buttons;
    }

    /**
     * Do not access directly. Only public for use with JNA.
     * Does private work?
     */
    public byte _buttons;

    private static libvm clibvm = libvm.libvm;

}
