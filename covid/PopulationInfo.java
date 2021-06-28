package covid;

import java.util.Set;
import java.util.TreeSet;

public class PopulationInfo implements COVIDInfo {

	private int minPopulation;
	private int maxPopulation;

	public PopulationInfo(int minPopulation, int maxPopulation) {
		this.minPopulation = minPopulation;
		this.maxPopulation = maxPopulation;
	}

	@Override
	public Set<String> getInfo(COVIDMap map) {
		Set<String> res = new TreeSet<>();
		for (HealthDistrict hd : map.getDistricts()) {
			if (hd.getPopulation() >= minPopulation && hd.getPopulation() <= maxPopulation) {
				res.add(hd.getName());
			}
		}

		return res;
	}

}
