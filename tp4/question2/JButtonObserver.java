package question2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.TextArea;

/**
 * DÃ©crivez votre classe JButtonObserver ici.
 * 
 * @author Agatha Khairallah
 * @version 1.0
 */
public class JButtonObserver implements ActionListener{ 

    private String nom;
    private TextArea contenu;

    /**
     * Constructeur d'objets de classe JButtonObserver
     * 
     * @param nom
     *            le nom du bouton, jbo1, jbo2, jbo3, jmo1, jmo2, jmo3
     * @param contenu
     *            la zone de texte de l'applette
     */
    public JButtonObserver(String nom, TextArea contenu) {
        this.nom = nom;
        this.contenu = contenu;
    }

    /**
     * affichage d'un message dans la zone de texte ce message est de la forme
     * observateur this.nom : clic du bouton nom_du_bouton exemple : observateur
     * jbo1 : clic du bouton A, voir la mÃ©thode getActionCommand()
     * 
     * @param Ã 
     *            complÃ©ter
     */
    public void actionPerformed (ActionEvent ae) {
        /**
         * String getActionCommand() :
         * Renvoie la commande associée à l'action sous forme 
         * d'une chaîne de caractère
         */  
        String message = "observateur " + this.nom + " : clic du bouton " + ae.getActionCommand();
        contenu.append(message + "\n");
    }

}

