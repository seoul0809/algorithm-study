package heeheejj.week03;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

// 괄호
public class BOJ_9012 {
    static String isRightBrk(char[] arr){
        Stack<Character> stack = new Stack<>();
        for(char brk: arr){	// 문자 배열의 원소를 하나씩 확인
            if(brk == '('){	// 괄호가 열리면 Stack에 쌓는다.
                stack.push('(');
            }else if(brk == ')'){	// 괄호가 닫히면
                if(!stack.isEmpty()){	// 괄호가 닫혔고, 스택이 비어있지 않다면 스택에서 하나 꺼낸다.
                    stack.pop();
                }else{
                    return "NO";	// 괄호가 닫혔는데 스택이 비어있다면 VPS(Valid Ps, 올바른 괄호 문자열)가 아니다.
                }
            }
        }

        // 문자 배열을 모두 확인하고 나서
        if(stack.empty()){	 // 스택이 비어있다면 VPS가 맞다.
            return "YES";
        }else{		// 스택이 비어있지 않다면 VPS가 아니다.
            return "NO";
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(in.readLine());
        for(int t = 0; t < T; t++){
            char[] inputs = in.readLine().toCharArray();
            System.out.println(isRightBrk(inputs));
        }
    }
}
