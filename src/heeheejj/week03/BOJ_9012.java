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
        for(char brk: arr){
            if(brk == '('){
                stack.push('(');
            }else if(brk == ')'){
                if(!stack.isEmpty()){
                    stack.pop();
                }else{
                    return "NO";
                }
            }
        }
        if(stack.empty()){
            return "YES";
        }else{
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
