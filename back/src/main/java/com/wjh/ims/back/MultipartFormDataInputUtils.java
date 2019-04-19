package com.wjh.ims.back;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.wjh.ims.common.utils.PatternUtils;
import javazoom.jl.decoder.Bitstream;
import javazoom.jl.decoder.BitstreamException;
import javazoom.jl.decoder.Header;
import org.apache.commons.io.IOUtils;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Copyright (C), 2015-2018
 * FileName: MultipartFormDataInputUtils
 * Author:   jcj
 * Date:     2018/9/25 9:11
 * Description: rest形式的字节流表单数据处理工具类
 */
public class MultipartFormDataInputUtils {
    
    private static final Logger logger = LoggerFactory.getLogger(MultipartFormDataInputUtils.class);

    /**
     * 功能描述: <br> 根据key获取form数据
     * @version: 1.0.0
     * @Author: jcj
     * @Date: 2018/9/25 9:13
     */
    public static String getByKey(MultipartFormDataInput input, String key)
    {
        List<InputPart> ips=getInputPartByInput(input,key);
        if (CollectionUtils.isEmpty(ips)){
            return null;
        }
        String result = null;
        for (InputPart ip : ips)
        {
            try
            {
                result=changeStream2Str(ip,"utf-8");
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        return result;
    }


    /**
     * 功能描述: <br>根据key获取form数据  集合
     * @version: 1.0.0
     * @Author: jcj
     * @Date: 2018/10/18 14:51
     */
    public static List<String> getListByKey(MultipartFormDataInput input, String key)
    {
        List<InputPart> ips=getInputPartByInput(input,key);
        if (CollectionUtils.isEmpty(ips)){
            return null;
        }
        List<String> result= Lists.newArrayList();
        for (InputPart ip : ips)
        {
            try
            {
                result .add(changeStream2Str(ip, "utf-8")) ;
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 功能描述: <br> 通过key获取file相对路径，直接存储qiniu，返回相对路径
     * @version: 1.0.0
     * @Author: jcj
     * @Date: 2018/9/25 9:38
     */
    public static String getFileByKey(MultipartFormDataInput input, String key)
    {
        List<InputPart> ips=getInputPartByInput(input,key);
        if (CollectionUtils.isEmpty(ips)){
            return null;
        }
        String result=null;
        InputStream inputStream;
        for (InputPart ip : ips)
        {
            try {
                inputStream = ip.getBody(InputStream.class, null);
                String fileName = getFileNameByInputPart(ip);
                // qiniu OSS 存储
//                result = QiniuOssUtils.upload(inputStream, fileName);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 功能描述: <br> 多附件上传
     * @version: 1.0.0
     * @Author: jcj
     * @Date: 2018/10/18 10:38
     */
    public static List<String> getFilesByKey(MultipartFormDataInput input, String key)
    {
        List<InputPart> ips=getInputPartByInput(input,key);
        if (CollectionUtils.isEmpty(ips)){
            return null;
        }
        List<String> result=Lists.newArrayList();
        InputStream inputStream;
        for (InputPart ip : ips)
        {
            try {

                inputStream = ip.getBody(InputStream.class, null);
                String fileName = getFileNameByInputPart(ip);

                // qiniu OSS 存储
//                String fileUrl = QiniuOssUtils.upload(inputStream, fileName);
//                result.add(fileUrl);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        return result;
    }

   private static List<InputPart> getInputPartByInput(MultipartFormDataInput input,String key) {
       Map<String, List<InputPart>> dataMaps = input.getFormDataMap();
       return dataMaps.get(key);
    }

    /**
     * 功能描述: <br> 流转换
     * @version: 1.0.0
     * @Author: jcj
     * @Date: 2018/10/18 14:49
     */
    private static String changeStream2Str(InputPart ip, String chartset)
        throws IOException
    {
        InputStream inputStream = ip.getBody(InputStream.class, null);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] bytes = IOUtils.toByteArray(inputStream);
        if (bytes.length == 0)
        {
            return null;
        }
        bos.write(bytes);
        return bos.toString(chartset);
    }

    private static String getFileNameByInputPart(InputPart ip){
        String fileType = "." + PatternUtils.getFileTypeFromContentType(JSONObject.toJSONString(ip.getHeaders()));
        Random random = new Random();
        String name = random.nextInt(10000) + System.currentTimeMillis() + fileType;
        return name;
    }


    /**
     * 功能描述: <br> 获取视频、音频时长
     * @version: 1.0.0
     * @Author: jcj
     * @Date: 2018/11/6 14:02
     */
    public static int getFileDurationByKey(MultipartFormDataInput input,String key) throws Exception {
        int result=0;
        List<InputPart> ips=getInputPartByInput(input,key);
        if (CollectionUtils.isEmpty(ips)){
            return result;
        }
        InputStream inputStream;
        for (InputPart ip : ips)
        {
            try {
                inputStream = ip.getBody(InputStream.class, null);
                result = getDurationByStream(inputStream);
            }
            catch (IOException e)
            {
                logger.info("operation:get file duration by key ,ioException response:"+e.getMessage());
            }
        }
        return result;
    }
    
    public static int getDurationByStream(InputStream inputStream)
        throws BitstreamException, IOException
    {
        int result = 0;
        BufferedInputStream bis = new BufferedInputStream(inputStream);
        Bitstream bt = new Bitstream(bis);
        Header h = bt.readFrame();
        
        byte[] size = IOUtils.toByteArray(inputStream);
        int b = size.length;
        result = (int)h.total_ms(b) / 1000;
        
        return result;
    }

    public static int getDurationByUrl(String url) throws BitstreamException, IOException {
        int result = 0;
        URL urlfile = new URL(url);
        URLConnection con = null;
        try {
            con = urlfile.openConnection();
        } catch (IOException e) {
            logger.info("operation:get file duration by url ,ioException response:"+e.getMessage());
        }
        int b = con.getContentLength();
        BufferedInputStream bis = new BufferedInputStream(con.getInputStream());
        Bitstream bt = new Bitstream(bis);
        // 不知道什么原因 这个方法调用两次即可获取到header的对象
        bt.readFrame();
        Header h = bt.readFrame();
        result = (int) h.total_ms(b) / 1000;
        return result;
    }
}
