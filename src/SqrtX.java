public class SqrtX {
    public static void main(String[] args) {
        System.out.println(mySqrt(21316));
        System.out.println(mySqrt(4));
        System.out.println(mySqrt(8));
        System.out.println(mySqrt(1024));
        System.out.println(mySqrt(8192));
    }

    /**
     * Given a non-negative integer x, return the square root of x rounded down to the nearest integer.
     * The returned integer should be non-negative as well.
     * You must not use any built-in exponent function or operator.
     * For example, do not use pow(x, 0.5) in c++ or x ** 0.5 in python.
     */


    public static int mySqrt(int x) {

        String input = String.valueOf(x);
        int[] pairs;
        if (input.length()%2 == 0){
            pairs = new int[input.length()/2];
        } else {
            pairs = new int[input.length()/2+1];
        }

        int pos = input.length()-1;
        int i=0;
        while (pos>0){
            pairs[i++] = Integer.parseInt(input.substring(pos-1, pos+1));
            pos = pos-2;
        }
        if (pos == 0){
            pairs[i] = Integer.parseInt(input.substring(0,1));
        }
        int[] calc;
        int sqrt;
        int first = getFirst(pairs[pairs.length-1]);
        sqrt = first;
        int rest = pairs[pairs.length-1] - first*first;
        for (i = pairs.length-2; i>=0 ; i--) {
            rest = rest*100 + pairs[i];
            calc = calculate(sqrt,rest);
            if (calc != null){
                sqrt = sqrt*10 + calc[0];
                rest = rest - calc[1];
            }
        }

        return sqrt;
    }

    public static int[] calculate(int input, int limit){
        int calc;
        for (int i = 9; i >= 0; i--) {
            calc = (input*2*10+i)*i;
            if (calc <= limit){
                int[] values = new int[2];
                values[0] = i;
                values [1] = calc;
                return values;
            }
        }
        return null;
    }
    public static int getFirst(int limit){
        for (int i = 9; i > 0; i--) {
            if (i*i<= limit){
                return i;
            }
        }
        return 0;
    }


    //another way to calculate square root
    //using binary search principle
    public static int mySqrt2(int x) {
        long ans = 0;
        long left = 1;
        long right = x;
        while(left <= right) {
            long mid = (left + right) / 2;
            if((mid * mid) <= x ){
                ans = Math.max(mid,ans);
            }
            if((mid * mid)>x){
                right = mid - 1;
            }else{
                left = mid + 1;
            }
        }
        return (int)ans;
    }
}