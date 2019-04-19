package com.wjh.ims.common.umeng.push.android;

import com.wjh.ims.common.umeng.exception.RecruitException;
import com.wjh.ims.common.umeng.push.AndroidNotification;
import org.json.JSONObject;

public class AndroidGroupcast extends AndroidNotification {
	public AndroidGroupcast(String appkey,String appMasterSecret) throws RecruitException {
			setAppMasterSecret(appMasterSecret);
			setPredefinedKeyValue("appkey", appkey);
			this.setPredefinedKeyValue("type", "groupcast");	
	}
	
	public void setFilter(JSONObject filter) throws RecruitException {
    	setPredefinedKeyValue("filter", filter);
    }
}
