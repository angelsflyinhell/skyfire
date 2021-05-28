package sys;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Assistant {

    private BufferedImage image;
    private int x, y;
    private Color color;

    public Assistant(BufferedImage frame, int x, int y, Color color) {
        this.image = frame;
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public void process() throws IOException {
        int centerX = x / 2, centerY = y / 2;

        int pos = image.getRGB(centerX, centerY);

        int red = (pos & 0x00ff0000) >> 16;
        int green = (pos & 0x0000ff00) >> 8;
        int blue = pos & 0x000000ff;

        scanForEntity();
    }

    private void scanForEntity() throws IOException {

        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                int clr = image.getRGB(x, y);
                int red = (clr & 0x00ff0000) >> 16;
                int green = (clr & 0x0000ff00) >> 8;
                int blue = clr & 0x000000ff;

                if (new Color(red, green, blue).equals(color)) {
                    System.out.println("found");
                }
                image.setRGB(x, y, Color.green.getRGB());

                ImageIO.write(image, "jpg", new File("rec/processed" + x + ".jpg"));
            }
        }



    }
}
