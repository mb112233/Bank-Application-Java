package bankApplication;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;



public class Bank implements BankInterface,Serializable{
	


	public static HashMap<Person,HashSet<Account>> accounts;
	String filename="out.bin";
	public Bank() {
		accounts=new HashMap<Person,HashSet<Account>>();
		try {
			deserialize();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	@SuppressWarnings("static-access")
	public void addClient(Person p) {
		
		assert isWellFormed();
		assert p!=null;
		
		HashSet<Account> acc=new HashSet<Account>();
		if (!this.accounts.containsKey(p) ){
			this.accounts.put(p, acc);
		}
		else {
			System.out.println("Person is already registered in this bank");
		}
		serialize();
	}

	@SuppressWarnings("static-access")
	public void deleteClient(Person p) {
		
		assert isWellFormed();
		assert p!=null;
		
		this.accounts.remove(p);
		serialize();
	}


	@SuppressWarnings("static-access")
	public void addAccount(String id, Account a) {
		
		assert isWellFormed();	 
		assert !id.isEmpty();
		assert a!=null;
		
		Person p=new Person("",id,0);
		this.accounts.get(p).add(a);
		a.addObserver(p);

		serialize();
	}

	@SuppressWarnings("static-access")
	public void deleteAccount(String id,Account a) {
		
		assert isWellFormed();
		assert !id.isEmpty();
		assert a!=null;
		
		Person p=new Person("",id,0);
		HashSet<Account> account=new HashSet<Account>();
		account=this.accounts.get(p);
		Iterator<Account> itr=account.iterator();
		while(itr.hasNext()) {
			  Object i=itr.next();
			  if (i.equals(a))
			  itr.remove();
			  itr.next().removeObserver(p);
		}
		this.accounts.put(p,account);
		serialize();
	}


	@SuppressWarnings("static-access")
	public void deposit(String id,String IBAN,double sum) {
		assert isWellFormed();
		assert !id.isEmpty();
		assert !IBAN.isEmpty();
		assert sum>=0;
		
		Person p=new Person("",id,0);
		HashSet<Account> accSet=this.accounts.get(p);
		for (Account i:accSet) {
			if(i.getAccountID().equals(IBAN)) {
				i.deposit(sum);
				
			}
		}
		serialize();
	}

	@SuppressWarnings("static-access")
	public void withdraw(String id, String IBAN, double sum) {
		
		assert isWellFormed();
		assert !id.isEmpty();
		assert !IBAN.isEmpty();
		assert sum>=0;
		
		Person p=new Person("",id,0);
		HashSet<Account> accSet=this.accounts.get(p);
		for (Account i:accSet) {
			if(i.getAccountID().equals(IBAN)) {
				i.withdraw(sum);
				
			}
		}
		serialize();
		
	}

	@SuppressWarnings("static-access")
	public boolean isWellFormed() {
		for (Entry<Person, HashSet<Account>> entry:this.accounts.entrySet()) {
			assert entry !=null;
			assert entry.getKey().getAge()>=18:"Person is underaged";
			assert !entry.getKey().getId().isEmpty():"Person id is empty";
			assert !entry.getKey().getName().isEmpty():"Person doesnt have a name";
			
			HashSet<Account> accountSet=entry.getValue();
			for (Account account:accountSet) {
				assert account!=null;
				assert !account.getAccountID().isEmpty():"Empty account id";
				assert account.getCurrentSold()>=0:"Empty account";
				assert !account.getTypeOfAccount().isEmpty();
			}
		}
		return true;
	}
	
	@SuppressWarnings("static-access")
	public boolean checkIfClientsExistsInBank(Person p) {
		if (this.accounts.containsKey(p)) return true;
		return false;
		
	}
	public HashMap<Person, HashSet<Account>> getAccounts() {
		return accounts;
	}
	public Account getAnAccountFromPerson(String id, String IBAN) {
		Person p=new Person("",id,0);
		HashSet<Account> set=accounts.get(p);
		Account rezult=null;
		for (Account i:set) {
			if (i.getAccountID().equals(IBAN))
				rezult=i;
		}
		return rezult;
	}
	@SuppressWarnings("static-access")
	public void setAccounts(HashMap<Person, HashSet<Account>> accounts) {
		this.accounts = accounts;
	}
	public ArrayList<Account> viewAll(String id) {
		assert !id.isEmpty();
		Person p=new Person("",id,0);
		HashSet<Account> account=accounts.get(p);
		ArrayList<Account> a=new ArrayList<Account>();
		for (Account i:account) {
			a.add(i);
		}
		return a;
	}

	public void serialize() {
		
		try {
			FileOutputStream fs=new FileOutputStream(filename);
			ObjectOutputStream os=new ObjectOutputStream(fs);
			os.writeObject(accounts);
			os.close();
			fs.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void printHashMap(HashMap<Person,HashSet<Account>> accounts) {
		for (Map.Entry<Person, HashSet<Account>> entry:accounts.entrySet()) {
			System.out.println("Person "+entry.getKey().getName()+" has register to this bank with accounts");
			HashSet<Account> setAcc=entry.getValue();
			for (Account i:setAcc) {
				System.out.println("Account of type "+i.getTypeOfAccount()+" and current sold "+i.getCurrentSold());
			}
			System.out.println();
		}
	}
	@SuppressWarnings("unchecked")
	public void deserialize() throws ClassNotFoundException {
			try {
				FileInputStream fs=new FileInputStream(filename);
				ObjectInputStream os = new ObjectInputStream(fs);
				
				accounts = (HashMap<Person, HashSet<Account>>) os.readObject();
				printHashMap(accounts);
				
				os.close();
				fs.close();
			} catch (FileNotFoundException e) {
				System.out.println("File not found");
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println("File doesnt exist");
				
			}
			
		
	}
	public void updateClient(String idForUpdate, Person p) {
		for (Map.Entry<Person, HashSet<Account>> entry:accounts.entrySet()) {
			if (entry.getKey().getId().equals(idForUpdate)) {
				entry.getKey().setId(p.getId());
				entry.getKey().setName(p.getName());
				entry.getKey().setAge(p.getAge());
			}
		}
		serialize();
		
	}
	public void updateAccount(String id, String ibanForUpdate, Account a) {
		Person p=new Person("",id,0);
		HashSet<Account> accSet=this.accounts.get(p);
		for (Account i:accSet) {
			if (i.getAccountID().equals(ibanForUpdate)) {
				i.setAccountID(a.getAccountID());
				i.setCurrentSold(a.getCurrentSold());
				i.setTypeOfAccount(a.getTypeOfAccount());
			}
		}
		serialize();
		
	}
	@SuppressWarnings({ "static-access", "unused" })
	public static void main(String[] args) throws ClassNotFoundException {
		
		Account sa=new SavingAccount("188712872",234.56,"saving");
		Account sp=new SpendingAccount("144597805",550.99,"spending");
		Account sa1=new SavingAccount("188712234",567.56,"saving");
		Account sp1=new SpendingAccount("1445977865",980.99,"spending");
		
		HashSet<Account> set=new HashSet<Account>();
		set.add(sa);
		set.add(sp);
		HashSet<Account> set1=new HashSet<Account>();
		
		set1.add(sp1);
		
		Person p=new Person("Jackson","12211112265531",34);
		Person p1=new Person("Deiana","12478112265531",25);
		Person p2=new Person("Damian","12478112262861",26);
		
		Bank b=new Bank();
		b.accounts.put(p,set);
		//b.accounts.put(p1,set1);
		//b.addClient(p2);
		// b.addAccount(p.getId(),sp1);
		//b.deleteAccount(p.getId(), sa1);
		//b.deleteClient(p);
		//b.accounts.clear();
		 //b.deposit(p.getId(), sa.getAccountID(), 1.00);
		// b.withdraw(p.getId(), sa.getAccountID(), 200);
		Person update=new Person("Jackson","345",34);
		b.updateClient(p.getId(),update);
		//Account rezult=b.getAnAccountFromPerson(p.getId(), sa.accountID);
		//System.out.println(rezult.getCurrentSold());
		for (Map.Entry<Person, HashSet<Account>> entry:b.accounts.entrySet()) {
			System.out.println(entry.getKey().getName()+" "+entry.getKey().getId()+" "+entry.getKey().getAge());
			HashSet<Account> setAcc=entry.getValue();
			for (Account i:setAcc) {
				System.out.println(i.getAccountID() +  " " +i.getTypeOfAccount()+" "+i.getCurrentSold());
			}
			System.out.println();
		}
	}





	

}
