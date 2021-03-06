package chapter_1;

import java.util.Iterator;
import java.util.Scanner;

public class QuenesArr<Item> {
	
	private Item[] s;
	private int first = 0;
	private int last = 0;
	
	public QuenesArr() {
		s = (Item[]) new Object[1];
	}
	
	public boolean isEmpty() {
		return last == first;
	}
	
	public void enqueue(Item item) {
		if(last == s.length) {
			resize(2 * s.length);
		}
		s[last] = item;
		last++;
	}
	
	public Item dequeue() {
		Item head = s[first];
		s[first] = null;
		first++;
		if((last - first) > 0 && (last - first) == s.length / 4) {
			resize(s.length / 2);
		}
		return head;
	}
	
	private void resize(int capacity) {
		int j = 0;
		Item[] copy = (Item[]) new Object[capacity];
		for(int i = first; i < last; i++) {
			copy[j] = s[i];
			j++;
		}
		last = j;
		first = 0;
		s = copy;
	}
	
	public Iterator<Item> iterator() {
		return new ArrayIterator();
	}
	
	public class ArrayIterator implements Iterator<Item> {
		private int ilast = last;
		private int ifirst = first;
		public boolean hasNext() {
			return (ilast - ifirst) != 0;
		}
		
		public void remove() {
			
		}
		
		public Item next() {
			return s[ifirst++];
		}
		
	}

	public static void main(String[] args) {
		QuenesArr<String> q = new QuenesArr<String>();
		Scanner in = new Scanner(System.in);
		String x;
		while(in.hasNext()) {
			x = in.next();
			if(x.charAt(0) == '-') {
				String result = q.dequeue();
				System.out.println(result);
			} else {
				q.enqueue(x);
			}
		}
		
		//Implement the Iterable:
		Iterator<String> i = q.iterator();
		while (i.hasNext()) {
		 String s = i.next();
		 System.out.println(s);
		}
		
		
		in.close();

	}

}
