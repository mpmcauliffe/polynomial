package poly;

import java.io.*;
import java.util.StringTokenizer;

/**
 * This class implements a term of a polynomial.
 * 
 * @author runb-cs112
 *
 */
class Term {
	/**
	 * Coefficient of term.
	 */
	public float coeff;
	
	/**
	 * Degree of term.
	 */
	public int degree;
	
	/**
	 * Initializes an instance with given coefficient and degree.
	 * 
	 * @param coeff Coefficient
	 * @param degree Degree
	 */
	public Term(float coeff, int degree) {
		this.coeff = coeff;
		this.degree = degree;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object other) {
		return other != null &&
		other instanceof Term &&
		coeff == ((Term)other).coeff &&
		degree == ((Term)other).degree;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		if (degree == 0) {
			return coeff + "";
		} else if (degree == 1) {
			return coeff + "x";
		} else {
			return coeff + "x^" + degree;
		}
	}
}

/**
 * This class implements a linked list node that contains a Term instance.
 * 
 * @author runb-cs112
 *
 */
class Node {
	
	/**
	 * Term instance. 
	 */
	Term term;
	
	/**
	 * Next node in linked list. 
	 */
	Node next;
	
	/**
	 * Initializes this node with a term with given coefficient and degree,
	 * pointing to the given next node.
	 * 
	 * @param coeff Coefficient of term
	 * @param degree Degree of term
	 * @param next Next node
	 */
	public Node(float coeff, int degree, Node next) {
		term = new Term(coeff, degree);
		this.next = next;
	}
}

/**
 * This class implements a polynomial.
 * 
 * @author runb-cs112
 *
 */
public class Polynomial {
	
	/**
	 * Pointer to the front of the linked list that stores the polynomial. 
	 */ 
	Node poly;
	
	/** 
	 * Initializes this polynomial to empty, i.e. there are no terms.
	 *
	 */
	public Polynomial() {
		poly = null;
	}

//	private void addToFront(Term term) {
//		Term node = new Term(employee);
//
//		node.setNext(head);
//		head = node;
//
//		size++;
//	}
	
	/**
	 * Reads a polynomial from an input stream (file or keyboard). The storage format
	 * of the polynomial is:
	 * <pre>
	 *     <coeff> <degree>
	 *     <coeff> <degree>
	 *     ...
	 *     <coeff> <degree>
	 * </pre>
	 * with the guarantee that degrees will be in descending order. For example:
	 * <pre>
	 *      4 5
	 *     -2 3
	 *      2 1
	 *      3 0
	 * </pre>
	 * which represents the polynomial:
	 * <pre>
	 *      4*x^5 - 2*x^3 + 2*x + 3 
	 * </pre>
	 * 
	 * @param br BufferedReader from which a polynomial is to be read
	 * @throws IOException If there is any input error in reading the polynomial
	 */
	public Polynomial(BufferedReader br) throws IOException {
		String line;
		StringTokenizer tokenizer;
		float coeff;
		int degree;
		
		poly = null;
		
		while ((line = br.readLine()) != null) {
			tokenizer = new StringTokenizer(line);
			coeff = Float.parseFloat(tokenizer.nextToken());
			degree = Integer.parseInt(tokenizer.nextToken());
			poly = new Node(coeff, degree, poly);
		}
	}
	
	
	/**
	 * Returns the polynomial obtained by adding the given polynomial p
	 * to this polynomial - DOES NOT change this polynomial
	 * 
	 * @param p Polynomial to be added
	 * @return A new polynomial which is the sum of this polynomial and p.
	 */
	public Polynomial add(Polynomial p) {

		Node poly1 		 = this.poly;			// input 1
		Node poly2 		 = p.poly;				// input 2
		Polynomial sum   = new Polynomial(); 	// for sum
		Node set;       //= new Node(poly1.term.coeff + poly2.term.coeff, poly1.term.degree, null); 								// build node to put into sum

//		sum.poly = set;
//		System.out.println(sum.poly.term);
//		sum.poly = sum.poly.next;
//		set = new Node(poly1.next.term.coeff + poly2.next.term.coeff, poly1.next.term.degree, null);
//		sum.poly = set;
//		System.out.println(sum.poly.term);

		while (poly1 != null || poly2 != null) {

			if (poly1 != null && poly2 != null) {
				/** If each term has equal degrees **/
				if (poly1.term.degree == poly2.term.degree) {
					/** Build node **/
					set = new Node(poly2.term.coeff + poly1.term.coeff,
							poly1.term.degree,
							null);


					sum.poly = set;            // put node into sum
					poly1 = poly1.next;            // increment poly1
					poly2 = poly2.next;            // increment poly2
					System.out.println(sum.poly.term);

				} else if (poly1.term.degree > poly2.term.degree) {
					/** Only adding term from poly2 **/
					set = new Node(poly2.term.coeff, poly2.term.degree, null);

					sum.poly = set;            // put node into sum
					poly2 = poly2.next;            // increment poly2 and only poly2
					System.out.println(sum.poly.term);

				} else {
					/** Only adding term from poly1 **/
					set = new Node(poly1.term.coeff, poly1.term.degree, null);

					sum.poly = set;            // put node into sum
					poly1 = poly1.next;            // increment poly1 and only poly1
					System.out.println(sum.poly.term);
				}
			}

			if (poly1 != null && poly2 == null) {
				set = new Node(poly1.term.coeff, poly1.term.degree, null);

				sum.poly = set;            // put node into sum
				poly1 = poly1.next;            // increment poly1 and only poly1
				System.out.println(sum.poly.term);
			} else if (poly1 == null && poly2 != null) {
				set = new Node(poly2.term.coeff, poly2.term.degree, null);

				sum.poly = set;            // put node into sum
				poly2 = poly2.next;            // increment poly2 and only poly2
				System.out.println(sum.poly.term);
			}
			sum.poly = sum.poly.next;
		}

//		while (sum.poly.next != null) {
//			System.out.print(sum.poly.term + "  ");
//			sum.poly = sum.poly.next;
//		}
//		while (poly1 != null) {
//			System.out.print(poly1.term + "  ");
//			poly1 = poly1.next;
//		}
//		System.out.print("\n");
//		while (poly2 != null) {
//			System.out.print(poly2.term + "  ");
//			poly2 = poly2.next;
//		}
//		System.out.print("\n");

		return sum;
	}
	
	/**
	 * Returns the polynomial obtained by multiplying the given polynomial p
	 * with this polynomial - DOES NOT change this polynomial
	 * 
	 * @param p Polynomial with which this polynomial is to be multiplied
	 * @return A new polynomial which is the product of this polynomial and p.
	 */
	public Polynomial multiply(Polynomial p) {
		/** COMPLETE THIS METHOD **/
		return null;
	}
	
	/**
	 * Evaluates this polynomial at the given value of x
	 * 
	 * @param x Value at which this polynomial is to be evaluated
	 * @return Value of this polynomial at x
	 */
	public float evaluate(float x) {
		/** COMPLETE THIS METHOD **/
		return 0;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		String retval;
		
		if (poly == null) {
			return "0";
		} else {
			retval = poly.term.toString();
			for (Node current = poly.next ;
			current != null ;
			current = current.next) {
				retval = current.term.toString() + " + " + retval;
			}
			return retval;
		}
	}
}
