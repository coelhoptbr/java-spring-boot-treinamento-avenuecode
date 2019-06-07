#language: pt

Funcionalidade: Operações utilizando content-type e accept XML

  Contexto:
    Dado que a tabela de carros esteja vazia

  Cenario: O cliente requisita a lista de carros vazia utilizando accept XML
    Quando o cliente requisita a lista de carros utilizando accept XML
    Entao o cliente recebe um status 406

  Cenario: O cliente requisita a lista de carros populada utilizando accept XML
    Dado que existem os seguintes carros no banco de dados:
      | id | Placa    | idModelo |
      | 1  | AAA-2123 | 1        |
      | 2  | ASD-3345 | 2        |
      | 3  | BGD-3345 | 3        |
    Quando o cliente requisita a lista de carros utilizando accept XML
    Entao o cliente recebe um status 406

  Cenario: O cliente requisita a informação de um carro específico existente utilizando accept XML
    Dado o cliente requisita a criação dos seguintes carros:
      | id | Placa    | idModelo |
      | 1  | AAA-2123 | 1        |
    Quando o cliente requisita as informações do carro com ID 1 utilizando accept XML
    Entao o cliente recebe um status 406

  Cenario: O cliente requisita a informação de um carro específico não existente utilizando accept XML
    Quando o cliente requisita as informações do carro com ID 1 utilizando accept XML
    Entao o cliente recebe um status 406

  Cenario: O cliente requisita a exclusao de um carro utilizando accept XML
    Dado que existem os seguintes carros no banco de dados:
      | id | Placa    | idModelo |
      | 1  | AAA-2123 | 1        |
    Quando o cliente requisita a exclusão do carro com ID 1 utilizando accept XML
    Entao o cliente recebe um status 406

  Cenario: O cliente requisita a exclusao de um carro não existente utilizando accept XML
    Quando o cliente requisita a exclusão do carro com ID 1 utilizando accept XML
    Entao o cliente recebe um status 406

  Cenario: O cliente requisita a criação de um carro utilizando accept XML
    Quando o cliente requisita a criação dos seguintes carros utilizando accept XML:
      | id | Placa    | idModelo |
      | 1  | AAA-2123 | 1        |
    Entao o cliente recebe um status 406

  Cenario: O cliente requisita a atualizacao de um carro utilizando accept XML
    Dado que existem os seguintes carros no banco de dados:
      | id | Placa    | idModelo |
      | 1  | AAA-2123 | 1        |
    Quando o cliente requisita a atualização do carro com ID 1 utilizando accept XML:
      | Placa    | idModelo |
      | DSS-2123 | 99       |
    Entao o cliente recebe um status 406

  Cenario: O cliente requisita a criação de um carro utilizando content-type XML
    Quando o cliente requisita a criação dos seguintes carros utilizando content-type XML:
      | id | Placa    | idModelo |
      | 1  | AAA-2123 | 1        |
    Entao o cliente recebe um status 415

  Cenario: O cliente requisita a atualizacao de um carro utilizando content-type XML
    Dado que existem os seguintes carros no banco de dados:
      | id | Placa    | idModelo |
      | 1  | AAA-2123 | 1        |
    Quando o cliente requisita a atualização do carro com ID 1 utilizando content-type XML:
      | Placa    | idModelo |
      | DSS-2123 | 99       |
    Entao o cliente recebe um status 415

