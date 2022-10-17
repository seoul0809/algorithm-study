# ���
# dynamic programming

# �� �� �ε������� �����Ѵ�.
# n��° �� ������ �ִ� ����(dp)�� �� ���� ��� �� �� ū ���̴�. 
# 1. n��° ���� ���� ������ ���: n+1��°�� ���������� �ִ� ����(dp)
# 2. n���� ���� ���� ���� ���: n��°���� ���� + (n+(n�������� ����� �Ⱓ))��° �� �������� �� �ִ� ����(dp)

import sys
sys.stdin = open("input.txt","r")
input = sys.stdin.readline

N = int(input())
schedule = [list(map(int, input().split())) for _ in range(N)]

dp = [0 for _ in range(N+1)]
for i in range(N-1, -1, -1):
  if i + schedule[i][0] > N: # (i+1)�� + �ش� ���� Ti ������ �� 8���� �Ѿ��
    dp[i] = dp[i+1]
  else:
    dp[i] = max(schedule[i][1]+dp[i+schedule[i][0]], dp[i+1])

print(dp[0])