package com.monkey.gzpt.service.impl;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.monkey.gzpt.service.BasicEventService;

@Service
public class BasicEventServiceImpl implements BasicEventService {

    /**
     * 
     * 客户点击“接入客服”按钮 客户输入信息
     * 
     * @param
     * @since 2016-12-21 下午17:34:50
     * @author SunMinghao
     * @return
     */
    @Override
    public String clickToCustomerService(JSONObject jsonObject, String backBaowen) throws Exception {
        return backBaowen;
    }

    @Override
    public String gotoKefu(JSONObject jsonObject, String backBaowen) throws Exception {
        return backBaowen;
    }

    // 用户在关注与取消关注公众号时，微信会把这个事件推送到开发者填写的URL。方便开发者给用户下发欢迎消息或者做帐号的解绑。
    // 微信服务器在五秒内收不到响应会断掉连接，并且重新发起请求，总共重试三次。
    // 关于重试的消息排重，推荐使用FromUserName + CreateTime 排重。
    // 假如服务器无法保证在五秒内处理并回复，可以直接回复空串，微信服务器不会对此作任何处理，并且不会发起重试。
    //
    // 推送XML数据包示例：
    // <xml>
    // <ToUserName><![CDATA[toUser]]></ToUserName>
    // <FromUserName><![CDATA[FromUser]]></FromUserName>
    // <CreateTime>123456789</CreateTime>
    // <MsgType><![CDATA[event]]></MsgType>
    // <Event><![CDATA[subscribe]]></Event>
    // </xml>
    // 参数说明：
    // 参数 描述
    // ToUserName 开发者微信号
    // FromUserName 发送方帐号（一个OpenID）
    // CreateTime 消息创建时间 （整型）
    // MsgType 消息类型，event
    // Event 事件类型，subscribe(订阅)、unsubscribe(取消订阅)
    @Override
    public String subscribe(JSONObject jsonObject, String backBaowen) throws Exception {
        return backBaowen;
    }

    @Override
    public String unsubscribe(JSONObject jsonObject, String backBaowen) throws Exception {
        return backBaowen;
    }

    // 推送XML数据包示例：
    // <xml>
    // <ToUserName><![CDATA[toUser]]></ToUserName>
    // <FromUserName><![CDATA[FromUser]]></FromUserName>
    // <CreateTime>123456789</CreateTime>
    // <MsgType><![CDATA[event]]></MsgType>
    // <Event><![CDATA[SCAN]]></Event>
    // <EventKey><![CDATA[SCENE_VALUE]]></EventKey>
    // <Ticket><![CDATA[TICKET]]></Ticket>
    // </xml>
    // 参数说明：
    // 参数 描述
    // ToUserName 开发者微信号
    // FromUserName 发送方帐号（一个OpenID）
    // CreateTime 消息创建时间 （整型）
    // MsgType 消息类型，event
    // Event 事件类型，SCAN
    // EventKey 事件KEY值，是一个32位无符号整数，即创建二维码时的二维码scene_id
    // Ticket 二维码的ticket，可用来换取二维码图片
    // 扫描带参数二维码事件
    @Override
    public String scan(JSONObject jsonObject, String backBaowen) throws Exception {
        return backBaowen;
    }

    // 用户同意上报地理位置后，每次进入公众号会话时，都会在进入时上报地理位置，或在进入会话后每5秒上报一次地理位置，公众号可以在公众平台网站中修改以上设置。上报地理位置时，微信会将上报地理位置事件推送到开发者填写的URL。
    // 推送XML数据包示例：
    // <xml>
    // <ToUserName><![CDATA[toUser]]></ToUserName>
    // <FromUserName><![CDATA[fromUser]]></FromUserName>
    // <CreateTime>123456789</CreateTime>
    // <MsgType><![CDATA[event]]></MsgType>
    // <Event><![CDATA[LOCATION]]></Event>
    // <Latitude>23.137466</Latitude>
    // <Longitude>113.352425</Longitude>
    // <Precision>119.385040</Precision>
    // </xml>
    // 参数说明：
    // 参数 描述
    // ToUserName 开发者微信号
    // FromUserName 发送方帐号（一个OpenID）
    // CreateTime 消息创建时间 （整型）
    // MsgType 消息类型，event
    // Event 事件类型，LOCATION
    // Latitude 地理位置纬度
    // Longitude 地理位置经度
    // Precision 地理位置精度
    // 上报地理位置事件
    @Override
    public String location(JSONObject jsonObject, String backBaowen) throws Exception {
        return backBaowen;
    }

    // 用户点击自定义菜单后，微信会把点击事件推送给开发者，请注意，点击菜单弹出子菜单，不会产生上报。

    @Override
    public String click(JSONObject jsonObject, String backBaowen) throws Exception {
        return backBaowen;
    }

    // 推送XML数据包示例：
    // <xml>
    // <ToUserName><![CDATA[toUser]]></ToUserName>
    // <FromUserName><![CDATA[FromUser]]></FromUserName>
    // <CreateTime>123456789</CreateTime>
    // <MsgType><![CDATA[event]]></MsgType>
    // <Event><![CDATA[VIEW]]></Event>
    // <EventKey><![CDATA[www.qq.com]]></EventKey>
    // <MenuId>MENUID</MenuId>
    // </xml>
    // 参数说明：
    // 参数 描述
    // ToUserName 开发者微信号
    // FromUserName 发送方帐号（一个OpenID）
    // CreateTime 消息创建时间 （整型）
    // MsgType 消息类型，event
    // Event 事件类型，VIEW
    // EventKey 事件KEY值，设置的跳转URL
    // MenuID 指菜单ID，如果是个性化菜单，则可以通过这个字段，知道是哪个规则的菜单被点击了。
    @Override
    public String view(JSONObject jsonObject, String backBaowen) throws Exception {
        return backBaowen;
    }

    // 推送XML数据包示例：
    // <xml><ToUserName><![CDATA[gh_e136c6e50636]]></ToUserName>
    // <FromUserName><![CDATA[oMgHVjngRipVsoxg6TuX3vz6glDg]]></FromUserName>
    // <CreateTime>1408090502</CreateTime>
    // <MsgType><![CDATA[event]]></MsgType>
    // <Event><![CDATA[scancode_push]]></Event>
    // <EventKey><![CDATA[6]]></EventKey>
    // <ScanCodeInfo><ScanType><![CDATA[qrcode]]></ScanType>
    // <ScanResult><![CDATA[1]]></ScanResult>
    // </ScanCodeInfo>
    // </xml>
    // 参数说明：
    // 参数 描述
    // ToUserName 开发者微信号
    // FromUserName 发送方帐号（一个OpenID）
    // CreateTime 消息创建时间（整型）
    // MsgType 消息类型，event
    // Event 事件类型，scancode_push
    // EventKey 事件KEY值，由开发者在创建菜单时设定
    // ScanCodeInfo 扫描信息
    // ScanType 扫描类型，一般是qrcode
    // ScanResult 扫描结果，即二维码对应的字符串信息
    @Override
    public String scancodePush(JSONObject jsonObject, String backBaowen) throws Exception {
        return backBaowen;
    }

    // 推送XML数据包示例：
    // <xml><ToUserName><![CDATA[gh_e136c6e50636]]></ToUserName>
    // <FromUserName><![CDATA[oMgHVjngRipVsoxg6TuX3vz6glDg]]></FromUserName>
    // <CreateTime>1408090606</CreateTime>
    // <MsgType><![CDATA[event]]></MsgType>
    // <Event><![CDATA[scancode_waitmsg]]></Event>
    // <EventKey><![CDATA[6]]></EventKey>
    // <ScanCodeInfo><ScanType><![CDATA[qrcode]]></ScanType>
    // <ScanResult><![CDATA[2]]></ScanResult>
    // </ScanCodeInfo>
    // </xml>
    // 参数说明：
    // 参数 描述
    // ToUserName 开发者微信号
    // FromUserName 发送方帐号（一个OpenID）
    // CreateTime 消息创建时间 （整型）
    // MsgType 消息类型，event
    // Event 事件类型，scancode_waitmsg
    // EventKey 事件KEY值，由开发者在创建菜单时设定
    // ScanCodeInfo 扫描信息
    // ScanType 扫描类型，一般是qrcode
    // ScanResult 扫描结果，即二维码对应的字符串信息
    @Override
    public String scancodeWaitmsg(JSONObject jsonObject, String backBaowen) throws Exception {
        return backBaowen;
    }

    // 推送XML数据包示例：
    // <xml><ToUserName><![CDATA[gh_e136c6e50636]]></ToUserName>
    // <FromUserName><![CDATA[oMgHVjngRipVsoxg6TuX3vz6glDg]]></FromUserName>
    // <CreateTime>1408090651</CreateTime>
    // <MsgType><![CDATA[event]]></MsgType>
    // <Event><![CDATA[pic_sysphoto]]></Event>
    // <EventKey><![CDATA[6]]></EventKey>
    // <SendPicsInfo><Count>1</Count>
    // <PicList><item><PicMd5Sum><![CDATA[1b5f7c23b5bf75682a53e7b6d163e185]]></PicMd5Sum>
    // </item>
    // </PicList>
    // </SendPicsInfo>
    // </xml>
    // 参数说明：
    // 参数 描述
    // ToUserName 开发者微信号
    // FromUserName 发送方帐号（一个OpenID）
    // CreateTime 消息创建时间 （整型）
    // MsgType 消息类型，event
    // Event 事件类型，pic_sysphoto
    // EventKey 事件KEY值，由开发者在创建菜单时设定
    // SendPicsInfo 发送的图片信息
    // Count 发送的图片数量
    // PicList 图片列表
    // PicMd5Sum 图片的MD5值，开发者若需要，可用于验证接收到图片

    @Override
    public String picSysphoto(JSONObject jsonObject, String backBaowen) throws Exception {
        return backBaowen;
    }

    // 推送XML数据包示例：
    // <xml><ToUserName><![CDATA[gh_e136c6e50636]]></ToUserName>
    // <FromUserName><![CDATA[oMgHVjngRipVsoxg6TuX3vz6glDg]]></FromUserName>
    // <CreateTime>1408090816</CreateTime>
    // <MsgType><![CDATA[event]]></MsgType>
    // <Event><![CDATA[pic_photo_or_album]]></Event>
    // <EventKey><![CDATA[6]]></EventKey>
    // <SendPicsInfo><Count>1</Count>
    // <PicList><item><PicMd5Sum><![CDATA[5a75aaca956d97be686719218f275c6b]]></PicMd5Sum>
    // </item>
    // </PicList>
    // </SendPicsInfo>
    // </xml>
    // 参数说明：
    // 参数 描述
    // ToUserName 开发者微信号
    // FromUserName 发送方帐号（一个OpenID）
    // CreateTime 消息创建时间 （整型）
    // MsgType 消息类型，event
    // Event 事件类型，pic_photo_or_album
    // EventKey 事件KEY值，由开发者在创建菜单时设定
    // SendPicsInfo 发送的图片信息
    // Count 发送的图片数量
    // PicList 图片列表
    // PicMd5Sum 图片的MD5值，开发者若需要，可用于验证接收到图片

    @Override
    public String picPhotoOrAlbum(JSONObject jsonObject, String backBaowen) throws Exception {
        return backBaowen;
    }

    // 推送XML数据包示例：
    // <xml><ToUserName><![CDATA[gh_e136c6e50636]]></ToUserName>
    // <FromUserName><![CDATA[oMgHVjngRipVsoxg6TuX3vz6glDg]]></FromUserName>
    // <CreateTime>1408090816</CreateTime>
    // <MsgType><![CDATA[event]]></MsgType>
    // <Event><![CDATA[pic_weixin]]></Event>
    // <EventKey><![CDATA[6]]></EventKey>
    // <SendPicsInfo><Count>1</Count>
    // <PicList><item><PicMd5Sum><![CDATA[5a75aaca956d97be686719218f275c6b]]></PicMd5Sum>
    // </item>
    // </PicList>
    // </SendPicsInfo>
    // </xml>
    // 参数说明：
    // 参数 描述
    // ToUserName 开发者微信号
    // FromUserName 发送方帐号（一个OpenID）
    // CreateTime 消息创建时间 （整型）
    // MsgType 消息类型，event
    // Event 事件类型，pic_weixin
    // EventKey 事件KEY值，由开发者在创建菜单时设定
    // SendPicsInfo 发送的图片信息
    // Count 发送的图片数量
    // PicList 图片列表
    // PicMd5Sum 图片的MD5值，开发者若需要，可用于验证接收到图片

    @Override
    public String picWeixin(JSONObject jsonObject, String backBaowen) throws Exception {
        return backBaowen;
    }

    // 推送XML数据包示例：
    // <xml><ToUserName><![CDATA[gh_e136c6e50636]]></ToUserName>
    // <FromUserName><![CDATA[oMgHVjngRipVsoxg6TuX3vz6glDg]]></FromUserName>
    // <CreateTime>1408091189</CreateTime>
    // <MsgType><![CDATA[event]]></MsgType>
    // <Event><![CDATA[location_select]]></Event>
    // <EventKey><![CDATA[6]]></EventKey>
    // <SendLocationInfo><Location_X><![CDATA[23]]></Location_X>
    // <Location_Y><![CDATA[113]]></Location_Y>
    // <Scale><![CDATA[15]]></Scale>
    // <Label><![CDATA[ 广州市海珠区客村艺苑路 106号]]></Label>
    // <Poiname><![CDATA[]]></Poiname>
    // </SendLocationInfo>
    // </xml>
    // 参数说明：
    // 参数 描述
    // ToUserName 开发者微信号
    // FromUserName 发送方帐号（一个OpenID）
    // CreateTime 消息创建时间 （整型）
    // MsgType 消息类型，event
    // Event 事件类型，location_select
    // EventKey 事件KEY值，由开发者在创建菜单时设定
    // SendLocationInfo 发送的位置信息
    // Location_X X坐标信息
    // Location_Y Y坐标信息
    // Scale 精度，可理解为精度或者比例尺、越精细的话 scale越高
    // Label 地理位置的字符串信息
    // Poiname 朋友圈POI的名字，可能为空
    @Override
    public String location_select(JSONObject jsonObject, String backBaowen) throws Exception {
        return backBaowen;
    }

}
