Estrutura do código até então:
Classe Polygons:
	- Classe principal, main, setupGUI
Classe DrawCanvas:
	- Trata da API do Java para desenhar figuras. É interessante que ela seja "invisível" para quem for implementar o código pedido em aula,
	i.e., não adicionar código/alterar código nessa classe (a não ser que necessário, por exemplo, ela cuida dos eventos do mouse).
	Estrutura da classe:
	- ArrayList<Polygon> polygons : lista de polígonos
	- DefaultListModel<String> polygonNames : lista de strings do tipo "Polygon + <int>"
	- JList<String> polygonList : elemento da GUI que exibe os nomes dos polígonos da lista polygonNames na interface

Classe Polygon:
	- Gerencia cada polígono desenhado em tela. Deve implementar os códigos que moça pediu.
	Estrutura da classe:
	- ArrayList<Point> vertices : lista de vértices adicionados através de cliques do mouse no canvas
	- ArrayList<Pixels> pixels : pixels de preenchimento/arestas gerado pelo código que a moça pediu
	- Graphics g : tela onde são desenhadas a grade, os pixels, etc

FAQ
. Como funciona o código?
A classe Polygons serve essencialmente para gerenciar a interface do programa e comunicar mudanças feitas pelo usuário à classe DrawCanvas
A classe DrawCanvas gerencia uma lista com todos os polígonos definidos pelo usuário. Também gerencia eventos do mouse.
Fornece uma estrutura para pintar "pixels" na grade, assim como gerar a própria grade. Essa estrutura funciona da seguinte forma:
	- Usuário clica em um espaço 
	- Um ponto da grade é adicionado ao polígono selecionado (se nenhum, cria um novo)
		- Se for o terceiro vértice adicionado, chama a função fillPolygon (código da moça) que deverá preencher o polígono
	- Chama-se a função repaint(), que irá chamar (internamente) paintComponent
	- A grade é redesenhada e é chamado paintPolygon para cada polígono
	- paintPolygon percorre as listas de vértices e pixels pintando um retângulo do tamanho dos quadrados da grade (equivale a um pixel)
A class Polygon contém todas as informações relativas a cor, vértices e pixels de um polígono

. Como implementar os códigos pedidos em aula pela moça?
Onde houver "paintPixel" no código, deve-se, ao invés, chamar a função "addPixel(Point p)"
. Como determinar as coordenadas corretas do ponto p?
---|------|------|------|------|------|---  ]
   |vertic|      |      |      |      |     ]
   |      |      |      |      |      |     ] gridSize (inicial: 20)
---|------X------|------|------|------|---  ]
   |      |pixel |      |      |      |
   |      |      |      |      |      |
---|------|------|------|------|------|---
Queremos pintar o pixel (1, 1) acima, dado o vértice (0, 0). O tamanho da grade é 20, ou seja, cada quadrado tem 20 de lado e as coordenadas das
intersecções das linhas e colunas da grade são múltiplos do tamanho da grade (20, no caso). Ou seja, para pintarmos o vértice (0, 0), as coordenadas
de p devem ser (0, 0)*. Para pintarmos o pixel (1, 1), as coordenadas de p devem ser (20, 20)*. Ou seja, é fácil obter os pixels a partir dos vértices
conhecendo-se o tamanho da grade gridSize.
. Fiz tudo isso e tá pintando tudo zoado/não está pintando nada/socorro. O que fazer?
Dá um toque que é fácil de corrigir. :)