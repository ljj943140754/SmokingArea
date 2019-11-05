package com.common.utils;


import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
 
public class SendSms {
	private static final String S_KEY = "yvqIpgKTFW72OmIOVWfNp0aHISj56U";//accessSecret
	private static final String A_ID = "LTAI8d3Hbaufuv1l";//accessKeyId
	
	public static void main(String[] args) {
		send("18370097140", "123456");
	}
    public static void send(String phone,String code) {
        DefaultProfile profile = DefaultProfile.getProfile("default",A_ID ,S_KEY);
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");//API域名。
        request.setVersion("2017-05-25");
        request.setAction("SendSms");//API名称。
        request.putQueryParameter("RegionId", "default");
        request.putQueryParameter("PhoneNumbers", phone);//手机号码
        request.putQueryParameter("SignName", "娱味");//短信签名。
        request.putQueryParameter("TemplateCode", "SMS_176524147");//短信模板。
        
      
    	JsonObject json = new JsonObject();
    	//模板变量为${number}  变量替换值<=6位数字或字母
    	json.addProperty("number", code);
    	//json.addProperty("minute", minute);
    	//模板变量中的值，必须JSON格式。
        request.putQueryParameter("TemplateParam", json.toString());
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
            //返回结果：要接受就自己反序列化一下。
            //{"Message":"OK","RequestId":"F9D67F8E-2BC4-46AD-AB86-71DB868D5F1E","BizId":"119617968608499819^0","Code":"OK"}
            //更多返回结果请参照：https://error-center.aliyun.com/status/product/Dysmsapi?spm=a2c1g.8271268.10000.119.66c2df25L0XSqd 
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        } 
    }
}