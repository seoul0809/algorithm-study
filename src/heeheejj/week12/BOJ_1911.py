# 흙길 보수하기
# 132ms

import sys, math
sys.stdin = open("input.txt", "r")
input = sys.stdin.readline
N, L = map(int, input().split())
arr = [tuple(map(int, input().split())) for _ in range(N)]
arr.sort(key=lambda x:x[1]) # 웅덩이의 끝인덱스를 기준으로 정렬

maxCoveredIdx = 0  # 널판지로 덮여진 마지막 인덱스를 저장한다.
result = 0  # 웅덩이의 개수
for s, e in arr:
    if s <= maxCoveredIdx:   # 웅덩이의 시작 지점이 이미 널판지로 덮여있다면
        s = maxCoveredIdx + 1
        if e <= s:  # 웅덩이의 끝까지 이미 널판지로 덮여있는 경우가 있을 수 있음
            continue

    cnt = math.ceil((e-s)/L)    # 현재 웅덩이에 필요한 널판지 개수 계산 (올림 함수 이용)
    result += cnt
    maxCoveredIdx = s + L * cnt - 1
print(result)