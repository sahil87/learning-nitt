/*Java program to demonstrate working of classes using First in Last out stack*/
/*Also uses a menu structure?*/
class Stack{
    private int stck[];
    private int tos;//top of stack
    //Constructor-
    Stack(){
        stck[] = new int[10];
        tos=-1;
    }
    Stack(int size){
        stck[] = new int[size];
        tos=-1;
    }

    //to push in a new element on top-
    void push(int inp){
        if(tos==stck.length-1)
                  System.out.println("Stack overflow");
        else
            stck[++tos]=inp;
    }

    //to take out a new element from top-
    int pop(){
        if(tos==-1){
                    System.out.println("Stack underrun");
                    return 0;
        }
        else
            return stck[tos--];
    }

    //to display all the elements
    void displayall(){
        System.out.println("Current stack -");
        for(int i=tos;i>=0;i--){
            System.out.println(stck[i]);
        }
    }
}

class StackFiloMenu{
    public static void main(String args[])
    throws java.io.IOException{
        char choice='0';
        Stack st=new Stack();
        while(choice!='4'){
            System.out.println("Enter your choice - ");
            System.out.println("\t1) Display the stack");
            System.out.println("\t2) Push an element on top");
            System.out.println("\t3) Pop out an element from the top");
            System.out.println("\t4) exit");
            System.out.print("Choice :");
            choice=(char)System.in.read();
            switch (choice){
                case '1' :{
                     st.displayall();
                     break;
                }
                case '2' :{
                     System.out.print("Enter the new top element :");
                     int a=System.in.read();
                     st.push(a);
                     break;
                }
                case '3' :{
                     System.out.println("Popping out the top element "+st.pop());
                     break;
                }
                case '4' :{
                     System.out.println("Exiting the program");
                }
            }
        }
    }
}