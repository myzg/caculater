package calulatorprogram;

public class Indexof {
	public int index;	//下标；

	public int rnumber() {		//本次处理字符的后一个字符；
		int toindex = index;
		return toindex;
	}
	public int lnumber() {
		int toindex= index - 2;		//本次处理字符的前一个字符；
		return toindex;
	}
	public void sub() {			//更新下标
		index--;
	}
	public void add() {				//更新下标
		index++;
	}
	public Indexof(int index) {//构造方法
		super();
		this.index = index;
	}

	public int getIndex() {		//返回下标
		
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
}
