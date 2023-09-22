package testprograms;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class TestSortingOfTimes {
	public static void main(String[] args) {
		List<String> allTimes = new ArrayList<>();
		allTimes.add("12:03 PM");
		allTimes.add("11:58 AM");
		allTimes.add("09/19/2023");
		allTimes.add("06/19/2023");
		Set<String> set = new HashSet<>();
		for (String time : allTimes) {
			set.add(time);
		}
		System.out.println(allTimes);
		System.out.println(set);
		
	}
}
