package com.RecordMic;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;

import marytts.util.data.audio.AudioPlayer;


public class RecordMic {
	RecordMic(){
		final long RECORD_TIME = 1000;  // 1 minute
		 
	 
	    // the line from which audio data is captured
	    TargetDataLine line;
	        float sampleRate = 16000;
	        int sampleSizeInBits = 8;
	        int channels = 2;
	        boolean signed = true;
	        boolean bigEndian = true;
	        AudioFormat format = new AudioFormat(sampleRate, sampleSizeInBits,
	                                             channels, signed, bigEndian);
 
	        try {
	        	DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
 
	        	// checks if system supports the data line
	        	if (!AudioSystem.isLineSupported(info)) {
	        		System.out.println("Line not supported");
	        		System.exit(0);
            }
            line = (TargetDataLine) AudioSystem.getLine(info);
            line.open(format);
            line.start();   // start capturing
            System.out.println("Start capturing...");
            AudioInputStream ais = new AudioInputStream(line);
            System.out.println("Start recording...");           
            try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
            
            AudioPlayer ap = new AudioPlayer();
			AudioInputStream audio = ais;
			ap.setAudio(audio);			
			ap.start();
			System.out.println("Start playing...");
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			line.stop();
            line.close();
            System.out.print("\n");
 
        } catch (LineUnavailableException ex) {
            ex.printStackTrace();
        }

	    System.out.println("Finished");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new RecordMic();
	}
}
