package bankApplication;


import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class GUI {
	private JFrame frame;
	private JTabbedPane tabPanel;
	String [] p= {"name","CNP","age"};
	String [] a= {"accountId","currentSold","typeOfAcc"};
	private static JButton bp1,bp2,bp3,bp4,bp5;
	private static JTextField tp1,tp2,tp3,tp4;
	private static JLabel lp1,lp2,lp3,lp4;
	
	
	private static JButton ba1,ba2,ba3;
	@SuppressWarnings("rawtypes")
	private static JComboBox pa1;
	private static JTextField ta1,ta2,ta4,ta5;
	private static JLabel la1,la2,la3,la4,la5;
	
	private static JButton bo1;
	@SuppressWarnings("rawtypes")
	private static JComboBox po2;
	private static JLabel lo1,lo3,lo4,lo5;
	private static JTextField to1,to2,to3;
	
	 static Bank b;
	static ArrayList<Account> accountSaArray=new ArrayList<Account>();
	static ArrayList<Account> accountSpArray=new ArrayList<Account>();
	static ArrayList<Account> accountArray=new ArrayList<Account>();
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public GUI() {
		 b=new Bank();
		 String [] accountType= {"saving","spending"};
		 String [] operationType= {"deposit","withdraw"};
		 tabPanel=new JTabbedPane();
		 bp1=new JButton("Add");
		 bp2=new JButton("Delete");
		 bp3=new JButton("View");
		 bp4=new JButton("View All");
		 bp5=new JButton("Update");
		 tp1=new JTextField();
		 tp2=new JTextField();
		 tp3=new JTextField();
		 tp4=new JTextField();
		 lp1=new JLabel("Name");
		 lp2=new JLabel("CNP");
		 lp3= new JLabel("Age");
		 lp4=new JLabel("Insert client CNP for update ");
		
		 ba1=new JButton("Add");
		 ba2=new JButton("Delete");
		 ba3=new JButton("Update");
		 ta1=new JTextField();
		 ta2=new JTextField();
		 ta4=new JTextField();
		 ta5=new JTextField();
		 la1=new JLabel("IBAN");
		 la2=new JLabel("Curent Sold");
		 la3= new JLabel("Type of Account");
		 la4=new JLabel("Insert main holder's CNP");
		 la5=new JLabel("Insert account's IBAN for update");
		 pa1=new JComboBox(accountType);
		 
		 bo1=new JButton("Perform transaction");
		 po2=new JComboBox(operationType);
		 to1=new JTextField();
		 to2=new JTextField();
		 to3=new JTextField();
		 lo1=new JLabel("Insert main holder's CNP");
		 lo3=new JLabel("Select type of transaction");
		 lo4=new JLabel("Insert amount of money");
		 lo5=new JLabel("Insert account's IBAN");
		 
		frame = new JFrame("Bank");
		frame.setBounds(300,500,450,300);
		frame.setVisible(true);
		frame.setResizable(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel personPanel=GUI.personPanel();
		JPanel accountPanel=GUI.accountPanel();
		JPanel operationPanel=GUI.operationPanel();
		
		tabPanel.addTab("Person", personPanel);
		tabPanel.addTab("Account", accountPanel);
		tabPanel.addTab("Transaction", operationPanel);
		
		frame.add(tabPanel);
		
	}
	
	public static JPanel personPanel() {
		JPanel p1=new JPanel();
		p1.setLayout(new BoxLayout(p1,BoxLayout.Y_AXIS));
		 
		p1.add(lp1);
		p1.add(tp1);
		p1.add(lp2);
		p1.add(tp2);
		p1.add(lp3);
		p1.add(tp3);
		p1.add(lp4);
		p1.add(tp4);
		
		JPanel p2=new JPanel();
		FlowLayout layout=new FlowLayout();
		p2.setLayout(layout);
		
		bp1.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				 if (e.getActionCommand().equals("Add")){
					String name=tp1.getText();
					String id=tp2.getText();
					int age=Integer.parseInt(tp3.getText());
					 
					Person p=new Person(name,id,age);
					
					b.addClient(p);
					
				 }
		    }
		});	
		bp2.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				 if (e.getActionCommand().equals("Delete")){
					 String name=tp1.getText();
					 String id=tp2.getText();
					 int age=Integer.parseInt(tp3.getText());
						 
					 Person p=new Person(name,id,age);
					 Bank b=new Bank();
					 b.deleteClient(p);
				 }
		    }
		});	
		bp3.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed (ActionEvent e) {
				 if (e.getActionCommand().equals("View")){
					
					 ArrayList<Person> persons=new ArrayList<Person>();
					 for (Person i:b.accounts.keySet()) {
						 persons.add(i);
					 }
				
					 new BuildJTable(persons);
				 }
		    }
		});	
		bp4.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed (ActionEvent e) {
				 if (e.getActionCommand().equals("View All")){
					 String id=tp2.getText();
					 
					 ArrayList<Account> accountSet=b.viewAll(id);
					 new BuildJTable(accountSet);
				 }
		    }
		});	
		bp5.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed (ActionEvent e) {
				 if (e.getActionCommand().equals("Update")){
					String name=tp1.getText();
					String id=tp2.getText();
					int age=Integer.parseInt(tp3.getText());
					String idForUpdate=tp4.getText();
					
					Person p=new Person(name,id,age);
					b.updateClient(idForUpdate,p);
					
				 }
		    }
		});	
		
		p2.add(bp1);
		p2.add(bp2);
		p2.add(bp3);
		p2.add(bp4);
		p2.add(bp5);
		
		JPanel p=new JPanel();
		p.setLayout(new BoxLayout(p,BoxLayout.Y_AXIS));
		p.add(p1);
		p.add(p2);
		return p;
		 
	}
	
	public static JPanel accountPanel() {
		JPanel p1=new JPanel();
		p1.setLayout(new BoxLayout(p1,BoxLayout.Y_AXIS));
		 
		p1.add(la1);
		p1.add(ta1);
		p1.add(la2);
		p1.add(ta2);
		p1.add(la3);
		p1.add(pa1);
		p1.add(la4);
		p1.add(ta4);
		p1.add(la5);
		p1.add(ta5);
		
		JPanel p2=new JPanel();
		FlowLayout layout=new FlowLayout();
		p2.setLayout(layout);
		
		ba1.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				 if (e.getActionCommand().equals("Add")){
					 String IBAN=ta1.getText();
					 double money=Double.parseDouble(ta2.getText());
					 String id=ta4.getText();
					 String accType=(String)pa1.getSelectedItem();
					
					 
					 if (accType.equals("saving")) {
						 Account sa=new SavingAccount(IBAN,money,accType);
						 b.addAccount(id, sa);
					 }
					 else if (accType.equals("spending")) {
						 Account sp=new SpendingAccount(IBAN,money,accType);
						 b.addAccount(id, sp);
					 }
					 
					 
					
				 }
		    }
		});	
		ba2.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed (ActionEvent e) {
				 if (e.getActionCommand().equals("Delete")){
					 String id =ta4.getText();
					 String IBAN=ta1.getText();
					 double money=Double.parseDouble(ta2.getText());
					 String accType=(String)pa1.getSelectedItem();
					 
					 for (Map.Entry<Person,HashSet<Account>> entry:b.accounts.entrySet()) {
						 if (entry.getKey().getId().equals(id)) {
							 if (accType.equals("saving")) {
								 Account sa=new SavingAccount(IBAN,money,accType);
								 b.deleteAccount(id, sa);
							 }
							 else if (accType.equals("spending")) {
								 Account sp=new SpendingAccount(IBAN,money,accType);
								 b.deleteAccount(id, sp);
							 }
						 }
					 }
				 }
		    }
		});	
		ba2.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed (ActionEvent e) {
				 if (e.getActionCommand().equals("Delete")){
					 String id =ta4.getText();
					 String IBAN=ta1.getText();
					 double money=Double.parseDouble(ta2.getText());
					 String accType=(String)pa1.getSelectedItem();
					 
					 for (Map.Entry<Person,HashSet<Account>> entry:b.accounts.entrySet()) {
						 if (entry.getKey().getId().equals(id)) {
							 if (accType.equals("saving")) {
								 Account sa=new SavingAccount(IBAN,money,accType);
								 b.deleteAccount(id, sa);
							 }
							 else if (accType.equals("spending")) {
								 Account sp=new SpendingAccount(IBAN,money,accType);
								 b.deleteAccount(id, sp);
							 }
						 }
					 }
				 }
		    }
		});	
	
		
		ba3.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed (ActionEvent e) {
				 if (e.getActionCommand().equals("Update")){
					 String id =ta4.getText();
					 String IBAN=ta1.getText();
					 double money=Double.parseDouble(ta2.getText());
					 String accType=(String)pa1.getSelectedItem();
					 String ibanForUpdate=ta5.getText();
					 
					 for (Map.Entry<Person,HashSet<Account>> entry:b.accounts.entrySet()) {
						 if (entry.getKey().getId().equals(id)) {
							 if (accType.equals("saving")) {
								 Account sa=new SavingAccount(IBAN,money,accType);
								 b.updateAccount(id,ibanForUpdate, sa);
							 }
							 else if (accType.equals("spending")) {
								 Account sp=new SpendingAccount(IBAN,money,accType);
								 b.updateAccount(id,ibanForUpdate,sp);
							 }
						 }
					 }
				 }
		    }
		});	
	
		
	
		
		p2.add(ba1);
		p2.add(ba2);
		p2.add(ba3);
		
		
		JPanel p=new JPanel();
		p.setLayout(new BoxLayout(p,BoxLayout.Y_AXIS));
		p.add(p1);
		p.add(p2);
		return p;
		 
	}
	
	public static JPanel operationPanel() {
		JPanel p1=new JPanel();
		p1.setLayout(new BoxLayout(p1,BoxLayout.Y_AXIS));
		 
		bo1.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				 if (e.getActionCommand().equals("Perform transaction")){
					String id=to1.getText();
					double money=Double.parseDouble(to2.getText());
					String IBAN=to3.getText();
					
					String opType=(String)po2.getSelectedItem();
					
					if (opType.equals("deposit")) {
						b.deposit(id,IBAN, money);
					}
					else if (opType.equals("withdraw")){
						b.withdraw(id,IBAN, money);
					}
					
				 }
		    }
		});	
		
		
		p1.add(lo1);
		p1.add(to1);
		p1.add(lo5);
		p1.add(to3);
		p1.add(lo4);
		p1.add(to2);
		p1.add(lo3);
		p1.add(po2);
		
		JPanel p2=new JPanel();
		FlowLayout layout=new FlowLayout();
		p2.setLayout(layout);
		
		p2.add(bo1);
		
		JPanel p=new JPanel();
		p.setLayout(new BoxLayout(p,BoxLayout.Y_AXIS));
		p.add(p1);
		p.add(p2);
		return p;
		 
	}
	
	
	public static void main(String[] args) {
		new GUI();
	}
}

