package com.monkey.basic.message.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import com.monkey.basic.base.util.ConfigInfo;
/**
 * 
 * @模块名：module_basic
 * @包名：com.tit.basic.message.util
 * @类名称： JavaMail
 * @类描述：【类描述】邮件工具类
 * @版本：1.0
 * @创建人：cc
 * @创建时间：2018年9月19日上午10:36:07
 */
public class JavaMail {
    /**
     * Message对象将存储我们实际发送的电子邮件信息， Message对象被作为一个MimeMessage对象来创建并且需要知道应当选择哪一个JavaMail session。
     */
    private MimeMessage message;

    /**
     * Session类代表JavaMail中的一个邮件会话。 每一个基于JavaMail的应用程序至少有一个Session（可以有任意多的Session）。
     * 
     * JavaMail需要Properties来创建一个session对象。 寻找"mail.smtp.host" 属性值就是发送邮件的主机 寻找"mail.smtp.auth" 身份验证，目前免费邮件服务器都需要这一项
     */
    private Session session;

    /***
     * 邮件是既可以被发送也可以被受到。JavaMail使用了两个不同的类来完成这两个功能：Transport 和 Store。 Transport
     * 是用来发送信息的，而Store用来收信。对于这的教程我们只需要用到Transport对象。
     */
    private Transport transport;

    private String mailHost = "";

    private String sender_username = "";

    private String sender_password = "";

    private String vipsender_username = "";

    private String vipsender_password = "";

    private Properties properties = new Properties();

    /*
     * 初始化方法
     */
    public JavaMail(boolean debug) {
        this.mailHost = ConfigInfo.getByProperties("mail.smtp.host");
        this.sender_username = ConfigInfo.getByProperties("mail.sender.username");
        this.sender_password = ConfigInfo.getByProperties("mail.sender.password");
        this.vipsender_username = ConfigInfo.getByProperties("mail.vipsender.username");
        this.vipsender_password = ConfigInfo.getByProperties("mail.vipsender.password");
        session = Session.getInstance(properties);
        session.setDebug(debug);// 开启后有调试信息
        message = new MimeMessage(session);
    }

    /**
     * 大额定制发送邮件 lvchong BYRM-663
     * 
     * @param subject 邮件主题
     * @param sendHtml 邮件内容
     * @param receiveUser 收件人地址
     */
    public void doVipSendHtmlEmail(String subject, String sendHtml, String receiveUser) {
        try {
            // 发件人
            // InternetAddress from = new InternetAddress(sender_username);
            // 下面这个是设置发送人的Nick name
            InternetAddress from = new InternetAddress(MimeUtility.encodeWord("富管家") + " <" + vipsender_username + ">");
            message.setFrom(from);

            // 收件人
            InternetAddress to = new InternetAddress(receiveUser);
            message.setRecipient(Message.RecipientType.TO, to);// 还可以有CC、BCC

            // 邮件主题
            message.setSubject(subject);

            String content = sendHtml.toString();
            // 邮件内容,也可以使纯文本"text/plain"
            message.setContent(content, "text/html;charset=UTF-8");

            // 保存邮件
            message.saveChanges();

            transport = session.getTransport("smtp");
            // smtp验证，就是你用来发邮件的邮箱用户名密码
            transport.connect(mailHost, vipsender_username, vipsender_password);
            // 发送
            transport.sendMessage(message, message.getAllRecipients());
            // System.out.println("send success!");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (transport != null) {
                try {
                    transport.close();
                }
                catch (MessagingException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 发送邮件
     * 
     * @param subject 邮件主题
     * @param sendHtml 邮件内容
     * @param receiveUser 收件人地址
     */
    public void doSendHtmlEmail(String subject, String sendHtml, String receiveUser) {
        try {
            // 发件人
            // InternetAddress from = new InternetAddress(sender_username);
            // 下面这个是设置发送人的Nick name
            InternetAddress from = new InternetAddress(MimeUtility.encodeWord("富管家平台") + " <" + sender_username + ">");
            message.setFrom(from);

            // 收件人
            InternetAddress to = new InternetAddress(receiveUser);
            message.setRecipient(Message.RecipientType.TO, to);// 还可以有CC、BCC

            // 邮件主题
            message.setSubject(subject);

            String content = sendHtml.toString();
            // 邮件内容,也可以使纯文本"text/plain"
            message.setContent(content, "text/html;charset=UTF-8");

            // 保存邮件
            message.saveChanges();

            transport = session.getTransport("smtp");
            // smtp验证，就是你用来发邮件的邮箱用户名密码
            transport.connect(mailHost, sender_username, sender_password);
            // 发送
            transport.sendMessage(message, message.getAllRecipients());
            // System.out.println("send success!");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (transport != null) {
                try {
                    transport.close();
                }
                catch (MessagingException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 发送邮件
     * 
     * @param subject 邮件主题
     * @param sendHtml 邮件内容
     * @param receiveUser 收件人地址
     */
    public void doAllSendHtmlEmail(String subject, String sendHtml, String receiveUser) {
        try {
            // 发件人
            // InternetAddress from = new InternetAddress(sender_username);
            // 下面这个是设置发送人的Nick name
            InternetAddress from = new InternetAddress(MimeUtility.encodeWord("富管家平台") + " <" + sender_username + ">");
            message.setFrom(from);
            List list = new ArrayList();// 不能使用string类型的类型，这样只能发送一个收件人
            String[] median = receiveUser.split(",");// 对输入的多个邮件进行逗号分割
            for (int i = 0; i < median.length; i++) {
                list.add(new InternetAddress(median[i]));
            }
            InternetAddress[] address = (InternetAddress[]) list.toArray(new InternetAddress[list.size()]);
            message.setRecipients(Message.RecipientType.TO, address);// 当邮件有多个收件人时，用逗号隔开
            // 邮件主题
            message.setSubject(subject);

            String content = sendHtml.toString();
            // 邮件内容,也可以使纯文本"text/plain"
            message.setContent(content, "text/html;charset=UTF-8");

            // 保存邮件
            message.saveChanges();

            transport = session.getTransport("smtp");
            // smtp验证，就是你用来发邮件的邮箱用户名密码
            transport.connect(mailHost, sender_username, sender_password);
            // 发送
            transport.sendMessage(message, message.getAllRecipients());
            // System.out.println("send success!");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (transport != null) {
                try {
                    transport.close();
                }
                catch (MessagingException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 发送邮件
     * 
     * @param subject 邮件主题
     * @param sendHtml 邮件内容
     * @param receiveUser 收件人地址
     * @param attachment 附件
     */
    public void doSendHtmlEmail(String subject, String sendHtml, String receiveUser, File attachment) {
        try {
            // 发件人
            InternetAddress from = new InternetAddress(sender_username);
            message.setFrom(from);

            // 收件人
            InternetAddress to = new InternetAddress(receiveUser);
            message.setRecipient(Message.RecipientType.TO, to);

            // 邮件主题
            message.setSubject(subject);

            // 向multipart对象中添加邮件的各个部分内容，包括文本内容和附件
            Multipart multipart = new MimeMultipart();

            // 添加邮件正文
            BodyPart contentPart = new MimeBodyPart();
            contentPart.setContent(sendHtml, "text/html;charset=UTF-8");
            multipart.addBodyPart(contentPart);

            // 添加附件的内容
            if (attachment != null) {
                BodyPart attachmentBodyPart = new MimeBodyPart();
                DataSource source = new FileDataSource(attachment);
                attachmentBodyPart.setDataHandler(new DataHandler(source));

                // 网上流传的解决文件名乱码的方法，其实用MimeUtility.encodeWord就可以很方便的搞定
                // 这里很重要，通过下面的Base64编码的转换可以保证你的中文附件标题名在发送时不会变成乱码
                // sun.misc.BASE64Encoder enc = new sun.misc.BASE64Encoder();
                // messageBodyPart.setFileName("=?GBK?B?" + enc.encode(attachment.getName().getBytes()) + "?=");

                // MimeUtility.encodeWord可以避免文件名乱码
                attachmentBodyPart.setFileName(MimeUtility.encodeWord(attachment.getName()));
                multipart.addBodyPart(attachmentBodyPart);
            }

            // 将multipart对象放到message中
            message.setContent(multipart);
            // 保存邮件
            message.saveChanges();

            transport = session.getTransport("smtp");
            // smtp验证，就是你用来发邮件的邮箱用户名密码
            transport.connect(mailHost, sender_username, sender_password);
            // 发送
            transport.sendMessage(message, message.getAllRecipients());

            System.out.println("send success!");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (transport != null) {
                try {
                    transport.close();
                }
                catch (MessagingException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 规定附件名发送邮件
     * 
     * @param subject 邮件主题
     * @param sendHtml 邮件内容
     * @param receiveUser 收件人地址
     * @param attachment 附件
     */
    public void doSendHtmlEmailForFileName(String subject, String sendHtml, String receiveUser, File attachment,
            String fileName) {
        try {
            // 发件人
            InternetAddress from = new InternetAddress(sender_username);
            message.setFrom(from);

            // 收件人
            InternetAddress to = new InternetAddress(receiveUser);
            message.setRecipient(Message.RecipientType.TO, to);

            // 邮件主题
            message.setSubject(subject);

            // 向multipart对象中添加邮件的各个部分内容，包括文本内容和附件
            Multipart multipart = new MimeMultipart();

            // 添加邮件正文
            BodyPart contentPart = new MimeBodyPart();
            contentPart.setContent(sendHtml, "text/html;charset=UTF-8");
            multipart.addBodyPart(contentPart);

            // 添加附件的内容
            if (attachment != null) {
                BodyPart attachmentBodyPart = new MimeBodyPart();
                DataSource source = new FileDataSource(attachment);
                attachmentBodyPart.setDataHandler(new DataHandler(source));

                // 网上流传的解决文件名乱码的方法，其实用MimeUtility.encodeWord就可以很方便的搞定
                // 这里很重要，通过下面的Base64编码的转换可以保证你的中文附件标题名在发送时不会变成乱码
                // sun.misc.BASE64Encoder enc = new sun.misc.BASE64Encoder();
                // messageBodyPart.setFileName("=?GBK?B?" + enc.encode(attachment.getName().getBytes()) + "?=");

                // MimeUtility.encodeWord可以避免文件名乱码
                attachmentBodyPart.setFileName(MimeUtility.encodeWord(fileName));
                multipart.addBodyPart(attachmentBodyPart);
            }

            // 将multipart对象放到message中
            message.setContent(multipart);
            // 保存邮件
            message.saveChanges();

            transport = session.getTransport("smtp");
            // smtp验证，就是你用来发邮件的邮箱用户名密码
            transport.connect(mailHost, sender_username, sender_password);
            // 发送
            transport.sendMessage(message, message.getAllRecipients());

            System.out.println("send success!");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (transport != null) {
                try {
                    transport.close();
                }
                catch (MessagingException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        JavaMail se = new JavaMail(false);
        // se.doSendHtmlEmail("邮件主题", "邮件内容", "cc_6688211@sina.com");
        // File affix = new File("D:\\json.txt");

        File affix = new File("D:\\测试pdf2016.pdf");
        System.out.println(new Date());
        // se.doSendHtmlEmail("邮件主题", "邮件内容", "chenhan_ntu@sina.cn", affix);
        // se.doSendHtmlEmail("邮件主题", "邮件内容", "ndonghong@tianan-insurance.com", affix);
        // se.doSendHtmlEmail("邮件主题", "邮件内容", "258457487@qq.com", affix);
        se.doSendHtmlEmail("邮件主题", "中午吃啥？" + new Date(), "cc_6688211@sina.com");
    }

    /**
     * 规定附件名发送邮件
     * 
     * @param subject 邮件主题
     * @param sendHtml 邮件内容
     * @param receiveUser 收件人地址
     * @param attachment 附件
     */
    public void doSendHtmlEmailForFileName1(String subject, String sendHtml, String receiveUser, File attachment,
            String fileName) {
        try {
            // 发件人
            InternetAddress from = new InternetAddress(MimeUtility.encodeWord("富管家平台") + " <" + vipsender_username
                    + ">");
            message.setFrom(from);

            // 收件人
            InternetAddress to = new InternetAddress(receiveUser);
            message.setRecipient(Message.RecipientType.TO, to);

            // 邮件主题
            message.setSubject(subject);

            // 向multipart对象中添加邮件的各个部分内容，包括文本内容和附件
            Multipart multipart = new MimeMultipart();

            // 添加邮件正文
            BodyPart contentPart = new MimeBodyPart();
            contentPart.setContent(sendHtml, "text/html;charset=UTF-8");
            multipart.addBodyPart(contentPart);

            // 添加附件的内容
            if (attachment != null) {
                BodyPart attachmentBodyPart = new MimeBodyPart();
                DataSource source = new FileDataSource(attachment);
                attachmentBodyPart.setDataHandler(new DataHandler(source));

                // 网上流传的解决文件名乱码的方法，其实用MimeUtility.encodeWord就可以很方便的搞定
                // 这里很重要，通过下面的Base64编码的转换可以保证你的中文附件标题名在发送时不会变成乱码
                // sun.misc.BASE64Encoder enc = new sun.misc.BASE64Encoder();
                // messageBodyPart.setFileName("=?GBK?B?" + enc.encode(attachment.getName().getBytes()) + "?=");

                // MimeUtility.encodeWord可以避免文件名乱码
                attachmentBodyPart.setFileName(MimeUtility.encodeWord(fileName));
                multipart.addBodyPart(attachmentBodyPart);
            }

            // 将multipart对象放到message中
            message.setContent(multipart);
            // 保存邮件
            message.saveChanges();

            transport = session.getTransport("smtp");
            // smtp验证，就是你用来发邮件的邮箱用户名密码
            transport.connect(mailHost, vipsender_username, vipsender_password);
            // 发送
            transport.sendMessage(message, message.getAllRecipients());

            System.out.println("send success!");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (transport != null) {
                try {
                    transport.close();
                }
                catch (MessagingException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}