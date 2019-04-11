package com.jay.java.view;

public class MeasureSpec {
    // 进位大小 = 2的30次方
    // int的大小为32位，所以进位30位 = 使用int的32和31位做标志位
    public static final int MODE_SHIFT = 30;

    // 运算遮罩：0x3为16进制，10进制为3，二进制为11
    // 3向左进位30 = 11 00000000000(11后跟30个0)
    // 作用：用1标注需要的值，0标注不要的值。因1与任何数做与运算都得任何数、0与任何数做与运算都得0
    public static final int MODE_MASK = 0x3 << MODE_SHIFT;

    // UNSPECIFIED的模式设置：0向左进位30 = 00后跟30个0，即00 00000000000
    // 通过高2位
    public static final int UNSPECIFIED = 0 << MODE_SHIFT;

    // EXACTLY的模式设置：1向左进位30 = 01后跟30个0 ，即01 00000000000
    public static final int EXACTLY = 1 << MODE_SHIFT;

    // AT_MOST的模式设置：2向左进位30 = 10后跟30个0，即10 00000000000
    public static final int AT_MOST = 2 << MODE_SHIFT;

    /**
     * makeMeasureSpec（）方法
     * 作用：根据提供的size和mode得到一个详细的测量结果吗，即measureSpec
     **/
    public static int makeMeasureSpec(int size, int mode) {

        return size + mode;
        // measureSpec = size + mode；此为二进制的加法 而不是十进制
        // 设计目的：使用一个32位的二进制数，其中：32和31位代表测量模式（mode）、后30位代表测量大小（size）
        // 例如size=100(4)，mode=AT_MOST，则measureSpec=100+10000...00=10000..00100

    }

    /**
     * getMode（）方法
     * 作用：通过measureSpec获得测量模式（mode）
     **/

    public static int getMode(int measureSpec) {

        return (measureSpec & MODE_MASK);
        // 即：测量模式（mode） = measureSpec & MODE_MASK;
        // MODE_MASK = 运算遮罩 = 11 00000000000(11后跟30个0)
        //原理：保留measureSpec的高2位（即测量模式）、使用0替换后30位
        // 例如10 00..00100 & 11 00..00(11后跟30个0) = 10 00..00(AT_MOST)，这样就得到了mode的值

    }

    /**
     * getSize方法
     * 作用：通过measureSpec获得测量大小size
     **/
    public static int getSize(int measureSpec) {

        return (measureSpec & ~MODE_MASK);
        // size = measureSpec & ~MODE_MASK;
        // 原理类似上面，即 将MODE_MASK取反，也就是变成了00 111111(00后跟30个1)，将32,31替换成0也就是去掉mode，保留后30位的size
    }

}
