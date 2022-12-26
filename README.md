# 7-days-of-java

O 7 days of Java é um mini projeto da Alura que apresenta 7 desafios por dia para estudos, nesse desafio será realizado consumo de APIs, utilização de collections, aprimoramento do conceito de POO na prática, entre outros aprendizados.

## Dia 1 

O desafio do primeiro dia foi o consumo da API do IMDB de TOP 250 filmes usando Java, nela foi utilizado o pacote **java.net.http** e as classes **HttpRequest**, **HttpClient** e **HttpResponse**. A requisição do tipo GET foi feita e ao executar foi obtido um json convertido em String e impresso o corpo dele no console.

Para acessar o webservice ou API da plataforma acesse: [https://imdb-api.com/api](https://imdb-api.com/api) 

Para algumas informações sobre a utilização do pacote e das classes mencionadas acesse: [https://docs.oracle.com/en/java/javase/11/docs/api/java.net.http/java/net/http/package-summary.html](https://docs.oracle.com/en/java/javase/11/docs/api/java.net.http/java/net/http/package-summary.html) 

## Dia 2

A requisição foi realizada com sucesso e agora se tem um json como string e o próximo desafio é parsear essa resposta. O json possui um array de filmes e cada filme possui atributos como ID, Título, ano, etc. A ideia inicial é aplicar as principais bibliotecas incluídas no JRE. como a classe String e as expressões regulares. Como resultado do parseamento foi criado 4 listas (titles, urlImage, year e imdbRating). 

## Dia 3

Realizado o dia 2 agora a tarefa é iniciar uma modelagem melhor ao código utilizando POO. Foi criado uma classe **Movie** e os seguintes atributos **title**, **urlImage**, **year** e **imdbRating**. Após a criação dessa classe foi organizado uma lista única de filmes(*List Movie*). 


## Dia 4

No momento temos uma impressão do corpo do json no console e uma lista única de filmes, porém o objetivo desse dia é criar uma saída usando o HTML. Portanto, vamos criar uma classe HTMLGenerator que irá receber um construtor Writer e irá gerar o HTML a partir da lista de filmes criada anteriormente.

## Dia 5

No dia 5 o desafio é para estudar o encapsulamento. O HTML foi gerado e encapsulado em uma classe pois essa geração não importa para quem lê o código, mas ainda há outros códigos expostos na classe principal. A tarefa desse dia é encapsular a chamada da requisição em uma classe **ImdbApiClient** e separar as resposnsabilidades do parseamento do Json em uma classe. 

## Dia 6

O objetivo do dia 6 é deixar o código mais genérico possível para ser possível receber dados de outras APIs e para isso será utilizado **Interfaces**. A interface para filmes será chamada *Content* e para o parseamento será *JsonParser*.

## Dia 7

O objetivo do dia 7 é ordenar os filmes da api da forma em que achar melhor, no caso eu as ordenei por ordem alfabética. Ao finalizar esse desafio de 7 dias meu conhecimento sobre a linguagem java e o consumo de APIs foi aumentado consideravelmente e a evolução foi bastante satisfatória. 
