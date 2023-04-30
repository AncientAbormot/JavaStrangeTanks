package project.something.utils;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static BufferedImage resize(BufferedImage image, int width, int height) {

        BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        newImage.getGraphics().drawImage(image, 0, 0, width, height, null);
        return newImage;
    }
    public static final Integer[] st2int_arrays(String[] sArr) {
        Integer[] result = new Integer[sArr.length];
        for (int i = 0; i < sArr.length; i++) {
            result[i] = Integer.parseInt(sArr[i]);
        }
        return result;
    }
    public static Integer[][] lvlParser(String filePath) {
        Integer[][] result = null;
        try (BufferedReader resize = new BufferedReader(new FileReader(new File(filePath)))) {
            String line = null;
            List<Integer[]> lvlLines = new ArrayList<>();
            while ((line = resize.readLine()) != null) {
                lvlLines.add(st2int_arrays(line.split(" ")));
            }
            result = new Integer[lvlLines.size()][lvlLines.get(0).length];

                for (int i = 0; i < lvlLines.size(); i++) {
                    result[i] = lvlLines.get(i);}
            return result;
        } catch (IOException e){
            throw new RuntimeException(e);
        }


    }

}