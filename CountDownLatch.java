package pw1;

//The idea of a CountDownLatch is pretty simple
public class CountDownLatch {
	int events; //We need an attribute that denotes the amount of events we are waiting on
	CountDownLatch(int n)
	{
		this.events = n;
	}
	
	//The count down function will just decrement the events field signifying that an event just happened
	//We need to notify asleep threads that this happened in case it is 0.
	synchronized void countdown()
	{
		System.out.println("Event just passed..");
		this.events--;
		notifyAll();
	}
	
	//The await function will just make the current thread wait until events is 0.
	synchronized void await()
	{
		while (this.events != 0)
		{
			try 
			{ 
				System.out.println("Still waiting on other threads...");
				wait();
			} 
			catch (InterruptedException e) {}
		}
		System.out.println("Waking up");
		return;
	}
}