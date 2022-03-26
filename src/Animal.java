public class Animal {
    String color;
    int age;

    public Animal(String color, int age) {
        this.color = color;
        this.age = age;
    }

    public String eat() {
        return "吃饭";
    }

    public String run() {
        return "奔跑";
    }
}
