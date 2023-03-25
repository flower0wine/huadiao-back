package com.huadiao.utils;

import com.huadiao.pojo.FestivalInfo;
import com.huadiao.web.service.AbstractOrdinaryService;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * @projectName 花凋
 * @author  flowerwine
 * @version  1.1
 * @description 节日处理器
 */
public class FestivalHandler {

    /**
     * 节日信息
     */
    public static FestivalInfo festivalInfo;

    /**
     * 节日日期, 第一列为月份, 第二列为日期 (行数不确定, 列数 2 列)
     */
    private static int[][] festivalDate;

    /**
     * 定时器
     */
    private static final Timer timer = new Timer();

    /**
     * 所有的节日信息
     */
    private static List<FestivalInfo> festivalInfos;

    /**
     * 节日处理
     */
    public FestivalHandler() throws IOException {
        getFestivalInfos();
        generatorFestivalInfo();
        // 初次运行时, 延时: 今晚零点 执行一次, 周期: 24 小时
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                generatorFestivalInfo();
            }
        }, generatorZeroPM(), 1000 * 60 * 60 * 24);
    }

    /**
     * 生成节日信息, 生成的节日在不同的时候会有不一样的变化
     *    如果是在节日当前 festivalInfo 中的 isNextFestival 为 false,
     *    否则当天没有任何节日则显示 下一个节日, 并且 isNextFestival 为 true
     */
    private void generatorFestivalInfo() {
        // 获取当前时间
        LocalDate localDate = LocalDate.now();
        int month = localDate.getMonthValue();
        int day = localDate.getDayOfMonth();
        // 使用原子类, 具有原子性, 即操作要么全部成功, 要么全部失败
        AtomicBoolean flag = new AtomicBoolean(true);
        AtomicInteger index = new AtomicInteger();

        Arrays.stream(festivalDate).forEach((item) -> {
            if (flag.get() && month <= item[0]) {
                if(month == item[0]) {
                    if(day > item[1]) {
                        index.getAndIncrement();
                        return;
                    }
                }
                // 获取到日期设置为 false
                flag.set(false);
                festivalInfo = festivalInfos.get(index.get());
                festivalInfo.setNextFestival(month != item[0] || day != item[1]);
            }
            index.getAndIncrement();
        });
    }

    /**
     * 获取所有的节日信息, 并且将日期处理
     */
    private void getFestivalInfos() throws IOException {
        // 从数据库获取所有的节日信息
        List<FestivalInfo> allFestivalInfos = AbstractOrdinaryService.getAllFestivalInfos();
        festivalDate = new int[allFestivalInfos.size()][2];
        AtomicInteger index = new AtomicInteger();
        // 将获取到的节日信息按照 日期 排序, 再按照排序后的节日进行日期处理
        festivalInfos = allFestivalInfos.stream().sorted((festivalInfo1, festivalInfo2) -> {
            String[] dateSplit1 = festivalInfo1.getFestivalDate().split("[月日]");
            String[] dateSplit2 = festivalInfo2.getFestivalDate().split("[月日]");
            int month1 = Integer.parseInt(dateSplit1[0]);
            int month2 = Integer.parseInt(dateSplit2[0]);
            int day1 = Integer.parseInt(dateSplit1[1]);
            int day2 = Integer.parseInt(dateSplit2[1]);
            // 前面一个比后面大 返回 1, 类似于 true
            if (month1 > month2 || (month1 == month2 && day1 > day2)) {
                return 1;
            } else if (month1 == month2 && day1 == day2) {
                return 0;
            } else {
                return -1;
            }
        }).peek((festivalInfo) -> {
            String[] dateSplit = festivalInfo.getFestivalDate().split("[月日]");
            festivalDate[index.get()][0] = Integer.parseInt(dateSplit[0]);
            festivalDate[index.get()][1] = Integer.parseInt(dateSplit[1]);
            index.getAndIncrement();
        }).collect(Collectors.toList());
    }

    /**
     * 生成一个今晚零点的 Date 对象
     *
     * @return Date
     */
    private Date generatorZeroPM() {
        // 获取现在的时间并加上一天
        LocalDateTime localDateTime = LocalDateTime.now().plusDays(1);
        // 构建一个今晚 24 点整点的 date
        return new Date(localDateTime.getYear() - 1900, localDateTime.getMonthValue(), localDateTime.getDayOfMonth(), 0, 0);
    }

}
