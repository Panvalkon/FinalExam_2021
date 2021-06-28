import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import covid.COVIDException;
import covid.HealthDistrict;

public class TestHealthDistrict {
	public static void main(String[] args) {
		
		
		/*HealthDistrict h1 = new HealthDistrict("La Vega", 110176, 0);
		HealthDistrict h2 = new HealthDistrict("Axarqu�a", 170141, 0);
		HealthDistrict h3 = new HealthDistrict("Valle del Guadalhorce", 156298, 0);
		HealthDistrict h4 = new HealthDistrict("Costa del Sol", 560785, 0);
		HealthDistrict h5 = new HealthDistrict("M�laga Distrito", 633521, 0);
		HealthDistrict h6 = new HealthDistrict("Serran�a", 54999, 0);
		*/
		System.out.println(args.length);
		Set<HealthDistrict> set = new TreeSet<>();
		for (int i = 0; i< args.length; i++) {
			String s = args[i];
			try(Scanner sc = new Scanner(s)){
				sc.useDelimiter("[: ]+");
				String distr = sc.next();
				int pop = Integer.parseInt(sc.next());
				set.add(new HealthDistrict(distr, pop, 0));
			} catch(NoSuchElementException e) {
				System.err.append(s + "Not	enough	values	are	provided	as	arguments	to	the	main	function" );
			} catch (NumberFormatException e) {
				System.err.append(s + "Some	of	the	entered	values	do	not	correspond	to	an	integer	number");
			} catch (COVIDException e) {
				System.err.append(s + "Some	of	the	entered	values	are	negative.");
			}
			System.out.println(set.toString());
		}
		
		
		
		
	}

}
