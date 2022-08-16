# 로봇 청소기

# 시간: 112ms
# 유형: 구현

import sys

def clean1(r, c, d):
  global map, dx, dy, cnt
  map[r][c] = -1  # 1번: 현재 위치를 청소한다.
  cnt += 1
  clean2(r, c, d)

def clean2(r, c, d):
  _d = d
  for x in range(4):
    _d = (_d - 1) if ((_d - 1) >= 0) else 3  # 삼항연산자..
    nx, ny = r+dx[_d], c+dy[_d]
    if map[nx][ny] == 0:  # 2-1
      clean1(nx, ny, _d)
      return
    # else:  # 2-2 -> _d방향으로 회전하기 위해서 다음 반복
  # for문을 다 돌게된 경우, 2-3 진행
  _d = (d + 2) % 4  # 한 칸 후진하기 위한 방향idx
  nx, ny = r+dx[_d], c+dy[_d]
  temp = map[nx][ny]
  if temp != 1:  # 2-3 (뒤쪽 방향이 벽이 아닌 경우 후진)
    clean2(nx, ny, d)
    return
  else:  # 2-4 (뒤쪽 방향이 벽인 경우 작동 멈춤)
    print(cnt)
    return

sys.stdin = open("input.txt","r")
input = sys.stdin.readline

N, M = map(int, input().split())
r, c, d =  map(int, input().split())
map = [list(map(int, input().split())) for _ in range(N)]
cnt = 0;

dx = [-1, 0, 1, 0]
dy = [0, 1, 0, -1]

clean1(r, c, d)