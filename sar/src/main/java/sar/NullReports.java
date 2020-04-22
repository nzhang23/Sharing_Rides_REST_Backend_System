package sar;

public class NullReports extends reports {
	


	
	
	public NullReports(int pid, String name) {
		super(pid, name);
		
	}

	@Override
    public boolean isNull() {
        return true;
    }
}
