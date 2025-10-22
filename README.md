# TicTacToe AI (Go & Java)

Même jeu (morpion) implémenté en **Go** et en **Java** avec une IA **minimax**.
- Terminal UI (CLI)
- Humain vs IA
- Code propre, commenté, facile à étendre

## Prérequis
- Go 1.21+
- Java 17+ (JDK)
- Maven 3.9+

## Lancer la version Go
```bash
cd go
go run .
```

## Lancer la version Java
```bash
cd java
mvn -q -DskipTests exec:java -Dexec.mainClass="com.example.tictactoe.Game"
```

## Idées d'amélioration
- Mode 2 joueurs
- Difficulté IA (profondeur limitée / random moves)
- UI graphique (JavaFX / ebiten en Go)
