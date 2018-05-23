public class Fish extends Animal implements Pet {
    private String name;

    public Fish(){
        super(0);
    }

    @Override
    protected void eat() {
        System.out.println(this.name + "吃");
    }

    public void walk(){
        System.out.println(this.name + "游");
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void play() {
        System.out.println(this.name + "玩耍");
    }
}
