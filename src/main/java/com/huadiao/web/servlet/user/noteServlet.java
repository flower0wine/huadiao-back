package com.huadiao.web.servlet.user; /**
 * @Project_Name huadiao
 * @Author 李志
 * @Version 1.0
 * @Start_Time 2022 08 16 20:32
 */

import com.alibaba.fastjson.JSON;
import com.huadiao.pojo.NoteDetail;
import com.huadiao.pojo.Notes;
import com.huadiao.utils.ParameterHandle;
import com.huadiao.web.mysqlProcess;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

@WebServlet(urlPatterns = "/noteServlet")
public class noteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletFileUpload upload = ParameterHandle.createFile();

        String s = uploadParasRequest(upload, request, response);
        response.getWriter().write(s);
    }

    // 这个是核心方法
    public String uploadParasRequest(ServletFileUpload upload, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        String tip = "";
        try {
            List<FileItem> fileItems = upload.parseRequest(request);
            //使用文件解析对象的parseRequest()(解析request)，这个方法就会将req中的表单项按照一个<input>一个FileItem对象来进行封装
            //parseRequest(HttpServletRequest) 方法可以将通过表单中每一个HTML标签提交的数据封装成一个FileItem对象，然后以List列表的形式返回
            System.out.println(fileItems);
            // 获取 userId
            HttpSession session = request.getSession();
            String userId = (String) session.getAttribute("userId");
            String myUid = (String) session.getAttribute("uid");
            String noteTitle = null;
            String noteContent = null;
            String noteId = null;
            String otherUid = null;
            String noteAbstract = null;
            String markContent = null;
            String rootId = null;
            String authorUid = null;
            String subId = null;
            //遍历，找到表单中每一个文件对应的<input>上传的文件数据
            for (FileItem fileItem : fileItems) {
                //这个<input>中的数据不是文件
                if (fileItem.isFormField()) {

                    //获取非文件<input>的name属性
                    String name = fileItem.getFieldName();
                    //获取非文件<input>的value属性
                    String value = fileItem.getString("utf-8");

                    switch (name) {
                        case "otherUid" : {
                            otherUid = value;
                            break;
                        }
                        case "noteId" : {
                            noteId = value;
                            break;
                        }
                        case "authorUid" : {
                            authorUid = value;
                            break;
                        }
                        case "rootId" : {
                            rootId = value;
                            break;
                        }
                        case "subId" : {
                            subId = value;
                            break;
                        }
                        case "markContent" : {
                            markContent = value;
                            break;
                        }
                        case "addViewNumber" : {
                            if (!myUid.equals(otherUid)) {
                                mysqlProcess.insertViewNumber(otherUid, noteId, myUid);
                                return "";
                            }
                            break;
                        }
                        case "noteLike" : {
                            mysqlProcess.insertNoteLike(otherUid, noteId, myUid);
                            return "";
                        }
                        case "buildRootMark" : {
                            mysqlProcess.insertRootMark(noteId, authorUid, myUid, userId, markContent, rootId, authorUid);
                            return "";
                        }
                        case "buildSubMark" : {
                            mysqlProcess.insertSubMark(noteId, otherUid, myUid, userId, markContent, rootId, subId, authorUid);
                            return "";
                        }
                        // 点赞评论，如果存在，并且为点赞状态，就取消点赞，反之进行点赞
                        case "likeMark" : {
                            mysqlProcess.insertMarkLike(noteId, myUid, otherUid, rootId, subId);
                            return "";
                        }
                        case "unlikeMark" : {
                            mysqlProcess.insertMarkUnlike(noteId, myUid, otherUid, rootId, subId);
                            return "";
                        }
                        // 取消笔记点赞
                        case "cancelNoteLike" : {
                            mysqlProcess.updateNoteLike(noteId, otherUid, myUid);
                            return "";
                        }
                        case "noteTitle" : {
                            noteTitle = value;
                            break;
                        }
                        case "noteAbstract" : {
                            noteAbstract = value;
                            break;
                        }
                        case "noteContent" : {
                            noteContent = value;
                            break;
                        }
                        case "buildNote" : {
                            mysqlProcess.insertNewNote(myUid, userId, noteTitle, noteContent, noteAbstract);
                            response.getWriter().write("uploadSucceed");
                            return "";
                        }
                        case "querySingle" : {
                            NoteDetail noteDetail;
                            try {
                                noteDetail = mysqlProcess.selectNoteByUidAndId(noteId, myUid, userId, otherUid, noteId);
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                                return "";
                            }
                            System.out.println(noteDetail);
                            response.getWriter().write(JSON.toJSONString(noteDetail));

                            // 记录用户访问笔记
                            if (!myUid.equals(otherUid)) {
                                mysqlProcess.insertViewedNote(otherUid, myUid, noteId);
                            }
                            return "";
                        }
                        case "queryAll" : {
                            Notes notes = mysqlProcess.selectNotesByUid(otherUid);
                            response.getWriter().write(JSON.toJSONString(notes));
                            return "";
                        }
                        case "noteStar" : {
                            mysqlProcess.insertNoteStar(otherUid, myUid, noteId);
                            return "";
                        }
                        case "cancelNoteStar" : {
                            mysqlProcess.cancelNoteStar(otherUid, myUid, noteId);
                            return "";
                        }
                        case "noteUnlike" : {
                            mysqlProcess.insertNoteUnlike(otherUid, noteId, myUid);
                            return "";
                        }
                        case "cancelNoteUnlike" : {
                            mysqlProcess.updateNoteUnlike(noteId, otherUid, myUid);
                            return "";
                        }
                        case "modify" : {
                            mysqlProcess.updateNoteContent(userId, noteTitle, noteContent, noteId, noteAbstract);
                            return "";
                        }
                        case "delete" : {
                            mysqlProcess.deleteNote(userId, noteId);
                            return "";
                        }
                        default: {

                        }
                    }
                    System.out.println(name + "::" + value);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return tip;
    }
}
