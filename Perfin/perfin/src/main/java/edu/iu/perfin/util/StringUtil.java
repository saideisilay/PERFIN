package edu.iu.perfin.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import javax.xml.bind.DatatypeConverter;

import edu.iu.perfin.type.ApplicationConstants;

public class StringUtil extends org.apache.commons.lang3.StringUtils {
	
	private static Random	random;
	private static String	sid;
	
	static {
		random = new Random(1000);
		try {
			sid = InetAddress.getLocalHost().toString();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
	}
	
	public static String getStackTrace(Throwable throwable) {
		if(throwable == null) return "";
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw, true);
        throwable.printStackTrace(pw);
        return sw.getBuffer().toString();
    }
	
	public static String getRandomGUID() {
		MessageDigest md5 = null;
		StringBuffer sbValueBeforeMD5 = new StringBuffer();
		
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			System.out.println("Error: " + e);
		}
		
		try {
			long time = System.currentTimeMillis();
			long rand = 0;
			
			rand = random.nextLong();
			
			sbValueBeforeMD5.append(sid);
			sbValueBeforeMD5.append(":");
			sbValueBeforeMD5.append(Long.toString(time));
			sbValueBeforeMD5.append(":");
			sbValueBeforeMD5.append(Long.toString(rand));
			
			String valueBeforeMD5 = sbValueBeforeMD5.toString();
			md5.update(valueBeforeMD5.getBytes());
			
			byte[] array = md5.digest();
			StringBuffer sb = new StringBuffer();
			for (byte element : array) {
				int b = element & 0xFF;
				if (b < 0x10) {
					sb.append('0');
				}
				sb.append(Integer.toHexString(b));
			}
			
			return sb.toString();
			
		} catch (Exception e) {
			System.out.println("Error:" + e);
		}
		return null;
	}
	
	/**
	 * Verilen bean in adini doner
	 * 
	 * @param clazz
	 * @return sinif adi
	 */
	public static String getBeanName(Class<?> clazz) {
		String name = clazz.getSimpleName();
		return name.replaceFirst(".", name.substring(0, 1).toLowerCase(new Locale("en_EN")));
	}
	
	/**
	 * Bir cumlede gecen belirli bir kelime/harf adedini doner 
	 * 
	 * @param search - aranacak kelime/harf
	 * @param sentence - icinde arama yapilacak olan cumle
	 * @return aranan kelime/harf in cumle icinde gecis adedi
	 */
	public static int findCount(String search, String sentence) {
		int index = 0;
		int start = 0;
		int count = 0;
		
		while ((index = sentence.indexOf(search, start)) > 0) {
			count++;
			start += index + 1;
		}
		
		return count;
	}
	
	/**
	 * Bir textin bos veya null olup olmadigi 
	 * check edilir. 
	 * 
	 * @param text - check edilecek kelime
	 * @return boolean : bossa true doner.
	 */
	public static boolean isEmpty(String text) {
		if (text != null) {
			text.replaceAll(" ", "");
			if (!"".equals(text)) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Bir textin null olup olmadigi 
	 * check edilir.ve null ise boş değer dönülür 
	 * 
	 * @param text - check edilecek kelime
	 * @return boolean : bossa true doner.
	 */
	public static String objectIsNull(Object obj) {
		if (obj != null) {
			String text = obj.toString();
			return text;
		}
		return "";
	}
	
	public static String getMD5sum(String input) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] messageDigest = md.digest(input.getBytes());
			BigInteger sayi = new BigInteger(1, messageDigest);
			String hash = sayi.toString(16);
			while (hash.length() < 32) {
				hash = "0" + hash;
			}
			return hash;
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * string olarak verilen sayıyı long değerine çevirir boşsa 0 sıfır döner
	 * */
	public static Long convertStringToLong(String sayi) {
		Long deger =0l;
		if (isNotEmpty(sayi))  deger= Long.parseLong(sayi);		
		return deger;
		
	}
	
	public static String convertDateToString(Date date, String targetPattern) {
		if (date == null || StringUtil.isEmpty(targetPattern)) {
			return "";
		}
		try {
			Format formatter = new SimpleDateFormat(targetPattern);
			return formatter.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public static Date convertStringToDate(String strDate, String sourcePattern) {
		if (StringUtil.isEmpty(strDate) || StringUtil.isEmpty(sourcePattern)) {
			return null;
		}
		try {
			return new SimpleDateFormat(sourcePattern).parse(strDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Date convertStringToDate(String strDate, String sourcePattern , String returnPattern) {
		if (StringUtil.isEmpty(strDate) || StringUtil.isEmpty(sourcePattern) ||  StringUtil.isEmpty(returnPattern)) {
			return null;
		}
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(sourcePattern, ApplicationConstants.turkishLocale);
			Date parsedDate = sdf.parse(strDate);
			SimpleDateFormat print = new SimpleDateFormat(returnPattern);
			Date returnDate=convertStringToDate(print.format(parsedDate), returnPattern);
			
			return returnDate ;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String toString (Object str) {
		if (str==null) {
			return "";
		}
		return str.toString();
	}
	
	/**
	 * iso formatindaki tarih stringini dd/MM/yyyy formatina cevirip doner
	 * hata alirsa null doner
	 * */
	public static String getDateStringFromISODate(String isoDate) {
		try {
			Calendar cal = DatatypeConverter.parseDateTime(isoDate);
			if (cal != null) {
				return convertDateToString(cal.getTime(), "dd/MM/yyyy");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	public static String convertStringToString(String strDate, String sourcePattern, String targetPattern) {
		Date date = convertStringToDate(strDate, sourcePattern);
		if (date != null) {
			return convertDateToString(date, targetPattern);
		} else {
			return "";
		}
	}
	
	
	
	/**
	 * stringin ilk limit kadar lık bolumunu doner
	 * 
	 * */
	public static String getString(String base, int limit) {
		if(base != null && base.length() > limit) return base.substring(0, limit-1);
		return base;
	}
	
	/**
	 * Verilen stringi tamamini kucuk karakterlere cevirir.
	 * Bunu yaparken turkce locale kullanir
	 * */
	public static String toLowerCase(String word) {
		if(isBlank(word)) return "";
		return word.toLowerCase(new Locale("tr"));
	}

	/**
	 * Verilen stringi tamamini kucuk karakterlere cevirir.
	 * Bunu yaparken turkce locale kullanir
	 * */
	public static String toUpperCase(String word) {
		if(isBlank(word)) return "";
		return word.toUpperCase(new Locale("tr")).trim();
	}
	
	

	public static String replace(String str, int index, char replace){     
		if(str == null) return str;
		else if(index < 0 || index >= str.length()) return str;
		char[] chars = str.toCharArray();
		chars[index] = replace;
		return String.valueOf(chars);       
	}

	public static List<Long> convertToLongList(List<String> list) {
		List<Long> newList = new ArrayList<Long>(list.size());
		for (String value : list) if(isNumeric(value)) newList.add(Long.valueOf(value));
		return newList;
	}

	public static List<Integer> convertToIntegerList(List<String> list) {
		List<Integer> newList = new ArrayList<Integer>(list.size());
		for (String value : list) if(isNumeric(value)) newList.add(Integer.valueOf(value));
		return newList;
	}

	public static String removeLastCharacter(String str) {
		if (str.length() > 0) return str.substring(0, str.length()-1);
		return str;
	}
	
	public static boolean getBoolean(String value) {
		if(isBlank(value)) return false;
		if("0".equals(value) || value.equalsIgnoreCase("false") || value.equalsIgnoreCase("no")) return false;
		if("1".equals(value) || value.equalsIgnoreCase("true") || value.equalsIgnoreCase("yes")) return true;
		return false;
	}
	
	public static boolean getBoolean(String value, boolean defult) {
		if(isBlank(value)) return defult;
		value = StringUtil.trim(value);
		if("0".equals(value) || value.equalsIgnoreCase("false") || value.equalsIgnoreCase("no")) return false;
		if("1".equals(value) || value.equalsIgnoreCase("true") || value.equalsIgnoreCase("yes")) return true;
		return defult;
	}
	
	public static boolean contains(String base, String... search) {
		for (String string : search) {
			boolean res = contains(base, string);
			if(res) return true;
		}
		return false;
	}
	
	public static boolean equalsAny(String base, String... search) {
		for (String string : search) {
			boolean res = equals(base, string);
			if(res) return true;
		}
		return false;
	}
	
	public static boolean endsWith(String base, String... search) {
		for (String string : search) {
			boolean res = base.endsWith(string);
			if(res) return true;
		}
		return false;
	}
	
	public static List<String> getListFromSeperatedString(String commaSeperated, String seperator) {
		if (isEmpty(commaSeperated)) {
			return new ArrayList<String>();
		}
		return Arrays.asList(commaSeperated.trim().split("\\s*" + seperator + "\\s*"));
	}
	
}
