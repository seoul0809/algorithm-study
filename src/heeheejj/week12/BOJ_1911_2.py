# 흙길 보수하기
# 7736ms
import sys
sys.stdin = open("input.txt", "r")
input = sys.stdin.readline
N, L = map(int, input().split())
arr = [tuple(map(int, input().split())) for _ in range(N)]
arr.sort(key=lambda x:x[1])
size = len(arr)
lastIdx = arr[size-1][1]
i = 0
j = 0
result = 0
while i < lastIdx:
    if i < arr[j][0]:
        i += 1
        continue
    elif i < arr[j][1]:
        result += 1
        for k in range(L):
            i += 1
            if j < size-1:  # 마지막 웅덩이가 아닐 때 check
                if i == arr[j+1][0]:    # 다음 웅덩이의 시작일 때 웅덩이인덱스 옮겨줌
                    j += 1
    else:
        i += 1
        j += 1
        continue
print(result)