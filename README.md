# Task Manager

Um projeto full stack 100% desenvolvido por mim, com o objetivo de demonstrar minhas habilidades e adquirir mais experiência com Spring boot, Angular e Docker.

Conforme o projeto progride irei listar as novas features abaixo.

## Como executar a aplicação?

Para executar a aplicação é muito simples, basta ter o Docker instalado e configurado em sua máquina, e executar o arquivo build.sh, como segue o exemplo?

~~~console
./build.sh
~~~

## Configuração de execução remota

Esse projeto foi desenvolvido utilizando o Visual Code como principal ferramenta de desenvolvimento em meus projetos pessoais pois ele tem uma ótima compatibilidade com o WSL 2, pelo fato do Visual Code não ser um editor desenvolvido para desenvolvimento Java, o processo de configuração de recursos mais complexos é menos intuitivo. A melhor forma de fazer esse configuração é seguindo o seguinte passo a passo:

- Primeiro instale as seguintes extensões em seu Visual Code para possibilitar o desenvolvimento Java na ferramenta: 
    - vscjava.vscode-java-pack
    - Pivotal.vscode-boot-dev-pack
    - GabrielBB.vscode-lombok (Essa extensão é opcional, apenas necessário se utilizar o lombok para o desenvolvimento)

-  Na raiz de seu workspace crie o diretório .vscode e dentro um arquivo chamado launch.json com o seguinte conteúdo.

~~~json
{
    "configurations": [
        {
            "type": "java",
            "name": "Local",
            "request": "launch",
            "cwd": "${workspaceFolder}",
            "console": "internalConsole",
            "mainClass": "com.leonardo.taskmanager.TaskmanagerApplication",
            "projectName": "taskmanager",
            "args": "",
            "envFile": "${workspaceFolder}/.env"
        }, 
        {
            "type": "java",
            "name": "Remote",
            "request": "launch",
            "cwd": "${workspaceFolder}",
            "console": "internalConsole",
            "mainClass": "org.springframework.boot.devtools.RemoteSpringApplication",
            "projectName": "taskmanager",
            "args": "http://127.0.0.1:8080",
            "envFile": "${workspaceFolder}/.env"
        }
    ]
}
~~~

#### Lembrando, você precisa alterar o atributo "projectName" em ambas configurações com valor da tag "artifactId" de seu arquivo pom.xml, e na configuração local, alterar o atributo "mainClass" passando como valor a classe de seu projeto que contém o método main. 

Dessa forma você tera duas configurações de execução de seu back-end presentes na aba "Executar e Depurar". Uma sendo de forma convencional nesse caso chamada de "Local" e a outra de forma remota utilizando o Spring Remote, que nesse caso será chamada de "Remote".

## Perfis do Back-end

O Back-end desenvolvido em Spring Boot contém 3 perfis, podendo ser alterado no arquivo [aplication.properties](/api/src/main/resources/application.properties) no atributo spring.profiles.active, sendo eles:

- test
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




