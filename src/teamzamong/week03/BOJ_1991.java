package week03;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * BOJ 1991: 트리 순회
 * > https://www.acmicpc.net/problem/2263
 * > 주어진 트리에 대해 전위, 중위, 후위 순회 순서 반환
 * 
 * [조건]
 * - 노드 개수: 1 <= N <= 26
 * 
 * [풀이 방법]
 * - 트리를 2차원 배열로 구현
 *   => tree[현재노드][자식노드] 형식. 자식노드가 0이면 왼쪽 자식, 1이면 오른쪽 자식
 * - 전위, 중위, 후위 순서에 맞춰 재귀로 구현
 *   - 기본 조건: 자식 노드가 존재하지 않을 시, return
 * 	 - 유지 조건
 * 	 	=> 전위: 루트, 왼쪽 자식, 오른쪽 자식
 *   	=> 중위: 왼쪽 자식, 루트, 오른쪽 자식
 *   	=> 후위: 왼쪽 자식, 오른쪽 자식, 루트
 * - 결과값을 StringBuilder에 append 후 마지막에 한 번에 출력
 */

public class BOJ_1991 {
	
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws Exception {

		int N = Integer.parseInt(br.readLine().trim());
		int[][] tree = new int[N][2];

		createTree(tree);

		StringBuilder sb = new StringBuilder();

		preOrder(tree, 0, sb);
		sb.append("\n");

		inOrder(tree, 0, sb);
		sb.append("\n");

		postOrder(tree, 0, sb);

		System.out.print(sb);
	}

	// 전위 순회
	private static void preOrder(int[][] tree, int currNode, StringBuilder sb) {
		if (currNode == -1)
			return;

		sb.append((char) ('A' + currNode));
		preOrder(tree, tree[currNode][0], sb);
		preOrder(tree, tree[currNode][1], sb);
	}

	// 중위 순회
	private static void inOrder(int[][] tree, int currNode, StringBuilder sb) {
		if (currNode == -1)
			return;

		inOrder(tree, tree[currNode][0], sb);
		sb.append((char) ('A' + currNode));
		inOrder(tree, tree[currNode][1], sb);
	}

	// 후위 순회
	private static void postOrder(int[][] tree, int currNode, StringBuilder sb) {
		if (currNode == -1)
			return;

		postOrder(tree, tree[currNode][0], sb);
		postOrder(tree, tree[currNode][1], sb);
		sb.append((char) ('A' + currNode));
	}

	// tree를 2차원 배열에 저장하는 함수
	private static void createTree(int[][] tree) throws Exception {
		StringTokenizer st;

		for (int i = 0, size = tree.length; i < size; i++) {
			st = new StringTokenizer(br.readLine());
			
			// 노드의 이름이 A부터 차례대로 대문자로 매겨져 들어옴
			// 'A' 노드 index = 0부터 시작해 차례대로 tree 자식 정보 추가
			int currNode = st.nextToken().charAt(0) - 'A';

			for (int j = 0; j < 2; j++) {
				String child = st.nextToken();
				if (child.equals(".")) {
					tree[currNode][j] = -1; // 자식 노드가 없는 경우, -1
				} else {
					tree[currNode][j] = child.charAt(0) - 'A'; // 자식 노드 있는 경우, index 추가
				}
			}
		}
	}

}
