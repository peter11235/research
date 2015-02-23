package virtual;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class VirtualScreen {

	private int height;
	private int width;
	private int dUser;
	private int dLeap;
	private int dTotal;
	private Point grid;
	private Rectangle[][] segments;
	
	public VirtualScreen(){};//For testing purposes
	
	public VirtualScreen(int aWidth, int aHeight, Point aGrid, int user, int leap)
	{
		width = aWidth;
		height = aHeight;
		grid = aGrid;
		dUser = user;
		dLeap = leap;
		dTotal = dUser + dLeap;
		
		int segmentWidth = width / grid.x;
		int segmentHeight = height/ grid.y;
		
		segments = new Rectangle[grid.x][grid.y];

		for (int i= 0; i < grid.y; i++)
		{
			for (int j = 0; j < grid.x; j++)
			{
				Rectangle r = new Rectangle(j*segmentWidth, i*segmentHeight, segmentWidth, segmentHeight);
				segments[j][i] = r;
			}
		}
	}
	
	
	public Point getLittleScreenLocation(float pitch, float yaw)
	{	
		
		float adjacent = dTotal;
		float oppositeY = (float)(adjacent * Math.tan((double)pitch));
		float oppositeX = (float)(adjacent * Math.tan((double)yaw));
		
		return new Point((int)oppositeX, (int)oppositeY);
	}
	
	public Point whichSegment(float pitch, float yaw)
	{
		Point location = getLittleScreenLocation(pitch, yaw);
		for(int i = 0; i < segments.length; i++)
		{
			for (int j = 0; j < segments[i].length; j++)
			{
				if (segments[i][j].contains(location)) return new Point(i,j);
			}
		}
		return null;
	}
	
}
