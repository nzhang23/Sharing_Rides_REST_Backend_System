package sar;

public class NullRatings extends ratings {

	public NullRatings() {
	}
	
	@Override
    public boolean isNull() {
        return true;
    }
}
