package soundgrid;

public class TestMain {

	public static void main(String[] args) {
		SoundBoard drumset = new Drumset();
		
		for (int i = 0; i < 10; i++)
		{
			((Drumset)drumset).snare(50);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}