package com.wjh.ims.common.umeng.push.android;


import com.wjh.ims.common.umeng.exception.RecruitException;
import com.wjh.ims.common.umeng.push.AndroidNotification;

public class AndroidBroadcast extends AndroidNotification {
	public AndroidBroadcast(String appkey,String appMasterSecret) throws RecruitException {
			setAppMasterSecret(appMasterSecret);
			setPredefinedKeyValue("appkey", appkey);
			this.setPredefinedKeyValue("type", "broadcast");	
	}
}
