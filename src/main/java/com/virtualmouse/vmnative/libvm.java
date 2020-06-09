package com.virtualmouse.vmnative;

import com.sun.jna.Library;
import com.sun.jna.Native;

public interface libvm extends Library {

    final libvm libvm = (libvm) Native.load("vm", libvm.class);

    /**
     * Opens the file for the process and returns the file descriptor.
     * @param path Path to the file. In this case use the device node.
     * @return file descriptor for the file.
     */
    int getFileDesc(String path);

    void closeFileDesc(int fd);

    /**
     * Combines param add into param base. Base will be changed.
     * Similar to addButtons except the base is changed and no new
     * button is created.
     * @param base base button to add param add too.
     * @param add buttons to add to base. (multiple add semantics here...).
     */
    void combineButton(Buttons base, Buttons add);

    /**
     * Converts the buttons to a byte representation.
     * Only the first 8 buttons can be represented here.
     * @param b Buttons to convert to byte representation.
     * @return byte representation of buttons.
     */
    byte buttonsToByte(Buttons b);

    void setButtonsFromByte(Buttons but, byte b);

    Buttons buttonsFromByte(byte b);

    Buttons addButtons(Buttons b1, Buttons b2);

    /*
        !!!IMPORTANT!!!
        The VirtualMouse library must be compiled with the EXPORT_GET_BUTTONS
        flag. This declares the getRightClick and getLeftClick functions.
     */

    Buttons getRightClick();

    Buttons getLeftClick();

    int fdSendIOCTLEvent(int file_desc, final vmMouseEvent event);

    int fdSendIOCTLEvents(int file_desc, final vmMouseEvent[] event, final int size);

    vmMouseEvent addMouseEvents(final vmMouseEvent ev1, final vmMouseEvent ev2);

    vmMouseEvent buildMouseEvent(final int dx, final int dy, final Buttons button);

}
