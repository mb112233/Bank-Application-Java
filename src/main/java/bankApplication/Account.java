package bankApplication;


import java.io.Serializable;
import java.util.ArrayList;


public abstract class Account implements Serializable,ObservableInterface {
	
 
	public String accountID;
    public double currentSold;
    public String typeOfAccount;
    private ArrayList<ObserverInterface> observers=new ArrayList<ObserverInterface>();
    
	protected Account(String accountID, double currentSold, String typeOfAccount) {
		
		this.accountID=accountID;
		this.currentSold = currentSold;
		this.typeOfAccount = typeOfAccount;
	}
	
	public abstract void deposit(double money);
	public abstract void withdraw(double money);
	
	public String getAccountID() {
		return accountID;
	}
	public void setAccountID(String accountID) {
		this.accountID = accountID;
	}
	public double getCurrentSold() {
		return currentSold;
	}
	public void setCurrentSold(double currentSold) {
		this.currentSold = currentSold;
	}
	public String getTypeOfAccount() {
		return typeOfAccount;
	}
	public void setTypeOfAccount(String typeOfAccount) {
		this.typeOfAccount = typeOfAccount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountID == null) ? 0 : accountID.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (accountID == null) {
			if (other.accountID != null)
				return false;
		} else if (!accountID.equals(other.accountID))
			return false;
		return true;
	}
	public void addObserver(ObserverInterface o) {
		this.observers.add(o);
	}
	public void removeObserver(ObserverInterface o) {
		this.observers.remove(o);
	}
	public void notifyObserver() {
		for (ObserverInterface i:observers) {
			i.updateObserver(this);
		}
	}
    
}
