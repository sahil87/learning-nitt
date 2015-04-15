package co;
import java.util.*;

public class Company {
	public BitSet check;
	public String name;
	public String info;
	public Pricing price;
	public Company()
	{
		check = new BitSet(3);
		name = new String();
		info = new String();
		price = new Pricing();
	}
}
