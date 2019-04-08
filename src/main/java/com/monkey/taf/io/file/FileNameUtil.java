/**
 * @模块名：taf
 * @包名：com.tit.taf.io.file
 * @描述：FileNameUtil.java
 * @版本：1.0
 * @创建人：cc
 * @创建时间：2018年8月27日下午1:58:13
 */

package com.monkey.taf.io.file;

import java.io.IOException;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOCase;

/**
 * @模块名：taf
 * @包名：com.tit.taf.io.file
 * @类名称： FileNameUtil
 * @类描述：【类描述】文件名操作类；更多可参考：http://commons.apache.org/proper/commons-io/javadocs/api-release/index.html
 * @版本：1.0
 * @创建人：cc
 * @创建时间：2018年8月27日下午1:58:13
 */

public class FileNameUtil {
    /**
     * 
     * @方法名：getBaseName
     * @方法描述【方法功能描述】从路径中获取去除后缀的文件名
     * @param filename 文件路径
     * @return 去除后缀的文件名
     * @修改描述【修改描述】
     * @版本：1.0
     * @创建人：cc
     * @创建时间：2018年8月28日 下午3:00:18
     * @修改人：cc
     * @修改时间：2018年8月28日 下午3:00:18
     */
    public static String getBaseName(String filename) {
        return FilenameUtils.getBaseName(filename);
    }

    /**
     * 
     * @方法名：getExtension
     * @方法描述【方法功能描述】 从文件路径中获取文件的扩展名
     * @param filename 文件路径
     * @return 文件扩展名
     * @修改描述【修改描述】
     * @版本：1.0
     * @创建人：cc
     * @创建时间：2018年8月28日 下午3:02:14
     * @修改人：cc
     * @修改时间：2018年8月28日 下午3:02:14
     */
    public static String getExtension(String filename) {
        return FilenameUtils.getExtension(filename);
    }

    /**
     * 
     * @方法名：getFullPath
     * @方法描述【方法功能描述】 从文件路径中获取文件的父路径
     * @param filename 文件路径
     * @return 文件的父路径
     * @修改描述【修改描述】
     * @版本：1.0
     * @创建人：cc
     * @创建时间：2018年8月28日 下午3:03:15
     * @修改人：cc
     * @修改时间：2018年8月28日 下午3:03:15
     */
    public static String getFullPath(String filename) {
        return FilenameUtils.getFullPath(filename);
    }

    /**
     * 
     * @方法名：getName
     * @方法描述【方法功能描述】从文件路径中获取文件的全名（包含后缀）
     * @param filename 文件路径
     * @return 文件的全名（包含后缀）
     * @修改描述【修改描述】
     * @版本：1.0
     * @创建人：cc
     * @创建时间：2018年8月28日 下午3:04:25
     * @修改人：cc
     * @修改时间：2018年8月28日 下午3:04:25
     */
    public static String getName(String filename) {
        return FilenameUtils.getName(filename);
    }

    /**
     * 
     * @方法名：concat
     * @方法描述【方法功能描述】拼接目录+文件
     * @param basePath 目录
     * @param fullFilenameToAdd 文件
     * @return 文件路径
     * @修改描述【修改描述】
     * @版本：1.0
     * @创建人：cc
     * @创建时间：2018年8月28日 下午2:29:35
     * @修改人：cc
     * @修改时间：2018年8月28日 下午2:29:35
     */
    public static String concat(String basePath, String fullFilenameToAdd) {
        return FilenameUtils.concat(basePath, fullFilenameToAdd);
    }

    /**
     * 
     * @方法名：directoryContains
     * @方法描述【方法功能描述】查询父文件夹是否包含子文件
     * @param canonicalParent 父目录
     * @param canonicalChild 子文件、子目录
     * @return 父目录是否包含子文件/子目录
     * @throws IOException
     * @修改描述【修改描述】
     * @版本：1.0
     * @创建人：cc
     * @创建时间：2018年8月28日 下午2:30:46
     * @修改人：cc
     * @修改时间：2018年8月28日 下午2:30:46
     */
    public static boolean directoryContains(String canonicalParent, String canonicalChild) throws IOException {
        return FilenameUtils.directoryContains(canonicalParent, canonicalChild);

    }

    /**
     * 
     * @方法名：equals
     * @方法描述【方法功能描述】判断两个文件路径是否表示同一文件，会进行格式化，并且不进行大小写区分
     * @param filename1 文件路径1
     * @param filename2 文件路径2
     * @return 两个文件路径是否表示同一文件
     * @修改描述【修改描述】
     * @版本：1.0
     * @创建人：cc
     * @创建时间：2018年8月28日 下午2:49:21
     * @修改人：cc
     * @修改时间：2018年8月28日 下午2:49:21
     */
    public static boolean equals(String filename1, String filename2) {
        return FilenameUtils.equals(filename1, filename2, true, IOCase.INSENSITIVE);
    }

    /**
     * 
     * @方法名：isExtension
     * @方法描述【方法功能描述】判断文件路径的扩展名是否包含在指定扩展名内
     * @param filename 文件路径
     * @param extensions 指定扩展名集合
     * @return 是否包含
     * @修改描述【修改描述】
     * @版本：1.0
     * @创建人：cc
     * @创建时间：2018年8月28日 下午3:07:27
     * @修改人：cc
     * @修改时间：2018年8月28日 下午3:07:27
     */
    public static boolean isExtension(String filename, String[] extensions) {
        return FilenameUtils.isExtension(filename, extensions);
    }

    /**
     * 
     * @方法名：separatorsToSystem
     * @方法描述【方法功能描述】根据系统格式化文件路径
     * @param filename 文件路径
     * @return 格式化后的路径
     * @修改描述【修改描述】
     * @版本：1.0
     * @创建人：cc
     * @创建时间：2018年8月28日 下午3:27:08
     * @修改人：cc
     * @修改时间：2018年8月28日 下午3:27:08
     */
    public static String separatorsToSystem(String filename) {
        return FilenameUtils.separatorsToSystem(filename);
    }

    /**
     * 
     * @方法名：normalize
     * @方法描述【方法功能描述】根据指定系统格式化文件路径
     * @param filename 文件路径
     * @param unixSeparator 是否为unix系统
     * @return 格式化的路径
     * @修改描述【修改描述】
     * @版本：1.0
     * @创建人：cc
     * @创建时间：2018年8月28日 下午3:34:08
     * @修改人：cc
     * @修改时间：2018年8月28日 下午3:34:08
     */
    public static String normalize(String filename, boolean unixSeparator) {
        return FilenameUtils.normalize(filename, unixSeparator);
    }

    /**
     * 
     * @方法名：wildcardMatch
     * @方法描述【方法功能描述】判断文件路径是否能够匹配通配符并指定是否区分大小写
     * @param filename 文件路径
     * @param wildcardMatcher 通配符
     * @param caseSensitivity 是否区分大小写；IOCase.INSENSITIVE不区分大小写；IOCase.SENSITIVE区分大小写；IOCase.SYSTEM根据系统决定
     * @return 是否满足通配符
     * @修改描述【修改描述】
     * @版本：1.0
     * @创建人：cc
     * @创建时间：2018年8月28日 下午3:29:48
     * @修改人：cc
     * @修改时间：2018年8月28日 下午3:29:48
     */
    public static boolean wildcardMatch(String filename, String wildcardMatcher, IOCase caseSensitivity) {
        return FilenameUtils.wildcardMatch(filename, wildcardMatcher, caseSensitivity);

    }

    /**
     * 
     * @方法名：changeFilenameSuffix
     * @方法描述【方法功能描述】 修改文件后缀
     * @param name 文件名称或路径
     * @param suffix 新后缀(不带点,如：docx|xls|txtStringUtils.isBlank(suffix))
     * @return 使用新后缀的文件名
     * @修改描述【修改描述】
     * @版本：1.0
     * @创建人：cc
     * @创建时间：2018年8月29日 上午9:38:51
     * @修改人：cc
     * @修改时间：2018年8月29日 上午9:38:51
     */
    public static String changeFilenameSuffix(String name, String suffix) {
        if (name == null || "".equals(name) || name.lastIndexOf(".") == -1 || suffix == null || "".equals(suffix))
            return name;
        String prefix = name.substring(0, name.lastIndexOf(".") + 1);
        return prefix + suffix;
    }

    public static void main(String[] args) {
        try {
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
}
