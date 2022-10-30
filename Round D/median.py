import sys

def main():
    T = int(input())
    
    for t in range(1, T + 1):
        N, M = [int(x) for x in input().split()]
        A = [int(x) for x in input().split()]
        A.sort()

        score = 0

        for i in range(M - 1):
            score += A.pop()
        
        median = 0

        if len(A) % 2 == 1:
            median = A[len(A) // 2]
        else:
            median = (A[len(A) // 2] + A[len(A) // 2 - 1]) / 2
        
        score += median

        print(f"Case #{t}: {score}")

	
    sys.stdout.flush()

if __name__ == '__main__':
	main()