package org.ipph.spiderPush.patent.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.ipph.spiderPush.patent.enumeration.PatentTypeEnum;
import org.springframework.util.StringUtils;

public class PatentUtil {
	/**
	 * appNumber="CN201420424716.X";实用新型
	 * appNumber="CN89105613.0";发明专利
	 * appNumber="CN85100613";发明专利
	 * 根据申请号返回查询的数据库
	 * @param appNumber
	 * @return
	 */
	public static String getPatentInterfaceDbName(String appNumber){
		
		if(PatentUtil.isForeignPatent(appNumber)) return null;
		
		String number="";
		String[] zlNumberArr=getZlNumber(appNumber);
		if(null!=zlNumberArr&&!StringUtils.isEmpty(zlNumberArr[0]))
		{
			String zlNumber=zlNumberArr[0];
			Pattern p=Pattern.compile("^(85|86|87|88)(1|2|3|8|9)\\d+");
			Matcher m=p.matcher(zlNumber);
			if(m.matches()){
				number=zlNumber.substring(2,3);
			}
			else if(zlNumber.length()==8){
				p=Pattern.compile("^(89|90|91|92|93|94|95|96|97|98|99|00|01|02|03)(1|2|3|8|9)\\d+");
				m=p.matcher(zlNumber);
				if(m.matches()){
					number=zlNumber.substring(2,3);
				}
			}
			else{
				number=zlNumber.substring(4,5);
			}
		}
			
		if(number.equals("1")||number.equals("8")){
			return PatentTypeEnum.FMZL.name();
		}
		else if(number.equals("2")||number.equals("9")){
			return PatentTypeEnum.SYXX.name();
		}
		else if(number.equals("3")){
			return PatentTypeEnum.WGZL.name();
		}
		else{
			return null;
		}
	}
	/**
	 * 判断是否是合法的申请号
	 * @param appNumber
	 * @return
	 */
	public static boolean isValidAppNumber(String appNumber){
		String[] zlNumber=getZlNumber(appNumber);
		if(null!=zlNumber&&!StringUtils.isEmpty(zlNumber[0])){
			return validation(zlNumber[0],zlNumber[1]);
		}
		return false;
	}
	/**
	 * 获取申请号对应的专利
	 * @param appNumber
	 * @return
	 */
	private static String[] getZlNumber(String appNumber){
		String[] zlNumber=new String[2];//第1个位置存放专利号，第二个wiz存放校验位
		if(!StringUtils.isEmpty(appNumber)){
			appNumber=appNumber.trim();
			Pattern p=Pattern.compile("^([a-zA-Z]{2})?+\\d{8}(\\d{4})?+(\\.[\\d|X|x])?+");
			Matcher m=p.matcher(appNumber);
			if(m.matches()){
				p=Pattern.compile("^[a-zA-Z]{2}(\\d+?)(\\.[\\d|X|x])?");
				m=p.matcher(appNumber);
				if(m.matches()){
					zlNumber[0]=m.group(1);
					if(appNumber.indexOf(".")!=-1){
						zlNumber[1]=appNumber.substring(appNumber.lastIndexOf(".")+1);
					}
				}
				else if(appNumber.indexOf(".")!=-1){
					zlNumber[0]=appNumber.substring(0,appNumber.lastIndexOf("."));
					zlNumber[1]=appNumber.substring(appNumber.lastIndexOf(".")+1);
				}
				else{
					zlNumber[0]=appNumber;
				}
			}
		}
		return zlNumber;
	}
	/**
	 * 根据校验位校验专利是否正确
	 * @param zlNumber
	 * @param validation
	 * @return
	 */
	private static boolean validation(String zlNumber,String validation){
		Pattern p=Pattern.compile("^(85|86|87|88)(1|2|3|8|9)\\d+");
		Matcher m=p.matcher(zlNumber);
		if("".equals(validation)){
			if(m.matches())
				return true;
		}
		if(zlNumber.length()==8){
			p=Pattern.compile("^(89|90|91|92|93|94|95|96|97|98|99|00|01|02|03)(1|2|3|8|9)\\d+");
			m=p.matcher(zlNumber);
			if(!m.matches())
				return false;
		}
		int[] numbers=new int[zlNumber.length()];
		int sum=0;
		for(int i=0, mul=2;i<numbers.length;i++,mul++){
			if(mul>9) mul=2;
			sum+=Integer.parseInt(zlNumber.substring(i,i+1))*mul;
		}
		int flag=sum%11;
		if(flag==10&&validation.equalsIgnoreCase("x")){
			return true;
		}
		else{
			return validation.equals(flag+"");
		}
	}
	
	/**
	 * 判断是否以字母开头
	 * @param str
	 */
	private static boolean isStartWithCharacter(String str){
		Pattern p=Pattern.compile("^[A-Za-z]+.*");
		Matcher m=p.matcher(str);
		if(m.matches()){
			return true;
		}
		return false;
	}
	/**
	 * 判断是否是国外专利
	 * @param appNumber
	 * @return
	 */
	private static boolean isForeignPatent(String appNumber){
		if(null!=appNumber&&!"".equals(appNumber)){
			if("CN".equalsIgnoreCase(getCountryEn(appNumber))){
				return false;
			}
		}
		return true;
	}
	/**
	 * 根据公开号获取类型
	 * @param pubNumber
	 * @return
	 */
	public static String[] getPubTypeByPubNo(String pubNumber){
		if(null!=pubNumber&&!"".equals(pubNumber)){
			String[] pub=new String[3];
			Pattern p=Pattern.compile("^([a-zA-Z]{2})(\\w+)\\((\\w+)\\)$");//类型使用括号括起来
			Matcher m=p.matcher(pubNumber);
			if(m.matches()){
				for(int c=1;c<=m.groupCount();c++){
					pub[c-1]=m.group(c);
				}
				return pub;
			}
			p=Pattern.compile("^([a-zA-Z]{2})(\\w+)([a-zA-Z]{1})$");//末尾为字母
			m=p.matcher(pubNumber);
			if(m.matches()){
				for(int c=1;c<=m.groupCount();c++){
					pub[c-1]=m.group(c);
				}
				return pub;
			}
			p=Pattern.compile("^([a-zA-Z]{2})(\\w+)([a-zA-Z]{1}\\d{1})$");//末尾为字母+数字
			m=p.matcher(pubNumber);
			if(m.matches()){
				for(int c=1;c<=m.groupCount();c++){
					pub[c-1]=m.group(c);
				}
				return pub;
			}
		}
		return null;
	}
	/**
	 * 获取申请号中国家电前两位字符
	 * @param appNumber
	 * @return
	 */
	public static String getCountryEn(String appNumber){
		if(isStartWithCharacter(appNumber)){
			Pattern p=Pattern.compile("^([a-zA-Z]{2})[\\w.]+");
			Matcher m=p.matcher(appNumber);
			if(m.matches()){
				return m.group(1);
			}
		}
		return "";
	}
}
