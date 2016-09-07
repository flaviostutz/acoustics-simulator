/*
 * Created on 07/05/2005
 */
package br.acousticsim.engine;

import javax.media.j3d.PickRay;

import br.acousticsim.math.IntersectionInfo;

/**
 * @author Flávio Stutz (flaviostutz@uol.com.br)
 */
public class ReflectionInfo {

	public IntersectionInfo intersectionInfo;
	public PickRay reflectedRay;
	
	/**
	 * @param intersectionInfo
	 * @param reflectedRay
	 */
	public ReflectionInfo(IntersectionInfo intersectionInfo,
			PickRay reflectedRay) {
		super();
		this.intersectionInfo = intersectionInfo;
		this.reflectedRay = reflectedRay;
	}
	public IntersectionInfo getIntersectionInfo() {
		return intersectionInfo;
	}
	public PickRay getReflectedRay() {
		return reflectedRay;
	}
}
