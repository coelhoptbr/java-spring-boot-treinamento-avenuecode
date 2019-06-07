#language: pt

Funcionalidade: Operações de atualização de carros

  Contexto:
    Dado que a tabela de carros esteja vazia

  Cenario: O cliente faz um PUT para atualizar um carro
    Dado que existem os seguintes carros no banco de dados:
      | id | Placa    | idModelo |
      | 1  | AAA-2123 | 1        |
      | 2  | AAA-2124 | 1        |
    Quando o cliente requisita a atualização do carro com ID 1:
      | Placa    | idModelo |
      | ASD-3345 | 2        |
    Entao o cliente recebe um status 204
    E o cliente requisita a lista de carros
    E o cliente recebe os seguintes carros:
      | Placa    | idModelo | nomeModelo | idMarca | nomeMarca |
      | ASD-3345 | 2        | Uno        | 2       | Fiat      |
      | AAA-2124 | 1        | Corsa      | 1       | Chevrolet |

  Cenario: O cliente faz um PUT para atualizar um carro enviando um ID diferente no body
    Dado que existem os seguintes carros no banco de dados:
      | id | Placa    | idModelo |
      | 1  | AAA-2123 | 1        |
      | 2  | AAA-2124 | 1        |
    Quando o cliente requisita a atualização do carro com ID 1:
      | id | Placa    | idModelo |
      | 2  | ASD-3345 | 2        |
    Entao o cliente recebe um status 204
    E o cliente requisita a lista de carros
    E o cliente recebe os seguintes carros:
      | Placa    | idModelo | nomeModelo | idMarca | nomeMarca |
      | ASD-3345 | 2        | Uno        | 2       | Fiat      |
      | AAA-2124 | 1        | Corsa      | 1       | Chevrolet |

  Cenario: O cliente faz um PUT para atualizar um carro com placa em branco
    Dado que existem os seguintes carros no banco de dados:
      | id | Placa    | idModelo |
      | 1  | AAA-2123 | 1        |
      | 2  | AAA-2124 | 2        |
    Quando o cliente requisita a atualização do carro com ID 1:
      | Placa | idModelo |
      |       | 2        |
    Entao o cliente recebe um status 400
    E o cliente requisita a lista de carros
    E o cliente recebe os seguintes carros:
      | Placa    | idModelo | nomeModelo | idMarca | nomeMarca |
      | AAA-2123 | 1        | Corsa      | 1       | Chevrolet |
      | AAA-2124 | 2        | Uno        | 2       | Fiat      |

  Cenario: O cliente faz um PUT para atualizar um carro com modelo em branco
    Dado que existem os seguintes carros no banco de dados:
      | id | Placa    | idModelo |
      | 1  | AAA-2123 | 1        |
      | 2  | AAA-2124 | 2        |
    Quando o cliente requisita a atualização do carro com ID 1:
      | Placa    | idModelo |
      | DSS-2123 |          |
    Entao o cliente recebe um status 400
    E o cliente requisita a lista de carros
    E o cliente recebe os seguintes carros:
      | Placa    | idModelo | nomeModelo | idMarca | nomeMarca |
      | AAA-2123 | 1        | Corsa      | 1       | Chevrolet |
      | AAA-2124 | 2        | Uno        | 2       | Fiat      |

  Cenario: O cliente faz um PUT para atualizar um carro com um modelo que não existe
    Dado que existem os seguintes carros no banco de dados:
      | id | Placa    | idModelo |
      | 1  | AAA-2123 | 1        |
      | 2  | AAA-2124 | 2        |
    Quando o cliente requisita a atualização do carro com ID 1:
      | Placa    | idModelo |
      | DSS-2123 | 99       |
    Entao o cliente recebe um status 400
    E o cliente requisita a lista de carros
    E o cliente recebe os seguintes carros:
      | Placa    | idModelo | nomeModelo | idMarca | nomeMarca |
      | AAA-2123 | 1        | Corsa      | 1       | Chevrolet |
      | AAA-2124 | 2        | Uno        | 2       | Fiat      |

  Cenario: O cliente faz um PUT para atualizar um carro sem enviar nenhum dado
    Dado que existem os seguintes carros no banco de dados:
      | id | Placa    | idModelo |
      | 1  | AAA-2123 | 1        |
      | 2  | AAA-2124 | 2        |
    Quando o cliente requisita a atualização do carro com ID 1 sem enviar nenhum dado
    Entao o cliente recebe um status 400
    E o cliente requisita a lista de carros
    E o cliente recebe os seguintes carros:
      | Placa    | idModelo | nomeModelo | idMarca | nomeMarca |
      | AAA-2123 | 1        | Corsa      | 1       | Chevrolet |
      | AAA-2124 | 2        | Uno        | 2       | Fiat      |
