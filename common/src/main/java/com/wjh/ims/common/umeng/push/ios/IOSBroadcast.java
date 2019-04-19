package com.wjh.ims.common.umeng.push.ios;


import com.wjh.ims.common.umeng.exception.RecruitException;
import com.wjh.ims.common.umeng.push.IOSNotification;

public class IOSBroadcast extends IOSNotification {
	public IOSBroadcast(String appkey,String appMasterSecret) throws RecruitException {
			setAppMasterSecret(appMasterSecret);
			setPredefinedKeyValue("appkey", appkey);
			this.setPredefinedKeyValue("type", "broadcast");	
		
	}
}
