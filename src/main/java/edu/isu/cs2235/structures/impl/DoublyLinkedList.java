package main.java.edu.isu.cs2235.structures.impl;

import main.java.edu.isu.cs2235.structures.List;

public class DoublyLinkedList<E> implements List<E> {

    private static class Node<E>{

        private E nodeElement;
        private Node<E> nextNode;
        private Node<E> prevNode;
        public Node(E element, Node<E> prev, Node<E> next){
            nodeElement = element;
            prevNode = prev;
            nextNode = next;
        }
        public E getNodeElement(){
            return nodeElement;
        }
        public Node<E> getNextNode(){
            return nextNode;
        }
        public Node<E> getPrevNode(){
            return prevNode;
        }
        public void setNextNode(Node<E> next){
            nextNode = next;
        }
        public void setPrevNode(Node<E> prev){
            prevNode = prev;
        }
    }

    private Node<E> head = null;
    private Node<E> tail = null;
    private int size = 0;
    public DoublyLinkedList(){
        head = new Node<E>(null, null, null);
        tail = new Node<E>(null, head, null);
        head.setNextNode(tail);
    }



    @Override
    public E first() {
        if(isEmpty())
            return null;
        else
            return head.getNextNode().getNodeElement();
    }

    @Override
    public E last() {
        if(isEmpty())
            return null;
        else
            return tail.getPrevNode().getNodeElement();
    }

    @Override
    public void addLast(E element) {
        if(element != null){
            Node<E> newNode = new Node<E>((E) element, tail.getPrevNode(), tail );
            if(isEmpty()){
                head.setNextNode(newNode);
                tail.setPrevNode(newNode);
                newNode.setNextNode(tail);
                newNode.setPrevNode(head);
            }
            else {
                newNode.setNextNode(tail);
                newNode.setPrevNode(tail.getPrevNode());
                tail.getPrevNode().setNextNode(newNode);
                tail.setPrevNode(newNode);

            }
            size++;
        }
    }

    @Override
    public void addFirst(E element) {
        if(element != null){
            Node<E> newNode = new Node<E>((E) element, head.getPrevNode(), head);
            if(isEmpty()){
                head.setNextNode(newNode);
                tail.setPrevNode(newNode);
                newNode.setPrevNode(head);
                newNode.setNextNode(tail);
            }
            else {
                newNode.setNextNode(head.getNextNode());
                newNode.setPrevNode(head);
                head.getNextNode().setPrevNode(newNode);
                head.setNextNode(newNode);
            }
            size++;
        }

    }

    @Override
    public E removeFirst() {
        if(isEmpty())
            return null;
        E first = head.getNextNode().getNodeElement();
        Node<E> previous = head;
        Node<E> follower = head.getNextNode().getNextNode();
        previous.setNextNode(follower);
        follower.setPrevNode(previous);
        size--;
        return first;
    }

    @Override
    public E removeLast() {
        if (isEmpty())
            return null;
        E last = tail.getPrevNode().getNodeElement();
        Node<E> curr = tail.getPrevNode();
        Node<E> previous = curr.getPrevNode();
        previous.setNextNode(tail);
        tail.setPrevNode(previous);
        size--;
        return last;
    }

    @Override
    public void insert(E element, int index) {
        if(index >= 0 && element != null){
            Node<E> newNode = new Node<E>((E) element, null, null);
            if(isEmpty()){
                head.setNextNode(newNode);
                tail.setPrevNode(newNode);
                newNode.setPrevNode(head);
                newNode.setNextNode(tail);
                size = 1;
            }
            else if(index == 0){
                head.getNextNode().setPrevNode(newNode);
                newNode.setNextNode(head.getNextNode());
                newNode.setPrevNode(head);
                head.setNextNode(newNode);
                size++;
            }
            else if(index >= size){
                tail.getPrevNode().setNextNode(newNode);
                newNode.setNextNode(tail);
                newNode.setPrevNode(tail.getPrevNode());
                tail.setPrevNode(newNode);
                size++;
            }
            else {
                Node<E> runner = head;
                for(int i = 0; i < index; i++){
                    runner = runner.getNextNode();
                }
                runner.getNextNode().setPrevNode(newNode);
                newNode.setNextNode(runner.getNextNode());
                newNode.setPrevNode(runner);
                runner.setNextNode(newNode);
                size++;
            }
        }

    }

    @Override
    public E remove(int index) {
        if(isEmpty() || index >= size || index < 0)
            return null;
        if(index == 0){
            E removedElement = head.getNextNode().getNodeElement();
            head.getNextNode().getNextNode().setPrevNode(head);
            head.setNextNode(head.getNextNode().getNextNode());
            size--;
            return removedElement;
        }
        if(index == (size - 1)){
            E removedElement = tail.getPrevNode().getNodeElement();
            tail.getPrevNode().getPrevNode().setNextNode(tail);
            tail.setPrevNode(tail.getPrevNode().getPrevNode());
            size--;
            return removedElement;
        }
        Node<E> runner = head;
        for(int i = 0; i < index; i++){
            runner = runner.nextNode;
        }
        E removedElement = runner.getNextNode().getNodeElement();
        runner.getNextNode().getNextNode().setPrevNode(runner);
        runner.setNextNode(runner.getNextNode().getNextNode());
        size--;
        return removedElement;
    }

    @Override
    public E get(int index) {
        if(isEmpty()) {
            return null;
        }
        if(index > size || index < 0){
            return null;
        }
        if(index == 0) {
            return head.getNextNode().getNodeElement();
        }
        if(index == (size -1)){
            return tail.getPrevNode().getNodeElement();
        }
        Node<E> runner = head;
        for(int i = 0; i < index; i++){
            runner = runner.nextNode;
        }
        return runner.getNextNode().getNodeElement();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        if(size == 0)
            return true;
        else
            return false;
    }

    @Override
    public void printList() {
        if(!isEmpty()){
            Node<E> runner = head;
            while(runner.getNextNode() != tail){
                runner = runner.nextNode;
                System.out.println(runner.getNodeElement().toString());
            }
        }
    }
}
