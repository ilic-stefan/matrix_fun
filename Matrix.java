/**
 * Class: matrix_class
 * Object representing an m-by-n matrix
 *   as 2D arrays in row-major order
 * Static methods to add and multiply matrices.
 */
public class Matrix {
    private int[][] values;
    private int row_count;
    private int col_count;

    // Constructor
    /**
     * Constructor
     * Args:
     *   @param vals - a 2-D array with int entries in row major order
     * Pre:
     *   vals cannot be a null reference
     *   all the subarrays in vals must have the same length
     *   i.e., all the rows must have same length 
     * Post:
     *   new matrix object is created
     */
    public Matrix(int[][] vals) {
        values = vals;
        row_count = vals.length;
        col_count = vals[0].length;
    }
    
    // toString
    /**
     * toString
     * @returns str - a string with the following properties
     * first line is "Matrix = "
     * then on subsequent new lines is a tab then the values of each row 
     *  listed horizontally (in order of column, each separated by tab)
     */
    public String toString() {
        String s = "Matrix = \n";
        for (int r = 0; r < row_count; r++) {
            s = s + "\t";
            for (int c = 0; c < col_count; c++) {
                s = s + values[r][c] + "\t";
            }
            s = s + "\n";
        }
        return s;
    }
    
    // getters
    public int getRowNum() {
        return row_count;
    }
    public int getColNum() {
        return col_count;
    }
    public int[][] getVals() {
        return values;
    }
    
    // add (static)
    /**
     * add
     * Arguments:
     *  @param mat1 - a matrix object
     *  @param mat2 - a matrix object
     * Returns:
     *  @returns sum - a matrix object which represents componentwise mat1+mat2
     *
     * Preconditions:
     *  mat1 and mat2 have the same dimensions (if not, returns null)
     */
    public static Matrix add (Matrix mat1, Matrix mat2) {

        int row_num = mat1.getRowNum();
        int col_num = mat1.getColNum();

        // improper dimesions check
        if ((row_num != mat2.getRowNum()) || (col_num != mat2.getColNum())) {
            System.out.println("Cannot add matricies of different dimensions.");
            return null;
        }
        else {
            int[][] sum = new int[row_num][col_num];
            
            for (int r = 0; r < row_num; r++) {
                for (int c = 0; c < col_num; c++) {
                    sum[r][c] = mat1.getVals()[r][c] + mat2.getVals()[r][c];
                }
            }

            return new Matrix(sum);
        }
    }
    // multiply (static)
    /**
     * mult
     * Arguments:
     *  @param mat1 - a matrix object
     *  @param mat2 - a matrix object
     * Returns:
     *  @returns result - a matrix object representing mat1 * mat2
     * Preconditions:
     *  The number of columns of mat1 equals the number of rows in mat2
     *  otherwise, function returns null
     * Postconditions:
     *  mat1 * mat2 where * is matrix multiplication
     *  (in that order, since mat. mult. is not commutative)
     */
    public static Matrix mult (Matrix mat1, Matrix mat2) {
        int mat1_row = mat1.getRowNum();
        int mat1_col = mat1.getColNum();

        int mat2_row = mat2.getRowNum();
        int mat2_col = mat2.getColNum();

        if (mat1_col != mat2_row) {
            System.out.println("Dimension Error");
            return null;
        }
        else {
            int[][] prod = new int[mat1_row][mat2_col];
            
            for (int m1_r = 0; m1_r < mat1_row; m1_r++) {
                for (int m2_c = 0; m2_c < mat2_col; m2_c++) {
                    prod[m1_r][m2_c] = 0;
                    for (int m1_c = 0; m1_c < mat1_col; m1_c++) {
                        prod[m1_r][m2_c]= prod[m1_r][m2_c] +
                            mat1.getVals()[m1_r][m1_c] *
                            mat2.getVals()[m1_c][m2_c];
                    }
                }
            }

            return new Matrix(prod);
        }
    }

    /** 
     * main function for testing purposes.
     */
    public static void main (String[] args) {
        System.out.println("Hello World!");

        // mat1 and mat2 are 2x3 matrices
        int[][] m1 = {{1, 2, 3}, {4, 5, 6}, {10, 10, 10}};
        Matrix mat1 = new Matrix(m1);

        int[][] m2 = {{2, 3}, {4, 5}, {6, 7}};
        Matrix mat2 = new Matrix(m2);

        System.out.println(mat1.toString());
        System.out.println(mat2.toString());

        // testing matrix multiply
        Matrix prod12 = mult(mat1, mat2);
        System.out.println(prod12);
        
        // testing add
        //Matrix sum12 = add(mat1, mat2);
        //System.out.println(sum12);
    }
}
