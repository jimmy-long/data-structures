package com.jwlong.datastructures;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Iterator;

import org.junit.jupiter.api.Test;

class LinkedListTest {

	private static final String[] TEST_STRINGS = new String[] {
			"String1",
			"String2",
			"String3",
			"String4"
	};
	
	@Test
	void testIterator() {
		LinkedList<String> list = new LinkedList<String>();
		for (String s : TEST_STRINGS) {
			list.add(s);
		}
		
		Iterator<String> i = list.iterator();
		
		assertTrue(i.hasNext());
		assertEquals(TEST_STRINGS[0], i.next());
		
		assertTrue(i.hasNext());
		assertEquals(TEST_STRINGS[1], i.next());
		
		assertTrue(i.hasNext());
		assertEquals(TEST_STRINGS[2], i.next());
		
		assertTrue(i.hasNext());
		assertEquals(TEST_STRINGS[3], i.next());
		
		assertFalse(i.hasNext());
	}
	
	@Test
	void testSize() {
		LinkedList<String> list = new LinkedList<String>();
		
		assertEquals(0, list.size());
		
		list.add(TEST_STRINGS[0]);
		assertEquals(1, list.size());
		
		list.add(TEST_STRINGS[1]);
		list.add(TEST_STRINGS[2]);
		assertEquals(3, list.size());
		
		list.remove(2);
		assertEquals(2, list.size());
		
		list.remove(0);
		list.remove(0);
		assertEquals(0, list.size());
	}
	
	@Test
	void testGet() {
		LinkedList<String> list = new LinkedList<String>();
	
		list.add(TEST_STRINGS[0]);
		list.add(TEST_STRINGS[1]);
		list.add(TEST_STRINGS[2]);
		
		assertEquals(TEST_STRINGS[0], list.get(0));
		assertEquals(TEST_STRINGS[1], list.get(1));
		assertEquals(TEST_STRINGS[2], list.get(2));
	}
	
	@Test
	void testIndexOf() {
		LinkedList<String> list = new LinkedList<String>();
		
		assertEquals(-1, list.indexOf(TEST_STRINGS[0]));
		
		list.add(TEST_STRINGS[0]);
		assertEquals(0, list.indexOf(TEST_STRINGS[0]));
		
		list.add(TEST_STRINGS[1]);
		list.add(TEST_STRINGS[2]);
		assertEquals(0, list.indexOf(TEST_STRINGS[0]));
		assertEquals(1, list.indexOf(TEST_STRINGS[1]));
		assertEquals(2, list.indexOf(TEST_STRINGS[2]));
		
		list.remove(0);
		list.remove(0);
		list.remove(0);
		
		assertEquals(-1, list.indexOf(TEST_STRINGS[0]));
		assertEquals(-1, list.indexOf(TEST_STRINGS[1]));
		assertEquals(-1, list.indexOf(TEST_STRINGS[2]));
	}
	
	@Test
	void testIsEmpty() {
		LinkedList<String> list = new LinkedList<String>();
		
		assertTrue(list.isEmpty());
		
		list.add(TEST_STRINGS[0]);
		assertFalse(list.isEmpty());
		
		list.remove(0);
		assertTrue(list.isEmpty());
	}
	
	@Test
	void testContains() {
		LinkedList<String> list = new LinkedList<String>();
		
		assertFalse(list.contains(TEST_STRINGS[0]));
		
		list.add(TEST_STRINGS[0]);
		assertTrue(list.contains(TEST_STRINGS[0]));
		assertFalse(list.contains(TEST_STRINGS[1]));
		
		list.remove(0);
		assertFalse(list.contains(TEST_STRINGS[0]));
	}
	
	@Test
	void testAppendingAdd() {
		LinkedList<String> list = new LinkedList<String>();
		
		assertEquals(0, list.size());
		
		list.add(TEST_STRINGS[0]);
		assertEquals(1, list.size());
		assertEquals(TEST_STRINGS[0], list.get(0));
		
		list.add(TEST_STRINGS[1]);
		assertEquals(2, list.size());
		assertEquals(TEST_STRINGS[0], list.get(0));
		assertEquals(TEST_STRINGS[1], list.get(1));
	}
	
	@Test
	void testIndexAdd() {
		LinkedList<String> list = new LinkedList<String>();
		
		assertEquals(0, list.size());
		
		list.add(0, TEST_STRINGS[2]);
		assertEquals(1, list.size());
		assertEquals(TEST_STRINGS[2], list.get(0));
		
		// Error checking.
		assertThrows(IndexOutOfBoundsException.class, () -> {
			list.add(-1, "This shouldn't be added.");
		});
		assertThrows(IndexOutOfBoundsException.class, () -> {
			list.add(3, "This shouldn't be added either.");
		});
		
		list.add(0, TEST_STRINGS[0]);
		assertEquals(2, list.size());
		assertEquals(TEST_STRINGS[0], list.get(0));
		assertEquals(TEST_STRINGS[2], list.get(1));
		
		list.add(1, TEST_STRINGS[1]);
		assertEquals(3, list.size());
		assertEquals(TEST_STRINGS[0], list.get(0));
		assertEquals(TEST_STRINGS[1], list.get(1));
		assertEquals(TEST_STRINGS[2], list.get(2));
		
		list.add(3, TEST_STRINGS[3]);
		assertEquals(4, list.size());
		assertEquals(TEST_STRINGS[0], list.get(0));
		assertEquals(TEST_STRINGS[1], list.get(1));
		assertEquals(TEST_STRINGS[2], list.get(2));
		assertEquals(TEST_STRINGS[3], list.get(3));
	}
	
	@Test
	void testAddAll() {
		LinkedList<String> list = new LinkedList<String>();
		LinkedList<String> list2 = new LinkedList<String>();
		
		// Try adding to an empty list to an empty list.
		list.addAll(list2);
		assertEquals(0, list.size());
		
		// Try adding a list of size 1 to an empty list.
		list2.add(TEST_STRINGS[0]);
		list.addAll(list2);
		assertEquals(1, list.size());
		assertEquals(TEST_STRINGS[0], list.get(0));
		
		// Try adding a list with multiple elements to an empty list.
		list = new LinkedList<String>();
		list2.add(TEST_STRINGS[1]);
		list2.add(TEST_STRINGS[2]);
		list.addAll(list2);
		assertEquals(3, list.size());
		assertEquals(TEST_STRINGS[0], list.get(0));
		assertEquals(TEST_STRINGS[1], list.get(1));
		assertEquals(TEST_STRINGS[2], list.get(2));
		
		// Try adding an empty list to a list with 1 element.
		list = new LinkedList<String>();
		list2 = new LinkedList<String>();
		
		// Try adding a list with 1 element to a list with 1 element.
		list.add(TEST_STRINGS[0]);
		list.addAll(list2);
		assertEquals(1, list.size());
		assertEquals(TEST_STRINGS[0], list.get(0));
		
		// Try adding a list with multiple elements to a list with 1 element.
		list = new LinkedList<String>();
		list.add(TEST_STRINGS[0]);
		
		list2 = new LinkedList<String>();
		list2.add(TEST_STRINGS[1]);
		list2.add(TEST_STRINGS[2]);
		list2.add(TEST_STRINGS[3]);
		
		list.addAll(list2);
		assertEquals(4, list.size());
		assertEquals(TEST_STRINGS[0], list.get(0));
		assertEquals(TEST_STRINGS[1], list.get(1));
		assertEquals(TEST_STRINGS[2], list.get(2));
		assertEquals(TEST_STRINGS[3], list.get(3));
		
		// Try adding an empty list to a list with multiple elements.
		list = new LinkedList<String>();
		list2 = new LinkedList<String>();
		
		list.add(TEST_STRINGS[0]);
		list.add(TEST_STRINGS[1]);
		
		list.addAll(list2);
		assertEquals(2, list.size());
		assertEquals(TEST_STRINGS[0], list.get(0));
		assertEquals(TEST_STRINGS[1], list.get(1));
		
		// Try adding a list with 1 element to a list with multiple elements.
		list2.add(TEST_STRINGS[2]);

		list.addAll(list2);
		assertEquals(3, list.size());
		assertEquals(TEST_STRINGS[0], list.get(0));
		assertEquals(TEST_STRINGS[1], list.get(1));
		assertEquals(TEST_STRINGS[2], list.get(2));
	
		// Try adding a list with multiple elements to a list with multiple elements.
		list = new LinkedList<String>();
		list2 = new LinkedList<String>();
		
		list.add(TEST_STRINGS[0]);
		list.add(TEST_STRINGS[1]);
		
		list2.add(TEST_STRINGS[2]);
		list2.add(TEST_STRINGS[3]);
		
		list.addAll(list2);
		assertEquals(4, list.size());
		assertEquals(TEST_STRINGS[0], list.get(0));
		assertEquals(TEST_STRINGS[1], list.get(1));
		assertEquals(TEST_STRINGS[2], list.get(2));
		assertEquals(TEST_STRINGS[3], list.get(3));
	}
	
	@Test
	void testRemove() {
		LinkedList<String> list = new LinkedList<String>();
		for (String s : TEST_STRINGS) {
			list.add(s);
		}
		
		// Test removing an element from the middle.
		list.remove(1);
		assertEquals(3, list.size());
		assertEquals(TEST_STRINGS[0], list.get(0));
		assertEquals(TEST_STRINGS[2], list.get(1));
		assertEquals(TEST_STRINGS[3], list.get(2));
	
		// Test removing an element from the front.
		list.remove(0);
		assertEquals(2, list.size());
		assertEquals(TEST_STRINGS[2], list.get(0));
		assertEquals(TEST_STRINGS[3], list.get(1));
		
		// Test removing an element from the end.
		list.remove(1);
		assertEquals(1, list.size());
		assertEquals(TEST_STRINGS[2], list.get(0));
		
		// Test removing the last element.
		list.remove(0);
		assertEquals(0, list.size());
	}
	
	@Test
	void testSet() {
		LinkedList<String> list = new LinkedList<String>();
		list.add(TEST_STRINGS[2]);
		
		list.set(0, TEST_STRINGS[0]);
		assertEquals(TEST_STRINGS[0], list.get(0));
		
		list.add(TEST_STRINGS[1]);
		list.add(TEST_STRINGS[2]);
		
		list.set(1, TEST_STRINGS[3]);
		assertEquals(TEST_STRINGS[0], list.get(0));
		assertEquals(TEST_STRINGS[3], list.get(1));
		assertEquals(TEST_STRINGS[2], list.get(2));
		
		list.set(2, TEST_STRINGS[1]);
		assertEquals(TEST_STRINGS[0], list.get(0));
		assertEquals(TEST_STRINGS[3], list.get(1));
		assertEquals(TEST_STRINGS[1], list.get(2));
	}
	
	@Test
	void testClear() {
		LinkedList<String> list = new LinkedList<String>();
		
		list.clear();
		assertEquals(0, list.size());
		
		list.add(TEST_STRINGS[0]);
		list.clear();
		assertEquals(0, list.size());
		
		list.add(TEST_STRINGS[0]);
		list.add(TEST_STRINGS[1]);
		list.add(TEST_STRINGS[2]);
		list.clear();
		assertEquals(0, list.size());
	}
	
	@Test
	void testToString() {
		String RESULT_0 = "[]";
		String RESULT_1 = "[" + TEST_STRINGS[0] + "]";
		String RESULT_2 = "[" + TEST_STRINGS[0] + ", " + TEST_STRINGS[1] + "]";
		String RESULT_3 = "[" + TEST_STRINGS[0] + ", " + TEST_STRINGS[1] + ", " + TEST_STRINGS[2] + "]";
		
		LinkedList<String> list = new LinkedList<String>();
		
		assertEquals(RESULT_0, list.toString());
		
		list.add(TEST_STRINGS[0]);
		assertEquals(RESULT_1, list.toString());
		
		list.add(TEST_STRINGS[1]);
		assertEquals(RESULT_2, list.toString());
		
		list.add(TEST_STRINGS[2]);
		assertEquals(RESULT_3, list.toString());
	}
}
