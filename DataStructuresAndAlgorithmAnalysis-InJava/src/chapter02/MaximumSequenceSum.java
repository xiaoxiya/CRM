package chapter02;

/**
 * @author xiaoxiya
 * @date 2019/12/11 21:46
 * @describe 最大子序列求和，找出下面数组的最大子序列和
 */
public class MaximumSequenceSum {

    /**
     * 暴力破解法
     * t(n) = o(n ^3)
     */
     static int maxSqeuence0(int[] a) {
        int maxSum = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = i; j < a.length; j++) {
               int sum = 0;
                for (int k = i; k <= j; k++) {
                    sum += a[k];
                }
                if (sum > maxSum) {
                    maxSum = sum;
                }
            }
        }
        return maxSum;
    }


    /**
     * 暴力破解改良
     * t(n)=o(n ^2)
     */
    static int maxSqeuence1(int[] A) {
        int maxSum  = 0;
        int N = A.length;
        for (int i = 0; i < N; i++) {
            int thisSum = 0;
            for (int j = i; j <= N; j++) {
                thisSum += A[j];
                if (thisSum > maxSum) {
                    maxSum = thisSum;
                }
            }
        }
        return maxSum;
    }

    /**
     * 分治法
     * t(n) = o(n*logn)
     */
    static int maxSequence2(int[] A, int left, int right) {
       if (left == right) {
           if (A[left] > 0){
               return A[left];
           } else {
               return 0;
           }
       }

       int center = (left + right) / 2;
       //左边区域最大值
       int maxLeftSum = maxSequence2(A, left, center);
       //右边区域最大值
       int maxRightSum = maxSequence2(A, center + 1, right);

       //跨越边界的最大值情况
       int maxLeftBorderSum = 0, leftBorderSum = 0;
       for (int i = center; i >= left; i--) {
           leftBorderSum += A[i];
           if (maxLeftBorderSum < leftBorderSum) {
               maxLeftBorderSum = leftBorderSum;
           }
       }
        int maxRightBorderSum = 0, rightBorderSum = 0;
        for (int i = center + 1; i <= right; i++) {
            rightBorderSum += A[i];
            if (maxRightBorderSum < rightBorderSum) {
                maxRightBorderSum = rightBorderSum;
            }
        }
        int maxBorderSum = maxLeftBorderSum + maxRightBorderSum;

        return maxBorderSum > maxLeftSum ? (maxBorderSum > maxRightSum ? maxBorderSum : maxRightSum) : (maxLeftSum > maxLeftSum ? maxLeftSum: maxLeftSum);
    }

    /**
     * 线性法
     * t(n) = o(n)
     */
    static int maxSequence3(int[] A) {
        int maxSum = A[0], thisSum =0;
        for (int i = 0; i < A.length; i++) {
            thisSum += A[i];
            if (thisSum > maxSum) {
                maxSum = thisSum;
            } else if (thisSum < 0) {
                thisSum = 0;
            }
        }
        return maxSum;
    }

}
