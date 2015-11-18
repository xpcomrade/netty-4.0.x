package com.xpcomrade.push.util;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by wangzp
 * Date: 2015/11/18 20:45
 * Copyright (c) 2015, xpcomrade@gmail.com All Rights Reserved.
 * Description: StackTrace辅助类. <br/>
 */
public class StackTraceUtil {
    /**
     * 取出exception中的信息
     *
     * @param exception
     * @return
     */
    public static String getStackTrace(Throwable exception) {
        StringWriter sw = null;
        PrintWriter pw = null;
        try {
            sw = new StringWriter();
            pw = new PrintWriter(sw);
            exception.printStackTrace(pw);
            return sw.toString();
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
    }

    public static Throwable findException(Throwable throwable, Class<?>... exClassArray) {
        Throwable t = throwable;
        int i = 0;

        while (t != null && (++i) < 10) {
            for (Class<?> exClass : exClassArray) {
                if (exClass.isInstance(t)) {
                    return t;
                }
                t = t.getCause();
            }
        }

        return null;
    }
}
