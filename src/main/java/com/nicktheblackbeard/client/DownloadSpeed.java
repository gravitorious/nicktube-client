package com.nicktheblackbeard.client;
import fr.bmartel.speedtest.SpeedTestReport;
import fr.bmartel.speedtest.inter.ISpeedTestListener;
import fr.bmartel.speedtest.model.SpeedTestError;
import fr.bmartel.speedtest.SpeedTestSocket;

import java.math.BigDecimal;


/**
 * @author nicktheblackbeard
 * 9/6/21
 */
public class DownloadSpeed{

    public static BigDecimal downloadSpeed;
    public static float floatDownloadSpeed;
    SpeedTestSocket speedTestSocket;


    /*
        1)run this contructor
        2)call countSpeed
        3)wait for 10+ seconds to get the result on downloadSpeed
     */
    public DownloadSpeed(){
        speedTestSocket = new SpeedTestSocket();

// add a listener to wait for speedtest completion and progress
        speedTestSocket.addSpeedTestListener(new ISpeedTestListener() {


            public void onCompletion(SpeedTestReport report) {
                downloadSpeed = report.getTransferRateBit();
            }


            public void onError(SpeedTestError speedTestError, String errorMessage) {
                // called when a download/upload error occur
            }


            public void onProgress(float percent, SpeedTestReport report) {
                // called to notify download/upload progress
            }
        });
    }
    public void countSpeed(){
        speedTestSocket.startFixedDownload("http://ipv4.ikoula.testdebit.info/100M.iso", 8000);
    }
}
