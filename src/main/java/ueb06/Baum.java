package ueb06;

import java.util.Stack;

class Baum<T extends Comparable<T>> {
	private class Element {
		T value;
		Element left, right;
		Element(T value) { this.value = value; }
		void addRekElem(T v){
			if(v.equals(value))return;
			else if (this.value.compareTo(v) > 0) {
				if (left == null) {
					left = new Element(v);
					return;
				} else {
					left.addRekElem(v);
					return;
				}
			} else {
					if (right == null) {
						right = new Element(v);
						return;
					} else {
						right.addRekElem(v);
						return;
					}
				}
		}
		boolean containsRek(T value){
				int c = this.value.compareTo(value);
				if (c == 0)
					return true;
				else if (c > 0)
					if(left!=null)
						return left.containsRek(value);
					else return false;
				else
					if(right!=null)
						return right.containsRek(value);
					else return false;
		}
		@Override
		public String toString(){
			if(left==null)
				if(right!=null) return value + ", " + right;
				else return value.toString();
			else
				if(right!=null) return left + ", " + value + ", "+ right;
				else return left + ", " + value;
		}
	}

	private Element root;

	/**
	 * Fügt ein Element in den Baum ein.
	 */
	void add(T value) {
		if (root == null) {
			root = new Element(value);
			return;
		}

		Element it = root;
		while (it != null) {
			int c = value.compareTo(it.value);

			if (c == 0)
				return;
			else if (c < 0) {
				if (it.left == null) {
					it.left = new Element(value);
					return;
				} else {
					it = it.left;
				}
			} else {
				if (it.right == null) {
					it.right = new Element(value);
					return;
				} else {
					it = it.right;
				}
			}
		}
	}

	/**
	 *  Wie `add`, aber rekursiv zu implementieren.
	 */
	void addRek(T value) {
		if (root == null) {
			root = new Element(value);
			return;
		}
		else root.addRekElem(value);
	}

	/**
	 * Gibt `true` zurück, wenn der Wert `value` im Baum enthalten ist.
	 */
	boolean contains(T value) {
		if (root == null)
			return false;

		Element it = root;
		while (it != null) {
			int c = value.compareTo(it.value);
			if (c == 0)
				return true;
			else if (c < 0)
				it = it.left;
			else
				it = it.right;
		}

		return false;
	}

	/**
	 * Wie `contains`, aber rekursiv zu implementieren.
	 */
	boolean containsRek(T value){
		if(root==null)
			return false;
		else return root.containsRek(value);
	}

	/**
	 * Gibt eine Stringrepraesentation des Baums zurück, wobei das Format
	 * eine aufsteigende sortierte Liste darstellt, z.B. [] oder [2, 3, 4].
	 * Abstrakt betrachtet ist dies die sog. Infixschreibweise, bei der fuer
	 * ein Element zuerst der linke Teilbaum, dann der Wert, dann der rechte
	 * Tb. ausgegeben wird.
	 */
	public String toString() {
		Stack<Element> agenda = new Stack<>();

		// Tiefenabstieg nach links
		Element it = root;
		while (it != null) {
			agenda.push(it);
			it = it.left;
		}

		StringBuilder sb = new StringBuilder();

		while (!agenda.empty()) {
			Element e = agenda.pop();
			sb.append(e.value);

			// Tiefenabstieg nach links
			it = e.right;
			while (it != null) {
				agenda.push(it);
				it = it.left;
			}

			if (agenda.size() > 0)
				sb.append(", ");
		}

		return "[" + sb.toString() + "]";
	}

	/**
	 * Zusatzaufgabe: Wie `toString`, nur rekursiv zu implementieren.
	 */
	String toStringRek() {
		if(root==null) return "[]";
		else return "[" + root.toString() + "]";
	}
}
