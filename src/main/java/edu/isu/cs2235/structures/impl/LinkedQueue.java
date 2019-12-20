package main.java.edu.isu.cs2235.structures.impl;

import main.java.edu.isu.cs2235.structures.Queue;
//import java.util.Queue;

public class LinkedQueue<E> implements Queue<E> {
    public DoublyLinkedList<E> list;
    public LinkedQueue(){
        list = new DoublyLinkedList<>();
    }



    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        if(list.isEmpty()) {
            return true;
        }
        return false;
    }

    @Override
    public void offer(E element) {
        if(element != null){
            list.addLast(element);
        }

    }

    @Override
    public E peek() {
        if(list.isEmpty()){
            return null;
        }
        return list.first();
    }

    @Override
    public E poll() {
        if(list.isEmpty()){
            return null;
        }
        return list.removeFirst();
    }

    @Override
    public void printQueue() {
        if(!isEmpty()){
            E head;
            for(int i = 0; i < list.size(); i++){
                head = list.get(i);
                System.out.println(head.toString());
            }
        }
    }

    @Override
    public void transfer(Queue into) {
        if(into != null){
            int size = list.size();
            for(int i = (size - 1); i >= 0; i--){
                into.offer(list.get(i));
            }
            while(!list.isEmpty()){
                list.removeFirst();
            }
        }
    }

    @Override
    public void reverse() {
        if(!list.isEmpty() && list.size() != 1){
            E tempFirst;
            E tempLast;
            for(int i = 0; i < list.size()/2; i++){
                tempFirst = list.remove(i);
                tempLast = list.remove(list.size() - 1 - i);
                list.insert(tempLast, i);
                list.insert(tempFirst, list.size() - i);
            }
        }
    }

    @Override
    public void merge(Queue<E> from) {
        if(from != null && !list.isEmpty()){
            Queue<E> temp = new LinkedQueue<E>();
            E tempFrom;
            int size = list.size();
            for(int i = 0; i < size; i++){
                list.addLast(from.peek());
                tempFrom = from.poll();
                temp.offer(tempFrom);
            }
            while(!temp.isEmpty()){
                from.offer(temp.poll());
            }
        }
    }
}
