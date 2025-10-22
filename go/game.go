package main

import "fmt"

const (
	Empty = ' '
	Human = 'X'
	AI    = 'O'
)

type Board [9]rune

func NewBoard() Board {
	b := Board{}
	for i := 0; i < 9; i++ {
		b[i] = Empty
	}
	return b
}

func (b Board) print() {
	fmt.Println()
	for r := 0; r < 3; r++ {
		fmt.Printf(" %c | %c | %c\n", b[3*r], b[3*r+1], b[3*r+2])
		if r < 2 {
			fmt.Println("---+---+---")
		}
	}
	fmt.Println()
}

func (b Board) availableMoves() []int {
	var m []int
	for i := 0; i < 9; i++ {
		if b[i] == Empty {
			m = append(m, i)
		}
	}
	return m
}

func (b Board) winner() rune {
	lines := [8][3]int{
		{0, 1, 2}, {3, 4, 5}, {6, 7, 8},
		{0, 3, 6}, {1, 4, 7}, {2, 5, 8},
		{0, 4, 8}, {2, 4, 6},
	}
	for _, ln := range lines {
		if b[ln[0]] != Empty && b[ln[0]] == b[ln[1]] && b[ln[1]] == b[ln[2]] {
			return b[ln[0]]
		}
	}
	return Empty
}

func (b Board) isFull() bool {
	for _, c := range b {
		if c == Empty {
			return false
		}
	}
	return true
}

func (b Board) terminal() (bool, int) {
	w := b.winner()
	if w == AI {
		return true, +1
	}
	if w == Human {
		return true, -1
	}
	if b.isFull() {
		return true, 0
	}
	return false, 0
}

func minimax(b Board, maximizing bool) (int, int) {
	term, score := b.terminal()
	if term {
		return score, -1
	}

	bestMove := -1
	if maximizing {
		bestScore := -2
		for _, mv := range b.availableMoves() {
			nb := b
			nb[mv] = AI
			s, _ := minimax(nb, false)
			if s > bestScore {
				bestScore, bestMove = s, mv
			}
		}
		return bestScore, bestMove
	}
	// minimizing (human)
	bestScore := 2
	for _, mv := range b.availableMoves() {
		nb := b
		nb[mv] = Human
		s, _ := minimax(nb, true)
		if s < bestScore {
			bestScore, bestMove = s, mv
		}
	}
	return bestScore, bestMove
}

func BestAIMove(b Board) int {
	_, mv := minimax(b, true)
	return mv
}
