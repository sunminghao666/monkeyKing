package com.monkey.taf.dataStructure;

import java.math.BigDecimal;

/**
 * 
 * @模块名：module_taf
 * @包名：com.tit.taf.dataStructure
 * @类名称： Arith
 * @类描述：【类描述】 由于Java的简单类型不能够精确的对浮点数进行运算，这个工具类提供精 确的浮点数运算，包括加减乘除和四舍五入。
 * @版本：1.0
 * @创建人：cc
 * @创建时间：2018年7月31日下午4:03:21
 */
public class Arith {

    /**
     * 默认除法运算精度
     */
    private static final int DEF_DIV_SCALE = 6;

    /**
     * 这个类不能实例化
     */
    private Arith() {

    }

    /**
     * 
     * @方法名：add
     * @方法描述【方法功能描述】 提供精确的加法运算。
     * @param v1 被加数
     * @param v2 加数
     * @return 两个参数的和
     * @修改描述【修改描述】
     * @版本：1.0
     * @创建人：cc
     * @创建时间：2018年7月31日 下午4:04:53
     * @修改人：cc
     * @修改时间：2018年7月31日 下午4:04:53
     */
    public static double add(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2).doubleValue();
    }

    /**
     * 
     * @方法名：sub
     * @方法描述【方法功能描述】提供精确的减法运算。
     * @param v1 被减数
     * @param v2 减数
     * @return 两个参数的差
     * @修改描述【修改描述】
     * @版本：1.0
     * @创建人：cc
     * @创建时间：2018年7月31日 下午4:05:18
     * @修改人：cc
     * @修改时间：2018年7月31日 下午4:05:18
     */
    public static double sub(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.subtract(b2).doubleValue();
    }

    /**
     * 
     * @方法名：mul
     * @方法描述【方法功能描述】提供精确的乘法运算。
     * @param v1 被乘数
     * @param v2 乘数
     * @return 两个参数的积
     * @修改描述【修改描述】
     * @版本：1.0
     * @创建人：cc
     * @创建时间：2018年7月31日 下午4:05:46
     * @修改人：cc
     * @修改时间：2018年7月31日 下午4:05:46
     */
    public static double mul(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.multiply(b2).doubleValue();
    }

    /**
     * 
     * @方法名：div
     * @方法描述【方法功能描述】 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到 小数点以后6位，以后的数字四舍五入。
     * @param v1 被除数
     * @param v2 除数
     * @return 两个参数的商
     * @修改描述【修改描述】
     * @版本：1.0
     * @创建人：cc
     * @创建时间：2018年7月31日 下午4:06:09
     * @修改人：cc
     * @修改时间：2018年7月31日 下午4:06:09
     */
    public static double div(double v1, double v2) {
        return div(v1, v2, DEF_DIV_SCALE);
    }

    /**
     * 
     * @方法名：div
     * @方法描述【方法功能描述】提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 定精度，以后的数字四舍五入。
     * @param v1 被除数
     * @param v2 除数
     * @param scale 表示表示需要精确到小数点以后几位。
     * @return 两个参数的商
     * @修改描述【修改描述】
     * @版本：1.0
     * @创建人：cc
     * @创建时间：2018年7月31日 下午4:07:16
     * @修改人：cc
     * @修改时间：2018年7月31日 下午4:07:16
     */
    public static double div(double v1, double v2, int scale) {
        if (v2 == 0) {
            return 0.00;
        }
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 
     * @方法名：round
     * @方法描述【方法功能描述】提供精确的小数位四舍五入处理。
     * @param v 需要四舍五入的数字
     * @param scale 小数点后保留几位
     * @return 四舍五入后的结果
     * @修改描述【修改描述】
     * @版本：1.0
     * @创建人：cc
     * @创建时间：2018年7月31日 下午4:07:54
     * @修改人：cc
     * @修改时间：2018年7月31日 下午4:07:54
     */
    public static double round(double v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b = new BigDecimal(Double.toString(v));
        BigDecimal one = new BigDecimal("1");
        return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
