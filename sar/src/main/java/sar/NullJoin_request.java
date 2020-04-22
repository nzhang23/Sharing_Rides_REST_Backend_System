package sar;

public class NullJoin_request extends join_request {

	public NullJoin_request() {
		
	}
	@Override
    public boolean isNull() {
        return true;
    }
}
