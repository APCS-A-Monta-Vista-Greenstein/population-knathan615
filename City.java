/**
 *	City data - the city name, state name, location designation,
 *				and population est. 2017
 *
 *	@author	Krishna Nathan
 *	@since	January 9, 2023
 */
public class City implements Comparable<City> {
	
	// fields
  
  private String state; // name of the state the city is in
  private String name; // name of the city
  private String designation; // type of the city
  private int population; // population of the city
	
	// constructor
  public City(String n, String s, String d, int pop)
  {
    name = n;
    state = s;
    designation = d;
    population = pop;
  }
	
	/**	Compare two cities populations
	 *	@param other		the other City to compare
	 *	@return				the following value:
	 *		If populations are different, then returns (this.population - other.population)
	 *		else if states are different, then returns (this.state - other.state)
	 *		else returns (this.name - other.name)
	 */
  public int compareTo(City other)
  {
    if(this.population != other.population)
      return (this.population - other.population);
    if(!(this.state.equals(other.state)))
      return this.state.compareTo(other.state);
    return this.name.compareTo(other.name);
  }

  /**	Compare two cities names
	 *	@param other		the other City to compare
	 *	@return				the following value:
	 *		this.name.compareTo(other.name)
	 */
  public int compareNameTo(City other)
  {
    return this.name.compareTo(other.name);
  }

  
	/**	Equal city name and state name
	 *	@param other		the other City to compare
	 *	@return				true if city name and state name equal; false otherwise
	 */
	
	/**	Accessor methods */
	public String getState() { return state; }
	public String getName() { return name;}
	public String getDesignation() { return designation;}
	public int getPopulation() { return population; }
	/**	toString */
	@Override
	public String toString() {
		return String.format("%-22s %-22s %-12s %,12d", state, name, designation,
						population);
	}

  /**
   * Checks if two cities are the same
   * @param other    the other City that is compared
   * @return true - the two cities share all attributes; false-otherwise
  */
  public boolean equals(City other)
  {
    boolean hasSameState = this.state.equals(other.state);
    boolean hasSameName = this.name.equals(other.name);
    boolean hasSameDesignation = this.designation.equals(other.designation);
    boolean hasSamePopulation = this.population == other.population;
    return hasSameState && hasSameName && hasSameDesignation && hasSamePopulation;
  }
}
