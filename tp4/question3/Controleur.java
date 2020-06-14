package question3;


import question3.tp3.PileI;
import question3.tp3.PilePleineException;
import question3.tp3.PileVideException;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * DÃ©crivez votre classe Controleur ici.
 * 
 * @author Agatha Khairallah
 * @version 1.0
 */
public class Controleur extends JPanel {

    private JButton push, add, sub, mul, div, clear;
    private PileModele<Integer> pile;
    private JTextField donnee;

    public Controleur(PileModele<Integer> pile) {
        super();
        this.pile = pile;
        this.donnee = new JTextField(8);

        this.push = new JButton("push");
        this.add = new JButton("+");
        this.sub = new JButton("-");
        this.mul = new JButton("*");
        this.div = new JButton("/");
        this.clear = new JButton("[]");

        setLayout(new GridLayout(2, 1));
        add(donnee);
        JPanel boutons = new JPanel();
        boutons.setLayout(new FlowLayout());

        /**
         * Création des classes internes XXXOperation. XXX étant le
         * nom du bouton concerné
         */

        boutons.add(push);  push.addActionListener(new pushOperation());
        boutons.add(add);   add.addActionListener(new addOperation());
        boutons.add(sub);   sub.addActionListener(new subOperation());
        boutons.add(mul);   mul.addActionListener(new mulOperation());
        boutons.add(div);   div.addActionListener(new divOperation());
        boutons.add(clear); clear.addActionListener(new clearOperation());
        add(boutons);
        boutons.setBackground(Color.red);
        actualiserInterface();
    }

    public void actualiserInterface() {
        if (pile.estPleine())
            push.setEnabled(false);
        else if (pile.estVide()){
            push.setEnabled(true);
            clear.setEnabled(false);
            add.setEnabled(false);
            sub.setEnabled(false);
            mul.setEnabled(false);
            div.setEnabled(false);
        } else if (pile.taille() == 1){
            push.setEnabled(true);
            clear.setEnabled(true);
            add.setEnabled(false);
            sub.setEnabled(false);
            mul.setEnabled(false);
            div.setEnabled(false);
        } else {
            push.setEnabled(true);
            clear.setEnabled(true);
            add.setEnabled(true);
            sub.setEnabled(true);
            mul.setEnabled(true);
            div.setEnabled(true);
        }
    }

    private Integer operande() throws NumberFormatException {
        return Integer.parseInt(donnee.getText());
    }
    /**
     * Dans les méthodes actionPerformed, l'utilisation de la 
     * fonction operande() déjà fournie permet de vérifier si la 
     * valeur du champ "donnee" est un Integer. Dans le 
     * cas d'échec, celle-ci retournera une exception 
     * de type "NumberFormatException"
     */  

    private class pushOperation implements ActionListener {
        public void actionPerformed (ActionEvent ae){
            try {
                pile.empiler(operande());
            }
            catch (NumberFormatException nfe){}
            catch (PilePleineException pve){}

            /**
             * Lorsque le calcul est effectué, des modifications
             * auront lieu alors il faut actualiser l'interface
             */ 
            actualiserInterface();
        }
    }

    private class addOperation  implements ActionListener {
        public void actionPerformed (ActionEvent ae){
            int op1 = 0, op2 = 0, valeur = 0;

            try {
                // Obtenir les deux opérandes par dépilation
                op1 = pile.depiler();
                op2 = pile.depiler();

                // Effectuer l'addition
                valeur = op2 + op1;
                pile.empiler(valeur);
            }
            catch (PileVideException pve){}
            catch (PilePleineException ppe){}

            /**
             * Lorsque le calcul est effectué, des modifications
             * auront lieu alors il faut actualiser l'interface
             */ 
            actualiserInterface();
        }
    }

    private class subOperation  implements ActionListener {
        public void actionPerformed (ActionEvent ae){
            int op1 = 0, op2 = 0, valeur = 0;

            try {
                // Obtenir les deux opérandes par dépilation
                op1 = pile.depiler();
                op2 = pile.depiler();

                // Effectuer la soustraction
                valeur = op2 - op1;
                pile.empiler(valeur);
            }
            catch (PileVideException pve){}
            catch (PilePleineException ppe){}

            /**
             * Lorsque le calcul est effectué, des modifications
             * auront lieu alors il faut actualiser l'interface
             */ 
            actualiserInterface();
        }
    }

    private class mulOperation  implements ActionListener {
        public void actionPerformed (ActionEvent ae){
            int op1 = 0, op2 = 0, valeur = 0;

            try {
                // Obtenir les deux opérandes par dépilation
                op1 = pile.depiler();
                op2 = pile.depiler();

                // Effectuer la multiplication
                valeur = op2 * op1;
                pile.empiler(valeur);
            }
            catch (PileVideException pve){}
            catch (PilePleineException ppe){}

            /**
             * Lorsque le calcul est effectué, des modifications
             * auront lieu alors il faut actualiser l'interface
             */ 
            actualiserInterface();
        }
    }

    private class divOperation  implements ActionListener {
        public void actionPerformed (ActionEvent ae){
            int op1 = 0, op2 = 0, valeur = 0;

            try {
                // Obtenir les deux opérandes par dépilation
                op1 = pile.depiler();
                op2 = pile.depiler();

                /**
                 * Effectuer la division.
                 * Attention: si op1 est égale à 0, alors la pile reste
                 * intacte
                 */ 
                if (op1 == 0 ) {
                    pile.empiler(op2);
                    pile.empiler(op1);
                }
                else {
                    valeur = op2 / op1;
                    pile.empiler(valeur);
                }
            }
            catch (PileVideException pve){}
            catch (PilePleineException ppe){}

            /**
             * Lorsque le calcul est effectué, des modifications
             * auront lieu alors il faut actualiser l'interface
             */ 
            actualiserInterface();
        }
    }

    private class clearOperation  implements ActionListener {
        public void actionPerformed (ActionEvent ae){
            while (!pile.estVide()){
                try {
                    pile.depiler();
                }
                catch (PileVideException pve){}
            }

            /**
             * Lorsque la pile est vidée, il faut actualiser 
             * l'interface
             */ 
            actualiserInterface();
        }
    }

    /**
     * Une autre solution consiste à créer un seul Listener 
     * pour tous les boutons
     */

    /* private class ButtonListener implements ActionListener{
        public void actionPerformed (ActionEvent ae){
            String actionName = ae.getActionCommand();
            if (actionName.equals("push")){
                try {
                    pile.empiler(operande());
                }
                catch (NumberFormatException nfe){}
                catch (PilePleineException pve){}
                actualiserInterface();
            }
            else if (actionName.equals("+") || actionName.equals("-")  
            || actionName.equals("*") || actionName.equals("/")){
                int op1 = 0, op2 = 0, valeur = 0;
                boolean estZero = false;

                // Obtenir les deux opérandes par dépilation
                try {
                    op1 = pile.depiler();
                    op2 = pile.depiler();
                }
                catch (PileVideException pve){}

                // Effectuer le calcul approprié
                // Si op1 est égale à 0, alors la pile reste
                // intacte
                switch (actionName){
                    case "+" : 
                        valeur = op2 + op1;
                        break;
                    case "-" : 
                        valeur = op2 - op1;
                        break;
                    case "*" : 
                        valeur = op2 * op1;
                        break;
                    case "/" :
                        if (op1 == 0)
                           estZero = true;
                        else
                           valeur = op2 / op1;
                }
                try {
                     if (estZero){
                        pile.empiler(op2);
                        pile.empiler(op1);
                     }
                     else {
                         pile.empiler (valeur);
                     }
                } 
                catch (PilePleineException pve){}
                actualiserInterface();
            }
            else if (actionName.equals("[]")) {
                while (!pile.estVide()){
                    try {
                        pile.depiler();
                    }
                    catch (PileVideException pve){}
                }
                actualiserInterface();
            }
         }
    } */

}

