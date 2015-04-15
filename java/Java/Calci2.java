import javax.swing.*;
import java.awt.*;
import java.awt.event.*; 

class Calci extends JFrame implements ActionListener {
 
 private JButton zeroButton;
 private JButton oneButton;
 private JButton twoButton;
 private JButton threeButton;
 private JButton fourButton;
 private JButton fiveButton;
 private JButton sixButton;
 private JButton sevenButton;
 private JButton eightButton;
 private JButton nineButton;
 private JButton addButton;
 private JButton mulButton;
 private JButton difButton;
 private JButton divButton;
 private JButton cButton;
 private JButton ceButton; 
 private JButton eqlButton;
 private JButton pntButton;

 private static final int FRAME_WIDTH    = 250; 
 private static final int FRAME_HEIGHT   = 300; 
 private static final int FRAME_X_ORIGIN = 150; 
 private static final int FRAME_Y_ORIGIN = 250; 
 private static final int BUTTON_WIDTH  = 42; 
 private static final int BUTTON_HEIGHT = 40; 
 private JTextField output;
 public int n=0,m=0,c,d=0,fl,fm,fc,a1Flag,cs;
 public double a1,a2,op,mc;
 public String exp=new String("");
 public String aw=new String("");


 public static void main (String k[]) {
 
    Calci frame =new Calci();

  frame.setTitle(" Watch this calci");

   frame.setVisible(true);

  }
 
 public Calci() {

  Container contentPane =getContentPane();
  contentPane.setLayout( null ); 
//myImage = Toolkit.getDefaultToolkit().getImage(filenameOrURL);
  contentPane.setBackground( Color.blue );
  setLocation(FRAME_X_ORIGIN,FRAME_Y_ORIGIN);
  setSize(FRAME_WIDTH,FRAME_HEIGHT);

  zeroButton = new JButton("0"); 
  zeroButton.setBounds(20, 200, BUTTON_WIDTH, BUTTON_HEIGHT); 
  contentPane.add(zeroButton); 

  oneButton = new JButton("1"); 
  oneButton.setBounds(20, 165, BUTTON_WIDTH, BUTTON_HEIGHT); 
  contentPane.add(oneButton); 

  twoButton = new JButton("2"); 
  twoButton.setBounds(62, 165, BUTTON_WIDTH, BUTTON_HEIGHT); 
  contentPane.add(twoButton); 

  threeButton = new JButton("3"); 
  threeButton.setBounds(104, 165, BUTTON_WIDTH, BUTTON_HEIGHT); 
  contentPane.add(threeButton); 

  fourButton = new JButton("4"); 
  fourButton.setBounds(20, 130, BUTTON_WIDTH, BUTTON_HEIGHT); 
  contentPane.add(fourButton); 

  fiveButton = new JButton("5"); 
  fiveButton.setBounds(62, 130, BUTTON_WIDTH, BUTTON_HEIGHT); 
  contentPane.add(fiveButton); 

  sixButton = new JButton("6"); 
  sixButton.setBounds(104, 130, BUTTON_WIDTH, BUTTON_HEIGHT); 
  contentPane.add(sixButton); 

  sevenButton = new JButton("7"); 
  sevenButton.setBounds(20, 95, BUTTON_WIDTH, BUTTON_HEIGHT); 
  contentPane.add(sevenButton); 

  eightButton = new JButton("8"); 
  eightButton.setBounds(62, 95, BUTTON_WIDTH, BUTTON_HEIGHT); 
  contentPane.add(eightButton); 

  nineButton = new JButton("9"); 
  nineButton.setBounds(104, 95, BUTTON_WIDTH, BUTTON_HEIGHT); 
  contentPane.add(nineButton); 

  addButton = new JButton("+"); 
  addButton.setBounds(150, 95, BUTTON_WIDTH, BUTTON_HEIGHT); 
  contentPane.add(addButton); 

  difButton = new JButton("-"); 
  difButton.setBounds(150, 130, BUTTON_WIDTH, BUTTON_HEIGHT); 
  contentPane.add(difButton); 

  mulButton = new JButton("*"); 
  mulButton.setBounds(150, 165, BUTTON_WIDTH, BUTTON_HEIGHT); 
  contentPane.add(mulButton); 

  divButton = new JButton("/"); 
  divButton.setBounds(150, 200, BUTTON_WIDTH, BUTTON_HEIGHT); 
  contentPane.add(divButton); 

  eqlButton = new JButton("="); 
  eqlButton.setBounds(104, 200, BUTTON_WIDTH, BUTTON_HEIGHT); 
  contentPane.add(eqlButton); 

  pntButton = new JButton("."); 
  pntButton.setBounds(62, 200, BUTTON_WIDTH, BUTTON_HEIGHT); 
  contentPane.add(pntButton); 

  cButton = new JButton("C"); 
  cButton.setBounds(180, 0, BUTTON_WIDTH+20, BUTTON_HEIGHT); 
  contentPane.add(cButton); 
  
  ceButton = new JButton("CE"); 
  ceButton.setBounds(180, 40, BUTTON_WIDTH+20, BUTTON_HEIGHT); 
  contentPane.add(ceButton); 




  zeroButton.addActionListener(this); 
  oneButton.addActionListener(this);
  twoButton.addActionListener(this);
  threeButton.addActionListener(this);
  fourButton.addActionListener(this);
  fiveButton.addActionListener(this);
  sixButton.addActionListener(this);
  sevenButton.addActionListener(this);
  eightButton.addActionListener(this);
  nineButton.addActionListener(this);
  addButton.addActionListener(this);
  difButton.addActionListener(this);
  mulButton.addActionListener(this);
  divButton.addActionListener(this);
  eqlButton.addActionListener(this);
  pntButton.addActionListener(this);
  cButton.addActionListener(this);
  ceButton.addActionListener(this);
  

 

  output=new JTextField();
  Font g=new Font("BankGothic Lt BT",Font.PLAIN,15);
  output.setFont(g);
  output.setForeground(Color.green);
  output.setBackground(Color.black);
  output.setEditable(false);
  output.setBounds(10,30,160,40);
  output.setHorizontalAlignment(JTextField.RIGHT);
  contentPane.add(output);


   setDefaultCloseOperation( EXIT_ON_CLOSE ); 
  }

 public void actionPerformed(ActionEvent event) { 

  if (event.getSource() instanceof JButton) { 
     JButton clickedButton = (JButton)event.getSource(); 
     String  buttonText = clickedButton.getText();  
  
     if(buttonText.equals("0") && fm==0)
       {
       	
	    exp+="0";
	    output.setText(exp);
       	mc=Double.valueOf(exp).doubleValue();
       	if(mc==0)	    
	      fm=1;

        }

      else if(buttonText.equals("1"))
       {

	    exp+="1";
	     output.setText(exp);

        }

      else if(buttonText.equals("2"))
       {

	    exp+="2";
	     output.setText(exp);

        }

      else if(buttonText.equals("3"))
       {

	    exp+="3";
	     output.setText(exp);

        }

      else if(buttonText.equals("4"))
       {
  
	    exp+="4";
	    output.setText(exp);

        }

      else if(buttonText.equals("5"))
       {

	    exp+="5";
	    output.setText(exp);

        }

      else if(buttonText.equals("6"))
       {

	    exp+="6";
	    output.setText(exp);

        }

      else if(buttonText.equals("7"))
       {

	    exp+="7";
	    output.setText(exp);

        }

      else if(buttonText.equals("8"))
       {

	    exp+="8";
	    output.setText(exp);

        }

      else if(buttonText.equals("9"))
       {

	    exp+="9";
	    output.setText(exp);

        }

	  else if(buttonText.equals("+") || buttonText.equals("-"))
	   {
		  cs=0;
         fl=0;
         fm=0;
       	if(a1Flag==1)
       	   a2=Double.valueOf(exp).doubleValue();
       	   
       	else
       	  {
       	  	a1=Double.valueOf(exp).doubleValue();       	  
       	  	a1Flag=1;
       	  	}
       	  	
       	  	if(buttonText.equals("+"))
               {
               	if(c==0 )
               	 { c=1;
               	   op=a1+a2;
                   a1=op;
                 } 
       
               	else if(c==1 && d==0 )
                {op=a1+a2;
                a1=op;
                c=1;
                } 
                else if(c==2 && d==0 )
                {op=a1-a2;
                a1=op;
                c=1;
                } 
                else if(c==3&& d==0)
                {op=a1*a2;
                a1=op;
                c=1;
                } 
                else if(c==4 && d==0 )
                {op=a1/a2;
                a1=op;
                c=1;
                } 
                else if(d==1)
                 c=1;


                }  
            else 
               {
               	if(c==0)
               	 {c=2;
               	 op=a1-a2;
                a1=op;
                c=2;
                } 

               	else if(c==1 && d==0)
                {op=a1+a2;
                a1=op;
                c=2;
                } 
                else if(c==2 && d==0 )
                {op=a1-a2;
                a1=op;
                c=2;
                } 
                else if(c==3  && d==0)
                {op=a1*a2;
                a1=op;
                c=2;
                } 
                else if(c==4 && d==0 )
                {op=a1/a2;
                a1=op;
                c=2;
                }
                else if(d==1)
                 c=2;

     
               }
               
           aw=String.valueOf(op);        
            output.setText(aw);               
                    	         	  	
          exp="";
	   }
      else if(buttonText.equals("*")||buttonText.equals("/"))
      {
         cs=0;
      	 fl=0;
      	 fm=0;
       	if(a1Flag==1)
       	  a2=Double.valueOf(exp).doubleValue();
     	   
       	else
       	  {
       	  	a1=Double.valueOf(exp).doubleValue();       	  
       	  	a2=1;
       	  	a1Flag=1;
       	  	}
       	  	
            if(buttonText.equals("*"))
               {
               	if(c==0)
               	 { c=3;
               	  op=a1*a2;
               	  a1=op;
               	  }
               	else if(c==1 && d==0 )
                {op=a1+a2;
                a1=op;
                c=3;
                } 
                else if(c==2 && d==0 )
                {op=a1-a2;
                a1=op;
                c=3;
                } 
                else if(c==3 && d==0 )
                {op=a1*a2;
                a1=op;
                c=3;
                } 
                else if(c==4 && d==0 )
                {op=a1/a2;
                a1=op;
                c=3;
                } 
                else if(d==1)
                 c=3;                

               }
            else if(buttonText.equals("/"))
               {
               	if(c==0)
               	 {c=4;
               	 op=a1/a2;
               	  a1=op;
               	  }
               	 else if(c==1 && d==0 )
                {op=a1+a2;
                a1=op;
                c=4;
                } 
                else if(c==2 && d==0 )
                {op=a1-a2;
                a1=op;
                c=4;
                } 
                else if(c==3 && d==0 )
                {op=a1*a2;
                a1=op;
                c=4;
                } 
                else if(c==4  && d==0)
                {op=a1/a2;
                a1=op;
                c=4;
                } 
                else if(d==1)
                 c=4;                

               }
               
           aw=String.valueOf(op);        
            output.setText(aw);               
                    	         	  	
          exp="";
          
	   }      
	   	
	  else if(buttonText.equals("C"))
	   {
	   	exp="";
	   	output.setText(exp);
	   	a1=0;
	   	a2=0;
	   	a1Flag=0;
	   	fl=0;
	   	fm=0;
	   	op=0;
	   	c=0;
	   	d=0;
	   	}
	  else if(buttonText.equals("CE"))
	   {
	   	exp="";
	   	a2=0;
	   	a1Flag=1;
	   	fl=0;
	   	fm=0;
	   	output.setText(exp);	   	
	   	}	   		   	
	  else if(buttonText.equals(".") && fl ==0)
	   {
	   	
	    exp+=".";
	    output.setText(exp);
	    fl =1;	   	
	   	}	   		   
	  else if(buttonText.equals("="))
       	  {

       	  	if (cs==1)
       	  	 a2=a2;
       	  	else if(exp=="")
       	  	  a2=a1;
       	  	else
       	  	  a2=Double.valueOf(exp).doubleValue();
       	  	fl=0;
       	  	fm=0;
          switch(c)
           {
           	 case 1:
           	  op=a1+a2;
           	  a1=op;
           	  d=1;
           	  break;
           	 case 2:
           	  op=a1-a2;
           	  a1=op;
			  d=1;
           	  break;
           	 case 3:
           	  op=a1*a2;
           	  a1=op;
           	  d=1;
           	  break;   
           	 case 4:
           	  op=a1/a2;
           	  a1=op;
           	  d=1;
           	  break;           	         	
            }  

            aw=String.valueOf(op);        
            output.setText(aw);
			cs=1;

	   	}
    }
   
 }
}