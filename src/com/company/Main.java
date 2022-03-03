package com.company;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {

    public static void main(String[] args) {
        GameProgress levelOne = new GameProgress(100, 2, 1, 54.5);
        GameProgress levelTwo = new GameProgress(200, 5, 2, 123);
        GameProgress levelThree = new GameProgress(300, 7, 3, 241.1);
        File save1 = new File(saveGame("D://Games/savegame/save1.dat", levelOne));
        File save2 = new File(saveGame("D://Games/savegame/save2.dat", levelTwo));
        File save3 = new File(saveGame("D://Games/savegame/save3.dat", levelThree));
        List<String> saveList = new ArrayList<>();
        saveList.add("D://Games/savegame/save1.dat");
        saveList.add("D://Games/savegame/save2.dat");
        saveList.add("D://Games/savegame/save3.dat");

        zipFiles("D://Games/savegame/zip.zip", saveList);
        save1.delete();
        save2.delete();
        save3.delete();


    }

    public static String saveGame(String savegame, GameProgress gameProgress) {
        try (FileOutputStream fos = new FileOutputStream(savegame);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(gameProgress);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ;
        }

        return savegame;
    }

    public static void zipFiles(String dirZip, List<String> saveList) {
        try (ZipOutputStream zip = new ZipOutputStream(new FileOutputStream(dirZip, true))) {
            for (String save : saveList) {
                FileInputStream fis = new FileInputStream(save);
                ZipEntry entry = new ZipEntry(save);
                zip.putNextEntry(entry);
                byte[] buffer = new byte[fis.available()];
                fis.read(buffer);
                fis.close();
                zip.write(buffer);
                zip.closeEntry();

            }


        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ;
        }
    }


}
