package pointee;

public class Pointee {

	float x;
	float y;
	float z;
	
	float targetPitch;
	float targetYaw;
	
	boolean isMetric;
	
	int sound;
	
	
	private final static float sqrt3 = 1.73205f;
	private final static float m2f= 3.2808f;//Meters to feet
	
	private final static float MAX_Z = 12;
	private final static float MIN_Z = 0;
	
	private final static float MAX_X = MAX_Z * sqrt3;
	private final static float MIN_X = -MAX_X;
	
	private final static float MAX_Y= 12;
	private final static float MIN_Y = 0;
	
	
	public Pointee(boolean metric)
	{
		x = y = z = 0f;
		isMetric = metric;
		if (isMetric)
		{
			x *= m2f;
			y *= m2f;
			z *= m2f;
			
			//metric support later. MURICA
		}

		getPitch();
		getYaw();
	}
	
	public Pointee(float aX, float aY, float aZ, boolean metric, int aSound)
	{
		x = aX;
		y = aY;
		z = aZ;
		
		isMetric = metric;
		sound = aSound;
		if (isMetric)
		{
			//metric stuff
		}
		
		if (x < MIN_X)
		{
			x = MIN_X;
		}
		else if (x > MAX_X)
		{
			x = MAX_X;
		}
		
		if (y < MIN_Y)
		{
			y = MIN_Y;
		}
		else if (y > MAX_Y)
		{
			y = MAX_Y;
		}
		
		if (z < MIN_Z)
		{
			z = MIN_Z;
		}
		else if (z > MAX_Z)
		{
			z = MAX_Z;
		}
		getPitch();
		getYaw();
	}
	
	public float getPitch()
	
	{
		targetPitch = (float)Math.atan(y/z);
		return targetPitch;
	}
	
	public float getYaw()
	{
		targetYaw = (float)Math.atan(x/z);
		return targetYaw;
	}
	
	
}
