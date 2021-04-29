package fr.launcher.nugarium.options.field;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

@SuppressWarnings("serial")
public class PUsernameField extends JTextField
{
    private int columnWidth;
    public PUsernameField(final String text) {
        setText(text);

    }


    public PUsernameField(final String text, String promptText) {
        super(promptText);
        addFocusListener(new FocusListener() {

            @Override
            public void focusLost(FocusEvent e) {
                if(getText().isEmpty()) {
                    setText(promptText);
                }
            }

            @Override
            public void focusGained(FocusEvent e) {
                if(getText().equals(promptText)) {
                    setText("");
                }
            }
        });
        setText(text);


    }
}

