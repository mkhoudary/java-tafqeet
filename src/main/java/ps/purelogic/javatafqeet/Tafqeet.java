/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ps.purelogic.javatafqeet;

import java.math.BigDecimal;
import java.util.*;

public class Tafqeet {

    private final static Map<Integer, String> NUMBERS_MAP = new HashMap<>();
    private final static Map<Integer, String[]> MULTIPLIERS_MAP = new HashMap<>();

    static {
        NUMBERS_MAP.put(0, "");
        NUMBERS_MAP.put(1, "واحد");
        NUMBERS_MAP.put(2, "اثنان");
        NUMBERS_MAP.put(3, "ثلاثة");
        NUMBERS_MAP.put(4, "اربعة");
        NUMBERS_MAP.put(5, "خمسة");
        NUMBERS_MAP.put(6, "ستة");
        NUMBERS_MAP.put(7, "سبعة");
        NUMBERS_MAP.put(8, "ثمانية");
        NUMBERS_MAP.put(9, "تسعة");
        NUMBERS_MAP.put(10, "عشر");
        NUMBERS_MAP.put(11, "احدى عشر");
        NUMBERS_MAP.put(12, "اثنى عشر");
        NUMBERS_MAP.put(13, "ثلاثة عشر");
        NUMBERS_MAP.put(14, "اربعة عشر");
        NUMBERS_MAP.put(15, "خمسة عشر");
        NUMBERS_MAP.put(16, "ستة عشر");
        NUMBERS_MAP.put(17, "سبعة عشر");
        NUMBERS_MAP.put(18, "ثمانية عشر");
        NUMBERS_MAP.put(19, "تسعة عشر");
        NUMBERS_MAP.put(20, "عشرون");
        NUMBERS_MAP.put(30, "ثلاثون");
        NUMBERS_MAP.put(40, "اربعون");
        NUMBERS_MAP.put(50, "خمسون");
        NUMBERS_MAP.put(60, "ستون");
        NUMBERS_MAP.put(70, "سبعون");
        NUMBERS_MAP.put(80, "ثمانون");
        NUMBERS_MAP.put(90, "تسعون");
        NUMBERS_MAP.put(100, "مئة");
        NUMBERS_MAP.put(200, "مئتان");
        NUMBERS_MAP.put(300, "ثلاثمائة");
        NUMBERS_MAP.put(400, "أربعمائة");
        NUMBERS_MAP.put(500, "خمسمائة");
        NUMBERS_MAP.put(600, "ستمائة");
        NUMBERS_MAP.put(700, "سبعمائة");
        NUMBERS_MAP.put(800, "ثمانمائة");
        NUMBERS_MAP.put(900, "تسعمائة");

        MULTIPLIERS_MAP.put(3, array("ألف", "ألفان", "آلاف"));
        MULTIPLIERS_MAP.put(6, array("مليون", "مليونان", "ملايين"));
        MULTIPLIERS_MAP.put(9, array("بليون", "بليونان", "بلايين"));
        MULTIPLIERS_MAP.put(12, array("ترليون", "ترليونان", "ترليونات"));
    }

    private static String[] array(String... data) {
        return data;
    }

    public static String doTafqeet(BigDecimal number) {
        if (number.floatValue() <= 0) {
            return "صفر";
        }

        StringBuilder numberToReturn = new StringBuilder();

        String[] splittedNumber = number.toString().split("\\.");

        String beforeDotNumber = splittedNumber[0];
        String remainderNumber = splittedNumber.length > 1 ? splittedNumber[1] : "0";

        convertNumberToWords(beforeDotNumber, numberToReturn);

        numberToReturn.append(" شيكل");

        int remainder = Integer.parseInt(remainderNumber);

        if (remainder > 0) {
            if (remainder == 1) {
                numberToReturn.append(" و ");

                numberToReturn.append("أغورة");
            } else if (remainder == 2) {
                numberToReturn.append(" و ");

                numberToReturn.append("أغورتان");
            } else if (remainder > 2 && remainder < 11) {
                convertNumberToWords(remainderNumber, numberToReturn);
                numberToReturn.append(" أغورات");
            } else if (remainder >= 11) {
                convertNumberToWords(remainderNumber, numberToReturn);
                numberToReturn.append(" أغورة");
            }
        }

        return numberToReturn.toString();
    }

    public static void convertNumberToWords(String numberToConvert, StringBuilder numberToReturn) {
        while (numberToConvert.length() % 3 != 0) {
            numberToConvert = "0" + numberToConvert;
        }

        while (numberToConvert.length() > 0 && Integer.parseInt(numberToConvert) != 0) {
            String threeNumbers = numberToConvert.substring(0, 3);
            numberToConvert = numberToConvert.substring(3);

            int multiplier = numberToConvert.length();

            int left = Integer.parseInt(threeNumbers.substring(0, 1));
            int mid = Integer.parseInt(threeNumbers.substring(1, 2));
            int right = Integer.parseInt(threeNumbers.substring(2, 3));

            StringBuilder currentThreeBuilder = new StringBuilder();

            if (left > 0) {
                currentThreeBuilder.append(NUMBERS_MAP.get(left * 100));
            }

            int rightCombined = right + (mid * 10);

            if (rightCombined > 0) {

                if (multiplier > 0 && rightCombined == 1) {
                    currentThreeBuilder.append(MULTIPLIERS_MAP.get(multiplier)[0]);
                } else if (multiplier > 0 && rightCombined == 2) {
                    currentThreeBuilder.append(MULTIPLIERS_MAP.get(multiplier)[1]);
                } else if (rightCombined > 20) {
                    if (left > 0) {
                        currentThreeBuilder.append(" و ");
                    }

                    if (right > 0) {
                        currentThreeBuilder.append(NUMBERS_MAP.get(right));
                        currentThreeBuilder.append(" و ");
                    }

                    currentThreeBuilder.append(NUMBERS_MAP.get(mid * 10));
                } else if (rightCombined <= 20) {
                    currentThreeBuilder.append(NUMBERS_MAP.get(rightCombined));
                }
            }

            if (multiplier > 0 && rightCombined > 2 && rightCombined < 11) {
                currentThreeBuilder.append(" ");
                currentThreeBuilder.append(MULTIPLIERS_MAP.get(multiplier)[2]);
            } else if (multiplier > 0 && rightCombined >= 11) {
                currentThreeBuilder.append(" ");
                currentThreeBuilder.append(MULTIPLIERS_MAP.get(multiplier)[0]);
            }

            if (numberToReturn.length() > 0) {
                numberToReturn.append(" و ");
            }

            numberToReturn.append(currentThreeBuilder.toString());
        }
    }
}