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
import java.net.Socket;

public class ChatClient {     // Classe cliente que irá conectar ao Server Socket
  public static void main(String args[]) throws IOException{
    Socket clientSocket = null;
    PrintWriter out = null;
    BufferedReader in = null;
    ChatThread escuta = null;
    int porta = 5000;
    String serverHostname = new String ("localhost");

    System.out.println ("Tentando conexão com host " + serverHostname + " na porta " + porta);

    try {
      clientSocket = new Socket (serverHostname, porta);    // Objeto conversa com o computador do IP serverHostName na porta informada.
      out = new PrintWriter(clientSocket.getOutputStream(), true);          // Escreve - Saída do Socket
      in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));  // Escuta - Entrada do Socket
    } catch(IOException e){
      System.err.println("Erro ao tentar conectar com o host " + serverHostname);
      System.exit(1);
    }
    

    System.out.println("Conexao com servidor iniciada");
    System.out.println("Digite uma mensagem:");

    escuta = new ChatThread(in);     // Cria o objeto Escuta recebendo IN (Escuta)            
    escuta.start();                 // Executa Thread em paralelo, escutando e exibindo na tela

    BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));   
    String entrada = "";

    do {
      entrada = stdIn.readLine();                       // Recebe uma mensagem do usuário
      out.println(entrada);                            // Escreve mensagem que usuário digitou no Socket        
      System.out.println("Cliente: "   + entrada);    // Exibe mensagem na tela
    } while (!entrada.equals("Tchau"));

    out.close();
    in.close();
    clientSocket.close();
  }
}
