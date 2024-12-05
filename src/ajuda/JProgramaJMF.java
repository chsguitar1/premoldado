/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ajuda;

/**
 *
 * @author cristiano
 */
import java.awt.Color;
import java.awt.Component;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.net.MalformedURLException;
import java.net.URL;
//import javax.media.CachingControl;
//import javax.media.CachingControlEvent;
//import javax.media.ControllerErrorEvent;
//import javax.media.ControllerEvent;
//import javax.media.ControllerListener;
//import javax.media.Manager;
//import javax.media.NoPlayerException;
//import javax.media.Player;
//import javax.media.RealizeCompleteEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class JProgramaJMF extends JFrame {
    
   // Instanciação de objetos necessários à aplicação
   //Player Visualizador = null;

   Component ComponenteVisual = null;

   Component ComponenteControle = null;

   Component BarraProgressao = null;

   JPanel InterfaceMidia;

   // Implementação do método construtor
   public JProgramaJMF(String NomeArquivoMidia) {
      URL URLArquivoMidia = null;
      getContentPane().setLayout(null);
      setTitle("JAVA Media Player");
      setBackground(Color.lightGray);
      setBounds(0, 0, 480, 540);

      InterfaceMidia = new JPanel();
      InterfaceMidia.setBounds(30, 50, 400, 430);
      InterfaceMidia.setBackground(Color.darkGray);
      InterfaceMidia.setLayout(null);
      getContentPane().add(InterfaceMidia);
//      super.addWindowListener(this);

      // Mecanismo para "tentar" associar uma mídia a um player
//      try {
//         URLArquivoMidia = new URL(NomeArquivoMidia);
//         Visualizador = Manager.createPlayer(URLArquivoMidia);
//         Visualizador.addControllerListener(this);
//         Visualizador.realize();
      }
      // Tratamento de exceções
//      catch (MalformedURLException Erro) {
//         System.err.println("Inválido URL para a mídia!");
//         return;
//      } catch (NoPlayerException Erro) {
//         System.err
//               .println("Não é possível encontrar um player adequado para a mídia");
//         return;
//      } catch (NullPointerException Erro) {
//         System.err.println("Não é possível criar um player para mídia");
//         return;
//      } catch (java.io.IOException Erro) {
//         System.err
//               .println("Erro de I/O no carregamento do arquivo da mídia");
//      }
//      System.out.println("Terminada a construção do player com sucesso...");
//   }

   // Métodos para controle dos eventos da janela principal
   public void windowClosed(WindowEvent Evento) {
//      Visualizador.close();
//      Visualizador = null;
      this.dispose(); // desalocar objetos da memória
      System.exit(0);
   }

   public void windowClosing(WindowEvent Evento) {
      this.dispose(); // desalocar objetos da memória
      System.exit(0);
   }

   public void windowDeactivated(WindowEvent Evento) {
   }

   public void windowActivated(WindowEvent Evento) {
   }

   public void windowIconified(WindowEvent Evento) {
   }

   public void windowDeiconified(WindowEvent Evento) {
   }

   public void windowOpened(WindowEvent Evento) {
   }

   // Implementação do método para tratamento de eventos do JAVA Media
   // Framework
//   public void controllerUpdate(ControllerEvent EventoReproducao) {
//      if (EventoReproducao instanceof RealizeCompleteEvent) {
//         // Experimente inserir comentários nos próximos dois "if"
//         // e veja o que irá acontecer...
//         ComponenteVisual = Visualizador.getVisualComponent();
//         if (ComponenteVisual != null) {
//            ComponenteVisual.setBounds(25, 25, 350, 350);
//            InterfaceMidia.setLayout(null);
//            InterfaceMidia.add(ComponenteVisual);
//         }
//         ComponenteControle = Visualizador.getControlPanelComponent();
//         if (ComponenteControle != null) {
//            ComponenteControle.setBounds(25, 380, 350, 25);
//            InterfaceMidia.setLayout(null);
//            InterfaceMidia.add(ComponenteControle);
//         }
//
//         InterfaceMidia.validate();
//         // InterfaceMidia.repaint();
//         System.out
//               .println("Evento de disparo da mídia completado com sucesso...");
//         Visualizador.start();
//      } else {
//         if (EventoReproducao instanceof ControllerErrorEvent)
//            System.err.println("Erro no controlador da mídia...");
//         else {
//            if (EventoReproducao instanceof CachingControlEvent) {
//               CachingControlEvent Execucao = (CachingControlEvent) EventoReproducao;
//               CachingControl ControleExecucao = Execucao
//                     .getCachingControl();
//               long Progresso = Execucao.getContentProgress();
//         
//      long TamanhoArquivo = ControleExecucao.getContentLength();
//
//               if (BarraProgressao == null) {
//                  BarraProgressao = ControleExecucao
//                        .getProgressBarComponent();
//                  if (BarraProgressao != null) {
//                     InterfaceMidia.add("NORTH", BarraProgressao);
//                     InterfaceMidia.validate();
//                  }
//                  if (BarraProgressao != null) {
//                     if (Progresso == TamanhoArquivo) {
//                        InterfaceMidia.remove(BarraProgressao);
//                        BarraProgressao = null;
//                        InterfaceMidia.validate();
//                     }
//                  }
//               }
//            }
//         }
//      }
//   }

   // Implementação do método "main"
   public static void main(String Argumentos[]) {
      String NomeArquivoMidia = "file:///G:/FILMES/capture.mov";
      JFrame VisualizadorApresentacao = new JProgramaJMF(NomeArquivoMidia);
      VisualizadorApresentacao.show();
      VisualizadorApresentacao.setVisible(true);
   }
}

