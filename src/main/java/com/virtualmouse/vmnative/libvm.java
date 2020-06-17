package com.virtualmouse.vmnative;

import com.sun.jna.Library;
import com.sun.jna.Native;

/**
 * Contains the interface for JNA to map the libvm library too.
 * Ideally this should NOT be used directly as semantics and types
 * may change over time. It is also more error prone to use this directly.
 * Instead, use the classes in the impl package to perform actions
 * using the libvm.
 */
public interface libvm extends Library {

    public static libvm lib = (libvm) Native.load("vm", libvm.class);

    /**
     * Opens the file for the process and returns the file descriptor.
     * @param path Path to the file. In this case use the device node.
     * @return file descriptor for the file.
     */
    int getFileDesc(String path);

    /**
     * Closes the file descriptor passed.
     * @param fd File descriptor
     */
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

    /**
     * Sets the buttons from the passed byte representation.
     * @param but Buttons object to modify
     * @param b Byte
     */
    void setButtonsFromByte(Buttons but, byte b);

    /**
     * Creates a Buttons object from a byte representation.
     * @param b byte representation of buttons pushed.
     * @return Buttons object from b
     */
    Buttons.ByValue buttonsFromByte(byte b);

    /**
     * Adds two buttons together and returns a new Buttons object (Value).
     * b1 and b2 stay unchanged.
     * @param b1 First set of buttons
     * @param b2 Second set of buttons
     * @return Two buttons sets union.
     */
    Buttons.ByValue addButtons(Buttons b1, Buttons b2);

    /*
        !!!IMPORTANT!!!
        The VirtualMouse library must be compiled with the EXPORT_GET_BUTTONS
        flag. This compiles the getRightClick and getLeftClick functions.
     */

    /**
     *
     * @return a button set containing only a right click.
     */
    Buttons.ByValue getRightClick();

    /**
     *
     * @return a button set containing only a left click.
     */
    Buttons.ByValue getLeftClick();

    /**
     * Sends an VM_IOCTL_MOUSE_EVENT (Defined in vmioctl.h in lib implementation)
     * with event as the passed mouse event.
     * @param file_desc File descriptor of the open virtual mouse device node.
     *                  This can be obtained by calling libvm.getFileDesc.
     * @param event Virtual mouse event to run
     * @return Status of the event. This corresponds to linux error codes.
     */
    int fdSendIOCTLEvent(int file_desc, final vmMouseEvent event);

    /**
     * Sends events using VM_IOCTL_MOUSE_EVENT (Defined in vmioctl.h in lib implementation)
     * with event as the passed mouse events.
     * @param file_desc File descriptor of the open virtual mouse device node.
     *                  This can be obtained by calling libvm.getFileDesc.
     * @param event Virtual mouse events to run
     * @param size Number of events in event. (Usually event.length)
     * @return Status of the event. This corresponds to linux error codes.
     */
    int fdSendIOCTLEvents(int file_desc, final vmMouseEvent[] event, final int size);

    vmMouseEvent.ByValue addMouseEvents(final vmMouseEvent ev1, final vmMouseEvent ev2);

    vmMouseEvent.ByValue buildMouseEvent(final int dx, final int dy, Buttons.ByValue button);

    vmMouseEvent.ByValue noneEvent();

    vmMouseEvent.ByValue leftDownEvent();

    vmMouseEvent.ByValue rightDownEvent();

    vmMouseEvent.ByValue moveEvent(int x, int y);

    int fdMoveMouse(int file_desc, int x, int y);

    int fdRightClick(int file_desc);

    int fdLeftClick(int file_desc);
}
