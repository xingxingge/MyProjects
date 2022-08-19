package dizoo.std.collectionframe.treeset;

/**
   @version 1.12 2012-01-26
   @author Cay Horstmann
*/

import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

/**
   This program sorts a set of item by comparing
   their descriptions.
*/
public class TreeSetTest
{  
   public static void main(String[] args)
   {  
      SortedSet<Item> parts = new TreeSet<>();
      parts.add(new Item("Toaster", 1234));
      parts.add(new Item("Widget", 4562));
      parts.add(new Item("Modem", 9912));
      System.out.println(parts);

      //自定义排序方法，这样一个对象也可以有不同的比较方法，使用匿名内部类实现
      SortedSet<Item> sortByDescription = new TreeSet<>(new
         Comparator<Item>()
         {  
            public int compare(Item a, Item b)
            {  
               String descrA = a.getDescription();
               String descrB = b.getDescription();
               return descrA.compareTo(descrB);
            }
         });

      sortByDescription.addAll(parts);
      System.out.println(sortByDescription);
   }
}
