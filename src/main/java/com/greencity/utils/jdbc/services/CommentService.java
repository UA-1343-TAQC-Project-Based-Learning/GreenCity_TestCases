package com.greencity.utils.jdbc.services;

import com.greencity.utils.jdbc.dao.CommentDao;
import com.greencity.utils.jdbc.dao.UserDAO;
import com.greencity.utils.jdbc.entity.CommentEntity;
import com.greencity.utils.jdbc.entity.UserEntity;

import java.util.List;


public class CommentService {
    private final CommentDao commentDAO;
    private final UserDAO userDAO;

    public CommentService(String url, String login, String password){
        this.commentDAO = new CommentDao(url, login, password);
        this.userDAO = new UserDAO(url,login, password);
    }

    public List<CommentEntity> getAllComments() {
        return commentDAO.selectAllComment();
    }

    private int userId(String userEmail){
        return userDAO.selectByEmail(userEmail).getId();
    }

    public List<CommentEntity> getCommentByUser(String userEmail) {
        return commentDAO.selectByUser(userId(userEmail));
    }

    public int createCommentByUser(String userEmail){
         return commentDAO.createNewCommentByUser(userId(userEmail));
    }

    public void deleteAllCommentsByUser(String userEmail){
        commentDAO.deleteAllCommentsByUser(userId(userEmail));
    }
}
