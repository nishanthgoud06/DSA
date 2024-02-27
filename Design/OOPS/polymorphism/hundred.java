package Design.OOPS.polymorphism;

public class hundred implements cr{
    int count;
    String country;

    @Override
    public String origin() {
        return country;
    }

    @Override
    public int viwership() {
        return count;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public hundred(String country, int count) {
        this.count = count;
        this.country = country;
    }
}

