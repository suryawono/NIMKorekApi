
import java.util.Scanner;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Surya Wono
 */
public class MainBoard {
    public static final int WATCH=0;
    public static final int PLAY=1;
    public Player[] playerList;
    public KorekApi ka;
    public int currentMove;
    public int winner;
    
    public MainBoard(int mode,int m,int n){
        this.ka=new KorekApi(m,n);
        this.playerList=new Player[2];
        this.playerList[0]=new Bot(this.ka,"bot1");
        this.winner=-1;
        switch (mode){
            case WATCH:
                this.playerList[1]=new Bot(this.ka,"bot2");
                this.currentMove=0;
                break;
            case PLAY:
               this.playerList[1]=new Human(this.ka,"Anung");
               this.currentMove=1;
                break;
        }
        
    }
    
    public void run(){
        Scanner sc=new Scanner(System.in);
        do{
            System.out.println(this.ka);
            if (this.playerList[this.currentMove] instanceof Human){
                int m,n;
                do{
                System.out.println("Ambil batang dari tumpukan ke- : ");m=sc.nextInt();
                System.out.println("Sebanyak : ");n=sc.nextInt();
                } while (!this.playerList[this.currentMove].take(m-1, n));
            }else{
                System.out.println("Mohon bersabar,"+this.playerList[this.currentMove].getNama()+" sedang melakukan perhitungan....");
                ((Bot)this.playerList[this.currentMove]).move();
                System.out.println(((Bot)this.playerList[this.currentMove]).getLastCommand());
            }
//            System.out.println("Total batang : "+this.ka.sisaTotalBatang());
            this.checkWinner();
            this.changeCurrentMove();
        }while(this.winner==-1);
        System.out.println(this.ka);
        System.out.println("Pemanang adalah "+this.playerList[this.winner].getNama());
    }
    
    public void checkWinner(){
        if (this.ka.sisaTotalBatang()==0){
            this.winner=this.currentMove;
        }
    }
    
    public void changeCurrentMove(){
        if (this.currentMove==0){
            this.currentMove=1;
        }else{
            this.currentMove=0;
        }
    }
}
