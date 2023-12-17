package util;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Provides util method to execute calculation with arabic or roman numbers
 */
public final class CalculatorUtils {
    private static final String PLUS = "+";
    private static final String MINUS = "-";
    private static final String DIVISION = "/";
    private static final String MULTIPLICATION = "*";
    private static final Map<Integer, String> ROMAN_UTIL = new LinkedHashMap<>();
    private static final List<String> ROMAN_NUMS = List.of("I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X");

    static {
        ROMAN_UTIL.put(100, "C");
        ROMAN_UTIL.put(90, "XC");
        ROMAN_UTIL.put(50, "L");
        ROMAN_UTIL.put(40, "XL");
        ROMAN_UTIL.put(10, "X");
        ROMAN_UTIL.put(9, "IX");
        ROMAN_UTIL.put(5, "V");
        ROMAN_UTIL.put(4, "IV");
        ROMAN_UTIL.put(1, "I");
    }


    public static String doCalculation(String numFirst, String numSecond, String operand) {
        boolean isRoman = false;
        int result;
        int operandFirst;
        int operandSecond;
        if (isRoman(numFirst) && isRoman(numSecond)) {
            operandFirst = getArabicFromRoman(numFirst);
            operandSecond = getArabicFromRoman(numSecond);
            isRoman = true;
        } else {
            operandFirst = Integer.parseInt(numFirst);
            operandSecond = Integer.parseInt(numSecond);
        }
        switch (operand) {
            case PLUS -> result = operandFirst + operandSecond;
            case MINUS -> result = operandFirst - operandSecond;
            case MULTIPLICATION -> result = operandFirst * operandSecond;
            case DIVISION -> result = operandFirst / operandSecond;
            default -> throw new RuntimeException("Invalid operand");
        }
        if (isRoman) {
            String test = getRomanValueOf(result);
            return getRomanValueOf(result);
        }
        return String.valueOf(result);
    }

    /**
     * convert roman number to arabic
     * @param num - roman number should be converted to roman
     * @return arabic value of roman number
     */
    private static int getArabicFromRoman(String num) {
        int arabicValue = 0;
        for (int i = 0; i < ROMAN_NUMS.size(); i++) {
            if (ROMAN_NUMS.get(i).equals(num)) {
                arabicValue = i + 1;
            }
        }
        if (arabicValue == 0) {
            throw new RuntimeException("Not valid arabic value");
        }
        return arabicValue;
    }

    /**
     * Convert arabic number to roman
     * @param num - arabic number should be converted to roman value
     * @return roman value of arabic number
     */
    private static String getRomanValueOf(int num) {
        StringBuilder romanNum = new StringBuilder();
        while (num > 0) {
            for (Map.Entry<Integer, String> entry : ROMAN_UTIL.entrySet()) {
                while (num >= entry.getKey()) {
                    romanNum.append(entry.getValue());
                    num -= entry.getKey();
                }
            }
        }
        return romanNum.toString();
    }

    /**
     * Check if number is roman
     * @param number - number which can be roman or not
     * @return true if given number is roman and false if not
     */
    private static boolean isRoman(String number) {
        return ROMAN_NUMS.contains(number);
    }

    /**
     * Check if expression has 2 operator and operand
     * @param expression - expression proceeding validation check
     * @return true if expression is valid
     */
    public static boolean isValidExpression(String[] expression) {
        return expression.length == 3;
    }

}