package programmers;

class Solution_타겟넘버 {
    static int answer;
    
    public int solution(int[] numbers, int target) {
        answer = 0;
        dfs(0, 0, 0, numbers.length, numbers, target);
        return answer;
    }
    
    
    void dfs(int cnt, int start, int res, int N, int[] numbers, int target){
        if (cnt==N){
            if (res==target){
                answer++;
            }
            return;
        }
        
        for (int i=start; i<N;i++){
            dfs(cnt+1, i+1, res+numbers[i], N, numbers, target);
            dfs(cnt+1, i+1, res-numbers[i], N, numbers, target);
        }
    }
}


