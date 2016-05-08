package com.dlaq.test.core.collection;

/**
 * 自定义LinkedList
 */
public class MyLinkedList {
	
	private int size;
	private Node first;
	private Node last;
	
	public MyLinkedList(){
	}
	public void add(Object e){
		Node l = last;
		Node newNode = new Node(l, e, null);
		last = newNode;
		if(null == l){
			first = newNode;
		}else{
			l.next = newNode;
		}
		size++;
	}
	public void add(int index,Object e){
		if(index == size){
			add(e);
		}else{
			Node oldNode = node(index);
			Node prev = oldNode.prev;
			Node newNode = new Node (prev, e, oldNode);
			oldNode.prev = newNode;
			if(null == prev){
				first = newNode;
			}else{
				prev.next = newNode;
			}
		}
		size++;
	}
	public Object get(int index){
		return node(index);
	}
	public Object getFirst(){
		return first.obj;
	}
	public Object getLast(){
		return last.obj;
	}
	public Object remove(int index){
		Node n = node(index);
		Object o = n.obj;
		Node prev = n.prev;
		Node next = n.next;
		
		if(null == prev){
			first = next;
		}else{
			prev.next = next;
			n.prev = null;
		}

		if(null == next){
			last = prev;
		}else{
			next.prev = prev;
			n.next = null;
		}
		size--;
		return o;
	}
	public int size(){
		return this.size;
	}
	
	private Node node(int index){
		if(index < (size >> 1) ){
			Node x = first;
			for(int i=0;i<index;i++){
				x = x.next;
			}
			return x;
		}else{
			Node x = last;
			for(int i=size-1;i>index;i--){
				x = x.prev;
			}
			return x;
		}
	}
	
	
	@Override
	public String toString() {
		String tmp = "[";
		for(int i=0;i<size;i++){
			tmp += node(i) + ",";
		}
		tmp += "]";
		return tmp;
	}
	
	private static class Node{
		Object obj;
		Node next;
		Node prev;
		public Node(Node prev, Object obj, Node next) {
			this.prev = prev;
			this.obj = obj;
			this.next = next;
		}
		@Override
		public String toString() {
			return obj.toString();
		}
	}
}
