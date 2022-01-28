package HomeWorkApp3;

public class HomeWorkApp3 {
    public static void main(String[] args){
        oneArr();
        twoArr();
        threeAgg();
        fourAgg(9);
        fiveAgg(5, 3);


    }

    public static void oneArr() {
        int[] oneArr = new int[] {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};
        for (int i = 0; i < oneArr.length; i++) {
            if (oneArr[i] == 0){
                oneArr[i] = 1;
            }else{
                oneArr[i] = 0;
            }
            int j = oneArr[i];
            System.out.print(j + " ");
        }
        System.out.println(" ");
    }
    public static void twoArr() {
        int[] twoArr = new int[100];
        for (int i = 0; i < twoArr.length; i++) {
            twoArr[i] = i ;
            System.out.print(i + 1 + " ");
        }
        System.out.println(" ");
    }
    public static void threeAgg() {
        int [] threeAgg = new  int[] {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 };
        for (int i = 0; i < threeAgg.length; i++){
            if (threeAgg[i] > 6){
            }else{
                threeAgg[i] *=2;
                int j = threeAgg[i];
                System.out.print(j + " ");
            }
        }
        System.out.println(" ");
    }
    public static void fourAgg(int len){
        int [][] fourAgg = new int[len][len];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (i == len -1 - j)
                {
                    fourAgg[i][j] =1;
                }
                System.out.print(fourAgg[i][j] + "\t");
            }
            System.out.println(" ");
        }
    }
     public static void fiveAgg(int len, int initialValue ){
         int[] fiveAgg = new int[len];
         for (int i = 0; i < fiveAgg.length; i++){
        System.out.print(initialValue + " ");
     }



}

}






