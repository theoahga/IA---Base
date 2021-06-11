package ia.base.modules;

import ia.base.IA;
import ia.base.modules.automate.Automate;
import metier.actions.Action;


/**
 * Module en charge de la prise de décision
 * @author Théo clere
 */
public class Module_Decision extends Module {
    
    private Automate automate; 
    /**
     * Constructeur
     * @param ia l'IA dont ce module fait partie
     */
    public Module_Decision(IA ia) {
        super(ia);
        this.automate = new Automate(this);
    }

    /**
     * Méthode principale de prise de décision
     * @param messageRecu dernier message reçu par l'IUA
     * @return le message à envoyer au serveur
     */
    public String determinerNouvelleAction(String messageRecu) {
        Action a =this.automate.evoluer();
        this.getIA().getModuleMemoire().effectuerAction(a);
        return a.getMessage();
    }
    

}
