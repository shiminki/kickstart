import random

def main():
    f = open("input.txt", "w")
    N = int(1e5)

    adj = [[False for i in range(N)] for j in range(N)]