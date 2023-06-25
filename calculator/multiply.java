package calculator;

import java.util.List;

public class multiply implements operate{
    @Override
    public Double getResult(Double... list) {
        Double result=1.0;
        for(int i=0;i<list.length;i++){
            result*=list[i];
        }
        return result;
    }
}
