# 행렬
# 124ms
import sys

def convert(a, n, m):
  for i in range(n, n+3):
    for j in range(m, m+3):
      a[i][j] = 1 - a[i][j]

def isSame():
  # 행렬 B와 같은지 확인
  for i in range(N):
    for j in range(M):
      if a[i][j] != b[i][j]:
        return False
  return True

sys.stdin = open("input.txt", "r")
input = sys.stdin.readline
N, M = map(int, input().split())
a = [list(map(int, input().rstrip())) for _ in range(N)]
b = [list(map(int, input().rstrip())) for _ in range(N)]
result = 0

for i in range(N-2):
  for j in range(M-2):
    if a[i][j] != b[i][j]:
      convert(a, i, j)
      result += 1

if isSame():
  print(result)
else:
  print(-1)