package com.yun.condition;

/**
 * @FileName: LinuxListCmdImpl
 * @Description: linux操作系统的list实现
 * @Author: zyk
 * @createTime: 2021/12/20 18:06
 * @version: 1.0
 */
public class LinuxListCmdImpl implements ListFileCmd {
    @Override
    public String showListCmd() {
        return "ls";
    }
}
