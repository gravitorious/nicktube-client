package com.nicktheblackbeard.client.data;

import java.util.ArrayList;

/**
 * @author nicktheblackbeard
 * 9/6/21
 */
public class NClient{
    ArrayList<NFile> mkvFiles;
    ArrayList<NFile> mp4Files;
    ArrayList<NFile> aviFiles;

    public NClient(){
        this.mkvFiles = new ArrayList<NFile>();
        this.mp4Files = new ArrayList<NFile>();
        this.aviFiles = new ArrayList<NFile>();
    }
}
