package pw1;

class MyRunnable implements Runnable
{
	private Semaphore mySemaphore = new Semaphore(1);
	
	public void run()
	{
		int i = 300000; //For a loop that holds the key
		mySemaphore.acquire(1);
		System.out.println("Acquired n...");
		
		//This loops just lets the thread loop for awhile... "doing work"
		while(i > 0)
		{
			i -= 1;
		}
		
		//Done with the work, release the key..
		mySemaphore.release(1);
		
		System.out.println("Done executing!");
	}
}

public class SemaphoreDemo {
	
	public static void main (String[] args)
	{
		MyRunnable myCode = new MyRunnable();
		Thread ThreadA = new Thread(myCode);
		Thread ThreadB = new Thread(myCode);
		Thread ThreadC = new Thread(myCode);
		Thread ThreadD = new Thread(myCode);
		
		System.out.println("Starting ThreadA");
		ThreadA.start();
		System.out.println("Starting ThreadB");
		ThreadB.start();
		System.out.println("Starting ThreadC");
		ThreadC.start();
		System.out.println("Starting ThreadD");
		ThreadD.start();
	}
}