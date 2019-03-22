package bankApplication;

public class SavingAccount extends Account {

	

	public SavingAccount(String accountID, double currentSold, String typeOfAccount) {
		super(accountID, currentSold, typeOfAccount);
	}

	@Override
	public void deposit(double moneyToDeposit) {
		double currentSold = this.getCurrentSold();
		this.setCurrentSold(moneyToDeposit + currentSold);

		super.notifyObserver();
		System.out.println(moneyToDeposit + " was deposited in current account");

	}

	// @Override
	public void withdraw(double moneyToWithdraw) {

		double interestRate = 0.50;
		double currentSold = this.getCurrentSold();
		if (currentSold - moneyToWithdraw >= 0) {
			this.setCurrentSold(currentSold - moneyToWithdraw - interestRate);
			super.notifyObserver();
			System.out.println(moneyToWithdraw + " were withdrawed from the saving account with 0.50 interest rate applied, current sold "
					+ this.getCurrentSold());
		} else
			System.out.println("Not enough money or limit exceeded ");

	}

	public void removeObserver(ObserverInterface o) {
		// TODO Auto-generated method stub
		
	}

	public void addObserver(ObserverInterface o) {
		// TODO Auto-generated method stub
		
	}

	public void notifyObserver() {
		// TODO Auto-generated method stub
		
	}

}
