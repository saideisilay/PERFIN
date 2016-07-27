package edu.iu.perfin.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class CollectionUtils {
		
	/**
	 * 
	 * @param key the variable must have a getter.
	 * @param modelList
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static HashMap addToListIfNotExists(HashMap<Object, List> hashMap, Object key, Object value) {
		
		if (hashMap.containsKey(key)) {
			List list = hashMap.get(key);
			list.add(value);
		} else {
			List newList = new ArrayList();
			newList.add(value);
			
			hashMap.put(key, newList);
		}
		
		return hashMap;
	}
	
	public static <T> List<T> convertToList(T[] arr) {
		List<T> list = new ArrayList<T>();
		if(arr == null) return list;
		for(T t: arr) list.add(t);
		return list;
	}

	public static boolean inList(String str, String ... a ) {
		if(str == null) return false;
		for (int i = 0; i < a.length; i++) {
			String currentString = a[i];
			
			if (str.equalsIgnoreCase(currentString)) {
				return true;
			}
		}
		return false;

	}
	
	public static boolean notInList(String str, String ... a ) {
		if(str == null) return true;
		return ! inList(str, a);
	}
	
	public static List<Long> convertBigDecimalToLong(List<BigDecimal> bigDecimalList ) {
		List<Long> longList = new ArrayList<Long>(bigDecimalList.size());
		
		for (BigDecimal bigDecimal : bigDecimalList) {
			longList.add(bigDecimal.longValue());
		}
		return longList;
	}
	


	/**
	 * Parametre adedince Long listesi doner.
	 * */
	public static List<Long> generateLongList(int size) {
		List<Long> longList = new ArrayList<Long>();
		
		for (int i = 0; i < size; i++) {
			longList.add( new Long(i) );
		}
		
		return longList;
	}
	
	public static Object[] getIfArray(Object object) {
		Object[] arr = null;
		try {
			arr = (Object[]) object;
		} catch (Exception e) {}
		return arr;
	}
	
	public static Collection getIfCollection(Object object) {
		Collection col = null;
		try {
			col = (Collection) object;
		} catch (Exception e) {}
		return col;
	}

	public static boolean isNotEmpty(List<?> findList) {
		return org.apache.commons.collections4.CollectionUtils.isNotEmpty(findList);
	}

}
