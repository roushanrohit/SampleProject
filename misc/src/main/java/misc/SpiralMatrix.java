package misc;

public class SpiralMatrix {

    public static void main(String[] args) {

        int[][] matrix = {{1,2,3},{4,5,6},{7,8,9}};
        spiralOrder(matrix);
    }

    public static void spiralOrder(int[][] matrix) {

        int n = matrix.length;
        int m = matrix[0].length;
        int rs = 0, re = n - 1, cs = 0, ce = m - 1;
        while(rs <= re && cs <= ce){

            for(int i = cs; i <= ce; i++){
                System.out.print(matrix[rs][i]);
            }
            rs++;
            for(int i = rs; i <= re; i++){
                System.out.print(matrix[i][ce]);
            }
            ce--;
            if(rs <= re){
                for(int i = ce; i >= cs; i--){
                    System.out.print(matrix[re][i]);
                }
                re--;
            }
            if(cs <= ce){
                for(int i = re; i >= rs; i--){
                    System.out.print(matrix[i][cs]);
                }
                cs++;
            }
        }
    }
}
