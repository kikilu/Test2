public class Cat extends Animal implements Pet {
    private String name;

    protected Cat(String name){
        this();
        this.name = name;
    }

    protected Cat(){
        super(4);
    }

    protected void eat(){
        System.out.println(this.name + "吃");
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public void play(){
        System.out.println(this.name + "玩耍");
    }
}
