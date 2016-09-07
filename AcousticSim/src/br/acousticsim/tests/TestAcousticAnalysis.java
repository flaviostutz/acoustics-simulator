package br.acousticsim.tests;

import javax.media.j3d.Appearance;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.PickRay;
import javax.media.j3d.Shape3D;
import javax.media.j3d.TransparencyAttributes;
import javax.swing.JApplet;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

import br.acousticsim.engine.AcousticAnalisysEngine;
import br.acousticsim.engine.AnalysisGraph;
import br.acousticsim.math.Geometries;
import br.acousticsim.scenery.AcousticScenery;
import br.acousticsim.scenery.SceneryUtil;
import br.acousticsim.scenery.SoundListener;
import br.acousticsim.scenery.SoundSource;

import com.sun.j3d.utils.geometry.ColorCube;

public class TestAcousticAnalysis extends JApplet {

	private static final long serialVersionUID = 3834028073791272241L;

	@Override
	public void init() {
		
		Shape3D triangle1 = Geometries.createTriangle(new Point3d(1.5,0,0), new Point3d(0,1,0), new Point3d(0,0,1));
//		Shape3D triangle2 = Geometries.createTriangle(new Point3d(-1,0,0), new Point3d(0,-1,0), new Point3d(0,0,-1));
		
//		Shape3D triangle3 = Geometries.createTriangle(new Point3d(1.2,0,0), new Point3d(0,0,-1.2), new Point3d(0,-1.2,0));
//		Shape3D triangle4 = Geometries.createTriangle(new Point3d(0,1.2,0), new Point3d(-1.2,0,0), new Point3d(0,0,-1.2));

		ColorCube cube = new ColorCube(3);
		//cube.setGeometry(new AcousticGeometry(cube.getGeometry(), new GenericMaterial()));
		
		TransparencyAttributes ta = new TransparencyAttributes();
		ta.setTransparencyMode(TransparencyAttributes.BLEND_SRC_ALPHA);
		ta.setTransparency(.5F);
		
		Appearance a = new Appearance();
		a.setTransparencyAttributes(ta);
		cube.setAppearance(a);

		triangle1.getAppearance().getColoringAttributes().setColor(255,0,0);
		triangle1.getAppearance().setTransparencyAttributes(ta);
/*		triangle2.getAppearance().getColoringAttributes().setColor(0,255,0);
		triangle2.getAppearance().setTransparencyAttributes(ta);
		triangle3.getAppearance().getColoringAttributes().setColor(0,0,255);
		triangle3.getAppearance().setTransparencyAttributes(ta);
		triangle4.getAppearance().getColoringAttributes().setColor(127,0,127);
		triangle4.getAppearance().setTransparencyAttributes(ta);
*/		
		
		AcousticScenery scenery = new AcousticScenery();
		BranchGroup bg = scenery.getBranchGroup();
		
		bg.addChild(new SoundSource(scenery, 0.1F, 20, new PickRay(new Point3d(0.5F, 0.5F, 0.9F), new Vector3d(1F,0F,0F)), 1F));
		bg.addChild(new SoundListener(scenery, 1F, 90, new PickRay(new Point3d(0.1F, 0.05F, 0.4F), new Vector3d(1,1,1))));

		//bg.addChild(cube);
		bg.addChild(triangle1);
/*		bg.addChild(triangle2);
		bg.addChild(triangle3);
		bg.addChild(triangle4);
*/
		
		//draw waves in scenery (optional)
		scenery.createWaveLines(10F);
		
		//run analysis
		AcousticAnalisysEngine.runAnalysis(scenery, 10F, 100F, 100F, 100F);
		
		//show results
		for(Shape3D s: scenery.getAllShapes()) {
			if(s instanceof SoundListener) {
				SoundListener sl = (SoundListener)s;
				AnalysisGraph.showFrequencySpectrum(sl);
				AnalysisGraph.showReverberantSoundField(sl);
			}
		}
		
		add("Center", SceneryUtil.getCanvas3D(bg));
		
	}

}
