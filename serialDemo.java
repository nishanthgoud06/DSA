import java.io.*;

class data implements Serializable {
    int id;
    String value;
    public data(int id,String value){
        this.id=id;
        this.value=value;
    }
}
public class serialDemo  {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        data student1=new data(1,"sai");
        File f=new File("testfile.ser");
            FileOutputStream fos=new FileOutputStream(f);
            ObjectOutputStream oos=new ObjectOutputStream(fos);
            oos.writeObject(student1);
            oos.close();
            fos.close();
            System.out.println("object has been Serial");

        FileInputStream fin=new FileInputStream(f);
        ObjectInputStream ofs=new ObjectInputStream(fin);
        data student2=(data)ofs.readObject();
            ofs.close();
            fin.close();
        System.out.println(student2.id);
        System.out.println(student2.value);
    }

}
