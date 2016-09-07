package br.acousticsim.engine;

import java.awt.Dimension;
import java.awt.Font;
import java.util.HashMap;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RefineryUtilities;

import br.acousticsim.scenery.SoundListener;
import br.acousticsim.scenery.SoundSource;

public class AnalysisGraph {

	public static void showFrequencySpectrum(SoundListener soundListener) {
		System.out.println("Creating frequency spectrum graph");
		//populate chart dataset
		XYSeriesCollection spectrumDataset = new XYSeriesCollection();
		
		for(SoundSource ss: soundListener.getListenedSounds().keySet()) {
			XYSeries series = new XYSeries("Source "+ss, true, true);
			
			HashMap<Float, Double> spectrum = soundListener.getFrequencySpectrum(ss);
			for(Float frequency: spectrum.keySet()) {
				series.add(frequency, spectrum.get(frequency));
			}
			spectrumDataset.addSeries(series);
		}
		
		//create chart
		JFreeChart jfreechart = ChartFactory.createXYLineChart(null,
				"Frequency (Hz)", "Intensity (%)", spectrumDataset,
				PlotOrientation.VERTICAL, true, true, false);
		
		configureAxis(jfreechart);

		TextTitle title1 = new TextTitle("Frequency spectrum", new Font("SansSerif", 1, 14));
		TextTitle title2 = new TextTitle("Spectrum of intensity of frequencies", new Font("SansSerif", 0, 11));
		jfreechart.addSubtitle(title1);
		jfreechart.addSubtitle(title2);

		showChart(jfreechart);
	}


	public static void showReverberantSoundField(SoundListener soundListener) {
		System.out.println("Creating reverberant sound field graph");

		//populate chart dataset
		XYSeriesCollection spectrumDataset = new XYSeriesCollection();
		
		for(SoundSource ss: soundListener.getListenedSounds().keySet()) {
			XYSeries series = new XYSeries("Source "+ss, true, true);
			
			HashMap<Float, Double> spectrum = soundListener.getFrequencySpectrum(ss);
			for(ListenedSound ls: soundListener.getListenedSounds().get(ss)) {
				series.add(ls.getTime(), ls.getRayIntensity());
			}
			spectrumDataset.addSeries(series);
		}
		
		//create chart
		//create chart
//		JFreeChart jfreechart = ChartFactory.createXYBarChart(
//				"Reverberant field", "Time (s)", false, "Intensity (%)", spectrumDataset,
//				PlotOrientation.VERTICAL, true, true, false);
		
		JFreeChart jfreechart = ChartFactory.createXYLineChart("Reverberant field",
				"Time (s)", "Intensity (%)", spectrumDataset,
				PlotOrientation.VERTICAL, true, true, false);
		
		configureAxis(jfreechart);

		showChart(jfreechart);
	}

	
	
	
	private static void configureAxis(JFreeChart jfreechart) {
		XYPlot xyplot = jfreechart.getXYPlot();
		NumberAxis numberaxis = (NumberAxis) xyplot.getDomainAxis();
		numberaxis.setUpperMargin(0.12);
		numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		NumberAxis numberaxis2 = (NumberAxis) xyplot.getRangeAxis();
		numberaxis2.setAutoRangeIncludesZero(false);
/*		Object obj = null;
		Font font = new Font("SansSerif", 0, 9);
		XYTextAnnotation xytextannotation = new XYTextAnnotation("3rd", 36.5, 11.76);
		xytextannotation.setFont(font);
		xytextannotation.setTextAnchor(TextAnchor.HALF_ASCENT_LEFT);
		xyplot.addAnnotation(xytextannotation);
		xytextannotation = new XYTextAnnotation("5th", 36.5, 12.04);
		xytextannotation.setFont(font);
		xytextannotation.setTextAnchor(TextAnchor.HALF_ASCENT_LEFT);
		xyplot.addAnnotation(xytextannotation);
		xytextannotation = new XYTextAnnotation("10th", 36.5, 12.493);
		xytextannotation.setFont(font);
		xytextannotation.setTextAnchor(TextAnchor.HALF_ASCENT_LEFT);
		xyplot.addAnnotation(xytextannotation);
		xytextannotation = new XYTextAnnotation("25th", 36.5, 13.313);
		xytextannotation.setFont(font);
		xytextannotation.setTextAnchor(TextAnchor.HALF_ASCENT_LEFT);
		xyplot.addAnnotation(xytextannotation);
		xytextannotation = new XYTextAnnotation("50th", 36.5, 14.33);
		xytextannotation.setFont(font);
		xytextannotation.setTextAnchor(TextAnchor.HALF_ASCENT_LEFT);
		xyplot.addAnnotation(xytextannotation);
		xytextannotation = new XYTextAnnotation("75th", 36.5, 15.478);
		xytextannotation.setFont(font);
		xytextannotation.setTextAnchor(TextAnchor.HALF_ASCENT_LEFT);
		xyplot.addAnnotation(xytextannotation);
		xytextannotation = new XYTextAnnotation("90th", 36.5, 16.642);
		xytextannotation.setFont(font);
		xytextannotation.setTextAnchor(TextAnchor.HALF_ASCENT_LEFT);
		xyplot.addAnnotation(xytextannotation);
		xytextannotation = new XYTextAnnotation("95th", 36.5, 17.408);
		xytextannotation.setFont(font);
		xytextannotation.setTextAnchor(TextAnchor.HALF_ASCENT_LEFT);
		xyplot.addAnnotation(xytextannotation);
		xytextannotation = new XYTextAnnotation("97th", 36.5, 17.936);
		xytextannotation.setFont(font);
		xytextannotation.setTextAnchor(TextAnchor.HALF_ASCENT_LEFT);
		xyplot.addAnnotation(xytextannotation);*/
	}

	public static void showChart(JFreeChart chart) {
		JFrame f = new JFrame();
		ChartPanel chartpanel = new ChartPanel(chart);
		chartpanel.setPreferredSize(new Dimension(500, 270));
		f.setContentPane(chartpanel);
		f.pack();
		RefineryUtilities.centerFrameOnScreen(f);
		f.setVisible(true);
	}

}
