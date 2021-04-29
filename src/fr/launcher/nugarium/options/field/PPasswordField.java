package fr.launcher.nugarium.options.field;

import fr.launcher.nugarium.LauncherFrame;
import fr.launcher.nugarium.LauncherPanel;

import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JPasswordField;

@SuppressWarnings("serial")
public class PPasswordField extends JPasswordField
{

    public PPasswordField(final String text) {
        setText(text);
    }

    public PPasswordField(final String text, String promptText) {
        super(promptText);
        addFocusListener(new FocusListener() {


            @SuppressWarnings("deprecation")
            @Override
            public void focusLost(FocusEvent e) {

                if(getText().isEmpty()) {
                    setEchoChar((char)0);
                    setText(promptText);
                }
            }
            @SuppressWarnings("deprecation")
            @Override
            public void focusGained(FocusEvent e) {
                if(getText().equals(promptText)) {
                    setEchoChar('\u25CF');
                    setText("");
                }
            }
        });
        setText(text);

    }
}

