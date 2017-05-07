package calculator;
import java.util.Scanner;

public class Operator {
	private double sum, num;
	private static short counter;
	private boolean running;
	public static Scanner sc = new Scanner(System.in);
	
	public Operator() {
		sum = 0;
		num = 0;
		running = true;
	}
	
	private String OperatorList() {
		System.out.println("Select operation: [+][-][/][*][=][c]");
		String selOp = sc.next();
		selOp = Character.toString(selOp.charAt(0));
		// System.out.println(selOp);						// debugging purposes
		if(selOp.equals("+")||selOp.equals("-")||selOp.equals("/")||selOp.equals("*")||selOp.equals("=")||selOp.equals("c")) return selOp;
		else return OperatorList();
	}
	
	private double ConsoleOp() {
		System.out.printf("Enter [%d]number: ",++counter);
		num = sc.nextDouble();
		if(Double.isNaN(num)) {
			System.out.println("Please enter integers only");
			counter--;
			ConsoleOp();
		}
		return num;
	}
	
	public void Start() {
		System.out.println("Welcome to based text Calculator!");
		double numHolder = ConsoleOp();
		String opList = OperatorList();
		num = ConsoleOp();
		while(running) {
			switch(opList) {
			case "+":
				if(counter>1) {
					numHolder = Addition(numHolder, num);
					SetSum(numHolder);
					// System.out.printf("%f: ", GetSum()); 	//debugging purposes
				}
				opList = "";
				break;
			case "-":
				if(counter>1) {
					numHolder = Subtraction(numHolder, num);
					SetSum(numHolder);
				}
				opList = "";
				break;
			case "/":
				if(counter>1) {
					numHolder = Division(numHolder, num);
					SetSum(numHolder);
				}
				opList = "";
				break;
			case "*":
				if(counter>1) {
					numHolder = Multiplication(numHolder, num);
					SetSum(numHolder);
				}
				opList = "";
				break;
			default :
				break;
			}
			switch(opList) {						// if operator list returns =, add previous number first before finalizing number
			case "=":
				System.out.printf("Total operation value = %.2f", GetSum());
				running = false;
				SetSum(0);
				num = 0;
				counter=0;
				break;
			case "c":								// Will enhance in the near future:
				running = false;					// + set previous value to null and call ConsoleOp() to re-enter new value
				SetSum(0);
				num = 0;
				counter=0;
				break;
			default:
				break;
			}
			if(counter>1) opList = OperatorList();
			if(!opList.equals("=")) num = ConsoleOp();
		}
		sc.close();
	}
	
	private void SetSum(double sum) { this.sum = sum; }
	private double GetSum() { return sum; }
	private double Multiplication(double a, double b) { return a*b; }
	private double Division(double a, double b) { return b/a; }
	private double Addition(double a, double b) { return a+b; }
	private double Subtraction(double a, double b) { return a-b; }	
}
