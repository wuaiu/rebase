import java.util.Scanner;

public class Score {
    public static void main(String[] args) {
        System.out.println("输入分数:");
        int c=new Scanner(System.in).nextInt();
        String result = new String();
        if (c >= 90) {
            result = "优秀";
        } else if (c >= 80) {
            result = "良好";
        } else if (c >= 60) {
            result = "及格";
        } else if (c < 60){
            result = "不及格";
        }
        System.out.println("结果:"+result);
    }
}
