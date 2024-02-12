# API de Empréstimos

## Sobre o Projeto

Este projeto implementa uma API de empréstimos que permite aos usuários solicitar e gerenciar empréstimos. A API foi desenvolvida utilizando o conceito de Arquitetura Limpa para garantir a separação de preocupações, facilitar a manutenção e a testabilidade, além de promover uma melhor organização do código.

## Por que Arquitetura Limpa?

A escolha pela Arquitetura Limpa se deu pelos seguintes motivos:

- **Separabilidade**: A arquitetura permite separar claramente as regras de negócio da interface do usuário, do banco de dados e de quaisquer agentes externos.
- **Testabilidade**: A separação clara facilita a escrita de testes unitários e de integração, já que as dependências podem ser facilmente simuladas ou substituídas.
- **Manutenibilidade**: A organização clara e a separação de componentes facilitam a manutenção e a evolução do sistema.
- **Independência de Tecnologia**: A lógica de negócio não fica atrelada a frameworks ou bancos de dados específicos, permitindo mudanças tecnológicas com menor impacto.

## Estrutura do Projeto

O projeto segue a seguinte estrutura de pacotes, alinhada com os princípios da Arquitetura Limpa:

- **`domain`**
    - **`entities`**: Contém as entidades do domínio.
    - **`usecases`**: Lógicas de negócio encapsuladas como casos de uso.
    - **`gateways`**: Interfaces dos gateways que abstraem o acesso a dados e serviços externos.
- **`infra`**
    - **`repositories`**: Implementações concretas dos repositórios, baseadas em frameworks de persistência.
    - **`gateways`**: Implementações concretas dos gateways definidos na camada de domínio.
- **`presentation`**
    - **`restcontrollers`**: Controladores REST que expõem as funcionalidades da API.

## Como Executar o Projeto

Para executar o projeto, siga os passos abaixo:

1. Clone o repositório:
   git clone https://github.com/linekerpablo/teste-instituicao-financeira.git
2. Entre no diretório do projeto:
   cd teste-instituicao-financeira
3. Execute o projeto utilizando o Maven:
   ./mvnw spring-boot:run

## Enviando Empréstimos para a Fila de Processamento

Após a solicitação de um empréstimo, a API envia os detalhes do empréstimo para uma fila de processamento, que será responsável por executar ações subsequentes, como análise de crédito e confirmação do empréstimo. Isso é feito utilizando o RabbitMQ como sistema de mensageria.

### Configuração do RabbitMQ

Certifique-se de que o RabbitMQ está instalado e em execução. A configuração padrão utilizada é:

- **Host**: `localhost`
- **Porta**: `5672`
- **Usuário**: `user`
- **Senha**: `password`

Você pode alterar essas configurações no arquivo `application.properties`.

Após subir o serviço do RabbitMQ, você pode acessar o painel de gerenciamento do RabbitMQ em http://localhost:15672 usando as credenciais definidas (user / password).

### Enviando Mensagens

Quando um empréstimo é solicitado através do endpoint REST correspondente, a API automaticamente envia uma mensagem para a fila do RabbitMQ contendo os detalhes do empréstimo. Um serviço de processamento de empréstimos deve estar escutando essa fila para processar as solicitações.