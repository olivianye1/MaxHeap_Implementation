/**
 * Olivia Nye
 * 
 * MaxHeap Class
 * 
 * Intro to Algorithms Project 1 - Milestone 2
 * 
 * This class implements a maxheap, including a HeapSort algorithm that returns a sorted heap in descending order, as is consistent with the properties of a MaxHeap.
 * 
 *  */
package alg_project1_MaxHeap;

import java.util.*; 
  
public class MaxHeap <E extends Comparable<? super E>>{
	protected ArrayList<Integer> A;
	protected int i;
	
	/**
	 * constructor for MaxHeap class
	 */
	public MaxHeap(){
		A = new ArrayList<Integer>();
		
	}


	/**
	 * helper method for heapsort that finds the position of a an elements left child
	 * @param A heap in arraylist form
	 * @param i element whose left child we are interested in
	 * @return the position of i's left child in A
	 */
	protected int getLeft(ArrayList<Integer> A, int i) {
		// uses heap rule about finding child indices, but accounts for the fact that our ArrayList starts at 0, not 1
		int leftChildIndex = (2*(i + 1)) - 1;
		return leftChildIndex;
	}
	
	/**
	 * helper method for heapsort that finds the position of a an elements right child
	 * @param A heap in arraylist form
	 * @param i element whose right child we are interested in
	 * @return the position of i's right child in A
	 */
	protected int getRight(ArrayList<Integer> A, int i) {
		// uses heap rule about finding child indices, but accounts for the fact that our ArrayList starts at 0, not 1
		int rightChildIndex = (2*(i + 1));
		return rightChildIndex;
	}
	
	/**
	 * helper method for heapsort that changes the order of the heap elements so that all nodes satisfy the max heap property
	 * @param A ArrayList being heapsorted
	 * @param i position of element in the heap that needs to be checked to see if it satisfies the property or not
	 * @return heapified arraylist
	 */
	protected ArrayList<Integer> MaxHeapify(ArrayList<Integer> A, int i) {
		int largerChild;
		int leftIndex = getLeft(A, i);
		int rightIndex = getRight(A, i);
		
		//base case
		if (A.size() < 1) {
			return A;
		}
		//is leaf
		if (rightIndex > A.size() - 1 && leftIndex > A.size()-1) {
			return A;
		}
		

			
		//has no left child but has right child
		if (rightIndex <= A.size() - 1 && leftIndex > A.size()-1) {
			largerChild = rightIndex;
			//child is bigger than parent
			if (A.get(i) < A.get(largerChild)) {
				Collections.swap(A, largerChild, i);
				MaxHeapify(A, i++);
			}
		}

		//has no right child but has left child
		else if (leftIndex <= A.size() - 1 && rightIndex > A.size()-1) {
			largerChild = leftIndex;
			//child is bigger than parent
			if (A.get(i) < A.get(largerChild)) {
				Collections.swap(A, largerChild, i);
				MaxHeapify(A, i++);
			}
		}
		//has both kids
		else {
			//choose largest child
			if (A.get(leftIndex) > A.get(rightIndex)) {
				largerChild = leftIndex;
			}
			else {
				largerChild = rightIndex;
			}
			
			//compare largest child to parent
			if (A.get(i) < A.get(largerChild)) {
				Collections.swap(A, largerChild, i);
				MaxHeapify(A, i++);
			}
		}
		return A;
	}

	
	/**
	 * helper method for heapsort that takes the ArrayList and transforms it into a maxheapified arraylist, from the root down to the leaves
	 * @param A ArrayList being maxheapified
	 * @return maxheapified arraylist
	 */
	protected ArrayList<Integer> BuildMaxHeap(ArrayList<Integer> A) {
		int n = A.size() - 1;
		int mid = n/2;
		
		for (int x = mid; x >= 0; x-- ) {
			A = MaxHeapify(A, x);
		}
		return A;
	}

	//take in unsorted array, returns sorted array that satisfies max heap properties (AKA a sorted max heap)
	/**
	 * implements heapsort algorithm with a max heap, descending sort
	 * @param A arraylist being heap sorted
	 * @return returns arraylist in sorted descending order that satisfies max heap properties (AKA a sorted max heap)
	 */
	protected ArrayList<Integer> HeapSort(ArrayList<Integer> A){
		ArrayList<Integer> myHeap = new ArrayList<Integer>();
		A = BuildMaxHeap(A);
		
		while (A.size() > 0) {
			//in array, swap root with last element
			Collections.swap(A,  0, A.size() - 1);
			//add the new last element (old root) to new heap
			myHeap.add(A.get(A.size() - 1));
			//remove last element from array
			A.remove(A.size()- 1);
			A = MaxHeapify(A, 0);

		    for (Integer number : myHeap) {
		        System.out.println("Number = " + number);
		     } 
		}
		return myHeap;
	}
	
	
	
}
