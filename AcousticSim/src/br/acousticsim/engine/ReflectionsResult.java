package br.acousticsim.engine;

import java.util.Vector;

import javax.media.j3d.PickRay;
import javax.media.j3d.Shape3D;

/**
 * Guarda o resultado do processamento de um raio em um certo grupo de objetos
 * @author f3310193
 */
public class ReflectionsResult {

	Vector<ReflectionInfo> reflections;
	PickRay ray;
	Vector<Shape3D> shapes;
	int maxNumberOfReflectionsAnalysed;

	
	
	/**
	 * @param reflections
	 * @param ray
	 * @param group
	 * @param numberOfReflections
	 */
	public ReflectionsResult(Vector<ReflectionInfo> reflections, PickRay ray,
			Vector<Shape3D> shapes, int maxNumberOfReflectionsAnalysed) {
		super();
		this.reflections = reflections;
		this.ray = ray;
		this.shapes = shapes;
		this.maxNumberOfReflectionsAnalysed = maxNumberOfReflectionsAnalysed;
	}
	/**
	 * @return Returns the group.
	 */
	public Vector<Shape3D> getShapes() {
		return shapes;
	}
	/**
	 * @return Returns the numberOfReflections.
	 */
	public int getMaxNumberOfReflections() {
		return maxNumberOfReflectionsAnalysed;
	}
	/**
	 * @return Returns the ray.
	 */
	public PickRay getRay() {
		return ray;
	}
	/**
	 * @return Returns the reflections.
	 */
	public Vector<ReflectionInfo> getReflections() {
		return reflections;
	}
}
