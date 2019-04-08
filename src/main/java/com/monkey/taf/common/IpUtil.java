package com.monkey.taf.common;

import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

import com.monkey.taf.log.TAFLog;

public class IpUtil {
    /**
     * 获取服务器IP与传入的IP是否一样
     * 
     * @return
     */
    public static boolean getInetAddress(String ad) {
        if (Tools.isBlank(ad)) {
            return false;
        }
        String mi = null;
        try {
            mi = IpUtil.getLocalHostIP();
            TAFLog.info(mi);
        }
        catch (Exception e) {
            TAFLog.error("获得服务器ip异常", e);
        }
        return ad.equals(mi);
    }

    public static String getLocalHostIP() {
        try {
            for (Enumeration < NetworkInterface > nis = NetworkInterface.getNetworkInterfaces(); nis.hasMoreElements();) {
                NetworkInterface ni = nis.nextElement();
                if (ni.isLoopback() || !ni.isUp())
                    continue;
                for (Enumeration < InetAddress > ias = ni.getInetAddresses(); ias.hasMoreElements();) {
                    InetAddress ia = ias.nextElement();
                    if (ia instanceof Inet6Address)
                        continue;
                    return ia.getHostAddress();
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 获得主机IP
     * 
     * @return String
     */
    public static boolean isWindowsOS() {
        boolean isWindowsOS = false;
        String osName = System.getProperty("os.name");
        if (osName.toLowerCase().indexOf("windows") > -1) {
            isWindowsOS = true;
        }
        return isWindowsOS;
    }

    /**
     * 获取本机ip地址，并自动区分Windows还是linux操作系统
     * 
     * @return String
     */
    public static String getLocalIP() {
        String sIP = "";
        InetAddress ip = null;
        try {
            // 如果是Windows操作系统
            if (isWindowsOS()) {
                ip = InetAddress.getLocalHost();
            }
            // 如果是Linux操作系统
            else {
                boolean bFindIP = false;
                Enumeration < NetworkInterface > netInterfaces = (Enumeration < NetworkInterface >) NetworkInterface
                        .getNetworkInterfaces();
                while (netInterfaces.hasMoreElements()) {
                    if (bFindIP) {
                        break;
                    }
                    NetworkInterface ni = (NetworkInterface) netInterfaces.nextElement();
                    // ----------特定情况，可以考虑用ni.getName判断
                    // 遍历所有ip
                    Enumeration < InetAddress > ips = ni.getInetAddresses();
                    while (ips.hasMoreElements()) {
                        ip = (InetAddress) ips.nextElement();
                        if (ip.isSiteLocalAddress() && !ip.isLoopbackAddress() // 127.开头的都是lookback地址
                                && ip.getHostAddress().indexOf(":") == -1) {
                            bFindIP = true;
                            break;
                        }
                    }

                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        if (null != ip) {
            sIP = ip.getHostAddress();
        }
        return sIP;
    }
}
