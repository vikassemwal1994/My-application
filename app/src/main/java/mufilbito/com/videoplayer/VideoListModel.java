package mufilbito.com.videoplayer;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class VideoListModel implements Serializable {

    @SerializedName("video_name")
    String video_name;
    @SerializedName("video_rendition_thumbnail")
    String video_rendition_thumbnail;
    @SerializedName("video_rendition_path")
    String video_rendition_path;

    public String getVideo_name() {
        return video_name;
    }

    public void setVideo_name(String video_name) {
        this.video_name = video_name;
    }

    public String getVideo_rendition_thumbnail() {
        return video_rendition_thumbnail;
    }

    public void setVideo_rendition_thumbnail(String video_rendition_thumbnail) {
        this.video_rendition_thumbnail = video_rendition_thumbnail;
    }

    public String getVideo_rendition_path() {
        return video_rendition_path;
    }

    public void setVideo_rendition_path(String video_rendition_path) {
        this.video_rendition_path = video_rendition_path;
    }
}
