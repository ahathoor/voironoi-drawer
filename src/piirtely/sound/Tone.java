/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package piirtely.sound;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sql.rowset.spi.SyncResolver;

/**
 *
 * @author ahathoor
 */
public class Tone {

    public static double SAMPLE_RATE = 44000f;

    enum Note {

        B(new String[]{"B"}, 61.7354),
        As(new String[]{"A#", "Bb"}, 58.2705),
        A(new String[]{"A"}, 55.0000),
        Gs(new String[]{"G#", "Ab"}, 51.9131),
        G(new String[]{"G"}, 48.9994),
        Fs(new String[]{"F#", "Gb"}, 46.2493),
        F(new String[]{"F"}, 43.6535),
        E(new String[]{"E"}, 41.2034),
        Ds(new String[]{"D#", "Eb"}, 38.8909),
        D(new String[]{"D"}, 36.7081),
        Cs(new String[]{"C#", "Db"}, 34.6478),
        C(new String[]{"C"}, 32.7032);
        private final String[] names;
        private final double frequency;

        Note(String[] names, double frequency) {
            this.names = names;
            this.frequency = frequency;
        }
        
        private static HashMap<String, Note> map;

        public static void buildMap() {
            map = new HashMap<>();
            for (Note note : Note.values()) {
                for (String name : note.names) {
                    map.put(name, note);
                }
            }
        }

        public static double frequencyOf(String notename, int multiplier) {
            if (map == null) {
                buildMap();
            }
            if (map.containsKey(notename))
                return map.get(notename).frequency * Math.pow(2, multiplier);
//            for (Note note : Note.values()) {
//                for (String name : note.names) {
//                    if (name.equals(notename)) {
//                        return (note.frequency * Math.pow(2, multiplier));
//                    }
//                }
//            }
            throw new IllegalArgumentException("No such note " + notename);
        }
    }

    public static void sound(int hz, int msecs, double vol)
            throws LineUnavailableException {

        if (hz <= 0) {
            throw new IllegalArgumentException("Frequency <= 0 hz");
        }

        if (msecs <= 0) {
            throw new IllegalArgumentException("Duration <= 0 msecs");
        }

        if (vol > 1.0 || vol < 0.0) {
            throw new IllegalArgumentException("Volume out of range 0.0 - 1.0");
        }

        byte[] buf = new byte[(int) SAMPLE_RATE * msecs / 1000];

        for (int i = 0; i < buf.length; i++) {
            double angle = i / (SAMPLE_RATE / hz) * 2.0 * Math.PI;
            buf[i] = (byte) (Math.sin(angle) * 127.0 * vol);
        }

        // shape the front and back 10ms of the wave form
        for (int i = 0; i < SAMPLE_RATE / 100.0 && i < buf.length / 2; i++) {
            buf[i] = (byte) (buf[i] * i / (SAMPLE_RATE / 100.0));
            buf[buf.length - 1 - i] =
                    (byte) (buf[buf.length - 1 - i] * i / (SAMPLE_RATE / 100.0));
        }

        AudioFormat af = new AudioFormat((float) SAMPLE_RATE, 8, 1, true, false);
        try (SourceDataLine sdl = AudioSystem.getSourceDataLine(af)) {
            sdl.open(af);
            sdl.start();
            sdl.write(buf, 0, buf.length);
            sdl.drain();
        }
    }

    public static void playTone(String name, int line, int duration, float vol) {
        try {
            sound((int) Note.frequencyOf(name, line), duration, vol);
        } catch (LineUnavailableException ex) {
            Logger.getLogger(Tone.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    static class ToneThread extends Thread {

        static int runnin = 0;
        String name;
        int line;
        int duration;
        float vol;

        public ToneThread(String name, int line, int duration, float vol) {
            this.name = name;
            this.line = line;
            this.duration = duration;
            this.vol = vol;
        }

        private static synchronized void in() {
            runnin++;
        }

        private static synchronized void out() {
            runnin--;
        }

        @Override
        public void run() {
            in();
            Tone.playTone(name, line, duration, vol);
            out();
        }

        public static synchronized boolean running() {
            return runnin != 0;
        }
    }

    public static void main(String[] args) throws
            LineUnavailableException {
        new ToneThread("C", 4, 1000, 1).start();
        new ToneThread("E", 4, 1000, 1).start();
        new ToneThread("G", 4, 1000, 1).start();
        while (ToneThread.running()) {
        }
        new ToneThread("D", 4, 1000, 1).start();
        new ToneThread("F", 4, 1000, 1).start();
        new ToneThread("A", 4, 1000, 1).start();
    }
}
