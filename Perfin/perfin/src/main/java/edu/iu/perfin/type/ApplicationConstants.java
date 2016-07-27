package edu.iu.perfin.type;

import java.math.RoundingMode;
import java.util.Locale;

/**
 * Sabit olarak kullanan değişkenler burada tanımlanarak kullanılacak.
 */

public class ApplicationConstants {
	
	public static String				companyName										= "İstanbul Kültür Üniversitesi";
	public static String				subCompanyName									= "saideisilay";
	public static String				copyright										= "Tüm hakları saklıdır. © 2016";
	
	public static Locale				currentLocale										= new Locale("tr");
	public static Locale				turkishLocale										= new Locale("tr");
	public static String				currencyPattern										= "#,##0.00000";
	public static String				currencySimplePattern								= "#,##0.00";
	
	public static final RoundingMode	BigDecimalRoundingMode								= RoundingMode.HALF_UP;
	public static final int				BigDecimalScale										= 3;
	public static final int				BigDecimalScaleForReport							= 2;
	public static final int				BigDecimalScaleForYaklasikMaaliyet					= 5;
	public static final int				BigDecimalTeklifScale								= 5;
	public static final int				BigDecimalTeklifPresicion							= 20;
	public static final int				SQLFetchMaxResult									= 25;
	
	public static final String			SQL_TIMESTAMP_FORMAT								= "YYYY-MM-DD HH24:MI:SS.SSSSS";									// Oracle TO_DATE ('2013-06-02 12:34:56.0', 'YYYY-MM-DD HH24:MI:SS.SSSSS')
	public static final String			SQL_ORACLE_TIMESTAMP_FORMAT							= "YYYY-MM-DD HH24:MI:SS"; 			// Oracle TO_DATE ('2013-06-02 12:34:56.0', 'YYYY-MM-DD HH24:MI:SS.FF9')
	public static final String			SQL_ORACLE_TURKISH_FORMAT							= "DD.MM.YYYY HH24:MI:SS"; 			// Oracle TO_DATE ('2013-06-02 12:34:56.0', 'YYYY-MM-DD HH24:MI:SS.FF9')
	public static final String			ORACLE_TIME_FORMAT_HOUR_MINUTES						= "HH24:MI";// Format formatter = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss.S");
	public static final String			ORACLE_TIME_FORMAT_HOUR_MINUTE_SECONDS				= "HH24:MI:SS";// Format formatter = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss.S");
	public static final String			SQL_JAVA_TIMESTAMP_FORMAT							= "yyyy-MM-dd HH:mm:ss.S";											// Format formatter = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss.S");
	public static final String			REPORT_DATE_FORMAT									= "dd.MM.yyyy";													//tarih formatı için kullanıldı
	public static final String			DATE_FORMAT											= "yyyyMMdd";														//tarih formatı için kullanıldı
	public static final String			SIMPLE_DATE_FORMAT									= "yyyy_MM_dd-HH_mm_ss";											//tarih formatı için
	public static final String			LONG_DATE_FORMAT								= "dd/MMMM/yyyy HH:mm";// Format formatter = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss.S");
	public static final String			DATE_FORMAT_2				= "dd.MM.yyyy HH:mm";
	public static final String			FULL_TIME_FORMAT								= "HH:mm:ss.S";// Format formatter = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss.S");
	
	public final static int DEFAULT_PAGE_NUMBER = 1 ;
	
	public final static int DEFAULT_PAGE_ROW_COUNT = 20 ;
	
}
