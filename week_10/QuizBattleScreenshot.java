import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class QuizBattleScreenshot {
    public static void main(String[] args) throws IOException {
        int w = 720, h = 640;
        BufferedImage img = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = img.createGraphics();
        // Background
        g.setColor(new Color(20,20,30));
        g.fillRect(0,0,w,h);

        // Title
        g.setFont(new Font("Consolas", Font.BOLD, 20));
        g.setColor(new Color(200,200,220));
        drawCenteredString(g, "CODE BOSS BATTLE", w/2, 40);

        // Boss emoji
        g.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 120));
        drawCenteredString(g, "\uD83E\uDD16", w/2, 140);

        // Boss and player bars
        drawBar(g, 60, 220, 300, 20, 80, 100, new Color(220,50,50)); // boss
        drawBar(g, 360, 220, 300, 20, 60, 100, new Color(50,150,220)); // player

        // Question area
        g.setFont(new Font("Consolas", Font.BOLD, 16));
        g.setColor(Color.WHITE);
        String q = "Q: Which keyword is used to create a class in Java?";
        drawWrappedString(g, q, 40, 270, 640, 20);

        // Options
        g.setFont(new Font("Consolas", Font.PLAIN, 14));
        String[] opts = {"A. class","B. struct","C. define","D. object"};
        for (int i=0;i<4;i++){
            int x = 40 + (i%2)*340;
            int y = 350 + (i/2)*60;
            g.setColor(new Color(45,45,65));
            g.fillRoundRect(x,y,300,40,8,8);
            g.setColor(Color.WHITE);
            g.drawString(opts[i], x+10, y+26);
        }

        g.dispose();
        File out = new File("/workspaces/ALI-SHARIF-ABDULKADIR-SHARIF--202505010493-JAVA-PROGRAMMING/quiz_screenshot.png");
        ImageIO.write(img, "png", out);
        System.out.println("Wrote " + out.getAbsolutePath());
    }

    private static void drawBar(Graphics2D g, int x, int y, int w, int h, int value, int max, Color fill) {
        g.setColor(new Color(60,20,20));
        g.fillRect(x,y,w,h);
        int fw = (int)((value/(float)max)*w);
        g.setColor(fill);
        g.fillRect(x,y,fw,h);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Consolas", Font.PLAIN, 12));
        g.drawString("HP: " + value + " / " + max, x + w/2 - 30, y + h - 4);
    }

    private static void drawCenteredString(Graphics2D g, String text, int cx, int y) {
        FontMetrics fm = g.getFontMetrics();
        int tx = cx - fm.stringWidth(text)/2;
        g.drawString(text, tx, y);
    }

    private static void drawWrappedString(Graphics2D g, String text, int x, int y, int width, int lineHeight) {
        FontMetrics fm = g.getFontMetrics();
        String[] words = text.split(" ");
        String line = "";
        int yy = y;
        for (String w : words) {
            String test = line.isEmpty() ? w : line + " " + w;
            if (fm.stringWidth(test) > width) {
                g.drawString(line, x, yy);
                line = w;
                yy += lineHeight;
            } else {
                line = test;
            }
        }
        if (!line.isEmpty()) g.drawString(line, x, yy);
    }
}
