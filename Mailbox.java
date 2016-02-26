package pw1;

public class Mailbox {
	int box;
	//The mailbox will start off empty. In this case I will have an integer called box
	//represent the fullness of the mailbox. If box is 0 it is empty, if it is 1, then it is full.
	Mailbox()
	{
		this.box = 0;
	}
	
	//Put waits if the box is full (1 in this case), if it isn't full then make it full and notifyAll()
	synchronized void put()
	{
		while(box == 1)
		{
			try
			{
				System.out.println("Box is full, waiting!");
				wait();
			}
			catch (InterruptedException ignored){}
		}
		//If we get here then we should be able to put() safely
		this.box++;
		notifyAll();
	}
	
	//Take waits if the box is empty, if it isn't empty then make it empty and notifyAll()
	synchronized void take()
	{
		while(box == 0)
		{
			try
			{
				System.out.println("Nothing to take, waiting!");
				wait();
			}
			catch (InterruptedException ignored) {}
		}
		//If we get here then we should be able to take() safely
		this.box--;
		notifyAll();
	}
}
