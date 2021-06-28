package covid;



public class HealthDistrict implements Comparable<HealthDistrict> {
	private String district;
	private int population;
	private int covidCases14days;
	
	
	public HealthDistrict(String district, int population, int covidCases14days) {
		if (population <= 0) {
			throw new COVIDException("Error. Negative of null population");
		}
		if (covidCases14days < 0) {
			throw new COVIDException("Error. Negative number of diagnosed cases");
		}
		this.district = district;
		this.population = population;
		this.covidCases14days = covidCases14days;
	}


	public int getCovidCases14days() {
		return covidCases14days;
	}


	public void setCovidCases14days(int covidCases14days) {
		if (covidCases14days < 0) {
			throw new COVIDException("Error. Negative number of diagnosed cases");
		}
		this.covidCases14days = covidCases14days;
	}


	public String getName() {
		return district;
	}


	public int getPopulation() {
		return population;
	}
	
	public int accumulatedIncidence() {
		int index = population/100000;
		int result = 0;
		if (index != 0) {
			result = covidCases14days/index;
		}
		return result;
	}
	
	@Override
	public boolean equals(Object o) {
		return o instanceof HealthDistrict && ((HealthDistrict) o).getName().equalsIgnoreCase(this.district);
	}
	
	@Override
	public int hashCode() {
		return this.getName().toUpperCase().hashCode();
	}

	@Override
	public int compareTo(HealthDistrict o) {		
		return this.district.compareToIgnoreCase(o.getName());
	}
	
	public String toString() {
		return "District(" + this.district + ", " + this.covidCases14days + ")";
	}


	


	
}
