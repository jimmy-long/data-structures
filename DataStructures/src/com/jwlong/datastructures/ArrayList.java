package com.jwlong.datastructures;

import java.util.Iterator;

public class ArrayList<E> implements List<E> {

	@Override
	public Iterator<E> iterator() {
		return new ArrayListIterator();
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public E get(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int indexOf(E value) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean contains(E value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void add(E value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void add(int index, E value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addAll(List<E> other) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(int index) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void set(int index, E value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}
	
	private class ArrayListIterator implements Iterator<E> {

		public ArrayListIterator() {
			// TODO Placeholder method stub
		}
		
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public E next() {
			// TODO Auto-generated method stub
			return null;
		}
		
	}

}
