package util;

public class ExceptionPong extends Exception {
	
	private static final long serialVersionUID = 1L;

	public ExceptionPong(String msg) {
		System.err.println(msg);
	}
}