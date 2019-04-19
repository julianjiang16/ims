package com.wjh.ims.common.umeng.push.ios;

import com.wjh.ims.common.umeng.exception.RecruitException;
import com.wjh.ims.common.umeng.push.IOSNotification;
import org.json.JSONObject;

public class IOSGroupcast extends IOSNotification {
	public IOSGroupcast(String appkey,String appMasterSecret) throws RecruitException {
			setAppMasterSecret(appMasterSecret);
			setPredefinedKeyValue("appkey", appkey);
			this.setPredefinedKeyValue("type", "groupcast");	
	}
	
	public void setFilter(JSONObject filter) throws RecruitException {
    	setPredefinedKeyValue("filter", filter);
    }
}
