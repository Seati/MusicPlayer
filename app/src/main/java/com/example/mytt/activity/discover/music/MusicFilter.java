package com.example.mytt.activity.discover.music;

import java.io.File;
import java.io.FilenameFilter;

/**
 * Created by Seati on 2019/1/2.
 */

public class MusicFilter implements FilenameFilter {

    @Override
    public boolean accept(File dir, String filename) {
        return (filename.endsWith(".mp3"));
    }
}
