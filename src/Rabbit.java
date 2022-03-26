public class Rabbit extends Animal{
    public Rabbit(String color, int age) {
        super(color, age);
    }

    public String run() {
        return "跳跃";
    }
    public static void main(String[] args) {
        Animal rabbit = new Rabbit("white", 2);
        System.out.println("rabbit "+rabbit.run());
    }
}
