package edu.vsu.csci693;

public class BlackScholesWorker implements Runnable {
	
	private long N;
	private int threadId;
	private int numberThreads;
	
        private long start;
	private long lengthPerThread;
        
	private StockOption option;
	
	public BlackScholesWorker(int threadId, int numberThreads, long N)
	{	
		this.threadId = threadId;
		this.numberThreads = numberThreads;
		this.N = N;
		
		option = null;
                
                //important calculation to divide work
		lengthPerThread = N / numberThreads;
		start = threadId * lengthPerThread;
	}
	
	public void setOption(StockOption option)
	{
		this.option = option;
	}
	
	public StockOption getOption()
	{
		return option;
	}
	
	@Override
	public void run() {
		if(option == null) return;
		
        double sum = 0.0;
        //for (int i = 0; i < N; i++) {
        for(long i=start; i < (start+lengthPerThread); i++){
            
            double eps = StdRandom.gaussian();
            double price = option.S * Math.exp(option.r*option.T - 
            		0.5*option.sigma*option.sigma*option.T + 
            		option.sigma*eps*Math.sqrt(option.T));
            double value = Math.max(price - option.X, 0);
            sum += value;
        }
        double mean = sum / N;
     
        option.price =  Math.exp((-option.r)*(option.T)) * mean;
        
        //System.out.println("Fair Price is " + option.price);
	}

}
