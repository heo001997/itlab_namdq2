package itlab.buoi2;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

public class Bai3_CreateImage {
    public static void main(String[] args) {
        String path = "/home/namquocdang/Learning/BTVN_BAI2_NAMDQ2/src/itlab/buoi2/image";
        String bmpFile = createBitmapImage(100, 100, path);
        convertImageTo(bmpFile, "PNG");
        String bmp1File = createBitmapImage(10000, 10000, path);
        convertImageTo(bmp1File, "PNG");

        convertImageTo(path+"/100x100.bmp", "PNG");
        convertImageTo(path+"/10000x10000.bmp", "PNG");
    }

    private static String createBitmapImage(int width, int height, String pathDir) {
        Random random = new Random();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int r = random.nextInt(255);
                int g = random.nextInt(255);
                int b = random.nextInt(255);
                int p = (255 << 24) | (r << 16) | (g << 8) | b;
                image.setRGB(x, y, p);
            }
        }

        try {
            File file = new File(pathDir + "/" + width + "x" + height + "_1.bmp");
            ImageIO.write(image, "bmp", file);
            System.out.println(file.getAbsolutePath());
            return file.getAbsolutePath();
        } catch (IOException exception) {
            System.out.println(exception);
        }
        return null;
    }

    private static void convertImageTo(String path, String fileType) {
        BufferedImage img = null;
        File f = null;

        try {
            f = new File(path);
            img = ImageIO.read(f);

            String[] cut = path.split(Pattern.quote("."));

            f = new File(cut[0] + "." + fileType);
            System.out.println(f.getAbsolutePath());
            ImageIO.write(img, fileType, f);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}