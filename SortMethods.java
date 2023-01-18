import java.util.List;
import java.util.ArrayList;
/**
 *	SortMethods - Sorts an array of Cities as a utility for population
 *  
 *
 *	@author Krishna Nathan
 *	@since  January 9, 2023
 */
public class SortMethods {

	/**
	 *	Swaps two City objects in array arr
	 *	@param cities		list of City objects
	 *	@param x		index of first object to swap
	 *	@param y		index of second object to swap
	 */
	private void swap(List<City> cities, int x, int y) 
  {
    City temp = cities.get(x);
    cities.set(x, cities.get(y));
    cities.set(y, temp);
  }
	/**
	 *	Selection Sort algorithm - city population in ascending order
	 *	@param cities		list of City objects to sort
   *  @return the elapsed time for the sort
	 */
	public long sortPopulationAscending(List<City> cities) 
  {
    long startMillisec = System.currentTimeMillis();
    for(int outer = cities.size()-1; outer >= 1; outer--)
    {
      int maxSoFar= 0;
      for(int inner = 0; inner <= outer; inner++)
      {
        City current = cities.get(inner);
        City maxPop = cities.get(maxSoFar);
        if(current.compareTo(maxPop) > 0)
          maxSoFar = inner;
      }
      swap(cities, maxSoFar, outer);
    }
    long endMilliSec = System.currentTimeMillis();
    return endMilliSec - startMillisec;
  }
	
	/**
	 *	Insertion Sort algorithm - city name in ascending order
	 *	@param cities		list of City objects to sort
   *  @return the elapsed time for the sort
	 */
	public long sortNameAscending(List<City> cities) 
  {
    long startMillisec = System.currentTimeMillis();
    for(int outer = 1; outer < cities.size(); outer++)
    {
      boolean keepLooking = true;
      int currentSwapped = outer;
      int inner = outer-1;
      /* Keeps swapping towards the sorted part of the list until the element finds its place */
      while((inner >= 0) && cities.get(inner+1).compareNameTo(cities.get(inner)) < 0)
      {
        swap(cities, inner, inner+1);
        inner--;
      }
    }
    long endMilliSec = System.currentTimeMillis();
    return endMilliSec - startMillisec;
  }
	/**
	 *	Merge Sort algorithm - city population in descending order
	 *	@param cities		list of City objects to sort
   *  @param low  the index of the first element in a split section of the array
   *  @param high  the index of the last element in a split section of the array
   *  @return the elapsed time for the sort
	 */
	public long sortPopulationDescending(List<City> cities, int low, int high) 
  {
    long startMillisec = System.currentTimeMillis();
    /* If there is more than two elements (needed to be sorted) */
    if(high > low)
    {
      int mid = (low+high)/2;
      sortPopulationDescending(cities, low, mid);
      sortPopulationDescending(cities, mid+1, high);
      mergeDescending(cities, low, high, false);
    }
    long endMilliSec = System.currentTimeMillis();
    return endMilliSec - startMillisec;
  }
  /**
	 *	Merge Sort algorithm - city name in descending order
	 *	@param cities		list of City objects to sort
   *  @param low  the index of the first element in a split section of the array
   *  @param high  the index of the last element in a split section of the array
   *  @return the elapsed time for the sort
	 */
	public long sortNameDescending(List<City> cities, int low, int high) 
  {
    long startMillisec = System.currentTimeMillis();
    /* If there is more than two elements (needed to be sorted) */
    if(high > low)
    {
      int mid = (low+high)/2;
      sortNameDescending(cities, low, mid);
      sortNameDescending(cities, mid+1, high);
      mergeDescending(cities, low, high, true);
    }
    long endMilliSec = System.currentTimeMillis();
    return endMilliSec - startMillisec;
  }
  /**
   * Merges two sorted arrays into one larger sorted array (descending population/names)
   * @param cities  the array of the City objects to sort
   * @param low  the index of the first element in a split section of the array
   * @param high  the index of the last element in a split section of the array
   * @param sortNames  true - the list is sorted by name, false - sorted by population
   */
  private void mergeDescending(List<City> cities, int low, int high, boolean sortNames)
  {
    List<City> result = new ArrayList<City>();
    int mid = (low+high)/2;
    int left = low;
    int right = mid+1;
    while((left <= mid) && (right <= high))
    {
      City currentLeft = cities.get(left);
      City currentRight = cities.get(right);
      if(sortNames)
      {
        /* Element from the right split list is smaller */
        if(currentRight.compareNameTo(currentLeft) < 0)
        {
          result.add(currentLeft);
          left++;
        }
        /* Element from the left split list is equal or smaller */
        else
        {
          result.add(currentRight);
          right++;
        }
      }
      /* Sort by population */
      else
      {
        /* Element from the right split list is smaller */
        if(currentRight.compareTo(currentLeft) < 0)
        {
          result.add(currentLeft);
          left++;
        }
        /* Element from the left split list is equal or smaller */
        else
        {
          result.add(currentRight);
          right++;
        }
      }
    }
    /* Copy remaining elements */
    
    /* If the right split list has elements remaining */
    if(left > mid)
    {
      for(int k = right; k <= high; k++)
        result.add(cities.get(k));
    }

    /* If the left split list has elements remaining */
    if(right > high)
    {
      for(int j = left; j <= mid; j++)
        result.add(cities.get(j));
    }
    /* Copies the elements from the result list back into the original list */
    for(int i = low; i <= high; i++)
      cities.set(i, result.get(i-low));   
  }
	/*****************************************************************/
	/************************* For Testing ***************************/
	/*****************************************************************/
	
	/**
	 *	Print an array of Integers to the screen
	 *	@param arr		the array of Integers
	 */
	public void printArray(Integer[] arr) {
		if (arr.length == 0) System.out.print("(");
		else System.out.printf("( %4d", arr[0]);
		for (int a = 1; a < arr.length; a++) {
			if (a % 10 == 0) System.out.printf(",\n  %4d", arr[a]);
			else System.out.printf(", %4d", arr[a]);
		}
		System.out.println(" )");
	}
}