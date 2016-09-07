
public class Looping {

	int number;
	int fromNumber;
	int toNumber;
	int step;
	boolean increase = true;
	
	public Looping(int fromNumber, int toNumber, int step) {
		super();
		this.fromNumber = fromNumber;
		this.toNumber = toNumber;
		this.step = step;
		this.number = fromNumber;
		if(fromNumber<toNumber) increase = true;
	}

	public void reverse() {
		increase = !increase;
	}
	
	public void reset() {
		if(increase) {
			number = fromNumber;
		} else {
			number = toNumber;
		}
	}
	
	public boolean next() {
		if(increase) {
			if(number>=toNumber) return false;
			number += step;
		} else {
			if(number<=fromNumber) return false;
			number -= step;
		}
		return true;
	}
	
	public int getNumber() {
		if(increase) {
			return (number - step);
		} else {
			return (number + step);
		}
	}
	
}
