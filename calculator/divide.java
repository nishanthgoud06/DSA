package calculator;

import java.util.List;

public class divide implements operate{
    @Override
    public Double getResult(Double... list) {
        Double result=list[0];
        for(int i=1;i<list.length;i++){
            result/=list[i];
        }
        return result;
    }
}
