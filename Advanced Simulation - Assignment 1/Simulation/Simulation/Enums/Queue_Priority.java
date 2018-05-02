package Simulation.Enums;

public enum Queue_Priority {
	High(3),
	Normal(2),
	Low(1);
	
	private final int levelCode;

	Queue_Priority(int levelCode) {
        this.levelCode = levelCode;
    }
    
    public int getLevelCode() {
        return this.levelCode;
    }
}
