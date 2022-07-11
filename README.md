<h1 style="text-align: center; font-weight: bold;">RabbitMQ Service</h1>

---

## Sobre o Projeto

Projeto do curso *Spring AMQP com RabbitMQ* da plataforma **Digital Innovation One**.

Neste projeto foram implementados exemplos práticos de produtores e consumidores com os principais tipos de Exchanges
(Fanout, Direct e Topic) do RabbitMQ. Onde envolveu necessariamente assuntos sobre Mensagens, Filas, Routing Keys, Bind, Binding Keys e Exchange.

O curso abordou questões como: 
* Sistemas de mensageria;
* Estrutura do RabbitMQ;
* Message broker - Exchanges;
* Acks e Tratamento de erros.

Aprendi mais sobre o protocolo AMQP, sendo este utilizado pelo RabbitMQ para transporte das mensagens, oferendo segurança e confiabilidade.

Também sobre os dois modelos de comunicação assíncrona, o primeiro denomina-se <b>Sistemas de filas</b>, onde a comunicação 
é direta entre as filas e o segundo o <b>Publisher/Subscriber</b> que possui Exchange para orquestrar o envio de mensagens para as filas.

No contexto de tratamentos de erros, o RabbitMQ oferece suporte para tratar problemas no consumo e publicação de mensagens.
Assim como, possui uma forma de tratamento chamada Dead Letter Exchange, que é enviar para uma fila as mensagens que não foram consumidas
levando em consideração alguns critérios. Este tratamento pode ser implementado através de politicas do servidor ou propriedades da fila.



### 🛠 Tecnologias

As seguintes ferramentas foram usadas na construção do projeto:

- [Java 11]()
- [RabbitMQ]()
- [Spring Boot]()
- [Spring AMQP]()
- [Maven]()
- [Lombok]()

---


<a href="https://www.linkedin.com/in/leandroebarros/">
<img src="./github/linkedin.png" alt="linkedin" height="30"></a>
<br />

LinkedIn: [Leandro Barros](https://www.linkedin.com/in/leandroebarros/)
