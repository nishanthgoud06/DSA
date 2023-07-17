class calci{
    private int index=0;
    char[] c;
    public calci(String s){
        c=s.toCharArray();
    }
    public int getResult(){
        if(c.length==0)
            return 0;
        return dfs();
    }
    private int dfs(){
        int operator=1;
        int sum=0;
        while(index<c.length){
            if(c[index]==')')
                break;
            else if(c[index]=='('){
                index++;
                sum+=operator*dfs();
            }else if(c[index]=='-'){
                operator=-1;
            }else if(c[index]=='+'){
                operator=1;
            }else if(Character.isDigit(c[index])){
                StringBuilder sb=new StringBuilder();
                while (index<c.length && Character.isDigit(c[index])){
                    sb.append(c[index++]);
                }
                index--;
                sum+=operator*Integer.parseInt(sb.toString());
            }
            index++;
        }
        return sum;
    }
}