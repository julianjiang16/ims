package com.wjh.ims.common.umeng;

import com.wjh.ims.common.umeng.enums.FollowUpOperationEnum;
import com.wjh.ims.common.umeng.enums.MessageUserGroupEnum;
import com.wjh.ims.common.umeng.enums.PushMessageEquipmentTypeEnum;
import com.wjh.ims.common.umeng.enums.RecruitErrorEnum;
import com.wjh.ims.common.umeng.exception.RecruitException;
import com.wjh.ims.common.umeng.model.UmengMessage;
import com.wjh.ims.common.umeng.push.AndroidNotification;
import com.wjh.ims.common.umeng.push.IOSNotification;
import com.wjh.ims.common.umeng.push.PushClient;
import com.wjh.ims.common.umeng.push.android.AndroidBroadcast;
import com.wjh.ims.common.umeng.push.android.AndroidGroupcast;
import com.wjh.ims.common.umeng.push.android.AndroidUnicast;
import com.wjh.ims.common.umeng.push.ios.IOSBroadcast;
import com.wjh.ims.common.umeng.push.ios.IOSGroupcast;
import com.wjh.ims.common.umeng.push.ios.IOSUnicast;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Copyright (C), 2015-2018, 大胜百一网络科技
 * FileName: UmengMessageUtils
 * Author:   jcj
 * Date:     2018/11/5 13:47
 * Description: 友盟推送工具类
 */
public class UmengMessageUtils {

    /** slf4j log */
    private static final Logger log = Logger.getLogger(UmengMessageUtils.class);

    /** umeng push client */
    private static PushClient client;

    /** android business umeng app key */
    private static String ANDROID_BUSINESS_APP_KEY = "";

    /** android business umeng app master secret */
    private static String ANDROID_BUSINESS_APP_MASTER_SECRET = "";

    /** android custom umeng app key */
    private static String ANDROID_CUSTOM_APP_KEY = "";

    /** android custom umeng app master secret */
    private static String ANDROID_CUSTOM_APP_MASTER_SECRET = "";

    /** ios business umeng app key */
    private static String IOS_BUSINESS_APP_KEY = "";

    /** ios business umeng app master secret */
    private static String IOS_BUSINESS_APP_MASTER_SECRET = "";

    /** ios custom umeng app key */
    private static String IOS_CUSTOM_APP_KEY = "";

    /** ios custom umeng app master secret */
    private static String IOS_CUSTOM_APP_MASTER_SECRET = "";

    /** filter tag key */
    private static final String TAG_KEY = "tag";

    /** filter and key */
    private static final String AND_KEY = "and";

    /** filter where key */
    private static final String WHERE_KEY = "where";

    private UmengMessageUtils() {
    }

    static {
        InputStream inputStream=UmengMessageUtils.class.getClassLoader().getResourceAsStream("config.properties");
        Properties properties=new Properties();
        client = new PushClient();
        try {
            properties.load(inputStream);
            // android b端
            ANDROID_BUSINESS_APP_KEY = properties.getProperty("android.business.umeng.app.key");
            ANDROID_BUSINESS_APP_MASTER_SECRET =properties.getProperty("android.business.umeng.app.master.secret");
            // android c端
            ANDROID_CUSTOM_APP_KEY = properties.getProperty("android.custom.umeng.app.key");
            ANDROID_CUSTOM_APP_MASTER_SECRET =properties.getProperty("android.custom.umeng.app.master.secret");
            // ios b端
            IOS_BUSINESS_APP_KEY = properties.getProperty("ios.business.umeng.app.key");
            IOS_BUSINESS_APP_MASTER_SECRET =properties.getProperty("ios.business.umeng.app.master.secret");
            // ios c端
            IOS_CUSTOM_APP_KEY = properties.getProperty("ios.custom.umeng.app.key");
            IOS_CUSTOM_APP_MASTER_SECRET =properties.getProperty("ios.custom.umeng.app.master.secret");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("UmengMessageUtils初始化，系统异常退出，异常信息："+e.getMessage());
            System.exit(1);
        }

    }


    /**
     * 功能描述: <br> 根据设备类型、用户群体推送单播
     * @version: 1.0.0
     * @Author: jcj
     * @Date: 2018/12/13 15:29
     */
    public static void sendUnicastByDeviceType(UmengMessage message, MessageUserGroupEnum group,
                                               PushMessageEquipmentTypeEnum type)
            throws IOException, RecruitException
    {
        switch (type)
        {
            case ALL_EQUIPMENT:
                sendIOSUnicastByUserGroup(message, group);
                sendAndroidBroadcastByUserGroup(message, group);
                break;
            case IOS_EQUIPMENT:
                sendIOSUnicastByUserGroup(message, group);
                break;
            case ANDROID_EQUIPMENT:
                sendAndroidBroadcastByUserGroup(message, group);
                break;
            default:
                break;
        }
    }

    /**
     * 功能描述: <br> 根据设备类型、用户群体推送广播
     * @version: 1.0.0
     * @Author: jcj
     * @Date: 2018/12/13 15:31
     */
    public static void sendBroadcastByDeviceType(UmengMessage message, MessageUserGroupEnum group,
                                               PushMessageEquipmentTypeEnum type)
            throws IOException, RecruitException
    {
        switch (type)
        {
            case ALL_EQUIPMENT:
                sendAndroidBroadcastByUserGroup(message, group);
                sendIOSBroadcastByUserGroup(message, group);
                break;
            case IOS_EQUIPMENT:
                sendIOSBroadcastByUserGroup(message, group);
                break;
            case ANDROID_EQUIPMENT:
                sendAndroidBroadcastByUserGroup(message, group);
                break;
            default:
                break;
        }
    }

    /**
     * 功能描述: <br> Android B端 单播
     * @version: 1.0.0
     * @Author: jcj
     * @Date: 2018/11/30 9:38
     */
    public static void sendAndroidBusinessUnicast(UmengMessage message) throws RecruitException, IOException {
        sendAndroidUnicast(message,ANDROID_BUSINESS_APP_KEY ,ANDROID_BUSINESS_APP_MASTER_SECRET);
    }

    /**
     * 功能描述: <br> Android C端 单播
     * @version: 1.0.0
     * @Author: jcj
     * @Date: 2018/11/30 9:42
     */
    public static void sendAndroidCustomUnicast(UmengMessage message) throws IOException, RecruitException {
        sendAndroidUnicast(message,ANDROID_CUSTOM_APP_KEY,ANDROID_CUSTOM_APP_MASTER_SECRET);
    }
    
    public static void sendAndroidUnicastByUserGroup(UmengMessage message, MessageUserGroupEnum group) throws IOException, RecruitException {
        switch (group)
        {
            case CUSTOMER_USER_GROUP:
                sendAndroidCustomUnicast(message);
                break;
            case BUSINESS_USER_GROUP:
                sendAndroidBusinessUnicast(message);
                break;
            default:
                break;
        }
    }

    /**
     * 功能描述: <br> IOS B端 单播
     * @version: 1.0.0
     * @Author: jcj
     * @Date: 2018/11/30 9:38
     */
    public static void sendIOSBusinessUnicast(UmengMessage message) throws RecruitException, IOException {
        sendIOSUnicast(message,IOS_BUSINESS_APP_KEY ,IOS_BUSINESS_APP_MASTER_SECRET);
    }

    /**
     * 功能描述: <br> IOS C端 单播
     * @version: 1.0.0
     * @Author: jcj
     * @Date: 2018/11/30 9:42
     */
    public static void sendIOSCustomUnicast(UmengMessage message) throws IOException, RecruitException {
        sendIOSUnicast(message,IOS_CUSTOM_APP_KEY,IOS_CUSTOM_APP_MASTER_SECRET);
    }

    public static void sendIOSUnicastByUserGroup(UmengMessage message, MessageUserGroupEnum group) throws IOException, RecruitException {
        switch (group)
        {
            case CUSTOMER_USER_GROUP:
                sendIOSCustomUnicast(message);
                break;
            case BUSINESS_USER_GROUP:
                sendIOSBusinessUnicast(message);
                break;
            default:
                break;
        }
    }

    /**
     * 功能描述: <br> Android C端广播
     * @version: 1.0.0
     * @Author: jcj
     * @Date: 2018/12/13 14:19
     */
    public static void sendAndroidCustomBroadcast(UmengMessage message) throws IOException, RecruitException {
        sendAndroidBroadcast(message,ANDROID_CUSTOM_APP_KEY,ANDROID_CUSTOM_APP_MASTER_SECRET);
    }

    /**
     * 功能描述: <br> Android B端广播
     * @version: 1.0.0
     * @Author: jcj
     * @Date: 2018/12/13 14:20
     */
    public static void sendAndroidBusinessBroadcast(UmengMessage message) throws IOException, RecruitException {
        sendAndroidBroadcast(message,ANDROID_BUSINESS_APP_KEY,ANDROID_BUSINESS_APP_MASTER_SECRET);
    }

    public static void sendAndroidBroadcastByUserGroup(UmengMessage message, MessageUserGroupEnum group) throws IOException, RecruitException {
        switch (group)
        {
            case CUSTOMER_USER_GROUP:
                sendAndroidCustomBroadcast(message);
                break;
            case BUSINESS_USER_GROUP:
                sendAndroidBusinessBroadcast(message);
                break;
            default:
                break;
        }
    }

    /**
     * 功能描述: <br> 发送Android 广播
     * @version: 1.0.0
     * @Author: jcj
     * @Date: 2018/11/5 14:48
     */
    private static void sendAndroidBroadcast(UmengMessage message,String key,String secret) throws RecruitException, IOException {

        // 1.构建模型
        AndroidBroadcast broadcast  = new AndroidBroadcast(key,secret);
        // 2.填充基本信息
        fillAndroidBasicInfo(broadcast, message);
        // 3.发送消息
        client.send(broadcast);
    }

    /**
     * 功能描述: <br> 发送Android 组播
     *           <br> message -> tags填写filter参数
     *           <br> 发送先需要添加tags 或者 确定tags已经存在
     * @version: 1.0.0
     * @Author: jcj
     * @Date: 2018/11/5 15:49
     */
    public static void sendAndroidGroupCast(UmengMessage message) throws RecruitException, IOException {

        AndroidGroupcast groupcast = new AndroidGroupcast("","");
        // 1.填充基本信息
        fillAndroidBasicInfo(groupcast, message);
        // 2.填充过滤条件
        groupcast.setFilter(createGroupFilter(message.getTags()));
        // 3.发送
        client.send(groupcast);
    }

    /**
     * 功能描述: <br> 发送Android 单播
     * @version: 1.0.0
     * @Author: jcj
     * @Date: 2018/11/5 16:30
     */
    private static void sendAndroidUnicast(UmengMessage message,String key,String secret) throws RecruitException, IOException {
        AndroidUnicast unicast = new AndroidUnicast(key,secret);
        // 1.填写设备token
        if (StringUtils.isEmpty(message.getReceiver())){
            log.error("发送友盟单播消息时，没有填写接收者的token，消息体：" + message);
            throw new RecruitException(RecruitErrorEnum.PUSH_ANDROID_MESSAGE_MISSING_PARAM_ERROR);
        }
        unicast.setDeviceToken( message.getReceiver());
        
        // 2.填充基本信息
        fillAndroidBasicInfo(unicast,message);

        // 3.发送
        client.send(unicast);

    }

    /**
     * 功能描述: <br> 推送ios C端广播
     * @version: 1.0.0
     * @Author: jcj
     * @Date: 2018/12/13 15:07
     */
    public static void sendIOSCustomBroadcast(UmengMessage message) throws IOException, RecruitException {
        sendIOSBroadcast(message,IOS_CUSTOM_APP_KEY,IOS_CUSTOM_APP_MASTER_SECRET);
    }

    /**
     * 功能描述: <br> 推送ios B端广播
     * @version: 1.0.0
     * @Author: jcj
     * @Date: 2018/12/13 15:07
     */
    public static void sendIOSBusinessBroadcast(UmengMessage message) throws IOException, RecruitException {
        sendIOSBroadcast(message,IOS_BUSINESS_APP_KEY,IOS_BUSINESS_APP_MASTER_SECRET);
    }

    /**
     * 功能描述: <br> 根据推送群体推送到指定的终端设备
     * @version: 1.0.0
     * @Author: jcj
     * @Date: 2018/12/13 15:07
     */
    public static void sendIOSBroadcastByUserGroup(UmengMessage message, MessageUserGroupEnum group) throws IOException, RecruitException {
        switch (group)
        {
            case CUSTOMER_USER_GROUP:
                sendIOSCustomBroadcast(message);
                break;
            case BUSINESS_USER_GROUP:
                sendIOSBusinessBroadcast(message);
                break;
            default:
                break;
        }
    }

    /**
     * 功能描述: <br> ios 广播
     * @version: 1.0.0
     * @Author: jcj
     * @Date: 2018/11/30 10:59
     */
    private static void sendIOSBroadcast(UmengMessage message,String key,String secret) throws RecruitException, IOException {

        // 1.构建广播模型
        IOSBroadcast broadcast = new IOSBroadcast(key,secret);

        // 2.填充广播内容
        fillIOSBasicInfo(broadcast,message);

        // 3.发送广播
        client.send(broadcast);
    }

    /**
     * 功能描述: <br> ios 组播
     * @version: 1.0.0
     * @Author: jcj
     * @Date: 2018/11/30 10:59
     */
    private static void sendIOSGroupcast(UmengMessage message,String key,String secret) throws RecruitException, IOException {

        // 1.构建广播模型
        IOSGroupcast groupcast = new IOSGroupcast(key,secret);

        // 2.填充广播内容
        fillIOSBasicInfo(groupcast,message);

        // 3.填充过滤条件
        groupcast.setFilter(createGroupFilter(message.getTags()));

        // 4.发送广播
        client.send(groupcast);
    }

    /**
     * 功能描述: <br> ios 单播
     * @version: 1.0.0
     * @Author: jcj
     * @Date: 2018/11/30 11:00
     */
    private static void sendIOSUnicast(UmengMessage message,String key,String secret) throws RecruitException, IOException {
        IOSUnicast unicast = new IOSUnicast(key,secret);
        // 1.填写设备token
        if (StringUtils.isEmpty(message.getReceiver())){
            log.error("发送友盟单播消息时，没有填写接收者的token，消息体：" + message);
            throw new RecruitException(RecruitErrorEnum.PUSH_ANDROID_MESSAGE_MISSING_PARAM_ERROR);
        }
        unicast.setDeviceToken( message.getReceiver());

        // 2.填充基本信息
        fillIOSBasicInfo(unicast,message);

        // 3.发送
        client.send(unicast);
    }

    /**
     * 功能描述: <br> 填充Android 发送消息基础信息
     * @version: 1.0.0
     * @Author: jcj
     * @Date: 2018/11/5 16:17
     */
    private static <S extends AndroidNotification> void fillAndroidBasicInfo(S source, UmengMessage message) throws RecruitException {
        if (StringUtils.isBlank(message.getTicker()) || StringUtils.isBlank(message.getTitle())
            || StringUtils.isBlank(message.getText()) || message.getOperation() == null)
        {
            log.error("发送友盟消息时，缺少必要参数，消息体：" + message);
            throw new RecruitException(RecruitErrorEnum.PUSH_ANDROID_MESSAGE_MISSING_PARAM_ERROR);
        }
        source.setDisplayType(message.getDisplay());
        if (message.getDisplay()== AndroidNotification.DisplayType.MESSAGE){
            source.setCustomField(message.getTitle());
        }else {
            source.setTitle(message.getTitle());
            source.setText(message.getText());
            source.setExpireTime(message.getExpireTime());
            source.setDescription(message.getDesc());
            source.setProductionMode(message.isProductionMode());
        }
        switch (message.getOperation()) {
            case OPEN_APP:
                source.goAppAfterOpen();
                break;
            case GO_TO_URL:
                if(StringUtils.isBlank(message.getUrlOrActivity())){
                    log.error("发送友盟单播消息时，没有填写打开的url，消息体：" + message);
                    throw new RecruitException(RecruitErrorEnum.PUSH_ANDROID_MESSAGE_MISSING_PARAM_ERROR);
                }
                source.goUrlAfterOpen(message.getUrlOrActivity());
                break;
            case GO_TO_ACTIVITY:
                if(StringUtils.isBlank(message.getUrlOrActivity())){
                    log.error("发送友盟单播消息时，没有填写打开的activity，消息体：" + message);
                    throw new RecruitException(RecruitErrorEnum.PUSH_ANDROID_MESSAGE_MISSING_PARAM_ERROR);
                }
                source.goActivityAfterOpen(message.getUrlOrActivity());
                break;
            default:
                source.goAppAfterOpen();
                break;
        }
        if (message.getExtraField() != null){
            for (Map.Entry<String,String> entry:message.getExtraField().entrySet()){
                source.setExtraField(entry.getKey(), entry.getValue());
            }
        }
    }

    /**
     * 功能描述: <br> 填充ios 基本数据
     * @version: 1.0.0
     * @Author: jcj
     * @Date: 2018/11/30 11:11
     */
    private static <S extends IOSNotification> void  fillIOSBasicInfo(S source, UmengMessage message) throws RecruitException {
        if (StringUtils.isBlank(message.getTitle()) || message.getOperation() == null)
        {
            log.error("发送友盟消息时，缺少必要参数，消息体：" + message);
            throw new RecruitException(RecruitErrorEnum.PUSH_ANDROID_MESSAGE_MISSING_PARAM_ERROR);
        }
        source.setExpireTime(message.getExpireTime());
        source.setProductionMode(message.isProductionMode());

        // todo jcj ios message body
        source.setDescription(message.getDesc());

        JSONObject alertJson=new JSONObject();
        alertJson.put("title",message.getTitle());
        if (StringUtils.isNotEmpty(message.getText())){
            alertJson.put("subtitle", message.getText());
        }
        alertJson.put("body",  message.getDesc());
        switch (message.getOperation()) {
            case OPEN_APP:
                alertJson.put("after_open", FollowUpOperationEnum.OPEN_APP.getEnglishName());
                alertJson.put("url","" );
                alertJson.put("activity","" );
                break;
            case GO_TO_URL:
                if(StringUtils.isBlank(message.getUrlOrActivity())){
                    log.error("发送友盟单播消息时，没有填写打开的url，消息体：" + message);
                    throw new RecruitException(RecruitErrorEnum.PUSH_ANDROID_MESSAGE_MISSING_PARAM_ERROR);
                }
                alertJson.put("after_open", FollowUpOperationEnum.GO_TO_URL.getEnglishName());
                alertJson.put("url",message.getUrlOrActivity());
                alertJson.put("activity","" );
                break;
            case GO_TO_ACTIVITY:
                if(StringUtils.isBlank(message.getUrlOrActivity())){
                    log.error("发送友盟单播消息时，没有填写打开的activity，消息体：" + message);
                    throw new RecruitException(RecruitErrorEnum.PUSH_ANDROID_MESSAGE_MISSING_PARAM_ERROR);
                }
                alertJson.put("after_open", FollowUpOperationEnum.GO_TO_ACTIVITY.getEnglishName());
                alertJson.put("url","" );
                alertJson.put("activity",message.getUrlOrActivity());
                break;
            default:
                alertJson.put("after_open", FollowUpOperationEnum.OPEN_APP.getEnglishName());
                alertJson.put("url","" );
                alertJson.put("activity","" );
                break;
        }
        source.setSound("default");
        source.setAlert(alertJson);
        source.setBadge(1);
    }


    /**
     * 功能描述: <br> 构建filter json
     * @version: 1.0.0
     * @Author: jcj
     * @Date: 2018/11/5 16:27
     */
    private static JSONObject createGroupFilter(List<String> tags){
        JSONObject filterJson = new JSONObject();
        JSONObject whereJson = new JSONObject();
        JSONArray tagArray = new JSONArray();
        for (String tag : tags) {
            JSONObject tagJson = new JSONObject();
            tagJson.put(TAG_KEY, tag);
            tagArray.put(tagJson);
        }
        whereJson.put(AND_KEY, tagArray);
        filterJson.put(WHERE_KEY, whereJson);
        return filterJson;
    }
}
