package cn.iocoder.springboot.labs.lab09.search;

/**
 * 使用二分查找，计算平方根。
 */
public class SquareTest {

    public static void main(String[] args) {
        double result = sqrt(5, 0.00001D);
        System.out.println(result);
    }

    // precision 表示精度
    private static double sqrt(int number, double precision) {
        int rounds = 0; // 用于记录轮次，调试用途
        double low = 0;
        double high = number;
        while (low <= high) {
            double middle = low + ((high - low) / 2);
            double result = middle * middle;
            System.out.println(String.format("第 %d 轮，结果：%f", ++rounds, middle));

            double diff = number - result;
            if (0 <= diff && diff <= precision) {
                return middle;
            }
            if (result < number) {
                low = middle;
            } else if (result > number) {
                high = middle;
            } else {
                throw new IllegalStateException("不可能出现");
            }
        }
        throw new IllegalStateException("必然有结果，还能求不出平方根");
    }

}
