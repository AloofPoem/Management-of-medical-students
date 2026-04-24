package com.gestionestudiantesmedicina.daos;

import java.io.File;
import java.nio.file.Files;

import javax.persistence.EntityManager;

import com.gestionestudiantesmedicina.entities.Prueba;

public class PruebaDAO extends AbstractGenericDAO<Prueba, Long> {

    public PruebaDAO(){
        super(Prueba.class);
    }

    public void guardarArchivo(String ruta){
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        File archivo = new File(ruta);
        byte[] bytePDF;
        try {
            bytePDF = Files.readAllBytes(archivo.toPath());
            
        } catch (Exception e) {
            System.out.println("no.");
            return;
        }

        Prueba prueba = new Prueba();
        prueba.setContenido(bytePDF);

        em.persist(prueba);
        em.getTransaction().commit();;
        em.close();
        
    }

}
