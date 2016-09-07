/*
 * Created on 04/05/2005
 */
package br.acousticsim.math;

import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

import javax.media.j3d.GeometryArray;
import javax.media.j3d.PickRay;
import javax.media.j3d.QuadArray;
import javax.media.j3d.Shape3D;
import javax.media.j3d.TriangleArray;
import javax.vecmath.Point3d;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;

/**
 * Classe de utilitários de vetores
 * @author Flávio Stutz (flaviostutz@uol.com.br)
 */
public class Vectors {

	private static Set<Class> incompatibleGeometries = new HashSet<Class>();
	
	
	/**
	 * Localizar a folha que intercepta o raio mais próxima
	 */
	public static IntersectionInfo findNearestIntersection(Vector<Shape3D> shapes, PickRay inRay) {
		
		IntersectionInfo nearestIntersection = null;
		float shortestDistance = Float.MAX_VALUE;
		
		//localizar o objeto que intercepta o raio mais próximo
        for(Shape3D shape: shapes) {
        	
        	IntersectionInfo ii = findIntersectionPoint(shape, inRay);
			if(ii==null) continue;
			
			float dist = ii.getPoint().distance(getRayPoint(inRay));
			
			if(dist<shortestDistance) {
				shortestDistance = dist;
				nearestIntersection = ii;
			}
        }
        
        return nearestIntersection;
	}
	
	/**
	 * Verificar para cada elemento geométrico da forma analisada se há intersecção com o raio
	 * @param shape
	 * @param ray
	 * @return
	 */
	public static IntersectionInfo findIntersectionPoint(Shape3D shape, PickRay inRay) {
		
		IntersectionInfo intersection = null;

		float shortestDistance = Float.MAX_VALUE;
		GeometryArray geometryArray = null;
		
		Enumeration e = shape.getAllGeometries();

		while(e.hasMoreElements()) {
			
			Object obj = e.nextElement();
			
			if(obj instanceof GeometryArray) {
				geometryArray = (GeometryArray)obj;
				IntersectionInfo ii = findIntersectionPoint(geometryArray, inRay);
				if(ii==null) {
					continue;
				}
				float s = ii.getPoint().distance(getRayPoint(inRay));
				if(s<shortestDistance) {
					intersection = ii;
					shortestDistance = s;
				}
			}
			
		}
		if(intersection==null) return null;
		intersection.setShape(shape);
		return intersection;
		
	}

	/**
	 * Verificar se o raio intercepta a geometria
	 */
	public static IntersectionInfo findIntersectionPoint(GeometryArray geometry, PickRay inRay) {

		//TODO implementar análise de outros tipos de geometrias
		if(geometry instanceof TriangleArray) {
			return findIntersectionPoint(geometry, 3, inRay);
			
		} else if(geometry instanceof QuadArray) {
			return findIntersectionPoint(geometry, 4, inRay);
			
		} else {
			if(!incompatibleGeometries.contains(geometry.getClass())) {
				System.err.println("A geometria do tipo '"+ geometry.getClass() +"' não pode ser analisada quanto à intersecção com o raio");
				incompatibleGeometries.add(geometry.getClass());
			}
			return null;
		}
	}
	
	private static IntersectionInfo findIntersectionPoint(GeometryArray geometry, int numberOfVertexesInGroup, PickRay inRay) {
		Point3f nearestPoint = null;
		Vector<TriangleArray> nearestGeometries = new Vector<TriangleArray>();
		float shortestDistance = Float.MAX_VALUE;
		
		//varrer todas as geometrias que compõem a geometria 'geometry'
		for(int i=0; i<(geometry.getVertexCount()/numberOfVertexesInGroup); i++) {
		
			float[] v = new float[numberOfVertexesInGroup*3];
			geometry.getCoordinates(i*numberOfVertexesInGroup, v);
			boolean intersectionFoundInGroup = false;

			//testar todas as combinações de triângulos para a geometrial atual
			for(int a=0; a<numberOfVertexesInGroup; a++) {
				
				Point3f[] tp = {new Point3f(getVertex(v, a*3+0),getVertex(v, a*3+1),getVertex(v, a*3+2)), 
								new Point3f(getVertex(v, a*3+3),getVertex(v, a*3+4),getVertex(v, a*3+5)), 
								new Point3f(getVertex(v, a*3+6),getVertex(v, a*3+7),getVertex(v, a*3+8))}; 

				TriangleArray triangle = new TriangleArray(3, TriangleArray.COORDINATES);
				triangle.setCoordinates(0, tp);
				
				//localizar um ponto de intersecção para o triângulo atual
				Point3f fp = findIntersectionPointTriangle(triangle, inRay, 0, 0, true);

				if(fp!=null) {
					
					float dist = fp.distance(getRayPoint(inRay));
					if(dist==0) continue;
					
					//testar se o ponto atual é mais próximo do raio
					if(!intersectionFoundInGroup) {
						
						if(dist==shortestDistance) {
							//nearestPoint = fp;
							nearestGeometries.add(triangle);
							intersectionFoundInGroup = true;
							
						} else if(dist<shortestDistance) {
							shortestDistance = dist;
							nearestPoint = fp;
							nearestGeometries = new Vector<TriangleArray>();
							nearestGeometries.add(triangle);
							intersectionFoundInGroup = true;
						}
					}
				}
			}
			
		}
		if(nearestGeometries.size()==0) {
			return null;
		}
		return new IntersectionInfo(null, nearestPoint, nearestGeometries, inRay);
	}

	private static float getVertex(float[] vertexes, int index) {
		return vertexes[index%vertexes.length];
	}
	
	/**
	 * Encontrar intersecção entre uma linha e um triângulo indicado
	 * @param triangle
	 * @param ray
	 * @return
	 */
	 static Point3f findIntersectionPointTriangle(TriangleArray triangle, PickRay ray, float intersectTolerance, float insideTolerance, boolean normalModule) {
		
		if(ray==null) throw new IllegalArgumentException("'ray' não pode ser nulo");
		
		float[] c = new float[9];
		triangle.getCoordinates(0, c);
		
		Vector3f normal = getTriangleNormal(triangle);
		
        float d = normal.dot(new Vector3f(c[0], c[1], c[2]));
		Vector3f rv = getRayVector(ray);
		rv.scale(100);
		
		float lambda = (d - normal.dot(new Vector3f(getRayPoint(ray)))) / rv.dot(normal);

		//só serve para linhas!
        if(lambda >= (0.0F - intersectTolerance) && lambda <= (1.0F + intersectTolerance)) {
        	
            Vector3f dir = (Vector3f)rv.clone();
            dir.scale(lambda);
            Point3f intersectPoint = (Point3f)getRayPoint(ray).clone();
            intersectPoint.add(dir);

            if(isPointInsideTriangle(triangle, intersectPoint, insideTolerance)) {
            	return intersectPoint;
            }
        }
		return null;
	}


	 /**
	  * Testar se um ponto está dentro de um triângulo.
	  * A resolução de análise interna é baseada em tipos float, em vez de double
	  * para eliminar problemas de resíduos de cálculo percebidos anteriormente.
	  * @param triangle
	  * @param p
	  * @param insideTolerance
	  * @return
	  */
	public static boolean isPointInsideTriangle(TriangleArray triangle, Point3f p, float insideTolerance) {
		Point3f p1 = new Point3f();
		triangle.getCoordinate(0, p1);
		
		Point3f p2 = new Point3f();
		triangle.getCoordinate(1, p2);
		
		Point3f p3 = new Point3f();
		triangle.getCoordinate(2, p3);
		
		float[] c = new float[9];
		triangle.getCoordinates(0, c);
		
        Vector3f dir1P = new Vector3f(p);
        dir1P.sub(p1);
        Vector3f dir2P = new Vector3f(p);
        dir2P.sub(p2);
        
        Vector3f dir1 = new Vector3f(c[3]-c[0], c[4]-c[1], c[5]-c[2]);
        dir1.normalize();
        Vector3f dir2 = new Vector3f(c[6]-c[3], c[7]-c[4], c[8]-c[5]);
        dir2.normalize();
        Vector3f dir3 = new Vector3f(c[6]-c[0], c[7]-c[1], c[8]-c[2]);
        dir3.normalize();
        
		float cosA1;
		float cosA2;
        if(dir1P.length() == 0.0F) {
            cosA1 = 1.0F;            cosA2 = 1.0F;
        } else {
            dir1P.normalize();
            cosA1 = dir1.dot(dir1P);
            cosA2 = dir3.dot(dir1P);
        }

		float cosB1;
        if(dir2P.length() == 0.0F) {
            cosB1 = 1.0F;
        } else {
            dir2P.normalize();
            dir1.negate();
            cosB1 = dir1.dot(dir2P);
            dir1.negate();
        }

		float cosA = dir1.dot(dir3);
        dir1.scale(-1F);
		float cosB = dir2.dot(dir1);
        
        return cosA - (float)insideTolerance <= cosA1 && cosA - (float)insideTolerance <= cosA2 && cosB - (float)insideTolerance <= cosB1 || isEdgepoint(triangle, p, insideTolerance);
	}
	
    public static boolean isEdgepoint(TriangleArray triangle, Point3f p, float epsilon) {
		
		Point3f p1 = new Point3f();
		triangle.getCoordinate(0, p1);
		
		Point3f p2 = new Point3f();
		triangle.getCoordinate(1, p2);
		
		Point3f p3 = new Point3f();
		triangle.getCoordinate(2, p3);
		
        
		return p.epsilonEquals(p1, epsilon) ||
			   p.epsilonEquals(p2, epsilon) ||
			   p.epsilonEquals(p3, epsilon);
    }

	
	
	
	
	
	public static PickRay createReflectedRay(IntersectionInfo intersection) {
		PickRay inRay = intersection.getRay();
		Vector3f normal = intersection.getResultingGeometriesNormal();
		
		Vector3f inVect = getRayVector(inRay);
		
		Vector3f outVect = createReflectedVectorOnPlane(normal, inVect);
		PickRay outRay = new PickRay(new Point3d(intersection.getPoint()), new Vector3d(outVect));
		
		return outRay;
	}
	
	/**
	 * Refletir um vetor em um dado plano e retornar o vetor resultante
	 * @param planeNormal
	 * @param vector
	 * @return
	 */
	public static Vector3f createReflectedVectorOnPlane(Vector3f planeNormal, Vector3f vector) {
		planeNormal.normalize();
		
		Vector3f inVect = new Vector3f(vector);
		inVect.negate();

		Vector3f i = new Vector3f(planeNormal);
		i.scale(planeNormal.dot(inVect));
		
		Vector3f j = new Vector3f();
		j.cross(inVect, planeNormal);
		j.cross(j, planeNormal);
		
		Vector3f reflected = new Vector3f();
		reflected.add(i, j);
		
		return reflected;
	}


	
	
	
	
	public static Vector3f getTriangleNormal(TriangleArray triangle) {
		Vector3f normal = new Vector3f();

		float[] coords = new float[9];
		triangle.getCoordinates(0, coords);
		
		normal.cross(new Vector3f((coords[3]-coords[0]), (coords[4]-coords[1]), (coords[5]-coords[2])), new Vector3f((coords[6]-coords[0]), (coords[7]-coords[1]), (coords[8]-coords[2])));
		normal.normalize();
		
		return normal;
	}

	public static Point3f getRayPoint(PickRay ray) {
		Point3d p = new Point3d();
		ray.get(p, new Vector3d());
		return new Point3f(p);
	}
	
	public static Vector3f getRayVector(PickRay ray) {
		Vector3d v = new Vector3d();
		ray.get(new Point3d(), v);
		return new Vector3f(v);
	}
	
}
