/*
 * Created on 04/05/2005
 */
package br.acousticsim.engine;

import java.util.Vector;

import javax.media.j3d.Group;
import javax.media.j3d.PickRay;
import javax.media.j3d.Shape3D;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3f;

import br.acousticsim.math.Geometries;
import br.acousticsim.math.IntersectionInfo;
import br.acousticsim.math.Vectors;

/**
 * @author Flávio Stutz (flaviostutz@uol.com.br)
 */
public class ReflectionEngine {

	public static void addRayReflectionLinesToGroup(ReflectionsResult rr, Group group) {
		ReflectionInfo lastReflection = new ReflectionInfo(null, rr.getRay());
		Vector<ReflectionInfo> reflections = rr.getReflections();
		
		//criar as linhas e adiciona-las ao cenário
		for(ReflectionInfo reflection: reflections) {
			Shape3D line = Geometries.createLine(Vectors.getRayPoint(reflection.getIntersectionInfo().getRay()), reflection.getIntersectionInfo().getPoint());
			group.addChild(line);
			lastReflection = reflection;
		}
		
		
		//desenhar a última linha (a que não intercepta nenhum objeto do cenário)

		//garantir que não há objetos que interceptam o último raio
		Vector<Shape3D> shapes = Geometries.getAllShapes(group);
		IntersectionInfo ii = Vectors.findNearestIntersection(shapes, lastReflection.getReflectedRay());

		//desenhar a última linha
		if(ii==null) {
			
			Vector3f endv = Vectors.getRayVector(lastReflection.getReflectedRay());
			endv.scale(50);
			Point3f endp = new Point3f();
			endv.get(endp);
			
			Shape3D line = Geometries.createLine(Vectors.getRayPoint(lastReflection.getReflectedRay()), endp);
			group.addChild(line);
			
		}

	}
	
	public static ReflectionsResult computeRayReflections(Vector<Shape3D> shapes, int numberOfReflections, PickRay inRay) {

		Vector<ReflectionInfo> reflections = new Vector<ReflectionInfo>();
		
		PickRay originalRay = inRay;

		//looping do número de reflexões desejadas
		for(int i=0; i<numberOfReflections; i++) {

			//localizar a folha interceptada pelo raio mais próxima da origem do raio
			IntersectionInfo ii = Vectors.findNearestIntersection(shapes, inRay);
			
			if(ii!=null) {
				
				//criar um raio de reflexão sobre a geometria
				PickRay newRay = Vectors.createReflectedRay(ii);

				reflections.add(new ReflectionInfo(ii, newRay));
				
				inRay = newRay;
				
			} else {
				break;
			}
		}
		
		return new ReflectionsResult(reflections, originalRay, shapes, numberOfReflections);
	}

}
