package com.example.primevideoclone.Model;

import java.util.List;

public class MovieTrailer {
    /**
     * id : 550
     * results : [{"id":"5c9294240e0a267cd516835f","iso_639_1":"en","iso_3166_1":"US","key":"BdJKm16Co6M","name":"Fight Club | #TBT Trailer | 20th Century FOX","site":"YouTube","size":1080,"type":"Trailer"},{"id":"5e382d1b4ca676001453826d","iso_639_1":"en","iso_3166_1":"US","key":"6JnN1DmbqoU","name":"Fight Club - Theatrical Trailer Remastered in HD","site":"YouTube","size":1080,"type":"Trailer"}]
     */

    private int id;
    private List<ResultsBean> results;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        /**
         * id : 5c9294240e0a267cd516835f
         * iso_639_1 : en
         * iso_3166_1 : US
         * key : BdJKm16Co6M
         * name : Fight Club | #TBT Trailer | 20th Century FOX
         * site : YouTube
         * size : 1080
         * type : Trailer
         */

        private String id;
        private String iso_639_1;
        private String iso_3166_1;
        private String key;
        private String name;
        private String site;
        private int size;
        private String type;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getIso_639_1() {
            return iso_639_1;
        }

        public void setIso_639_1(String iso_639_1) {
            this.iso_639_1 = iso_639_1;
        }

        public String getIso_3166_1() {
            return iso_3166_1;
        }

        public void setIso_3166_1(String iso_3166_1) {
            this.iso_3166_1 = iso_3166_1;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSite() {
            return site;
        }

        public void setSite(String site) {
            this.site = site;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
