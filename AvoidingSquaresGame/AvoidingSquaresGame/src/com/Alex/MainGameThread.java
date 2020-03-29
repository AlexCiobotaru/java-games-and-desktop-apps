package com.Alex;

import java.util.Random;
import javax.swing.JOptionPane;

public class MainGameThread extends Thread {
    private int id;
    private Window win;
    private int processorLoad;

    MainGameThread(int id, int priority, Window win, int processorLoad){
        this.id = id;
        this.win = win;
        this.processorLoad = processorLoad;
        this.setPriority(priority);
    }

    static Random r = new Random(10);

    public void run() {

        int tryCount = 3;
        while (tryCount > 0) {
            this.win.RestartObject(id);
            int c = 0;
            while (!this.win.IsShapeOut(id)) {

                if (this.win.IsCrash(id))
                    break;
                for (int j = 0; j < this.processorLoad; j++) {

                    j++;
                }
                c++;
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                this.win.DropObject(id, r.nextInt(30));

            }
            if (this.win.IsCrash(id))
                JOptionPane.showMessageDialog(null, "Game Over!", "Game Over", JOptionPane.INFORMATION_MESSAGE);

            tryCount--;
        }
    }

}

