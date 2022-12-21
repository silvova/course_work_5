package gomoku.history;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Iterator;

public class History implements Serializable{

	private LinkedList<Element> hist;
	public History(){
		this.hist = new LinkedList<Element>();
	}
	public void addMove(int x, int y, char player){
		Element el = new Element(x, y, player);
		this.hist.addLast(el);
	}

	public int getSize(){
		return hist.size();
	}

	@Override
	public final String toString(){
		StringBuilder str = new StringBuilder();
		int i = 0;
		Iterator<Element> iter = this.hist.iterator();
		str.append("History{\n");
		while(iter.hasNext()) {
			Element el = iter.next();
			i++;
			str.append("" + i + ":\t");
			str.append(el);
			str.append(",\n");
		}
		str.append("}\n");
		return str.toString();
	}
}