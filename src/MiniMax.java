
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Surya Wono
 */
public class MiniMax {

    public static HashMap<String, Double> stateValue = new HashMap();

    public MiniMax() {
    }

    public int[] max(int[] state) {
        ArrayList<int[]> stateChance = this.generateStateChance(state);
        Iterator it = stateChance.iterator();
        double[] selectedMinMax = new double[]{0.0, 0.0};
        int[] selectedState = new int[state.length];
        while (it.hasNext()) {
            int[] currentState = (int[]) it.next();
            it.remove();
            double[] currentMinMax = this.min(currentState);
//            System.out.println(String.format("%s : %f - %f", this.getString(currentState),currentMinMax[0],currentMinMax[1]));
            if (currentMinMax[0] > selectedMinMax[0] || (currentMinMax[0] == selectedMinMax[0] && currentMinMax[1] >= selectedMinMax[1])) {
                selectedMinMax = currentMinMax;
                selectedState = currentState;
            }
        }
//        System.out.println(this.getString(selectedState));
        return selectedState;
    }

    /*
     * @param state
     * @return 2 nilai dalam array, untuk index 0 adalah nilai minimun dan 1 adalah nilai max
     */
    public double[] terminal(int[] state) {
        double[] result = new double[2];
        result[0] = 1.1;
        result[1] = -0.1;
        ArrayList<int[]> stateChance = this.generateStateChance(state);
        Iterator it = stateChance.iterator();
        while (it.hasNext()) {
            int[] currentState = (int[]) it.next();
            it.remove();
            double currentValue = this.calculateStateValue(currentState);
            if (currentValue <= result[0]) {
                result[0] = currentValue;
            }
            if (currentValue >= result[1]) {
                result[1] = currentValue;
            }
        }
        if (result[0]>1){
            result[0]=0;
        }
        return result;
    }

    public double[] min(int[] state) {
        double[] result = new double[]{1.1, 1.1};
        ArrayList<int[]> stateChance = this.generateStateChance(state);
        Iterator it = stateChance.iterator();
        while (it.hasNext()) {
            int[] currentState = (int[]) it.next();
            it.remove();
            double[] minMaxValue = this.terminal(currentState);
            if (minMaxValue[1]<result[1] || (minMaxValue[1]==result[1] && minMaxValue[0]<result[0])) {
                result = minMaxValue;
            }
        }
        if (result[0]>1){
            result[0]=0;
        }
        return result;
    }

    public ArrayList<int[]> generateStateChance(int[] state) {
        ArrayList<int[]> stateChance = new ArrayList();
        for (int i = 0; i < state.length; i++) {
            int n = state[i];
            while (--n >= 0) {
                int[] copy = new int[state.length];
                System.arraycopy(state, 0, copy, 0, state.length);
                copy[i] = n;
                stateChance.add(copy);
            }
        }

        return stateChance;
    }

    public int[] getDraw(int[] state) {
        int[] draw = new int[2];
        int[] toState=this.max(state);
        for (int i = 0; i < state.length; i++) {
            if (state[i] != toState[i]) {
                draw[0] = i;
                draw[1] = state[i] - toState[i];
            }
        }
        return draw;
    }
    
    public double calculateStateValue(int[] state){
        double result;
        int[] status=this.getStatusState(state);
        if (status[1]==0){
            result=1;
        }else if (status[0]==1){
            result=0.0;
        }else if (status[0]==2){
            if (status[0]==status[1]){
                result=1.0;
            }else if (status[1]%2==0){
                result=0.975;
            }else{
                result=0.025;
            }
        }else if (status[0]%2==0){
            if (status[0]==status[1]){
                result=1.0;
            }else if (status[0]-status[3]==1){
                result=0.0;
            }else if (status[2]>0){
                if (status[1]%2==0){
                    result=0.875;
                }else{
                    result=0.85;
                }
            }else{
                if (status[1]%2==0){
                    result=0.825;
                }else{
                    result=0.8;
                }
            }
        }else{
            if (status[0]==status[1]){
                result=0.0;
            }else if (status[1]%2==0){
                result=0.025;
            }else{
                result=0.775;
            }
        }
        return result;
    }
    
    //index 0 untuk sisa tumpukan,index 1 untuk total batang,index 2 untuk total tumpukan genap,index 3 untuk total tumpukan dengan sisa batang 1 
    public int[] getStatusState(int[] state){
        int[] result=new int[4];
        for (int n:state){
            if (n>0){
                result[0]++;
            }
            if (n!=0 && n%2==0){
                result[2]++;
            }
            if (n==1){
                result[3]++;
            }
            result[1]+=n;
        }
        return result;
    }
//    perhitungan lama, semua kemungkinan dicari
//    ======================================================================
//    public double calculateStateValue(int[] state) {
//        double result;
//        if (this.alreadyWin(state)){
//            result=1;
//        }else if (!stateValue.containsKey(this.getString(state))) {
//            result = this.minValue(state);
//        } else {
//            result = stateValue.get(this.getString(state));
//        }
//        stateValue.put(this.getString(state), result);
//        return result;
//    }
//
//    public double maxValue(int[] state) {
//        double result;
//        if (stateValue.containsKey(this.getString(state))) {
//            result = stateValue.get(this.getString(state));
//        } else {
//            if (this.checkWinState(state)) {
//                result = 1.0;
//            } else {
//                ArrayList<int[]> stateChance = this.generateStateChance(state);
//                Iterator it = stateChance.iterator();
//                int totalState = 0;
//                double winState = 0;
//                while (it.hasNext()) {
//                    int[] currentState = (int[]) it.next();
//                    it.remove();
//                    winState += this.minValue(currentState);
//                    totalState++;
//                }
//                result = winState / totalState;
//            }
//            stateValue.put(this.getString(state), result);
//        }
//        return result;
//    }
//
//    public double minValue(int[] state) {
//        double result = 0.0;
//        if (this.checkWinState(state)) {
//        } else {
//            ArrayList<int[]> stateChance = this.generateStateChance(state);
//            Iterator it = stateChance.iterator();
//            int totalState = 0;
//            double winState = 0;
//            while (it.hasNext()) {
//                int[] currentState = (int[]) it.next();
//                it.remove();
//                winState += this.maxValue(currentState);
//                totalState++;
//            }
//            result = winState / totalState;
//        }
//        return result;
//    }
//    
//    public boolean checkWinState(int[] state) {
//        boolean result = true;
//        int sisaTumpukan = 1;
//        for (int t : state) {
//            if (t > 0) {
//                if (--sisaTumpukan < 0) {
//                    result = false;
//                    break;
//                }
//            }
//        }
//        return result;
//    }
//
//    public boolean alreadyWin(int[] state) {
//        int total = 0;
//        for (int t : state) {
//            total += t;
//        }
//        if (total == 0) {
//            return true;
//        }
//        return false;
//    }
//    ======================================================================
    public String getString(int[] state) {
        String result = "";
        for (int n : state) {
            result += n + "";
        }
        return result;
    }
}
