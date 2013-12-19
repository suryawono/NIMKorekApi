
import java.util.Scanner;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Surya Wono
 */
public class RunBoard {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int m,n;
        System.out.println("Jumlah tumpukan : ");m=sc.nextInt();
        System.out.println("Banyak korek api per tumpukan : ");n=sc.nextInt();
        System.out.println("Mode (1:melawan AI/2:AI melawan AI) : ");int x=sc.nextInt();
        MainBoard papanPermainan=new MainBoard(x==1?MainBoard.PLAY:MainBoard.WATCH,m,n);
        papanPermainan.run();
        
//        MiniMax mm=new MiniMax();
//        System.out.println(mm.max(new int[]{2,2,2,2,0,0,0})[0]);
//        KorekApi ka=new KorekApi(4,5);
//        ka.take(4, 5);
    }
}
