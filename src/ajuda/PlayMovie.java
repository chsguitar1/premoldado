package ajuda;

/**
dsj demo code.
You may use, modify and redistribute this code under the terms laid out in the header of the DSJDemo application.
copyright 2005-10
N.Peters
humatic GmbH
Berlin, Germany
 **/
import de.humatic.dsj.*;
import javax.swing.JOptionPane;

public class PlayMovie implements java.beans.PropertyChangeListener {
String arquivo ;
    private DSFiltergraph movie;

    public PlayMovie(String arquivo) {
        this.arquivo = arquivo;
        createGraph();
    }

    public void createGraph() {

        javax.swing.JFrame f = new javax.swing.JFrame("dsj - play movie");

//         java.awt.FileDialog fd = new java.awt.FileDialog(f, "select movie", java.awt.FileDialog.LOAD);
//
//          fd.setVisible(true);
//
//        if (fd.getFile() == null) {
//            return;
//        }
       // System.out.println(fd.getDirectory() + fd.getFile
         String caminhorel = System.getProperty("user.dir");

//       JOptionPane.showMessageDialog(null, caminhorel + "\\videos\\" + arquivo);
//        System.out.println(arquivo);

       // movie = new DSMovie( "\\C:\\Aca\\videos\\" + arquivo, DSFiltergraph.DD7, this);

         movie = new DSMovie(caminhorel + "\\videos\\" + arquivo, DSFiltergraph.DD7, this);
       // movie = new DSMovie("", DSFiltergraph.DD7, this);

        f.getContentPane().add(java.awt.BorderLayout.CENTER, movie.asComponent());

        f.getContentPane().add(java.awt.BorderLayout.SOUTH, new SwingMovieController(movie));

        f.pack();

        f.setVisible(true);

        /**
        Don't do this at home. This demo relies on dsj closing and disposing off filtergraphs when the JVM exits. This is
        OK for a "open graph, do something & exit" style demo, but real world applications should take care of calling
        dispose() on filtergraphs they're done with themselves.
         **/
        f.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

    }

    public void propertyChange(java.beans.PropertyChangeEvent pe) {

        //System.out.println("received event or callback from "+pe.getPropagationId());

        switch (DSJUtils.getEventType(pe)) {

        }

    }

   
}
