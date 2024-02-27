package Design;
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Marks {
    private HashMap<String,Integer> hashmap;
    private static Marks instance;
    private Marks(){
        hashmap=new HashMap<>();
    }
    public static synchronized Marks getInstance(){
        if(instance==null)
            instance=new Marks();
        return instance;
    }
    public void addMarks(String studentName,int marks){
        instance.hashmap.put(studentName,marks);
    }
    public int getMarks(String StudentName){
        return instance.hashmap.get(StudentName);
    }
    public List<String> studentFaile(){
        List<String> result=new ArrayList<>();
        for(Map.Entry<String,Integer> entry:hashmap.entrySet()){
            if(entry.getValue()<35){
                result.add(entry.getKey());
            }
        }
        return result;
    }
}
