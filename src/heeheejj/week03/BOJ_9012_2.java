package heeheejj.boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
/*
    BOJ_9012.java 스택을 쓰지 않은 풀이 - 시간은 비슷하게 나온다.

    문제 이름: 괄호
    알고리즘 분류: 자료구조, 문자열, 스택
    시간: 140ms

    VPS = Valid Parenthesis String = 올바른 괄호 문자열
    괄호 문자열을 순서대로 확인한다.
    int형 변수 cnt는 짝을 찾고있는 열린괄호의 개수이다.
    열린 괄호를 만나면 cnt를 -1하고, 닫힌 괄호를 만나면 cnt를 +1한다.
    올바른 괄호 문자열이 아닐 경우는 두 가지이다.
        1. 닫힌 괄호를 만났는데 cnt가 0인 경우
        2. 문자열을 끝까지 확인하고 나서 cnt가 0보다 큰 경우
        이 두 가지 경우, 짝이 없는 괄호가 존재하여 VPS가 아니다.
     */

public class BOJ_9012_2 {
    static String isRightBrk(char[] arr){
        int cnt = 0;
        for(char brk: arr){     // 문자 배열의 원소를 하나씩 확인
            if(brk == '('){     // 괄호가 열리면 개수 +1.
                cnt += 1;
            }else if(brk == ')'){   // 괄호가 닫히면
                if(cnt != 0){   // 괄호가 닫혔고, cnt가 0이 아니라면 -1
                    cnt -= 1;
                }else{
                    return "NO";    // 괄호가 닫혔는데 cnt가 0이라면 VPS(Valid Ps, 올바른 괄호 문자열)가 아니다.
                }
            }
        }

        // 문자 배열을 모두 확인하고 나서
        if(cnt <= 0){  // cnt가 0이하면 VPS가 맞다.
            return "YES";
        }else{              // cnt가 0보다 크면 VPS가 아니다.
            return "NO";
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("./input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(in.readLine());
        for(int t = 0; t < T; t++){
            char[] inputs = in.readLine().toCharArray();
            System.out.println(isRightBrk(inputs));
        }
    }
}
