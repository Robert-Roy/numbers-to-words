/*
 * All rights reserved. Copyright Robert Roy 2016.
 */
package numtoword;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Robert Roy <www.robertsworkspace.com>
 */
public class NumToWord {

    private static final String[] UNITS = new String[]{
        "",
        " thousand ",
        " million ",
        " billion ",
        " trillion ",
        " quadrillion ",
        " quintillion ",
        " sextillion ",
        " septillion ",
        " octillion ",
        " nonillion ",
        " decillion ",
        " undecillion ",
        " duodecillion ",
        " tredecillion ",
        " quattuordecillion ",
        " quindecillion ",
        " sexdecillion ",
        " septendecillion ",
        " octodecillion ",
        " novemdecillion ",
        " vigintillion ",};

    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = "";
        String output = "";
        while (!isNumeric(input)) {
            System.out.print("Input a number:");
            try {
                input = br.readLine();
            } catch (IOException ex) {
                System.out.println("This program has encountered an error.");
                System.exit(0);
            }
            if (input.length() > 66) {
                System.out.println("Sorry, that number was too large.");
                input = "";
            }
        }
        for (int i = 0; i < input.length(); i++) {
            int commas = (input.length() - 1) / 3;
            int precomma = input.length() % 3;
            if (precomma == 0) {
                precomma = 3;
            }
            String strThisUnit = input.substring(0, precomma);
            for (int a = 0; a < strThisUnit.length(); a++) {
                if (strThisUnit.substring(a, a + 1).equals("0")) {
                    strThisUnit = strThisUnit.substring(a + 1, strThisUnit.length());
                    a--;
                } else {
                    output = output + wordsFromNum(strThisUnit) + UNITS[commas];
                    input = input.substring(precomma, input.length());
                    break;
                }
            }
        }
        if (output.equals("")) {
            output = "zero";
        }
        output = output.replaceAll("  ", " ");
        output = output.trim();
        System.out.println(output);
    }

    public static String wordsFromNum(String strinput) {
        switch (strinput) {
            case "0":
                return "";
            case "1":
                return "one";
            case "2":
                return "two";
            case "3":
                return "three";
            case "4":
                return "four";
            case "5":
                return "five";
            case "6":
                return "six";
            case "7":
                return "seven";
            case "8":
                return "eight";
            case "9":
                return "nine";
            case "10":
                return "ten";
            case "11":
                return "eleven";
            case "12":
                return "twelve";
            case "13":
                return "thirteen";
            case "14":
                return "fourteen";
            case "15":
                return "fifteen";
            case "16":
                return "sixteen";
            case "17":
                return "seventeen";
            case "18":
                return "eighteen";
            case "19":
                return "nineteen";
            default:
        }
        String retval = "";
        switch (strinput.length()) {
            case 1:
                return wordsFromNum(strinput.substring(0, 1));
            case 2:
                retval = tens(strinput.substring(0, 1));
                if (!strinput.substring(1, 2).equals("0") && !strinput.substring(0, 1).equals("0")) {
                    retval = retval + "-";
                }
                retval = retval + wordsFromNum(strinput.substring(1, 2));
                return retval;
            case 3:
                retval = wordsFromNum(strinput.substring(0, 1)) + " hundred ";
                String strones = strinput.substring(1, 3);
                retval = retval + wordsFromNum(strones);
                return retval;
            default:
                return " ERROR ";
        }
    }

    public static String tens(String input) {
        switch (input) {
            case "2":
                return "twenty";
            case "3":
                return "thirty";
            case "4":
                return "forty";
            case "5":
                return "fifty";
            case "6":
                return "sixty";
            case "7":
                return "seventy";
            case "8":
                return "eighty";
            case "9":
                return "ninety";
            default:
                return "";
        }
    }

    public static boolean isNumeric(String input) {
        final int ALLOWED_DECIMALS = 0;
        int decimals = 0;
        if (input.length() == 0) {
            return false;
        }
        for (int i = 0; i < input.length(); i++) {
            switch (input.substring(i, i + 1)) {
                case "0":
                    break;
                case "1":
                    break;
                case "2":
                    break;
                case "3":
                    break;
                case "4":
                    break;
                case "5":
                    break;
                case "6":
                    break;
                case "7":
                    break;
                case "8":
                    break;
                case "9":
                    break;
                case ".":
                    decimals++;
                    if (decimals > ALLOWED_DECIMALS) {
                        return false;
                    }
                    break;
                default:
                    return false;
            }
        }
        return true;
    }

}
