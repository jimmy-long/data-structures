package com.jwlong.datastructures;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Stores data as a linked list. The list will be doubly linked. The list will
 * be circular.
 * 
 * @author jimmy-long
 *
 * @param <E>
 */
public class LinkedList<E> implements List<E> {

	private int size;
	private ListNode<E> head;

	public LinkedList() {
		this.size = 0;
		this.head = null;
	}

	@Override
	public Iterator<E> iterator() {
		return new LinkedListIterator();
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public E get(int index) {
		return nodeAt(index).data;
	}

	@Override
	public int indexOf(E value) {
		if (this.size == 0) {
			return -1;
		} else if (this.head.data.equals(value)) {
			return 0;
		}
		
		int index = 1;
		ListNode<E> current = this.head.next;
		
		while (!current.equals(this.head)) {
			if (current.data.equals(value)) {
				return index;
			}
			index++;
			current = current.next;
		}
		
		return -1;
	}

	@Override
	public boolean isEmpty() {
		return this.size == 0;
	}

	@Override
	public boolean contains(E value) {
		return this.indexOf(value) > -1;
	}

	@Override
	public void add(E value) {
		this.add(this.size, value);
	}

	@Override
	public void add(int index, E value) {
		if (index < 0 || index > this.size) {
			throw new IndexOutOfBoundsException();
		}

		if (this.size == 0) {
			this.head = new ListNode<E>(value);
			
			this.head.prev = this.head;
			this.head.next = this.head;
			
			this.size++;
			return;
		}
		
		if (index == 0) {
			this.head = new ListNode<E>(value, this.head.prev, this.head);
			this.head.prev.next = this.head;
			this.size++;
			return;
		}
		
		ListNode<E> current = this.nodeAt(index - 1);
		ListNode<E> newNode = new ListNode<E>(value, current, current.next);
		current.next.prev = newNode;
		current.next = newNode;
		
		if (index == this.size) {
			this.head.prev = current.next;
		}
		this.size++;
	}

	@Override
	public void addAll(List<E> other) {
		for (E value : other) {
			this.add(value);
		}
	}

	@Override
	public void remove(int index) {
		if (index == 0) {
			if (this.size == 1) {
				this.head = null;
				this.size--;
				return;
			}
			
			this.head.next.prev = this.head.prev;
			this.head.prev.next = this.head.next;
			this.head = this.head.next;
			this.size--;
			return;
		}
		
		ListNode<E> current = nodeAt(index);
		current.next.prev = current.next;
		current.prev.next = current.next;
		this.size--;
	}

	@Override
	public void set(int index, E value) {
		this.nodeAt(index).data = value;
	}

	@Override
	public void clear() {
		this.head = null;
		this.size = 0;
	}

	public String toString() {
		if (head == null) {
			return "[]";
		}

		String result = "[" + head.data;
		ListNode<E> current = head.next;
		while (current != this.head) {
			result += ", " + current.data;
			current = current.next;
		}
		result += "]";
		return result;
	}

	private ListNode<E> nodeAt(int index) {
		if (index < 0 || index >= this.size) {
			throw new IndexOutOfBoundsException();
		}
		
		ListNode<E> current;
		if (index < (this.size / 2)) {
			current = this.head;
			for (int i = 0; i < index; i++) {
				current = current.next;
			}
		} else {
			current = this.head.prev;
			for (int i = this.size - 1; i > index; i--) {
				current = current.prev;
			}
		}
		
		return current;
	}
	
	private class ListNode<T> {
		public T data;

		public ListNode<T> prev;
		public ListNode<T> next;

		public ListNode(T data) {
			this(data, null, null);
		}
		
		public ListNode(T data, ListNode<T> prev, ListNode<T> next) {
			this.data = data;
			this.prev = prev;
			this.next = next;
		}
	}
	
	private class LinkedListIterator implements Iterator<E> {

		private ListNode<E> current;
		private int currentIndex;
		private boolean removeOK;
		
		public LinkedListIterator() {
			this.current = head;
			this.currentIndex = 0;
			this.removeOK = false;
		}
		
		@Override
		public boolean hasNext() {
			return this.currentIndex < LinkedList.this.size;
		}

		@Override
		public E next() {
			if (!this.hasNext()) {
				throw new NoSuchElementException();
			}
			
			E result = this.current.data;
			this.current = this.current.next;
			this.currentIndex++;
			this.removeOK = true;
			return result;
		}
		
		@Override
		public void remove() {
			if (!this.removeOK) {
				throw new IllegalStateException();
			}
			
			ListNode<E> prev2 = this.current.prev.prev;
			prev2.next = this.current;
			this.current.prev = prev2;
			LinkedList.this.size--;
			this.removeOK = false;
		}
		
	}

}
