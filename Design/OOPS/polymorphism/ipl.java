package Design.OOPS.polymorphism;

public class ipl implements  cr{
    String country;
    int count;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public ipl(String country,int count) {
        this.count=count;
        this.country=country;
    }

    @Override
    public String origin() {
        return this.country;
    }
    @Override
    public int viwership() {
        return this.count;
    }

}
