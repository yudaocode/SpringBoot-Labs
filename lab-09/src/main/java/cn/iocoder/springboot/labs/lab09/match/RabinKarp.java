package cn.iocoder.springboot.labs.lab09.match;

/**
 * 参考 https://blog.csdn.net/jianke0503/article/details/79735928 文章
 *
 * 实现 Rabin Karp 字符串查找
 */
public class RabinKarp {

    // power 指数
    // base 计算超过范围，则返回 base
    public static int find(String src, String target, int power, int base) {
        // 计算长度
        int n = src.length();
        int m = target.length();

        // target 的 hashcode
        int targetCode = 0;
        int mPower = 1;
        for (int i = 0; i < m; i++) {
            targetCode = (targetCode * power + target.charAt(i)) % base;
            mPower = (mPower * power) % base;
        }

        int srcCode = 0;
        for (int i = 0; i < n; i++) {
            srcCode = (srcCode * power + src.charAt(i)) % base;
            if (i < m - 1) { // 不等于的原因是，i 是从 0 开始的。
                continue;
            }

            if (i >= m) { // 此时，需要减掉头的
                srcCode = srcCode - ((src.charAt(i - m) * mPower) % base);

                // 避免减成负数
                if (srcCode < 0) {
                    srcCode = srcCode + base;
                }
            }

            if (srcCode == targetCode) {
                if (src.substring(i - m + 1, i + 1).equals(target)) {
                    return i - m + 1; // + 1 的原因是，i 是从 0 开始，而 m 是从 1 开始。
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        String src = "2359023141526739921";
        String target = "5902";
        System.out.println(find(src, target, 31, 100));
    }

}
