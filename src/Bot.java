/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Surya Wono
 */
public class Bot  extends Player{
    public MiniMax mm;
    public String lastCommand;
    
    
    public Bot(KorekApi ka,String nama){
        super(ka,nama);
        this.mm=new MiniMax();
        this.lastCommand="";
    }

    @Override
    public boolean take(int m, int n) {
        this.ka.take(m,n);
        this.lastCommand=String.format("%s mengambil %s batang dari tumpukan ke-%s",this.nama,n,m+1);
        return true;
    }
    
    public void move(){
        int[] draw=mm.getDraw(this.ka.getState());
        take(draw[0],draw[1]);
    }

    public String getLastCommand() {
        return lastCommand;
    }
    
    
}
