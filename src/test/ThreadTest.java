package test;

public class ThreadTest {
	
	public static void main(String[] args) {
		 
		 final int time_over = 10000;
		new Thread(new Runnable(){

			@Override
			public void run() {
				int run_time = 0;
				boolean flag = true;
				// TODO Auto-generated method stub
				while(flag){
					System.out.println("========running=========");
					try {
						Thread.sleep(1000);
						run_time += 1000;
						if(run_time >= time_over){
							flag = false;
						}
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				System.out.println("========warning!!!======");
			}}).start();
	}
}
