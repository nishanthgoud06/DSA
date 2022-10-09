class arr{
    int value;
    int priority;
}
public class priorityQueue {
        static arr[] item=new arr[10];
        static int size=-1;
        public static void enqueue(int val,int p){
            size++;
            item[size]=new arr();
            item[size].value=val;
            item[size].priority=p;
        }
        public static int peek(){
            int highest=0;
            int index=-1;
            for(int i=0;i<size;i++){
                if(highest<item[i].priority){
                    highest=item[i].priority;
                    index=i;
                }else if(highest==item[i].priority&&item[index].value<item[i].value&&index>-1){
                    highest=item[i].priority;
                    index=i;
                }
            }
            return index;
        }
        public static void dequeue(){
            int a=peek();
            for(int i=a;i<size;i++){
                item[i]=item[i+1];
            }
        }
        public static void main(String args[]) {
            enqueue(10, 2);
            enqueue(14, 4);
            enqueue(16, 4);
            enqueue(12, 3);

            // Stores the top element
            // at the moment
            int ind = peek();

            System.out.println(item[ind].value);

            // Dequeue the top element
            dequeue();

            // Check the top element
            ind = peek();
            System.out.println(item[ind].value);

            // Dequeue the top element
            dequeue();

            // Check the top element
            ind = peek();
            System.out.println(item[ind].value);


        }
    }

