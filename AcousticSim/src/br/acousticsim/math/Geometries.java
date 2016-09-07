/*
 * Created on 04/05/2005
 */
package br.acousticsim.math;

import java.awt.Color;
import java.util.Enumeration;
import java.util.Vector;

import javax.media.j3d.Appearance;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.Group;
import javax.media.j3d.LineArray;
import javax.media.j3d.LineAttributes;
import javax.media.j3d.PolygonAttributes;
import javax.media.j3d.Shape3D;
import javax.media.j3d.TriangleArray;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Point3f;

/**
 * @author Flávio Stutz (flaviostutz@uol.com.br)
 */
public class Geometries {

	public static TriangleArray createTriangleGeometry(Point3d v1, Point3d v2, Point3d v3) {
		TriangleArray triangle = new TriangleArray(3, TriangleArray.COORDINATES);
		Point3d[] tp = {v1, v2, v3};
		triangle.setCoordinates(0, tp);
		return triangle;
	}
	
	public static Shape3D createTriangle(Point3d v1, Point3d v2, Point3d v3) {
		
		Shape3D triangle = new Shape3D(createTriangleGeometry(v1, v2, v3));
		Appearance app = new Appearance();
		triangle.setAppearance(app);
		
		ColoringAttributes ca = new ColoringAttributes();
		app.setColoringAttributes(ca);
		
		PolygonAttributes pa = new PolygonAttributes();
		app.setPolygonAttributes(pa);
		pa.setCullFace(PolygonAttributes.CULL_NONE);
		
		return triangle;
	}
	
	public static Shape3D createLine(Point3f p1, Point3f p2) {
		
		//create geometry
		LineArray l = new LineArray(2, LineArray.COORDINATES);
		l.setCoordinates(0, new Point3f[]{p1, p2});

		//set appearance
		Appearance appearance = new Appearance();
		LineAttributes lineAttr = new LineAttributes(); 
		ColoringAttributes colorAttr = new ColoringAttributes(); 
		appearance.setLineAttributes(lineAttr);
		appearance.setColoringAttributes(colorAttr);
		
		lineAttr.setLinePattern(LineAttributes.PATTERN_SOLID);
		lineAttr.setLineWidth(1);
		lineAttr.setLineAntialiasingEnable(true);
		colorAttr.setColor(new Color3f(Color.LIGHT_GRAY));
		
		//create shape3D
		Shape3D lineShape = new Shape3D(l, appearance);
		return lineShape;
	}

	/**
	 * Extrai todas as folhas aninhadas em um grupo e as adiciona à uma coleção única
	 * @param group
	 * @return
	 */
	public static Vector<Shape3D> getAllShapes(Group group) {
		Vector<Shape3D> v = new Vector<Shape3D>();
		Enumeration e = group.getAllChildren();
		while(e.hasMoreElements()) {
			Object obj = e.nextElement();
			if(obj instanceof Group) {
				v.addAll(getAllShapes((Group)obj));
			} else if(obj instanceof Shape3D) {
				v.add((Shape3D)obj);
			}
		}
		return v;
	}
	
	
}
