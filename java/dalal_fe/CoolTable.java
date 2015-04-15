/******************************************************************/
////	
////	Class Description	:	Cool Table, extends Composite to 
////							produce tables with sortable fields
////	Created By			:	Abhishek Verma
////	Created on			:	Unknown
////	Last Modification 	: 
////	Last Modified By 	: 
////	Last Modified on	:	
////
/******************************************************************/

import java.util.Arrays;
import java.util.Comparator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;


public class CoolTable extends Composite
{

	public Table table;
	public TableEditor editor;
	public TableItem item;
	private static int lastSortColumn= -1;
	public String[] columnNames;// =
	public CoolTable(Composite parent,String[] names,int[] colWidths,int style)
	{
		super(parent, SWT.NONE);
		this.setLayout(new FillLayout());

		columnNames=names;
		table = new Table(this,style | SWT.BORDER |SWT.FULL_SELECTION|SWT.FILL);
		table.setLinesVisible (true);
		table.setHeaderVisible(true);	
		for(int i = 0; i < columnNames.length; i++) 
		{
			TableColumn column = new TableColumn(table, SWT.NONE);
			column.setText(columnNames[i]);
			if(colWidths!=null)
			{
				column.setWidth(colWidths[i]);
			}
			else
			{
				column.setWidth(90);
			}
			column.setMoveable(true);
			final int columnIndex = i;
			column.addSelectionListener(new SelectionAdapter() {		
				public void widgetSelected(SelectionEvent e) {
					sort(columnIndex);
				}
			});
		}
	this.setSize(table.computeSize(SWT.DEFAULT, SWT.DEFAULT).x, 600);
	//this.setSize(table.computeSize(500,500).x, table.computeSize(500,500).y);
	}

	public void sortByColumn2(int direction)
	{
		if(direction==-1)	lastSortColumn = 1;
		sort(1);
	}
	private void sort(int column) {
		if(table.getItemCount() <= 1) return;
	
		TableItem[] items = table.getItems();
		int rows=items.length;
		int cols=table.getColumnCount();
		String[][] data = new String[rows][cols+1];
		
		//System.out.println("rows= "+rows+"cols="+cols);
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < cols; j++) {
				
				data[i][j] = items[i].getText(j);
			}
			data[i][cols]=items[i].getData()+"";
		}
		
		RowComparator rowComparator = new RowComparator(column);
		Arrays.sort(data,rowComparator);
		table.setSortColumn(table.getColumn(column));
		if (lastSortColumn != column) 
		{
			table.setSortDirection(SWT.DOWN);
			//TODO Add check for "" and " ", if any of these add in an array and check them out later.
			int rcount[] = new int[rows];
			int acount=-1,r=0;
			for (int i = 0; i < rows; i++) 
			{
				if((data[i][column]).equals("") || (data[i][column]).equals(" "))
				{	
					rcount[++acount]=i;
					//System.out.println("CoolTable.java : i : acount "+i+" "+acount);
					continue;
				}
				for(int j=0;j<cols;j++)
					items[r].setText(j,data[i][j]);
				items[r].setData(data[i][cols]);
				r++;
			}
			int counter = 0;
			while(counter<=acount)
			{
				int i = rcount[counter];
				for(int j=0;j<cols;j++)
					items[r].setText(j,data[i][j]);
				items[r].setData(data[i][cols]);
				r++;
				counter++;
			}
			lastSortColumn = column;
		} else {
			// reverse order if the current column is selected again
			table.setSortDirection(SWT.UP);
			int k = data.length -1;
			
		/*	int icount[] = new int[rows];
			int kcount[] = new int[rows];
			int count=-1;*/
			for (int i = 0; i < rows; i++) 
			{
			/*	if((data[k][column]).equals("") || (data[k][column]).equals(" "))
				{	
					count++;
					icount[count]=i;
					kcount[count]=k;
					k=k-1;
					//System.out.println("in CoolTable.java : k : column "+k+" "+column);
					continue;
				}*/
				for(int j=0;j< cols;j++)
					items[i].setText(j,data[k][j]);
				items[i].setData(data[k][cols]);
				k=k-1;
			}
		/*	int counter=0;
			while(counter<=count)
			{
				int i = icount[counter];
				k = kcount[counter];
				for(int j=0;j< cols;j++)
					items[i].setText(j,data[k][j]);
				items[i].setData(data[k][cols]);
				counter++;
			}*/
			lastSortColumn = -1;
		}
		
	}
	public void newEntry(String[] data,int bidId)
	{
		
		if (data != null) {
			item = new TableItem(table, SWT.NONE);
			item.setData(bidId+"");
			item.setText(data);
		}
	
	}
	/**
	 * To compare entries (rows) by the given column
	 */
	private class RowComparator implements Comparator {
		private int column;
		
		/**
		 * Constructs a RowComparator given the column index
		 * @param col The index (starting at zero) of the column
		 */
		public RowComparator(int col) {
			column = col;
		}
		
		/**
		 * Compares two rows (type String[]) using the specified
		 * column entry.
		 * @param obj1 First row to compare
		 * @param obj2 Second row to compare
		 * @return negative if obj1 less than obj2, positive if
		 * 			obj1 greater than obj2, and zero if equal.
		 */
		public int compare(Object obj1, Object obj2) 
		{
			String[] row1 = (String[])obj1;
			String[] row2 = (String[])obj2;
			int i,j;double k,l;
			try
			{
				i=Integer.parseInt(row1[column]);
				j=Integer.parseInt(row2[column]);
			}
			catch(NumberFormatException e)
			{
				try
				{
					k=Double.parseDouble(row1[column]);
					l=Double.parseDouble(row2[column]);
				}
				catch(NumberFormatException e2) 
				{
					return row1[column].compareTo(row2[column]);		
				}
				return (int)(k-l);
			}
			return (i-j);
		}
	}
}
