package Design.OOPS.polymorphism;

public class bbl implements cr{
    String name;
    int count;

    public bbl(String name, int count) {
        this.name = name;
        this.count = count;
    }

    @Override
    public String origin() {
        return name;
    }

    @Override
    public int viwership() {
        return count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
