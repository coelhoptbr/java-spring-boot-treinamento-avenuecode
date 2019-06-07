#language: pt

Funcionalidade: Operações de exclusão de carros

  Contexto:
    Dado que a tabela de carros esteja vazia

  Cenario: O cliente faz um DELETE para excluir um carro
    Dado que existem os seguintes carros no banco de dados:
      | id | Placa    | idModelo |
      | 1  | AAA-2123 | 1        |
      | 2  | AAA-2124 | 1        |
    Quando o cliente requisita a exclusão do carro com ID 1
    Entao o cliente recebe um status 204
    E o cliente requisita a lista de carros
    E o cliente recebe os seguintes carros:
      | Placa    | idModelo | nomeModelo | idMarca | nomeMarca |
      | AAA-2124 | 1        | Corsa      | 1       | Chevrolet |

  Cenario: O cliente faz um DELETE para excluir um carro que não existe
    Quando o cliente requisita a exclusão do carro com ID 1
    Entao o cliente recebe um status 404
