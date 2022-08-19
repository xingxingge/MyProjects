package dizoo.std.collectionframe;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class ForEach {
	public static void main(String[] args){
		Collection<Integer> c = new ArrayList<Integer>();
		c.add(1);
		c.add(2);
		c.add(3);
		c.add(4);
		Iterator<Integer> itr = c.iterator();
		
		//删除2和3元素
		while (itr.hasNext()) {
			int n = itr.next();
			if (n==2 || n==3) {
				itr.remove();
			}
		}
		for (Integer n: c){
			System.out.println(n);
		}
		
	}
}
