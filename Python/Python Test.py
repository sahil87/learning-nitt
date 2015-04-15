#a program to print somthing from stderr
hello = "hello world"
import sys 
sys.stderr.write(hello+"\n")
raw_input("typed this just to stop the program from exitting immediately")