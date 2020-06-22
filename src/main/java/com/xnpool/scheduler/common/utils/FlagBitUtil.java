package com.xnpool.scheduler.common.utils;

public class FlagBitUtil {

    public static final int FULL_FALG = 0XFFFFFFFF;

    /**
     * 打标
     *
     * @param falg   需要打标的源数
     * @param offset 标位
     */
    public static int sign(int flag, int offset) {
        int offsetT = 1 << (offset - 1);
        return flag | offsetT;
    }

    /**
     * 去标
     *
     * @param falg   需要移除标的源数
     * @param offset 标位
     */
    public static int removeSign(int flag, int offset) {
        int offsetT = 1 << (offset - 1);
        offsetT = FULL_FALG ^ offsetT;
        return flag & offsetT;
    }

    /**
     * 检查标位是否被打标
     *
     * @param flag
     * @param offset 标位
     * @return 该标位是否被打标
     */
    public static boolean checkSign(int flag, int offset) {
        int offsetT = 1 << (offset - 1);
        return (flag & offsetT) == offsetT;
    }

    public static void main(String[] args) {
        int flag = 0;

        flag = FlagBitUtil.sign(flag, 1);

        System.out.println(flag + "\t" + Integer.toBinaryString(flag));

        flag = FlagBitUtil.sign(flag, 4);  //将标的第4位标记

        System.out.println(flag + "\t" + Integer.toBinaryString(flag));

        flag = FlagBitUtil.removeSign(flag, 3);  //将标的第3位标记移除

        System.out.println(flag + "\t" + Integer.toBinaryString(flag));

        //检查第1、2、3、4、5位是否被打标
        System.out.println(FlagBitUtil.checkSign(flag, 1));
        System.out.println(FlagBitUtil.checkSign(flag, 2));
        System.out.println(FlagBitUtil.checkSign(flag, 3));
        System.out.println(FlagBitUtil.checkSign(flag, 4));
        System.out.println(FlagBitUtil.checkSign(flag, 5));

    }

}
