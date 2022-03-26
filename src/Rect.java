import java.util.Scanner;

public class Rect {

    public static void main(String[] args) {
        System.out.println("输入长度:");
        Double c=new Scanner(System.in).nextDouble();
        System.out.println("输入宽度:");
        Double k=new Scanner(System.in).nextDouble();
        System.out.println("面积:"+c*k);
        System.out.println("周长:"+(c+k)*2);
    }
}
