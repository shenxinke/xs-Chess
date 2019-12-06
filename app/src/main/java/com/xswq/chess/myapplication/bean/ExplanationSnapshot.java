package com.xswq.chess.myapplication.bean;

public class ExplanationSnapshot {

    private String snapshotImage;
    private String ix;

    public String getChessid() {
        return chessid;
    }

    public void setChessid(String chessid) {
        this.chessid = chessid;
    }

    private String chessid;

    public String getIx() {
        return ix;
    }

    public void setIx(String ix) {
        this.ix = ix;
    }

    public ExplanationSnapshot(){

    }

    public String getSnapshotImage() {
        return snapshotImage;
    }

    public void setSnapshotImage(String snapshotImage) {
        this.snapshotImage = snapshotImage;
    }
}
