# 30
# 136ms, 118532kb
# 0을 포함하고, 각 자리수의 합이 3의 배수면 30의 배수

N = input()

if '0' not in N:
    print(-1)
else:
    sum = 0
    for i in N:
        sum += int(i)
    if sum % 3 == 0:
        str = list(N)
        str.sort(reverse=True)
        print("".join(str))
    else:
        print(-1)