package my.samples;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Collections {
	List <String> cow = new CopyOnWriteArrayList<>();
	Iterator<String> it = cow.iterator();
	volatile int statusCount;
	public static void main(String[] args) {
		Collections c = new Collections();		
		c.copyOnWriteArrayListTest();
	}
	
	private void copyOnWriteArrayListTest(){
		ExecutorService es = Executors.newCachedThreadPool();
		
		es.execute(new TestCOWWrite());
		es.execute(new TestCOWRead());
		while (statusCount < 2){
			//System.out.println("awaiting task completion");
			if (statusCount >= 2){
				break;
			}
		}
		es.shutdown();
		System.out.println("task completed");
		
	}
	
	class TestCOWRead implements Runnable{
		TestCOWRead(){
			
		}
		
		public void run(){
			
			while(it.hasNext()){
				System.out.println(it.next());
			}
			System.out.println("Inside read");
			statusCount++;
		}
		
	}
	
	class TestCOWWrite implements Runnable{
		TestCOWWrite(){
		}
		
		public void run(){
			for(int i=0;i<10;i++){
				cow.add(String.valueOf(i));
			}
			statusCount++;
			System.out.println("Write completed");
		}
		
	}

}
