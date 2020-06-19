# libvm-java
A java library to interact with the [VirtualMouse libvm](https://github.com/kingpulse/VirtualMouse). Uses JNA to access libvm.

## Build
Build using the shadowJar gradle task.

## Executing
The libvm library must either be placed in a system library path or specified using the system variable `jna.library.path`. 
For more information on library loading see [Loading JNA](https://java-native-access.github.io/jna/4.2.1/overview-summary.html#loading).
An example execution with the library in the working directory would be as follows:
`java -cp ".:libVHJava-1.0-SNAPSHOT-all.jar" -Djna.library.path=$(pwd) example`
