package com.virtualmouse.vmnative;

import com.sun.jna.Native;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class LibLoader {

    /**
     * Purpose of this class is to provide means for any class to set a static
     * field containing the lib reference. This was required because
     * occasionally setting a static field, lib, in an arbitrary class, C,
     * C.lib would be null if set before the library has loaded. Instead of checking
     * for loaded status each time, or making the end user developer call a load() func,
     * or finally accessing only the single static loaded variable (in this case LibLoader.lib,
     * if it were public) these methods can be used. The other approaches (imo)
     * are a code smell. This instead does it automatically and ensures a single libvm
     * variable that is loaded.
     *
     * Couldn't find anything on a load() callback for JNA so this
     * implementation  will do for now.
     */

    private static libvm lib;
    private static List<Field> registerList = new ArrayList<>();
    private static boolean loaded = false;
    static {
        load();
    }

    public static void load() {
        lib = (libvm) Native.load("vm", libvm.class);
        loaded = true;
    }

    private static void setLibField(Field f, Object object) {
        boolean access = f.isAccessible();
        //boolean access = f.canAccess(object); Some impl doesn't work with this
        f.setAccessible(true);
        try {
            f.set(object, lib);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        f.setAccessible(access);
    }

    public static void registerStaticLibRef(Field f) {
        if (hasLoaded()) {
            setLibField(f, null);
        } else {
            registerList.add(f);
        }
    }

    public static void registerStaticLibRef(Class<?> c, String field) {
        Field f = null;
        try {
            f = c.getDeclaredField(field);
            registerStaticLibRef(f);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public static libvm getLibVm() {
        if(lib == null) {
            load();
        }
        return lib;
    }

    public static boolean hasLoaded() {
        return loaded;
    }

}
