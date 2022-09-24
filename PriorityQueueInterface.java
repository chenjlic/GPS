//Author: Jonny Chen
//Worked with: Jerome Washika, Cole Winkhart
//Date: 12/3/2021
//Instructor: Stahr
public interface PriorityQueueInterface<T extends Comparable<? super T>> {
      boolean isEmpty();
      boolean isFull();
      void clear();
      int size();
      void add(T newEntry);
      T peek();      // return null if empty
      T remove();    // throw NoSuchElementException if empty
}

