package br.acousticsim.scenery;

import javax.media.j3d.GeometryArray;
import javax.media.j3d.GeometryUpdater;
import javax.media.j3d.J3DBuffer;
import javax.media.j3d.NodeComponent;
import javax.media.j3d.NodeReferenceTable;
import javax.vecmath.Color3b;
import javax.vecmath.Color3f;
import javax.vecmath.Color4b;
import javax.vecmath.Color4f;
import javax.vecmath.Point2f;
import javax.vecmath.Point3d;
import javax.vecmath.Point3f;
import javax.vecmath.TexCoord2f;
import javax.vecmath.TexCoord3f;
import javax.vecmath.TexCoord4f;
import javax.vecmath.Vector3f;

/**
 * Extension of GeometryArray to include acoustic characteristics to the
 * geometry.
 */
public class AcousticGeometry extends GeometryArray {

	Material material;

	GeometryArray geometryArray;

	public AcousticGeometry(GeometryArray geometryArray, Material material) {
		super(geometryArray.getVertexCount(), geometryArray.getVertexFormat());
		this.material = material;
		this.geometryArray = geometryArray;
	}

	public double getAbsorptionFactor(float frequency) {
		return material.getAbsorptionFactor(frequency);
	}

	public void getColor(int arg0, Color3b arg1) {

		geometryArray.getColor(arg0, arg1);
	}

	public void getColor(int arg0, Color3f arg1) {

		geometryArray.getColor(arg0, arg1);
	}

	public void getColor(int arg0, Color4b arg1) {

		geometryArray.getColor(arg0, arg1);
	}

	public void getColor(int arg0, Color4f arg1) {

		geometryArray.getColor(arg0, arg1);
	}

	public void getColor(int arg0, byte[] arg1) {

		geometryArray.getColor(arg0, arg1);
	}

	public void getColor(int arg0, float[] arg1) {

		geometryArray.getColor(arg0, arg1);
	}

	public Color3b[] getColorRef3b() {

		return geometryArray.getColorRef3b();
	}

	public Color3f[] getColorRef3f() {

		return geometryArray.getColorRef3f();
	}

	public Color4b[] getColorRef4b() {

		return geometryArray.getColorRef4b();
	}

	public Color4f[] getColorRef4f() {

		return geometryArray.getColorRef4f();
	}

	public J3DBuffer getColorRefBuffer() {

		return geometryArray.getColorRefBuffer();
	}

	public byte[] getColorRefByte() {

		return geometryArray.getColorRefByte();
	}

	public float[] getColorRefFloat() {

		return geometryArray.getColorRefFloat();
	}

	public void getColors(int arg0, Color3b[] arg1) {

		geometryArray.getColors(arg0, arg1);
	}

	public void getColors(int arg0, Color3f[] arg1) {

		geometryArray.getColors(arg0, arg1);
	}

	public void getColors(int arg0, Color4b[] arg1) {

		geometryArray.getColors(arg0, arg1);
	}

	public void getColors(int arg0, Color4f[] arg1) {

		geometryArray.getColors(arg0, arg1);
	}

	public void getColors(int arg0, byte[] arg1) {

		geometryArray.getColors(arg0, arg1);
	}

	public void getColors(int arg0, float[] arg1) {

		geometryArray.getColors(arg0, arg1);
	}

	public Point3d[] getCoordRef3d() {

		return geometryArray.getCoordRef3d();
	}

	public Point3f[] getCoordRef3f() {

		return geometryArray.getCoordRef3f();
	}

	public J3DBuffer getCoordRefBuffer() {

		return geometryArray.getCoordRefBuffer();
	}

	public double[] getCoordRefDouble() {

		return geometryArray.getCoordRefDouble();
	}

	public float[] getCoordRefFloat() {

		return geometryArray.getCoordRefFloat();
	}

	public void getCoordinate(int arg0, Point3d arg1) {

		geometryArray.getCoordinate(arg0, arg1);
	}

	public void getCoordinate(int arg0, Point3f arg1) {

		geometryArray.getCoordinate(arg0, arg1);
	}

	public void getCoordinate(int arg0, double[] arg1) {

		geometryArray.getCoordinate(arg0, arg1);
	}

	public void getCoordinate(int arg0, float[] arg1) {

		geometryArray.getCoordinate(arg0, arg1);
	}

	public void getCoordinates(int arg0, Point3d[] arg1) {

		geometryArray.getCoordinates(arg0, arg1);
	}

	public void getCoordinates(int arg0, Point3f[] arg1) {

		geometryArray.getCoordinates(arg0, arg1);
	}

	public void getCoordinates(int arg0, double[] arg1) {

		geometryArray.getCoordinates(arg0, arg1);
	}

	public void getCoordinates(int arg0, float[] arg1) {

		geometryArray.getCoordinates(arg0, arg1);
	}

	public int getInitialColorIndex() {

		return geometryArray.getInitialColorIndex();
	}

	public int getInitialCoordIndex() {

		return geometryArray.getInitialCoordIndex();
	}

	public int getInitialNormalIndex() {

		return geometryArray.getInitialNormalIndex();
	}

	public int getInitialTexCoordIndex(int arg0) {

		return geometryArray.getInitialTexCoordIndex(arg0);
	}

	public int getInitialVertexIndex() {

		return geometryArray.getInitialVertexIndex();
	}

	public J3DBuffer getInterleavedVertexBuffer() {

		return geometryArray.getInterleavedVertexBuffer();
	}

	public float[] getInterleavedVertices() {

		return geometryArray.getInterleavedVertices();
	}

	public void getNormal(int arg0, Vector3f arg1) {

		geometryArray.getNormal(arg0, arg1);
	}

	public void getNormal(int arg0, float[] arg1) {

		geometryArray.getNormal(arg0, arg1);
	}

	public Vector3f[] getNormalRef3f() {

		return geometryArray.getNormalRef3f();
	}

	public J3DBuffer getNormalRefBuffer() {

		return geometryArray.getNormalRefBuffer();
	}

	public float[] getNormalRefFloat() {

		return geometryArray.getNormalRefFloat();
	}

	public void getNormals(int arg0, Vector3f[] arg1) {

		geometryArray.getNormals(arg0, arg1);
	}

	public void getNormals(int arg0, float[] arg1) {

		geometryArray.getNormals(arg0, arg1);
	}

	public TexCoord2f[] getTexCoordRef2f(int arg0) {

		return geometryArray.getTexCoordRef2f(arg0);
	}

	public TexCoord3f[] getTexCoordRef3f(int arg0) {

		return geometryArray.getTexCoordRef3f(arg0);
	}

	public J3DBuffer getTexCoordRefBuffer(int arg0) {

		return geometryArray.getTexCoordRefBuffer(arg0);
	}

	public float[] getTexCoordRefFloat(int arg0) {

		return geometryArray.getTexCoordRefFloat(arg0);
	}

	public int getTexCoordSetCount() {

		return geometryArray.getTexCoordSetCount();
	}

	public void getTexCoordSetMap(int[] arg0) {

		geometryArray.getTexCoordSetMap(arg0);
	}

	public int getTexCoordSetMapLength() {

		return geometryArray.getTexCoordSetMapLength();
	}

	public void getTextureCoordinate(int arg0, Point2f arg1) {

		geometryArray.getTextureCoordinate(arg0, arg1);
	}

	public void getTextureCoordinate(int arg0, Point3f arg1) {

		geometryArray.getTextureCoordinate(arg0, arg1);
	}

	public void getTextureCoordinate(int arg0, float[] arg1) {

		geometryArray.getTextureCoordinate(arg0, arg1);
	}

	public void getTextureCoordinate(int arg0, int arg1, TexCoord2f arg2) {

		geometryArray.getTextureCoordinate(arg0, arg1, arg2);
	}

	public void getTextureCoordinate(int arg0, int arg1, TexCoord3f arg2) {

		geometryArray.getTextureCoordinate(arg0, arg1, arg2);
	}

	public void getTextureCoordinate(int arg0, int arg1, TexCoord4f arg2) {

		geometryArray.getTextureCoordinate(arg0, arg1, arg2);
	}

	public void getTextureCoordinate(int arg0, int arg1, float[] arg2) {

		geometryArray.getTextureCoordinate(arg0, arg1, arg2);
	}

	public void getTextureCoordinates(int arg0, Point2f[] arg1) {

		geometryArray.getTextureCoordinates(arg0, arg1);
	}

	public void getTextureCoordinates(int arg0, Point3f[] arg1) {

		geometryArray.getTextureCoordinates(arg0, arg1);
	}

	public void getTextureCoordinates(int arg0, float[] arg1) {

		geometryArray.getTextureCoordinates(arg0, arg1);
	}

	public void getTextureCoordinates(int arg0, int arg1, TexCoord2f[] arg2) {

		geometryArray.getTextureCoordinates(arg0, arg1, arg2);
	}

	public void getTextureCoordinates(int arg0, int arg1, TexCoord3f[] arg2) {

		geometryArray.getTextureCoordinates(arg0, arg1, arg2);
	}

	public void getTextureCoordinates(int arg0, int arg1, TexCoord4f[] arg2) {

		geometryArray.getTextureCoordinates(arg0, arg1, arg2);
	}

	public void getTextureCoordinates(int arg0, int arg1, float[] arg2) {

		geometryArray.getTextureCoordinates(arg0, arg1, arg2);
	}

	public int getValidVertexCount() {

		return geometryArray.getValidVertexCount();
	}

	public int getVertexCount() {

		return geometryArray.getVertexCount();
	}

	public int getVertexFormat() {

		return geometryArray.getVertexFormat();
	}

	public void setColor(int arg0, Color3b arg1) {

		geometryArray.setColor(arg0, arg1);
	}

	public void setColor(int arg0, Color3f arg1) {

		geometryArray.setColor(arg0, arg1);
	}

	public void setColor(int arg0, Color4b arg1) {

		geometryArray.setColor(arg0, arg1);
	}

	public void setColor(int arg0, Color4f arg1) {

		geometryArray.setColor(arg0, arg1);
	}

	public void setColor(int arg0, byte[] arg1) {

		geometryArray.setColor(arg0, arg1);
	}

	public void setColor(int arg0, float[] arg1) {

		geometryArray.setColor(arg0, arg1);
	}

	public void setColorRef3b(Color3b[] arg0) {

		geometryArray.setColorRef3b(arg0);
	}

	public void setColorRef3f(Color3f[] arg0) {

		geometryArray.setColorRef3f(arg0);
	}

	public void setColorRef4b(Color4b[] arg0) {

		geometryArray.setColorRef4b(arg0);
	}

	public void setColorRef4f(Color4f[] arg0) {

		geometryArray.setColorRef4f(arg0);
	}

	public void setColorRefBuffer(J3DBuffer arg0) {

		geometryArray.setColorRefBuffer(arg0);
	}

	public void setColorRefByte(byte[] arg0) {

		geometryArray.setColorRefByte(arg0);
	}

	public void setColorRefFloat(float[] arg0) {

		geometryArray.setColorRefFloat(arg0);
	}

	public void setColors(int arg0, Color3b[] arg1) {

		geometryArray.setColors(arg0, arg1);
	}

	public void setColors(int arg0, Color3b[] arg1, int arg2, int arg3) {

		geometryArray.setColors(arg0, arg1, arg2, arg3);
	}

	public void setColors(int arg0, Color3f[] arg1) {

		geometryArray.setColors(arg0, arg1);
	}

	public void setColors(int arg0, Color3f[] arg1, int arg2, int arg3) {

		geometryArray.setColors(arg0, arg1, arg2, arg3);
	}

	public void setColors(int arg0, Color4b[] arg1) {

		geometryArray.setColors(arg0, arg1);
	}

	public void setColors(int arg0, Color4b[] arg1, int arg2, int arg3) {

		geometryArray.setColors(arg0, arg1, arg2, arg3);
	}

	public void setColors(int arg0, Color4f[] arg1) {

		geometryArray.setColors(arg0, arg1);
	}

	public void setColors(int arg0, Color4f[] arg1, int arg2, int arg3) {

		geometryArray.setColors(arg0, arg1, arg2, arg3);
	}

	public void setColors(int arg0, byte[] arg1) {

		geometryArray.setColors(arg0, arg1);
	}

	public void setColors(int arg0, byte[] arg1, int arg2, int arg3) {

		geometryArray.setColors(arg0, arg1, arg2, arg3);
	}

	public void setColors(int arg0, float[] arg1) {

		geometryArray.setColors(arg0, arg1);
	}

	public void setColors(int arg0, float[] arg1, int arg2, int arg3) {

		geometryArray.setColors(arg0, arg1, arg2, arg3);
	}

	public void setCoordRef3d(Point3d[] arg0) {

		geometryArray.setCoordRef3d(arg0);
	}

	public void setCoordRef3f(Point3f[] arg0) {

		geometryArray.setCoordRef3f(arg0);
	}

	public void setCoordRefBuffer(J3DBuffer arg0) {

		geometryArray.setCoordRefBuffer(arg0);
	}

	public void setCoordRefDouble(double[] arg0) {

		geometryArray.setCoordRefDouble(arg0);
	}

	public void setCoordRefFloat(float[] arg0) {

		geometryArray.setCoordRefFloat(arg0);
	}

	public void setCoordinate(int arg0, Point3d arg1) {

		geometryArray.setCoordinate(arg0, arg1);
	}

	public void setCoordinate(int arg0, Point3f arg1) {

		geometryArray.setCoordinate(arg0, arg1);
	}

	public void setCoordinate(int arg0, double[] arg1) {

		geometryArray.setCoordinate(arg0, arg1);
	}

	public void setCoordinate(int arg0, float[] arg1) {

		geometryArray.setCoordinate(arg0, arg1);
	}

	public void setCoordinates(int arg0, Point3d[] arg1) {

		geometryArray.setCoordinates(arg0, arg1);
	}

	public void setCoordinates(int arg0, Point3d[] arg1, int arg2, int arg3) {

		geometryArray.setCoordinates(arg0, arg1, arg2, arg3);
	}

	public void setCoordinates(int arg0, Point3f[] arg1) {

		geometryArray.setCoordinates(arg0, arg1);
	}

	public void setCoordinates(int arg0, Point3f[] arg1, int arg2, int arg3) {

		geometryArray.setCoordinates(arg0, arg1, arg2, arg3);
	}

	public void setCoordinates(int arg0, double[] arg1) {

		geometryArray.setCoordinates(arg0, arg1);
	}

	public void setCoordinates(int arg0, double[] arg1, int arg2, int arg3) {

		geometryArray.setCoordinates(arg0, arg1, arg2, arg3);
	}

	public void setCoordinates(int arg0, float[] arg1) {

		geometryArray.setCoordinates(arg0, arg1);
	}

	public void setCoordinates(int arg0, float[] arg1, int arg2, int arg3) {

		geometryArray.setCoordinates(arg0, arg1, arg2, arg3);
	}

	public void setInitialColorIndex(int arg0) {

		geometryArray.setInitialColorIndex(arg0);
	}

	public void setInitialCoordIndex(int arg0) {

		geometryArray.setInitialCoordIndex(arg0);
	}

	public void setInitialNormalIndex(int arg0) {

		geometryArray.setInitialNormalIndex(arg0);
	}

	public void setInitialTexCoordIndex(int arg0, int arg1) {

		geometryArray.setInitialTexCoordIndex(arg0, arg1);
	}

	public void setInitialVertexIndex(int arg0) {

		geometryArray.setInitialVertexIndex(arg0);
	}

	public void setInterleavedVertexBuffer(J3DBuffer arg0) {

		geometryArray.setInterleavedVertexBuffer(arg0);
	}

	public void setInterleavedVertices(float[] arg0) {

		geometryArray.setInterleavedVertices(arg0);
	}

	public void setNormal(int arg0, Vector3f arg1) {

		geometryArray.setNormal(arg0, arg1);
	}

	public void setNormal(int arg0, float[] arg1) {

		geometryArray.setNormal(arg0, arg1);
	}

	public void setNormalRef3f(Vector3f[] arg0) {

		geometryArray.setNormalRef3f(arg0);
	}

	public void setNormalRefBuffer(J3DBuffer arg0) {

		geometryArray.setNormalRefBuffer(arg0);
	}

	public void setNormalRefFloat(float[] arg0) {

		geometryArray.setNormalRefFloat(arg0);
	}

	public void setNormals(int arg0, Vector3f[] arg1) {

		geometryArray.setNormals(arg0, arg1);
	}

	public void setNormals(int arg0, Vector3f[] arg1, int arg2, int arg3) {

		geometryArray.setNormals(arg0, arg1, arg2, arg3);
	}

	public void setNormals(int arg0, float[] arg1) {

		geometryArray.setNormals(arg0, arg1);
	}

	public void setNormals(int arg0, float[] arg1, int arg2, int arg3) {

		geometryArray.setNormals(arg0, arg1, arg2, arg3);
	}

	public void setTexCoordRef2f(int arg0, TexCoord2f[] arg1) {

		geometryArray.setTexCoordRef2f(arg0, arg1);
	}

	public void setTexCoordRef3f(int arg0, TexCoord3f[] arg1) {

		geometryArray.setTexCoordRef3f(arg0, arg1);
	}

	public void setTexCoordRefBuffer(int arg0, J3DBuffer arg1) {

		geometryArray.setTexCoordRefBuffer(arg0, arg1);
	}

	public void setTexCoordRefFloat(int arg0, float[] arg1) {

		geometryArray.setTexCoordRefFloat(arg0, arg1);
	}

	public void setTextureCoordinate(int arg0, Point2f arg1) {

		geometryArray.setTextureCoordinate(arg0, arg1);
	}

	public void setTextureCoordinate(int arg0, Point3f arg1) {

		geometryArray.setTextureCoordinate(arg0, arg1);
	}

	public void setTextureCoordinate(int arg0, float[] arg1) {

		geometryArray.setTextureCoordinate(arg0, arg1);
	}

	public void setTextureCoordinate(int arg0, int arg1, TexCoord2f arg2) {

		geometryArray.setTextureCoordinate(arg0, arg1, arg2);
	}

	public void setTextureCoordinate(int arg0, int arg1, TexCoord3f arg2) {

		geometryArray.setTextureCoordinate(arg0, arg1, arg2);
	}

	public void setTextureCoordinate(int arg0, int arg1, TexCoord4f arg2) {

		geometryArray.setTextureCoordinate(arg0, arg1, arg2);
	}

	public void setTextureCoordinate(int arg0, int arg1, float[] arg2) {

		geometryArray.setTextureCoordinate(arg0, arg1, arg2);
	}

	public void setTextureCoordinates(int arg0, Point2f[] arg1) {

		geometryArray.setTextureCoordinates(arg0, arg1);
	}

	public void setTextureCoordinates(int arg0, Point2f[] arg1, int arg2,
			int arg3) {

		geometryArray.setTextureCoordinates(arg0, arg1, arg2, arg3);
	}

	public void setTextureCoordinates(int arg0, Point3f[] arg1) {

		geometryArray.setTextureCoordinates(arg0, arg1);
	}

	public void setTextureCoordinates(int arg0, Point3f[] arg1, int arg2,
			int arg3) {

		geometryArray.setTextureCoordinates(arg0, arg1, arg2, arg3);
	}

	public void setTextureCoordinates(int arg0, float[] arg1) {

		geometryArray.setTextureCoordinates(arg0, arg1);
	}

	public void setTextureCoordinates(int arg0, float[] arg1, int arg2, int arg3) {

		geometryArray.setTextureCoordinates(arg0, arg1, arg2, arg3);
	}

	public void setTextureCoordinates(int arg0, int arg1, TexCoord2f[] arg2) {

		geometryArray.setTextureCoordinates(arg0, arg1, arg2);
	}

	public void setTextureCoordinates(int arg0, int arg1, TexCoord2f[] arg2,
			int arg3, int arg4) {

		geometryArray.setTextureCoordinates(arg0, arg1, arg2, arg3, arg4);
	}

	public void setTextureCoordinates(int arg0, int arg1, TexCoord3f[] arg2) {

		geometryArray.setTextureCoordinates(arg0, arg1, arg2);
	}

	public void setTextureCoordinates(int arg0, int arg1, TexCoord3f[] arg2,
			int arg3, int arg4) {

		geometryArray.setTextureCoordinates(arg0, arg1, arg2, arg3, arg4);
	}

	public void setTextureCoordinates(int arg0, int arg1, TexCoord4f[] arg2) {

		geometryArray.setTextureCoordinates(arg0, arg1, arg2);
	}

	public void setTextureCoordinates(int arg0, int arg1, TexCoord4f[] arg2,
			int arg3, int arg4) {

		geometryArray.setTextureCoordinates(arg0, arg1, arg2, arg3, arg4);
	}

	public void setTextureCoordinates(int arg0, int arg1, float[] arg2) {

		geometryArray.setTextureCoordinates(arg0, arg1, arg2);
	}

	public void setTextureCoordinates(int arg0, int arg1, float[] arg2,
			int arg3, int arg4) {

		geometryArray.setTextureCoordinates(arg0, arg1, arg2, arg3, arg4);
	}

	public void setValidVertexCount(int arg0) {

		geometryArray.setValidVertexCount(arg0);
	}

	public void updateData(GeometryUpdater arg0) {

		geometryArray.updateData(arg0);
	}

	public NodeComponent cloneNodeComponent() {

		return geometryArray.cloneNodeComponent();
	}

	public NodeComponent cloneNodeComponent(boolean arg0) {

		return geometryArray.cloneNodeComponent(arg0);
	}

	public void duplicateNodeComponent(NodeComponent arg0) {

		geometryArray.duplicateNodeComponent(arg0);
	}

	public void duplicateNodeComponent(NodeComponent arg0, boolean arg1) {

		geometryArray.duplicateNodeComponent(arg0, arg1);
	}

	public boolean getDuplicateOnCloneTree() {

		return geometryArray.getDuplicateOnCloneTree();
	}

	public void setDuplicateOnCloneTree(boolean arg0) {

		geometryArray.setDuplicateOnCloneTree(arg0);
	}

	public Object getUserData() {

		return geometryArray.getUserData();
	}

	public void setUserData(Object arg0) {

		geometryArray.setUserData(arg0);
	}

	public void updateNodeReferences(NodeReferenceTable arg0) {

		geometryArray.updateNodeReferences(arg0);
	}

	// delegate methods

}
