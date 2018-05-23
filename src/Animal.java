public abstract class Animal {
    private int legs;

    protected Animal(int legs){
        this.legs = legs;
    }

    protected abstract void eat();

    protected void walk(){
        System.out.println("用" + legs + "条腿移动");
    }
}
