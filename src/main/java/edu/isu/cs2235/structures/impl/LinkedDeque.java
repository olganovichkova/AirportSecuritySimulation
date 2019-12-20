package main.java.edu.isu.cs2235.structures.impl;

import main.java.edu.isu.cs2235.structures.Deque;
import main.java.edu.isu.cs2235.structures.Queue;

import java.util.Collection;
//import java.util.Deque;
import java.util.Iterator;

public class LinkedDeque<E> implements Deque<E> {
    public DoublyLinkedList<E> list;
    public LinkedDeque(){
        list = new DoublyLinkedList<>();
    }

    @Override
    public E peekLast() {
        //check to see if list is empty
        if(list.isEmpty()){
            return null;
        }
        //returns last element without removing it
        return list.last();
    }

    @Override
    public void offerFirst(E element) {
        //check to make sure element does not equal null before making changes
        if(element != null){
            //adds element to the front of list
            list.addFirst(element);
        }
    }

    @Override
    public E pollLast() {
        //check to see if list is empty
        if(list.isEmpty()){
            return null;
        }
        //returns and removes last element
        return list.removeLast();
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        if(list.size() == 0){
            return true;
        }
        return false;
    }

    @Override
    public void offer(E element) {
        //check to make sure element does not equal null before making changes
        if(element != null){
            //adds element to the end of list
            list.addLast(element);
        }
    }

    @Override
    public E peek() {
        //check to see if list is empty
        if(list.isEmpty()){
            return null;
        }
        //returns first element without removing it
        return list.first();
    }

    @Override
    public E poll() {
        //check to see if list is empty
        if(list.isEmpty()){
            return null;
        }
        //returns and removes first element
        return list.removeFirst();
    }

    @Override
    public void printQueue() {
        //checks to make sure size is not 0 before continuing
        if(!isEmpty()){
            E head;
            //run down the list and print out each element
            for(int i = 0; i < list.size(); i++){
                head = list.get(i);
                System.out.println(head.toString());
            }
        }
    }

    @Override
    public void transfer(Queue<E> into) {
        //check to make sure into stack is not null
        if(into != null){
            int size = list.size();
            //run down the list backwards and insert those elements into stack
            for(int i = (size - 1); i >= 0; i--){
                into.offer(list.get(i));
            }
            //remove elements one by one from list until the list is empty
            while(!list.isEmpty()){
                list.removeFirst();
            }
        }
    }

    @Override
    public void reverse() {
        //check to make sure the list is not empty AND size of the list is not 1 before continuing
        if(!list.isEmpty() && list.size() != 1){
            E tempFirst;
            E tempLast;
            //run down the first half of the list and assign
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
