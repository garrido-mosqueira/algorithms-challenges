package topic3_stacks_queues;

import java.util.Stack;

public class QueueUsingTwoStacks {

    static class MyQueue{
        private final Stack<Integer> in = new Stack<Integer>();
        private final Stack<Integer> out = new Stack<Integer>();

        public void queue(int value) {
            in.push(value);
        }

        public int peek(){
            moveInToOut();
            return out.peek();
        }

        public int dequeue(){
            moveInToOut();
            return out.pop();
        }

        private void moveInToOut(){
            if(out.empty()){
                while(!in.empty()) {
                    out.push(in.pop());
                }
            }
        }
    }

    public static void main(String[] args) {
        MyQueue queue = new MyQueue();
        queue.queue(1);
        queue.queue(2);
        queue.queue(3);
        System.out.println(queue.peek());//1
        queue.queue(4);
        System.out.println(queue.dequeue());//1
        queue.dequeue();//2
        System.out.println(queue.dequeue());//3
    }

}
