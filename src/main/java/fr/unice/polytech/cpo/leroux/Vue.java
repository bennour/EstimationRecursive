package fr.unice.polytech.cpo.leroux;

import java.awt.Color;
import java.awt.Shape;

import org.jblas.DoubleMatrix;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.util.ShapeUtilities;

public class Vue extends ApplicationFrame {
	private static final long serialVersionUID = 1L;
	
	private DoubleMatrix observeur, mobile;
	
    /**
     * Creates a new demo.
     *
     * @param title  the frame title.
     */
    public Vue(final String title, DoubleMatrix observeur, DoubleMatrix mobile) {
 
        super(title);
        
        this.observeur = observeur;
        this.mobile = mobile;
 
        final XYDataset dataset = createDataset();
        final JFreeChart chart = createChart(dataset);
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);
 
    }
   
    /**
     * Creates a sample dataset.
     *
     * @return a sample dataset.
     */
    private XYDataset createDataset() {
       
        final XYSeries series1 = new XYSeries("Observeur");
        for(int i = 1; i < observeur.columns; i++)
        	series1.add(observeur.get(0, i), observeur.get(1, i));
 
        final XYSeries series2 = new XYSeries("Mobile");
        for(int i = 0; i < mobile.columns; i++)
        	series2.add(mobile.get(0, i), mobile.get(1, i));
 
 
        final XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series1);
        dataset.addSeries(series2);
               
        return dataset;
       
    }
   
    /**
     * Creates a chart.
     *
     * @param dataset  the data for the chart.
     *
     * @return a chart.
     */
    private JFreeChart createChart(final XYDataset dataset) {
       
        // create the chart...
        final JFreeChart chart = ChartFactory.createXYLineChart(
            "Trajectoire du mobile et de l'observateur",      // chart title
            "X",                      // x axis label
            "Y",                      // y axis label
            dataset,                  // data
            PlotOrientation.VERTICAL,
            true,                     // include legend
            true,                     // tooltips
            false                     // urls
        );
 
        // NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...
        chart.setBackgroundPaint(Color.white);
 
//        final StandardLegend legend = (StandardLegend) chart.getLegend();
  //      legend.setDisplaySeriesShapes(true);
       
        // get a reference to the plot for further customisation...
        final XYPlot plot = chart.getXYPlot();
        plot.setBackgroundPaint(Color.lightGray);
    //    plot.setAxisOffset(new Spacer(Spacer.ABSOLUTE, 5.0, 5.0, 5.0, 5.0));
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);
       
        final XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesLinesVisible(0, false);
        renderer.setSeriesLinesVisible(1, false);
        plot.setRenderer(renderer);
        
        Shape cross = ShapeUtilities.createDiagonalCross(3, 1);
        renderer.setSeriesShape(0, cross);
 
        // change the auto tick unit selection to integer units only...
        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        // OPTIONAL CUSTOMISATION COMPLETED.
               
        return chart;
       
    }
 
    // ****************************************************************************
    // * JFREECHART DEVELOPER GUIDE                                               *
    // * The JFreeChart Developer Guide, written by David Gilbert, is available   *
    // * to purchase from Object Refinery Limited:                                *
    // *                                                                          *
    // * http://www.object-refinery.com/jfreechart/guide.html                     *
    // *                                                                          *
    // * Sales are used to provide funding for the JFreeChart project - please    *
    // * support us so that we can continue developing free software.             *
    // ****************************************************************************
}
