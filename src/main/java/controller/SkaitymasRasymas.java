package controller;

import model.KursuMokymoSistema;

import java.io.*;

public class SkaitymasRasymas implements Serializable{

    public static KursuMokymoSistema loadFromFile(String fileName){

        KursuMokymoSistema kursuMokymoSistema = null;
        try{
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(fileName));
            kursuMokymoSistema = (KursuMokymoSistema) objectInputStream.readObject();
            objectInputStream.close();
        } catch (ClassNotFoundException ce) {
            ce.printStackTrace();
        } catch (Exception io){
            io.printStackTrace();
        }
        return kursuMokymoSistema;
    }

    public static void writeToFile(String fileName, KursuMokymoSistema kursuMokymoSistema){
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName));
            outputStream.writeObject(kursuMokymoSistema);
            outputStream.close();
        }   catch (IOException io){
            io.printStackTrace();
        }
    }

}
