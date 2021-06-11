package ia.base.modules;

import ia.base.IA;
import ia.base.metier.Joueur;
import ia.base.metier.carte.Carte;
import ia.base.metier.carte.Coordonnee;
import ia.base.metier.carte.ressources.TypeRessource;
import static ia.base.metier.carte.ressources.TypeRessource.BOIS;
import static ia.base.metier.carte.ressources.TypeRessource.CAULIFLOWERMATURE;
import static ia.base.metier.carte.ressources.TypeRessource.CAULIFLOWERSEED;
import static ia.base.metier.carte.ressources.TypeRessource.CHICKEN;
import static ia.base.metier.carte.ressources.TypeRessource.EAU;
import static ia.base.metier.carte.ressources.TypeRessource.EGG;
import static ia.base.metier.carte.ressources.TypeRessource.GOLD;
import static ia.base.metier.carte.ressources.TypeRessource.MAYONNAISE;
import static ia.base.metier.carte.ressources.TypeRessource.PARSNIPMATURE;
import static ia.base.metier.carte.ressources.TypeRessource.PARSNIPSEED;
import ia.base.metier.cases.Case;
import ia.base.metier.objets.FabriqueObjet;
import ia.base.metier.objets.Objet;
import ia.base.metier.objets.Plante;
import ia.base.metier.objets.TypeObjet;
import java.util.ArrayList;
import java.util.HashMap;
import metier.actions.Action;
import static metier.actions.TypeAction.ACHAT;
import static metier.actions.TypeAction.MOUVEMENT;
import static metier.actions.TypeAction.PLANTER;
import static metier.actions.TypeAction.RECOLTE;

/**
 * Module en charge de la mémorisation et de la restitution des informations obtenues
 * @author Matthieu & theo clere
 */
public class Module_Memoire extends Module  {
    
    private Carte carte;
    private Joueur joueur;
    private HashMap<TypeRessource, Integer> inventaire ;
    private HashMap<TypeRessource, Integer> stockMagasin ;
    private ArrayList<Plante> listePlantes;
    
    /**
     * Constructeur
     * @param ia l'IA dont ce module fait partie
     */
    public Module_Memoire(IA ia) {
        super(ia);
        this.inventaire = new HashMap<>();
        this.listePlantes = new ArrayList<>();
        this.stockMagasin = null;
        this.inventaire.put(GOLD, 500);
        this.inventaire.put(BOIS, 0);
        this.inventaire.put(PARSNIPSEED, 0);
        this.inventaire.put(PARSNIPMATURE, 0);
        this.inventaire.put(CAULIFLOWERSEED, 0);
        this.inventaire.put(CAULIFLOWERMATURE, 0);
        this.inventaire.put(CHICKEN, 0);
        this.inventaire.put(EGG, 0);
        this.inventaire.put(MAYONNAISE, 0);
        this.inventaire.put(EAU,20);
    }

    /**
     * Renvoie si le module mémoire a un Joueur
     * @return
     */
    public boolean hasJoueur(){
        boolean b=false;
        if(this.joueur != null){
            b = true;
        }
        return b;
    }

    /**
     * Renvoie la carte du module mémoire
     * @return
     */
    public Carte getCarte() {
        return carte;
    }
        
    /**
     * Renvoie le Joueur du module mémoire
     * @return
     */
    public Joueur getJoueur() {
        return joueur;
    }
    
    /**
     * Génere un Joueur aux coordonnees passées
     * @param coordonnee
     */
    public void genererJoueur(Coordonnee coordonnee){
        this.joueur = new Joueur(coordonnee);
    }
    
    /**
     * Génere la carte grâce au message recu de la part du serveur
     * @param messageRecu
     */
    public void genererCarte(String messageRecu){
        this.carte = new Carte(messageRecu);
        this.genererJoueur(this.carte.getCoordonneeDepart());
        System.out.println("[Joueur]"+this.joueur.getCoordonnee().toString());
    }
    
    /**
     * Renvoie si la module mémoire à une carte 
     * @return
     */
    public boolean hasCarte(){
        boolean b = false;
        if(this.carte != null){
            b = true;
        }
        return b;
    }
    
    /**
     * Renvoie la case où se situe le joueur
     * @return
     */
    public Case getCaseJoueur(){
        Case c = this.carte.getCase(this.joueur.getCoordonnee());
        return c;
    }
    
    /**
     * Permet d'effectuer une action de la liste des actions à réaliser
     * @param action
     */
    public void effectuerAction(Action action){
        switch(action.getType()){
            case MOUVEMENT:
                this.joueur.deplacer(action.getDirection());
                break;
            case RECOLTE:
                if(action.getDirection() != null){
                Case caseDestination = this.carte.getCase(this.getCaseJoueur().getCoordonnee().getVoisin(action.getDirection()));
                this.recolter(caseDestination.getObjet());
                caseDestination.setObjet(null);
                }
                break;
            case ACHAT:
                if(action.getTypeRessource() ==  TypeRessource.PARSNIPSEED){
                this.inventaire.replace(TypeRessource.GOLD, inventaire.get(TypeRessource.GOLD) - 20);
                this.inventaire.replace(TypeRessource.PARSNIPSEED, inventaire.get(TypeRessource.PARSNIPSEED) + 1);
                this.stockMagasin.replace(TypeRessource.PARSNIPSEED, stockMagasin.get(TypeRessource.PARSNIPSEED) - 1 );
                }else if (action.getTypeRessource() ==  TypeRessource.CAULIFLOWERSEED){
                this.inventaire.replace(TypeRessource.GOLD, inventaire.get(TypeRessource.GOLD) - 80);
                this.inventaire.replace(TypeRessource.PARSNIPSEED, inventaire.get(TypeRessource.PARSNIPSEED) + 1);
                this.stockMagasin.replace(TypeRessource.PARSNIPSEED, stockMagasin.get(TypeRessource.PARSNIPSEED) - 1 );
                }
                break;
            case PLANTER: 
                this.addPlante(TypeObjet.PANAIS);
                this.inventaire.replace(TypeRessource.PARSNIPSEED, this.inventaire.get(TypeRessource.PARSNIPSEED) - 1);
                break;
            case ARROSER:
                for(Plante p : this.listePlantes){
                    if(p.getPosition() == this.getCaseJoueur()){
                        this.inventaire.replace(EAU,this.inventaire.get(EAU)-1);
                        p.setEstArrosee(true);
                    }
                }
                break;
            case ACTIONSTATIQUE:
                this.stockMagasin = null;
                for(Plante p : this.listePlantes){
                     p.grandir();
                    p.setEstArrosee(false);
                }
                break;       
            case REMPLIR:
                this.inventaire.replace(EAU,20);
                break;
            case CUEILLIR:
                recolterPlante();
        }
        
    }
    
    
    private void recolter(Objet objet){
        if(objet != null){ 
            for (TypeRessource ressource: inventaire.keySet()) {
                for (TypeRessource ressourceObjet : objet.getLoot().keySet()) {
                   if(ressource == ressourceObjet){
                       this.inventaire.replace(ressourceObjet, inventaire.get(ressourceObjet) + objet.getLoot().get(ressourceObjet));
                   } 
                }
            }
        }
    }
    
    /**
     * Renvoie la quantité restante d'une ressource passé en paraamètre
     * @param type
     * @return
     */
    public int getQuantiteRessource(TypeRessource type){
        return this.inventaire.get(type);
    }
    
    /**
     * Génére les stocks du magasin
     * @param nbGrainePanais
     * @param nbGraineChoufleur
     */
    public void genererStockMagasin(int nbGrainePanais, int nbGraineChoufleur){
        HashMap<TypeRessource, Integer> a = new HashMap<>();
        a.put(PARSNIPSEED, nbGrainePanais);
        a.put(CAULIFLOWERSEED,nbGraineChoufleur);
        this.stockMagasin = a;
    }
    
    /**
     * Renvoie le module mémoire possède un Stock Magasin
     * @return
     */
    public boolean hasStockMagasin(){
        boolean b = false;
        if(this.stockMagasin != null){
            b = true;
        }
        
        return b;
    }
    
    /**
     * Renvoie la quantité restante d'une ressource daans le stocks magasin
     * @param type
     * @return
     */
    public int getStockMagasin(TypeRessource type){
        return this.stockMagasin.get(type);
    }
    
    /**
     * Ajoute une plante à la liste
     * @param type
     */
    private void addPlante(TypeObjet type){
        Objet o = FabriqueObjet.creerPlante(this.getCaseJoueur(), type);
        this.getCaseJoueur().setObjet(o);
        this.listePlantes.add((Plante) o);
    }
    
    /**
     * Récolte la plante où se trouve le Joueur
     */
    private void recolterPlante(){
        Objet o = this.getCaseJoueur().getObjet();
        this.recolter(o);
        this.listePlantes.remove(o);
        this.getCaseJoueur().setObjet(null);
    }

    /**
     * Renvoie la liste des plantes
     * @return
     */
    public ArrayList<Plante> getListePlantes() {
        return listePlantes;
    }
    
}
