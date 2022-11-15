import sys

def main():
    T = int(input())
    
    for t in range(1, T + 1):
        L, N = [int(x) for x in input().split(" ")]

        cnt = 0

        pos, dir = input().split(" ")
        pos = int(pos)

        cnt += pos // L
        pos %= L

        for i in range(N - 1):
            dist, new_dir = input().split(" ")
            dist = int(dist)
            if dir == new_dir:
                pos += dist
                cnt += pos // L
                pos %= L
            else:
                pos -= dist
                if pos < 0:
                    dir = new_dir
                    pos *= -1
                    cnt += pos // L
                pos %= L
                    

        print(f"Case #{t}: {cnt}")
	
    sys.stdout.flush()

if __name__ == '__main__':
	main()