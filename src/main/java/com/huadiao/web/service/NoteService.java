package com.huadiao.web.service;

public interface NoteService extends Service {
    /**
     * 笔记上传成功
     */
    String NOTE_UPLOAD_SUCCEED = "uploadSucceed";

    /**
     * 给笔记的评论点赞成功
     */
    String NOTE_MARK_LIKES_SUCCEED = "noteMarkLikesSucceed";

    /**
     * 笔记评论取消点赞成功
     */
    String NOTE_MARK_UNLIKES_SUCCEED = "noteMarkUnlikesSucceed";

    /**
     * 新增笔记评论成功
     */
    String ADD_NOTE_MARK_SUCCEED = "addNoteMarkSucceed";

    /**
     * 新增笔记喜欢成功
     */
    String ADD_NEW_NOTE_LIKE_SUCCEED = "addNewNoteLikeSucceed";

    /**
     * 取消喜欢笔记成功
     */
    String CANCEL_NEW_NOTE_LIKE_SUCCEED = "cancelNewNoteLikeSucceed";

    /**
     * 不喜欢笔记成功
     */
    String ADD_NEW_NOTE_UNLIKE_SUCCEED = "addNewNoteUnlikeSucceed";

    /**
     * 取消不新欢笔记成功
     */
    String CANCEL_NOTE_UNLIKE_SUCCEED = "cancelNoteUnlikeSucceed";

    /**
     * 添加笔记评论成功
     */
    String ADD_NOTE_COMMENT_SUCCEED = "addNoteCommentSucceed";
}
