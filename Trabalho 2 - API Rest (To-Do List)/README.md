# Serviço Web REST - API To Do List

O objetivo deste trabalho é implementar um sistema de gerenciamento de tarefas (to do list).

## Biblioteca/Framework/Tecnologias

![Javascript](https://img.shields.io/badge/JavaScript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black)
![Node.JS](https://img.shields.io/badge/Node.js-43853D?style=for-the-badge&logo=node.js&logoColor=white)

## Requisitos

- [Node & Npm](https://nodejs.org/en/)
- [MongoDB](https://www.mongodb.com/home)
- [Insomnia](https://insomnia.rest/download)

## Instalação

1. Clonar repositório

```
git clone https://github.com/larisnarciso/GCC129-Sistemas-Distribuidos.git
```

2. Instalar aplicação no terminal dentro da pasta api-todo-list

```
npm install
```

3. Criar variáveis de ambiente
   - Faça uma cópia do arquivo _.env.example_ na raiz do projeto. Renomeie-o para _.env_
4. Iniciar o servidor no terminal dentro da pasta api-todo-list

```
npm start
ou
npm run dev
```

5. Abrir Insomnia no endereço abaixo para testar serviço web `http://localhost:3000/tarefas `

---

## Modelo de Dados

```javascript
{
  id*: integer($uint)
  descricao*: string
  prazo: string($date)
  completa: boolean($boolean)
}
```

## Funcionalidades da API

|          |                 |                              |
| -------- | --------------- | ---------------------------- |
| `GET`    | `/tarefas/{id}` | Retorna uma tarefa pelo id   |
| `DELETE` | `/tarefas/{id}` | Exclui uma tarefa pelo id    |
| `PUT`    | `/tarefas/{id}` | Atualiza uma tarefa pelo id  |
| `POST`   | `/tarefas`      | Cria uma nova tarefa         |
| `GET`    | `/tarefas`      | Retorna uma lista de tarefas |

## Exemplo para teste no Insomnia

O **id** é criado automaticamente pelo MongoDB, o **prazo** está como padrão a data/hora atual e o parametro **completa** é iniciada como false. Dessa forma, pode ser criada uma tarefa passando apenas a **descrição**.

```javascript
POST
{
  "descricao": "Jogar lis",
  "prazo": "2022-12-25T02:32:51.407Z",
  "completa": false
}
ou
{
  "descricao": "Jogar lis"
}
```
