Estrutura do c�digo at� ent�o:
Classe Polygons:
	- Classe principal, main, setupGUI
Classe DrawCanvas:
	- Trata da API do Java para desenhar figuras. � interessante que ela seja "invis�vel" para quem for implementar o c�digo pedido em aula,
	i.e., n�o adicionar c�digo/alterar c�digo nessa classe (a n�o ser que necess�rio, por exemplo, ela cuida dos eventos do mouse).
	Estrutura da classe:
	- ArrayList<Polygon> polygons : lista de pol�gonos
	- DefaultListModel<String> polygonNames : lista de strings do tipo "Polygon + <int>"
	- JList<String> polygonList : elemento da GUI que exibe os nomes dos pol�gonos da lista polygonNames na interface

Classe Polygon:
	- Gerencia cada pol�gono desenhado em tela. Deve implementar os c�digos que mo�a pediu.
	Estrutura da classe:
	- ArrayList<Point> vertices : lista de v�rtices adicionados atrav�s de cliques do mouse no canvas
	- ArrayList<Pixels> pixels : pixels de preenchimento/arestas gerado pelo c�digo que a mo�a pediu
	- Graphics g : tela onde s�o desenhadas a grade, os pixels, etc

FAQ
. Como funciona o c�digo?
A classe Polygons serve essencialmente para gerenciar a interface do programa e comunicar mudan�as feitas pelo usu�rio � classe DrawCanvas
A classe DrawCanvas gerencia uma lista com todos os pol�gonos definidos pelo usu�rio. Tamb�m gerencia eventos do mouse.
Fornece uma estrutura para pintar "pixels" na grade, assim como gerar a pr�pria grade. Essa estrutura funciona da seguinte forma:
	- Usu�rio clica em um espa�o 
	- Um ponto da grade � adicionado ao pol�gono selecionado (se nenhum, cria um novo)
		- Se for o terceiro v�rtice adicionado, chama a fun��o fillPolygon (c�digo da mo�a) que dever� preencher o pol�gono
	- Chama-se a fun��o repaint(), que ir� chamar (internamente) paintComponent
	- A grade � redesenhada e � chamado paintPolygon para cada pol�gono
	- paintPolygon percorre as listas de v�rtices e pixels pintando um ret�ngulo do tamanho dos quadrados da grade (equivale a um pixel)
A class Polygon cont�m todas as informa��es relativas a cor, v�rtices e pixels de um pol�gono

. Como implementar os c�digos pedidos em aula pela mo�a?
Onde houver "paintPixel" no c�digo, deve-se, ao inv�s, chamar a fun��o "addPixel(Point p)"
. Como determinar as coordenadas corretas do ponto p?
---|------|------|------|------|------|---  ]
   |vertic|      |      |      |      |     ]
   |      |      |      |      |      |     ] gridSize (inicial: 20)
---|------X------|------|------|------|---  ]
   |      |pixel |      |      |      |
   |      |      |      |      |      |
---|------|------|------|------|------|---
Queremos pintar o pixel (1, 1) acima, dado o v�rtice (0, 0). O tamanho da grade � 20, ou seja, cada quadrado tem 20 de lado e as coordenadas das
intersec��es das linhas e colunas da grade s�o m�ltiplos do tamanho da grade (20, no caso). Ou seja, para pintarmos o v�rtice (0, 0), as coordenadas
de p devem ser (0, 0)*. Para pintarmos o pixel (1, 1), as coordenadas de p devem ser (20, 20)*. Ou seja, � f�cil obter os pixels a partir dos v�rtices
conhecendo-se o tamanho da grade gridSize.
. Fiz tudo isso e t� pintando tudo zoado/n�o est� pintando nada/socorro. O que fazer?
D� um toque que � f�cil de corrigir. :)