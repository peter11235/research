package pete1;

public interface PObservable {

	public void registerObserver(PObserver o);
	public void deleteObserver(PObserver o);
	public void setChanged();
	public void notifyObservers();
}
