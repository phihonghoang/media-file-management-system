package io;

import domainLogic.MediaUploadableAdmin;

import java.io.*;

public class MediaUploadablePersistence {

    public void save(String filename, MediaUploadableAdmin model) {

        try (FileOutputStream fos = new FileOutputStream(filename);
             ObjectOutputStream oos = new ObjectOutputStream(fos);) {

            oos.writeObject(model);

        } catch (FileNotFoundException e ) {
            System.out.println("Datei nicht gefunden: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Ein-/Ausgabe-Fehler: " + e.getMessage());
        }

    }

    public MediaUploadableAdmin load(String filename) {
        MediaUploadableAdmin model = null;

        try (FileInputStream fis = new FileInputStream(filename);
             ObjectInputStream ois = new ObjectInputStream(fis);) {

            model = (MediaUploadableAdmin) ois.readObject();

        } catch (FileNotFoundException e) {
            System.out.println("Datei nicht gefunden: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Ein-/Ausgabe-Fehler: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Klasse nicht gefunden: " + e.getMessage());
        }

        return model;
    }

}
