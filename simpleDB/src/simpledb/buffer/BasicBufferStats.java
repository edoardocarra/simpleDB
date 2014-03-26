package simpledb.buffer;

public class BasicBufferStats {

	private int lettureSuDisco;
	private int scrittureSuDisco;
	
	public BasicBufferStats() {
		this.lettureSuDisco = 0;
		this.scrittureSuDisco = 0;
	}

	public int getLettureSuDisco() {
		return lettureSuDisco;
	}

	public void setLettureSuDisco() {
		this.lettureSuDisco += 1;
	}

	public int getScrittureSuDisco() {
		return scrittureSuDisco;
	}

	public void setScrittureSuDisco() {
		this.scrittureSuDisco += 1;
	}
	
	
	
}
