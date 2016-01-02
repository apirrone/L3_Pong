package util;

public class ExceptionPong extends Exception {
	
	public ExceptionPong(String msg) {
		System.err.println(msg);
	}
}