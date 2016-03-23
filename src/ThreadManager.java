public class ThreadManager
{
	int	numThreads;

	public ThreadManager()
	{
		numThreads = 0;
	}
	public void addThread()
	{
		numThreads++;
	}
	public void removeThread()
	{
		numThreads--;
	}
	public int getNumThreads()
	{
		return numThreads;
	}

}
