/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia.base.metier.carte;

import ia.base.metier.TypeMouvement;
import ia.base.metier.cases.Case;
import ia.base.metier.cases.FabriqueCase;
import static ia.base.metier.cases.TypeCase.HERBE;
import static ia.base.metier.cases.TypeCase.TERRE;
import ia.base.metier.objets.FabriqueObjet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 *
 * @author theo clere
 */
public class Carte{
    private int taille;
    private HashMap<Coordonnee,Case> cases;
    private Coordonnee CoordonneeDepart;
    private ArrayList<Coordonnee> coordonneesMagasin;

    /**
     * Constructeur de la classe Carte
     * @param messageRecu
     */
    public Carte(String messageRecu){
        this.cases = new HashMap<>();
        this.taille = (int)Math.sqrt(messageRecu.length());  
        for(int i=0;i<this.taille;i++){
            for(int j=0;j<this.taille;j++){
                this.ajouterCase(new Coordonnee(i,j),messageRecu.charAt(j+this.taille*i));
                
            }
        }
        this.coordonneesMagasin = new ArrayList<>();
        this.coordonneesMagasin.add(0, new Coordonnee(CoordonneeDepart.getLigne()+2, CoordonneeDepart.getColonne() -4));
        this.coordonneesMagasin.add(1, new Coordonnee(CoordonneeDepart.getLigne()+2, CoordonneeDepart.getColonne() -3));
        for(int k=0 ; k<this.taille ; k++){
            for(int m=0 ; m<this.taille ; m++){
                Coordonnee cooCase = new Coordonnee(k,m);
                for(TypeMouvement mouvement : TypeMouvement.values()){
                    Coordonnee cooVoisin = cooCase.getVoisin(mouvement);
                    if(this.cases.get(cooVoisin) != null){
                        this.cases.get(cooCase).ajouterVoisin(this.cases.get(cooVoisin));
                    }
                }
            }        
        }
        
        
        Coordonnee c = this.CoordonneeDepart.getVoisin(TypeMouvement.BOTTOM);
        FabriqueObjet.creer(this.cases.get(c),'S');
        Coordonnee c1 = c.getVoisin(TypeMouvement.RIGHT);
        Coordonnee c2 = c.getVoisin(TypeMouvement.LEFT);
        FabriqueObjet.creer(this.cases.get(c1),'S');
        FabriqueObjet.creer(this.cases.get(c2),'S');
        
        
        
        
    }
    
    /**
     * Renvoie la taille de la carte
     * @return
     */
    public int size(){
        return this.taille;
    }
    
    private void ajouterCase(Coordonnee coordonnee, Character lettre){
        Case c = FabriqueCase.creer(coordonnee, lettre);
        this.cases.put(coordonnee,c);
        
        if(lettre == 'D'){
            this.CoordonneeDepart = coordonnee;
        }
    }
    
    /**
     * Affiche la carte dans la console
     */
    public void afficheConsole(){
        for(int i=0;i<this.taille; i++){
            for(int j=0;j<this.taille; j++){
                String affichage = "E";
                Case caseEnCours = (Case) this.cases.get(new Coordonnee(i,j));
                if(caseEnCours.getType() == HERBE){
                    if(caseEnCours.getObjet() == null){
                        affichage = "H";
                    }
                    else{
                        switch(caseEnCours.getObjet().getType()){
                            case ARBRE : 
                                affichage = "A";
                                break;
                            case MAISON: 
                                affichage = "M";
                                break;
                            case ESCALIER : 
                                affichage = "S";
                                break;   
                            case DEPART : 
                                affichage = "D";
                                break; 
                        }
                    }
                }else if(caseEnCours.getType() == TERRE){
                    affichage = "T";
                }
                System.out.println(affichage);
            }
            System.out.println("");
        }
    }

    /**
     * Renvoie les coordonnées de la case départ
     * @return
     */
    public Coordonnee getCoordonneeDepart() {
        return CoordonneeDepart;
    }
    
    /**
     * Renvoie la case aux coordonnées donnés en paramètre
     * @param coordonnee
     * @return
     */
    public Case getCase(Coordonnee coordonnee){
        return this.cases.get(coordonnee);
    }
    
    /**
     * Renvoie toutes les cases de la carte
     * @return
     */
    public Collection<Case> getCases(){
        return this.cases.values();
    }

    /**
     * Renvoie les coordonnées du magasin
     * @return
     */
    public ArrayList<Coordonnee> getCoordonneesMagasin() {
        return coordonneesMagasin;
    }

    /**
     * Change les coordonnées du magasin poour ceux passées en paramètre
     * @param coordonneesMagasin
     */
    public void setCoordonneesMagasin(ArrayList<Coordonnee> coordonneesMagasin) {
        this.coordonneesMagasin = coordonneesMagasin;
    }

}
