# Task Manager

Um projeto full stack 100% desenvolvido por mim, com o objetivo de demonstrar minhas habilidades e adquirir mais experiência com Spring boot, Angular e Docker.

Conforme o projeto progride irei listar as novas features abaixo.

## Como executar a aplicação?

Para executar a aplicação é muito simples, basta ter o Docker instalado e configurado em sua máquina, e executar o arquivo build.sh, como segue o exemplo?

~~~console
./build.sh
~~~

## Perfis do Back-end

O Back-end desenvolvido em Spring Boot contém 3 perfis, podendo ser alterado no arquivo [aplication.properties](/api/src/main/resources/application.properties) no atributo spring.profiles.active, sendo eles:

- test
    - No início de sua execução cria dados para testes unitários e de integração;
    - Instância e se conecta a um banco de dados H2.
    - Se conecta ao container de servidor FTP responsável por armazenar os anexos.
- dev
    - Configurado para desenvolvimento utilizando o Spring Remote, onde um container do back-end é instânciado, e conforme os arquivos do projeto são alterados, é realizado o upload para o container assim atualizando seu comportamento;
    - Se conecta ao banco de dados PostgreSQL instânciado pelo docker que é utilizado apenas para desenvolvimento;
    - Se conecta ao container de servidor FTP responsável por armazenar os anexos.
- prod
    - Se conecta ao banco de dados PostgreSQL de produção;
    - Se conecta ao servidor FTP de produção.

## Documentação do Back-end

O Back-end está sendo documentado utilizando o Swagger que por sua vez segue as especificações do OpenAPI, dessa forma, a documentação é padronizada de logo é familiar a qualquer desenvolvedor que já utilizou uma documentação que segue as mesmas especificações assim facilitando a curva de aprendizado.

Além disso, por seguir as especificações do OpenAPI é de extrema facilidade exportar a documentação para ferramentas como o Postman ou Insomnia.

## Segurança

A segurança sistema está a cargo do spring security, que é um framework poderoso com muitas opções de customização de autenticação e controle de acesso, como descrito em sua documentação.

A Autenticação é feita por meio de tokens JWT que podem ser recuperados pelo usuário por meio do endpoint ``/api/v1/auth/login``, onde é necessário enviar seu usuário e senha no body da requisição da seguinte maneira:

~~~json
{
    "username": "NOME DE USUÁRIO",
    "password": "SENHA"
}
~~~

Se a requisição for bem sucedida retornará um código 200 e o token de acesso no header authorization.

Para executar os requests que necessitam de autenticação, esse token deve ser enviado no header com o nome **Authorization**.


## Banco de dados

O sistema utiliza o PostgreSQL como base de dados. Segue abaixo o diagrama da base de dados.

![diagram](/api/docs/DatabaseDiagram.png)

O diagrama pode ser vizualizado com mais detalhes [aqui](https://dbdiagram.io/d/62362e4ebed6183873c38a3f).




