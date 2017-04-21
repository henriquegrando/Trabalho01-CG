JFLAGS = -g
JC = javac
SRC = ./src
BIN = ./bin
EXE = Polygons
RM = rm -rf

CLASSES = Polygon.java \
				DrawCanvas.java \
        Polygons.java	\
				Edges/Edge.java \
				Edges/EdgeTable.java 

LIST = $(addprefix $(BIN)/, $(CLASSES:.java=.class))

default: $(BIN) $(LIST)

$(BIN):
	mkdir -p $(BIN)

$(BIN)/%.class: $(SRC)/%.java
	$(JC) $(JFLAGS) -d $(BIN)  -sourcepath $(SRC) $<

.PHONY: clean run
clean:
	$(RM) $(BIN)

run:
	cd $(BIN); java $(EXE)
