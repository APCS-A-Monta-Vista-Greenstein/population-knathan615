import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
/**
 *	Population - 
 *  Program that sorts the populations of over 30,000 USA cities
    to process various statistics requested by the user.
 *	Requires FileUtils and Prompt classes.
 *  Uses the SortMethods class as a sorting utility
 *
 *	@author	Krishna Nathan
 *	@since	January 9, 2023
 */
public class Population {
	
	// List of cities
	private List<City> cities;

	// sorting utility
	SortMethods sorter; 
	
	// US data file
	private final String DATA_FILE = "usPopData2017.txt";

  public Population()
  {
    cities = new ArrayList<City>();
    sorter = new SortMethods();
    loadData();
  }
	public static void main(String [] args)
  {
    Population pop = new Population();
    pop.run();
  }
  /**
   * Drives the execution of the program 
   */
  public void run()
  {
    printIntroduction();
    int choice = 0;
    long timeElapsed = 0;
    while(choice != 9)
    {
      printMenu();  
      do
      {
        choice = Prompt.getInt("Enter selection");
      }
      while((choice < 1 || choice > 6) && choice != 9);
      System.out.println();
      if(choice == 1)
      {
        System.out.println("Fifty least populous cities");
        timeElapsed = sorter.sortPopulationAscending(cities);
        printCities(cities);
        System.out.println("Elapsed Time "+timeElapsed+" milliseconds");
      }
      else if(choice == 2)
      {
        System.out.println("Fifty most populous cities");
        timeElapsed = sorter.sortPopulationDescending(cities, 0, cities.size()-1);
        printCities(cities);
        System.out.println();
        System.out.println("Elapsed Time "+timeElapsed+" milliseconds");
      }
      else if(choice == 3)
      {
        System.out.println("Fifty cities sorted by name");
        timeElapsed = sorter.sortNameAscending(cities);
        printCities(cities);
        System.out.println();
        System.out.println("Elapsed Time "+timeElapsed+" milliseconds");
      }
      else if(choice == 4)
      {
        System.out.println("Fifty cities sorted by name descending");
        timeElapsed = sorter.sortNameDescending(cities, 0, cities.size()-1);
        printCities(cities);
        System.out.println();
        System.out.println("Elapsed Time "+timeElapsed+" milliseconds");
      }
      else if(choice == 5)
      {
        
      }
      else 
      {
        
      }
      System.out.println();
    }
  }
  /**
   * Reads from the usPopData2017.txt file to load data into the cities list
   */
  private void loadData()
  {
    Scanner reader = FileUtils.openToRead(DATA_FILE);
    reader.useDelimiter("[\t\n]");
    while(reader.hasNext())
    {
      /* Using the delimiter, grabs each set of information for a city */
      String name = reader.next();
      String state = reader.next();
      String type = reader.next();
      int pop = Integer.parseInt(reader.next());
      cities.add(new City(state, name, type, pop));
    }
  }
  /**
   * finds and returns the 50 most populous cities in a chosen state
   * @param  chosenState the state chosen by the user
   * @return a list with all of the most populous cities
   */
  public List<City> findPopulousCities(String chosenState)
  {
    List<City> result = new ArrayList<City>();
    sorter.sortPopulationDescending(cities, 0, cities.size()-1);
    for(int i=0; (i < cities.size()) && (result.size() < 50); i++)
    {
      /* If the current state name equals the chosen state */
      if(cities.get(i).getState().equalsIgnoreCase(chosenState))
        result.add(cities.get(i));
    }
    return result;
  }
  /**
   * finds and returns all cities given a chosen city name
   * @param chosenName  the name chosen by the user
   * @return a list with all cities that share that name
   */
  public List<City> findCitiesByName(String chosenName)
  {
    List<City> result = new ArrayList<City>();
    sorter.sortPopulationDescending(cities, 0, cities.size()-1);
    for(int i=0; (i < cities.size()) && (result.size() < 50); i++)
    {
      /* If the current city name equals the chosen city name */
      if(cities.get(i).getName().equalsIgnoreCase(chosenName))
        result.add(cities.get(i));
    }
    return result;
  }
  /** 
   * Prints the first 50 cities in a list of all cities 
   * @param  allCities the entire list of cities
   */
  public void printCities(List<City> allCities)
  {
    System.out.printf("      %-22s %-22s %-12s %12s", "State", "City", "Type", "Population");
    System.out.println();
    for(int i=0; i < 50 && i < allCities.size(); i++)
    {
      System.out.printf("%5s ", ((i+1)+":"));
      System.out.print(allCities.get(i));
      System.out.println();
    }
  }
	/**	Prints the introduction to Population */
	public void printIntroduction() {
		System.out.println("   ___                  _       _   _");
		System.out.println("  / _ \\___  _ __  _   _| | __ _| |_(_) ___  _ __ ");
		System.out.println(" / /_)/ _ \\| '_ \\| | | | |/ _` | __| |/ _ \\| '_ \\ ");
		System.out.println("/ ___/ (_) | |_) | |_| | | (_| | |_| | (_) | | | |");
		System.out.println("\\/    \\___/| .__/ \\__,_|_|\\__,_|\\__|_|\\___/|_| |_|");
		System.out.println("           |_|");
		System.out.println();
	}
	
	/**	Print out the choices for population sorting */
	public void printMenu() {
		System.out.println("1. Fifty least populous cities in USA (Selection Sort)");
		System.out.println("2. Fifty most populous cities in USA (Merge Sort)");
		System.out.println("3. First fifty cities sorted by name (Insertion Sort)");
		System.out.println("4. Last fifty cities sorted by name descending (Merge Sort)");
		System.out.println("5. Fifty most populous cities in named state");
		System.out.println("6. All cities matching a name sorted by population");
		System.out.println("9. Quit");
	}
	
}
