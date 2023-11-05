package com.example.kotlin_test2.test1.fragment04.paging3.petnewsbean;

import java.util.List;

public class PetNewsBean {

    /**
     * curpage : 1
     * allnum : 10
     * newslist : [{"id":"a484e162d69c4450790632ed10f39cef","ctime":"2023-11-05 06:00","title":"坐地铁遇不文明行为如何应对？各地整治违法违规行为","description":"如随地吐痰，便溺，吐口香糖，乱扔果皮、纸屑等废弃物的，处一百元以上五百元以下罚款；携带宠物、家禽等动物乘车的，处五十元以上二百元以下罚款；吸烟(含电子烟)的，处五十元罚款；对强行上下车、在车...","source":"中新宠物","picUrl":"","url":"http://www.chinanews.com.cn/sh/2023/11-05/10106217.shtml"},{"id":"696694ab482b354c60e1df4bd65efb61","ctime":"2023-11-04 21:00","title":"（进博故事）水田贤治：进博会为日本企业带来更多机会和可能性","description":"在消费品展区，我们首次尝试将露营、滑雪等中国消费者需求不断扩大的户外品牌以及宠物用品品牌进驻进博会，为大家提供现场体验实感操作。","source":"中新宠物","picUrl":"","url":"http://www.chinanews.com.cn/cj/2023/11-04/10106107.shtml"},{"id":"3afbeb1e6bec304b9503e07624661c77","ctime":"2023-11-03 20:00","title":"聚焦\u201c双十一\u201d 川渝陕甘青蒙消费者组织发布网购消费典型案例","description":"案例五为陕西省消费者权益保护委员会接到消费者黄先生投诉称，自己在某直播平台购买宠物猫，被诱导添加商家微信后支付700元，次日又被要求支付300元运费。","source":"中新宠物","picUrl":"","url":"http://www.chinanews.com.cn/cj/2023/11-03/10105601.shtml"},{"id":"e4859c443839579a1fa835fcae51bf38","ctime":"2023-11-03 09:00","title":"年轻人中有一批\u201c只为喜欢而消费\u201d","description":"小组还举行\u201c物尽其用成果汇报大会\u201d并进行评奖，汇集网友为节约物资而进行的各种手工改造，比如有人自己画设计图，用纸箱子改造出一个宠物狗屋。以\u201c不买\u201d为目标的小组在豆瓣还有不少。","source":"中新宠物","picUrl":"","url":"http://www.chinanews.com.cn/cj/2023/11-03/10105276.shtml"},{"id":"e83d1bcf1d79db42bcc30b90fb754f49","ctime":"2023-11-03 09:00","title":"国际跳棋何时能火起来","description":"赛场里，64格女子卫冕冠军刘沛坐在靠近主席台的棋桌，身后的背包挂着宠物挂件。在直播区，周德邦仍喜欢探着身子下比赛，上届智运会，他为上海赢得100格少年男子冠军。眼前的场景...","source":"中新宠物","picUrl":"","url":"http://www.chinanews.com.cn/ty/2023/11-03/10105278.shtml"},{"id":"7cc2397ef1cd4d6aaf20ce4bb0e20bbe","ctime":"2023-11-03 04:00","title":"城市里的大量流浪犬该怎么处理","description":"宠物主随意遗弃宠物带来疾病传播扰民伤民环境污染等隐患城市里的大量流浪...","source":"中新宠物","picUrl":"","url":"http://www.chinanews.com.cn/sh/2023/11-03/10105215.shtml"},{"id":"7ce13d3b5da8c4490a1b9f1b5ee58b7d","ctime":"2023-11-03 01:00","title":"通过照片就能和宠物对话？\u201c宠物沟通师\u201d的读心术有点玄","description":"最近，一种自称\u201c宠物沟通师\u201d的职业在社交媒体上兴起。这些\u201c宠物沟通师\u201d声称，凭借一张...","source":"中新宠物","picUrl":"","url":"https://www.chinanews.com.cn/sh/2023/11-02/10104746.shtml"},{"id":"0145e2551dc50dc93a8c0a9964fe8e45","ctime":"2023-11-02 17:00","title":"（第六届进博会）通用磨坊中国区总裁苏强：深信中国市场无限潜力","description":"\u201c第四届进博会上，通用磨坊高端宠物天然食品品牌\u2018蓝挚\u2019第一次亮相中国，此后通过跨境电商向中国市场销售，两年来，我们看到了中国高端","source":"中新宠物","picUrl":"","url":"http://www.chinanews.com.cn/cj/2023/11-02/10104960.shtml"},{"id":"9ce5abe4c2b0c96c2ae9dec6ca3d2cc5","ctime":"2023-11-02 10:00","title":"通过照片就能和宠物对话？\u201c宠物沟通师\u201d的读心术有点玄","description":"最近，一种自称\u201c宠物沟通师\u201d的职业在社交媒体上兴起。这些\u201c宠物沟通师\u201d声称，凭借一张...","source":"中新宠物","picUrl":"","url":"http://www.chinanews.com.cn/sh/2023/11-02/10104746.shtml"},{"id":"d5f14123e2bedf7e19f441d9f113f6c7","ctime":"2023-11-02 01:00","title":"逃离完美偶像的日子","description":"屏幕里二次元的女孩会聊新养的宠物，也聊晚餐和电影。谭杉杉记得，宠物的名字是真的，但晚饭通常不是。...","source":"中新宠物","picUrl":"","url":"https://www.chinanews.com.cn/sh/2023/11-01/10104063.shtml"}]
     */

    private int curpage;
    private int allnum;
    private List<NewslistBean> newslist;

    public int getCurpage() {
        return curpage;
    }

    public void setCurpage(int curpage) {
        this.curpage = curpage;
    }

    public int getAllnum() {
        return allnum;
    }

    public void setAllnum(int allnum) {
        this.allnum = allnum;
    }

    public List<NewslistBean> getNewslist() {
        return newslist;
    }

    public void setNewslist(List<NewslistBean> newslist) {
        this.newslist = newslist;
    }

    public static class NewslistBean {
        /**
         * id : a484e162d69c4450790632ed10f39cef
         * ctime : 2023-11-05 06:00
         * title : 坐地铁遇不文明行为如何应对？各地整治违法违规行为
         * description : 如随地吐痰，便溺，吐口香糖，乱扔果皮、纸屑等废弃物的，处一百元以上五百元以下罚款；携带宠物、家禽等动物乘车的，处五十元以上二百元以下罚款；吸烟(含电子烟)的，处五十元罚款；对强行上下车、在车...
         * source : 中新宠物
         * picUrl :
         * url : http://www.chinanews.com.cn/sh/2023/11-05/10106217.shtml
         */

        private String id;
        private String ctime;
        private String title;
        private String description;
        private String source;
        private String picUrl;
        private String url;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCtime() {
            return ctime;
        }

        public void setCtime(String ctime) {
            this.ctime = ctime;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getPicUrl() {
            return picUrl;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
