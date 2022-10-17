import sys

sys.stdin = open("input.txt", "r")
input = sys.stdin.readline
T = int(input())

def dfs(month, payment):
    global result
    if month >= 12:
        result = min(result, payment)   # 앞 결과보다 작으면 대체
        return
    else:
        dfs(month+1, payment+payments[0]*schedule[month])    # 1일 이용권
        dfs(month+1, payment+payments[1])                    # 1달 이용권
        dfs(month+3, payment+payments[2])                     # 3달 이용권

for t in range(1, T+1):
    payments = list(map(int, input().split()))    # 요금, 순서대로 1일, 1달, 3달, 1년 이용권
    schedule = list(map(int, input().split()))    # 12개월동안의 이용계획
    result = payments[3]    # 12달 이용권값이 최대값

    dfs(0, 0)
    print(f"#{t} {result}")