# iwatts - Tech challenger Pós tech FIAP

## Grupo 29
- [@marsleite](https://github.com/marsleite)
- [@CaioLacerda88](https://github.com/CaioLacerda88)
- [@GugaASF](https://github.com/GugaASF)


## Motivação e Objetivo

Atualmente, muitos consumidores de produtos eletroeletrônicos acabam gastando mais do que realmente precisam, o que encarece o orçamento familiar e desperdiça energia desnecessariamente. A solução tecnológica proposta tem como objetivo central fornecer aos usuários uma visão em tempo real de seus consumos, permitindo identificar onde ocorrem os maiores gastos e se há vazamentos de corrente em pontos específicos. Isso possibilitará aos usuários tomar medidas adequadas para economizar energia e reduzir seus custos.

## Requisitos Funcionais

### Cadastro de endereços

- Receber as informações dos endereços, como os campos de rua, número, bairro, cidade e estado.
- Receber as solicitações em formato HTTP POST.
- Validar as informações para garantir que estejam no formato correto e sejam válidas.
- Retornar uma mensagem de erro em caso de problemas.
- Confirmar o sucesso do cadastro retornando uma resposta positiva ao usuário.
- Consultar os endereços cadastrados.
- Atualizar os endereços cadastrados.
- Excluir os endereços cadastrados.

### Gestão de pessoas

- Receber as informações das pessoas, incluindo nome, data de nascimento, sexo e parentesco com o usuário.
- Receber as solicitações em formato HTTP POST.
- Validar as informações recebidas.
- Retornar erro e uma mensagem em caso de problemas.
- Consultar as pessoas cadastradas.
- Atualizar as pessoas cadastradas.
- Excluir as pessoas cadastradas.

### Gestão de eletrodomésticos

- Receber informações dos eletrodomésticos, como nome, modelo e potência.
- Receber as solicitações em formato HTTP POST.
- Validar as informações recebidas.
- Consultar os eletrodomésticos cadastrados.
- Atualizar os eletrodomésticos cadastrados.
- Excluir os eletrodomésticos cadastrados.

## Use Cases

### Gestão de pessoas

`POST /iwatts/api/v1/pessoa/register`
```json
{
  "nome": "Fulano",
  "email": "iwatts@gmail.com",
  "dataNascimento": "2000-10-03",
  "sexo": "Masculino",
  "parentesco": "Pai"
}
```

`Response:
Status Code: 201 created`
```json
{
  "id": 1,
  "nome": "Fulano",
  "email": "iwatts@gmail.com"
}
```

### Cadastro de endereço

`POST /iwatts/api/v1/endereco/register`
```json
{
  "rua": "Rua A",
  "numero": "10",
  "cidade": "Sao Paulo",
  "estado": "SP",
  "cep": "12565178",
  "pais": "Brasil",
  "bairro": "Morumbi"
}
```
`Response:
Status Code: 201 created`
```json        
{
  "id": 1
}
```

### Consulta de endereço

`GET /iwatts/api/v1/endereco/findEnderecosByPessoa/{pessoaId}`
```json
{
  "id": 1,
  "rua": "Rua A",
  "numero": "10",
  "cidade": "Sao Paulo",
  "estado": "SP",
  "cep": "12565178",
  "pais": "Brasil",
  "bairro": "Morumbi"
}
```

### Consulta de endereço por bairro

`GET /iwatts/api/v1/endereco/findEnderecosByBairro/{bairro}`
```json
{
  "id": 1,
  "rua": "Rua A",
  "numero": "10",
  "cidade": "Sao Paulo",
  "estado": "SP",
  "cep": "12565178",
  "pais": "Brasil",
  "bairro": "Morumbi"
}
```

### Consulta de endereço por cidade

`GET /iwatts/api/v1/endereco/findEnderecosByCidade/{cidade}`
```json
{
  "id": 1,
  "rua": "Rua A",
  "numero": "10",
  "cidade": "Sao Paulo",
  "estado": "SP",
  "cep": "12565178",
  "pais": "Brasil",
  "bairro": "Morumbi"
}
```

### Consulta de endereço por estado

`GET /iwatts/api/v1/endereco/findEnderecosByEstado/{estado}`
```json
{
  "id": 1,
  "rua": "Rua A",
  "numero": "10",
  "cidade": "Sao Paulo",
  "estado": "SP",
  "cep": "12565178",
  "pais": "Brasil",
  "bairro": "Morumbi"
}
```

### Consulta de endereço por rua

`GET /iwatts/api/v1/endereco/findEnderecosByRua/{rua}`
```json
{
  "id": 1,
  "rua": "Rua A",
  "numero": "10",
  "cidade": "Sao Paulo",
  "estado": "SP",
  "cep": "12565178",
  "pais": "Brasil",
  "bairro": "Morumbi"
}
```

### Atualização de endereço

`PUT /iwatts/api/v1/endereco/updateEndereco/{enderecoId}`
```json
{
  "rua": "Rua A",
  "numero": "10",
  "cidade": "Sao Paulo",
  "estado": "SP",
  "cep": "12565178",
  "pais": "Brasil",
  "bairro": "Morumbi"
}
```

### Exclusão de endereço

`DELETE /iwatts/api/v1/endereco/deleteEndereco/{enderecoId}`

### Cadastro de Eletrodomésticos

`POST /iwatts/api/v1/eletrodomestico/register`
```json
{
  "name": "Geladeira",
  "modelo": "AB-GELADEIRA",
  "potencia": 1500
}
```
`Response
Status Code: 201 created`
```json
{
  "id": 1,
  "nome": "Geladeira",
   "modelo": "AB-GELADEIRA",
  "potencia": 1500
}
```

### Usando o docker

Para executar o projeto com o docker, siga as etapas abaixo:

1. Abra o terminal e navegue até a pasta raiz do projeto.
2. Execute o comando `docker-compose up -d` para iniciar a app e o banco de dados MySQL.
3. Execute o comando `./gradlew bootRun` para iniciar o projeto.
4. Acesse a URL `http://localhost:8080/iwatts/api/v1/eletrodomestico/register` em um navegador web para testar a API.
5. Execute o comando `docker-compose down` para parar o banco de dados H2.
6. Execute o comando `./gradlew clean` para limpar os arquivos gerados pelo projeto.

## Tecnologias utilizadas

- Java 17
- Spring versão 3.1.1
- MySQL DataBase
- Projeto em gradle
- Docker

## Executando a aplicação

Para executar a aplicação, siga as etapas abaixo:

1. Certifique-se de ter o Java JDK e o Apache Maven instalados em sua máquina.

2. Clone o repositório do projeto iwatts.

3. Navegue até o diretório raiz do projeto no terminal e na branch `develop`.

4. Execute o seguinte comando para compilar o projeto:
    - `./gradlew clean build`
5. Após a conclusão da compilação, execute o seguinte comando para iniciar a aplicação:
    - `./gradlew bootRun`


A aplicação será iniciada e estará pronta para receber as solicitações nos endpoints especificados.

# Desafios e Consideração Final - Projeto iWatts

Durante o desenvolvimento do projeto *iWatts*, que utiliza a combinação do framework Spring com Java 17, nos deparamos com desafios significativos relacionados à gestão das tabelas de endereço, as quais possuem uma relação de um para muitos com as tabelas de pessoa e eletrodoméstico. Neste contexto, enfrentamos dificuldades para estabelecer corretamente a correlação entre o cadastro de endereço e a ligação entre as tabelas que possuem essa relação.

Uma das principais razões para essa complexidade foi a ausência de uma camada de serviço no nosso sistema. Sem essa camada intermediária, a responsabilidade de gerenciar as relações entre as tabelas foi atribuída diretamente ao controller. No entanto, essa abordagem poderia ferir o princípio SOLID de responsabilidade única, uma vez que o controller já é responsável pela lógica de controle da interação com o usuário e pelo gerenciamento de requisições.

Ao tentar relacionar o cadastro de endereço com as tabelas de pessoa e eletrodoméstico no controller, notamos que a complexidade do código aumentava consideravelmente. Além disso, qualquer mudança nas regras de negócio ou nas relações entre as entidades exigiria alterações diretas no controller, o que comprometeria a manutenibilidade e a escalabilidade do sistema.

Diante desse cenário, identificamos a necessidade de introduzir uma camada de serviço ou use case para tratar das operações de relacionamento entre as tabelas. Essa camada intermediária nos permitiria encapsular a lógica complexa de correlação e manter o controller focado em suas responsabilidades principais, garantindo uma melhor aderência ao princípio SOLID.

Em conclusão, o projeto iWatts enfrentou desafios significativos na correlação das tabelas de endereço com as tabelas de pessoa e eletrodoméstico. A ausência de uma camada de serviço dificultou essa correlação, e a solução proposta é a implementação dessa camada intermediária para tratar das operações de relacionamento. Essa abordagem fortalecerá a arquitetura do sistema, mantendo o controller focado em suas responsabilidades principais e garantindo uma melhor aderência aos princípios SOLID.
