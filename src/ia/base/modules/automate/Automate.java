/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia.base.modules.automate;

import ia.base.modules.Module_Decision;
import ia.base.modules.Module_Memoire;
import ia.base.modules.automate.etats.EtatInitial;
import java.util.ArrayList;
import metier.actions.Action;

/**
 *
 * @author theo clere
 */
public class Automate {
    private Module_Decision ModuleDecision;
    private ArrayList<Action> listeDesActionArealiser;
    private Etat etatCourant;

    /**
     * Construcetur de la classe Automate
     * @param ModuleDecision
     */
    public Automate(Module_Decision ModuleDecision) {
        this.ModuleDecision = ModuleDecision;
        this.etatCourant = new EtatInitial(this);
        this.listeDesActionArealiser = new ArrayList<>();
        
    }

    /**
     * Renvoie le module décision
     * @return
     */
    public Module_Decision getModuleDecision() {
        return ModuleDecision;
    }

    /**
     * Renvoie le module Memoire
     * @return
     */
    public Module_Memoire getModuleMemoire() {
        return this.ModuleDecision.getIA().getModuleMemoire();
    }

    /**
     * Renvoie la liste des actions à réaliser
     * @return
     */
    public ArrayList<Action> getListeDesActionArealiser() {
        return listeDesActionArealiser;
    }
    
    /**
     * Fait évoler l'automate
     * @return
     */
    public Action evoluer(){
        Action prochaineAction = null;
        while(prochaineAction == null){
            this.etatCourant = this.etatCourant.transition();
            prochaineAction = this.etatCourant.action();
        }
        return prochaineAction;
        
    }
}
