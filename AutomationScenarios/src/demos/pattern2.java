package demos;

/**
 * 
 * @author Asus
 *
 */
public class pattern2 {	
	public static void main(String[] args) {
		int j=5;
		for(int rows=1; rows<=5; rows++){
			for(int cols=1; cols<=j; cols++){
				System.out.print("* ");
			}
			j--;
			System.out.println();
		}

	}
}
