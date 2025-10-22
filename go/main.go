package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

func readMove(prompt string, b Board) int {
	reader := bufio.NewReader(os.Stdin)
	for {
		fmt.Print(prompt)
		text, _ := reader.ReadString('\n')
		text = strings.TrimSpace(text)
		i, err := strconv.Atoi(text)
		if err != nil || i < 1 || i > 9 || b[i-1] != Empty {
			fmt.Println("👉 Entrez un numéro libre entre 1 et 9.")
			continue
		}
		return i - 1
	}
}

func drawHelp() {
	fmt.Println("Index des cases:")
	fmt.Println(" 1 | 2 | 3")
	fmt.Println("---+---+---")
	fmt.Println(" 4 | 5 | 6")
	fmt.Println("---+---+---")
	fmt.Println(" 7 | 8 | 9")
	fmt.Println()
}

func main() {
	b := NewBoard()
	drawHelp()
	b.print()

	turn := Human // Humain commence
	for {
		if turn == Human {
			mv := readMove("Ton coup (1-9): ", b)
			b[mv] = Human
		} else {
			fmt.Println("IA réfléchit...")
			mv := BestAIMove(b)
			b[mv] = AI
			fmt.Printf("IA joue en %d\n", mv+1)
		}
		b.print()

		if term, score := b.terminal(); term {
			if score == +1 {
				fmt.Println("💻 IA gagne !")
			} else if score == -1 {
				fmt.Println("🎉 Tu gagnes !")
			} else {
				fmt.Println("🤝 Match nul.")
			}
			break
		}
		if turn == Human {
			turn = AI
		} else {
			turn = Human
		}
	}
}
