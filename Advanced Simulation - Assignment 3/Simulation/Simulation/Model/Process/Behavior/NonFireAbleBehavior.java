package Simulation.Model.Process.Behavior;

public class NonFireAbleBehavior implements IProcessFireBehavior {

	@Override
	public void Fire() {
		// Do nothing
		
	}

	@Override
	public boolean CanFire() {
		return false;
	}

}
