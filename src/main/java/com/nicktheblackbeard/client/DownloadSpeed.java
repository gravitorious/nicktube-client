package com.nicktheblackbeard.client;
import com.nicktheblackbeard.client.*;
import fr.bmartel.speedtest.SpeedTestReport;
import fr.bmartel.speedtest.inter.ISpeedTestListener;
import fr.bmartel.speedtest.model.SpeedTestError;
import fr.bmartel.speedtest.SpeedTestSocket;

import java.io.IOException;
import java.math.BigDecimal;


/**
 * @author nicktheblackbeard
 * 9/6/21
 */
public class DownloadSpeed{

    public static BigDecimal downloadSpeed;
    SpeedTestSocket speedTestSocket;


    /*
        1)run contructor
        2)call countSpeed
        3)wait 10+ seconds to get the result on downloadSpeed
     */
    public DownloadSpeed(){
        //ClientConnection con = new ClientConnection();
        speedTestSocket = new SpeedTestSocket();

// add a listener to wait for speedtest completion and progress
        speedTestSocket.addSpeedTestListener(new ISpeedTestListener() {


            public void onCompletion(SpeedTestReport report) {
                // called when download/upload is complete
                System.out.println("[COMPLETED] rate in octet/s : " + report.getTransferRateOctet());
                System.out.println("[COMPLETED] rate in bit/s   : " + report.getTransferRateBit());
                downloadSpeed = report.getTransferRateBit();
            }


            public void onError(SpeedTestError speedTestError, String errorMessage) {
                // called when a download/upload error occur
            }


            public void onProgress(float percent, SpeedTestReport report) {
                // called to notify download/upload progress

                System.out.println("[PROGRESS] progress : " + percent + "%");
                System.out.println("[PROGRESS] rate in octet/s : " + report.getTransferRateOctet());
                System.out.println("[PROGRESS] rate in bit/s   : " + report.getTransferRateBit());
            }
        });
    }
    public void countSpeed(){
        speedTestSocket.startFixedDownload("http://ipv4.ikoula.testdebit.info/100M.iso", 10000);
    }
}
