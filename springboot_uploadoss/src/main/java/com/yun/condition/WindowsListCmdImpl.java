package com.yun.condition;

/**
 * @FileName: WindowsListCmdImpl
 * @Description:
 * @Author: zyk
 * @createTime: 2021/12/20 18:07
 * @version: 1.0
 */
public class WindowsListCmdImpl implements ListFileCmd {
    @Override
    public String showListCmd() {
        return "dir";
    }
}
