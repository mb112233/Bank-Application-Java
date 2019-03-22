package bankApplication;

public class SpendingAccount extends Account  {
	
	public SpendingAccount(String accountID, double currentSold, String typeOfAccount) {
		super(accountID, currentSold, typeOfAccount);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void deposit(double moneyToDeposit) {
		double currentSold=this.getCurrentSold();
		this.setCurrentSold(currentSold+moneyToDeposit);
		super.notifyObserver();
	}

	@Override
	public void withdraw(double moneyToWithdraw) {
		
		double currentSold=this.getCurrentSold();
		if (currentSold-moneyToWithdraw>0) {
			this.setCurrentSold(currentSold-moneyToWithdraw);
			super.notifyObserver();
			System.out.println(moneyToWithdraw+" was withdraw, current sold "+this.getCurrentSold());
		}
		else {
			System.out.println("Not enough money to complete this transaction");
		}
		
	}

	public void removeObserver(ObserverInterface o) {
		// TODO Auto-generated method stub
		
	}

	public void notifyObserver() {
		// TODO Auto-generated method stub
		
	}

	public void addObserver(ObserverInterface o) {
		// TODO Auto-generated method stub
		
	}

	

}
