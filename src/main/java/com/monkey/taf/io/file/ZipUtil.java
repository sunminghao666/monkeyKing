/**
 * @模块名：taf
 * @包名：com.tit.taf.io.file
 * @描述：ZipUtil.java
 * @版本：1.0
 * @创建人：cc
 * @创建时间：2018年8月27日下午3:32:47
 */

package com.monkey.taf.io.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;

/**
 * @模块名：taf
 * @包名：com.tit.taf.io.file
 * @类名称： ZipUtil
 * @类描述：【类描述】文件压缩操作类；更多可参考：http://commons.apache.org/proper/commons-compress/examples.html
 * @版本：1.0
 * @创建人：cc
 * @创建时间：2018年8月27日下午3:32:47
 */

public class ZipUtil {
    /**
     * 
     * @方法名：fileToZip
     * @方法描述【方法功能描述】将文件目录打包成指定路径的指定文件
     * @param sourceFilePath 源文件夹路径
     * @param zipFilePath 目的文件夹
     * @param fileName zip文件名
     * @return 是否成功
     * @修改描述【修改描述】
     * @版本：1.0
     * @创建人：cc
     * @创建时间：2018年8月28日 上午9:39:25
     * @修改人：cc
     * @修改时间：2018年8月28日 上午9:39:25
     */
    public static boolean fileToZip(String sourceFilePath, String zipFilePath, String fileName) {
        boolean flag = false;
        File sourceFile = new File(sourceFilePath);
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        FileOutputStream fos = null;
        ZipOutputStream zos = null;

        if (sourceFile.exists() == false) {
            System.out.println("待压缩的文件目录：" + sourceFilePath + "不存在.");
        }
        else {
            try {
                File zipFile = new File(zipFilePath + "/" + fileName + ".zip");
                if (zipFile.exists()) {
                    System.out.println(zipFilePath + "目录下存在名字为:" + fileName + ".zip" + "打包文件.");
                }
                else {
                    File[] sourceFiles = sourceFile.listFiles();
                    if (null == sourceFiles || sourceFiles.length < 1) {
                        System.out.println("待压缩的文件目录：" + sourceFilePath + "里面不存在文件，无需压缩.");
                    }
                    else {
                        fos = new FileOutputStream(zipFile);
                        zos = new ZipOutputStream(new BufferedOutputStream(fos));
                        byte[] bufs = new byte[1024 * 10];
                        for (int i = 0; i < sourceFiles.length; i++) {
                            // 创建ZIP实体，并添加进压缩包
                            ZipEntry zipEntry = new ZipEntry(sourceFiles[i].getName());
                            zos.putNextEntry(zipEntry);
                            // 读取待压缩的文件并写进压缩包里
                            fis = new FileInputStream(sourceFiles[i]);
                            bis = new BufferedInputStream(fis, 1024 * 10);
                            int read = 0;
                            while ((read = bis.read(bufs, 0, 1024 * 10)) != -1) {
                                zos.write(bufs, 0, read);
                            }
                        }
                        flag = true;
                    }
                }
            }
            catch (FileNotFoundException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
            catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
            finally {
                // 关闭流
                try {
                    if (null != bis)
                        bis.close();
                    if (null != zos)
                        zos.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }
        }
        return flag;
    }

    /**
     * 
     * @方法名：filetoZIP
     * @方法描述【方法功能描述】将制定目录打包到指定目录下的zip文件
     * @param dirpath 指定目录
     * @param zipname zip文件
     * @throws IOException
     * @修改描述【修改描述】
     * @版本：1.0
     * @创建人：cc
     * @创建时间：2018年8月28日 下午3:47:19
     * @修改人：cc
     * @修改时间：2018年8月28日 下午3:47:19
     */
    public static void filetoZIP(String dirpath, String zipname) throws IOException {
        File affix1 = new File(dirpath + zipname + ".zip");
        if (affix1 != null) {
            FileUtil.deleteFile(affix1);
        }
        List < File > listfile = new ArrayList < File >();
        File dir = new File(dirpath);
        if (dir.isDirectory()) {
            String[] filelist = dir.list();
            for (int i = 0; i < filelist.length; i++) {
                listfile.add(i, new File(dirpath + filelist[i]));
            }
        }
        String strZipName = dirpath + zipname + ".zip";
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(strZipName));
        byte[] buffer = new byte[1024];
        for (int i = 0; i < listfile.size(); i++) {
            FileInputStream fis = new FileInputStream(listfile.get(i));
            out.putNextEntry(new ZipEntry(listfile.get(i).getName()));
            int len;
            while ((len = fis.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }
            out.closeEntry();
            fis.close();
        }
        out.close();
    }

    /**
     * 
     * @方法名：unPackZip
     * @方法描述【方法功能描述】解压压缩文件到指定目录
     * @param zipFileName 压缩文件
     * @param outputDirectory 指定目录
     * @throws IOException
     * @修改描述【修改描述】
     * @版本：1.0
     * @创建人：cc
     * @创建时间：2018年8月28日 下午3:48:46
     * @修改人：cc
     * @修改时间：2018年8月28日 下午3:48:46
     */
    public static void unPackZip(String zipFileName, String outputDirectory) throws IOException {
        File dir = new File(outputDirectory);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        FileInputStream fis = new FileInputStream(zipFileName);
        ZipArchiveInputStream zis = new ZipArchiveInputStream(fis);

        ZipArchiveEntry zipEntry = zis.getNextZipEntry();
        while (zipEntry != null) {
            File zip = new File(outputDirectory + File.separator + zipEntry.getName());
            File parent = zip.getParentFile();
            if (!parent.exists()) {
                parent.mkdirs();
            }
            FileOutputStream fos = new FileOutputStream(zip.getParent() + File.separator + zip.getName(), false);
            byte[] buffer = new byte[1024];
            int i = zis.read(buffer);
            while (i > 0) {
                fos.write(buffer, 0, i);
                i = zis.read(buffer);
            }
            closeStream(fos);
            zipEntry = zis.getNextZipEntry();
        }
    }

    public static void closeStream(Object stream) {
        if (stream != null) {
            if (stream instanceof InputStream) {
                InputStream in = (InputStream) stream;
                try {
                    in.close();
                    in = null;
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else if (stream instanceof OutputStream) {
                OutputStream out = (OutputStream) stream;
                try {
                    out.flush();
                    out.close();
                    out = null;
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
    }
}
