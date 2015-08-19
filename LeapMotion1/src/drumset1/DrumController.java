/*package drumset1;

public class DrumController {

	private final int MAX_INSTRUMENTS = 4;
	
	private LeapListener leapListener;
	private DrumGui gui;
	private MidiPlayer[] players;
	
	private DrumController instance = null;
	private DrumController()
	{
		init();
	}
	
	public DrumController getInstance()
	{
		if (instance == null)
		{
			instance = new DrumController();
		}
		return instance;
	}
	
	private void init()
	{
		
		for (int i= 0; i <MAX_INSTRUMENTS; ++i )
		{
			players[i] = new MidiPlayer(i);
		}
		
		gui = new DrumGui("Drumset");
	}
}
*/