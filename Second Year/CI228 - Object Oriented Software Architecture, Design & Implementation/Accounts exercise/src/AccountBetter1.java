class AccountBetter1 extends Account implements Transfer
{
	interface Transfer
	{
		public boolean transferFrom( Account from, double amount);
		public boolean transferTo( Account to, double amount);
	}
	public boolean transferFrom( Account from, double amount)
	{
		if (from.getBalance() >= amount)
		{
			this.deposit(amount);
			from.withdraw(amount);
			return true;
		}
		else
		{
			return false;
		}
	}
	public boolean transferTo( Account to, double amount)
	{
		if ((amount > 0) && (this.getBalance() >= amount))
		{
			this.withdraw(amount);
			to.deposit(amount);
			return true;
		}
		else
		{
			return false;
		}
	}
	
}
