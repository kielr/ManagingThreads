package pw1;

class MyRunnable4 implements Runnable {
	CyclicBarrier myBarrier = new CyclicBarrier(4);
	public void run ()
	{
		System.out.println("I am a thread, I will now do stuff!");
		myBarrier.await();
		System.out.println("I am a thread, we've all been let free at the same time!");
	}
}

class CyclicBarrierDemo {
	public static void main(String[] args)
	{
		MyRunnable4 MyCode = new MyRunnable4();
		Thread ThreadA = new Thread(MyCode);
		Thread ThreadB = new Thread(MyCode);
		Thread ThreadC = new Thread(MyCode);
		Thread ThreadD = new Thread(MyCode);
		
		ThreadA.start();
		ThreadB.start();
		ThreadC.start();
		ThreadD.start();
		
		return;
	}
}
