package image_char_matching;

import java.util.*;

public class SubImgCharMatcher {

    private List<Character> charset;
    private HashMap<Character, Double> charBrightnessMap = new HashMap<>();

    public SubImgCharMatcher(Character [] charset) {
        this.charset = Arrays.asList(charset);
        initCharBrightnessMap(this.charset);
    }

    public char getCharByImageBrightness(double brightness) {
        char closestChar = charset.getFirst();
        double minDiff = Math.abs(charBrightnessMap.get(closestChar) - brightness);
        for (char c : charset) {
            double diff = Math.abs(charBrightnessMap.get(c) - brightness);
            if (diff < minDiff) {
                minDiff = diff;
                closestChar = c;
            }
        }
        return closestChar;
    }

    public void addChar(char c) {
        charset.add(c);
        //@TODO here we can make it better by not creating the map again we can save the calculated brightness and add the new char to the map
        initCharBrightnessMap(charset);
    }

    public void removeChar(char c) {
        charset.remove(c);
        //@TODO here we can make it better by not creating the map again we can save the calculated brightness and add the new char to the map
        initCharBrightnessMap(charset);
    }

    //@TODO This method was added to be able to fetch and was not on the original code
    public List<Character> getCharset() {
        return charset;
    }

    private void initCharBrightnessMap(List<Character> charset) {
        LinkedList<Double> brightness = calculateBrightnessForChars(this.charset);
        LinkedList<Double> normalized_brightness = normalizeBrightnessList(brightness);
        charBrightnessMap = new HashMap<>();
        for (int i = 0; i < charset.size(); i++) {
            charBrightnessMap.put(charset.get(i), normalized_brightness.get(i));
        }
    }

    private LinkedList<Double> calculateBrightnessForChars(List<Character> charset) {
        LinkedList<Double> brightnessList = new LinkedList<>();
        for (char c : charset) {
            double brightness = getBrightness(c);
            brightnessList.add(brightness);
        }
        return brightnessList;
    }

    private LinkedList<Double> normalizeBrightnessList(List<Double> brightnessList) {
        double max = brightnessList.stream().max(Double::compare).get();
        double min = brightnessList.stream().min(Double::compare).get();
        LinkedList<Double> normalizedList = new LinkedList<>();
        for (double brightness : brightnessList) {
            normalizedList.add((brightness - min) / (max - min));
        }
        return normalizedList;
    }

    private static double getBrightness(char c) {
        boolean[][] charMatrix = CharConverter.convertToBoolArray(c);
        double brightness = 0;
        for (int y = 0; y < charMatrix.length; y++) {
            for (int x = 0; x < charMatrix[y].length; x++) {
                if (charMatrix[y][x]) {
                    brightness++;
                }
            }
        }
        brightness /= charMatrix.length * charMatrix[0].length;
        return brightness;
    }

}
