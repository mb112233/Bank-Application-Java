package bankApplication;

public interface BankInterface {
	 
		/*
		 * @pre p!=null
		 * @post accounts.size()++
		 */
		public void addClient(Person p);
		
		/*
		 * @pre account!=null
		 * @post accounts.size()--
		 */
		public void deleteClient(Person p);
	
		/*
		 * @pre account!=null
		 * @post !accountSet.isEmpty() && accounts.size()++
		 */
		public void addAccount(String id,Account a);
		
		/*
		 * @pre account!=null
		 * @post !accountSet.isEmpty() && accounts.size()--
		 */
		public void deleteAccount(String id,Account a);
		
		/*
		 * @pre id!=null,IBAN !=null, sum>0
		 * @post !accountSet.isEmpty() && accounts.size()++
		 */
		public void deposit(String id, String IBAN,double sum);
		
		/*
		 * @pre id!=null,IBAN !=null, sum>0
		 * @post !accountSet.isEmpty() && accounts.size()--
		 */
		public void withdraw(String id,String IBAN,double sum);
		
		public boolean isWellFormed();
		
}
