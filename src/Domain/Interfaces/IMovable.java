package Domain.Interfaces;

import Domain.Primitives.Shooter;
import Domain.StatisticsWindow;

public interface IMovable {
	float[] getPosition();
	void step(float stepSize); //Will be used to step the object through the game.
	int getType();
	void modifyStatisticsWindow(StatisticsWindow statisticsWindow, float efficiency);
	boolean isMolecule();
	boolean isReactionBlocker();
	int removeOperation(StatisticsWindow statisticsWindow, Shooter shooter, float l);
}
