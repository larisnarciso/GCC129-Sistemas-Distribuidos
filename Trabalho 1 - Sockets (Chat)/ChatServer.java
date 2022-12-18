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
import java.net.*;

public class ChatServer {    // Classe Servidor que espera a conexão na porta
  public static void main(String args[]) throws IOException{
    ServerSocket serverSocket = null;
    Socket clientSocket = null;
    PrintWriter out = null;
    BufferedReader in = null;
    ChatThread escuta = null;
    int porta = 5000;

    try {
      serverSocket = new ServerSocket(porta);               // Cria objeto serverSocket
      try {
        System.out.println("Aguardando conexao...");
        clientSocket = serverSocket.accept();               // Aguardando conexão do Cliente
        out = new PrintWriter(clientSocket.getOutputStream(), true);          // Escreve - Saída do Socket
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));  // Escuta - Entrada do Socket
      }catch(IOException e){
        System.err.println("Erro na conexao");
        System.exit(1);
      }
    }catch (IOException e){
      System.err.println("Erro ao escutar a porta " + porta);
      System.exit(1);
    }

    System.out.println("Conexao iniciada");
    System.out.println("Digite uma mensagem:");

    escuta = new ChatThread(in);     // Cria o objeto Escuta recebe IN (Escuta)                              
    escuta.start();                 // Executa Thread em paralelo, escutando e exibindo na tela

    BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));    
    String entrada = "";

    do {
      entrada = stdIn.readLine();                       // Recebe uma mensagem do usuário
      out.println(entrada);                            // Escreve mensagem que usuário digitou no Socket 
      System.out.println("Servidor: " + entrada);     // Exibe mensagem na tela
    } while (!entrada.equals("Tchau"));

    out.close();
    in.close();
    serverSocket.close();
    clientSocket.close();
  }
}