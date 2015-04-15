/*program to show arrays in Strings
and to show the default methods in String object*/

class StringArrayDemo{
    public static void main(String args[]){
        String mystring[]={"first","second","third","first"};

        //The Commamd-Line Arguments
        System.out.println("The Command-Line Arguments:");
        for(int i=0;i<args.length;i++){
            System.out.println("args[" + i + "]: " + args[i]);
        }

        //Using length instance variable in any array
        System.out.println("\nThe mystring array :");
        for(int i=0;i<mystring.length;i++){
            System.out.println("mystring["+i+"]: "+mystring[i]);
        }
        //length() method in any String object
        System.out.println("\nLength of \"mystring[1]\":");
        System.out.println(mystring[1].length());

        //equals() method in any String object
        System.out.print("\nmystring[0].equals(mystring[3]): ");
        System.out.println(mystring[0].equals(mystring[3]));

        //charAt() method in any String object
        System.out.print("\nFifth character of mystring[1]: ");
        System.out.println(mystring[1].charAt(4));

        //Concatenating Strings
        String concat=mystring[1] + " and " + mystring[2];
        System.out.println("\nConcatenating mystring[1] and mystring[2]: " + concat);
    }
}