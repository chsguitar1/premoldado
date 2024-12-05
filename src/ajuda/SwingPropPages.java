package ajuda;

/**
dsj demo code.
You may use, modify and redistribute this code under the terms laid out in the header of the DSJDemo application.
copyright 2009
N.Peters
humatic GmbH
Berlin, Germany
**/

import de.humatic.dsj.*;

import javax.swing.*;

/**
Shows presentation of native filter properties pages in a non-modal, non-blocking Swing dialog.
**/

public class SwingPropPages {

	private DSCapture graph;

	private JDialog pages;

	private int VID_DEV_IDX = 0;

	public SwingPropPages() {}

	public void createGraph() {

		JFrame f = new JFrame("dsj - SwingPropPages");

		DSFilterInfo[][] dsi = DSCapture.queryDevices();

		graph = new DSCapture(DSFiltergraph.DD7, dsi[0][VID_DEV_IDX], false, DSFilterInfo.doNotRender(), null);

		f.add(java.awt.BorderLayout.CENTER, graph.asComponent());

		final DSFilter[] filtersInGraph = graph.listFilters();

		final JComboBox filterList = new JComboBox();

		filterList.setLightWeightPopupEnabled(false);

		filterList.addItem("Show properties page for: ");

		for (int i = 0; i < filtersInGraph.length; i++) {

			if (filtersInGraph[i].getName().indexOf("\\?") != -1) filterList.addItem(dsi[0][VID_DEV_IDX].getName());

			else filterList.addItem(filtersInGraph[i].getName());

		}

		filterList.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				showEmbeddedPropPage(filtersInGraph[filterList.getSelectedIndex()-1]);
			}

		});

		f.add(java.awt.BorderLayout.NORTH, filterList);

		f.pack();

		f.setVisible(true);

		/**
		Don't do this at home. This demo relies on dsj closing and disposing off filtergraphs when the JVM exits. This is
		OK for a "open graph, do something & exit" style demo, but real world applications should take care of calling
		dispose() on filtergraphs they're done with themselves.
		**/

		f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

	}

	private void showEmbeddedPropPage(final DSFilter theFilter) {

		try{

			pages.setVisible(false);

			pages.dispose();

		} catch (Exception e){}

		int numPages = theFilter.getPropPageCount();

		if (numPages == 0) return;

		pages = new JDialog(new JFrame(), "Filter properties: "+theFilter.getName(), false);

		pages.setResizable(false);

		String[] titles = theFilter.getPropPageTitles();

		final int[] ppDim = theFilter.getPropPageSize(0);

		final int buttonHeight = (int)(Math.ceil(numPages/4.0)*20);

		final int confirmHeight = 30;

		JPanel buttons = null;

		if (numPages > 1) {

			buttons = new JPanel(new java.awt.GridLayout(0, 4));
			buttons.setPreferredSize(new java.awt.Dimension(ppDim[1], buttonHeight));

			for (int i = 0; i < numPages; i++) {

				final int id = i;

				final JButton b = new JButton(titles[i]);
				b.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent e){

						try{

							theFilter.closePropPage();

							pages.setPreferredSize(new java.awt.Dimension(theFilter.getPropPageSize(id)[0], theFilter.getPropPageSize(id)[1]+buttonHeight+confirmHeight));

							theFilter.embedPropertiesPage(pages, new java.awt.Point(0,  buttonHeight), id);

							pages.pack();

						} catch (DSJException de){}
					}
				});
				buttons.add(b);
			}

			pages.add(java.awt.BorderLayout.NORTH, buttons);

		}

		pages.setPreferredSize(new java.awt.Dimension(ppDim[0], ppDim[1] + buttonHeight+confirmHeight));
		pages.addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent we) {
				try{ theFilter.closePropPage(); } catch (Exception e){}
			}
		});

		JPanel pp = new JPanel();
		pp.setPreferredSize(new java.awt.Dimension(ppDim[0], ppDim[1]));
		pages.add(java.awt.BorderLayout.CENTER, pp);

		JPanel confirm = new JPanel(new java.awt.GridLayout(1, 0));
		((java.awt.GridLayout)(confirm.getLayout())).setHgap(8);
		confirm.setBorder(new javax.swing.border.EmptyBorder(0,0,7,10));
		confirm.setPreferredSize(new java.awt.Dimension(ppDim[0], confirmHeight));
		final JButton c = new JButton("Apply");
		c.setPreferredSize(new java.awt.Dimension(70, 20));
		c.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e){
				try{
					theFilter.applyPropPageSettings();
				} catch (DSJException de){}
			}
		});
		final JButton ok = new JButton("OK");
		ok.setPreferredSize(new java.awt.Dimension(70, 20));
		ok.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e){
				try{
					pages.dispose();
				} catch (DSJException de){}
			}
		});
		confirm.add(javax.swing.Box.createRigidArea(new java.awt.Dimension(70, 20)));
		confirm.add(javax.swing.Box.createRigidArea(new java.awt.Dimension(70, 20)));
		confirm.add(c);
		confirm.add(ok);
		pages.add(java.awt.BorderLayout.SOUTH, confirm);

		pages.pack();

		theFilter.embedPropertiesPage(pages, new java.awt.Point(0, buttonHeight), 0);

		pages.setVisible(true);

	}


	public static void main(String[] args){

		new SwingPropPages().createGraph();

	}


}