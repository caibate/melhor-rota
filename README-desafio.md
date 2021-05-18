# Rota de Viagem #

Um turista deseja viajar pelo mundo pagando o menor preÃ§o possÃ­vel independentemente do nÃºmero de conexÃµes necessÃ¡rias.
Vamos construir um programa que facilite ao nosso turista, escolher a melhor rotaDTO para sua viagem.

Para isso precisamos inserir as rotas atravÃ©s de um arquivo de entrada.

## Input Example ##
```csv
GRU,BRC,10
BRC,SCL,5
GRU,CDG,75
GRU,SCL,20
GRU,ORL,56
ORL,CDG,5
SCL,ORL,20
```

## Explicando ## 
Caso desejemos viajar de **GRU** para **CDG** existem as seguintes rotas:

1. GRU - BRC - SCL - ORL - CDG ao custo de **$40**
2. GRU - ORL - CGD ao custo de **$64**
3. GRU - CDG ao custo de **$75**
4. GRU - SCL - ORL - CDG ao custo de **$45**

O melhor preÃ§o Ã© da rotaDTO **1** logo, o output da consulta deve ser **GRU - BRC - SCL - ORL - CDG**.

### ExecuÃ§Ã£o do programa ###
A inicializacao do teste se darÃ¡ por linha de comando onde o primeiro argumento Ã© o arquivo com a lista de rotas inicial.

```shell
$ mysolution input-rotas-teste.csv
```

Duas interfaces de consulta devem ser implementadas:
- Interface de console deverÃ¡ receber um input com a rotaDTO no formato "DE-PARA" e imprimir a melhor rotaDTO e seu respectivo valor.
  Exemplo:

```shell
please enter the route: GRU-CGD
best route: GRU - BRC - SCL - ORL - CDG > $40
please enter the route: BRC-CDG
best route: BRC - ORL > $30
```

- Interface Rest
  A interface Rest deverÃ¡ suportar:
    - Registro de novas rotas. Essas novas rotas devem ser persistidas no arquivo csv utilizado como input(input-routes.csv),
    - Consulta de melhor rotaDTO entre dois pontos.

TambÃ©m serÃ¡ necessÃ¡ria a implementaÃ§Ã£o de 2 endpoints Rest, um para registro de rotas e outro para consula de melhor rotaDTO.

## RecomendaÃ§Ãµes ##
Para uma melhor fluides da nossa conversa, atente-se aos seguintes pontos:

* Envie apenas o cÃ³digo fonte,
* Estruture sua aplicaÃ§Ã£o seguindo as boas prÃ¡ticas de desenvolvimento,
* Evite o uso de frameworks ou bibliotecas externas Ã  linguagem. Utilize apenas o que for necessÃ¡rio para a exposiÃ§Ã£o do serviÃ§o,
* Implemente testes unitÃ¡rios seguindo as boas praticas de mercado,
* DocumentaÃ§Ã£o
  Em um arquivo Texto ou Markdown descreva:
    * Como executar a aplicaÃ§Ã£o,
    * Estrutura dos arquivos/pacotes,
    * Explique as decisÃµes de design adotadas para a soluÃ§Ã£o,
    * Descreva sua APÃŒ Rest de forma simplificada.
