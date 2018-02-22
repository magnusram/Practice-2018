package my.samples;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Generics {

	public static void main(String[] args) {
		List <Number> ls = new ArrayList<Number>();
		ArrayList <Double> ls1 = new ArrayList<Double>();
		ls1.add(1.0);
		ls.add(1);
		ls.add(1.0);
		
		System.out.println("Is any match: " + ls.stream().anyMatch(e -> e.equals(1)));
		System.out.println("Is all match: " + ls.stream().allMatch(e -> e.equals(1)));
		
		System.out.println("Matching ones " + ls.stream().filter(e -> e.equals(1)).collect(Collectors.toList()));
		
        genericTest(ls);
        genericTest(ls1);

	}
	
	private static <T extends Number>  void  genericTest(List<T> ints){
		System.out.println(ints);
	}

}
