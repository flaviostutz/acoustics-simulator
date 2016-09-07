package br.acousticsim.tests;
import javax.media.j3d.PickRay;
import javax.media.j3d.TriangleArray;
import javax.vecmath.Point3d;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3d;

import junit.framework.TestCase;
import br.acousticsim.math.IntersectionInfo;
import br.acousticsim.math.Vectors;

/*
 * Created on 02/05/2005
 */

/**
 * @author Flávio Stutz (flaviostutz@uol.com.br)
 */
public class TestVectors extends TestCase {

	public void testIsEdgePoint() {
		TriangleArray triangle = new TriangleArray(3, TriangleArray.COORDINATES);
		triangle.setCoordinate(0, new float[]{0,0,3});
		triangle.setCoordinate(1, new float[]{2,0,3});
		triangle.setCoordinate(2, new float[]{1,2,3});
		
		Point3f p = new Point3f();

		p.set(0,0,3);
		assertTrue(Vectors.isEdgepoint(triangle, p, 0));

		p.set(2,0,3);
		assertTrue(Vectors.isEdgepoint(triangle, p, 0));

		p.set(1,2,3);
		assertTrue(Vectors.isEdgepoint(triangle, p, 0));

		p.set(1,1,3);
		assertFalse(Vectors.isEdgepoint(triangle, p, 0));
		
		p.set(5,1,2);
		assertFalse(Vectors.isEdgepoint(triangle, p, 0));
	}
	
	
	public void testIsPointInsideTriangle() {
		TriangleArray triangle = new TriangleArray(3, TriangleArray.COORDINATES);
		triangle.setCoordinate(0, new float[]{0,0,3});
		triangle.setCoordinate(1, new float[]{2,0,3});
		triangle.setCoordinate(2, new float[]{1,2,3});
		
		Point3f p = new Point3f();

		p.set(1,1,3);
		assertTrue(Vectors.isPointInsideTriangle(triangle, p, 0));

		p.set(0,0,3);
		assertTrue(Vectors.isPointInsideTriangle(triangle, p, 0));

		p.set(2.1F,0,3);
		assertFalse(Vectors.isPointInsideTriangle(triangle, p, 0));

		p.set(2,2,3);
		assertFalse(Vectors.isPointInsideTriangle(triangle, p, 0));

		p.set(-2,-2,-7);
		assertFalse(Vectors.isPointInsideTriangle(triangle, p, 0));
	}
		
	public void testIsPointInsideTriangle2() {
		TriangleArray triangle = new TriangleArray(3, TriangleArray.COORDINATES);
		triangle.setCoordinate(0, new float[]{0.5F,0.5F,0.5F});
		triangle.setCoordinate(1, new float[]{0.5F,0.5F,-0.5F});
		triangle.setCoordinate(2, new float[]{-0.5F,0.5F,0.5F});

		Point3f p = new Point3f();

		p.set(0.25F,0,0.5F);
		assertTrue(Vectors.isPointInsideTriangle(triangle, p, 0));
	}
	
	public void testFindIntersectionPointTriangle1() {
		TriangleArray triangle = new TriangleArray(3, TriangleArray.COORDINATES);
		triangle.setCoordinate(0, new double[]{0,0,3});
		triangle.setCoordinate(1, new double[]{2,0,3});
		triangle.setCoordinate(2, new double[]{1,2,3});
		
		Point3d p = new Point3d(0,0,0);
		Vector3d v = new Vector3d(1,1,3);
		PickRay ray = new PickRay(p, v);
		
		IntersectionInfo ii = Vectors.findIntersectionPoint(triangle, ray);
		assertTrue(ii.getPoint().x==1);
		assertTrue(ii.getPoint().y==1);
		assertTrue(ii.getPoint().z==3);
	}
	
	public void testFindIntersectionPointTriangle2() {
		TriangleArray triangle = new TriangleArray(3, TriangleArray.COORDINATES);
		triangle.setCoordinate(0, new double[]{1,1,1});
		triangle.setCoordinate(1, new double[]{-1,1,1});
		triangle.setCoordinate(2, new double[]{1,1,-1});
		
		Point3d p = new Point3d(-0.5, 0, -0.5);
		Vector3d v = new Vector3d(0.5,0.5,1);
		PickRay ray = new PickRay(p, v);
		
		IntersectionInfo ii = Vectors.findIntersectionPoint(triangle, ray);
		//assertNotNull(ii);
	}
	
}
