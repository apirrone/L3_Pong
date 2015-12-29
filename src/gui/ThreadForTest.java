/*package gui;

public class ThreadForTest extends Thread{
	
	private double x1, x2, y1, y2;
	private boolean test;
	
	public ThreadForTest (){
		test=false;
	}
	
	public void setValue(double x1, double x2, double y1, double y2, boolean test){
		this.x1=x1;
		this.x2=x2;
		this.y1=y1;
		this.y2=y2;
		this.test=test;
	}
	
	public void run(){
		while(true){
			if(test){
				try {
					if (x1!=x2 || y1 != y2)
						System.out.println("CHEATER !!!!");
					Thread.sleep(10);
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
					break;
				}
				test=false;
			}
		}
	}
}
*/