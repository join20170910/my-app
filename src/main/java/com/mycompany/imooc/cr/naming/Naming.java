package com.mycompany.imooc.cr.naming;

import java.time.LocalDate;

public class Naming {

    // 足够长以揭示意图， 又不够长难于理解

    public void getProductInventoryFromInventorySystemByProductId(String productId) {

    }

    public void getProductInventory(String productId) {

    }


    // 使用有意义的名字

    /***** 当前日期 *****/

    // 反例

    private LocalDate date;

    // 正例

    private LocalDate currentDate;

    /***** 每页数据条数  *****/

    // 反例

    private int size;

    private int lines;

    // 正例

    private int pageSize;

    private int linesPerPage;

    /***** 布尔值 *****/

    // 反例

    private boolean flag;

    // 正例

    private boolean dataReady;

    /***** 数值 *****/

    // 反例

    private static final int THREE = 3;

    // 正例

    private static final int RETRY_TIMES = 3;


    // 参数

    // 反例

    public void copyArray(int[] aArray, int[] bArray) {
        for (int i = 0; i < aArray.length; i++) {
            bArray[i] = aArray[i];
        }
    }

    // 正例

    public void copy(int[] source, int[] target) {
        for (int sourceIndex = 0; sourceIndex < source.length; sourceIndex++) {
            target[sourceIndex] = source[sourceIndex];
        }
    }


    public void printScoreDemoOne() {
        int[][] scores = new int[10][5];
        for (int i = 0; i < scores.length; i++) {
            for (int j = 0; j < scores[i].length; j++) {
                int score = scores[i][j];
                // xxx
            }
        }
    }

    public void printScoreDemoTwo() {
        int[][] scores = new int[10][5];
        for (int userIndex = 0; userIndex < scores.length; userIndex++) {
            for (int courseIndex = 0; courseIndex < scores[userIndex].length; courseIndex++) {
                int score = scores[userIndex][courseIndex];
                // xxx
            }
        }
    }


    // 缩写

    public void genId() {

    }

    private double pc;

    private String dnsServer;

    private String k8sService;

    // 专业领域

    public void dijkstra() {

    }

    // 一种自然语言

    public void tijiaoOrder() {

    }

    // 考虑拆分
    public void updateUserBothDBAndCacheThenSendMail() {
        // 检查用户是否存在
        // 更新用户
        // 更新缓存
        // 发送邮件给用户
    }

    public void isUserExisted() {

    }

    public void updateUserInDB() {

    }

    public void updateUserInCache() {

    }

    public void sendNotification() {

    }
}
