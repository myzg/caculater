package calulatorprogram;

public class Indexof {
	public int index;	//�±ꣻ

	public int rnumber() {		//���δ����ַ��ĺ�һ���ַ���
		int toindex = index;
		return toindex;
	}
	public int lnumber() {
		int toindex= index - 2;		//���δ����ַ���ǰһ���ַ���
		return toindex;
	}
	public void sub() {			//�����±�
		index--;
	}
	public void add() {				//�����±�
		index++;
	}
	public Indexof(int index) {//���췽��
		super();
		this.index = index;
	}

	public int getIndex() {		//�����±�
		
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
}
