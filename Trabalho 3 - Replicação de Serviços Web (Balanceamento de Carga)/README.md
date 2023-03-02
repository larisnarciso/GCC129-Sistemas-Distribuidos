# Replicação de Serviços Web - Balanceamento de Carga

O objetivo deste trabalho é implementar um cenário de balanceamento de carga em serviços web. O Sistema Operacional utilizado na realização do trabalho foi o _Ubuntu_.

## Biblioteca/Framework/Tecnologias

![Javascript](https://img.shields.io/badge/JavaScript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black)

## Requisitos

- [Nginx](https://docs.nginx.com/)
- [NodeJS](https://nodejs.org/en/)

## Instalação

1. Clonar repositório

```
git clone https://github.com/larisnarciso/GCC129-Sistemas-Distribuidos.git
```

2. Instalar Node.js

```
sudo apt install nodejs
```

3. Instalar Nginx

```
sudo apt install nginx
```

4. Iniciar Nginx

```
service nginx start
```

## Execução

Para rodar os arquivos (_Server-1.js, Server-2.js, Server-3.js_), abra 3 terminais diferentes na pasta do arquivo e execute cada comando em um terminal:

```
node server-1.js
node server-2.js
node server-3.js
```

Para confirmar se está funcionando, digite localhost:3001 ou localhost:3002 ou localhost:3003 no browser.

---

## Configuração Política de Balanceamento de Carga

Para configurar o balanceamento de carga deve editar o arquivo _nginx.conf_ dentro da pasta _/etc/nginx_, utilizado o comando

```
nano etc/nginx/nginx.conf
```

### Least Connection

O método Least Connection distribui a requisição para o servidor com menor carga naquele momento. Caso todos os servidores possuam a mesma quantidade de conexões, ele funciona como o Round Robin, onde as requisições são distribuídas de forma uniforme.

Um exemplo de configuração de balanceamento de carga Least Connection:

```javascript

  upstream backend_servers {
    least_conn;
    server localhost:3001;
    server localhost:3002;
    server localhost:3003 backup;
  }
  server {
    listen 80;
    server_name localhost;
    location / {
      proxy_pass http://backend_servers;
    }
  }

```

O servidor _Localhost:3003_ servirá como _backup_ para caso os servidores _Localhost:3001_ e _Localhost:3002_ fique indisponível.

### Weighted Least Connection

No método Weighted Least Connection o peso é aplicado apenas para determinar a proporção de solicitações que cada servidor receberá, mas não afeta a ordem em que as solicitações são encaminhadas para os servidores, dessa forma o método Least Connection continua sendo utilizado

Um exemplo de configuração de balanceamento de carga Weighted Least Connection:

```javascript

  upstream backend_servers {
    least_conn;
    server localhost:3001 weight=3;
    server localhost:3002 weight=2;
    server localhost:3003;
  }
  server {
    listen 80;
    server_name localhost;
    location / {
      proxy_pass http://backend_servers;
    }
  }

```

Dessa forma o servidor _Localhost:3001_ receberá o triplo de requisições do que o _Localhost:3003_. E o _Localhost:3002_ receberá o dobro de requisições que o _Localhost:3003_

### IP Hash

No método IP Hash o servidor para qual a solicitação é enviada é determinado a partir do endereço IP. Com esse método de balanceamento, as solicitações do mesmo cliente sempre serão direcionadas para o mesmo servidor, garantindo que o estado da sessão seja mantido corretamente.

Um exemplo de configuração de balanceamento de carga IP Hash:

```javascript

  upstream backend_servers {
    ip_hash;
    server localhost:3001;
    server localhost:3002;
    server localhost:3003;
  }
  server {
    listen 80;
    server_name localhost;
    location / {
      proxy_pass http://backend_servers;
    }
  }

```
