package com.xpcomrade.push.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Created by wangzp
 * Date: 2015/11/18 20:44
 * Copyright (c) 2015, xpcomrade@gmail.com All Rights Reserved.
 * Description: 通用工具处理类. <br/>
 */
public class LangUtil {
    static Logger logger = LoggerFactory.getLogger(LangUtil.class);

    /**
     * 布尔解析
     * @param value
     * @return
     */
    public static Boolean parseBoolean(Object value) {
        if (value != null) {
            if (value instanceof Boolean) {
                return (Boolean) value;
            } else if (value instanceof String) {
                return Boolean.valueOf((String) value);
            }
        }
        return null;
    }

    public static boolean parseBoolean(Object value, boolean defaultValue) {
        if (value != null) {
            if (value instanceof Boolean) {
                return (Boolean) value;
            } else if (value instanceof String) {
                try {
                    return Boolean.valueOf((String) value);
                } catch (Exception e) {
                    logger.warn("parse boolean value({}) failed.", value);
                }
            }
        }
        return defaultValue;
    }

    /**
     * Int解析方法，可传入Integer或String值
     * @param value  Integer或String值
     * @return
     */
    public static Integer parseInt(Object value) {
        if (value != null) {
            if (value instanceof Integer) {
                return (Integer) value;
            } else if (value instanceof String) {
                return Integer.valueOf((String) value);
            }
        }
        return null;
    }

    public static Integer parseInt(Object value, Integer defaultValue) {
        if (value != null) {
            if (value instanceof Integer) {
                return (Integer) value;
            } else if (value instanceof String) {
                try {
                    return Integer.valueOf((String) value);
                } catch (NumberFormatException e) {
                    logger.warn("parse Integer value({}) failed.", value);
                }
            }
        }
        return defaultValue;
    }

    /**
     *  获取ASCII编码字符串
     * @param str
     * @return
     */
    public static String getASCIIString(String str) {
        if (str != null) {
            try {
                return URLEncoder.encode(str, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                logger.error(StackTraceUtil.getStackTrace(e));
            }
        }
        return null;
    }

    /**
     * 获取UTF8编码字符串
     * @param str
     * @return
     */
    public static String getUTF8String(String str) {
        if (str != null) {
            try {
                return URLDecoder.decode(str, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                logger.error(StackTraceUtil.getStackTrace(e));
            }
        }
        return null;
    }

    /**
     * long解析方法，可传入Long或String值
     * @param value
     * @return
     */
    public static Long parseLong(Object value) {
        if (value != null) {
            if (value instanceof Long) {
                return (Long) value;
            } else if (value instanceof String) {
                return Long.valueOf((String) value);
            }
        }
        return null;
    }

    public static Long parseLong(Object value, Long defaultValue) {
        if (value != null) {
            if (value instanceof Long) {
                return (Long) value;
            } else if (value instanceof String) {
                try {
                    return Long.valueOf((String) value);
                } catch (NumberFormatException e) {
                    logger.warn("parse Long value({}) failed.", value);
                }
            }
        }
        return defaultValue;
    }

    /**
     * Double解析方法，可传入Double或String值
     * @param value
     * @return
     */
    public static Double parseDouble(Object value) {
        if (value != null) {
            if (value instanceof Double) {
                return (Double) value;
            } else if (value instanceof String) {
                return Double.valueOf((String) value);
            }
        }
        return null;
    }

    /**
     * toString实现，当对象为null时直接返回null
     * @param value
     * @return
     */
    public static String toString(Object value) {
        if (value == null) {
            return null;
        }

        return value.toString();
    }

    /**
     * 验证两个字符串是否相等
     * @param str1
     * @param str2
     * @return
     */
    public static boolean stringEquals(String str1, String str2) {
        if (str1 == null || str2 == null) {
            return false;
        }

        return str1.equals(str2);
    }
}
