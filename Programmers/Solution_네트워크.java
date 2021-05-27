package programmers;
class Solution_네트워크 {
    
    static boolean[] visited;
    
    public int solution(int n, int[][] computers) {
        
        int answer = 0;
        visited = new boolean[n];
        for (int i=0;i<n;i++){
            if (!visited[i]) {
                dfs(i, computers);
                answer++;
            }
        }
        return answer;
    }
    
    void dfs(int x, int[][] computers){
        visited[x] = true;
        for (int i=0;i<computers[x].length;i++){
            if (!visited[i] && computers[x][i]==1){
                dfs(i, computers);
            }    
        }
    }
}