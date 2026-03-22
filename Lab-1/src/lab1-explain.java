import java.util.ArrayList;
import java.util.List;
public class Main {

    public static List<Integer> fonctionAleatoire(int n) {
        List<Integer> list = new ArrayList<>();
        
        if (n < 2) {
            return list;
        }
        
        boolean[] isGood = new boolean[n + 1];
        for (int i = 2; i <= n; i++) {
            isGood[i] = true;
        }
        
        for (int i = 2; i * i <= n; i++) {
            if (isGood[i]) {
                for (int j = i * i; j <= n; j += i) {
                    isGood[j] = false;
                }
            }
        }
        
        
        for (int i = 2; i <= n; i++) {
            if (isGood[i]) {
                list.add(i);
            }
        }
        
        return list;
    }
    
    public static void main(String[] args) {
        int limit = 10;
        List<Integer> list = fonctionAleatoire(limit);
        System.out.println("Le résultat est " + limit + " : " + list);
    }
}


