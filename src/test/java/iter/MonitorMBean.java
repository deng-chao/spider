package iter;

public interface MonitorMBean {

	int getGrapTaskQueueSize();
	
	int getGrapingQueue();
	
	int getRunningUrlsSize();
	
	int getArticleCnt();
	
	int getIndexingQueue();
	
	String getBloomFilterSize();
	
	long getGrapedSize();
	
	void getTask(int index) throws Exception;
	
	void exit(int sign);
}
