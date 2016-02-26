package pw1;

//Below is just my test driver of the above class
class MyRunnable2 implements Runnable
{
	CountDownLatch myLatch = new CountDownLatch(2);
	public volatile int threadCount = 0;
	public void run(){
		threadCount++;
		//The first thread will get here
		if(threadCount == 1)
		{
			System.out.println("I'm thread 1! I'm fast! Waiting..");
			myLatch.await(); //Thread 1 will just wait until the other threads are done.
			
			//And then do its job:
			System.out.println("Everyone else is done being slow! Now I can finally do stuff!");
		}
		else
		{
			System.out.println("I'm a slow poke....");
			myLatch.countdown();
		}
		return;
	}
}

public class CountDownLatchDemo {
	
	static public void main(String[] args)
	{
		MyRunnable2 MyCode = new MyRunnable2();
		Thread ThreadA = new Thread(MyCode);
		Thread ThreadB = new Thread(MyCode);
		Thread ThreadC = new Thread(MyCode);
		
		ThreadA.start();
		ThreadB.start();
		ThreadC.start();
	}
}
