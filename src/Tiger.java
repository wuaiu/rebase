public class Tiger extends Animal {
    public Tiger(String color, int age) {
        super(color, age);
    }

    public String eat() {
        return "吃肉";
    }

    public static void main(String[] args) {
        Animal tiger = new Tiger("yellow", 5);
        System.out.println("tiger " + tiger.eat());
    }
}
