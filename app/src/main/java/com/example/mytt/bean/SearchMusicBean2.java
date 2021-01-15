package com.example.mytt.bean;

import java.util.List;

public class SearchMusicBean2 {
    private ResultObj result;

    public ResultObj getResult() {
        return result;
    }

    public void setResult(ResultObj result) {
        this.result = result;
    }

    public  class ResultObj{

        private List<SongObj>songs;

        public List<SongObj> getSongs() {
            return songs;
        }

        public void setSongs(List<SongObj> songs) {
            this.songs = songs;
        }

        public class SongObj{

            private String id;
            private String name;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
