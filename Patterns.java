import java.util.Scanner;

public class Patterns {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
//		for(int i=0;i<=4;i++){
//			for(int j=0;j<=4;j++){
//				System.out.print(j+"");
//			}
//			System.out.println();
//		}
//		
//			
			System.out.println("Enter no.of lines");
			Scanner sc=new Scanner(System.in);
			int n=sc.nextInt();
			for(int i=1;i<=n;i++){
				//System.out.println(1);
				for(int j=1;j<=i;j++){
					System.out.print(j+"");
				}
				System.out.println();
			}
	}
}
