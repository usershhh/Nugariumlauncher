package fr.nugarium.bootstrap;

import fr.theshark34.openlauncherlib.bootstrap.Bootstrap;
import fr.theshark34.openlauncherlib.bootstrap.LauncherClasspath;
import fr.theshark34.openlauncherlib.bootstrap.LauncherInfos;
import fr.theshark34.openlauncherlib.util.ErrorUtil;
import fr.theshark34.openlauncherlib.util.GameDir;
import fr.theshark34.openlauncherlib.util.SplashScreen;
import fr.theshark34.supdate.BarAPI;
import fr.theshark34.supdate.SUpdate;
import fr.theshark34.swinger.Swinger;
import fr.theshark34.swinger.colored.SColoredBar;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class NugariumBootstrap {

    private static SplashScreen splash;
    private static SColoredBar bar;
    private static Thread barThread;

    private static Image point1;
    private static Image point2;
    private static Image point3;

    private static final LauncherInfos SC_B_INFOS = new LauncherInfos("Nugarium", "fr.launcher.nugarium.LauncherFrame");
    private static final File SC_DIR = GameDir.createGameDir("nugarium");
    private static final LauncherClasspath SC_B_CP = new LauncherClasspath(new File(SC_DIR, "Launcher/launcher.jar"), new File(SC_DIR, "Launcher/Libs/"));

    private static ErrorUtil errorUtil = new ErrorUtil(new File(SC_DIR, "launcher/crashes/"));

    public static boolean launcherIsLaunch = false;

    public static void main(String[] args){

        Swinger.setResourcePath("/fr/nugarium/bootstrap/resources/");
        displaySplash();
        try {
            doUpdate();
        } catch (Exception e){
            errorUtil.catchError(e, "Impossible de mettre a jour le launcher !");
            barThread.interrupt();
        }

        try {
            launchLauncher();
            launcherIsLaunch = true;
        } catch (IOException e) {
            errorUtil.catchError(e, "Impossible de lancer le launcher !");
        }

        while (!launcherIsLaunch)
        {
            try {

                TimeUnit.MICROSECONDS.sleep(5);
                splash.getGraphics().drawImage(point1, 512,651,11,19,splash);
                TimeUnit.SECONDS.sleep(1);
                splash.getGraphics().drawImage(point2, 526,651,11,19,splash);
                TimeUnit.SECONDS.sleep(1);
                splash.getGraphics().drawImage(point3, 539,651,11,19,splash);
                TimeUnit.SECONDS.sleep(1);
                splash.getGraphics().drawImage(point1, 0,0,10,10,null);
                splash.getGraphics().drawImage(point2, 0,0,10,10,null);
                splash.getGraphics().drawImage(point3, 0,0,10,10,null);
                splash.repaint();
                TimeUnit.SECONDS.sleep(1);

            } catch (InterruptedException e) {
                launcherIsLaunch = true;
            }
        }
    }

    private static void displaySplash(){

        splash = new SplashScreen("Splash", Swinger.getResource("splash.png"));

        splash.setLayout(null);
        //splash.update(splash.getGraphics());
        // splash.setBackground(Swinger.TRANSPARENT);
        // splash.getContentPane().setBackground(Swinger.TRANSPARENT);

        bar = new SColoredBar(new Color(0, 0, 0, 150));
        bar.setBounds(0, 808, 586, 20);

        splash.setTitle("Nugarium Bootstrap");
        splash.setIconImage(Swinger.getResource("Nugar.png"));

        point1 = Swinger.getResource("point.png");
        point2 = Swinger.getResource("point.png");
        point3 = Swinger.getResource("point.png");

        splash.add(bar);
        splash.setVisible(true);

    }

    private static void doUpdate() throws Exception {

        SUpdate su = new SUpdate("http://vr.launcher.nugarium.com", new File(SC_DIR, "Launcher"));

        barThread = new Thread() {
            @Override
            public void run() {
                while (!this.isInterrupted()) {
                    bar.setValue((int) (BarAPI.getNumberOfTotalDownloadedBytes() / 1000));
                    bar.setMaximum((int) (BarAPI.getNumberOfTotalBytesToDownload() / 1000));
                }
            }
        };
        barThread.start();
        su.start();
        barThread.interrupt();
    }

    private static void launchLauncher() throws IOException {
        Bootstrap bootstrap = new Bootstrap(SC_B_CP, SC_B_INFOS);
        Process p = bootstrap.launch();
        splash.setVisible(false);

        try {
            System.exit(0);
            p.waitFor();
        } catch (InterruptedException e) {
            System.exit(0);
        }
    }
}


