#language: pt

Funcionalidade: Operações de criação de carros

  Contexto:
    Dado que a tabela de carros esteja vazia

  Cenario: O cliente faz um POST para criar um carro
    Quando o cliente requisita a criação dos seguintes carros:
      | id | Placa    | idModelo |
      | 1  | ASD-3345 | 2        |
    E o cliente recebe um status 201
    E o cliente recebe o header de location
    E o cliente requisita a lista de carros
    E o cliente recebe os seguintes carros:
      | Placa    | idModelo | nomeModelo | idMarca | nomeMarca |
      | ASD-3345 | 2        | Uno        | 2       | Fiat      |

  Cenario: O cliente faz um POST para criar um carro com placa em branco
    Quando o cliente requisita a criação dos seguintes carros:
      | id | Placa | idModelo |
      | 1  |       | 2        |
    E o cliente recebe um status 400

  Cenario: O cliente faz um POST para criar um carro com modelo em branco
    Quando o cliente requisita a criação dos seguintes carros:
      | id | Placa    | idModelo |
      | 1  | AAA-3233 |          |
    E o cliente recebe um status 400

  Cenario: O cliente faz um POST para criar um carro com um modelo que nao existe
    Quando o cliente requisita a criação dos seguintes carros:
      | id | Placa    | idModelo |
      | 1  | AAA-3233 | 99       |
    E o cliente recebe um status 400

  Cenario: O cliente faz um POST para criar um carro sem nenhum dado
    Quando o cliente requisita a criação de um carro sem enviar nenhum dado
    E o cliente recebe um status 400

  Cenario: O cliente faz um POST para criar um carro com o id de um carro já criado
    Dado que existem os seguintes carros no banco de dados:
      | id | Placa    | idModelo |
      | 1  | AAA-3233 | 1        |
    Quando o cliente requisita a criação do seguinte carro com o id do carro 1:
      | Placa    | idModelo |
      | ABC-9943 | 2        |
    Entao o cliente recebe um status 201
    E o cliente recebe o header de location
    E o cliente requisita a lista de carros
    E o cliente recebe os seguintes carros:
      | Placa    | idModelo | nomeModelo | idMarca | nomeMarca |
      | AAA-3233 | 1        | Corsa      | 1       | Chevrolet |
      | ABC-9943 | 2        | Uno        | 2       | Fiat      |
