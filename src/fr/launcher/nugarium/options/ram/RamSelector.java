package fr.launcher.nugarium.options.ram;

import java.io.*;
import java.lang.reflect.Constructor;

import javax.swing.*;

import fr.launcher.nugarium.HomeFrame;
import fr.theshark34.openlauncherlib.util.CrashReporter;
import fr.theshark34.openlauncherlib.util.LogUtil;
import fr.theshark34.openlauncherlib.util.Saver;


public class RamSelector
{

    /**
     * The RAM !
     */
    public static final String[] RAM_ARRAY = new String[]{"1Go", "2Go", "3Go", "4Go", "5Go", "6Go", "7Go", "8Go", "9Go", "10Go", "11Go", "12Go"};


    /**
     * The file where to save the ram
     */
    private File file;

    /**
     * The class of the selector frame
     */
    private Class<? extends AbstractOptionFrame> frameClass = HomeFrame.class;

    /**
     * The created frame
     */
    private AbstractOptionFrame frame;


    public RamSelector(File file)
    {
        this.file = file;
    }

    /**
     * Display the selector
     *
     * @return The displayed frame, an instance of the given
     * frame class (by default OptionFrame)
     *
     * @see #setFrameClass(Class)
     * @see #getFrameClass()
     */
    @SuppressWarnings("rawtypes")
    public JFrame display()
    {
        if (frame == null)


            try
            {

                Constructor[] contructors = frameClass.getDeclaredConstructors();

                Constructor constructor = null;
                for (Constructor c : contructors)
                    if (c.getParameterTypes().length == 1 && c.getParameterTypes()[0] == RamSelector.class)
                        constructor = c;

                if (constructor == null)
                    throw new IllegalStateException("Can't load the OptionFrame class, it needs to have a constructor with just a RamSelector as argument.");

                frame = (AbstractOptionFrame) constructor.newInstance(this);
                frame.setSelectedIndex(readRam());
            }
            catch (Exception e)
            {
                System.err.println("[Nugar] Can't display the Ram Selector !");
                System.err.println(CrashReporter.makeCrashReport("Nugar Ram Selector", e));

                return null;
            }

        frame.setVisible(true);

        return frame;
    }

    /**
     * Get the generated RAM arguments
     *
     * @return An array of two strings containing the arguments
     */
    public String[] getRamArguments()
    {
        int maxRam = Integer.parseInt(frame == null ? RAM_ARRAY[readRam()].replace("Go", "") : RAM_ARRAY[frame.getSelectedIndex()].replace("Go", "")) * 1024;
        int minRam = maxRam - 512;

        return new String[]{"-Xms" + minRam + "M", "-Xmx" + maxRam + "M"};
    }

    /**
     * Read the saved ram
     *
     * @return An int, of the selected index of RAM_ARRAY
     */

    private int readRam() {
        BufferedReader br = null;

        int var3;
        try {
            br = new BufferedReader(new FileReader(this.file));
            String ramText = br.readLine();
            if (ramText == null) {
                LogUtil.err(new String[]{"warn", "ram-empty"});
                return 0;
            }

            var3 = Integer.parseInt(ramText);
        } catch (IOException var14) {
            System.err.println("[OpenLauncherLib] WARNING: Can't read ram : " + var14);
            return 0;
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException var13) {
                    System.err.println("[OpenLauncherLib] WARNING: Can't close the file : " + var13);
                }
            }

        }

        return var3;
    }

    public void save() {

        if (HomeFrame.instance2 != null) {
            BufferedWriter bw = null;
            try {
                bw = new BufferedWriter(new FileWriter(this.file));
                bw.write(String.valueOf(HomeFrame.instance2.getSelectedIndex()));
                System.err.println(HomeFrame.instance2.getSelectedIndex());
            } catch (IOException var11) {
                System.err.println("[OpenLauncherLib] WARNING: Can't save ram : " + var11);
            } finally {
                if (bw != null) {
                    try {
                        bw.close();
                    } catch (IOException var10) {
                        System.err.println("[OpenLauncherLib] WARNING: Can't close the file : " + var10);
                    }
                }

            }

        }
        else
        {
            System.err.println(frame);
        }
    }


    public File getFile()
    {
        return file;
    }

    public void setFile(File file)
    {
        this.file = file;
    }

    /**
     * Return the class of the selector Frame (? extends JFrame)
     *
     * @return The selector frame class
     *
     * @see #setFrameClass(Class)
     */
    public Class<? extends JFrame> getFrameClass()
    {
        return frameClass;
    }

    /**
     * Set the class of the selector Frame (need to be a JFrame)
     *
     * @param frameClass The new class of the selector
     *
     * @see #getFrameClass()
     */
    public void setFrameClass(Class<? extends AbstractOptionFrame> frameClass)
    {
        this.frameClass = frameClass;
    }
}