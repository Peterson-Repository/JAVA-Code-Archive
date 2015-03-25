package edu.vsu.csci693;

public class Count3sWorker implements Runnable 
{
	public int threes;

	private int [][] numbers;
	private int start;
	private int lengthPerThread;
        private int arraySize;
	
        
        
       /**
        * @param numbers
        * @param thId
        * @param numberThs 
        */
	public Count3sWorker(int arraySize, int [][] numbers, int thId, int numberThs)
	{
		this.numbers = numbers;
                this.arraySize = arraySize;
		
                //System.out.println("Numbers Length " + numbers.length);
		
		//important calculation to divide work
		lengthPerThread = (arraySize)  / numberThs;
		start = thId * lengthPerThread;
                
                //System.out.println("Array Size " + arraySize);
               // System.out.println("Length Per thread " + lengthPerThread);
                //  System.out.println("Start " + start);
                
                
	}
        
                
        
	@Override
	public void run() {	
            
            
             int PrintHowMany = 5;
            //System.out.println("Test Print");
		for(int i=start; i < (start+lengthPerThread); i++){
                    for(int j=0; j < (arraySize); j++){
			numbers[i][j] = (numbers[i][j]) * 2;
                       // System.out.println(numbers[i][j]);
                        
                        
                       
                        //System.out.println("Test Print 2");
                        // for(int z=0; z<PrintHowMany; z++){
                        //    for(int q=0; z<PrintHowMany; z++){
                         //        System.out.println("Row " + (z+1) + " Column " + (q+1) + " = " + numbers[z][q] + "\n");
                        //    }
                        // }
			
                    }
                    
                }
        }
}
