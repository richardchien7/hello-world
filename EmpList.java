package homework;

import java.io.*;
import java.util.Scanner;
import java.util.LinkedList;

public class EmpList {
	static java.util.Scanner scanner = new java.util.Scanner(System.in);
	public static LinkedList<String> linkedList = new LinkedList<String>();

	public static void main(String[] args) {
		
		printinfo();
		readData();
		System.out.println("Please enter your choice:");
		System.out.println("1:新增 2:修改 3:刪除 4:查詢 5:列出 6:離開");
		int choice = scanner.nextInt(); // read in command
		String getenter = scanner.nextLine();
		while (choice != 6) {
			if (choice == 1) {
				addNewEmp();
				System.out.println("Please enter your choice: ");
				System.out.println("1:新增 2:修改 3:刪除 4:查詢 5:列出 6:離開");
			} else if (choice == 2) {
				updateEmp();
				System.out.println("Please enter your choice: ");
				System.out.println("1:新增 2:修改 3:刪除 4:查詢 5:列出 6:離開");
			} else if (choice == 3) {
				deleteEmp();
				System.out.println("Please enter your choice: ");
				System.out.println("1:新增 2:修改 3:刪除 4:查詢 5:列出 6:離開");
			} else if (choice == 4) {
				enquiry();
				System.out.println("Please enter your choice: ");
				System.out.println("1:新增 2:修改 3:刪除 4:查詢 5:列出 6:離開");
			} else if (choice == 5) {
				listAllEmp();
				System.out.println("Please enter your choice: ");
				System.out.println("1:新增 2:修改 3:刪除 4:查詢 5:列出 6:離開");
			} else if (choice == 6) {
				System.out.println("BUILD SUCCESSFUL");
				System.exit(0);
			} else
				System.out.println("Invalid choice. Please enter your choice: ");
			choice = scanner.nextInt();
			String getenter2 = scanner.nextLine();
		}
	}

	public static void readData() {
		String nameSt;
		String stLine;
		int salary;
		Emp theNode;
		try {
			File inFile = new File("C:\\empSalary.txt");
			Scanner sc = new Scanner(inFile);
			while (sc.hasNext()) {
				stLine = sc.nextLine();
				Scanner sc2 = new Scanner(stLine);
				while (sc2.hasNext()) {
					nameSt = sc2.next();
					salary = sc2.nextInt();
					theNode = new Emp(nameSt, salary);
					compareEmp(theNode); // 找出適當位置將新資料加入串列
				}
			}
			sc.close();
		} catch (IOException e) {
			System.out.println("無檔案");
		}
	}

	public static void compareEmp(Emp theNode) {
		String name = theNode.getName();
		int number = theNode.getSalary();
		String word = name + " " + String.valueOf(number);// 名字加上數字
		int size = linkedList.size();
		int get = 0;// 確定位置的索引
		boolean confirm = false;// 判斷
		for (int i = 0; i <= size; i++) {
			if (linkedList.size() == 0) {
				linkedList.add(word);
				
				break;
			} else {
				if (i != size && linkedList.get(i).compareTo(word) < 0) {
					get++;
					confirm = true;
				}
			}
		}
		if (confirm) {
			linkedList.add(get, word);
		}
	}

	public static void printinfo() {
		System.out.println("資管二甲 406401331 簡勝弘");
	}

	public static void addNewEmp() {
		System.out.print("請輸入員工名字 薪水: ");
		String name_salary = scanner.nextLine();
		int size = linkedList.size();
		int get = 0;// 確定位置的索引
		boolean confirm = false;// 判斷
		boolean confirm_same = true;// 判斷有沒有一樣的字
		String word = "";// 接新的員工的名字
		for (int i = 0; i < name_salary.length(); i++) {
			if (!(name_salary.substring(i, i + 1).equals(" "))) {
				word += name_salary.substring(i, i + 1);
			} else {
				break;
			}
		}
		for (int j = 0; j < size; j++) {
			if (confirm_same == false) {
				break;
			}
			String compare_word = "";
			for (int i = 0; i < linkedList.get(j).length(); i++) {
				if (!(linkedList.get(j).substring(i, i + 1).equals(" "))) {
					compare_word += linkedList.get(j).substring(i, i + 1);
				} else {
					break;
				}
			}
			if (compare_word.compareToIgnoreCase(word) == 0) {
				confirm_same = false;
			}
		}
		if (confirm_same) {
			for (int i = 0; i <= size; i++) {
				if (linkedList.size() == 0) {
					linkedList.add(name_salary);
					break;
				} else {
					if (i != size && linkedList.get(i).compareTo(name_salary) < 0) {
						get++;
						confirm = true;
					}
				}
			}
		}
		if (confirm) {
			linkedList.add(get, name_salary);
		} else {
			System.out.println("已有該名字，拒絕新增!");
		}
	}

	public static void updateEmp() {
		System.out.print("請輸入員工名字 欲改的薪水: ");
		try {
		String name_salary = scanner.nextLine();
		String word = "";//接員工的名字
		boolean confirm_same = false;// 判斷是否為一樣的字
		int size = linkedList.size();
		String salary_change = "";
		for (int i = 0; i < name_salary.length(); i++) {
			if (!(name_salary.substring(i, i + 1).equals(" ")) && !(name_salary.charAt(i) >= 48 && name_salary.charAt(i) <= 57)) {
				word += name_salary.substring(i, i + 1);
			} else if(name_salary.charAt(i) >= 48 && name_salary.charAt(i) <= 57){
				salary_change += name_salary.substring(i, i + 1);
			}
		}
		
		for (int j = 0; j < size; j++) {
			if(confirm_same) {
				break;
			}
			String salary = "";
			String compare_word = "";
			for (int i = 0; i < linkedList.get(j).length(); i++) {
				if (!(linkedList.get(j).substring(i, i + 1).equals(" ")) && !(linkedList.get(j).charAt(i) >= 48 && linkedList.get(j).charAt(i) <= 57)) {
					compare_word += linkedList.get(j).substring(i, i + 1);
				}else if(linkedList.get(j).charAt(i) >= 48 && linkedList.get(j).charAt(i) <= 57){
					salary += linkedList.get(j).substring(i, i + 1);
				}
			}
			
			if (compare_word.compareToIgnoreCase(word) == 0) {
				Emp change_salary = new Emp(word, Integer.parseInt(salary));
				confirm_same = true;
				System.out.println("原本資料: " + linkedList.get(j));
				change_salary.setSalary(Integer.parseInt(salary_change));
				String new_information = change_salary.getName() + " " +String.valueOf(change_salary.getSalary());
				linkedList.set(j, new_information);
				System.out.println("更改資料: " + new_information);
			}
		}
		if(!(confirm_same)){
			System.out.println("找不到資料!");
		}
		}catch(java.lang.NumberFormatException e) {
			System.out.println("格式錯誤");
		}
	}

	public static void deleteEmp() {
		System.out.println("請輸入刪除資料的姓名: ");
		String name = scanner.nextLine();
		String word = "";// 接員工的名字
		boolean confirm_same = false;// 判斷是否為一樣的字
		int size = linkedList.size();
		for (int j = 0; j < size; j++) {
			if (confirm_same) {
				break;
			}
			String compare_word = "";
			for (int i = 0; i < linkedList.get(j).length(); i++) {
				if (!(linkedList.get(j).substring(i, i + 1).equals(" "))) {
					compare_word += linkedList.get(j).substring(i, i + 1);
				} else {
					break;
				}
			}
			if (compare_word.compareToIgnoreCase(name) == 0) {
				confirm_same = true;
				linkedList.remove(j);
				System.out.println("刪除成功");
			}
		}
		if (!(confirm_same)) {
			System.out.println("找不到資料!");
		}
	}

	public static void enquiry() {
		System.out.println("請輸入查詢姓名: ");
		String name = scanner.nextLine();
		String word = "";// 接員工的名字
		boolean confirm_same = false;// 判斷是否為一樣的字
		int size = linkedList.size();

		for (int j = 0; j < size; j++) {
			if (confirm_same) {
				break;
			}
			String salary = "";
			String compare_word = "";
			for (int i = 0; i < linkedList.get(j).length(); i++) {
				if ((!(linkedList.get(j).substring(i, i + 1).equals(" "))) && !(linkedList.get(j).charAt(i) >= 48 && linkedList.get(j).charAt(i) <= 57)) {
					compare_word += linkedList.get(j).substring(i, i + 1);
				} else if(linkedList.get(j).charAt(i) >= 48 && linkedList.get(j).charAt(i) <= 57){
					salary += linkedList.get(j).substring(i, i + 1);
				}
			}
			if (compare_word.compareToIgnoreCase(name) == 0) {
				Emp get_information = new Emp(compare_word, Integer.parseInt(salary));
				confirm_same = true;
				System.out.println(get_information.getName() + " " + get_information.getSalary());
			}
		}
		if (!(confirm_same)) {
			System.out.println("找不到資料!");
		}

	}

	public static void listAllEmp() {
		for (int i = 0; i < linkedList.size(); i++) {
			System.out.println(linkedList.get(i));
		}
	}
}