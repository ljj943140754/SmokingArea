package com.smokeroom.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.sql.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;
import com.common.ano.Permission;
import com.common.bean.ResultData;
import com.common.controller.BaseController;
import com.common.enu.Role;

//import org.junit.Assert;
import net.sf.json.JSONObject;

@RestController
@RequestMapping("/upload")
public class UploadController extends BaseController {
	 
	private static final long serialVersionUID = 5522372203700422672L;
	
	 //@Permission(role = {Role.ADMIN,Role.USER,Role.WORKER})
	@GetMapping("policy.action")
	public ResultData doGet(HttpServletRequest request, HttpServletResponse response){
		response.setHeader("Access-Control-Allow-Origin", "*");
		
		System.err.println("进来了。。。");
		String accessId = "LTAI4FhR1cQLXY2vQRRhkXL8"; // 请填写您的AccessKeyId。
		String accessKey = "T0HeCFIuqy6v3zszkY5BUOYaolRV5E"; // 请填写您的AccessKeySecret。
		String endpoint = "oss-cn-beijing.aliyuncs.com"; // 请填写您的 endpoint。
		String bucket = "smokeroom"; // 请填写您的 bucketname 。
		String host = "https://" + bucket + "." + endpoint; // host的格式为 bucketname.endpoint
		// callbackUrl为 上传回调服务器的URL，请将下面的IP和Port配置为您自己的真实信息。
		//String callbackUrl = "http://localhost:8080";
		String dir = "image"; // 用户上传文件时指定的前缀。

		OSSClient client = new OSSClient(endpoint, accessId, accessKey);
		try {
			long expireTime = 100;
			long expireEndTime = System.currentTimeMillis() + expireTime * 1000;
			Date expiration = new Date(expireEndTime);
			PolicyConditions policyConds = new PolicyConditions();
			policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, 1048576000);
			policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir);

			String postPolicy = client.generatePostPolicy(expiration, policyConds);
			byte[] binaryData = postPolicy.getBytes("utf-8");
			String encodedPolicy = BinaryUtil.toBase64String(binaryData);
			String postSignature = client.calculatePostSignature(postPolicy);

			Map<String, String> respMap = new LinkedHashMap<String, String>();
			respMap.put("accessid", accessId);
			respMap.put("policy", encodedPolicy);
			respMap.put("signature", postSignature);
			respMap.put("dir", dir);
			respMap.put("host", host);
			respMap.put("expire", String.valueOf(expireEndTime / 1000));
			 
			response.setHeader("Access-Control-Allow-Origin", "*");
			response.setHeader("Access-Control-Allow-Methods", "GET, POST");
			 
			return ResultData.success().setData( respMap );
		} catch (Exception e) {
		  
		}
		return null;
	}
	
//	@GetMapping("policy.action")
//	public  String doGet(String name,HttpServletRequest request, HttpServletResponse response){
//		response.setHeader("Access-Control-Allow-Origin", "*");
//		System.err.println("进来了。。。"+name);
//		
//		return "OK";
//	}
 
}
