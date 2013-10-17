package mcp.mobius.waila.gui.widgets;

import mcp.mobius.waila.gui.interfaces.IWidget;
import net.minecraft.util.MathHelper;

import org.lwjgl.util.Point;



public class WidgetGeometry {
	
	public enum Align {LEFT, CENTER, RIGHT, TOP, BOTTOM};
	
	//double relX = -1;
	//double relY = -1;
	//int    absX = -1;
	//int    absY = -1;

	//double relSX = -1;
	//double relSY = -1;
	//int    absSX = -1;
	//int    absSY = -1;	

	//boolean isAbsolute;
	
	double  x = -1;
	double  y = -1;
	double sx = -1;
	double sy = -1;
	
	boolean fracPosX;
	boolean fracPosY;
	boolean fracSizeX;
	boolean fracSizeY;
	
	Align alignX;
	Align alignY;
	
	public class PointDouble{
		double x; double y;
		public PointDouble(double x, double y){ this.x = x; this.y = y;};
	}	
	
	public WidgetGeometry(double x, double y, double sx, double sy, boolean fracPos, boolean fracSize){
		this(x, y, sx, sy, fracPos, fracSize, Align.LEFT, Align.TOP);
	}
	
	public WidgetGeometry(double x, double y, double sx, double sy, boolean fracPos, boolean fracSize, Align alignX, Align alignY){
		this(x, y, sx, sy, fracPos, fracPos, fracSize, fracSize, alignX, alignY);
	}

	public WidgetGeometry(double x, double y, double sx, double sy, boolean fracPosX, boolean fracPosY, boolean fracSizeX, boolean fracSizeY, Align alignX, Align alignY){
		this.x = x;
		this.y = y;
		this.sx = sx;
		this.sy = sy;
		this.fracPosX = fracPosX;
		this.fracPosY = fracPosY;
		this.fracSizeX = fracSizeX;
		this.fracSizeY = fracSizeY;
		this.alignX = alignX;
		this.alignY = alignY;
	}	
	
	public Point getPos(IWidget parent){
		
		int x = -1;
		if (this.fracPosX)
			x = MathHelper.ceiling_double_int(parent.getPos().getX() + parent.getSize().getX() * this.x / 100D);
		if (!this.fracPosX && parent != null)
			x = parent.getPos().getX() + (int)this.x;
		if (!this.fracPosX && parent == null)
			x = (int)this.x;
		
		int y = -1;
		if (this.fracPosY)
			y = MathHelper.ceiling_double_int(parent.getPos().getY() + parent.getSize().getY() * this.y / 100D);
		if (!this.fracPosY && parent != null)
			y = parent.getPos().getY() + (int)this.y;
		if (!this.fracPosY && parent == null)
			y = (int)this.y;
		
		
		if (this.alignX == Align.CENTER)
			x -= this.getSize(parent).getX() / 2;
		
		if (this.alignY == Align.CENTER)
			y -= this.getSize(parent).getY() / 2;
		
		if (this.alignX == Align.RIGHT)
			x -= this.getSize(parent).getX();
		
		if (this.alignY == Align.BOTTOM)
			y -= this.getSize(parent).getY();
		
		return new Point(x,y);
	}
	
	public Point getSize(IWidget parent){
		int sx = -1;
		if (this.fracSizeX)
			sx = MathHelper.ceiling_double_int(parent.getSize().getX() * this.sx / 100D);
		if (!this.fracSizeX)
			sx = (int)this.sx;
		
		int sy = -1;
		if (this.fracSizeY)
			sy = MathHelper.ceiling_double_int(parent.getSize().getY() * this.sy / 100D);
		if (!this.fracSizeY)
			sy = (int)this.sy;
		
		return new Point(sx,sy);
	}	
}