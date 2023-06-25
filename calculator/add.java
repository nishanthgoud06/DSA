package calculator;

import java.util.List;

public class add implements operate{
    @Override
    public Double getResult(Double... list){
        Double result=0.0;
        for(int i=0;i<list.length;i++){
            result+=i;
        }
        return result;
    }
}
