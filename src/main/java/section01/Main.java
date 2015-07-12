package section01;

/**
 * @author alexandrubostan
 * @since 10.07.2015
 */
public class Main {
    public static void main(String[] args) {
        System.out.println(fixSpelling("hostleworld"));
    }

    public static String fixSpelling(String name) {
        String wordToCheck = new String(name);
//        if (wordToCheck == "hostleworld") {
        if (wordToCheck.equals("hostleworld")) {
            name = "hostelworld";
        } else {
            fixSpelling(name);
        }
        return name;
    }
}

/*
The "==" operator when applied to objects tests them for reference equality.

Since "wordToCheck" and "hostleworld" are two different objects the base
recursion condition is never satisfied and the program goes into an
infinite recursion and the stack overflows.

The solution is to use equals() to test the objects for value equality.
*/
