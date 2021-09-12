/* Variation of desert island exercise with arrays.
The program uses a list of 10 objects that each user can choose one of to take with them: "objectName[]".
As there are no actual users, the program tries to generate choices that are not completely random.

Required number of users (up to 99) is inputted.
Users are assigned an incremental user id from 0 up to whatever required.
The user ids are converted to unique usernames.
The usernames are then converted into values using a method: "convertUserNameToOption()".
This option is used as a simulated choice of object for each user - not random but determined by id.
The number of users that choose each option are totalled.
The collected data is outputted as a sorted table using a method: "printOptionTable()". */

package uds.knudsoft;

import java.util.Scanner;

public class Main {

    static Scanner input = new Scanner(System.in);

    //Simulate a single choice of one object from 10 possibilities for number of users up to 99. Output stats.
    public static void main(String[] args) {

        //input how many users max 99
        System.out.println("\nSurvey over which objects are most wanted on a desert island");
        System.out.print("How many users in the survey? Enter a number between 0 and 99: ");
        int numberOfUsers = input.nextInt();

        //Exit if number of users out of range and output table header
        if (numberOfUsers < 0 || numberOfUsers > 99) {
            numberOfUsers = 0;
        }
        System.out.println("OK. There were " + numberOfUsers + " users, whose choices were:");
        System.out.printf("%n%2s %-11s %2s %-16s %s%n", "Id", "Username", "Opt", "Object", "Total");

        //data to generate up to 100 different usernames, with alternatives that make different choices
        String[] digitName = {"Nul", "En", "To", "Tre", "Fire", "Fem", "Seks", "Syv", "Otte", "Ni"};
        //String[] digitName = {"Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};

        //List of selectable objects and how many times they are chosen by a user
        String[] objectName = {"Knife", "Fishing net", "Box of matches", "Hammock", "Bug spray", "Sunblock", "Inflatable raft", "Flashlight", "Spear", "Satellite phone"};
        int[] objectNumber = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

        //Create unique username for each user
        String[] username = new String[numberOfUsers];
        for (int i = 0; i < numberOfUsers; i++) {
            username[i] = digitName[i / 10] + digitName[i % 10];

            //Convert each username to an option 1-10
            int option = convertUsernameToOption(username[i]);

            //Increment the corresponding element of the objectNumber array
            objectNumber[option]++;

            //Output data for each users' choice
            System.out.printf("%2s %-10s %4s %-16s %s%n", i, username[i], option, objectName[option], objectNumber[option]);
        }

        //sort results and output
        printOptionTable(objectName, objectNumber);
    }

    //method to convert a string (username) into an integer between 1 and 10 inclusive (option)
    public static int convertUsernameToOption(String username) {
        char ch;
        int intValue;
        int option = 0;

        //Total the integer values of all characters in username and return as mod 10
        for (int i = 0; i < username.length(); i++) {
            ch = username.charAt(i);
            intValue = Character.getNumericValue(ch);
            option += intValue;
        }
        return option % 10;
    }

    //method to print sorted table of how many users chose each option
    public static void printOptionTable(String[] objectName, int[] objectNumber) {
        int tempInt;
        String tempString;
        for (int i = 0; i < 10; i++) {
            for (int j = i + 1; j < 10; j++) {
                if (objectNumber[j] > objectNumber[i]) {

                    //swap without using collections
                    tempInt = objectNumber[i];
                    objectNumber[i] = objectNumber[j];
                    objectNumber[j] = tempInt;
                    tempString = objectName[i];
                    objectName[i] = objectName[j];
                    objectName[j] = tempString;
                }
            }
            System.out.printf("%n%-16s %s", objectName[i], objectNumber[i]);
        }
    }
}
