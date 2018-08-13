
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class CollectionDemo1 {

	public static void main(String[] args) {
		List<Integer> values=new ArrayList();
		values.add(404);
		values.add(502);
		values.add(609);
		//values.add(0, 1);
		Comparator<Integer> c=new Comparator<Integer>()
				{
					public int compare(Integer i,Integer j)
					{
						if(i%10>j%10)
							return 1;
						
					
					else
					{
						return -1;
					}
					}
				};
		Collections.sort(values, c);
		Collections.reverse(values);
		Collections.shuffle(values);
		
		Iterator it=values.iterator();
		
		while(it.hasNext())
		{
			System.out.println(it.next());
		}
		for(Object o :values){
			System.out.println(o);
			
		}
		

	}

}
