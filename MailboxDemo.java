package pw1;

//The code below tests my Mailbox by having one thread put and another take
class MyRunnable3 implements Runnable {
	Mailbox myMailbox = new Mailbox();
	int nThreads = 0;
	public void run()
	{
		nThreads++;
		if (nThreads == 1)
		{
			myMailbox.put();
			myMailbox.put();
		
			System.out.println("Thread 1 Done!");
		}
		else
		{
			myMailbox.take();
			
			System.out.println("Thread 2 Done!");
		}
		return;
	}
}

/* a mailbox is a queue that holds at most one element, with put
and take methods. Put (respectively, take) waits if the mailbox is full (resp. empty). The
queue implementation from class with size==2 implements a mailbox, but the goal in this 
assignment is to implement the mailbox without the complication of head and tail indexes and an
array. */
public class MailboxDemo{
	public static void main (String[] args)
	{
		MyRunnable3 MyCode = new MyRunnable3();
		Thread ThreadA = new Thread(MyCode);
		Thread ThreadB = new Thread(MyCode);
		
		ThreadA.start();
		ThreadB.start();
	}
}