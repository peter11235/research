package pete1;

public class Location {

	public float x, y,z;
	private final float PAD = 10;
	
	public Location(float x, float y, float z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	boolean isClose(Location other)
	{
		if (other.x >= (this.x - PAD) && other.x <= (this.x + PAD))
		{
			if (other.y >= (this.y - PAD) && other.y <= (this.y + PAD))
			{
				if (other.z >= (this.z - PAD) && other.z <= (this.z + PAD))
				{
					return true;
				}
			}
		}
		return false;
	}
}
