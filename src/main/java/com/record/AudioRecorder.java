package com.record;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class AudioRecorder {
    private File wavFile;
    private AudioFileFormat.Type fileType;
    private TargetDataLine line;

    private static AudioFormat format;

    public AudioRecorder() {
        wavFile = new File("recorded.wav");
        fileType = AudioFileFormat.Type.WAVE;
    }

    private AudioFormat getAudioFormat() {
        final int sampleSizeInBits = 16;
        final float sampleRate = 16000;
        final int channels = 1;
        final boolean signed = true;
        final boolean bigEndian = false;

        if (format == null)
            format = new AudioFormat(sampleRate, sampleSizeInBits, channels, signed, bigEndian);

        return format;
    }


    public void start() {
        try {
            AudioFormat format = getAudioFormat();
            DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);

            if (!AudioSystem.isLineSupported(info)) {
                System.out.println("Not supported");
            }

            line = (TargetDataLine) AudioSystem.getLine(info);
            line.open(format);
            line.start();

            AudioInputStream ais = new AudioInputStream(line);
            AudioSystem.write(ais, fileType, wavFile);
        } catch (LineUnavailableException ex) {
            System.out.println("Not supported");
        } catch (IOException ex) {
            System.out.println("IO exception");
        }
    }

    public File stop() {
        line.stop();
        line.close();
        return wavFile;
    }
}
