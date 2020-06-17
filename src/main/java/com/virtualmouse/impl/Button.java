package com.virtualmouse.impl;

import com.virtualmouse.vmnative.Buttons;
import com.virtualmouse.vmnative.libvm;

public enum Button {

    RIGHT_CLICK(libvm.lib.getRightClick()), //Explicit 1. Button is done by bit. 1 << button
    LEFT_CLICK(libvm.lib.getLeftClick()), // Third button. By integer it is 4.
    //LEFT_AND_RIGHT_CLICK(libvm.libvm.addButtons(RIGHT_CLICK.button, LEFT_CLICK.button)),
    NONE_CLICK(libvm.lib.buttonsFromByte((byte) 0));



    public final Buttons.ByValue button;
    private Button(Buttons.ByValue button) {
        this.button = button;
    }
}
