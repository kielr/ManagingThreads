package pw1;


/*CyclicBarrier is described in section 5.5.4. The constructor initializes a counter. The await()
method decrements the counter and waits until the counter reaches zero at which point all waiting
threads are released, the counter is reset to its initial value, and subsequent calls to await again
block until the counter reaches 0. You do not need to implement the handling of timeouts and
interruptions described in the text nor do you need to implement the barrier action feature
described in the first line of p. 101.) Note: my Race program for assignment 1 contained a barrier
implementation but it does not fully meet the re-usability requirements for this assignment. You
may use it as a starting point if you wish.
*/
public class CyclicBarrier {
	int counter;
	int savedcounter;
	boolean exitFlag;
	
	//The constructor merely takes an integer and 
	CyclicBarrier(int n)
	{
		this.counter = n;
		this.savedcounter = n;
		this.exitFlag = false;
	}
	
	//The await function will decrement the counter and wait if it has to.
	//If it doesn't have to wait anymore, reset the counter.
	//The point of this whole class lies with this function.
	//Once counter hits 0, then every thread waiting should be woken up.
	synchronized void await()
	{
		this.counter--;
		notifyAll();
		while(this.counter != 0)
		{
			try
			{
				wait();
			}
			catch (InterruptedException ignored) {}
			
			if(exitFlag == true)
			{
				exitFlag = false;
				break;
			}
		}
		if(this.exitFlag == false)
		{
			this.exitFlag = true;
			this.counter = this.savedcounter;	
		}
	}
}
