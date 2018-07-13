package simulation.process;

public enum Process_Priority {

	High(3),
	Normal(2),
	Low(1);
		
	private final int levelCode;

	Process_Priority(int levelCode) {
        this.levelCode = levelCode;
    }
	    
	public int getLevelCode() {
	    return this.levelCode;
	}
}
