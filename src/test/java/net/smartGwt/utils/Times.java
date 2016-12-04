package net.smartGwt.utils;

public class Times {
	public Long Last = null;
	public Long SecondToLast = null;

	@Override
	public String toString() {
		return SecondToLast + ", " + Last;
	}

}
