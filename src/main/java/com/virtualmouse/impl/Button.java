package com.virtualmouse.impl;

import com.virtualmouse.vmnative.Buttons;
import com.virtualmouse.vmnative.libvm;

public enum Button {

    RIGHT_CLICK(libvm.libvm.getRightClick()), //Explicit 1. Button is done by bit. 1 << button
    LEFT_CLICK(libvm.libvm.getLeftClick()), // Third button. By integer it is 4.
    LEFT_AND_RIGHT_CLICK(libvm.libvm.addButtons(RIGHT_CLICK.button, LEFT_CLICK.button)),
    NONE_CLICK(libvm.libvm.buttonsFromByte((byte) 0));

    private static libvm clibvm = libvm.libvm;
    public final Buttons button;
    private Button(Buttons button) {
        this.button = button;
    }

}
