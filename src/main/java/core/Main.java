package core;

import sys.ScreenManager;

import java.awt.*;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, AWTException {
        ScreenManager recording = new ScreenManager();

        recording.start();
    }

}
