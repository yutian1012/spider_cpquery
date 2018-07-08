package org.ipph.spiderPush.patent.util;

import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;

public class PatentCipherUtil {
	private final static String keyParamName="keyStr";
	private final static String passParamName="valueStr";
	private final static String key="com.dddddd.dddddd";
	
	/**
	 * 获取加密参数字符串
	 * @return
	 */
	public static String getEncodeParamStr(boolean preSplit){
		StringBuilder sb=new StringBuilder();
		Map<String,String> encodeParam=getEncodeParam();
		if(encodeParam.size()>0){
			for(String key:encodeParam.keySet()){
				sb.append("&").append(key).append("=").append(encodeParam.get(key));
			}
			if(preSplit){
				return sb.substring(1);
			}
		}
		return sb.toString();
	}
	/**
	 * 获取加密参数集合对象
	 * @return
	 */
	public static Map<String,String> getEncodeParam(){
		Map<String,String> param=new HashMap<String,String>();
		long t=System.currentTimeMillis();
		param.put(PatentCipherUtil.keyParamName, t+"");
		param.put(PatentCipherUtil.passParamName, md5Encode(t+PatentCipherUtil.key));
		return param;
	}

    /**
     * <p>MD5加密 生成32位md5码</p>
     * 
     * @param 待加密字符串
     * @return 返回32位md5码
     */
    private static String md5Encode(String inStr) {
        MessageDigest md5 = null;
        byte[] byteArray = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byteArray = inStr.getBytes("UTF-8");
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }

        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }
}
