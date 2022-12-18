/*
 *  GCC129 - Sistemas Distribuidos (10A)
 *  Trabalho Prático 1 - Programação com a API de Sockets 
 *  Implementação de uma aplicação de mensagem instantânea (Chat)
 *  Com um sistema cliente-servidor utilizando API de sockets
 * 
 *  Ana Beatriz Rodrigues Torres
 *  Larissa Narciso Oliveira 
*/
import java.io.*;

public class ChatThread extends Thread {       // Thread que escuta as mensagens e exibe na tela
  private BufferedReader in;
  private String entrada = "";
  
  public ChatThread(BufferedReader in){       // Recebe objeto IN
    this.in = in;
  }
  
  public void run(){       
    try {
      while ((entrada = in.readLine()) != null){     // Enquanto escutar a Porta
        System.out.println("Recebi: " + entrada);   // É exibido na tela a mensagem que escutou
      }
    }
    catch (IOException e){
      System.exit(1);
    }
    finally{
      try{
        System.out.println("O outro lado se desconectou.");
        in.close();
        System.exit(0);
      }
      catch(IOException e){
        System.exit(1);
      }
    }
  }
}