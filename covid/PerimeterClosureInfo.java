package covid;

import java.util.HashSet;
import java.util.Set;

public class PerimeterClosureInfo implements COVIDInfo {
	static final int CLOSURE_CASES = 500;
	
	public PerimeterClosureInfo() {}
	
	@Override
	public Set<String> getInfo(COVIDMap map) {
		Set<String> res = new HashSet<>();
		for (String prov : map.getProvinces()) {
			if (map.incidenceOfTheProvincia(prov) >= CLOSURE_CASES) {
				res.add(prov);
			}
		}
		return res;
	}

}
