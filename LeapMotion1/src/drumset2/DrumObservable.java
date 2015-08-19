package drumset2;

public interface DrumObservable {

	public void addObserver(DrumObserver d);
	public void deleteObserver(DrumObserver d);
	//public void notifyObservers();
	public void notifyObservers(Object arg);
	public void setChanged();
}
