package cn.yufan.infomanager.utils;

public class StringUtils {
	public static boolean isEmpty(String str){
		if(str==null || str.length()==0){
			return true;
		}else{
			return false;
		}
	}

	public static boolean isBlank(String str){
		if(str==null)
			return true;
		else {
			str = str.replaceAll("\\s+","");
			if(str.length()==0)
				return true;
			else
				return false;
		}
	}
}
