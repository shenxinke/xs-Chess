package com.xswq.chess.myapplication.bean;

public class TeacherDataTaskBean {

    /**
     * error : {"returnCode":"0","returnMessage":"操作成功","returnUserMessage":"操作成功","userRanking":null,"homeTable":null}
     * data : {"id":62,"dayNum":1,"questionId":"44723,44724,44725","playChess":"1","course":"1","createTime":1547136000000,"createUser":2021,"stage":1,"level":"25K","content":"标准围棋盘由19条横线和竖线组成，也称19路棋盘，面向初学者也有9路和13路棋盘。 棋盘上9个特殊点称作星，最中央那个叫\u201c天元\u201d，这些星将棋盘分为了角、边和中腹区域。棋子要落在交叉点上。","title":"围棋基础知识","playDescribe":"什么都有","questionType":"0$$25k$$星位","questionsTest":"44723,44724,44725","achieveState":0,"achievePlayChessNum":"0","achieveQuestion":null,"achieveCourse":null,"achieveTest":0}
     */

    private ErrorBean error;
    private DataBean data;

    public ErrorBean getError() {
        return error;
    }

    public void setError(ErrorBean error) {
        this.error = error;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class ErrorBean {
        /**
         * returnCode : 0
         * returnMessage : 操作成功
         * returnUserMessage : 操作成功
         * userRanking : null
         * homeTable : null
         */

        private String returnCode;
        private String returnMessage;
        private String returnUserMessage;
        private Object userRanking;
        private Object homeTable;

        public String getReturnCode() {
            return returnCode;
        }

        public void setReturnCode(String returnCode) {
            this.returnCode = returnCode;
        }

        public String getReturnMessage() {
            return returnMessage;
        }

        public void setReturnMessage(String returnMessage) {
            this.returnMessage = returnMessage;
        }

        public String getReturnUserMessage() {
            return returnUserMessage;
        }

        public void setReturnUserMessage(String returnUserMessage) {
            this.returnUserMessage = returnUserMessage;
        }

        public Object getUserRanking() {
            return userRanking;
        }

        public void setUserRanking(Object userRanking) {
            this.userRanking = userRanking;
        }

        public Object getHomeTable() {
            return homeTable;
        }

        public void setHomeTable(Object homeTable) {
            this.homeTable = homeTable;
        }
    }

    public static class DataBean {
        /**
         * id : 62
         * dayNum : 1
         * questionId : 44723,44724,44725
         * playChess : 1
         * course : 1
         * createTime : 1547136000000
         * createUser : 2021
         * stage : 1
         * level : 25K
         * content : 标准围棋盘由19条横线和竖线组成，也称19路棋盘，面向初学者也有9路和13路棋盘。 棋盘上9个特殊点称作星，最中央那个叫“天元”，这些星将棋盘分为了角、边和中腹区域。棋子要落在交叉点上。
         * title : 围棋基础知识
         * playDescribe : 什么都有
         * questionType : 0$$25k$$星位
         * questionsTest : 44723,44724,44725
         * achieveState : 0
         * achievePlayChessNum : 0
         * achieveQuestion : null
         * achieveCourse : null
         * achieveTest : 0
         */

        private int id;
        private int dayNum;
        private String questionId;
        private String playChess;
        private String course;
        private long createTime;
        private int createUser;
        private int stage;
        private String level;
        private String content;
        private String title;
        private String playDescribe;
        private String questionType;
        private String questionsTest;
        private int achieveState;
        private String achievePlayChessNum;
        private String achieveQuestion;
        private String achieveCourse;
        private String achieveTest;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getDayNum() {
            return dayNum;
        }

        public void setDayNum(int dayNum) {
            this.dayNum = dayNum;
        }

        public String getQuestionId() {
            return questionId;
        }

        public void setQuestionId(String questionId) {
            this.questionId = questionId;
        }

        public String getPlayChess() {
            return playChess;
        }

        public void setPlayChess(String playChess) {
            this.playChess = playChess;
        }

        public String getCourse() {
            return course;
        }

        public void setCourse(String course) {
            this.course = course;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public int getCreateUser() {
            return createUser;
        }

        public void setCreateUser(int createUser) {
            this.createUser = createUser;
        }

        public int getStage() {
            return stage;
        }

        public void setStage(int stage) {
            this.stage = stage;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPlayDescribe() {
            return playDescribe;
        }

        public void setPlayDescribe(String playDescribe) {
            this.playDescribe = playDescribe;
        }

        public String getQuestionType() {
            return questionType;
        }

        public void setQuestionType(String questionType) {
            this.questionType = questionType;
        }

        public String getQuestionsTest() {
            return questionsTest;
        }

        public void setQuestionsTest(String questionsTest) {
            this.questionsTest = questionsTest;
        }

        public int getAchieveState() {
            return achieveState;
        }

        public void setAchieveState(int achieveState) {
            this.achieveState = achieveState;
        }

        public String getAchievePlayChessNum() {
            return achievePlayChessNum;
        }

        public void setAchievePlayChessNum(String achievePlayChessNum) {
            this.achievePlayChessNum = achievePlayChessNum;
        }

        public String getAchieveQuestion() {
            return achieveQuestion;
        }

        public void setAchieveQuestion(String achieveQuestion) {
            this.achieveQuestion = achieveQuestion;
        }

        public String getAchieveCourse() {
            return achieveCourse;
        }

        public void setAchieveCourse(String achieveCourse) {
            this.achieveCourse = achieveCourse;
        }

        public String getAchieveTest() {
            return achieveTest;
        }

        public void setAchieveTest(String achieveTest) {
            this.achieveTest = achieveTest;
        }
    }
}
