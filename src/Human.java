/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Surya Wono
 */
public class Human  extends Player{
    
    public Human(KorekApi ka,String nama){
        super(ka,nama);
    }

    @Override
    public boolean take(int m, int n) {
        return this.ka.take(m, n);
    }
}
