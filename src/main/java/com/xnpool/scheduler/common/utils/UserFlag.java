package com.xnpool.scheduler.common.utils;

public class UserFlag {

    /**
     * 打标---对应状态更改为是
     *
     * @param userFlag
     * @return
     */
    public static int add(int data, UserInfoFlag... userFlag) {
        for (UserInfoFlag flag : userFlag) {
            data = FlagBitUtil.sign(data, flag.getIndex());
        }
        return data;
    }

    /**
     * 去标---对应状态更改为否
     *
     * @param userFlag
     * @return
     */
    public static int remove(int data, UserInfoFlag... userFlag) {
        for (UserInfoFlag flag : userFlag) {
            data = FlagBitUtil.removeSign(data, flag.getIndex());
        }
        return data;
    }

    /**
     * 检验是否存在
     *
     * @param userFlag
     * @return
     */
    public static boolean get(final int data, UserInfoFlag userFlag) {
        return FlagBitUtil.checkSign(data, userFlag.getIndex());
    }

    //可以新增32个标
    public enum UserInfoFlag {
        IS_DISABLED(1, "是否禁用"),
        IS_BIND_MOBILE(2, "是否绑定手机"),
        IS_BIND_EMAIL(3, "是否绑定邮箱"),
        IS_VERIFY(4, "是否实名认证"),
        IS_BIND_CARD(5, "是否绑定投资账户银行卡"),
        IS_FINANCE_BIND_CARD(6, "是否绑定融资账户银行卡"),
        IS_FREE_BIND_CARD(7, "是否绑定自由账户银行卡"),
        IS_OPEN(8, "是否开通投资账户"),
        IS_FROZEN(9, "是否冻结投资账户"),
        IS_FINANCE_OPEN(10, "是否开通融资账户"),
        IS_FINANCE_FROZEN(11, "是否冻结融资账户"),
        IS_FREE_OPEN(12, "是否开通自由账户"),
        IS_FREE_FROZEN(13, "是否冻结自由账户"),
        IS_VIRTUAL_OPEN(14, "是否开通虚拟账户"),
        IS_VIRTUAL_FROZEN(15, "是否冻结虚拟账户"),
        IS_SAFE_QUESTION(16, "是否设置安全问题"),
        IS_RELATION(17, "是否设置紧急联系人");

        private UserInfoFlag(int index, String desc) {
            this.index = index;
            this.desc = desc;
        }

        private final String desc;
        private final int index;

        public String getDesc() {
            return desc;
        }

        public int getIndex() {
            return index;
        }

    }

    public static void main(String[] args) {
        int i = 0;
        int data = add(i, UserInfoFlag.IS_DISABLED, UserInfoFlag.IS_FROZEN);
        System.out.println(data);
        System.out.println(get(data, UserInfoFlag.IS_DISABLED));
        System.out.println(get(data, UserInfoFlag.IS_FROZEN));
        int removeDate = remove(154, UserInfoFlag.IS_BIND_CARD);
        System.out.println(removeDate);
        System.out.println(get(154, UserInfoFlag.IS_BIND_CARD));


    }

}
