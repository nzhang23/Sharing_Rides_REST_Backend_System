package sar;

public class NullAccounts extends accounts {
	
	public NullAccounts() {
		
	}

	@Override
    public boolean isNull() {
        return true;
    }
}
