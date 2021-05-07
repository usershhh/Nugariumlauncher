package fr.launcher.nugarium;

import fr.launcher.nugarium.ddb.ddb;
import fr.launcher.nugarium.options.ram.AbstractOptionFrame;
import fr.launcher.nugarium.options.ram.RamSelector;
import fr.theshark34.openlauncherlib.LanguageManager;
import fr.theshark34.swinger.Swinger;
import fr.theshark34.swinger.util.WindowMover;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.sql.*;

public class HomeFrame extends AbstractOptionFrame {



	public static HomeFrame instance2;
	public static HomePanel homePanel;
	public static boolean statue = false;

	//RAM
	public static JSlider ramBox;
	public static JLabel ramLabel, optionLabel;





	public HomeFrame(RamSelector selector){
		super(selector);


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

		statue = false;

		this.setTitle("Nugarium");
		this.setSize( 1300, 850);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setBackground(new Color(27, 27, 27));
		this.setLocationRelativeTo(null);
		this.setUndecorated(true);
		this.setIconImage(Swinger.getResource("Nugar.png"));
		this.setContentPane(homePanel = new HomePanel());
		WindowMover mover = new WindowMover(this);
		this.addMouseListener(mover);
		this.addMouseMotionListener(mover);
		this.setVisible(false);

		//OPTION LABEL
		optionLabel = new JLabel("Options");
		this.optionLabel.setFont(customFont);
		this.optionLabel.setFont(optionLabel.getFont().deriveFont(33.72f));
		this.optionLabel.setBounds(1100, -100, 282, 300);
		this.optionLabel.setForeground(new Color(231, 231, 231));

		//RAM BUTTON

		ramLabel = new JLabel(LanguageManager.lang("ram") + " : ");
		this.ramLabel.setBounds(1030, 75, 282, 300);
		this.ramLabel.setFont(customFont);
		this.ramLabel.setFont(ramLabel.getFont().deriveFont(15.72f));
		this.ramLabel.setForeground(new Color(231, 231, 231));

		ramBox = new JSlider(0, 100, 50);
		ramBox.setBounds(1080, 200, 195, 50);
		ramBox.setBackground(new Color(27, 27, 27, 0));
		ramBox.setPaintTicks(true);
		ramBox.setForeground(new Color(231, 231, 231));
		ramBox.setMajorTickSpacing(1);
		ramBox.setMinimum(1);
		ramBox.setMaximum(11);
		ramBox.setPaintLabels(true);

		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(HomePanel.ramSelector.getFile()));
			String ramText = br.readLine();
			ramBox.setValue(Integer.parseInt(ramText));
			System.err.println(ramText);
			System.err.println(ramBox.getValue());
		} catch (FileNotFoundException e) {
			ramBox.setValue(3);
			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}


	public static void Ram(){

	}

	public static HomeFrame getInstance2() {
		return instance2;
	}


	public HomePanel getHomePanel() {

		return homePanel;

	}


	@Override
	public int getSelectedIndex()
	{
		return ramBox.getValue();
	}

	@Override
	public void setSelectedIndex(int index)
	{
		ramBox.setValue(ramBox.getValue()-1);
	}

	@Override
	public RamSelector getSelector() {
		return super.getSelector();
	}


}


