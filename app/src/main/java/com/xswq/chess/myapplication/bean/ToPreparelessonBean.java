package com.xswq.chess.myapplication.bean;

import com.xswq.chess.myapplication.bean.PreparelessonBean;

import java.util.List;

public class ToPreparelessonBean {
    /**
     * grouptitle : 教学目标
     * listArray : [{"text":"1、知识目标\n①了解\u201c打吃\u201d\u201c长气\u201d的概念。\n②了解\u201c危险棋子\u201d的概念。\n③初步感知\u201c逃\u201d的意识。\n2、能力目标\n①学会\u201c打吃\u201d\u201c长气\u201d的方法，能解决相关习题。\n②学会\u201c危险棋子\u201d的观察方法。\n③初步建立\u201c防守\u201d的意识。\n3、素养目标\n①进一步培养幼儿倾听复述能力，发展语言表达力。\n②通过观察\u201c危险棋子\u201d，培养幼儿细微观察能力。\n③通过解决相关习题，培养幼儿独立发现问题、解决问题的能力。\n④通过相关习题的练习，使幼儿初步建立\u201c攻\u201d、\u201c防\u201d意识，并初步形成围棋思维逻辑。","listtitle":"","type":"1"}]
     */

    private String grouptitle;
    private List<PreparelessonBean.DataBean.Data2Bean.ListArrayBean> listArray;

    public String getGrouptitle() {
        return grouptitle;
    }

    public void setGrouptitle(String grouptitle) {
        this.grouptitle = grouptitle;
    }

    public List<PreparelessonBean.DataBean.Data2Bean.ListArrayBean> getListArray() {
        return listArray;
    }

    public void setListArray(List<PreparelessonBean.DataBean.Data2Bean.ListArrayBean> listArray) {
        this.listArray = listArray;
    }

    public static class ListArrayBean {
        /**
         * text : 1、知识目标
         * ①了解“打吃”“长气”的概念。
         * ②了解“危险棋子”的概念。
         * ③初步感知“逃”的意识。
         * 2、能力目标
         * ①学会“打吃”“长气”的方法，能解决相关习题。
         * ②学会“危险棋子”的观察方法。
         * ③初步建立“防守”的意识。
         * 3、素养目标
         * ①进一步培养幼儿倾听复述能力，发展语言表达力。
         * ②通过观察“危险棋子”，培养幼儿细微观察能力。
         * ③通过解决相关习题，培养幼儿独立发现问题、解决问题的能力。
         * ④通过相关习题的练习，使幼儿初步建立“攻”、“防”意识，并初步形成围棋思维逻辑。
         * listtitle :
         * type : 1
         */

        private String text;
        private String listtitle;
        private String type;
        private String imgUrl;
        private String myHandouts;

        public String getMyHandouts() {
            return myHandouts;
        }

        public void setMyHandouts(String myHandouts) {
            this.myHandouts = myHandouts;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getListtitle() {
            return listtitle;
        }

        public void setListtitle(String listtitle) {
            this.listtitle = listtitle;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
