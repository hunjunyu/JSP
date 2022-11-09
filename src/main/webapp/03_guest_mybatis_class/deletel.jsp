<%@ page language="java" contentType="text/html; charset=utf-8"%>
 <%@ page import="mybatis.guest.model.Comment" %>   
 <%@ page import="mybatis.guest.service.CommentService" %>   
  <% 
  int commentNo = Integer.parseInt( request.getParameter("cId"));
  //Comment comment = CommentService.getInstance().selectCommentByPrimaryKey(commentNo);
  CommentService.getInstance().deleteComment(commentNo);
  response.sendRedirect("listComment.jsp");
  %> 
  