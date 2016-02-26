package pw1;

public class Semaphore {
	private int keys;
	private int maxKeys;
	
	int GetKeys()
	{
		return this.keys;
	}
	
	//Constructor for Semaphore class
	//There will be a constructor that takes an integer
	//that is the number of keys that the semaphore has.
	Semaphore(int nkeys)
	{
		this.keys = nkeys;
		this.maxKeys = nkeys;
	}
	
	//This function will give permission to execute to one thread if there
	//are enough keys left. If there are no keys left, then the thread needs to sleep
	//If there are keys, decrement the total number of keys, and let it continue
	synchronized void acquire(int n)
	{
		if(n > this.maxKeys)
		{
			System.out.println("You can't take that many keys!");
			return;
		}
		while(this.keys == 0 || n > this.keys) //If we don't have enough keys
		{
			try //The thread must wait
			{
				System.out.println("No keys left! Sleeping...");
				wait();
			} catch(InterruptedException e){} //Have to do this or Java yells at you
		}
		
		//At this point we should have enough keys, so take them.
		this.keys -= n;
		//notifyAll();
	}
	
	synchronized void acquire()
	{
		while(this.keys == 0) //If we don't have enough keys
		{
			try //The thread must wait
			{
				System.out.println("No keys left! Sleeping...");
				wait();
			} catch(InterruptedException e){} //Have to do this or Java yells at you
		}
		
		//At this point we should have enough keys, so take them.
		this.keys -= 1;
	}
	//This function releases n keys to the classes key count
	synchronized void release(int n)
	{
		//Make sure that we are not releasing an invalid number of keys
		if(n > this.maxKeys)
		{
			System.out.println("Amount of keys attempted to release is more than maxKeys");
			return;
		}
		if((this.keys + n) > maxKeys)
		{
			System.out.println("Unexpected error has occured");
			return;
		}
		this.keys += n;
		notifyAll();
	}
	
	synchronized void release()
	{
		//Make sure that we are not releasing an invalid number of keys
		if((this.keys + 1) > maxKeys)
		{
			System.out.println("Unexpected error has occured");
			return;
		}
		this.keys += 1;
		notifyAll();
	}
}
