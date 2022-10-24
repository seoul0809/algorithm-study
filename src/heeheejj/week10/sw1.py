# 전기차 충전소
import sys
from itertools import combinations
sys.stdin = open("input.txt", "r")

def isAvailableDist(x1, y1, x2, y2, d):
    dist = abs(x2 - x1) + abs(y2 - y1)
    if dist <= d:
        return dist
    else:
        return -1

input = sys.stdin.readline

T = int(input())

for t in range(1, T+1):
    N = int(input())
    result = 9999999
    houses = list()
    for _ in range(N):
        houses.append(list(map(int, input().split())))
    #print(houses)
    # 충전소 하나라고 가정
    flag = True
    for i in range(-15, 16):
        for j in range(-15, 16):
            distSum = 0
            flag = True # houses 중 하나라도 충전소-집 거리가 d 초과이면 False
            for x, y, d in houses:
                if i == x and j == y:   # 충전소 좌표와 house 좌표가 같으면
                    flag = False
                    break
                temp = isAvailableDist(i, j, x, y, d)
                if temp != -1:
                    distSum += temp
                    #print(f"충전소 위치 - i: {i}, j: {j} / 집 좌표 - x: {x}, y: {y} / temp: {temp}")
                else:
                    flag = False
                    break
            if flag:    # 모든 집 성공했을 때 최솟값 갱신
                result = min(result, distSum)
                #print(f"충전소 하나: 충전소 위치 - i: {i}, j: {j} / distSum: {distSum}")
            #else:
                #print(f"(실패) 충전소 하나: 충전소 위치 - i: {i}, j: {j} / distSum: {distSum}")

    # 충전소 하나로 안될 때
    if result == 9999999:
        init = list()
        for i in range(-15, 16):
            for j in range(-15, 16):
                init.append((i, j))
        combs = combinations(init, 2)
        for comb in combs:
            x1, y1 = comb[0][0], comb[0][1] # 첫번째 충전소 좌표
            x2, y2 = comb[1][0], comb[1][1] # 두번째 충전소 좌표
            distSum = 0
            flag = True
            for x, y, d in houses:
                if (x1 == x and y1 == y) or (x2 == x and y2 == y):   # 충전소 좌표와 house 좌표가 같으면
                    flag = False
                    break
                temp1 = isAvailableDist(x1, y1, x, y, d)  # 첫번째 충전소
                temp2 = isAvailableDist(x2, y2, x, y, d)  # 두번째 충전소
                temp = 0
                if temp1 != -1 and temp2 != -1: # (x, y)에 있는 집이 첫번째, 두번째 충전소 다 가능할 때
                    temp = min(temp1, temp2)    # temp1, temp2 중 작은값
                elif temp1 != -1:   # 첫번째 충전소만 가능할 때
                    temp = temp1
                elif temp2 != -1:   # 두번째 충전소만 가능할 때
                    temp = temp2
                else:  # 첫번쨰 충전소 두번째 충전소 둘다 불가능하면
                    flag = False
                    break
                distSum += temp

            if flag:    # 모든 집 성공했을 때 최솟값 갱신
                #print(f"충전소 두개: 충전소 위치 - x1: {x1}, y1: {y1}/ x2: {x2}, y2: {y2} / distSum: {distSum}")
                result = min(result, distSum)

    if result == 9999999:    # 충전소를 두개 짓는 경우도 안될 때
        result = -1

    print(f"#{t} {result}")