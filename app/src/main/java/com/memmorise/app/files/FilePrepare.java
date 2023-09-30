package com.memmorise.app.files;

public class FilePrepare {

    private DiskWorker diskWorker;

    public FilePrepare() {
        diskWorker = new DiskWorker();
        try {
            diskWorker.setup();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }





}
