package fr.launcher.nugarium;

import fr.launcher.nugarium.ddb.ddb;
import fr.launcher.nugarium.options.ram.RamSelector;
import fr.theshark34.openlauncherlib.util.CrashReporter;
import fr.theshark34.openlauncherlib.util.Saver;
import fr.theshark34.swinger.Swinger;
import fr.theshark34.swinger.colored.SColoredBar;
import fr.theshark34.swinger.event.SwingerEvent;
import fr.theshark34.swinger.event.SwingerEventListener;
import fr.theshark34.openlauncherlib.LaunchException;
import fr.theshark34.swinger.textured.STexturedButton;
import sun.misc.Launcher;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import static fr.launcher.nugarium.launcher.*;

@SuppressWarnings("serrial")
public class HomePanel extends JPanel implements SwingerEventListener {



    public static Saver saver = new Saver(new File(launcher.SC_DIR, "launcher.properties"));

    public static Image background = Swinger.getResource("Fondlauncherhome.png");
    public static Image Head;

    public static Image etats_on_lobby = Swinger.getResource("on.png");
    public static Image etat_off_lobby = Swinger.getResource("off.png");
    public static Image etats_on_freebuild = Swinger.getResource("on.png");
    public static Image etats_off_freebuild = Swinger.getResource("off.png");
    public static Image etats_on_guild = Swinger.getResource("on.png");
    public static Image etats_off_guild = Swinger.getResource("off.png");

    public static Image option_display = Swinger.getResource("option_frame.png");




    public static RamSelector ramSelector = new RamSelector(new File(launcher.SC_DIR, "ram.txt"));

    public STexturedButton quitButton = new STexturedButton(Swinger.getResource("quit.png"));
    public STexturedButton radusbutton = new STexturedButton(Swinger.getResource("iconised.png"));
    public STexturedButton ramButton = new STexturedButton(Swinger.getResource("settings_button.png"));
    public STexturedButton playButton = new STexturedButton(Swinger.getResource("play_button.png"));
    public STexturedButton disconnectButton = new STexturedButton(Swinger.getResource("exit.png"));


    public SColoredBar progresseBar = new SColoredBar(new Color(43, 142, 188, 80));
    public JLabel infolabel = new JLabel("", SwingConstants.CENTER);
    public static JLabel s, etat_lobby, etat_freebuild, etat_guild, player_lobby, player_freebuild, player_guild, template1, slash1, template2, slash2, template3, slash3, raison, wlraison;


    private static String keyWordTolookFor = "average";

    public static boolean statue = false;
    public static boolean statue2 = true;
    public static HomePanel instance = new HomePanel();


    public HomePanel() {


        InputStream is = null;
        Font customFont = null;
        GraphicsEnvironment ge = null;
        is = LauncherPanel.class.getResourceAsStream("resources/Minecraft.ttf");
        try {
            customFont = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(20);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(customFont);

        this.setLayout(null);


        this.playButton.setBounds(32, 745);
        this.playButton.setSize(197, 50);
        this.playButton.addEventListener(this);

        this.disconnectButton.setBounds(260, 20);
        this.disconnectButton.setSize(46, 46);
        this.disconnectButton.addEventListener(this);

        this.ramButton.setBounds(229, 745);
        this.ramButton.setSize(50, 50);
        this.ramButton.addEventListener(this);

        this.radusbutton.setBounds(1255, 0);
        this.radusbutton.setSize(15, 15);
        this.radusbutton.addEventListener(this);

        this.quitButton.setBounds(1280, 0);
        this.quitButton.setSize(15, 15);
        this.quitButton.addEventListener(this);

        this.progresseBar.setBounds(316, 830, 1300, 30);


        //USERNAME
        s = new JLabel(launcher.username_player);
        s.setBounds(12, 187, 290, 45);
        s.setForeground(Color.white);
        s.setFont(customFont);
        s.setOpaque(false);
        s.setFont(s.getFont().deriveFont(33.72f));

        if (Ping.lobby == true) {
            etat_lobby = new JLabel("Online");
        } else {
            etat_lobby = new JLabel("Offline");
        }
        etat_lobby.setBounds(41, 295, 290, 45);
        etat_lobby.setForeground(Color.white);
        etat_lobby.setFont(customFont);
        etat_lobby.setFont(etat_lobby.getFont().deriveFont(14f));

        if (Ping.freebuild == true) {
            etat_freebuild = new JLabel("Online");
        } else {
            etat_freebuild = new JLabel("Offline");
        }
        etat_freebuild.setBounds(41, 390, 290, 45);
        etat_freebuild.setForeground(Color.white);
        etat_freebuild.setFont(customFont);
        etat_freebuild.setFont(etat_freebuild.getFont().deriveFont(14f));

        if (Ping.guild == true) {
            etat_guild = new JLabel("Online");
        } else {
            etat_guild = new JLabel("Offline");
        }
        etat_guild.setBounds(41, 483, 290, 45);
        etat_guild.setForeground(Color.white);
        etat_guild.setFont(customFont);
        etat_guild.setFont(etat_guild.getFont().deriveFont(14f));


        template1 = new JLabel("120");
        template1.setBounds(255, 295, 290, 45);
        template1.setForeground(new Color(69, 68, 68));
        template1.setFont(customFont);
        template1.setFont(s.getFont().deriveFont(23f));
        slash1 = new JLabel("/");
        slash1.setBounds(240, 295, 290, 45);
        slash1.setForeground(Color.white);
        slash1.setFont(customFont);
        slash1.setFont(s.getFont().deriveFont(23f));
        if (String.valueOf(Ping.lobby_player) != null) {
            player_lobby = new JLabel(String.valueOf(Ping.lobby_player));
        } else {
            player_lobby = new JLabel("0");
        }
        player_lobby.setBounds(214, 295, 290, 45);
        player_lobby.setForeground(new Color(69, 68, 68));
        player_lobby.setFont(customFont);
        player_lobby.setFont(player_lobby.getFont().deriveFont(23f));

        template2 = new JLabel("120");
        template2.setBounds(255, 390, 290, 45);
        template2.setForeground(new Color(69, 68, 68));
        template2.setFont(customFont);
        template2.setFont(s.getFont().deriveFont(23f));
        slash2 = new JLabel("/");
        slash2.setBounds(240, 390, 290, 45);
        slash2.setForeground(Color.white);
        slash2.setFont(customFont);
        slash2.setFont(s.getFont().deriveFont(23f));
        if (String.valueOf(Ping.freebuild_player) != null) {
            player_freebuild = new JLabel(String.valueOf(Ping.freebuild_player));
        } else {
            player_freebuild = new JLabel("0");
        }
        player_freebuild.setBounds(214, 390, 290, 45);
        player_freebuild.setForeground(new Color(69, 68, 68));
        player_freebuild.setFont(customFont);
        player_freebuild.setFont(player_freebuild.getFont().deriveFont(23f));

        template3 = new JLabel("120");
        template3.setBounds(255, 483, 290, 45);
        template3.setForeground(new Color(69, 68, 68));
        template3.setFont(customFont);
        template3.setFont(template2.getFont().deriveFont(23f));
        slash3 = new JLabel("/");
        slash3.setBounds(240, 483, 290, 45);
        slash3.setForeground(Color.white);
        slash3.setFont(customFont);
        slash3.setFont(s.getFont().deriveFont(23f));
        if (Ping.freebuild == true) {
            player_guild = new JLabel(String.valueOf(Ping.guild_player));
        } else {
            player_guild = new JLabel("0");
        }
        player_guild.setBounds(214, 483, 290, 45);
        player_guild.setForeground(new Color(69, 68, 68));
        player_guild.setFont(customFont);
        player_guild.setFont(player_guild.getFont().deriveFont(23f));



        raison = new JLabel(ddb.raison);
        raison.setBounds(600, 200, 800, 400);
        raison.setForeground(new Color(231, 231, 231));
        raison.setFont(customFont);
        raison.setFont(raison.getFont().deriveFont(40f));

        wlraison = new JLabel("Pour l'instant, le launcher n'est pas disponible.");
        wlraison.setBounds(600, 200, 800, 400);
        wlraison.setForeground(new Color(231, 231, 231));
        wlraison.setFont(customFont);
        wlraison.setFont(wlraison.getFont().deriveFont(23f));


        if(!ddb.wluser.contains(launcher.username_player)){
            remove(playButton);
            remove(ramButton);
            add(wlraison);
        }else {
            add(playButton);
            add(ramButton);
            repaint();

        }

        if (saver.get("token") != null){
            add(playButton);
            add(ramButton);
            remove(wlraison);
            repaint();
        }



        add(s);
        this.add(radusbutton);
        this.add(quitButton);
        this.add(progresseBar);
        this.add(etat_lobby);
        this.add(etat_freebuild);
        this.add(etat_guild);
        this.add(disconnectButton);
        this.add(template1);
        this.add(slash1);
        this.add(player_lobby);
        this.add(template2);
        this.add(slash2);
        this.add(player_freebuild);
        this.add(template3);
        this.add(slash3);
        this.add(player_guild);



        if (ddb.q != false){
            if(ddb.statue.contains("off")){
                remove(playButton);
                remove(ramButton);
                add(raison);
                repaint();
            }
        }

        if (ddb.q != false) {

            if (ddb.perm.contains("Administrateur") || ddb.perm.contains("Moderateurplus")) {
                add(playButton);
                add(ramButton);
                remove(raison);
                repaint();
            }
        }

    }


    public static void test() {
        try {


            URL url = new URL(("http://api.craftmywebsite.fr/skin/face.php?u=" + launcher.username_player + "&s=64.png"));
            URLConnection conn = url.openConnection();
            conn.setRequestProperty("User-Agent", "Mozilla/5.0");
            conn.setRequestProperty("User-Agent", "Mozilla/5.0");
            conn.connect();
            InputStream urlStream = conn.getInputStream();
            HomePanel.Head = ImageIO.read(urlStream);
            HomePanel.s.setText(launcher.username_player);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void test2() {

        try {

            URL url = new URL(("http://api.craftmywebsite.fr/skin/face.php?u=" + HomePanel.saver.get("username_player") + "&s=64.png"));
            URLConnection conn = url.openConnection();
            conn.setRequestProperty("User-Agent", "Mozilla/5.0");
            conn.connect();
            InputStream urlStream = conn.getInputStream();
            HomePanel.Head = ImageIO.read(urlStream);
            HomePanel.s.setText(HomePanel.saver.get("username_player"));



        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void onEvent(SwingerEvent e) {

        if (e.getSource() == playButton) {


            remove(playButton);
            remove(ramButton);
            remove(disconnectButton);
            repaint();

            Thread t = new Thread() {
                @Override
                public void run() {
                    try {


                        if (saver.get("token") != null) {
                            authToken(HomePanel.saver.get("token"), HomePanel.saver.get("clientToken"));
                        }
                        launcher.update();
                        ramSelector.save();

                    } catch (Exception e) {

                        launcher.interruptThread();
                        LauncherFrame.getCrashReporter().catchError(e, "impossible de mettre Nugarium a jour");
                    }

                    try {
                        HomeFrame.instance2.setVisible(false);

                        launcher.launch();


                    } catch (LaunchException e) {

                        launcher.interruptThread();

                        LauncherFrame.getCrashReporter().catchError(e, "impossible de lancer Nugarium");

                    }
                }


            };
            t.start();
        } else if (e.getSource() == quitButton) {
            System.exit(0);
        } else if (e.getSource() == this.disconnectButton) {
            ProcessBuilder builder = new ProcessBuilder(launcher.SC_DIR + "\\Launcher\\Loggout.exe");
            try {
                Process process = builder.start();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            System.exit(0);
        } else if (e.getSource() == this.radusbutton) {
            HomeFrame.getInstance2().setState(JFrame.ICONIFIED);
        }

        Thread t = new Thread() {
            @Override
            public void run() {

                if (e.getSource() == ramButton) {

                    System.out.println("\"C:\\Program Files (x86)\\Nugarium\\runtime\\java" + "\\bin\\java\"");
                    if (statue == true) {
                        statue = false;
                    } else {
                        statue = true;
                    }
                    repaint();

                    if (statue2 == true) {
                        statue2 = false;
                        try {
                            TimeUnit.MILLISECONDS.sleep(40);
                            add(HomeFrame.ramLabel);
                            add(HomeFrame.ramBox);
                            add(HomeFrame.optionLabel);
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                    } else {
                        statue2 = true;
                        remove(HomeFrame.ramLabel);
                        remove(HomeFrame.ramBox);
                        remove(HomeFrame.optionLabel);
                    }


                }

            }


        };
        t.start();

    }


    public SColoredBar getProgressBar() {
        return progresseBar;
    }

    public RamSelector getRamSelector() {
        return ramSelector;
    }

    public void setInfoText(String text) {
        infolabel.setText(text);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);


        g.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), this);
        g.drawImage(Head, 11, 24, 151, 151, this);

        if (Ping.lobby == true) {
            g.drawImage(etats_on_lobby, 25, 310, 12, 12, this);
        } else {
            g.drawImage(etat_off_lobby, 25, 310, 12, 12, this);
        }

        if (Ping.freebuild == true) {
            g.drawImage(etats_on_freebuild, 25, 405, 12, 12, this);
        } else {
            g.drawImage(etats_off_freebuild, 25, 405, 12, 12, this);
        }

        if (Ping.guild == true) {
            g.drawImage(etats_on_guild, 25, 497, 12, 12, this);
        } else {
            g.drawImage(etats_off_guild, 25, 497, 12, 12, this);
        }

        if (statue == true) {
            g.drawImage(option_display, 1000, 16, this.getWidth(), this.getHeight(), this);
            SwingUtilities.updateComponentTreeUI(HomeFrame.homePanel);
        }
        Image contour = Swinger.getResource(ddb.perm);
        g.drawImage(contour,11, 24, 151, 151, this);

    }


    public static HomePanel getInstance() {
        return instance;
    }

}