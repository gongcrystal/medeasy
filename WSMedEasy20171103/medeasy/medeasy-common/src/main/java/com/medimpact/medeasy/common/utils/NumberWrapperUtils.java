package com.medimpact.medeasy.common.utils;
/** 
* @author Crystal E-mail: 
* @version 创建时间：2017年9月15日 
* 类说明 
*/
public class NumberWrapperUtils {

    public static boolean greaterThanZero(Long l) {
        return l != null && l > 0L;
    }

    public static boolean lessThanZero(Long l) {
        return !greaterThanZero(l);
    }

    public static boolean greaterThanZero(Integer l) {
        return l != null && l > 0L;
    }

    public static boolean lessThanZero(Integer l) {
        return !greaterThanZero(l);
    }


}
