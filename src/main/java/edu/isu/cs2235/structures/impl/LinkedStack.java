package main.java.edu.isu.cs2235.structures.impl;

import main.java.edu.isu.cs2235.structures.Stack;

public class LinkedStack<E> implements Stack<E> {

    private DoublyLinkedList<E> list;
    public LinkedStack(){
        list = new DoublyLinkedList<>();
    }


    @Override
    public void push(E element) {
        if(element != null) {
            list.addFirst(element);
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
    public E pop() {
        if(list.isEmpty()){
            return null;
        }
        return list.removeFirst();
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        if(list.isEmpty()){
            return true;
        }
        return false;
    }

    @Override
    public void transfer(Stack<E> to) {
        if(to != null) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                to.push(list.get(0));
                list.removeFirst();
            }
        }
    }

    @Override
    public void reverse() {
        if(!list.isEmpty() && list.size() != 1){
            E tempFirst;
            E tempLast;
            for(int i = 0; i < (list.size()/2); i++){
                tempFirst = list.remove(i);
                tempLast = list.remove(list.size() - 1 - i);
                list.insert(tempLast, i);
                list.insert(tempFirst, list.size() - i);
            }
        }
    }

//    Merges the contents of the provided stack onto the bottom of this stack.
//     * The order of both stacks must be preserved in the order of this stack
//     * after the method call. Furthermore, the provided stack must still contain
//     * its original contents in their original order after the method is
//     * complete. If the provided stack is null, no changes should occur.
//            *
//            * @param other Stack whose contents are to be merged onto the bottom of
//     * this stack.
    @Override
    public void merge(Stack<E> other) {
        if(other != null && !list.isEmpty()){
            Stack<E> temp = new LinkedStack<E>();
            E tempOther;
            for(int i = 0; i < list.size(); i++){
                list.addLast(other.peek());
                tempOther = other.pop();
                temp.push(tempOther);
            }
            while(!temp.isEmpty()){
                other.push(temp.pop());
            }
        }
    }

    @Override
    public void printStack() {
        if(!isEmpty()) {
            Stack<E> stack = new LinkedStack<E>();
            E head;
            for (int i = 0; i < list.size(); i++) {
                head = list.get(i);
                stack.push(head);
                System.out.println(stack.peek().toString());
            }
        }
    }
}
