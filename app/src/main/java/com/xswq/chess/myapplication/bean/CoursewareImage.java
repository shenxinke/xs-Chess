package com.xswq.chess.myapplication.bean;

public class CoursewareImage {

    private int index;

    private String title;

    private int video;

    private int practice;

    private boolean canWatch;

    public boolean getCanWatch() {
        return canWatch;
    }

    public void setCanWatch(boolean canWatch) {
        this.canWatch = canWatch;
    }

    public CoursewareImage(){

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getVideo() {
        return video;
    }

    public void setVideo(int video) {
        this.video = video;
    }

    public int getPractice() {
        return practice;
    }

    public void setPractice(int practice) {
        this.practice = practice;
    }


    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
