/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.algorithmes;

import ia.base.metier.TypeMouvement;
import static ia.base.metier.TypeMouvement.BOTTOM;
import static ia.base.metier.TypeMouvement.LEFT;
import static ia.base.metier.TypeMouvement.RIGHT;
import static ia.base.metier.TypeMouvement.TOP;
import ia.base.metier.carte.Carte;
import ia.base.metier.carte.Coordonnee;
import ia.base.metier.cases.Case;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test unitaire du Parcours en largeur (calcul des distances)
 * @author simonetma
 */
public class ParcoursLargeurTest {
    
    /**
     * Test of calculerDistancesDepuis method, of class ParcoursLargeur.
     */
    @Test
    public void testCalculerDistancesDepuis() {
        System.out.println("calculerDistancesDepuis");
        Carte carte = new Carte(
                "TMDMT"
               +"EMMMA"
               +"TTTTH"
               +"HAAAH"
               +"HHHAH" 
        );
                
        Case depart = carte.getCase(new Coordonnee(0,2));
        ParcoursLargeur instance = new ParcoursLargeur(carte);
        
        
        
        instance.calculerDistancesDepuis(depart);           // A débugger mettre point d'arrêt ->débugger le fichier et suivre étape par étape.
                
        assertEquals(null, instance.getDistance(carte.getCase(new Coordonnee(0,0))));
        assertEquals(null, instance.getDistance(carte.getCase(new Coordonnee(0,1))));
        assertEquals(new Integer(0), instance.getDistance(carte.getCase(new Coordonnee(0,2))));
        assertEquals(null, instance.getDistance(carte.getCase(new Coordonnee(0,3))));
        assertEquals(null, instance.getDistance(carte.getCase(new Coordonnee(0,4))));
        
        assertEquals(null, instance.getDistance(carte.getCase(new Coordonnee(1,0))));
        assertEquals(new Integer(2), instance.getDistance(carte.getCase(new Coordonnee(1,1))));
        assertEquals(new Integer(1), instance.getDistance(carte.getCase(new Coordonnee(1,2))));
        assertEquals(new Integer(2), instance.getDistance(carte.getCase(new Coordonnee(1,3))));
        assertEquals(null, instance.getDistance(carte.getCase(new Coordonnee(1,4))));
        
        assertEquals(new Integer(4), instance.getDistance(carte.getCase(new Coordonnee(2,0))));
        assertEquals(new Integer(3), instance.getDistance(carte.getCase(new Coordonnee(2,1))));
        assertEquals(new Integer(2), instance.getDistance(carte.getCase(new Coordonnee(2,2))));
        assertEquals(new Integer(3), instance.getDistance(carte.getCase(new Coordonnee(2,3))));
        assertEquals(new Integer(4), instance.getDistance(carte.getCase(new Coordonnee(2,4))));
        
        assertEquals(new Integer(5), instance.getDistance(carte.getCase(new Coordonnee(3,0))));
        assertEquals(null, instance.getDistance(carte.getCase(new Coordonnee(3,1))));
        assertEquals(null, instance.getDistance(carte.getCase(new Coordonnee(3,2))));
        assertEquals(null, instance.getDistance(carte.getCase(new Coordonnee(3,3))));
        assertEquals(new Integer(5), instance.getDistance(carte.getCase(new Coordonnee(3,4))));
        
        assertEquals(new Integer(6), instance.getDistance(carte.getCase(new Coordonnee(4,0))));
        assertEquals(new Integer(7), instance.getDistance(carte.getCase(new Coordonnee(4,1))));
        assertEquals(new Integer(8), instance.getDistance(carte.getCase(new Coordonnee(4,2))));
        assertEquals(null, instance.getDistance(carte.getCase(new Coordonnee(4,3))));
        assertEquals(new Integer(6), instance.getDistance(carte.getCase(new Coordonnee(4,4))));
    }
    
    
    @Test
    public void testGetChemin() {
        System.out.println("getChemin");
        Carte carte = new Carte(
                "MDMTT"
               +"MMMAT"
               +"TEETH"
               +"HAAAH"
               +"HHHHH" 
        );
                
        Case depart = carte.getCase(new Coordonnee(0,1));
        ParcoursLargeur instance = new ParcoursLargeur(carte);
        instance.calculerDistancesDepuis(depart);
        
        ArrayList<TypeMouvement> result = instance.getChemin(carte.getCase(new Coordonnee(0,3)));
        TypeMouvement[] expResultArray = {BOTTOM,LEFT,BOTTOM,BOTTOM,BOTTOM,RIGHT,RIGHT,RIGHT,RIGHT,TOP,TOP,TOP,TOP,LEFT};
        ArrayList<TypeMouvement> expResult = new ArrayList<>(Arrays.asList(expResultArray));
        
        assertEquals(expResult,result);
        
    }
    
}
