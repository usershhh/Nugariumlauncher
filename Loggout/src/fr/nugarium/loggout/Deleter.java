package fr.nugarium.loggout;

import fr.theshark34.openlauncherlib.minecraft.GameInfos;
import fr.theshark34.openlauncherlib.minecraft.GameTweak;
import fr.theshark34.openlauncherlib.minecraft.GameType;
import fr.theshark34.openlauncherlib.minecraft.GameVersion;
import java.io.File;
import java.util.concurrent.TimeUnit;


public class Deleter {

    public static final GameVersion SC_VERSION = new GameVersion("1.12.2", GameType.V1_8_HIGHER);
    public static final GameInfos SC_INFOS = new GameInfos("nugarium", SC_VERSION, new GameTweak[] {GameTweak.FORGE});
    public static final File SC_DIR = SC_INFOS.getGameDir();

    public static void main(String[] args) {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        File properties = new File(SC_DIR, "launcher.properties");
        properties.delete();
    }
}