
package br.acousticsim.math;

import java.util.Vector;

import javax.media.j3d.GeometryArray;
import javax.media.j3d.PickRay;
import javax.media.j3d.Shape3D;
import javax.media.j3d.TriangleArray;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3f;

/**
 * @author Flávio Stutz (flaviostutz@uol.com.br)
 */
public class IntersectionInfo {

	/**
	 * @param shape
	 * @param point
	 * @param geometry A geometry with just three points in space
	 * @param ray
	 */
	public IntersectionInfo(Shape3D shape, Point3f point,
			Vector<TriangleArray> triangleGeometries, PickRay ray) {
		super();
		this.shape = shape;
		this.point = point;
		this.triangleGeometries = triangleGeometries;
		this.ray = ray;
	}
	public Shape3D shape;
	public Point3f point;
	public Vector<TriangleArray> triangleGeometries;
	public PickRay ray;

	public double getRayToNormalAngle() {
		return Math.toDegrees(getResultingGeometriesNormal().angle(Vectors.getRayVector(ray)));
	}
	
	public PickRay getRay() {
		return ray;
	}
	public void setRay(PickRay ray) {
		this.ray = ray;
	}
	public Vector<TriangleArray> getTriangleGeometries() {
		return triangleGeometries;
	}
	public void setTriangleGeometries(Vector<TriangleArray> triangleGeometries) {
		this.triangleGeometries = triangleGeometries;
	}
	public Point3f getPoint() {
		return point;
	}
	public void setPoint(Point3f point) {
		this.point = point;
	}
	public Shape3D getShape() {
		return shape;
	}
	public void setShape(Shape3D shape) {
		this.shape = shape;
	}
	public String toString() {
		return point + " at geometry " + triangleGeometries;
	}
	
	public GeometryArray getGeometry() {
		return triangleGeometries.firstElement();
	}
	
	/**
	 * @return
	 */
	public Vector3f getResultingGeometriesNormal() {
		if(triangleGeometries.size()==0) return null;
		
		Vector3f normal = new Vector3f(0,0,0);

		for(TriangleArray triangle: triangleGeometries) {
			normal.add(Vectors.getTriangleNormal(triangle));
			normal.normalize();
		}
		
		return normal;
	}
}
