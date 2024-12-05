/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ajuda;

/**
 *
 * @author cristiano
 */
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class MediaTest
{
   // inicia a aplicação
   public static void main( String args[] )throws MalformedURLException
   {
         String video = "/G:/FILMES/capture1.swf";
         File arquivo_video = new File(video);

         URL mediaURL = null;

         try{
               mediaURL = arquivo_video.toURL();
         }catch(MalformedURLException malformedURLException){
               System.err.println("Não foi possível criar um URL do arquivo");
         }
         if(mediaURL != null){
              MediaPanel mediaPanel = new MediaPanel(mediaURL);
         }

         if ( mediaURL != null ) // exibi somente se houver uma URL válida
         {
            JFrame mediaTest = new JFrame( "Mangue Media Player" );
            mediaTest.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

            MediaPanel mediaPanel = new MediaPanel( mediaURL );
            mediaTest.add( mediaPanel );
            //System.out.println("URL: "+mediaURL);
            mediaTest.setSize( 300, 300 );
            mediaTest.setLocation(500, 100);
            mediaTest.setVisible( true );
         }
      }
}  
