/**
 * @模块名：taf
 * @包名：com.tit.taf.io.img
 * @描述：ImgUtil.java
 * @版本：1.0
 * @创建人：cc
 * @创建时间：2018年8月30日下午4:46:16
 */

package com.monkey.taf.io.img;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.color.ColorSpace;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

/**
 * @模块名：taf
 * @包名：com.tit.taf.io.img
 * @类名称： ImgUtil
 * @类描述：【类描述】
 * @版本：1.0
 * @创建人：cc
 * @创建时间：2018年8月30日下午4:46:16
 */

public class ImgUtil {
    // 图形交换格式
    public static String IMAGE_TYPE_GIF = "gif";

    // 联合照片专家组
    public static String IMAGE_TYPE_JPG = "jpg";

    // 联合照片专家组
    public static String IMAGE_TYPE_JPEG = "jpeg";

    // 英文Bitmap（位图）的简写，它是Windows操作系统中的标准图像文件格式
    public static String IMAGE_TYPE_BMP = "bmp";

    // 可移植网络图形
    public static String IMAGE_TYPE_PNG = "png";

    // Photoshop的专用格式Photoshop
    public static String IMAGE_TYPE_PSD = "psd";

    /**
     * 
     * @方法名：getSizeInfo
     * @方法描述【方法功能描述】获取图片尺寸信息
     * @param filePath 图片路径
     * @return [width, height]
     * @throws IOException
     * @修改描述【修改描述】
     * @版本：1.0
     * @创建人：cc
     * @创建时间：2018年9月11日 上午9:18:06
     * @修改人：cc
     * @修改时间：2018年9月11日 上午9:18:06
     */
    public static int[] getSizeInfo(String filePath) throws IOException {
        File file = new File(filePath);
        BufferedInputStream input = new BufferedInputStream(new FileInputStream(file));
        BufferedImage img = ImageIO.read(input);
        int w = img.getWidth(null);
        int h = img.getHeight(null);
        return new int[] { w, h };
    }

    /**
     * 
     * @方法名：convert
     * @方法描述【方法功能描述】 转换图片格式
     * @param srcImageFile 源图片地址
     * @param formatName 转换后格式
     * @param destImageFile 转换后图片地址
     * @throws IOException
     * @修改描述【修改描述】
     * @版本：1.0
     * @创建人：cc
     * @创建时间：2018年9月10日 上午9:13:58
     * @修改人：cc
     * @修改时间：2018年9月10日 上午9:13:58
     */
    public static void convert(String srcImageFile, String formatName, String destImageFile) throws IOException {
        File f = new File(srcImageFile);
        f.canRead();
        f.canWrite();
        BufferedImage src = ImageIO.read(f);
        ImageIO.write(src, formatName, new File(destImageFile));
    }

    /**
     * 
     * @方法名：cropImage
     * @方法描述【方法功能描述】对图片裁剪，并把裁剪新图片保存
     * @param srcPath 读取源图片路径
     * @param toPath 写入图片路径
     * @param x 剪切起始点x坐标
     * @param y 剪切起始点y坐标
     * @param width 剪切宽度
     * @param height 剪切高度
     * @param readImageFormat 读取图片格式
     * @param writeImageFormat 写入图片格式
     * @throws IOException
     * @修改描述【修改描述】
     * @版本：1.0
     * @创建人：cc
     * @创建时间：2018年9月10日 上午9:24:31
     * @修改人：cc
     * @修改时间：2018年9月10日 上午9:24:31
     */
    public static void cropImage(String srcPath, String toPath, int x, int y, int width, int height,
            String readImageFormat, String writeImageFormat) throws IOException {
        FileInputStream fis = new FileInputStream(srcPath);
        ImageInputStream iis = ImageIO.createImageInputStream(fis);
        Iterator it = ImageIO.getImageReadersByFormatName(readImageFormat);
        ImageReader reader = (ImageReader) it.next();
        // 获取图片流
        reader.setInput(iis, true);
        ImageReadParam param = reader.getDefaultReadParam();
        // 定义一个矩形
        Rectangle rect = new Rectangle(x, y, width, height);
        // 提供一个 BufferedImage，将其用作解码像素数据的目标。
        param.setSourceRegion(rect);
        BufferedImage bi = reader.read(0, param);
        // 保存新图片
        ImageIO.write(bi, writeImageFormat, new File(toPath));
    }

    /**
     * 
     * @方法名：grayImage
     * @方法描述【方法功能描述】图片灰化操作
     * @param srcImage 读取图片路径
     * @param toPath 写入灰化后的图片路径
     * @param imageFormat 图片写入格式
     * @throws IOException
     * @修改描述【修改描述】
     * @版本：1.0
     * @创建人：cc
     * @创建时间：2018年9月10日 上午9:40:02
     * @修改人：cc
     * @修改时间：2018年9月10日 上午9:40:02
     */
    public static void grayImage(String srcImage, String toPath, String imageFormat) throws IOException {
        BufferedImage src = ImageIO.read(new File(srcImage));
        ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);
        ColorConvertOp op = new ColorConvertOp(cs, null);
        src = op.filter(src, null);
        ImageIO.write(src, imageFormat, new File(toPath));
    }

    /**
     * 
     * @方法名：mergeBothImage
     * @方法描述【方法功能描述】 合并图片(按指定初始x、y坐标将附加图片贴到底图之上)
     * @param negativeImagePath 背景图片路径
     * @param additionImagePath 附加图片路径
     * @param iamgeFromat 目标图片格式
     * @param x 附加图片的起始点x坐标
     * @param y 附加图片的起始点y坐标
     * @param toPath 图片写入路径
     * @throws IOException
     * @修改描述【修改描述】
     * @版本：1.0
     * @创建人：cc
     * @创建时间：2018年9月10日 上午9:38:08
     * @修改人：cc
     * @修改时间：2018年9月10日 上午9:38:08
     */
    public static void mergeBothImage(String negativeImagePath, String additionImagePath, String iamgeFromat, int x,
            int y, String toPath) throws IOException {
        InputStream is = null;
        InputStream is2 = null;
        OutputStream os = null;
        is = new FileInputStream(negativeImagePath);
        is2 = new FileInputStream(additionImagePath);
        BufferedImage image = ImageIO.read(is);
        BufferedImage image2 = ImageIO.read(is2);
        Graphics g = image.getGraphics();
        g.drawImage(image2, x, y, null);
        os = new FileOutputStream(toPath);
        ImageIO.write(image, iamgeFromat, os);// 写图片
        if (os != null) {
            os.close();
        }
        if (is2 != null) {
            is2.close();
        }
        if (is != null) {
            is.close();
        }
    }

    /**
     * 
     * @方法名：alphaWords2Image
     * @方法描述【方法功能描述】在源图片上设置水印文字
     * @param srcImagePath 源图片路径
     * @param alpha 透明度（0<alpha<1）
     * @param font 字体（例如：宋体）
     * @param fontStyle 字体格式(例如：普通样式--Font.PLAIN、粗体--Font.BOLD )
     * @param fontSize 字体大小
     * @param color 字体颜色(例如：黑色--Color.BLACK)
     * @param inputWords 输入显示在图片上的文字
     * @param x 文字显示起始的x坐标
     * @param y 文字显示起始的y坐标
     * @param imageFormat 写入图片格式（png/jpg等）
     * @param toPath 写入图片路径
     * @throws IOException
     * @修改描述【修改描述】
     * @版本：1.0
     * @创建人：cc
     * @创建时间：2018年9月10日 上午9:46:05
     * @修改人：cc
     * @修改时间：2018年9月10日 上午9:46:05
     */
    public static void alphaWords2Image(String srcImagePath, float alpha, String font, int fontStyle, int fontSize,
            Color color, String inputWords, int x, int y, String imageFormat, String toPath) throws IOException {
        FileOutputStream fos = null;
        BufferedImage image = ImageIO.read(new File(srcImagePath));
        // 创建java2D对象
        Graphics2D g2d = image.createGraphics();
        // 用源图像填充背景
        g2d.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null, null);
        // 设置透明度
        AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
        g2d.setComposite(ac);
        // 设置文字字体名称、样式、大小
        g2d.setFont(new Font(font, fontStyle, fontSize));
        g2d.setColor(color);// 设置字体颜色
        g2d.drawString(inputWords, x, y); // 输入水印文字及其起始x、y坐标
        g2d.dispose();
        fos = new FileOutputStream(toPath);
        ImageIO.write(image, imageFormat, fos);
        if (fos != null) {
            fos.close();
        }

    }

    /**
     * 
     * @方法名：makeRoundedCorner
     * @方法描述【方法功能描述】 制作圆角
     * @param srcFile 原图
     * @param destFile 目标图
     * @param radius 角度;如45
     * @throws IOException
     * @修改描述【修改描述】
     * @版本：1.0
     * @创建人：cc
     * @创建时间：2018年9月11日 上午9:29:29
     * @修改人：cc
     * @修改时间：2018年9月11日 上午9:29:29
     */
    public static void makeRoundedCorner(String srcFile, String destFile, int radius) throws IOException {
        InputStream inputStream = new BufferedInputStream(new FileInputStream(srcFile));
        OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(destFile));
        BufferedImage sourceImage = null;
        BufferedImage targetImage = null;
        sourceImage = ImageIO.read(inputStream);
        int w = sourceImage.getWidth();
        int h = sourceImage.getHeight();

        int cornerRadius = radius < 1 ? w / 4 : radius;
        targetImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2 = targetImage.createGraphics();
        g2.setComposite(AlphaComposite.Src);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.WHITE);
        g2.fill(new RoundRectangle2D.Float(0, 0, w, h, cornerRadius, cornerRadius));
        g2.setComposite(AlphaComposite.SrcAtop);
        g2.drawImage(sourceImage, 0, 0, null);
        g2.dispose();
        ImageIO.write(targetImage, "png", outputStream);
    }

    /**
     * 
     * @方法名：Rotate
     * @方法描述【方法功能描述】 转换角度
     * @param srcFile 原图片
     * @param destFile 新图片
     * @param angel 角度，如90
     * @throws IOException
     * @修改描述【修改描述】
     * @版本：1.0
     * @创建人：cc
     * @创建时间：2018年9月11日 上午9:45:06
     * @修改人：cc
     * @修改时间：2018年9月11日 上午9:45:06
     */
    public static void rotate(String srcFile, String destFile, int angel) throws IOException {
        InputStream inputStream = new BufferedInputStream(new FileInputStream(srcFile));
        OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(destFile));
        BufferedImage src = null;
        src = ImageIO.read(inputStream);
        int src_width = src.getWidth(null);
        int src_height = src.getHeight(null);
        Rectangle rect_des = calcRotatedSize(new Rectangle(new Dimension(src_width, src_height)), angel);
        BufferedImage res = new BufferedImage(rect_des.width, rect_des.height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = res.createGraphics();
        g2.translate((rect_des.width - src_width) / 2, (rect_des.height - src_height) / 2);
        g2.rotate(Math.toRadians(angel), src_width / 2, src_height / 2);
        g2.drawImage(src, null, null);
        g2.dispose();
        ImageIO.write(res, "png", outputStream);
    }

    /**
     * 
     * @方法名：CalcRotatedSize
     * @方法描述【方法功能描述】 计算角度
     * @param src
     * @param angel 角度
     * @return
     * @修改描述【修改描述】
     * @版本：1.0
     * @创建人：cc
     * @创建时间：2018年9月11日 上午9:43:56
     * @修改人：cc
     * @修改时间：2018年9月11日 上午9:43:56
     */
    public static Rectangle calcRotatedSize(Rectangle src, int angel) {
        if (angel >= 90) {
            if (angel / 90 % 2 == 1) {
                int temp = src.height;
                src.height = src.width;
                src.width = temp;
            }
            angel = angel % 90;
        }

        double r = Math.sqrt(src.height * src.height + src.width * src.width) / 2;
        double len = 2 * Math.sin(Math.toRadians(angel) / 2) * r;
        double angel_alpha = (Math.PI - Math.toRadians(angel)) / 2;
        double angel_dalta_width = Math.atan((double) src.height / src.width);
        double angel_dalta_height = Math.atan((double) src.width / src.height);

        int len_dalta_width = (int) (len * Math.cos(Math.PI - angel_alpha - angel_dalta_width));
        int len_dalta_height = (int) (len * Math.cos(Math.PI - angel_alpha - angel_dalta_height));
        int des_width = src.width + len_dalta_width * 2;
        int des_height = src.height + len_dalta_height * 2;
        return new java.awt.Rectangle(new Dimension(des_width, des_height));
    }

    public static void main(String[] args) {
        try {
            // ImgUtil.convert("d://a/IMG_0143.JPG", ImgUtil.IMAGE_TYPE_PNG, "d://a/IMG_0142.PNG");
            // ImgUtil.cropImage("d://a/IMG_0143.JPG", "d://a/IMG_0143_1.JPG", 100, 300, 500, 600,
            // ImgUtil.IMAGE_TYPE_JPG,
            // ImgUtil.IMAGE_TYPE_JPG);
            // String[] pics = { "d://a/IMG_0143.JPG", "d://a/IMG_0155.JPG" };
            // ImgUtil.joinImageListHorizontal(pics, ImgUtil.IMAGE_TYPE_JPG, "d://a/a.JPG");
            // ImgUtil.mergeBothImage("d://a/IMG_0155.JPG", "d://a/IMG_0143_1.JPG", ImgUtil.IMAGE_TYPE_JPG, 100, 300,
            // "d://a/IMG_0155_1.JPG");
            // ImgUtil.grayImage("d://a/IMG_0622.JPG", "d://a/IMG_0622_2.JPG", ImgUtil.IMAGE_TYPE_JPG);
            // ImgUtil.alphaWords2Image("d://a/IMG_0622.JPG", (float) 0.5, "宋体", Font.BOLD, 100, Color.WHITE, "程城的图片",
            // 100, 300, ImgUtil.IMAGE_TYPE_JPG, "d://a/IMG_0622_1.JPG");
            // System.out.println(ImgUtil.getSizeInfo("d://a/IMG_0622.JPG"));
            // ImgUtil.makeRoundedCorner("d://a/IMG_0622.JPG", "d://a/IMG_0622_3.JPG", 90);
            ImgUtil.rotate("d://a/IMG_0622.JPG", "d://a/IMG_0622_4.JPG", 90);
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
}
