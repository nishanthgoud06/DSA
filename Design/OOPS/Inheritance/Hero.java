package Design.OOPS.Inheritance;

public class Hero extends Details{
    String superPower;
    public Hero(String name,int age,String superPower){
        super(name,age);
        this.superPower=superPower;
    }

    public String getSuperPower() {
        return superPower;
    }
    @Override
    public int getAge(){
        System.out.println(super.age);
        return 0;
    }
}
