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
        // TODO code application logic here
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Feed me:");
        String input = "";
        try {
            input = br.readLine();
        } catch (IOException ex) {
            System.out.println("This program has encountered an error.");
            System.exit(1);
        }
        String output = "";
        int num = Integer.parseInt(input);
        if (num == 0) {
            System.out.println("zero");
            input = "";
        } else if (num < 20) {
            System.out.println(nineteenMinus(input));
            input = "";
        } else if (input.length() > 6) {
            String strmillions = input.substring(0, input.length() - 6);
            int intmillions = Integer.parseInt(strmillions);
            if (intmillions < 20) {
                output = nineteenMinus(strmillions) + " million ";
            } else {
                output = twentyPlus(strmillions, intmillions) + " million ";
            }
            input = input.substring(input.length() - 6, input.length());
        }

        if (input.length() > 3) {
            String strthousands = input.substring(0, input.length() - 3);
            int intthousands = Integer.parseInt(strthousands);
            if (intthousands < 20) {
                output = output + nineteenMinus(strthousands) + " thousand ";
            } else {
                output = output + twentyPlus(strthousands, intthousands) + " thousand ";
            }
            input = input.substring(input.length() - 3, input.length());
        }

        if (input.length()
                > 0 && input.length() <= 3) {
            String strones = input.substring(0, input.length());
            int intones = Integer.parseInt(strones);
            if (intones < 20) {
                output = output + nineteenMinus(strones);
            } else {
                output = output + twentyPlus(strones, intones);
            }
        }
        output = output.replaceAll("  ", " ");
        output = output.trim();
        System.out.println(output);
    }

    public static String twentyPlus(String strinput, int intinput) {
        String retval = "";
        if (intinput < 20) {
            return nineteenMinus(strinput.substring(0, 1));
        }
        switch (strinput.length()) {
            case 1:
                return nineteenMinus(strinput.substring(0, 1));
            case 2:
                retval = tens(strinput.substring(0, 1));
                if (!strinput.substring(1, 2).equals("0")) {
                    retval = retval + "-" ;
                }
                retval = retval + nineteenMinus(strinput.substring(1, 2));
                return retval;
            case 3:
                retval = nineteenMinus(strinput.substring(0, 1)) + " hundred ";
                String strones = strinput.substring(1, 3);
                int intones = Integer.parseInt(strones);
                if (intones < 20) {
                    retval = retval + nineteenMinus(strones);
                } else {
                    retval = retval + twentyPlus(strones, intones);
                }
                //retval = retval + tens(strinput.substring(1, 1));
                //if (strinput.substring(1, 1).equals("0") && strinput.substring(2, 2).equals("0")) {
                //    retval = retval + " - ";
                //}
                retval = retval + nineteenMinus(strinput.substring(2, 2));
                return retval;
            default:
                return "ERROR";

        }
    }

    public static String tens(String input) {
        switch (input) {
            case "2":
                return "twenty";
            case "3":
                return "thirty";
            case "4":
                return "fourty";
            case "5":
                return "fifty";
            case "6":
                return "sixty";
            case "7":
                return "seventy";
            case "8":
                return "eightty";
            case "9":
                return "ninety";
            default:
                return "";
        }
    }

    public static String nineteenMinus(String input) {
        switch (input) {
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
                return "seventeeb";
            case "18":
                return "eighteen";
            case "19":
                return "nineteen";
            default:
                return "";

        }
    }

}
