JFLAGS = -g -d
JC = javac
SRC = ./src
BIN = ./bin

CLASSES = Polygon.java \
				DrawCanvas.java \
        Polygons.java

LIST = $(addprefix $(BIN)/, $(CLASSES:.java=.class))



default: $(BIN) $(LIST)

$(BIN):
	mkdir -p $(BIN)

$(BIN)/%.class: $(SRC)/%.java
	$(JC) $(JFLAGS) $(BIN) $<

clean:
	$(RM) $(BIN)/*.class
