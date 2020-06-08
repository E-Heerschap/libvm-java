package com.virtualmouse.impl;

import com.virtualmouse.vmnative.MouseEvent;
import com.virtualmouse.vmnative.libvm;

import java.io.IOException;

public class VirtualMouse {

    private static final libvm clibvm = libvm.libvm;

    private int fileDescriptor = -1;

    public VirtualMouse(String path) throws IOException {
               this.open(path);
    }

    private void throwIfNotOpen() throws IOException{
        if (!isOpen()) throw new IOException("VirtualMouse object has no open file descriptor");
    }

    public boolean isOpen() { return fileDescriptor >= 0; }

    public void open(String path) throws IOException{
        if ((this.fileDescriptor = clibvm.getFileDesc(path)) < 0) {
            throw new IOException("VirtualMouse failed to open and retrieve" +
                    "a file descriptor.");
        };
    }

    public void close() {
        if (this.fileDescriptor >= 0) {
            clibvm.closeFileDesc(fileDescriptor);
        }
    }

    int sendEvent(final MouseEvent event) throws IOException{
        throwIfNotOpen();
        return clibvm.fdSendIOCTLEvent(this.fileDescriptor, event);
    }

    int sendEvents(final int file_desc, final MouseEvent[] event) throws IOException{
        throwIfNotOpen();
        return clibvm.fdSendIOCTLEvents(file_desc, event, event.length);
    }



}
