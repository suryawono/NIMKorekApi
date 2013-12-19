/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Surya Wono
 */
public class KorekApi {
    
    public int[] state;
    public int totalBatang;
    public int sisaTumpukan;
    
    public KorekApi(int m,int n){
        this.state=new int[m];
        this.totalBatang=m*n;
        this.sisaTumpukan=m;
        for (int i=0;i<this.state.length;i++){
            this.state[i]=n;
        }
//        this.state=new int[]{2,1,1};
    }
    
    public int[] getState(){
        return this.state;
    }
    
    public boolean take(int m,int n){
        boolean result=false;
        try{
            int temp=this.state[m];
            this.state[m]-=n;
            this.totalBatang-=n;
            if (this.state[m]<0){
                result=false;
                this.state[m]=temp;
                this.totalBatang+=n;
            }else{
                result=true;
            }
            if (this.state[m]==0){
                this.sisaTumpukan--;
            }
        }catch(Exception e){
            
        }
        return result;
    }
    
    public int sisaTotalBatang(){
        int total=0;
        for (int n:state){
            total+=n;
        }
        return total;
    }
    
    @Override
    public String toString(){
        String result="";
        int i=1;
        for (int n:state){
            result+=String.format("Tumpukan ke-%s tersisa %s batang korek api\n",i++,n);
        }
        return result;
    }
}
