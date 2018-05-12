package webapp.models;

public class Stream {

    private String filterWord;
    private String language;
    private int streamLength;
    private boolean putInDB;
    private boolean useFiltering;
    private boolean uploadPictures;

    public String getFilterWord() {
        return filterWord;
    }

    public void setFilterWord(String filterWord) {
        this.filterWord = filterWord;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getStreamLength() {
        return streamLength;
    }

    public void setStreamLength(int streamLength) {
        this.streamLength = streamLength;
    }

    public boolean isPutInDB() {
        return putInDB;
    }

    public void setPutInDB(boolean putInDB) {
        this.putInDB = putInDB;
    }

    public boolean isUseFiltering() {
        return useFiltering;
    }

    public void setUseFiltering(boolean useFiltering) {
        this.useFiltering = useFiltering;
    }

    public boolean isUploadPictures() {
        return uploadPictures;
    }

    public void setUploadPictures(boolean uploadPictures) {
        this.uploadPictures = uploadPictures;
    }
}
