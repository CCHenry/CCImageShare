/*******************************************************************************
 * PROPRIETARY/CONFIDENTIAL
 * Copyright (c) 2004-2009 WisageTech 
 *
 * All rights reserved. This medium contains confidential and proprietary
 * source code and other information which is the exclusive property of
 * WisageTech.  None of these materials may be used,
 * disclosed, transcribed, stored in a retrieval system, translated into
 * any other language or other computer language, or transmitted in any form
 * or by any means (electronic, mechanical, photocopied, recorded or
 * otherwise) without the prior written permission of WisageTech.
 *******************************************************************************
 *
 * $Header: /iManage/Betty/mobile/MobileClient/Android/MobileClient/src/com/gearteks/imanage/util/StringUtil.java,v 1.1.2.42 2016/09/12 09:45:22 tobbyye Exp $
 * $Id: StringUtil.java,v 1.1.2.42 2016/09/12 09:45:22 tobbyye Exp $
 * $Author: tobbyye $
 * $Date: 2016/09/12 09:45:22 $
 * $Revision: 1.1.2.42 $
 *
 *******************************************************************************
 */
package com.example.henryzheng.ccimageshare.M.utils;

import android.graphics.Color;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author phoebechen
 */
public class StringUtil {
	
   /**
    * src为空则显示默认值
    * @param src
    * @param alt
    * @return
    */
   public static String nvl(Object src, String alt) {
      if (src == null) {
			return alt;
	  } else {
			return StringUtil.nvl(src.toString(), alt);
	  }
   }
   
   /**
    * src为空则显示默认值
    * @param srcStr
    * @param objStr
    * @return
    */
   public static String nvl(String srcStr, String objStr) {
      if (srcStr == null || 0 == srcStr.trim().length() || "null".equalsIgnoreCase(srcStr.trim())) {
         return objStr;
      } else {
         return srcStr;
      }
   }
	
   /**
    * 判断src是否为空
    * @param srcStr
    * @return
    */
   public static boolean isEmpty(Object srcStr) {
      return nvl(srcStr, "").trim().length() == 0 || nvl(srcStr, "").equals("null");
   }


   public static boolean isEmpty1(String str) {
      if (null == str) {
         return true;
      }
      return "".equals(str.trim());
   }

   public static String trim1(String str) {
      if (null == str) {
         return "";
      }
      return str.trim();
   }

   public static String toFirstLetterUpperCase(String str) {
      if (str == null || str.length() < 2) {
         return str;
      }
      String firstLetter = str.substring(0, 1).toUpperCase();
      return firstLetter + str.substring(1, str.length());
   }

   /**
    * 把 null 字符串 换成 "" 过滤空字符串
    * 
    * @param str
    * @return
    */
   public static String filterNULL(String str) {
      if (str == null || "null".equalsIgnoreCase(str))
         return "";
      return str;
   }
   
   /**
    * 去除空格
    * 
    * @param str
    * @return
    */
   public static String filterBlankSpace(String str){
   	if (str == null || "null".equalsIgnoreCase(str)) {
			return "";
		}
      return str.replace(" ",""); 
   }

   /**
    * 把 null 字符串 换成 "-" 过滤空字符串
    * 
    * @param str
    * @return
    */
   public static String transformNULLToLine(String str) {
      if (str == null || "".equals(str.trim()) || "null".equals(str.trim()))
         return "-";
      return str;
   }

   /**
    * String 转long
    * 
    * @param str
    * @return
    */
   public static Long parseLong(String str) {
      if (isEmpty1(str)) {
         return null;
      } else {
         return Long.parseLong(str);
      }
   }

   public static String toSqlInString(Collection col) {
      if (col == null) {
         return null;
      }

      return toSqlInString(col.toArray());
   }

   public static String toSqlInString(Object[] col) {
      if (null == col) {
         return null;
      }
      int index = 0;
      StringBuilder buf = new StringBuilder();
      Object value = null;
      while (index < col.length) {
         value = col[index];
         if (value == null) {
            continue;
         }
         if (index != 0) {
            buf.append(",");
         }
         buf.append(addQuote(String.valueOf(value)));
         index++;
      }
      return buf.toString();
   }

   public static String addQuote(String value) {
      StringBuilder str = new StringBuilder();

      if (null == value) {
         return null;
      }

      str.append("\'").append(value).append("\'");

      return str.toString();
   }



   /**
    * 如果是中文，返回拼音字母序列
    * 
    * @param str
    * @return
    */


   private static Pattern PATTERN_NAME = Pattern.compile("[^,'\"><\\\\]*");

   private static Pattern PATTERN_NAME1 = Pattern.compile("[^,'\"]*");

   private static Pattern PATTERN_NAME2 = Pattern.compile("[^,\\\\]*");

   private static Pattern PATTERN_NAME3 = Pattern.compile("[^:\\-]*");

   private static Pattern PATTERN_NAME4 = Pattern.compile("[^\\\\]*");

   // private static Pattern PATTERN_FLOAT =
   // Pattern.compile("^-?\\d*\\.\\d*$");
   private static Pattern PATTERN_FLOAT_TWO_FIXEDDIGIT = Pattern.compile("^-?\\d*\\.\\d{2}$");

   // private static Pattern PATTERN_NUMBER =
   // Pattern.compile("^-?\\d*\\.?\\d*$");
   private static Pattern PATTERN_NUMBER_TWO_FIXEDDIGIT = Pattern.compile("^-?\\d*\\.\\d{2}$");

   // private static Pattern PATTERN_PLUSNUMBER =
   // Pattern.compile("^\\d*\\.?\\d*$");
   private static Pattern PATTERN_PLUSNUMBER_TWO_FIXEDDIGIT = Pattern.compile("^\\d*\\.\\d{2}$");
   
   private static Pattern PATTERN_NONEGATIVE_FLOAT_NUMBER = Pattern.compile("^//d+(//.//d+)?$");



   public static String removeComma(String s) {
      if (s == null) {
         return s;
      }

      StringBuilder buff = new StringBuilder();

      int len = s.length();

      for (int i = 0; i < len; i++) {
         if (s.charAt(i) != ',') {
            buff.append(s.charAt(i));
         }
      }

      return buff.toString();
   }

   public static Integer toInteger(Object srcStr, Integer defaultValue) {
      try {
         if (srcStr != null && StringUtil.isInt(srcStr)) {
            String s = srcStr.toString().replaceAll("(\\s)", "");
            return s.length() > 0 ? Integer.valueOf(s) : defaultValue;
         }
         // return Integer.valueOf(srcStr.toString().replaceAll("(\\s)",
         // ""));
      } catch (Exception e) {
         /*
          * if (null == defaultValue) { defaultValue = ZERO; } return
          * defaultValue;
          */
         ;
      }
      return defaultValue;
   }

   public static boolean isInt(Object srcStr) {
      // return null == srcStr || srcStr.equals("null");
      if (srcStr == null) {
         return false;
      }
      String s = srcStr.toString().replaceAll("(\\s)", "");
      Pattern p = Pattern.compile("([-]?[\\d]+)");
      Matcher m = p.matcher(s);
      return m.matches();
      // return nvl(srcStr, "").trim().length() == 0 || nvl(srcStr,
      // "").equals("null");
   }
   
   /**
    * convert a string to integer.
    * 
    * @param str
    * @return a int value or 0 if cant convert.
    * @see #convertStrToInteger(Object, Integer)
    */
   public static Integer convertStrToInteger(String str) {
      return convertStrToInteger(str, 0);
   }

   /**
    * convert a string to integer.
    * <p>
    * null => defaultValue<br/> "abc" => defaultValue<br/> "123" => 123<br/> "
    * 123 " => 123<br/> "1,234,567" => 1234567<br/>
    * </p>
    * 
    * @param str
    * @param defaultValue
    * @return a int value or <code>defaultValue</code> if cant convert.
    */
   public static Integer convertStrToInteger(Object str, Integer defaultValue) {
      if (null == str) {
         return defaultValue;
      }
      try {
         String s = str.toString().replace(",", "");
         return Integer.valueOf(s.trim());
      } catch (Exception e) {
         return defaultValue;
      }
   }
   
   /**
    * 判断是否是非负浮点数
    * @param number
    * @return
    */
   public static boolean isNonnegativeFloatingNumber(String number) {
      if (number == null) {
         return false;
      }
      
      if (PATTERN_NONEGATIVE_FLOAT_NUMBER.matcher(StringUtil.removeComma(number)).matches()) 
         return true;
      
      return false;
   }

   public static int toInt(Object srcStr, int defaultValue) {
      try {
         if (srcStr != null && StringUtil.isInt(srcStr)) {
            String s = srcStr.toString().replaceAll("(\\s)", "");
            return s.length() > 0 ? Integer.parseInt(s) : defaultValue;
         }
      } catch (Exception e) {
         ;
         // Simply skip.
      }
      return defaultValue;
   }

   public static double toDouble(Object srcStr, double defaultValue) {
      try {
         if (srcStr != null) {
            return Double.valueOf(srcStr.toString().replaceAll(",", ""));
         }
      } catch (Exception e) {
         ;
      }
      return defaultValue;
   }


   /**
    * DOCUMENT ME!
    * 
    * @param id
    *           DOCUMENT ME!
    * @param ids
    *           DOCUMENT ME!
    * @return DOCUMENT ME!
    */
   public static boolean isIn(String id, String[] ids) {
      if ((null == id) || (id.trim().equals("")) || (null == ids)) {
         return false;
      }

      boolean flag = false;

      for (int i = 0; i < ids.length; i++) {
         if (ids[i] != null && id.trim().equalsIgnoreCase(ids[i].trim())) {
            flag = true;

            break;
         }
      }

      return flag;
   }

   /**
    * 根据 ":"拆分字符串,并且返回指定位置的String
    * 
    * @param str
    * @return
    */
   public static String splitStrAndReturnByPosition(String str, int position) {
      String[] strs = splitLabelAndValue(str);
      if (strs != null && strs.length > position) {
         return strs[position];
      }
      return "";
   }

   /**
    * 根据 ":"拆分字符串,并且返回指定位置的String
    * 
    * @param str
    * @return
    */
   public static String splitStrAndReturnByPosition(String str, String expr, int position) {
      String[] strs = splitLabelAndValue(str, expr);
      if (strs != null && strs.length > position) {
         return strs[position];
      }
      return "";
   }

   /**
    * 根据 ":"拆分字符串
    * 
    * @param str
    * @return
    */
   public static String[] splitLabelAndValue(String str) {
      return splitLabelAndValue(str, ":");
   }

   /**
    * 根据 任意字符拆分字符串
    * 
    * @param str
    * @return
    */
   public static String[] splitLabelAndValue(String str, String expr) {
      String[] strs = null;
      if (str != null && str.indexOf(expr) > 0) {
         strs = str.split(expr);
      }
      return strs;
   }

   public static String checkId(String str) {
      int check;
      String result = null;
      if (str == null) {
         result = null;
      } else {
         check = str.indexOf("tmp_");
         if (check < 0) {
            result = str;
         } else {
            result = null;
         }
      }
      return result;
   }

   public static String addId2AttachName(String name, String Id) {
      String FileName = null;
      String FileEnd = null;

      int dot = name.lastIndexOf('.');
      if ((dot > -1) && (dot < (name.length()))) {
         FileName = name.substring(0, dot);
      }

      if ((name != null) && (name.length() > 0)) {
         int dots = name.lastIndexOf('.');
         if ((dots > -1) && (dots < (name.length() - 1))) {
            FileEnd = name.substring(dots + 1);
         }
      }
      String returns = FileName + "_" + Id + "." + FileEnd;
      return returns;

   }

   public static String getFileName(String name) {
      String FileName = null;
      int dots = name.lastIndexOf('/');
      if ((dots > -1) && (dots < (name.length() - 1))) {
         FileName = name.substring(dots + 1);
      }
      return FileName;
   }

   /**
    * 清除html代码
    * 
    * @param str
    * @return
    */
   public static String htmlToPlainString(String str) {
      if (str == null) {
         return "";
      }

      String retValue = str;

      retValue = retValue.replaceAll("<br/>", "\n");
      retValue = retValue.replaceAll("&nbsp;", " ");
      retValue = retValue.replaceAll("&nbsp", " ");
      retValue = retValue.replaceAll("&acute;", "`");
      // retValue = retValue.replaceAll("&apos;", "\'");
      retValue = retValue.replaceAll("\\&#39;", "\'");
      retValue = retValue.replaceAll("&quot;", "\"");
      retValue = retValue.replaceAll("&gt;", ">");
      retValue = retValue.replaceAll("&lt;", "<");
      retValue = retValue.replaceAll("&amp;", "&");

      return retValue;
   }

   /**
    * 判断该id是不是本地取的
    * 
    * @param id
    * @return
    */
   public static boolean idIsCreateByLocal(String id) {
      if (id == null) {
         return false;
      }
      return id.indexOf("tmp_") >= 0;
   }

   public static String processMoneyFieldByNull(String value) {
      if (value == null) {
         return null;
      }
      if (value.indexOf(":") > 0) {
         String[] strs = value.split(":");
         if (strs.length > 1 && "null".equals(strs[1])) {
            return strs[0] + ":-";
         }
      }
      return value;
   }





   /**
    * 把数字类型字符串转成BigDecimal类型
    * 
    * @param
    *           str
    * @return BigDecimal
    */
   public static BigDecimal strToDecimal(String str) {
      try {
         if (!StringUtil.isEmpty1(str)) {
            return new BigDecimal(str);
         } else {
            return new BigDecimal(0);
         }
      } catch (Exception e) {
         return new BigDecimal(0);
      }

   }

   /**
    * 把数字类型字符串转成long类型
    * 
    * @param
    *           str
    * @return BigDecimal
    */
   public static long strTolong(String str) {
      try {
         return Long.parseLong(str, 10);
      } catch (Exception e) {
         return 0;
      }
   }

   public static String recode(String str) {
      String formart = "";
      try {
         boolean ISO = Charset.forName("ISO-8859-1").newEncoder().canEncode(str);
         if (ISO) {
            formart = new String(str.getBytes("ISO-8859-1"), "GB2312");
         } else {
            formart = str;
         }
      } catch (UnsupportedEncodingException e) {
         e.printStackTrace();
      }
      return formart;
   }

   public static boolean toBoolean(Object srcStr, boolean defaultValue) {
      try {
         if (srcStr != null) {
            return Boolean.parseBoolean(trim1(srcStr.toString()));
         }
      } catch (Exception e) {
         ;
      }
      return defaultValue;
   }

   public static boolean isEditable(String editable) {
      if ("true".equalsIgnoreCase(editable))
         return true;

      return false;
   }

   /**
    * 判断totalStr是否包含了destStr这个字符串
    * 
    * @param totalStr
    * @param destStr
    * @return
    */
   public static boolean containStr(String totalStr, String destStr) {
      if (totalStr != null)
         return totalStr.indexOf(destStr) >= 0;

      return false;
   }

   public static Object getObjValue(String methodName, Object object) {
      try {
         Class<? extends Object> cls = object.getClass();
         Method fieldMethod = cls.getMethod(methodName);
         if (fieldMethod != null) {
            return fieldMethod.invoke(object);
         }
         return null;
      } catch (Exception e) {
      }
      return null;
   }

   public static void setObjValue(String methodName, Object object, String value) {
      try {
         Class<? extends Object> cls = object.getClass();
         cls.getMethod(methodName, String.class).invoke(object, value);
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
   
   /**
    * 转成字符串
    * @param obj
    * @return
    */
   public static String toString(Object obj) {
      if (null == obj) {
         return "";
      }

      return obj.toString();
   }
   
   /**
    * 转成字符串
    * @param obj
    * @return
    */
   public static String toString(Object obj, String defaultStr) {
      if (null == obj) {
         return defaultStr;
      }

      return obj.toString();
   }
   
   /**
    * 是否是空或者0
    * @param id
    * @return
    */
   public static boolean isNullOrZero(Integer id) {
      return id == null || id == 0;
   }
   
   /**
    * 去掉名字中的颜色标签
    * @param name
    * @return
    */
   public static String getNameWithOutColor(String name) {
	  Object[] objs = getNameAndColor(name);
	  if (objs != null && objs.length == 2) {
		  return StringUtil.toString(objs[0]);
	  }
      return name;
   }
   
   /**
    * 得到名字和名字颜色
    * @param name
    * @return
    */
   public static Object[] getNameAndColor(String name) {
	   Object[] result = null;
	   if (StringUtil.isEmpty1(name)) {
		   return result;
	   }
	   
       Integer color = null;
       if (name.indexOf("<font") != -1) {
           if (name.indexOf("color='red'") != -1 || name.indexOf("color=\"red\"") != -1) {
        	   name = name.substring(name.indexOf(">") + 1, name.indexOf("</font>"));
        	   color = Color.RED;
           }
       }
       return new Object[]{name, color};
   }

   
   public static boolean isEqualsTrim(String oldStr, String newStr) {
      if ((null == oldStr) || (null == newStr)) {
         return false;
      }

      return oldStr.trim().equals(newStr.trim());
   }


   /**
    *  将两个数组拼接成一个数组
    * @param list
    * @param appendList
    * @return
    */
   public static String[] appendArray(String[] list, String[] appendList) {
      if (appendList == null || appendList.length == 0) {
         return list;
      }
      
      String[] result = null;
      if (list == null || list.length == 0) {
         result = appendList;
      } else {
         result = new String[list.length + appendList.length];
         for (int i = 0; i < list.length; i++) {
            result[i] = list[i];
         }
         
         for (int i = 0; i < appendList.length; i++) {
            result[list.length + i] = appendList[i];
         }
      }

      return result;
   }
   
   /**
    *  通过数组获取指定分隔符字符串
    * @return
    */
   public static String getStringWithSplit(String[] list, String split) {
      String str = StringUtil.toString("");
      if (list == null || list.length == 0) {
         return str;
      }
      for (String string : list) {
         if (!StringUtil.isEmpty(string)) {
            str = str + StringUtil.toString(string) + StringUtil.toString(split);
         }
      }
      if (str.endsWith(split) && str.length() > 0) {
         str = str.substring(0, str.length() - 1);
      }
      return str;
   }
}
