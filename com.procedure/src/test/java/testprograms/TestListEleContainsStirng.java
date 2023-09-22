package testprograms;

import java.util.ArrayList;
import java.util.List;

public class TestListEleContainsStirng {
	public static void main(String[] args) {
		List<String> l = new ArrayList<>();
		l.add("Uk Chaithanya"); l.add("Fireflink Sravani"); l.add("Fireflink Sravani");
		//l.add("Vivek Ok Google");l.add("Google Ok");l.add("Goog");l.add("Google is Good, Ok");
		String str = "Chai Sra";
		String [] strArr = null;
		boolean result=false;
		if (str.contains(" ")) {
			strArr=str.split(" ");
			for (String ele : l) {
				for (int i = 0; i < strArr.length; i++) {
					if (ele.contains(strArr[i])) {
						result = true;
						break;
					}
					else {
						result = false;
					}
				}
			}
			if (result) {
				System.out.println("Each Element Contains "+str);
			}else {
				System.out.println("Each Element not Contains "+str);
			}
		}else {
			for (String ele : l) {
				if (ele.contains(str)) {
					result = true;
					break;
				}
				else {
					result = false;
				}
			}
			if (result) {
				System.out.println("Each Element Contains "+str);
			}else {
				System.out.println("Each Element not Contains "+str);
			}
		}
	}
}
