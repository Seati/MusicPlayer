package com.example.mytt.bean;

import java.util.List;

public class SearchMusicBean {
    private int code;
    private String msg;
    private DataObj data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataObj getData() {
        return data;
    }

    public void setData(DataObj data) {
        this.data = data;
    }

    public  class DataObj{

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
            private List<ArtistObj>artists;

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

            public List<ArtistObj> getArtists() {
                return artists;
            }

            public void setArtists(List<ArtistObj> artists) {
                this.artists = artists;
            }

            public class ArtistObj{

                private String name;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }
            }
        }
    }
}
