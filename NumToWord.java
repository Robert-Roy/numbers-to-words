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
        // Validate Input
        while (!isValid(input)) {
            System.out.print("Input a commaless whole number between 10^67 and -10^67:");
            try {
                input = br.readLine(); // get user input
            } catch (IOException ex) {
                System.out.println("This program has encountered an error.");
                System.exit(0);
            }
        }
        String strNegative = "";
        if(isNegative(input)){
            strNegative = "negative ";
            input = input.substring(1, input.length());
        }
        while (!input.equals("")) {
            //Note: There are no commas in the input at this point, comma is only
            //used for ease of reference
            //number of commas that would be in the number written out
            int commas = (input.length() - 1) / 3;
            //everything before the comma (or end, if there is not one)
            int precomma = input.length() % 3;
            // input without length cannot get to this point, so 0 is always 3
            if (precomma == 0) {
                precomma = 3;
            }
            // substring of everything before the comma
            String strThisUnit = input.substring(0, precomma);
            //loop trims zeroes at the beginning of numbers
            for (int a = 0; a < strThisUnit.length(); a++) {
                if (strThisUnit.substring(a, a + 1).equals("0")) {
                    // remove first letter of string
                    strThisUnit = strThisUnit.substring(a + 1, strThisUnit.length());
                    // move counter back
                    a--;
                } else {
                    // if a nonzero number is hit, output is created
                    output = output + wordsFromNum(strThisUnit) + UNITS[commas];
                    break;
                }
            }
            // input is cut shorter to remove addressed portion
            input = input.substring(precomma, input.length());
        }
        if (output.equals("")) {
            output = "zero"; // if no output has been added yet, result is zero
        }else{
            output = strNegative + output;
        }
        output = output.replaceAll("  ", " "); // remove doublespaces
        output = output.trim(); // remove trailing spaces
        System.out.println(output);
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

    public static boolean isValid(String input){
        if(input.equals("")){
            return false;
        }
        if(isNegative(input)){
            if(!(input.length() < 68)){
                return false;
            }
        }else{
            if(!(input.length() < 67)){
                return false;
            }
        }
        if(!isNumeric(input)){
            return false;
        }
        return true;
    }
    
    public static boolean isNegative(String input){
        if(input.substring(0,1).equals("-")){
            return true;
        }
        return false;
    }
    
    public static boolean isNumeric(String input) {
        final int ALLOWED_DECIMALS = 0; //maximum number of decimals. 0 for ints, 1 for other
        int decimals = 0; //counter
        if (input.length() == 0) {
            return false; // "" is not numeric
        }
        for (int i = 0; i < input.length(); i++) {
            switch (input.substring(i, i + 1)) {
                case "-":
                    if(i == 0){
                        break;
                    }else{
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
