import java.util.Scanner;

public class Forte {

    static double[] givenNumber;
    static char[] arrayOperations = {'+', '-', '*', '/'};
    static int[] possibleOperator;
    static double finalTarget;
    static int target;

    public static void main(String[] args) {
        Forte forte = new Forte();
        forte.getInput();
    }

    private void getInput() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Target : ");
        target = scan.nextInt();
        givenNumber = new double[4];
        for (int i = 0; i < 4; i++) {
            System.out.print("\nEnter the Number " + (i + 1) + " :");
            givenNumber[i] = scan.nextInt();
        }
        findPerumutation(givenNumber, 0);
    }

    public void findPerumutation(double[] array, int currentIndex) {
        if (currentIndex == array.length - 1) findArrayOperation();
        for (int i = currentIndex; i < array.length; i++) {
            swapArray(array, i, currentIndex);
            findPerumutation(array, currentIndex + 1);
            swapArray(array, i, currentIndex);
        }
    }

    private void swapArray(double[] array, int i, int currentIndex) {
        double temp = array[i];
        array[i] = array[currentIndex];
        array[currentIndex] = temp;
    }

    public void findArrayOperation() {
        int posible = 64;
        for (int i = 0; i < posible; i++) {
            int index = 2;
            possibleOperator = new int[3];
            int divident = i;
            while (index != -1) {
                int rem = divident % 4;
                possibleOperator[index--] = rem;
                divident = divident / 4;
            }
            findTarget();
        }
    }

    private void findTarget() {
        //possibility 1
        finalTarget = findResult(givenNumber[0],givenNumber[1],0);
        finalTarget = findResult(finalTarget,givenNumber[2],1);
        finalTarget = findResult(finalTarget,givenNumber[3],2);
        if(finalTarget == target)
            System.out.println("(("+(int)givenNumber[0]+" "+arrayOperations[possibleOperator[0]]+" "+(int)givenNumber[1]+") "+arrayOperations[possibleOperator[1]]+" "+(int)givenNumber[2]+") "+arrayOperations[possibleOperator[2]]+" "+(int)givenNumber[3]);
        //possibility 2
        double finalTarget1 = findResult(givenNumber[0],givenNumber[1],0);
        finalTarget = findResult(givenNumber[2],givenNumber[3],2);
        finalTarget = findResult(finalTarget1,finalTarget,1);
        if(finalTarget == target)
            System.out.println("("+(int)givenNumber[0]+" "+arrayOperations[possibleOperator[0]]+" "+(int)givenNumber[1]+") "+arrayOperations[possibleOperator[1]]+" ("+(int)givenNumber[2]+" "+arrayOperations[possibleOperator[2]]+" "+(int)givenNumber[3]+" )");
        //possibility 3
        finalTarget = findResult((givenNumber[0]*10+givenNumber[1]),givenNumber[2],1);
        finalTarget = findResult(finalTarget,givenNumber[3],2);
        if(finalTarget == target)
            System.out.println("("+(int)givenNumber[0]+""+(int)givenNumber[1]+") "+arrayOperations[possibleOperator[1]]+" "+(int)givenNumber[2]+" "+arrayOperations[possibleOperator[2]]+" "+(int)givenNumber[3]);
        //possibility 4
        finalTarget = findResult((givenNumber[0]*10+givenNumber[1]),(givenNumber[2]*10+givenNumber[3]),2);
        if(finalTarget == target)
        System.out.println("("+(int)givenNumber[0]+""+(int)givenNumber[1]+") "+arrayOperations[possibleOperator[2]]+" ("+(int)givenNumber[2]+" "+(int)givenNumber[3]+" )");
        //possibility 5
        finalTarget = findResult((givenNumber[0]*100+givenNumber[2]+givenNumber[1]*10),givenNumber[3],2);
        if(finalTarget == target)
        System.out.println("("+(int)givenNumber[0]+""+(int)givenNumber[1]+""+(int)givenNumber[2]+" "+arrayOperations[possibleOperator[2]]+" "+(int)givenNumber[3]+" )");
    }

    public double findResult(double number1,double number2, int index) {
        switch (arrayOperations[possibleOperator[index]]) {
            case '+':
                return (number1 + number2);
            case '-':
                return (number1 - number2);
            case '*':
                return (number1 * number2);
            case '/':
                return (number1 / number2);
            default:
                break;
        }
        return Integer.MIN_VALUE;
    }
}
