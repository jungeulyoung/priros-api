package mybatis;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

public class Utils {

@SuppressWarnings("rawtypes")
	public static boolean isEmpty(Object obj) throws IllegalArgumentException {
		if (obj == null) return true;
		if (obj instanceof String && ((String) obj).length() == 0) return true;
		else if (obj instanceof Collection && ((Collection) obj).isEmpty()) return true;
		else if (obj.getClass().isArray() && Array.getLength(obj) == 0) return true;
		else if (obj instanceof Map && ((Map) obj).isEmpty()) return true;
		else return false;
	}

	public static boolean isNotEmpty(Object obj) {
		return !isEmpty(obj);
	}

	public static boolean isNotEmpty(Object... args) {
		for (Object object : args) {
			if (isEmpty(object)) return false;			
		}
		return true;
	}

	public static boolean isNotEmptyContain(Object... args) {
		for (Object object : args) {
			if (!isEmpty(object)) return true;
		}
		return false;
	}


	public static boolean isNull(Object obj) throws IllegalArgumentException {
		if (obj == null) return true;
		else return false;
	}

	public static boolean isNotNull(Object obj) {
		return !isNull(obj);
	}
	
	public static boolean isContain(String text, String s) {
		try {
			return text.contains(s);
		} catch (Exception e) {
			return false;
		}		
	}    
    
}
