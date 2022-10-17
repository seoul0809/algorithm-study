# �κ� û�ұ�

# �ð�: 112ms
# ����: ����

import sys

def clean1(r, c, d):
  global map, dx, dy, cnt
  map[r][c] = -1  # 1��: ���� ��ġ�� û���Ѵ�.
  cnt += 1
  clean2(r, c, d)

def clean2(r, c, d):
  _d = d
  for x in range(4):
    _d = (_d - 1) if ((_d - 1) >= 0) else 3  # ���׿�����..
    nx, ny = r+dx[_d], c+dy[_d]
    if map[nx][ny] == 0:  # 2-1
      clean1(nx, ny, _d)
      return
    # else:  # 2-2 -> _d�������� ȸ���ϱ� ���ؼ� ���� �ݺ�
  # for���� �� ���Ե� ���, 2-3 ����
  _d = (d + 2) % 4  # �� ĭ �����ϱ� ���� ����idx
  nx, ny = r+dx[_d], c+dy[_d]
  temp = map[nx][ny]
  if temp != 1:  # 2-3 (���� ������ ���� �ƴ� ��� ����)
    clean2(nx, ny, d)
    return
  else:  # 2-4 (���� ������ ���� ��� �۵� ����)
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