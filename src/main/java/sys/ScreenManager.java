package sys;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;

public class ScreenManager {

    private int frame = 1;
    private boolean record = false;

    private void captureCurrentWindow() throws IOException, AWTException {
        checkForSystemFolders();

        Robot robot = new Robot();

        String format = "jpg";
        String fileName = "skyfire.frame" + frame + "." + format;

        Rectangle screen = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        BufferedImage screenImg = robot.createScreenCapture(screen);
        ImageIO.write(screenImg, format, new File("rec/" + fileName));

        System.out.println("Saved Frame " + frame);

        Assistant ass = new Assistant(screenImg, screenImg.getHeight(), screenImg.getWidth(), ColorConfig.ENEMY_OUTER_LINE);
        ass.process();

        System.out.println("Processed Frame " + frame);
        frame++;
    }

    public void start() throws IOException, AWTException {
        record = true;
        recordSync();
    }

    public void stop() {
        record = false;
    }

    public void close() {
        record = false;
        frame = 1;
    }

    public void toggle() {
        record = !record;
    }

    private void recordSync() throws IOException, AWTException {
        while (record) {
            captureCurrentWindow();

            //dev
            close();
        }
    }

    private void checkForSystemFolders() throws IOException {
        if(Files.notExists(new File("rec/").toPath(), LinkOption.NOFOLLOW_LINKS)) {
            Files.createDirectories(new File("rec/").toPath());
        }
    }

}
