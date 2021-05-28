package programmers;
import java.util.*;

class Solution_단어변환 {
    public int solution(String begin, String target, String[] words) {
        int answer = 0;
                
        int[] visited = new int[words.length];
        
        // 초기 큐 설정
        Queue<Integer> q = new LinkedList<>();
        for (int i=0;i<words.length;i++){
            if (isOne(words[i], begin)){
                visited[i] = 1;
                q.add(i);
            }
        }
        
    
        while (!q.isEmpty()){
            int size = q.size();
            for (int i=0;i<size;i++){
                int idx = q.poll();
                if (words[idx].equals(target)){
                    return visited[idx];
                }
                for (int j=0;j<words.length;j++){
                    if (visited[j]==0 && isOne(words[idx], words[j])){
                        
                        visited[j] = visited[idx] + 1;
                        q.add(j);
                    }
                }
            }
            
        }
        return 0; 
                
    }
               
               
    static boolean isOne(String a, String b){   // 두 문자열 다른 문자 하나만 있는지 확인 
        int cnt = 0;
        for (int i=0;i<a.length();i++){
            if (a.charAt(i)!=b.charAt(i)){
                cnt++;
                if (cnt>1) return false;
            }
        }
        if (cnt==1) return true;
        else return false;
    }
}