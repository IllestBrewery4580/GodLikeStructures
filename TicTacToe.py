import tkinter as tk
from tkinter import messagebox
import random
import subprocess

class GitManager:
    @staticmethod
    def git_init():
        subprocess.run(["git", "init"])

    @staticmethod
    def git_add(file_path):
        subprocess.run(["git", "add", file_path])

    @staticmethod
    def git_commit(message):
        subprocess.run(["git", "commit", "-m", message])

    @staticmethod
    def git_push():
        subprocess.run(["git", "push", "-u", "origin", "master"])

class TicTacToe:
    def __init__(self, root):
        self.root = root
        self.root.title("Tic-Tac-Toe")
        self.current_player = "X"
        self.board = [["" for _ in range(3)] for _ in range(3)]
        self.buttons = [[None for _ in range(3)] for _ in range(3)]
        self.mode = None
        self.create_welcome_screen()
    
    def create_welcome_screen(self):
        self.create_screen()
        tk.Label(self.root, text = "Choose Game Mode", font=('normal', 20)).pack(pady=20)
        tk.Button(self.root, text="Multiplayer", font=('normal', 20), command=self.start_vs_computer).pack(pady=10)
        tk.Button(self.root, text="Play against Computer", font=('normal', 20), command=self.start_vs_computer).pack(pady=10)

    def start_multiplayer(self):
        self.mode = "multiplayer"
        self.create_board()

    def start_vs_computer(self):
        self.mode = "vs_computer"
        self.create_board()
    
    def clear_screen(self):
        for widget in self.root.winfo_children():
            widget.destroy()

    def create_board(self):
        for row in range(3):
            for col in range(3):
                self.buttons[row][col] = tk.Button(
                    self.root, text="", font=('normal', 40), width=5, height=2,
                    command=lambda row=row, col=col: self.on_button_click(row, col)
                )
                self.buttons[row][col].grid(row=row, column=col)

    def on_button_click(self, row, col):
        if self.board[row][col] == "" and not self.check_winner():
            self.board[row][col] = self.current_player
            self.buttons[row][col].config(text=self.current_player)
            if self.check_winner():
                messagebox.showinfo("Tic-Tac-Toe", f"Player {self.current_player} wins!")
                self.reset_game()
            elif self.check_draw():
                messagebox.showinfo("Tic-Tac-Toe", "It's a draw!")
                self.reset_game()
            else:
                self.current_player = "0" if self.current_player == "X" else "X"
    
    def check_winner(self):
        #Check rows, columns, and diagonals for a win
        for i in range(3):
            if self.board[i][0] == self.board[i][1] == self.board[i][2] != "":
                return True
            if self.board[0][i] == self.board[1][i] == self.board[2][i] != "":
                return True
        if self.board[0][0] == self.board[1][1] == self.board[2][2] != "":
            return True
        if self.board[0][2] == self.board[1][1] == self.board[2][0] != "":
            return True
        return False
    
    def check_draw(self):
        for row in self.board:
            for cell in row:
                if cell == "":
                    return False
        return True
    
    def reset_game(self):
        self.current_player = "X"
        self.board = [["" for _ in range(3)] for _ in range(3)]
        for row in range(3):
            for col in range(3):
                self.buttons[row][col].config(text="")
    
if __name__ == "__main__":
    # Git initialization
    GitManager.git_init()

    root = tk.Tk()
    game = TicTacToe(root)
    root.mainloop()
