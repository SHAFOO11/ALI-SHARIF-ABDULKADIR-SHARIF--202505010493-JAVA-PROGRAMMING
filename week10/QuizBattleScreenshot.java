import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.SwingUtilities;

public class QuizBattleScreenshot {

    public static void main(String[] args) throws Exception {
        String path = args.length > 0 ? args[0] : "quiz_screenshot.png";

        QuizBattleGUI[] holder = new QuizBattleGUI[1];

        SwingUtilities.invokeAndWait(() -> {
            holder[0] = new QuizBattleGUI();
            holder[0].setVisible(true);
        });

        Thread.sleep(1500);

        SwingUtilities.invokeAndWait(() -> {
            QuizBattleGUI frame = holder[0];
            BufferedImage image = new BufferedImage(frame.getWidth(), frame.getHeight(),
                    BufferedImage.TYPE_INT_ARGB);
            frame.printAll(image.createGraphics());

            try {
                File out = new File(path);
                ImageIO.write(image, "png", out);
                System.out.println("Wrote " + out.getAbsolutePath());
            } catch (IOException e) {
                System.out.println("Could not write screenshot: " + e.getMessage());
            }
            frame.dispose();
        });

        System.exit(0);
    }
}
