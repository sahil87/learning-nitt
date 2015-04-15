/*Program to check whether the size of the array can be provided by
a variable or not (in c malloc has to be used to declare a variable
size according to the requirements)*/

class Array
{	public static void main(String arg[])
        {       int i=5;
		int arr[];
                arr = new int[i];
                for(i=0;i<5;i++)
                        System.out.println("int[" + i + "] = " + arr[i]);
	}
}
