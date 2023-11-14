package com.aston.kupriyanov.bankAccountRestApi.util;

import java.util.Random;

public class AccountHelper {

    public static String generateNumber() {
        StringBuilder number = new StringBuilder();
        int countDigitsInAccountNumber = 16;
        Random value = new Random();

        int count = 0;
        int n = 0;
        for (int i = 0; i < countDigitsInAccountNumber; i++) {
            if (count == 4) {
                number.append(" ");
                count = 0;
            } else
                n = value.nextInt(10);
            number.append(n);
            count++;
        }
        return number.toString();
    }
}
