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
		System.out.println("1:�s�W 2:�ק� 3:�R�� 4:�d�� 5:�C�X 6:���}");
		int choice = scanner.nextInt(); // read in command
		String getenter = scanner.nextLine();
		while (choice != 6) {
			if (choice == 1) {
				addNewEmp();
				System.out.println("Please enter your choice: ");
				System.out.println("1:�s�W 2:�ק� 3:�R�� 4:�d�� 5:�C�X 6:���}");
			} else if (choice == 2) {
				updateEmp();
				System.out.println("Please enter your choice: ");
				System.out.println("1:�s�W 2:�ק� 3:�R�� 4:�d�� 5:�C�X 6:���}");
			} else if (choice == 3) {
				deleteEmp();
				System.out.println("Please enter your choice: ");
				System.out.println("1:�s�W 2:�ק� 3:�R�� 4:�d�� 5:�C�X 6:���}");
			} else if (choice == 4) {
				enquiry();
				System.out.println("Please enter your choice: ");
				System.out.println("1:�s�W 2:�ק� 3:�R�� 4:�d�� 5:�C�X 6:���}");
			} else if (choice == 5) {
				listAllEmp();
				System.out.println("Please enter your choice: ");
				System.out.println("1:�s�W 2:�ק� 3:�R�� 4:�d�� 5:�C�X 6:���}");
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
					compareEmp(theNode); // ��X�A���m�N�s��ƥ[�J��C
				}
			}
			sc.close();
		} catch (IOException e) {
			System.out.println("�L�ɮ�");
		}
	}

	public static void compareEmp(Emp theNode) {
		String name = theNode.getName();
		int number = theNode.getSalary();
		String word = name + " " + String.valueOf(number);// �W�r�[�W�Ʀr
		int size = linkedList.size();
		int get = 0;// �T�w��m������
		boolean confirm = false;// �P�_
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
		System.out.println("��ޤG�� 406401331 ²�ӥ�");
	}

	public static void addNewEmp() {
		System.out.print("�п�J���u�W�r �~��: ");
		String name_salary = scanner.nextLine();
		int size = linkedList.size();
		int get = 0;// �T�w��m������
		boolean confirm = false;// �P�_
		boolean confirm_same = true;// �P�_���S���@�˪��r
		String word = "";// ���s�����u���W�r
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
			System.out.println("�w���ӦW�r�A�ڵ��s�W!");
		}
	}

	public static void updateEmp() {
		System.out.print("�п�J���u�W�r ���諸�~��: ");
		try {
		String name_salary = scanner.nextLine();
		String word = "";//�����u���W�r
		boolean confirm_same = false;// �P�_�O�_���@�˪��r
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
				System.out.println("�쥻���: " + linkedList.get(j));
				change_salary.setSalary(Integer.parseInt(salary_change));
				String new_information = change_salary.getName() + " " +String.valueOf(change_salary.getSalary());
				linkedList.set(j, new_information);
				System.out.println("�����: " + new_information);
			}
		}
		if(!(confirm_same)){
			System.out.println("�䤣����!");
		}
		}catch(java.lang.NumberFormatException e) {
			System.out.println("�榡���~");
		}
	}

	public static void deleteEmp() {
		System.out.println("�п�J�R����ƪ��m�W: ");
		String name = scanner.nextLine();
		String word = "";// �����u���W�r
		boolean confirm_same = false;// �P�_�O�_���@�˪��r
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
				System.out.println("�R�����\");
			}
		}
		if (!(confirm_same)) {
			System.out.println("�䤣����!");
		}
	}

	public static void enquiry() {
		System.out.println("�п�J�d�ߩm�W: ");
		String name = scanner.nextLine();
		String word = "";// �����u���W�r
		boolean confirm_same = false;// �P�_�O�_���@�˪��r
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
			System.out.println("�䤣����!");
		}

	}

	public static void listAllEmp() {
		for (int i = 0; i < linkedList.size(); i++) {
			System.out.println(linkedList.get(i));
		}
	}
}