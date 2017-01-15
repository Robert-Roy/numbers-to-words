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

    public static void main(String[] args) {
        //choose which mode to run in
        run();
        //debug();
    }

    public static void debug() {
        // here I can run whatever code I want to test
        String test = "1";
        for (int i = 0; i < 3005; i++) {
            test = test + "0";
        }
        test = test + ".";
        for (int i = 0; i < 3005; i++) {
            test = test + "0";
        }
        test = test + "1";
        System.out.println(test.length());
        System.out.println(isValid(test));
        System.out.println(convert(test));
    }

    public static void run() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = "";
        String output = "";
        // Validate Input
        while (!isValid(input)) {
            System.out.print("Input a number (no commas):");
            try {
                input = br.readLine(); // get user input
            } catch (IOException ex) {
                System.out.println("This program has encountered an error.");
                System.exit(0);
            }
        }
        output = convert(input);
        System.out.println(output);
    }

    public static String convert(String input) {
        int decimalIndex; // where in input the decimal is
        String output = "";
        String strNegative = ""; // added to output later
        if (isNegative(input)) {
            strNegative = "negative ";
            input = input.substring(1, input.length()); // trim "-"
        }
        decimalIndex = input.indexOf(".");
        if (decimalIndex == -1) {
            output = getConvertedString(input, false); // convert input
        } else {
            // split string at decimal
            String strPredecimal = input.substring(0, decimalIndex);
            String strPostdecimal = input.substring(decimalIndex + 1, input.length());
            // convert both strings
            strPredecimal = getConvertedString(strPredecimal, false);
            strPostdecimal = getConvertedString(strPostdecimal, true);
            if (strPredecimal.equals("zero") && strPostdecimal.length() != 0) {
                output = strPostdecimal; // only post decimal stuff
            } else if (strPredecimal.length() != 0 && strPostdecimal.length() != 0) {
                output = strPredecimal + " and " + strPostdecimal; // pre and post decimal stuff
            } else {
                output = strPredecimal; // only predecimal stuff
            }
        }
        output = strNegative + output;
        return output;
    }

    public static String getConvertedString(String strConvert, boolean blnFraction) {
        Boolean blnFractionSelected = false;
        String strFraction = "";
        String retval = "";
        while (!strConvert.equals("")) {
            //Note: There are no commas in the input at this point, comma is only
            //used for ease of reference
            //number of commas that would be in the number written out
            int commas = (strConvert.length() - 1) / 3;
            //everything before the comma (or end, if there is not one)
            int precomma = strConvert.length() % 3;
            // input without length cannot get to this point, so 0 is always 3
            if (precomma == 0) {
                precomma = 3;
            }
            if (blnFraction && !blnFractionSelected) {
                // find the appropriate fraction for post-decimal text
                Byte nextUnit = 0;
                switch (precomma) {
                    case 1:
                        strFraction = " ten";
                        break;
                    case 2:
                        strFraction = " hundred";
                        break;
                    case 3:
                        nextUnit = 1;
                }
                String unit = LatinUnits.UNITS[commas + nextUnit].trim();
                if (unit.length() > 0) {
                    try {
                        int value = Integer.parseInt(strConvert);
                        if (value == 1) {
                            strFraction = strFraction + " " + unit + "th";
                        } else {
                            strFraction = strFraction + " " + unit + "ths";
                        }
                    } catch (Exception ex) {
                        strFraction = strFraction + " " + unit + "ths";
                    }
                } else {
                    try {
                        int value = Integer.parseInt(strConvert);
                        if (value == 1) {
                            strFraction = strFraction + "th";
                        } else {
                            strFraction = strFraction + "ths";
                        }
                    } catch (Exception ex) {
                        strFraction = strFraction + "ths";

                    }
                }
                blnFractionSelected = true;
            }
            // substring of everything before the comma
            String strThisUnit = strConvert.substring(0, precomma);
            //loop trims zeroes at the beginning of numbers
            for (int a = 0; a < strThisUnit.length(); a++) {
                if (strThisUnit.substring(a, a + 1).equals("0")) {
                    // remove first letter of string
                    strThisUnit = strThisUnit.substring(a + 1, strThisUnit.length());
                    // move counter back
                    a--;
                } else {
                    // if a nonzero number is hit, output is created
                    retval = retval + wordsFromNum(strThisUnit) + LatinUnits.UNITS[commas];
                    break;
                }
            }
            // input is cut shorter to remove addressed portion
            strConvert = strConvert.substring(precomma, strConvert.length());
        }

        if (retval.equals("")) {
            retval = "zero"; // if no output has been added yet, result is zero
        }
        retval = retval.replaceAll("  ", " "); // remove doublespaces
        retval = retval.trim(); // remove trailing spaces
        if (blnFraction && !strFraction.equals("")) {
            return retval + strFraction;
        } else if (blnFraction) {
            return "";
        } else {
            return retval;
        }
    }

    public static String wordsFromNum(String strinput) {
        // return anything 0-19
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
        }
        String retval = ""; //return value
        switch (strinput.length()) {
            case 2:
                // input >= 20, so must get tens and ones separate
                retval = tens(strinput.substring(0, 1));
                // num = XY, if X and Y are not zero, we need a hyphen
                if (!strinput.substring(1, 2).equals("0") && !strinput.substring(0, 1).equals("0")) {
                    retval = retval + "-";
                }
                // recurse for ones
                retval = retval + wordsFromNum(strinput.substring(1, 2));
                return retval;
            case 3:
                // recurse for first digit
                retval = wordsFromNum(strinput.substring(0, 1)) + " hundred ";
                // recurse for remaining two digit number
                String strones = strinput.substring(1, 3);
                retval = retval + wordsFromNum(strones);
                return retval;
            default:
                // should be unreachable, but just in case
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

    public static boolean isValid(String input) {
        if (input.equals("")) {
            return false;
        }
        if (isNegative(input)) {
            input = input.substring(1, input.length());
        }
        int decimalIndex = 0;
        decimalIndex = input.indexOf(".");
        if (decimalIndex == -1 && input.length() < 3007) {
            return true;
        } else {
            String strPredecimal = input.substring(0, decimalIndex);
            String strPostdecimal = input.substring(decimalIndex + 1, input.length());
            if (strPredecimal.length() < 3007 && strPostdecimal.length() < 3007) {
                return true;
            } else {
                return false;
            }
        }
    }

    public static boolean isNegative(String input) {
        if (input.substring(0, 1).equals("-")) {
            return true;
        }
        return false;
    }

    public static boolean isNumeric(String input) {
        final int ALLOWED_DECIMALS = 1; //maximum number of decimals. 0 for ints, 1 for other
        int decimals = 0; //counter
        if (input.length() == 0) {
            return false; // "" is not numeric
        }
        for (int i = 0; i < input.length(); i++) {
            switch (input.substring(i, i + 1)) {
                case "-":
                    if (i == 0) {
                        break;
                    } else {
                        return false;
                    }
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
                    // if we got here, input was not numeric
                    return false;
            }
        }
        return true;
    }

}
