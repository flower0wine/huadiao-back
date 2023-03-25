package com.huadiao.pojo;

import java.util.List;

/**
 * @Project_Name huadiao
 * @Author 李志
 * @Version 1.0
 * @Start_Time 2022 08 29 9:29
 */
public class HistoryInfer {
    List<NotesHistory> notesHistoryList;
    List<FanjuHistory> fanjuHistoryList;

    public List<NotesHistory> getNotesHistoryList() {
        return notesHistoryList;
    }

    public void setNotesHistoryList(List<NotesHistory> notesHistoryList) {
        this.notesHistoryList = notesHistoryList;
    }

    public List<FanjuHistory> getFanjusHistoryList() {
        return fanjuHistoryList;
    }

    public void setFanjusHistoryList(List<FanjuHistory> fanjuHistoryList) {
        this.fanjuHistoryList = fanjuHistoryList;
    }

    @Override
    public String toString() {
        return "HistoryInfer{" +
                "notesHistoryList=" + notesHistoryList +
                ", fanjuHistoryList=" + fanjuHistoryList +
                '}';
    }
}
