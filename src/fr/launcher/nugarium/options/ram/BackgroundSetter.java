package fr.launcher.nugarium.options.ram;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JComponent;

import fr.theshark34.swinger.Swinger;

@SuppressWarnings("serial")
public class BackgroundSetter extends JComponent
{
    private Image image;
    public BackgroundSetter(Image image) {
        this.image = image;
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Swinger.drawFullsizedImage(g, this, image);
    }
}

