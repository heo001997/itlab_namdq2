package app;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ConvertRedToBlue {
    public static void main(String[] args) throws Exception {
        BufferedImage img = null;
        File f = null;

        try {
            f = new File("/home/namquocdang/Learning/BTVN_BAI2_NAMDQ2/image/input.jpg");
            img = ImageIO.read(f);

            int width = img.getWidth();
            int height = img.getHeight();

            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {

                    int p = img.getRGB(x, y);

                    int a = (p >> 24) & 0xff;
                    int r = (p >> 16) & 0xff;
                    int g = (p >> 8) & 0xff;
                    int b = p & 0xff;

                    int avg = (b + r + g) / 3;

                    if (isRed(r, g, b)) {
                        p = (a << 24) | (b << 16) | (g << 8) | r;
                    }

                    img.setRGB(x, y, p);
                }
            }

            f = new File("/home/namquocdang/Learning/BTVN_BAI2_NAMDQ2/image/output.jpg");
            ImageIO.write(img, "jpg", f);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static boolean isRed(int r, int g, int b) {
        int max = Math.max(r, Math.max(g, b));
        return max > 8 && r == max && g < max * 0.5 && b < max * 0.5;
    }
}