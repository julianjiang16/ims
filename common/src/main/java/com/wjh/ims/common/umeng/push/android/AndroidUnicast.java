package com.wjh.ims.common.umeng.push.android;

import com.wjh.ims.common.umeng.exception.RecruitException;
import com.wjh.ims.common.umeng.push.AndroidNotification;

public class AndroidUnicast extends AndroidNotification {
	public AndroidUnicast(String appkey,String appMasterSecret) throws RecruitException {
			setAppMasterSecret(appMasterSecret);
			setPredefinedKeyValue("appkey", appkey);
			this.setPredefinedKeyValue("type", "unicast");	
	}
	
	public void setDeviceToken(String token) throws RecruitException {
    	setPredefinedKeyValue("device_tokens", token);
    }

}