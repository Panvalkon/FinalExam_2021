package covid;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

public class COVIDMap {
	private String region;
	private SortedMap<String, SortedSet<HealthDistrict>> map;

	public COVIDMap(String region, String filename) throws FileNotFoundException {
		this.region = region;
		this.map = new TreeMap<String, SortedSet<HealthDistrict>>();
		readData(filename);
	}

	public void readData(String filename) throws FileNotFoundException {
		try (Scanner sc = new Scanner(new File(filename))) {
			readData(sc);
		}

	}

	public void readData(Scanner scan) {
		while (scan.hasNextLine()) {
			String line = scan.nextLine();
			try (Scanner sc = new Scanner(line)) {
				sc.useDelimiter("[():]+");
				String distr = sc.next();
				String prov = sc.next();
				int pop = Integer.parseInt(sc.next());
				int ill = Integer.parseInt(sc.next());
				HealthDistrict hd = new HealthDistrict(distr, pop, ill);
				addDistrict(prov, hd);
			} catch (NumberFormatException | NoSuchElementException | COVIDException e) {
				// ignoring line with errors
			}
		}

	}

	public void addDistrict(String prov, HealthDistrict hd) {
		map.putIfAbsent(prov, new TreeSet<HealthDistrict>());
		if (!map.get(prov).contains(hd)) {
			map.get(prov).add(hd);
		}
	}

	public String getRegion() {
		return this.region;
	}

	public Set<String> getProvinces() {
		Set<String> prov = map.keySet();
		return prov;
	}

	public Set<HealthDistrict> getDistricts() {
		Set<HealthDistrict> hdSet = new HashSet<>();
		for (SortedSet<HealthDistrict> hd : map.values()) {
			hdSet.addAll(hd);
		}
		return hdSet;
	}

	public int incidenceOfTheProvincia(String prov) {
		int res = 0;
		Set<HealthDistrict> hdset = map.getOrDefault(prov, null);
		if (hdset != null) {
			for (HealthDistrict hd : hdset) {
				res += hd.accumulatedIncidence();
			}
		}

		return res / hdset.size();
	}

	public void printMap(String filename) throws FileNotFoundException {
		try (PrintWriter pw = new PrintWriter(filename)) {
			printMap(pw);
		}
	}

	public void printMap(PrintWriter pw) {
		StringBuilder sb = new StringBuilder();
		sb.append(this.region.toUpperCase() + ":\n");
		for (Entry<String, SortedSet<HealthDistrict>> prov : map.entrySet()) {
			sb.append(prov.getKey() + "\n");
			for (HealthDistrict hd : prov.getValue()) {
				sb.append("\t" + hd.toString() + "\n");
			}
		}
		pw.append(sb);
	}

	public Set<String> getCOVIDInfo(COVIDInfo info) {
		Set<String> res = info.getInfo(this);
		return res;
		
	}
}
