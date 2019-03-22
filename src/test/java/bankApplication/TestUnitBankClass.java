package bankApplication;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Map.Entry;

import org.junit.Test;


public class TestUnitBankClass {

	@Test
	public void testAddPerson() {
		Person p1=new Person("Jackson","12211112265531",34);
		Bank b=new Bank();
		b.addClient(p1);
		Person p=null;
		
		for (Person i:b.accounts.keySet()) {
			if (i.getId().equals("12211112265531")) 
				p=i;
		}
		assertEquals(p,p1);
	}
	@Test
	public void testAddAccount() {
		Account sa=new SavingAccount("188712872",234.56,"saving");
		Account sp=new SpendingAccount("144597805",550.99,"spending");
		Account result=null;
		Person p=new Person("Deiana","12478112265531",25);
		Bank b=new Bank();
		
		b.addClient(p);
		b.addAccount(p.getId(), sa);
		HashSet<Account> account=b.accounts.get(p);
		
		for (Account i:account) {
			if (i.getAccountID().equals("188712872"))
				result=i;
		}
		assertEquals(result,sa);
	}
	@SuppressWarnings("deprecation")
	@Test
	public void testWithdraw() {
		Person p=new Person("Damian","12478112262861",26);
		Account sp=new SpendingAccount("1445977865",980.99,"spending");
		Bank b=new Bank();
		
		b.addClient(p);
		b.addAccount(p.getId(), sp);
		
		double sum=1;
		b.withdraw(p.getId(),sp.accountID, sum);
		Account hSet=b.getAnAccountFromPerson(p.getId(),sp.accountID);
		System.out.println("curent sold in hSet "+hSet.getCurrentSold());
		
		String s2=Double.toString(hSet.getCurrentSold());
		assertEquals("979.99",s2);
	}

}
