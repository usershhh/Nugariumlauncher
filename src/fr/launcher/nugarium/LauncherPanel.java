package fr.launcher.nugarium;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.*;
import java.util.concurrent.TimeUnit;

import javax.swing.*;

import fr.launcher.nugarium.ddb.ddb;
import fr.launcher.nugarium.options.field.PPasswordField;
import fr.launcher.nugarium.options.field.PUsernameField;
import fr.theshark34.openauth.AuthenticationException;
import fr.theshark34.openlauncherlib.util.Saver;
import fr.theshark34.swinger.Swinger;
import fr.theshark34.swinger.event.SwingerEvent;
import fr.theshark34.swinger.event.SwingerEventListener;
import fr.theshark34.swinger.textured.STexturedButton;
import javafx.geometry.HPos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import sun.font.Decoration;

import static fr.launcher.nugarium.HomePanel.ramSelector;
import static fr.launcher.nugarium.HomePanel.test;


@SuppressWarnings("serial")
public class LauncherPanel extends JPanel implements SwingerEventListener{

	public static Saver saver = new Saver(new File(launcher.SC_DIR, "launcher.properties"));

	public static PUsernameField usernameField = new PUsernameField(saver.get("username"));
	public static PPasswordField passewordField = new PPasswordField("");
	public static Image background = Swinger.getResource("Fondlauncher.png");

	public STexturedButton playButton = new STexturedButton(Swinger.getResource("play.png"));
	public STexturedButton quitButton = new STexturedButton(Swinger.getResource("quit.png"));
	public STexturedButton radusbutton = new STexturedButton(Swinger.getResource("iconised.png"));
	public STexturedButton discordButton = new STexturedButton(Swinger.getResource("Discord.png"));
	public STexturedButton webButton = new STexturedButton(Swinger.getResource("web_ico.png"));

	JLabel passwd_forgot = new JLabel("Mot de passe oublie ?");
	JLabel register_t = new JLabel("Vous n'avez pas encore de compte ?");
	JLabel register = new JLabel("S'inscrire ici");

	public static GridPane layout;

	public JLabel infolabel = new JLabel("", SwingConstants.CENTER);



	public LauncherPanel() {

		this.setLayout(null);

		this.playButton.setBounds(488, 489);
		this.playButton.addEventListener(this);

		this.add(LauncherFrame.imagelabel);



		InputStream is = null;
		Font customFont = null;
		GraphicsEnvironment ge = null;
		is = LauncherPanel.class.getResourceAsStream("resources/font.ttf");
		try {
			customFont = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(24f);
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		ge.registerFont(customFont);



		this.usernameField.setForeground(Color.white);
		this.usernameField.setFont(customFont);
		this.usernameField.setCaretColor(Color.white);
		this.usernameField.setOpaque(false);
		this.usernameField.setBorder(null);
		this.usernameField.setFont(usernameField.getFont().deriveFont(21f));
		this.usernameField.setBounds(490, 320, 320, 42);


		this.passewordField.setForeground(Color.white);
		this.passewordField.setCaretColor(Color.white);
		this.passewordField.setOpaque(false);
		this.passewordField.setBorder(null);
		this.passewordField.setFont(passewordField.getFont().deriveFont(21f));
		this.passewordField.setBounds(490, 406, 320, 42);

		this.radusbutton.setBounds(1255,0);
		this.radusbutton.setSize(15, 15);
		this.radusbutton.addEventListener(this);

		this.quitButton.setBounds(1280,0);
		this.quitButton.setSize(15, 15);
		this.quitButton.addEventListener(this);

		this.infolabel.setFont(infolabel.getFont().deriveFont(25f));
		this.infolabel.setForeground(Color.WHITE);
		this.infolabel.setBounds(0 ,593, 950, 35);


		passwd_forgot.setForeground(new Color(141,147,163));
		passwd_forgot.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		passwd_forgot.setBounds(488,456,112,20);
		passwd_forgot.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				try {

					Desktop.getDesktop().browse(new URI("https://www.minecraft.net/fr-fr/password/forgot/"));

				} catch (IOException | URISyntaxException e1) {
					e1.printStackTrace();
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				passwd_forgot.setForeground(new Color(122,127,141));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				passwd_forgot.setForeground(new Color(141,147,163));
			}
		});

		register.setForeground(new Color(61,96,147));
		register.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		register.setBounds(620,650,73,20);
		register.setFont(new Font("Arial Rounded MT Bold", Font.CENTER_BASELINE, 12));
		register.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				try {

					Desktop.getDesktop().browse(new URI("https://www.minecraft.net/fr-fr/profile"));

				} catch (IOException | URISyntaxException e1) {
					e1.printStackTrace();
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				register.setForeground(new Color(61,96,147));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				register.setForeground(new Color(61,96,147));
			}
		});

		register_t.setForeground(new Color(141,147,163));
		register_t.setBounds(549, 630,300,20);
		register_t.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));


		this.webButton.setBounds(590, 550);
		this.webButton.setSize(48, 48);
		this.webButton.addEventListener(this);

		this.discordButton.setBounds(670, 550);
		this.discordButton.setSize(48, 48);
		this.discordButton.addEventListener(this);


		this.add(quitButton);
		this.add(radusbutton);
		this.add(infolabel);
		this.add(passewordField);
		this.add(usernameField);
		this.add(playButton);
		this.add(passwd_forgot);
		this.add(register);
		this.add(register_t);
		this.add(webButton);
		this.add(discordButton);

	}

	Graphics g;
	@SuppressWarnings("deprecation")
	@Override
	public void onEvent(SwingerEvent e) {

		if(e.getSource() == playButton) {


			if(usernameField.getText().replaceAll("", "").length() == 0 || passewordField.getText().length() == 0) {
				JOptionPane.showMessageDialog(this, "Erreur , votre adresse mail et/ou votre mot de passe n'est pas valide", "Erreur" , JOptionPane.ERROR_MESSAGE);
				setFieldEnableds(true);

				return;
			}

			Thread t = new Thread() {
				@Override
				public void run() {
					try {


						launcher.auth(usernameField.getText(), passewordField.getText());
						File f = new File(String.valueOf(launcher.SC_DIR));
						f.mkdir();

						saver.set("token", launcher.user_token);
						saver.set("clientToken", launcher.clientToken);
						saver.set("username", LauncherPanel.usernameField.getText());
						saver.set("username_player", launcher.username_player);
						saver.set("!WARNING!", "/!Merci de ne JAMAIS diffuser ce fichier!/");

						HomeFrame.instance2 = new HomeFrame(ramSelector);
						HomeFrame.instance2.setVisible(true);
						test();
						LauncherFrame.instance.setVisible(false);
						TimeUnit.MILLISECONDS.sleep(5);
						SwingUtilities.updateComponentTreeUI(HomeFrame.homePanel);
					}
					catch(AuthenticationException e) {
						JOptionPane.showMessageDialog(LauncherPanel.this, "Erreur ,impossible de se connecter : " + e.getErrorModel().getErrorMessage(), "Erreur" , JOptionPane.ERROR_MESSAGE);
						setFieldEnableds(true);
						return;

					} catch (InterruptedException ex) {
						ex.printStackTrace();
					}

				}

			};

			t.start();
		}
		else if(e.getSource() == quitButton) {
			System.exit(0);
		}


		else if(e.getSource() == this.radusbutton){
			LauncherFrame.getInstance().setState(JFrame.ICONIFIED);

		}

		else if(e.getSource() == this.webButton){
			if(Desktop.isDesktopSupported())
			{
				try {
					Desktop.getDesktop().browse(new URI("https://nugarium.com/"));
				} catch (IOException | URISyntaxException e1) {
					e1.printStackTrace();
				}
			}
		}

		else if(e.getSource() == this.discordButton){
			if(Desktop.isDesktopSupported())
			{
				try {
					Desktop.getDesktop().browse(new URI("https://nugarium.com/discord"));
				} catch (IOException | URISyntaxException e1) {
					e1.printStackTrace();
				}
			}
		}



	}


	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), this);

	}



	public void setFieldEnableds(boolean enabled) {
		usernameField.setEnabled(enabled);
		passewordField.setEnabled(enabled);
		playButton.setEnabled(enabled);
	}


	public void setInfoText(String text) {
		infolabel.setText(text);
	}

}
