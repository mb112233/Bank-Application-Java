package bankApplication;



import java.lang.reflect.Field;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;

public class BuildJTable {
	    JFrame frame;
		JTable table;
		String [] p= {"name","CNP","age"};
		String [] a= {"IBAN","currentSold","typeOfAcc"};
		private JScrollPane scrollPane ;
		public Object[][] matrix;
	
		public BuildJTable(ArrayList<?> list) {
			matrix=makeTable(list);
			frame = new JFrame();
			frame.setVisible(true);
			frame.setResizable(true);
			
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			if (list.get(0).getClass().getName().contains("Account")) {
				table=new JTable(this.matrix,a);
				table.addMouseListener(new java.awt.event.MouseAdapter(){
					 public void mouseClicked(java.awt.event.MouseEvent e){
						 int row=table.rowAtPoint(e.getPoint());
						 int col= table.columnAtPoint(e.getPoint());
						 JOptionPane.showMessageDialog(null," Value in the cell clicked :"+ " "+
						 table.getValueAt(row,col).toString());
						 System.out.println("Value in the cell clicked :"+ " "+table.getValueAt(row,col).toString());
					 }
				});
			}
			else  {
				table=new JTable(this.matrix,p);
				 table.addMouseListener(new java.awt.event.MouseAdapter(){
					 public void mouseClicked(java.awt.event.MouseEvent e){
						 int row=table.rowAtPoint(e.getPoint());
						 int col= table.columnAtPoint(e.getPoint());
						 JOptionPane.showMessageDialog(null," Value in the cell clicked :"+ " "+
						 table.getValueAt(row,col).toString());
						 System.out.println("Value in the cell clicked :"+ " "+table.getValueAt(row,col).toString());
					 }
				});
			}
			
			scrollPane = new JScrollPane(table,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			frame.add(scrollPane);
			frame.pack();
		}
	
		
	    public BuildJTable(Object[][] matrix2, String[] a2) {
			// TODO Auto-generated constructor stub
		}


		public static Object[][] makeTable(ArrayList<?> list) {
	  
			Object[][] data = new Object[list.size()][3];
			int line=0,column=0;
			for(Object o: list) {
				Field[] fields;
				if(o.getClass()==Person.class) {
					fields = o.getClass().getDeclaredFields();
				}
				else {
					fields = o.getClass().getSuperclass().getDeclaredFields();
				}
				column=0;
				for(Field f : fields) {
					if (!f.toGenericString().contains("private")) {
					f.setAccessible(true);
					Object value;
					try {
						value = f.get(o);
						data[line][column]=value;
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
					column++;
					}
				}
				line++;
			}
			return data;
	    	
	    }
	  
	      
}
