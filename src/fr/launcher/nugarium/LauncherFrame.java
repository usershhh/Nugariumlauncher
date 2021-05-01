package fr.launcher.nugarium;



import javax.swing.*;


import fr.launcher.nugarium.ddb.ddb;
import fr.theshark34.openlauncherlib.util.CrashReporter;
import fr.theshark34.swinger.Swinger;
import fr.theshark34.swinger.event.SwingerEvent;
import fr.theshark34.swinger.event.SwingerEventListener;
import fr.theshark34.swinger.util.WindowMover;

import java.awt.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.concurrent.TimeUnit;

import static fr.launcher.nugarium.HomePanel.test2;


@SuppressWarnings("serial")
public class LauncherFrame extends JFrame{

	public LauncherPanel launcherPanel;
	public static LauncherFrame instance;
	public static CrashReporter crashReporter;
	private static final long serialVersionUID = 1L;
	JPanel contentPane;




	JLabel headerLabel = new JLabel();

	public static JLabel imagelabel = new JLabel();

	public LauncherFrame() {
		ddb.ddbinit();


		this.setTitle("Nugarium");
		this.setSize( 1300, 850);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setUndecorated(true);
		this.setIconImage(Swinger.getResource("Nugar.png"));
		this.setContentPane(launcherPanel = new LauncherPanel());
		WindowMover mover = new WindowMover(this);
		this.addMouseListener(mover);
		this.addMouseMotionListener(mover);
		this.setVisible(true);
		launcherPanel.add(headerLabel, java.awt.BorderLayout.NORTH);
		try {

			launcherPanel.setLayout(new BorderLayout());
			launcherPanel.add(headerLabel, java.awt.BorderLayout.NORTH);
		}
		catch (Exception exception) {

			exception.printStackTrace();
		}




	}
	public static void main(String[] args) {



		Swinger.setSystemLookNFeel();
		Swinger.setResourcePath("/fr/launcher/nugarium/resources/");
		launcher.SC_CRASHES_DIR.mkdir();
		crashReporter = new CrashReporter("Launcher Survie", launcher.SC_CRASHES_DIR);
		instance = new LauncherFrame();

		if (HomePanel.saver.get("token") != null)
		{
			LauncherFrame.instance.setVisible(false);
		}

		try {
			Ping.Ping_lobby();
		} catch (IOException e) {
		}
		try {
			Ping.Ping_freebuild();
		} catch (IOException e) {
		}

		try {
			Ping.Ping_guild();
		} catch (IOException e) {
		}

		if (HomePanel.saver.get("token") != null)
		{
			HomeFrame.instance2 = new HomeFrame(HomePanel.ramSelector);

			LauncherFrame.instance.setVisible(false);
			HomeFrame.instance2.setVisible(true);
			test2();
			try {
				TimeUnit.MILLISECONDS.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			SwingUtilities.updateComponentTreeUI(HomeFrame.homePanel);


		}




	}

	private boolean running = true;





	public static LauncherFrame getInstance() {

		return instance;

	}

	public LauncherPanel getLauncherPanel() {


		return launcherPanel;

	}

	public static CrashReporter getCrashReporter() {
		return crashReporter;
	}



}

