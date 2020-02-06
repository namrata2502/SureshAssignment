package demos;

public class pattern1 {

	public static void main(String[] args) {
		for(int rows=1; rows<=5; rows++){
			for(int cols=1; cols<=rows; cols++){
				System.out.print("* ");
			}
			System.out.println();
		}

	}

}
