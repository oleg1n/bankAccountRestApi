package com.aston.kupriyanov.bankAccountRestApi.util;

public class LogHelper {
    private static final String CLASS_DELIMITER = "###";
    private static final String METHOD_DELIMITER = "@";

    public static String getLogString(Class<?> clazz, String message) {
        return CLASS_DELIMITER +
                clazz.getName() +
                CLASS_DELIMITER +
                message +
                METHOD_DELIMITER +
                new Throwable().getStackTrace()[1].getMethodName();
    }
}