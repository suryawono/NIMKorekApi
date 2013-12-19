/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Surya Wono
 */
public abstract class Player {
    
    public KorekApi ka;
    public String nama;
    public Player(KorekApi ka,String nama){
        this.ka=ka;
        this.nama=nama;
    }
    
    public abstract boolean take(int m,int n);

    public String getNama() {
        return nama;
    }
}
