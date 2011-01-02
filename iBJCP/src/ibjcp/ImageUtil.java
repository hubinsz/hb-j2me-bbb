package ibjcp;

import javax.microedition.lcdui.*;
/**
 * @author hubin
 * hu.bin@msn.com
 */
public class ImageUtil {

    public static final int TURN_LEFT = 1;
    public static final int TURN_RIGHT = 2;

    public int[] getPixels(Image src) {
        int w = src.getWidth();
        int h = src.getHeight();
        int[] pixels = new int[w * h];
        src.getRGB(pixels, 0, w, 0, 0, w, h);
        return pixels;
    }

    public Image drawPixels(int[] pixels, int w, int h) {
        Image image = Image.createRGBImage(pixels, w, h, true);
        pixels = null;
        return image;
    }

    public Image resizeImage(Image src, int destW, int destH) {
        int srcW = src.getWidth();
        int srcH = src.getHeight();

        int[] destPixels = new int[destW * destH];

        int[] srcPixels = getPixels(src);
        for (int destY = 0; destY < destH; ++destY) {
            for (int destX = 0; destX < destW; ++destX) {
                int srcX = (destX * srcW) / destW;
                int srcY = (destY * srcH) / destH;
                destPixels[destX + destY * destW] = srcPixels[srcX + srcY
                        * srcW];
            }
        }

        return drawPixels(destPixels, destW, destH);
    }
}
