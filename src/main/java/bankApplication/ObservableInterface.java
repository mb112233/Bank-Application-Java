package bankApplication;
public interface ObservableInterface {
	public abstract void removeObserver(ObserverInterface o);
	public abstract  void addObserver(ObserverInterface o);
	public abstract  void notifyObserver();
}
