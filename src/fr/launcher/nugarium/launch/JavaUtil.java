package fr.launcher.nugarium.launch;

import java.lang.reflect.Field;

public class JavaUtil {
    public JavaUtil() {
    }

    public static String[] getSpecialArgs() {
        return new String[]{"-XX:-UseAdaptiveSizePolicy", "-XX:+UseConcMarkSweepGC"};
    }

    public static String macDockName(String name) {
        return "-Xdock:name=" + name;
    }

    public static String getJavaCommand() {
        return "\"C:\\Program Files (x86)\\Nugarium\\runtime\\java" + "\\bin\\java\"";
    }

    public static void setLibraryPath(String path) throws Exception {
        System.setProperty("java.library.path", path);
        Field fieldSysPath = ClassLoader.class.getDeclaredField("sys_paths");
        fieldSysPath.setAccessible(true);
        fieldSysPath.set((Object)null, (Object)null);
    }
}