#language: pt

Funcionalidade: Operações de obtenção de informações sobre os carros

  Contexto:
    Dado que a tabela de carros esteja vazia

  Cenario: O cliente requisita a lista de carros
    Dado que existem os seguintes carros no banco de dados:
      | id | Placa    | idModelo |
      | 1  | AAA-2123 | 1        |
      | 2  | ASD-3345 | 2        |
      | 3  | BGD-3345 | 3        |
    Quando o cliente requisita a lista de carros
    Entao o cliente recebe um status 200
    E o cliente recebe os seguintes carros:
      | Placa    | idModelo | nomeModelo | idMarca | nomeMarca |
      | AAA-2123 | 1        | Corsa      | 1       | Chevrolet |
      | ASD-3345 | 2        | Uno        | 2       | Fiat      |
      | BGD-3345 | 3        | Versa      | 3       | Nissan    |

  Cenario: O cliente requisita as informações de um carro específico
    Dado que existem os seguintes carros no banco de dados:
      | id | Placa    | idModelo |
      | 1  | AAA-2123 | 1        |
      | 2  | ASD-3345 | 2        |
      | 3  | BGD-3345 | 3        |
    Quando o cliente requisita as informações do carro com ID 2
    Entao o cliente recebe um status 200
    E o cliente recebe o seguinte carro:
      | Placa    | idModelo | nomeModelo | idMarca | nomeMarca |
      | ASD-3345 | 2        | Uno        | 2       | Fiat      |

  Cenario: O cliente requisita as informações de um carro que não existe
    Quando o cliente requisita as informações do carro com ID 1
    E o cliente recebe um status 404
