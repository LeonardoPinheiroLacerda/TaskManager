# Task Manager

Um projeto full stack 100% desenvolvido por mim, com o objetivo de demonstrar minhas habilidades e adquirir mais experiência com Spring boot, Angular, Docker e outras tecnologias que serão citadas a baixo.

Conforme o projeto progride irei listar as novas features abaixo.

## Como executar a aplicação?

Para executar a aplicação é muito simples, basta ter o Docker instalado e configurado em sua máquina, e executar o arquivo build.sh, como segue o exemplo?

~~~console
./build.sh
~~~

## Documentação com swagger

Todos os end-points da aplicação estão documentão sendo sendo documentados pelo swagger no caminho ``/swagger-ui/index.html``.

O swagger é uma ferramenta muito utilizada para documentar APIs, pois: 
- Gera automaticamente a documentação da aplicação;
- Contém diversas configurações para adequar a documentação à sua aplicação, por exemplo: 
    - Adicionar descrições em métodos, parâmetros e models;
    - Configurações para métodos que esperam algum tipo de autenticação;
    - Adicionar informações de contato e mais.
- Segue o padrão **OpenAPI** o que facilita a integração da documentação gerada com softwares client http como Postman ou Insomnia.

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
- local
    - Mesma finalidade do perfil dev, mas configurado para executar a api em ambiente de desenvolvimento na máquina local sem Docker.
- prod
    - Se conecta ao banco de dados PostgreSQL de produção;
    - Se conecta ao servidor FTP de produção.

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

## Armazenamento de cache - Redis

Redis é um acrônimo de REmote DIctionary Server (servidor de dicionário remoto), onde ele é um banco de dados noSQL que armazena os dados com uma extrutura de chave-valor, pode ser utilizado para armazenamento de cache, gerenciamento de sessões e PUB/SUB.

Nessa aplicação ele está sendo utilizado para armazenar o cache da aplicação, com a finalidade de reduzir o número de consultas ao banco de dados, assim aprimorando a performance da aplicação.

