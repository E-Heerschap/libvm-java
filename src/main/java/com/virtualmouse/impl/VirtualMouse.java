package com.virtualmouse.impl;

import com.virtualmouse.vmnative.vmMouseEvent;
import com.virtualmouse.vmnative.libvm;
import com.virtualmouse.vmnative.vmMouseEvent;

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

        if (isOpen()) throw new VirtualMouseError("Already open!");

        if ((this.fileDescriptor = clibvm.getFileDesc(path)) < 0) {
            throw new IOException(String.format("VirtualMouse failed to open and retrieve" +
                    "a file descriptor. Error code: %d", fileDescriptor));
        }
    }

    public void close() {
        if (this.fileDescriptor >= 0) {
            clibvm.closeFileDesc(fileDescriptor);
        }
    }

    public int sendEvent(final vmMouseEvent event) throws IOException{
        throwIfNotOpen();
        return clibvm.fdSendIOCTLEvent(this.fileDescriptor, event);
    }

    public int sendEvents(final int file_desc, final vmMouseEvent[] event) throws IOException{
        throwIfNotOpen();
        return clibvm.fdSendIOCTLEvents(file_desc, event, event.length);
    }



}
